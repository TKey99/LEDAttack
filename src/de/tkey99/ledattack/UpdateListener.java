package de.tkey99.ledattack;

/**
 * Listener for updating the view while in game.
 * 
 * @author TKey99
 * 
 */
public interface UpdateListener {

	/**
	 * Tells the activity to change into the score activity
	 */
	public void changeToScoreActivity();

	/**
	 * Updates the score textfield
	 * 
	 * @param score
	 *            score to be shown
	 */
	public void updateScore(int score);
}
