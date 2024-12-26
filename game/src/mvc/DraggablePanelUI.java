package mvc;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * @author Yuting
 * We dont have to implement this
 * 
 */
@SuppressWarnings("serial")
public class DraggablePanelUI extends JPanel {
	
	//top left corner x & y pos
	private int mouseX = 0, mouseY = 0 , panelX = 0, panelY = 0;
	
	DraggablePanelUI () {
		setBackground(Color.red);
		setBounds(0,0,50,50);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
		        mouseX = e.getXOnScreen();
		        mouseY = e.getYOnScreen();

		        panelX = getX();
		        panelY = getY();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				 int deltaX = e.getXOnScreen() - mouseX;
			     int deltaY = e.getYOnScreen() - mouseY;

			     setLocation(panelX + deltaX, panelY + deltaY);
			     
			}
		});
	}
	
	
}
