package de.tkey99.ledattack.engine.gamefield;

import java.util.Iterator;
import java.util.List;

import de.tkey99.ledattack.engine.gameobjects.Box;
import de.tkey99.ledattack.engine.gameobjects.GameObject;
import de.tkey99.ledattack.engine.gameobjects.Player;

/**
 * Provides the gamefield of the game.
 * 
 * @author TKey99
 * 
 */
public class Gamefield {

	/**
	 * Maximum number of leds on the x axis
	 */
	public static final int MAX_LED_X = 24;

	/**
	 * Maximum number of leds on the y axis
	 */
	public static final int MAX_LED_Y = 24;

	/**
	 * Value to set a led on
	 */
	public static final byte LED_ON = (byte) 255;

	/**
	 * Value to set a led off
	 */
	public static final byte LED_OFF = (byte) 0;

	/**
	 * Gamefield as a matrix
	 */
	private byte[][] gamefield;

	// y zeile
	// x spalte

	/**
	 * Constructs a new gamefield
	 */
	public Gamefield() {
		this.gamefield = new byte[MAX_LED_Y][MAX_LED_X];
	}

	/**
	 * Inserts all gameobjects into the gamefield
	 * 
	 * @param boxes
	 *            boxes objects
	 * @param player
	 *            player object
	 */
	public synchronized void refresh(List<Box> boxes, Player player) {

		// clear gamefield
		for (int y = 0; y < gamefield.length; y++) {
			for (int x = 0; x < gamefield.length; x++) {
				gamefield[y][x] = 0;
			}
		}

		// insert all boxes
		for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
			Box current = iter.next();
			insertSymbol(current);
		}

		// insert player
		insertSymbol(player);
	}

	/**
	 * Inserts the gameobjects symbol at a specific position of the gamefield
	 * 
	 * @param objectToInsert
	 *            object to insert
	 */
	private void insertSymbol(GameObject objectToInsert) {
		int posY = objectToInsert.getPosition().getTopLeftY();
		int posX = objectToInsert.getPosition().getTopLeftX();
		for (int y = 0; y < objectToInsert.getSymbol().length; y++) {
			for (int x = 0; x < objectToInsert.getSymbol()[0].length; x++) {
				gamefield[posY + y][posX + x] = objectToInsert.getSymbol()[y][x];
			}
		}
	}

	/**
	 * Returns the gamefield matrix
	 * 
	 * @return the gamefield
	 */
	public byte[][] getGamefield() {
		return gamefield;
	}
}
