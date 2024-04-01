package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class StudentProgressionController implements Controller {
	
	private static Controller previous;
	private StudentProgressionView view = new StudentProgressionView();
	private StudentProgressionData data = null;
	
	/**
	 * Constructor.
	 */
	public StudentProgressionController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
	}
	
	@Override
	public void onEnter(Controller previous) {
		StudentProgressionController.previous = previous;
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
		
		//when back button is pressed, return to previous screen
		view.backButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.studentProgressionController.onExit();
				StudentProgressionController.previous.onEnter(Main.studentProgressionController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
			
		});
		
	}
	
	/**
	 * 
	 * @param userData
	 */
	public void setUserData(UserData userData) {
		data = new StudentProgressionData(userData);
		ProgressionData progressionData;
		
		for (int i = 0; i < 10; i++) {
			
			progressionData = data.getProgression(i);

			if (progressionData == null) {
				break;
			}
			
			view.setEntry(
					i,
					progressionData.getStageID(),
					progressionData.getCompleted(),
					progressionData.getShortestSteps(),
					progressionData.getHighestScore(), 
					progressionData.getTimeSpent(), 
					progressionData.getAttempts()
				);
		}
	}
}
