package tkey99.ledattack;

import tkey99.ledattack.utilities.BluetoothManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
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
public class GameEngineActivity extends Activity implements UpdateListener {

	private ToggleButton statusButton;

	private Button jumpButton;

	private Button pushButton;

	private TextView scoreView;

	private LedAttackEngine engine;

	private SensorManager sensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("s", "game created!");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		statusButton = (ToggleButton) findViewById(R.id.game_status_toggle);
		statusButton.setOnCheckedChangeListener(new StatusButtonListener());
		jumpButton = (Button) findViewById(R.id.jump_button);
		jumpButton.setOnClickListener(new JumpButtonListener());
		pushButton = (Button) findViewById(R.id.push_button);
		pushButton.setOnTouchListener(new PushButtonListener());
		scoreView = (TextView) findViewById(R.id.game_score_value);

		engine = new LedAttackEngine();

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onStart() {
		super.onStart();

		engine.setUpdateListener(this);
		engine.start();
	}

	@Override
	protected void onRestart() {
		super.onRestart();

	}

	@Override
	protected void onResume() {
		super.onResume();

		statusButton.setChecked(true);
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (engine.getGameStatus() == GameStatus.PAUSE) {
			BluetoothManager.getInstance().send(StaticGameFields.PAUSE);
		}
		statusButton.setChecked(false);
	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

	/**
	 * Listener for the status button
	 * 
	 * @author TKey99
	 * 
	 */
	private class StatusButtonListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked == true) {
				engine.setGameStatus(GameStatus.INGAME);
				sensorManager.registerListener(engine,
						sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
						SensorManager.SENSOR_DELAY_GAME);
			} else {
				engine.setGameStatus(GameStatus.PAUSE);
				sensorManager.unregisterListener(engine,
						sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
			}
		}
	}

	/**
	 * Listener for the jump button
	 * 
	 * @author TKey99
	 * 
	 */
	private class JumpButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			engine.changeJumping(true);
		}
	}

	/**
	 * Listener for the push button
	 * 
	 * @author TKey99
	 * 
	 */
	private class PushButtonListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				engine.changePushing(true);
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				engine.changePushing(false);
				return true;
			}
			return false;
		}

	}

	@Override
	public void changeToScoreActivity() {
		Intent scoreIntent = new Intent(getApplicationContext(),
				ScoreActivity.class);
		scoreIntent.putExtra("Score", this.scoreView.getText());
		startActivity(scoreIntent);
		this.finish();
	}

	@Override
	public void updateScore(final int score) {
		runOnUiThread(new Runnable() {
			public void run() {
				scoreView.setText(String.valueOf(score));
			}
		});
	}
}
