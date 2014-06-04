package tkey99.ledattack;

import tkey99.ledattack.utilities.BluetoothManager;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends Activity {

	TextView score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.score);

		score = (TextView) findViewById(R.id.score_final);
		score.setText(getIntent().getStringExtra("Score"));
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
}
