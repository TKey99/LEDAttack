package tkey99.ledattack;

public class StaticGameFields {

	private StaticGameFields() {

	}
	
	/**
	 * Value to let an LED shine.
	 */
	private static final byte X = Gamefield.LED_ON;
	
	public static final byte[][] COUNTDOWN_READY = new byte[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{X, X, X, 0, 0, X, X, X, X, 0, 0, X,   X, 0, 0, X, X, 0, 0, 0, X, 0, 0, X},
		{X, X, X, X, 0, X, X, X, X, 0, 0, X,   X, 0, 0, X, X, X, 0, 0, X, 0, 0, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, X, X, 0, X, 0, 0, X},
		{X, X, 0, X, 0, X, X, 0, 0, 0, X, X,   X, X, 0, X, X, X, X, 0, X, 0, 0, X},
		{X, X, 0, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, X, X, X, X},
		{X, X, 0, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, 0, X, X, 0, X, X, 0},
		{X, X, X, 0, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, 0, X, X, 0, X, X, 0},

		{X, X, X, 0, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, 0, X, X, 0, X, X, 0},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, 0, X, X, 0, X, X, 0},
		{X, X, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, 0, X, X, 0},
		{X, X, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, 0, X, X, 0},
		{X, X, 0, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, X, X, 0, 0, X, X, 0},
		{X, X, 0, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, X, X, 0, 0, X, X, 0},
		{X, X, 0, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, X, 0, 0, 0, X, X, 0},
		{X, X, 0, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, 0, 0, 0, 0, X, X, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

	public static final byte[][] COUNTDOWN_SET = new byte[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, X, X, X,   0, 0, 0, 0, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, X, X, X,   0, 0, 0, 0, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, X, X, X,   0, 0, 0, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, 0, X, X, X, X, 0, 0, 0},

		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, 0, 0, 0, X, X, X, 0, X, X, X,   0, 0, 0, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, 0, 0, 0, X, X, X, 0, X, X, X,   0, 0, 0, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, 0, 0, 0, X, X, X, 0, X, X, X,   0, 0, 0, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, 0, X, X, X,   X, X, X, 0, 0, X, X, X, X, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

	public static final byte[][] COUNTDOWN_GO = new byte[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, X, X, X, X, X, X, X, X, 0, 0,   0, 0, X, X, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, X, X, X, X, X, 0, 0,   0, 0, X, X, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, X, X, X, X, X, 0, 0,   0, 0, X, X, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, 0, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, 0, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, 0, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, 0, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, 0, 0, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
	
		{0, 0, X, X, X, 0, X, X, X, X, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, X, X, X, X, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, X, X, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, X, X, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, 0, 0, 0, X, X, 0, 0,   0, 0, X, X, X, 0, 0, X, X, X, 0, 0},
		{0, 0, X, X, X, X, X, X, X, X, 0, 0,   0, 0, X, X, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, X, X, X, X, X, 0, 0,   0, 0, X, X, X, X, X, X, X, X, 0, 0},
		{0, 0, X, X, X, X, X, X, X, X, 0, 0,   0, 0, X, X, X, X, X, X, X, X, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

	public static final byte[][] SCORE = new byte[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, X, 0, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, 0, 0, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, X, X, 0, 0},
		{X, X, 0, 0, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, X, X, 0, 0},
		{X, X, 0, 0, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, X, X, 0, 0},
		{X, X, X, X, 0, X, X, 0, 0, 0, X ,0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, X, 0, 0, X, X, X, X},

		{X, X, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, X, 0, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{0, 0, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, 0, 0},
		{0, 0, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, 0, 0},
		{0, 0, X, X, 0, X, X, 0, 0, 0, X, 0,   0, X, 0, X, X, 0, X, 0, X, X, 0, 0},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, 0, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, 0, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, X,   X, X, 0, X, X, 0, X, 0, X, X, X, X},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

	public static final byte[][] TITLE = new byte[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, X, X, 0, 0, 0, 0, 0, X, X,   X, X, 0, 0, X, X, X, 0, 0, 0, 0, 0},
		{0, 0, 0, X, X, 0, 0, 0, 0, 0, X, X,   X, X, 0, 0, X, X, X, X, 0, 0, 0, 0},
		{0, 0, 0, X, X, 0, 0, 0, 0, 0, X, X,   0, 0, 0, 0, X, X, 0, X, X, 0, 0, 0},
		{0, 0, 0, X, X, 0, 0, 0, 0, 0, X, X,   X, X, 0, 0, X, X, 0, X, X, 0, 0, 0},
		{0, 0, 0, X, X, 0, 0, 0, 0, 0, X, X,   X, X, 0, 0, X, X, 0, X, X, 0, 0, 0},
		{0, 0, 0, X, X, 0, 0, 0, 0, 0, X, X,   0, 0, 0, 0, X, X, 0, X, X, 0, 0, 0},
		{0, 0, 0, X, X, X, X, X, 0, 0, X, X,   X, X, 0, 0, X, X, X, X, 0, 0, 0, 0},
		{0, 0, 0, X, X, X, X, X, 0, 0, X, X,   X, X, 0, 0, X, X, X, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, X, X, 0, X, X, X, X, X, X, 0, X,   X, 0, 0, X, X, X, X, 0, X, 0, 0, X},
		{0, X, X, 0, X, X, X, X, X, X, 0, X,   X, 0, 0, X, X, X, X, 0, X, X, X, 0},
		{X, 0, 0, X, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, X, X, 0, 0, 0, X, X, X, 0},
		{X, 0, 0, X, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, X, X, 0, 0, 0, X, X, 0, 0},
		{X, X, X, X, 0, X, 0, 0, X, 0, X, X,   X, X, 0, X, X, 0, 0, 0, X, X, 0, 0},
		{X, X, X, X, 0, X, 0, 0, X, 0, X, X,   X, X, 0, X, X, 0, 0, 0, X, X, X, 0},
		{X, 0, 0, X, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, 0},
		{X, 0, 0, X, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, 0, 0, X},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

	public static final byte[][] PAUSE = new byte[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{X, X, X, X, 0, 0, X, X, 0, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, X, X, 0, 0, X, X, 0, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, 0, 0, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, 0, 0, 0, X, X, 0, 0},
		{X, 0, 0, X, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, X, X, 0, 0, 0, X, X, 0, 0},
		{X, 0, 0, X, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, X, X, 0, 0, 0, X, X, 0, 0},
		{X, X, X, X, 0, X, X, X, X, 0, X ,0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, X},

		{X, X, X, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, X, X, 0, X, X, X, X, 0, X, 0,   0, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, 0, 0, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, 0, 0, X, X, 0, X, X, 0, 0},
		{X, X, 0, 0, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, 0, 0, X, X, 0, X, X, 0, 0},
		{X, X, 0, 0, 0, X, 0, 0, X, 0, X, 0,   0, X, 0, 0, 0, X, X, 0, X, X, 0, 0},
		{X, X, 0, 0, 0, X, 0, 0, X, 0, X, X,   X, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, 0, 0, 0, X, 0, 0, X, 0, X, X,   X, X, 0, X, X, X, X, 0, X, X, X, X},
		{X, X, 0, 0, 0, X, 0, 0, X, 0, X, X,   X, X, 0, X, X, X, X, 0, X, X, X, X},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
	
	public static final byte[][] EMPTY = new byte[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,   0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

}
