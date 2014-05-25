package tkey99.ledattack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ManualActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("s", "manual created");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.manuals);

	}
}
