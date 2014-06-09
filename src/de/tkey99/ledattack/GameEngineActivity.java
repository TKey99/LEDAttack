package de.tkey99.ledattack;

import de.tkey99.ledattack.R;
import de.tkey99.ledattack.utilities.BluetoothManager;
import de.tkey99.ledattack.engine.GameStatus;
import de.tkey99.ledattack.engine.LedAttackEngine;
import de.tkey99.ledattack.engine.gamefield.StaticGameFields;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

	/**
	 * Statusbutton for the game status
	 */
	private ToggleButton statusButton;

	/**
	 * Jump button
	 */
	private Button jumpButton;

	/**
	 * Push button
	 */
	private Button pushButton;

	/**
	 * Shows the actual score
	 */
	private TextView scoreView;

	/**
	 * Game engine
	 */
	private LedAttackEngine engine;

	/**
	 * Manages the devices sensors
	 */
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

		if (!engine.isAlive()) {
			engine.start();
			sensorManager.registerListener(engine,
					sensorManager.getDefaultSensor(LedAttackEngine.SENSOR),
					SensorManager.SENSOR_DELAY_GAME);
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();

		statusButton.setChecked(false);
	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// if app is closing
		if (isFinishing()) {
			BluetoothManager.getInstance().send(StaticGameFields.GAME_OVER);
		}
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
						sensorManager.getDefaultSensor(LedAttackEngine.SENSOR),
						SensorManager.SENSOR_DELAY_GAME);
				synchronized (engine) {
					engine.notify();
				}
			} else {
				engine.setGameStatus(GameStatus.PAUSE);
				sensorManager.unregisterListener(engine,
						sensorManager.getDefaultSensor(LedAttackEngine.SENSOR));
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

	@Override
	public void onBackPressed() {

		if (statusButton.isChecked()) {
			statusButton.setChecked(false);
		}

		Builder alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setTitle(R.string.alert_quit_game_title);
		alertBuilder.setMessage(R.string.alert_quit_game_text);
		alertBuilder.setCancelable(true);
		alertBuilder.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (engine.getGameStatus() == GameStatus.PAUSE) {
							statusButton.setChecked(true);
						}
						engine.setGameStatus(GameStatus.GAME_OVER);
						dialog.cancel();
					}
				});
		alertBuilder.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// do nothing
						dialog.cancel();
					}
				});

		AlertDialog alert = alertBuilder.create();
		alert.show();
	}
}
