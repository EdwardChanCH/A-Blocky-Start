package mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

/**
 * This controller class manages the student progression screen.
 * @version 1.0
 * @since March 11, 2024
 * @author Doyle Blacklock
 */
public class StudentProgressionController implements Controller {
	
	private static Controller previous;
	private StudentProgressionView view = new StudentProgressionView();
	private StudentProgressionData data;
	
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
		view.backButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.studentProgressionController.onExit();
				StudentProgressionController.previous.onEnter(Main.studentProgressionController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
			
		});
		
	}
	
	public void setUserData(UserData userData) {
		data = new StudentProgressionData(userData);
		
		ProgressionData progressionData;
		for (int i = 0; i < data.entriesPerPage; i++) {
			progressionData = data.getProgression(i);

			if (progressionData == null) {
				break;
			}
			
			ButtonUI playButton = new ButtonUI(new Dimension(400, 20), "", IconUI.playButtonIcon);
			playButton.addActionListener(new PlayButtonListener(i));
			
			view.setEntry(
					i,
					progressionData.getStageID(),
					progressionData.getCompleted(),
					progressionData.getShortestSteps(),
					progressionData.getHighestScore(), 
					progressionData.getTimeSpent(), 
					progressionData.getAttempts(), 
					playButton
					);
		}
		
	}
	
	// Action Listeners
	
	private class PlayButtonListener implements ActionListener {

		private int stageID;
		
		public PlayButtonListener(int stageID) {
			this.stageID = stageID;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			UserData activeUser = Main.loginController.getActiveUserData();
			Main.gameplayController.setupStage(activeUser, stageID);
			
			Main.studentProgressionController.onExit();
			Main.gameplayController.onEnter(Main.studentProgressionController);
			Main.soundController.playSound(SoundController.buttonClick);
		}
		
	}
	
}
