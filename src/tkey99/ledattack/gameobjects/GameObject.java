package tkey99.ledattack.gameobjects;

import tkey99.ledattack.Direction;
import tkey99.ledattack.Gamefield;
import tkey99.ledattack.Position;

/**
 * Represents a game object
 * 
 * @author TKey99
 * 
 */
public abstract class GameObject {

	/**
	 * Value to set a led on
	 */
	protected final byte X = Gamefield.LED_ON;

	/**
	 * Position of the game object
	 */
	protected Position position;

	/**
	 * Returns the symbol of the game object
	 * 
	 * @return the symbol of the game object
	 */
	public abstract byte[][] getSymbol();

	/**
	 * Moves a game object into an direction
	 * 
	 * @param direction
	 *            direction to move to
	 * @return true if moved false if not
	 */
	public boolean move(Direction direction) {
		switch (direction) {
		case DOWN:
			if (position.getBottomRightY() < Gamefield.MAX_LED_Y - 1) {
				position.changePostionDown();
				return true;
			} else {
				return false;
			}
		case LEFT:
			if (position.getTopLeftX() > 0) {
				position.changePostionLeft();
				return true;
			} else {
				return false;
			}
		case RIGHT:
			if (position.getBottomRightX() < (Gamefield.MAX_LED_X - 1)) {
				position.changePostionRight();
				return true;
			} else {
				return false;
			}
		case UP:
			if (position.getTopLeftY() > 0) {
				position.changePostionUp();
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	/**
	 * Returns the position of the game object
	 * 
	 * @return the position of the game object
	 */
	public Position getPosition() {
		return position;
	}

	public boolean isAtBottom() {
		if (position.getBottomRightY() >= Gamefield.MAX_LED_Y - 1) {
			return true;
		}
		return false;
	}
}