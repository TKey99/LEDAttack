package tkey99.ledattack;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;

import tkey99.ledattack.gameobjects.Box;
import tkey99.ledattack.gameobjects.Player;
import tkey99.ledattack.utilities.BluetoothManager;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Game engine.
 * 
 * @author TKey99
 * 
 */
public class LedAttackEngine extends Thread implements SensorEventListener {

	/**
	 * Gamefield of the game
	 */
	private Gamefield gamefield;

	/**
	 * Player in the game
	 */
	private Player player;

	/**
	 * Boxes in the game
	 */
	private ArrayList<Box> boxes;

	/**
	 * Actual value of the sensor
	 */
	private float sensorValue;

	/**
	 * Value of the sensor to move the player
	 */
	private final float SENSOR_VALUE_TO_MOVE = 1.0f;

	/**
	 * Wait time for the intro
	 */
	private final long INTRO_WAIT_TIME = 1000;

	/**
	 * Wait time for the game calculation
	 */
	private final long LOOP_WAIT_TIME = 80;

	/**
	 * Provides the status of the game
	 */
	private GameStatus gameStatus;

	private double spawnTimer = 0;

	private int score = 0;

	private int bottomBoxCount = 0;

	private final int SCORE_BONUS_FULL_ROW = 50;

	private final int SCORE_BONUS_BOX_DESTROYED = 15;

	private final int JUMP_HEIGHT = 3;

	private int jumpCounter = 0;

	/**
	 * Constructs a new engine
	 */
	public LedAttackEngine() {
		gameStatus = GameStatus.INGAME;
		gamefield = new Gamefield();
		player = new Player();
		boxes = new ArrayList<Box>();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
			float x = event.values[0]; // für kippen
			float y = event.values[1];
			float z = event.values[2];

			sensorValue += x;

		}
	}

	/**
	 * Sends the intro to the led matrix
	 */
	private void showIntro() {
		Log.d("intro", "intro gesendet");
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_READY);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_SET);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_GO);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends the game over screen to the led matrix
	 */
	private void showGameOver() {
		BluetoothManager.getInstance().send(StaticGameFields.GAME_OVER);
	}

	@Override
	public void run() {
		showIntro();

		boxes.add(new Box());
		boxes.add(new Box());
		while (true) {

			// eventually delete bottom boxes and increase score
			deleteBoxes();

			// move player up or down
			movePlayerUpDown();

			// move left or right player and try to push
			movePlayerLeftRight();

			// move boxes and check if player is dead
			moveBoxesDown();
			if (gameStatus == GameStatus.GAME_OVER) {
				showGameOver();
				return;
			}

			// eventually spawn box#
			spawnBox();

			// send gamefield
			gamefield.refresh(boxes, player);
			BluetoothManager.getInstance().send(gamefield.getGamefield());

			// TODO enhance code
			if (gameStatus == GameStatus.PAUSE) {
				BluetoothManager.getInstance().send(StaticGameFields.PAUSE);
				while (gameStatus == GameStatus.PAUSE) {
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				sleep(LOOP_WAIT_TIME);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Changes whether the player is pushing or not
	 * 
	 * @param status
	 *            status to set
	 */
	public void changePushing(boolean status) {
		player.setPushing(status);
	}

	public void changeJumping(boolean status) {
		int testBotX = player.getPosition().getBottomRightX();
		int testBotY = player.getPosition().getBottomRightY();
		if (status) {
			if (player.isAtBottom()
					|| gamefield.getGamefield()[testBotY][testBotX] == Gamefield.LED_ON
					|| gamefield.getGamefield()[testBotY][testBotX - 1] == Gamefield.LED_ON
					|| gamefield.getGamefield()[testBotY][testBotX - 2] == Gamefield.LED_ON) {
				player.setJumping(status);
			}
		} else {
			player.setJumping(status);
		}
	}

	/**
	 * Sets the ingame status
	 * 
	 * @param status
	 *            status to set
	 */
	public void setGameStatus(GameStatus status) {
		gameStatus = status;
	}

	/**
	 * Spawns a new box at a random time
	 */
	private void spawnBox() {

	}

	/**
	 * Moves the player left or right if he can and pushes boxes if player holds
	 * the push button and boxes can be pushed
	 */
	private void movePlayerLeftRight() {
		// TODO pushen einfügen
		int testXTop = player.getPosition().getTopLeftX() - 1;
		int xBot = player.getPosition().getBottomRightX() + 1;
		int yTop = player.getPosition().getTopLeftY();
		int yBot = player.getPosition().getBottomRightY();
		if (sensorValue >= SENSOR_VALUE_TO_MOVE) {
			if (!player.isRight()) {
				if (gamefield.getGamefield()[yBot][xBot] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[yTop
								+ player.getSymbol().length / 2][xBot] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[yTop][xBot] == Gamefield.LED_OFF) {
					player.move(Direction.RIGHT);
					Log.d("player", "move right");
				}
			}
		} else if (sensorValue <= (SENSOR_VALUE_TO_MOVE * -1)) {
			if (!player.isLeft()) {
				if (gamefield.getGamefield()[yTop][testXTop] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[yTop
								+ player.getSymbol().length / 2][testXTop] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[yBot][testXTop] == Gamefield.LED_OFF) {
					player.move(Direction.LEFT);
					Log.d("player", "move left");
				}
			}
		}
	}

	/**
	 * Moves boxes down if possible
	 */
	private void moveBoxesDown() {
		for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
			Box current = iter.next();
			int testX = current.getPosition().getBottomRightX();
			int testY = current.getPosition().getBottomRightY() + 1;

			if (!current.isAtBottom()) {
				if (gamefield.getGamefield()[testY][testX] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testY][testX - 1] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testY][testX - 2] == Gamefield.LED_OFF) {

					current.move(Direction.DOWN);

					if (current.isAtBottom()) {
						bottomBoxCount++;
					} else if (player.isHead(current.getPosition())) {
						if (player.isJumping()) {
							boxes.remove(current);
							score += SCORE_BONUS_BOX_DESTROYED;
						} else {
							setGameStatus(GameStatus.GAME_OVER);
						}
					}
				}
			}
		}
	}

	/**
	 * Deletes bottom row of boxes if it is full and increases score
	 */
	private void deleteBoxes() {
		if (bottomBoxCount >= Gamefield.MAX_LED_X / player.getSymbol().length) {
			for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
				Box current = iter.next();
				if (current.isAtBottom())
					boxes.remove(current);
			}
			score += SCORE_BONUS_FULL_ROW;
		}
	}

	/**
	 * Moves the player up or down
	 */
	private void movePlayerUpDown() {
		int testBotX = player.getPosition().getBottomRightX();
		int testBotY = player.getPosition().getBottomRightY() + 1;
		if (player.isJumping()) {
			if (jumpCounter <= 0) {
				jumpCounter = JUMP_HEIGHT;
			}
			if (!player.isTop()) {
				player.move(Direction.UP);
				jumpCounter--;
				if (jumpCounter <= 0) {
					changeJumping(false);
				}
			}
		} else {
			if (!player.isAtBottom()) {
				if (gamefield.getGamefield()[testBotY][testBotX] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testBotY][testBotX - 1] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testBotY][testBotX - 2] == Gamefield.LED_OFF) {
					player.move(Direction.DOWN);
				}
			}
		}
	}
}
