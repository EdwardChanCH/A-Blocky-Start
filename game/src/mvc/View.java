package mvc;

import javax.swing.JFrame;

/**
 * This interface represents all views.
 * @version 1.0
 * @since March 28, 2024
 * @author Yuting Hou
 * @author Chun Ho Chan (Edward)
 */
public interface View {
	
	/**
	 * (Re)Initialize the root panel.
	 */
	public void initPanel();
	
	/**
	 * Refresh (repaint & revalidate) the root panel.
	 */
	public void refreshPanel();
	
	/**
	 * Insert the root panel into a JFrame.
	 * @param frame JFrame
	 */
	public void insertPanelToFrame(JFrame frame);
	
	/**
	 * Toggle the visibility of the root panel.
	 * @param visibility True if visible, false otherwise.
	 */
	public void setVisibility(boolean visibility);

}
