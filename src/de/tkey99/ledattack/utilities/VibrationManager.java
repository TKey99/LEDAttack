package de.tkey99.ledattack.utilities;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

/**
 * Manages the vibrations in the game.
 * 
 * @author TKey99
 * 
 */
public class VibrationManager {

	/**
	 * Instance
	 */
	private static VibrationManager instance;

	/**
	 * Operates the vibrations
	 */
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

	/**
	 * Initializes the vibrationmanager
	 * 
	 * @param context
	 *            context
	 */
	public static void initialize(Context context) {
		if (instance == null) {
			instance = new VibrationManager(context);
		} else {
			Log.d("vibrationmanager", "not initialized");
		}
	}

	/**
	 * Vibrates for a duration
	 */
	public void vibrate() {
		vibrator.vibrate(VIBRATION_TIME);
	}
}
