package tkey99.ledattack.utilities;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

public class VibrationManager {

	private static VibrationManager instance;

	private Vibrator vibrator;
	
	/**
	 * Time to vibrate in milliseconds
	 */
	private final int VIBRATION_TIME = 1000;

	/**
	 * Private constructor
	 */
	private VibrationManager(Context context) {

		vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
	}

	/**
	 * Singleton method
	 * 
	 * @return instance of this class
	 */
	public static VibrationManager getInstance() {
		return instance;
	}

	public static void initialize(Context context) {
		if (instance == null) {
			instance = new VibrationManager(context);
		} else {
			Log.d("vibrationmanager", "not initialized");
		}
	}

	public void vibrate() {
		vibrator.vibrate(VIBRATION_TIME);
	}
}
