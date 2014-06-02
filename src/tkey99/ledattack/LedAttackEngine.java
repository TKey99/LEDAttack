package tkey99.ledattack;

import java.util.ArrayList;

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
	private final long INTRO_WAIT_TIME = 2000;

	/**
	 * Wait time for the game calculation
	 */
	private final long LOOP_WAIT_TIME = 80;

	/**
	 * Provides the status of the game
	 */
	private boolean isIngame;

	/**
	 * Constructs a new engine
	 */
	public LedAttackEngine() {
		isIngame = true;
		gamefield = new Gamefield();
		player = new Player();
		boxes = new ArrayList<Box>();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
			float x = event.values[0]; // für kippen
			float y = event.values[1];
			float z = event.values[2];

			sensorValue += z;

			Log.d("gyrosensor", "" + z);
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

		while (true) {
			// TODO push einrichten evtl unten stehendes in priavte methode
			// auslagern

			if (sensorValue >= SENSOR_VALUE_TO_MOVE) {
				player.move(Direction.RIGHT);
				Log.d("player", "move right");
			} else if (sensorValue <= (SENSOR_VALUE_TO_MOVE * -1)) {
				player.move(Direction.LEFT);
				Log.d("player", "move left");
			}

			
			
			
			
			
			
			
			
			
			// send gamefield
			gamefield.refresh(boxes, player);
			BluetoothManager.getInstance().send(gamefield.getGamefield());
			try {
				sleep(LOOP_WAIT_TIME);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			// TODO enhance code
			if (!isIngame) {
				BluetoothManager.getInstance().send(StaticGameFields.PAUSE);
				while (!isIngame) {
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Changes whether the player is pushing or not
	 * 
	 * @param status
	 *            status to set
	 */
	public void changePushStatus(boolean status) {
		player.setPushing(status);
	}

	/**
	 * Sets the ingame status
	 * 
	 * @param status
	 *            status to set
	 */
	public void setIngameStatus(boolean status) {
		isIngame = status;
	}
}
