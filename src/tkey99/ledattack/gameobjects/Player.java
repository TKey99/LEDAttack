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

	private boolean isJumping;

	/**
	 * Symbol of the player
	 */
	private final byte[][] SYMBOL = new byte[][] { { X, X, X }, { X, X, X },
			{ 0, X, 0 }, { X, X, X }, { 0, X, 0 }, { X, 0, X } };

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
	 * @param boxPosition
	 *            position of the box that hits maybe
	 * @return true if hit, false otherwise
	 */
	public boolean isHead(Position boxPosition) {
		int testY = boxPosition.getBottomRightY() + 1;
		int testX = boxPosition.getBottomRightX();
		if (testY == position.getTopLeftY()) {
			if (testX == position.getTopLeftX()
					|| testX == position.getTopLeftX() + 1
					|| testX == position.getBottomRightX()
					|| testX - 1 == position.getBottomRightX()
					|| testX - 2 == position.getBottomRightX()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public byte[][] getSymbol() {
		return SYMBOL;
	}

	public boolean isTop() {
		if (position.getTopLeftY() <= 0) {
			return true;
		}
		return false;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
}
