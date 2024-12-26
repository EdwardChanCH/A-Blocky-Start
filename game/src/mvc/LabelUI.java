package mvc;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * @author Yuting
 *
 */
@SuppressWarnings("serial")
public class LabelUI extends JLabel {
	
	private static final Font defaultFont = new Font("Serif", Font.PLAIN, 16);
	
	/**
	 * Creates a JLabel whose location is managed by layoutmanager
	 * @param size the size of the label
	 * @param text the text inside the label
	 */
	public LabelUI(Dimension size, String text) {
		setVisible(true);
		setSize(size);
		setText(text);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
	}
	
	/**
	 * Creates a JLabel
	 * @param location
	 * @param size
	 * @param text
	 */
	public LabelUI(Point location, Dimension size, String text) {
		
		setVisible(true);
		setLocation(location);
		setSize(size);
		setText(text);
		
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.CENTER);
	}
	
	public LabelUI(Point location, Dimension size, Icon icon) {
		
		setVisible(true);
		setLocation(location);
		setSize(size);
		setIcon(icon);
		
		setHorizontalTextPosition(JLabel.CENTER);
		setVerticalTextPosition(JLabel.CENTER);
	}

	/**
	 * @deprecated
	 * @param text
	 * @param bound
	 */
	LabelUI(String text, Rectangle bound) {
		
		setVisible(true);			//make Label visible
		setBounds(bound);			//set size and location
		setText(text);				//set text
		
		//aligns text
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		
	}
	
	/**
	 * Construct a transparent JLabel that only has a centered text.
	 * @param dimension Dimension
	 * @param position Position, can be null
	 */
	public LabelUI(Dimension dimension, Point position, String text) {
		setVisible(true);
		if (position != null) {
			setLocation(position);
		}
		setSize(dimension);
		setText(text);
		setFont(defaultFont);
	}
	
	/**
	 * Construct a transparent JLabel that only has a centered icon.
	 * @param dimension Dimension
	 * @param position Position, can be null
	 */
	public LabelUI(Dimension dimension, Point position, Icon icon) {
		setVisible(true);
		if (position != null) {
			setLocation(position);
		}
		setSize(dimension);
		setIcon(icon);
	}

}
