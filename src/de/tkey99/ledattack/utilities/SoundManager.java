package de.tkey99.ledattack.utilities;

import de.tkey99.ledattack.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

/**
 * Manages the sounds in the game.
 * 
 * @author TKey99
 * 
 */
public class SoundManager {

	/**
	 * Instance of this class
	 */
	private static SoundManager instance;

	/**
	 * Soundpool to play sounds
	 */
	private SoundPool soundPool;

	/**
	 * Access to volume control
	 */
	private AudioManager audioManager;

	/**
	 * Maximum amount of sound streams at a moment
	 */
	private final int MAX_STREAMS = 3;

	/**
	 * Jump sound
	 */
	private int jumpSound;

	/**
	 * Destroy Sound
	 */
	private int destroyBox;

	/**
	 * Game over sound
	 */
	private int gameOver;

	/**
	 * Ready sound
	 */
	private int ready;

	/**
	 * Set sound
	 */
	private int set;

	/**
	 * Go sound
	 */
	private int go;

	/**
	 * Scored sound
	 */
	private int scored;

	/**
	 * Push sound
	 */
	private int push;

	/**
	 * Private constructor
	 */
	private SoundManager(Context context) {
		soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
		audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		jumpSound = soundPool.load(context, R.raw.jump, 1);
		destroyBox = soundPool.load(context, R.raw.destroybox, 1);
		gameOver = soundPool.load(context, R.raw.gameover, 1);
		ready = soundPool.load(context, R.raw.ready, 1);
		set = soundPool.load(context, R.raw.set, 1);
		go = soundPool.load(context, R.raw.go, 1);
		scored = soundPool.load(context, R.raw.scored, 1);
		push = soundPool.load(context, R.raw.push, 1);
	}

	/**
	 * Singleton method
	 * 
	 * @return instance of this class
	 */
	public static SoundManager getInstance() {
		return instance;
	}

	/**
	 * Initializes the soundmanager
	 * 
	 * @param context
	 *            context
	 */
	public static void initialize(Context context) {
		if (instance == null) {
			instance = new SoundManager(context);
		} else {
			Log.d("soundmanager", "not initialized");
		}
	}

	/**
	 * Plays the game over sound
	 */
	public void playGameOver() {
		playSound(gameOver);
	}

	/**
	 * Plays the ready sound
	 */
	public void playReady() {
		playSound(ready);
	}

	/**
	 * Plays the set sound
	 */
	public void playSet() {
		playSound(set);
	}

	/**
	 * Plays the go sound
	 */
	public void playGo() {
		playSound(go);
	}

	/**
	 * Plays the score sound
	 */
	public void playRowScore() {
		playSound(scored);
	}

	/**
	 * Plays the destroy sound
	 */
	public void playDestroyBox() {
		playSound(destroyBox);
	}

	/**
	 * Plays the push sound
	 */
	public void playPush() {
		playSound(push);
	}

	/**
	 * Plays the jump sound
	 */
	public void playJump() {
		playSound(jumpSound);
	}

	/**
	 * Plays a sound by a given id
	 * 
	 * @param soundID
	 *            sound id given
	 */
	private void playSound(int soundID) {
		Log.d("soundmanager", "plays sound");
		float volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundPool.play(soundID, volume, volume, 1, 0, 1f);
	}

	/*
	 * public void mute() {
	 * audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true); }
	 * 
	 * public void unmute() {
	 * audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false); }
	 */
}
