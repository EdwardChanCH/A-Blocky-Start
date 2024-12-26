package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class LayeredPaneUI extends JLayeredPane {

	/**
	 * Construct a default JLayeredPane without any layout manager.<br>
	 * Used by GameplayView.<br>
	 * @param visibility Visibility
	 * @param dimension Dimension, can be null
	 * @param position Position, can be null
	 * @param bgColor Background color
	 */
	public LayeredPaneUI(boolean visibility, Dimension dimension, Point position, Color bgColor) {
		setVisible(visibility);
		if (dimension != null) {
			setSize(dimension);
		}
		if (position != null) {
			setLocation(position);
		}
		setBackground(bgColor);
	}
	
}
