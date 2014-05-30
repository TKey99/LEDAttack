package tkey99.ledattack;

public class Position {

	private int topLeftX;

	private int topLeftY;

	private int bottomRightX;

	private int bottomRightY;

	public Position(int topLeftX, int topLeftY, int bottomRightX,
			int bottomRightY) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.bottomRightX = bottomRightX;
		this.bottomRightY = bottomRightY;
	}

	public int getTopLeftX() {
		return topLeftX;
	}

	public int getTopLeftY() {
		return topLeftY;
	}

	public int getBottomRightX() {
		return bottomRightX;
	}

	public int getBottomRightY() {
		return bottomRightY;
	}

	public boolean changePostionDown() {
		this.topLeftY += 1;
		this.bottomRightY += 1;
		return true;
	}

	public boolean changePostionUp() {
		this.topLeftY -= 1;
		this.bottomRightY -= 1;
		return true;
	}

	public boolean changePostionLeft() {
		this.topLeftX -= 1;
		this.bottomRightX -= 1;
		return true;
	}

	public boolean changePostionRight() {
		this.topLeftX += 1;
		this.bottomRightX += 1;
		return true;
	}
}
