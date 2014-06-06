package tkey99.ledattack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import tkey99.ledattack.gameobjects.Box;
import tkey99.ledattack.gameobjects.Player;
import tkey99.ledattack.utilities.BluetoothManager;
import tkey99.ledattack.utilities.SoundManager;
import tkey99.ledattack.utilities.VibrationManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
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
	private final long LOOP_WAIT_TIME = 120;

	/**
	 * Provides the status of the game
	 */
	private GameStatus gameStatus;

	private int score = 0;

	private int bottomBoxCount = 0;

	private final int SCORE_BONUS_FULL_ROW = 50;

	private final int SCORE_BONUS_BOX_DESTROYED = 15;

	private final int JUMP_HEIGHT = 3;

	private int jumpCounter;

	private int pushCounter;

	private UpdateListener updateListener;

	/**
	 * Minimum delay of spawning boxes in milliseconds
	 */
	private final int MINIMUM_SPAWN_DELAY = 2000;

	private long spawnTimer = 0;

	/**
	 * Constructs a new engine
	 */
	public LedAttackEngine() {
		gameStatus = GameStatus.INGAME;
		gamefield = new Gamefield();
		player = new Player((Gamefield.MAX_LED_X / 2),
				(Gamefield.MAX_LED_Y / 2));
		boxes = new ArrayList<Box>();
		jumpCounter = JUMP_HEIGHT;
		pushCounter = 3;
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

	@Override
	public void run() {
		// TODO seitlich in kiste reinspringen manchmal bug
		// sounds fertig machen
		// bilder einfügen
		// 3 schritt schiebe regel
		// bei mehrehren kisten aufeinander lässt sich nur die unterste
		// verschieben
		// sensor verfeinern
		// manual updaten

		showIntro();

		while (true) {

			// eventually delete bottom boxes and increase score
			deleteBoxes();

			// move player up or down
			movePlayerUpDown();

			// move boxes
			moveBoxesDown();

			// move left or right player and try to push
			movePlayerLeftRight();

			// eventually spawn box
			spawnBox();

			// send gamefield
			refreshAndSend();

			if (gameStatus == GameStatus.GAME_OVER) {
				VibrationManager.getInstance().vibrate();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				VibrationManager.getInstance().vibrate();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				VibrationManager.getInstance().vibrate();
				notifyUpdateListener();
				return;
			}

			// TODO enhance code
			if (gameStatus == GameStatus.PAUSE) {
				BluetoothManager.getInstance().send(StaticGameFields.PAUSE);
				spawnTimer = 0;
				while (gameStatus == GameStatus.PAUSE) {
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// loop wait
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

	/**
	 * Changes the jump status
	 * 
	 * @param status
	 *            status to set
	 */
	public void changeJumping(boolean status) {
		int testBotX = player.getPosition().getBottomRightX();
		int testBotY = player.getPosition().getBottomRightY() + 1;
		if (status) {
			if (player.isAtBottom()
					|| gamefield.getGamefield()[testBotY][testBotX] == Gamefield.LED_ON
					|| gamefield.getGamefield()[testBotY][testBotX - 1] == Gamefield.LED_ON
					|| gamefield.getGamefield()[testBotY][testBotX - 2] == Gamefield.LED_ON) {
				player.setJumping(status);
				SoundManager.getInstance().playJump();
			}
		} else {
			player.setJumping(status);
		}
	}

	/**
	 * Sets the game status
	 * 
	 * @param status
	 *            status to set
	 */
	public void setGameStatus(GameStatus status) {
		gameStatus = status;
	}

	/**
	 * Spawns a new box at a random time, if box spawns in a box because an old
	 * box is lying there, game over
	 */
	private void spawnBox() {
		long currentTime = Calendar.getInstance().getTimeInMillis();
		if (spawnTimer <= 0) {
			spawnTimer = ((long) (Math.random() * 1000 + MINIMUM_SPAWN_DELAY))
					+ currentTime;
		} else if (currentTime >= spawnTimer) {
			Box box = new Box();
			int testY = box.getPosition().getTopLeftY();
			int testX = box.getPosition().getTopLeftX();
			for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
				Box current = iter.next();
				if (current.getPosition().getTopLeftX() == testX
						&& current.getPosition().getTopLeftY() == testY) {
					gameStatus = GameStatus.GAME_OVER;
					return;
				}
			}
			boxes.add(box);
			spawnTimer = 0;
		}
	}

	/**
	 * Moves the player left or right if he can and pushes boxes if player holds
	 * the push button and boxes can be pushed
	 */
	private void movePlayerLeftRight() {
		int testXTop = player.getPosition().getTopLeftX() - 1;
		int testXBot = player.getPosition().getBottomRightX() + 1;
		int testYTop = player.getPosition().getTopLeftY();
		int testYBot = player.getPosition().getBottomRightY();
		if (sensorValue >= SENSOR_VALUE_TO_MOVE) {
			if (!player.isRight()) {
				if (gamefield.getGamefield()[testYBot][testXBot] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testYTop
								+ player.getSymbol().length / 2][testXBot] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testYTop][testXBot] == Gamefield.LED_OFF) {
					player.move(Direction.RIGHT);
					Log.d("player", "move right");
				} else {
					if (player.isPushing()) {
						tryToPushRight();
					}
				}
			}
		} else if (sensorValue <= (SENSOR_VALUE_TO_MOVE * -1)) {
			if (!player.isLeft()) {
				if (gamefield.getGamefield()[testYTop][testXTop] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testYTop
								+ player.getSymbol().length / 2][testXTop] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testYBot][testXTop] == Gamefield.LED_OFF) {
					player.move(Direction.LEFT);
					Log.d("player", "move left");
				} else {
					if (player.isPushing()) {
						tryToPushLeft();
					}
				}
			}
		}
	}

	/**
	 * Tries to push a box to the right
	 */
	private void tryToPushRight() {
		for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
			Box current = iter.next();
			if (current.getPosition().getBottomRightX()
					- current.getSymbol()[0].length == player.getPosition()
					.getBottomRightX()) {
				if (!current.isRight()
						&& gamefield.getGamefield()[current.getPosition()
								.getBottomRightY()][current.getPosition()
								.getBottomRightX() + 1] == Gamefield.LED_OFF) {
					current.move(Direction.RIGHT);
					player.move(Direction.RIGHT);
				}
				return;
			}
		}
	}

	/**
	 * Tries to push a box to the left
	 */
	private void tryToPushLeft() {
		for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
			Box current = iter.next();
			if (current.getPosition().getBottomRightX() == player.getPosition()
					.getBottomRightX() - player.getSymbol()[0].length) {
				if (!current.isLeft()
						&& gamefield.getGamefield()[current.getPosition()
								.getBottomRightY()][current.getPosition()
								.getBottomRightX()
								- current.getSymbol()[0].length] == Gamefield.LED_OFF) {
					current.move(Direction.LEFT);
					player.move(Direction.LEFT);
				}
				return;
			}
		}
	}

	/**
	 * Moves boxes down if possible or destroy them when hit player while
	 * jumping
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
						Log.d("engine", "hits head");
						if (player.isJumping()) {
							SoundManager.getInstance().playDestroyBox();
							VibrationManager.getInstance().vibrate();
							boxes.remove(current);
							score += SCORE_BONUS_BOX_DESTROYED;
							notifyUpdateListener();
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
		if (bottomBoxCount >= Gamefield.MAX_LED_X
				/ player.getSymbol()[0].length) {
			for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
				if (iter.next().isAtBottom()) {
					iter.remove();
				}
			}
			VibrationManager.getInstance().vibrate();
			score += SCORE_BONUS_FULL_ROW;
			notifyUpdateListener();
			bottomBoxCount = 0;
		}
	}

	/**
	 * Moves the player up or down
	 */
	private void movePlayerUpDown() {
		int testBotX = player.getPosition().getBottomRightX();
		int testBotY = player.getPosition().getBottomRightY() + 1;
		int testTopY = player.getPosition().getTopLeftY() - 1;
		if (player.isJumping()) {
			Log.d("engine", "player is jumping");
			if (!player.isTop()
					&& gamefield.getGamefield()[testTopY][testBotX] == Gamefield.LED_OFF
					&& gamefield.getGamefield()[testTopY][testBotX - 1] == Gamefield.LED_OFF
					&& gamefield.getGamefield()[testTopY][testBotX - 2] == Gamefield.LED_OFF) {
				player.move(Direction.UP);
				jumpCounter--;
				if (jumpCounter <= 0) {
					changeJumping(false);
					jumpCounter = JUMP_HEIGHT;
				}
			} else {
				changeJumping(false);
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

	private void notifyUpdateListener() {
		updateListener.updateScore(score);
		if (gameStatus == GameStatus.GAME_OVER) {
			updateListener.changeToScoreActivity();
		}
	}

	public void setUpdateListener(UpdateListener listener) {
		updateListener = listener;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	private void refreshAndSend() {
		gamefield.refresh(boxes, player);
		BluetoothManager.getInstance().send(gamefield.getGamefield());
	}
}
