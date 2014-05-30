package tkey99.ledattack.gameobjects;

import tkey99.ledattack.Gamefield;
import tkey99.ledattack.Position;

public class Box extends GameObject {

	private final byte[][] SYMBOL = new byte[][] { 
			{ X, X, X }, 
			{ X, 0, X },
			{ X, X, X } };

	public Box() {
		int spawnTopLeftX = (int) (Math.random() * (Gamefield.MAX_LED_X
				- SYMBOL[0].length - 1));
		position = new Position(spawnTopLeftX, 0, spawnTopLeftX
				+ SYMBOL[0].length - 1, (SYMBOL.length - 1));
	}

	@Override
	public byte[][] getSymbol() {
		return SYMBOL;
	}
}
