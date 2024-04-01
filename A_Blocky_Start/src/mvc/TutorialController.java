package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class TutorialController {
	
	/** The view*/
	private TutorialView view = new TutorialView();
	
	/**
	 * Called in Main.java
	 * 
	 */
	public TutorialController() {
		
		view.insertPanelToFrame(Main.gameFrame);
		
		populateActionListener();
	}
	
	/**
	 * 
	 */
	private void populateActionListener() {
		
		view.backButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.tutorialController.onExit();
				Main.mainMenuController.onEnter();
			}
		});
	}
	
	/**
	 * 
	 */
	public void onEnter() {
		view.setVisibility(true);
		Main.setColorblindOverlay();
	}
	
	/**
	 * 
	 */
	public void onExit() {
		view.setVisibility(false);
	}
	
}
