package tkey99.ledattack.gameobjects;

import tkey99.ledattack.Gamefield;
import tkey99.ledattack.Position;

/**
 * Represents a box in the game.
 * 
 * @author TKey99
 * 
 */
public class Box extends GameObject {

	/**
	 * Symbol of the box
	 */
	private final byte[][] SYMBOL = new byte[][] { { X, X, X }, { X, 0, X },
			{ X, X, X } };

	/**
	 * Constructs a new box
	 */
	public Box() {
		int spawnTopLeftX = (int) (Math.random() * 8);
		spawnTopLeftX *= 3;
		position = new Position(spawnTopLeftX, 0, spawnTopLeftX
				+ SYMBOL[0].length - 1, (SYMBOL.length - 1));
	}

	@Override
	public byte[][] getSymbol() {
		return SYMBOL;
	}
}
