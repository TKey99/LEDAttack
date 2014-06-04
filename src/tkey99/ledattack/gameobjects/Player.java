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
	 * @param bottomRightX
	 * @param bottomRightY
	 * @return
	 */
	public boolean isHead(Position boxPosition) {
		return false;
	}

	@Override
	public byte[][] getSymbol() {
		return SYMBOL;
	}

	public boolean isLeft() {
		if (position.getTopLeftX() <= 0) {
			return true;
		}
		return false;
	}

	public boolean isRight() {
		if (position.getBottomRightX() >= Gamefield.MAX_LED_X - 1) {
			return true;
		}
		return false;
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
