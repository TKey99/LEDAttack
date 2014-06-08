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

	private final int SCORE_BONUS_FULL_ROW = 100;

	private final int SCORE_BONUS_BOX_DESTROYED = 15;

	private final int JUMP_HEIGHT = 3;

	private final int PUSH_LENGTH = 3;

	/**
	 * Minimum delay of spawning boxes in milliseconds
	 */
	private final int MINIMUM_SPAWN_DELAY = 2000;

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
	 * Provides the status of the game
	 */
	private GameStatus gameStatus;

	private int score = 0;

	private int bottomBoxCount = 0;

	private int jumpCounter;

	private int pushCounterLeft = 0;

	private int pushCounterRight = 0;

	private UpdateListener updateListener;

	/**
	 * Time until next box is spawned
	 */
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
		SoundManager.getInstance().playReady();
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_READY);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SoundManager.getInstance().playSet();
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_SET);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SoundManager.getInstance().playGo();
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_GO);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void showGameOver() {
		VibrationManager.getInstance().vibrate();
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SoundManager.getInstance().playGameOver();
		VibrationManager.getInstance().vibrate();
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VibrationManager.getInstance().vibrate();
		notifyUpdateListener();
	}

	@Override
	public void run() {
		// sensor verfeinern

		showIntro();

		boxes.add(new Box(9, 0));
		while (true) {

			// eventually delete bottom boxes and increase score
			deleteBoxes();

			// move player up or down
			movePlayerUpDown();

			// move left or right player and try to push
			if (pushCounterLeft > 0) {
				tryToPushLeft();
			} else if (pushCounterRight > 0) {
				tryToPushRight();
			} else {
				movePlayerLeftRight();
			}
			gamefield.refresh(boxes, player);

			// move boxes
			moveBoxesDown();
			gamefield.refresh(boxes, player);

			// eventually spawn box
			// spawnBox();

			// send gamefield
			refreshAndSend();

			if (gameStatus == GameStatus.PAUSE) {
				BluetoothManager.getInstance().send(StaticGameFields.PAUSE);
				spawnTimer = 0;
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				refreshAndSend();
			}

			if (gameStatus == GameStatus.GAME_OVER) {
				showGameOver();
				return;
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
			gamefield.refresh(boxes, player);
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
				if (gamefield.getGamefield()[testYTop][testXBot] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testYTop
								+ player.getSymbol().length / 2 - 1][testXBot] == Gamefield.LED_OFF) {
					if (gamefield.getGamefield()[testYBot][testXBot] == Gamefield.LED_OFF) {
						player.move(Direction.RIGHT);
						gamefield.refresh(boxes, player);
						Log.d("player", "move right");
					} else {
						if (player.isPushing()) {
							pushCounterRight = PUSH_LENGTH;
							tryToPushRight();
						}
					}
				}
			}
		} else if (sensorValue <= (SENSOR_VALUE_TO_MOVE * -1)) {
			if (!player.isLeft()) {
				if (gamefield.getGamefield()[testYTop][testXTop] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testYTop
								+ player.getSymbol().length / 2 - 1][testXTop] == Gamefield.LED_OFF) {
					if (gamefield.getGamefield()[testYBot][testXTop] == Gamefield.LED_OFF) {
						player.move(Direction.LEFT);
						gamefield.refresh(boxes, player);
						Log.d("player", "move left");
					} else {
						if (player.isPushing()) {
							pushCounterLeft = PUSH_LENGTH;
							tryToPushLeft();
						}
					}
				}
			}
		}
	}

	/**
	 * Tries to push a box to the right
	 */
	private void tryToPushRight() {
		Log.d("engine", "try to push left");
		for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
			Box current = iter.next();
			if (current.getPosition().getBottomRightX()
					- current.getSymbol()[0].length == player.getPosition()
					.getBottomRightX()
					&& current.getPosition().getBottomRightY() == player
							.getPosition().getBottomRightY()) {
				if (!current.isRight()
						&& gamefield.getGamefield()[current.getPosition()
								.getTopLeftY()][current.getPosition()
								.getBottomRightX() + 1] == Gamefield.LED_OFF) {
					if (pushCounterRight > 0) {
						pushCounterRight--;
						SoundManager.getInstance().playPush();
						current.move(Direction.RIGHT);
						player.move(Direction.RIGHT);
						gamefield.refresh(boxes, player);
					}
					return;
				} else {
					pushCounterRight = 0;
					return;
				}
			}
		}
	}

	/**
	 * Tries to push a box to the left
	 */
	private void tryToPushLeft() {
		Log.d("engine", "try to push left");
		for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
			Box current = iter.next();
			if (current.getPosition().getBottomRightX() == player.getPosition()
					.getBottomRightX() - player.getSymbol()[0].length
					&& current.getPosition().getBottomRightY() == player
							.getPosition().getBottomRightY()) {
				if (!current.isLeft()
						&& gamefield.getGamefield()[current.getPosition()
								.getTopLeftY()][current.getPosition()
								.getBottomRightX()
								- current.getSymbol()[0].length] == Gamefield.LED_OFF) {
					if (pushCounterLeft > 0) {
						pushCounterLeft--;
						SoundManager.getInstance().playPush();
						current.move(Direction.LEFT);
						player.move(Direction.LEFT);
						gamefield.refresh(boxes, player);
					}
					return;
				} else {
					pushCounterLeft = 0;
					return;
				}
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
			boolean canMove = true;

			if (!current.isAtBottom()) {
				for (Iterator<Box> iter2 = boxes.iterator(); iter2.hasNext();) {
					Box boxToTest = iter2.next();
					if (boxToTest.isBox(current.getPosition())) {
						canMove = false;
					}
				}

				if (canMove) {
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
							return;
						} else {
							setGameStatus(GameStatus.GAME_OVER);
							return;
						}
					}
					gamefield.refresh(boxes, player);
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
			SoundManager.getInstance().playRowScore();
			VibrationManager.getInstance().vibrate();
			score += SCORE_BONUS_FULL_ROW;
			notifyUpdateListener();
			bottomBoxCount = 0;
			gamefield.refresh(boxes, player);
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
				gamefield.refresh(boxes, player);
				jumpCounter--;
				if (jumpCounter <= 0) {
					changeJumping(false);
					jumpCounter = JUMP_HEIGHT;
				}
			} else {
				jumpCounter--;
				if (jumpCounter <= 0) {
					changeJumping(false);
					jumpCounter = JUMP_HEIGHT;
				}
			}
		} else {
			if (!player.isAtBottom()) {
				if (gamefield.getGamefield()[testBotY][testBotX] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testBotY][testBotX - 1] == Gamefield.LED_OFF
						&& gamefield.getGamefield()[testBotY][testBotX - 2] == Gamefield.LED_OFF) {
					player.move(Direction.DOWN);
					gamefield.refresh(boxes, player);
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
