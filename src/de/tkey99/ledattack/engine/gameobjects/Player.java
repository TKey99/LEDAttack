package de.tkey99.ledattack.engine.gameobjects;

/**
 * Represents the player in the game
 * 
 * @author TKey99
 * 
 */
public class Player extends GameObject {

	/**
	 * Indicates if the player is pushing at the moment
	 */
	private boolean isPushing;

	/**
	 * Indicates if the player is jumping at the moment
	 */
	private boolean isJumping;

	/**
	 * Symbol of the player
	 */
	private final byte[][] SYMBOL = new byte[][] { { X, X, X }, { X, X, X },
			{ 0, X, 0 }, { X, X, X }, { 0, X, 0 }, { X, 0, X } };

	/**
	 * Constructs a new player
	 */
	public Player(int topLeftX, int topLeftY) {
		position = new Position(topLeftX, topLeftY, topLeftX + SYMBOL[0].length
				- 1, topLeftY + SYMBOL.length - 1);

		isPushing = false;
		isJumping = false;
	}

	/**
	 * Returns whether the player is pushing
	 * 
	 * @return true if is pushing, false otherwise
	 */
	public boolean isPushing() {
		return isPushing;
	}

	/**
	 * Sets whether the player is pushing
	 * 
	 * @param isPushing
	 *            push status
	 */
	public void setPushing(boolean isPushing) {
		this.isPushing = isPushing;
	}

	/**
	 * Returns whether the player is jumping
	 * 
	 * @return true if is jumping, false otherwise
	 */
	public boolean isJumping() {
		return isJumping;
	}

	/**
	 * Sets whether the player is pushing
	 * 
	 * @param isJumping
	 *            push status
	 */
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	/**
	 * Returns whether a box hits the head or not
	 * 
	 * @param boxPosition
	 *            position of the box that hits maybe
	 * @return true if hit, false otherwise
	 */
	public boolean isHead(Position boxPosition) {
		int testY = boxPosition.getBottomRightY();
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

	/**
	 * Returns whether the player is at the top of the gamefield
	 * 
	 * @return true if is at the top, false otherwise
	 */
	public boolean isTop() {
		if (position.getTopLeftY() <= 0) {
			return true;
		}
		return false;
	}
}
