package de.tkey99.ledattack;

import de.tkey99.ledattack.R;
import de.tkey99.ledattack.utilities.BluetoothManager;
import de.tkey99.ledattack.engine.gamefield.StaticGameFields;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * This activity provides the score after losing a game.
 * 
 * @author TKey99
 * 
 */
public class ScoreActivity extends Activity {

	/**
	 * Score to be shown
	 */
	private TextView score;

	/**
	 * OK button
	 */
	private Button okButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.score);

		score = (TextView) findViewById(R.id.score_final);
		score.setText(getIntent().getStringExtra("Score"));
		okButton = (Button) findViewById(R.id.ok_button);
		okButton.setOnClickListener(new OKListener());
	}

	@Override
	protected void onResume() {
		super.onResume();
		BluetoothManager.getInstance().send(StaticGameFields.GAME_OVER);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * Listener for the OK button
	 * 
	 * @author TKey99
	 * 
	 */
	private class OKListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			finish();
		}

	}
}
