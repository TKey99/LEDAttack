package tkey99.ledattack.gameobjects;

import tkey99.ledattack.Gamefield;
import tkey99.ledattack.Position;

/**
 * Represents the player in the game
 * 
 * @author TKey99
 * 
 */
public class Player extends GameObject {

	/**
	 * 
	 */
	private boolean isPushing;

	/**
	 * Symbol of the player
	 */
	private final byte[][] SYMBOL = new byte[][] { { X, X, X }, { X, X, X },
			{ 0, X, 0 }, { X, X, X }, { 0, X, 0 }, { 0, X, 0 } };

	public boolean isPushing() {
		return isPushing;
	}

	public void setPushing(boolean isPushing) {
		this.isPushing = isPushing;
	}

	/**
	 * Constructs a new player
	 */
	public Player() {
		position = new Position((Gamefield.MAX_LED_X / 2),
				(Gamefield.MAX_LED_Y - 1) - (SYMBOL.length - 1),
				(Gamefield.MAX_LED_X / 2) + (SYMBOL[0].length - 1),
				Gamefield.MAX_LED_Y - 1);

		isPushing = false;
	}

	/**
	 * Returns whether a box hits the head or not
	 * 
	 * @param bottomRightX
	 * @param bottomRightY
	 * @return
	 */
	public boolean isHead(int bottomRightX, int bottomRightY) {
		return false;
	}

	@Override
	public byte[][] getSymbol() {
		return SYMBOL;
	}
}
