package tkey99.ledattack;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class LedAttackEngine implements SensorEventListener {

	private SensorManager sensorManager;

	private Gamefield gamefield;

	public LedAttackEngine(Activity activity) {
		sensorManager = (SensorManager) activity
				.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
				SensorManager.SENSOR_DELAY_NORMAL);

		gamefield = new Gamefield();
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

			Log.d("x", "x = " + x);
			Log.d("y", "y = " + y);
			Log.d("z", "z = " + z);
			// TODO
		}
	}
}
