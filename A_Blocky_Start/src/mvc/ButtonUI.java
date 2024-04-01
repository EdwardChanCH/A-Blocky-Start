package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;


/**
 * @author Yuting
 * This is a JButton
 * When clicked, switches the view from one panel to another
 */
public class ButtonUI extends JButton{
	
	
	ButtonUI(Dimension size, String text) {
	
		setSize(size);
		setText(text);				//set text
		setPreferredSize(size);
		
		//aligns text
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		
	}
	
	/**
	 * This creates a new button with a default background
	 * @param text the text inside the button
	 * @param bound the size and location of the button
	 */
	ButtonUI(Point location, Dimension size, String text) {
		
		setVisible(true);			//make button visible
		setLocation(location);			//set size and location
		setSize(size);

		setIcon(IconUI.backButtonIcon);	//set image

		setText(text);				//set text
		
		//aligns text
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		
		//remove default background
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		
	}
	
	/**
	 * Creates a new button without a location
	 * @param size
	 * @param text
	 * @param icon
	 */
	ButtonUI(Dimension size, String text, Icon icon){
		setVisible(true);			//make button visible
		setSize(size);
		setIcon(icon);	//set image
		setText(text);				//set text
		setIcon(icon);
		
		//aligns text
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		
		//remove default background
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	ButtonUI(Point location, Dimension size, String text, Icon icon) {
		
		setVisible(true);			//make button visible
		setLocation(location);			//set size and location
		setSize(size);

		setIcon(icon);	//set image

		setText(text);				//set text
		setIcon(icon);
		
		//aligns text
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		
		//remove default background
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		
	}
	
	/**
	 * This creates a new custom button
	 * @param text
	 * @param icon
	 * @param bound
	 * @deprecated
	 */
	ButtonUI(String text, Icon icon, Rectangle bound) {
		
		setVisible(true);			//make button visible
		setBounds(bound);			//set size
		setIcon(icon);				//set image
		setText(text);				//set text
		
		//aligns text
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		
		//remove default background
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		
	}
	
	/**
	 * Construct a transparent JButton that only has an icon.
	 * @param visibility Visibility
	 * @param dimension Dimension, can be null
	 * @param position Position, can be null
	 * @param icon Icon
	 */
	public ButtonUI(boolean visibility, Dimension dimension, Point position, Icon icon) {
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusable(false);
		setVisible(visibility);
		if (position != null) {
			setLocation(position);
		}
		if (dimension != null) {
			setSize(dimension);
		}
		if (position != null) {
			setBounds(new Rectangle(position, dimension));
		} else {
			setBounds(new Rectangle(new Point(0, 0), dimension));
		}
		setIcon(icon);
	}
	
	/**
	 * Construct a dark, semi-transparent JButton that has no output.<br>
	 * Used as an overlay in pause menu to blocks input to all buttons layered underneath it.<be>
	 * @param visibility Visibility
	 * @param dimension Dimension
	 * @param position Position, can be null
	 */
	public ButtonUI(boolean visibility, Dimension dimension, Point position, Color colour) {
		setContentAreaFilled(true);
		setBackground(colour);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusable(false);
		setEnabled(visibility);
		setVisible(visibility);
		setLocation(position);
		setSize(dimension);
		setBounds(new Rectangle(position, dimension));
	}
	
}
