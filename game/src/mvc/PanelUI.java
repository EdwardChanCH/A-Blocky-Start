package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * This builder class makes JPanels. 
 * @version 1.0
 * @since March 10, 2024
 * @author Yuting Hou
 * @author Chun Ho Chan (Edward)
 */
@SuppressWarnings("serial")
public class PanelUI extends JPanel {

	/**
	 * @deprecated
	 * @param bound
	 */
	PanelUI(Rectangle bound) {
		setLayout(null);
		setBounds(bound);
		setVisible(true);
		setBackground(Color.gray);
	}

	/**
	 * Constructor for panel whose location is managed by layout manager.
	 * @param size
	 */
	PanelUI(Dimension size) {
		setLayout(null);
		setSize(size);
		setPreferredSize(size);
		setVisible(true);
		setBackground(Color.gray);
	}

	/**
	 * Constructor for panel whose location is managed by layout manager.
	 * @param size
	 * @param color
	 */
	PanelUI(Dimension size, Color color) {
		setLayout(null);
		setSize(size);
		setPreferredSize(size);
		setVisible(true);
		setBackground(color);
	}
	
	/**
	 * Construct a default JPanel without any LayoutManager.<br>
	 * Construct a panel with location, size and color
	 * @param location
	 * @param size
	 * @param color
	 */
	public PanelUI(Point location, Dimension size, Color color) {
		setLocation(location);
		setSize(size);
		setPreferredSize(size);
		setVisible(true);
		setBackground(color);
	}
	
	/**
	 * Construct a default JPanel without any layout manager.<br>
	 * Used by GameplayView.<br>
	 * @param visibility Visibility
	 * @param dimension Dimension, can be null
	 * @param position Position, can be null
	 * @param bgColor Background color
	 */
	public PanelUI(boolean visibility, Dimension dimension, Point position, Color bgColor) {
		setLayout(null);
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
