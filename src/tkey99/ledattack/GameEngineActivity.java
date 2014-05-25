package tkey99.ledattack;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

/**
 * This activity is active during playing the game.
 * 
 * @author TKey99
 * 
 */
public class GameEngineActivity extends Activity {

	SensorManager sensorManager;

	private Button statusButton;

	private Button jumpButton;

	private Button pushButton;
	
	private LedAttackEngine engine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("s", "game created!");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		statusButton = (Button) findViewById(R.id.game_status_toggle);
		jumpButton = (Button) findViewById(R.id.jump_button);
		pushButton = (Button) findViewById(R.id.push_button);
		
		engine = new LedAttackEngine(this);
	}
}
