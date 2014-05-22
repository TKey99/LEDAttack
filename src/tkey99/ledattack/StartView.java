package tkey99.ledattack;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This View is shown at the start of the application.
 * 
 * @author TKey99
 * 
 */
public class StartView extends LinearLayout {

	/**
	 * Title of the game.
	 */
	TextView title;

	/**
	 * Button to start the game.
	 */
	Button start;

	/**
	 * Button to show the manuals.
	 */
	Button manual;

	/**
	 * Button to show the highscores.
	 */
	Button highscores;

	public StartView(Context context) {
		super(context);

		this.setOrientation(LinearLayout.VERTICAL);
		
		// this.addView(highscores);
	}

	
}
