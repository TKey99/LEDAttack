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
		}
	}

	public void showIntro() {
		Log.d("intro", "intro gesendet");
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_READY);
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_SET);
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BluetoothManager.getInstance().send(StaticGameFields.COUNTDOWN_GO);
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean startGame() {
		return true;

	}

	@Override
	public void run() {
		showIntro();
	}
}
