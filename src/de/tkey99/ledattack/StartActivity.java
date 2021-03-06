package de.tkey99.ledattack;

import de.tkey99.ledattack.R;
import de.tkey99.ledattack.utilities.BluetoothManager;
import de.tkey99.ledattack.utilities.SoundManager;
import de.tkey99.ledattack.utilities.VibrationManager;
import de.tkey99.ledattack.engine.gamefield.StaticGameFields;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

/**
 * This activity is the activity started at the start of the application.
 * 
 * @author TKey99
 * 
 */
public class StartActivity extends Activity {

	/**
	 * Button to start the game.
	 */
	private Button start;

	/**
	 * Button to show the manuals.
	 */
	private Button manual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("s", "start created!");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.start);

		start = (Button) findViewById(R.id.start_button);
		manual = (Button) findViewById(R.id.manual_button);

		start.setOnClickListener(new ButtonListener());
		manual.setOnClickListener(new ButtonListener());
	}

	@Override
	protected void onStart() {
		super.onStart();

		VibrationManager.initialize(this);
		SoundManager.initialize(this);

		if (!BluetoothManager.getInstance().isEnabled()) {
			BluetoothManager.getInstance().enableBT(this);
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();

	}

	@Override
	protected void onResume() {
		super.onResume();

		BluetoothManager.getInstance().send(StaticGameFields.TITLE);
	}

	@Override
	protected void onPause() {
		super.onPause();
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
			BluetoothManager.getInstance().closeConnection();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			BluetoothManager.getInstance().connect();
			BluetoothManager.getInstance().send(StaticGameFields.TITLE);
		}
	}

	/**
	 * Listener for the Buttons in the View
	 * 
	 * @author TKey99
	 * 
	 */
	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == start) {
				if (BluetoothManager.getInstance().isEnabled()) {
					Intent gameIntent = new Intent(getApplicationContext(),
							GameEngineActivity.class);
					startActivity(gameIntent);
				} else {
					Toast.makeText(getApplicationContext(),
							R.string.bt_not_connected, Toast.LENGTH_LONG)
							.show();
				}
			} else if (v == manual) {

				Intent manualIntent = new Intent(getApplicationContext(),
						ManualActivity.class);
				startActivity(manualIntent);
			}
		}
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();

		// does not finish activity on back pressed
		moveTaskToBack(true);
	}
}
