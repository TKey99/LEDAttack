package tkey99.ledattack.gameobjects;

import tkey99.ledattack.Gamefield;

public class Box extends GameObject {
	
private final byte X = Gamefield.LED_ON;
	
	private final byte[][] SYMBOL = new byte[][] {
			{X, X, X},
			{X, 0, X},
			{X, X, X}};

	@Override
	public boolean move(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean spawn(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
