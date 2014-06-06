package tkey99.ledattack.utilities;

public class SoundManager {

	/**
	 * Instance of this class
	 */
	private static SoundManager instance;

	/**
	 * Private constructor
	 */
	private SoundManager() {

	}

	/**
	 * Singleton method
	 * 
	 * @return instance of this class
	 */
	public static SoundManager getInstance() {
		if (instance == null) {
			instance = new SoundManager();
		}
		return instance;
	}
	
	public void playGameOver() {
		
	}
	
	public void playGameStart() {
		
	}
	
	public void playRowScore() {
		
	}
	
	public void playDestroyScore() {
		
	}
	
	public void playPush() {
		
	}
	
	public void playJump() {
		
	}
	
	private void playSound() {
		
	}
}
