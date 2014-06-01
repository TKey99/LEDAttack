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

public class LedAttackEngine extends Thread implements SensorEventListener {

	private Gamefield gamefield;

	private Player player;

	private ArrayList<Box> boxes;

	private float sensorValue;

	private final float SENSOR_VALUE_TO_MOVE = 1.5f;

	private final long INTRO_WAIT_TIME = 2000;
	
	private boolean isIngame = false;

	public LedAttackEngine(Activity activity) {

		gamefield = new Gamefield();
		player = new Player();
		boxes = new ArrayList<Box>();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
			float x = event.values[0]; // für kippen
			float y = event.values[1];
			float z = event.values[2];

			// Log.d("x", "x = " + x);
			// Log.d("y", "y = " + y);
			// Log.d("z", "z = " + z);
			// TODO

			sensorValue += x;

			Log.d("gyrosensor", "" + sensorValue);
			if (sensorValue >= SENSOR_VALUE_TO_MOVE
					&& player.getPosition().getBottomRightX() < (Gamefield.MAX_LED_X - 1)) {
				player.move(Direction.RIGHT);
				Log.d("player", "move right");
			} else if (sensorValue <= (SENSOR_VALUE_TO_MOVE * -1)
					&& player.getPosition().getTopLeftX() > 0) {
				player.move(Direction.LEFT);
				Log.d("player", "move left");
			}
		}
	}

	private void showIntro() {
		Log.d("intro", "intro gesendet");
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_READY);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_SET);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_GO);
		try {
			sleep(INTRO_WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showGameOver() {
		BluetoothManager.getInstance().send(StaticGameFields.GAME_OVER);
	}

	public void setGameStatus(boolean status) {
		isIngame = status;
	}

	@Override
	public void run() {
		showIntro();
		
		while(isIngame) {
			
		}
	}

	// TODO in regelmäßigem abstand gamefield refreshen und senden
}
