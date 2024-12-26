package mvc;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTextField;

/**
 * @author Yuting
 *
 */
@SuppressWarnings("serial")
public class TextFieldUI extends JTextField{
	
	/**
	 * @deprecated
	 * @param bound
	 */
	TextFieldUI(Rectangle bound){
		setVisible(true);
		setBounds(bound);
		setOpaque(isOpaque());
	}
	
	/**
	 * 
	 */
	public TextFieldUI(Point position, Dimension size) {
		setVisible(true);
		setLocation(position);
		setSize(size);
		
	}
	
}
