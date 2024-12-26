package mvc;

/**
 * This interface represents all controllers.
 * @version 1.0
 * @since March 14, 2024
 * @author Yuting Hou
 * @author Chun Ho Chan (Edward)
 */
public interface Controller {
	
	/**
	 * Call this to load a screen.
	 */
	public void onEnter(Controller previous);
	
	/**
	 * Call this to unload a screen.
	 */
	public void onExit();
	
}
