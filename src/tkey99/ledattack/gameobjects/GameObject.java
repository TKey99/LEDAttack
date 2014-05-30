package tkey99.ledattack.gameobjects;

import tkey99.ledattack.Direction;
import tkey99.ledattack.Gamefield;
import tkey99.ledattack.Position;

public abstract class GameObject {

	protected final byte X = Gamefield.LED_ON;

	protected Position position;
	
	public abstract byte[][] getSymbol();

	public boolean move(Direction direction) {
		switch (direction) {
		case DOWN:
			position.changePostionDown();
			return true;
		case LEFT:
			position.changePostionLeft();
			return true;
		case RIGHT:
			position.changePostionRight();
			return true;
		case UP:
			position.changePostionUp();
			return true;
		default:
			return false;
		}
	}

	public Position getPosition() {
		return position;
	}
}