package tkey99.ledattack;

import android.R.color;
import android.R.drawable;
import android.R.style;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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
		statusButton.setChecked(true);
		statusButton.setOnCheckedChangeListener(new StatusButtonListener());
		jumpButton = (Button) findViewById(R.id.jump_button);
		jumpButton.setOnClickListener(new JumpButtonListener());
		pushButton = (Button) findViewById(R.id.push_button);
		pushButton.setOnTouchListener(new PushButtonListener());
		score = (TextView) findViewById(R.id.game_score_value);

		engine = new LedAttackEngine(this);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onStart() {
		super.onStart();

		engine.start();
	}

	@Override
	protected void onRestart() {
		super.onRestart();

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

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}
	
	private class StatusButtonListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if(isChecked == true) {
				engine.setGameStatus(true);
			} else {
				engine.setGameStatus(false);
			}
		}
	}
	
	private class JumpButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}
	}
	
	private class PushButtonListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				engine.changePushStatus(true);
				return true;
			} else if(event.getAction() == MotionEvent.ACTION_UP) {
				engine.changePushStatus(false);
				return true;
			}
			return false;
		}
		
	}
}
