package mvc;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class SpinnerUI extends JSpinner {

	/**
	 * Constructs a default JSpinner.
	 * @param dimension Dimension
	 * @param position Position
	 * @param defaultValue Default value
	 * @param minValue Minimum value
	 * @param maxValue Maximum value
	 * @param step Step size, up button = +step, down button = -step
	 */
	public SpinnerUI(Dimension dimension, Point position, int defaultValue, int minValue, int maxValue, int step) {
		super(new SpinnerNumberModel(defaultValue, minValue, maxValue, step));
		if (dimension != null) {
			setSize(dimension);
		}
		if (position != null) {
			setLocation(position);
		}
	}
	
}
