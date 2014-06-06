package tkey99.ledattack.utilities;

import tkey99.ledattack.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class SoundManager {

	/**
	 * Instance of this class
	 */
	private static SoundManager instance;

	private SoundPool soundPool;

	private AudioManager audioManager;

	private final int MAX_STREAMS = 3;

	private int jumpSound;

	private int destroyBox;

	/**
	 * Private constructor
	 */
	private SoundManager(Context context) {
		soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
		audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		jumpSound = soundPool.load(context, R.raw.jump, 1);
		destroyBox = soundPool.load(context, R.raw.destroybox, 1);
	}

	/**
	 * Singleton method
	 * 
	 * @return instance of this class
	 */
	public static SoundManager getInstance() {
		return instance;
	}

	public static void initialize(Context context) {
		if (instance == null) {
			instance = new SoundManager(context);
		} else {
			Log.d("soundmanager", "not initialized");
		}
	}

	public void playGameOver() {

	}

	public void playGameStart() {

	}

	public void playRowScore() {

	}

	public void playDestroyBox() {
		playSound(destroyBox);
	}

	public void playPush() {

	}

	public void playJump() {
		playSound(jumpSound);
	}

	private void playSound(int soundID) {
		Log.d("soundmanager", "plays sound");
		float volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(soundID, volume, volume, 1, 0, 1f);
	}
}
