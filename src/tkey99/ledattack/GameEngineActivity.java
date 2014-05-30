package tkey99.ledattack;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * This activity is active during playing the game.
 * 
 * @author TKey99
 * 
 */
public class GameEngineActivity extends Activity {

	private ToggleButton statusButton;

	private Button jumpButton;

	private Button pushButton;

	private TextView score;

	private LedAttackEngine engine;

	private SensorManager sensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("s", "game created!");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		statusButton = (ToggleButton) findViewById(R.id.game_status_toggle);
		jumpButton = (Button) findViewById(R.id.jump_button);
		pushButton = (Button) findViewById(R.id.push_button);
		score = (TextView) findViewById(R.id.game_score_value);

		engine = new LedAttackEngine(this);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onStart() {
		super.onStart();

		sensorManager.registerListener(engine,
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
				SensorManager.SENSOR_DELAY_NORMAL);

		engine.start();
	}

	@Override
	protected void onRestart() {
		super.onRestart();

		sensorManager.registerListener(engine,
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onResume() {
		super.onResume();

		sensorManager.registerListener(engine,
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();

		sensorManager.unregisterListener(engine,
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
	}

	@Override
	protected void onStop() {
		super.onStop();

		sensorManager.unregisterListener(engine,
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		sensorManager.unregisterListener(engine,
				sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
	}
}
