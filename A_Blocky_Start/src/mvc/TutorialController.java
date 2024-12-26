package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class TutorialController implements Controller {
	
	private static Controller previous;
	private TutorialView view = new TutorialView();
	
	/**
	 * Constructor.
	 */
	public TutorialController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
	}
	
	@Override
	public void onEnter(Controller previous) {
		TutorialController.previous = previous;
		view.setVisibility(true);
		Main.refreshColorblindOverlay();
	}
	
	@Override
	public void onExit() {
		view.setVisibility(false);
	}
	
	/**
	 * Help to insert action listeners to UI elements.
	 */
	private void populateActionListener() {
		
		view.backButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.tutorialController.onExit();
				TutorialController.previous.onEnter(Main.tutorialController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
	}
	
}
