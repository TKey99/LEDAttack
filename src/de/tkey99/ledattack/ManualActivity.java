package de.tkey99.ledattack;

import de.tkey99.ledattack.R;
import de.tkey99.ledattack.utilities.BluetoothManager;
import de.tkey99.ledattack.engine.gamefield.StaticGameFields;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * This activity provides the manual of the game.
 * 
 * @author TKey99
 * 
 */
public class ManualActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("s", "manual created");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.manuals);
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
}
