package tkey99.ledattack;

import tkey99.ledattack.utilities.BluetoothManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

/**
 * This activity is the activity started at the start of the application.
 * 
 * @author TKey99
 * 
 */
public class StartActivity extends Activity {

	/**
	 * Title of the game.
	 */
	private TextView title;

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
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.start);

		BluetoothManager.getInstance().enableBT(this);

		title = (TextView) findViewById(R.id.title);
		start = (Button) findViewById(R.id.start_button);
		manual = (Button) findViewById(R.id.manual_button);

		start.setOnTouchListener(new ButtonListener());
		manual.setOnTouchListener(new ButtonListener());
	}

	/**
	 * Listener for the Buttons in the View
	 * 
	 * @author TKey99
	 * 
	 */
	private class ButtonListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v == start) {
				// TODO wenn tocuhlistener aktiviert kein button highlighting
				// mehr....
				Intent startIntent = new Intent(getApplicationContext(),
						GameEngineActivity.class);
				startActivityForResult(startIntent, 1);
				return true;
			} else if (v == manual) {
				setContentView(R.layout.manuals);
				return true;

			}
			return false;
		}
	}
}
