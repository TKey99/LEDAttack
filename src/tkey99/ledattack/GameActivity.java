package tkey99.ledattack;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.start);
	}
}
