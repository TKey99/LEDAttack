package tkey99.ledattack;

import java.util.Iterator;
import java.util.List;

import tkey99.ledattack.gameobjects.Box;
import tkey99.ledattack.gameobjects.GameObject;
import tkey99.ledattack.gameobjects.Player;

public class Gamefield {

	public static final int MAX_LED_X = 24;

	public static final int MAX_LED_Y = 24;

	public static final byte LED_ON = (byte) 255;

	public static final byte LED_OFF = (byte) 0;

	private byte[][] gamefield;

	// erster array zeile
	// zweiter array spalte

	public Gamefield() {
		this.gamefield = new byte[MAX_LED_Y][MAX_LED_X];
	}

	public synchronized boolean refresh(List<Box> boxes, Player player) {

		// clear gamefield
		gamefield = StaticGameFields.EMPTY;

		// insert all boxes
		for (Iterator<Box> iter = boxes.iterator(); iter.hasNext();) {
			Box current = iter.next();
			insertSymbol(current);
		}

		// insert player
		insertSymbol(player);
		return true;
	}

	private boolean insertSymbol(GameObject objectToInsert) {
		int helpY = 0;
		int helpX = 0;
		for (int y = objectToInsert.getPosition().getTopLeftY(); y < objectToInsert
				.getSymbol().length; y++) {
			for (int x = objectToInsert.getPosition().getTopLeftX(); x < objectToInsert
					.getSymbol()[0].length; x++) {
				gamefield[y][x] = objectToInsert.getSymbol()[helpY][helpX];
				helpX++;
			}
			helpY++;
		}
		return true;
	}
}
