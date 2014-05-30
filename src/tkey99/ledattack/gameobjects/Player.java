package tkey99.ledattack.gameobjects;

import tkey99.ledattack.Gamefield;
import tkey99.ledattack.Position;

public class Player extends GameObject {

	private final byte[][] SYMBOL = new byte[][] { 
			{ X, X, X }, 
			{ X, X, X },
			{ 0, X, 0 }, 
			{ X, X, X }, 
			{ 0, X, 0 }, 
			{ 0, X, 0 } };

	public Player() {
		position = new Position((Gamefield.MAX_LED_X / 2),
				(Gamefield.MAX_LED_Y - 1) - (SYMBOL.length - 1),
				(Gamefield.MAX_LED_X / 2) + (SYMBOL[0].length - 1),
				Gamefield.MAX_LED_Y - 1);
	}

	public boolean isHead(int bottomRightX, int bottomRightY) {
		return false;
	}

	@Override
	public byte[][] getSymbol() {
		return SYMBOL;
	}
}
