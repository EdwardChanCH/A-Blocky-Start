import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * @author Yuting
 *
 */
public class Panel extends JPanel{
	
	Panel(Rectangle bound) {
		
		setLayout(null);
		setBounds(bound);
		setVisible(true);
		setBackground(Color.gray);
		
	}

	/**
	 * This function should be left empty
	 * Java will have runtime error if this is deleted
	 */
	public void OnEnter() {
	}

	


}
