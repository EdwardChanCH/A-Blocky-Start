package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ScrollPaneUI extends JScrollPane {

	/**
	 * Creates an empty JScrollPane.<br>
	 * Used by GameplayView.<br>
	 * @param basePanel The JPanel that contains this JScrollPane
	 * @param scrollDirection 0 = horizontal scroll bar, 1 = vertical scroll bar, 2 = both
	 */
	public ScrollPaneUI(JComponent innerPanel, int scrollDirection, boolean visibility, Dimension dimension, Point position) {
		super(innerPanel);
		
		if (scrollDirection == 0) {
			setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
			setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		} else if (scrollDirection == 1) {
			setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
			setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		} else {
			setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
			setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		}
		getVerticalScrollBar().setUnitIncrement(10);
		getHorizontalScrollBar().setUnitIncrement(10);
		setWheelScrollingEnabled(true);
		
		setVisible(visibility);
		setSize(dimension);
		setLocation(position);
		setBackground(new Color(0, 0, 0, 0));
	}
	
}
