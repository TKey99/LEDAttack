package tkey99.ledattack.gameobjects;

public abstract class GameObject {

	private int[] position_top_left;

	private int[] position_bottom_right;

	public abstract boolean move(int x, int y);

	public abstract boolean spawn(int x, int y);
}