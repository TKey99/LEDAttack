package tkey99.ledattack;

/**
 * Represents a position of a game object
 * 
 * @author TKey99
 * 
 */
public class Position {

	/**
	 * Top left x coordinate
	 */
	private int topLeftX;

	/**
	 * Top left y coordinate
	 */
	private int topLeftY;

	/**
	 * Bottom right x coordinate
	 */
	private int bottomRightX;

	/**
	 * Bottom right y coordinate
	 */
	private int bottomRightY;

	/**
	 * Constructs a new position
	 * 
	 * @param topLeftX
	 *            top left x coordinate
	 * @param topLeftY
	 *            top left y coordinate
	 * @param bottomRightX
	 *            bottom right x coordinate
	 * @param bottomRightY
	 *            bottom right y coordinate
	 */
	public Position(int topLeftX, int topLeftY, int bottomRightX,
			int bottomRightY) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.bottomRightX = bottomRightX;
		this.bottomRightY = bottomRightY;
	}

	/**
	 * Returns the top left x coordinate
	 * 
	 * @return the top left x coordinate
	 */
	public int getTopLeftX() {
		return topLeftX;
	}

	/**
	 * Returns the top left y coordinate
	 * 
	 * @return the top left y coordinate
	 */
	public int getTopLeftY() {
		return topLeftY;
	}

	/**
	 * Returns the bottom right x coordinate
	 * 
	 * @return the bottom right x coordinate
	 */
	public int getBottomRightX() {
		return bottomRightX;
	}

	/**
	 * Returns the bottom right y coordinate
	 * 
	 * @return the bottom right y coordinate
	 */
	public int getBottomRightY() {
		return bottomRightY;
	}

	/**
	 * Changes the position one position down
	 */
	public void changePostionDown() {
		this.topLeftY += 1;
		this.bottomRightY += 1;
	}

	/**
	 * Changes the position one position up
	 */
	public void changePostionUp() {
		this.topLeftY -= 1;
		this.bottomRightY -= 1;
	}

	/**
	 * Changes the position one position left
	 */
	public void changePostionLeft() {
		this.topLeftX -= 1;
		this.bottomRightX -= 1;
	}

	/**
	 * Changes the position one position right
	 */
	public void changePostionRight() {
		this.topLeftX += 1;
		this.bottomRightX += 1;
	}
}
