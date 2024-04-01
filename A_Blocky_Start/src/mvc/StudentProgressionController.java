package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class StudentProgressionController implements Controller{
	
	/** The view*/
	private StudentProgressionView view;
	
	private Controller previousController;
	
	private StudentProgressionData data;
	
	/**
	 * Constructor for this class
	 */
	public StudentProgressionController() {
		
		//create view which stores UI elements
		view = new StudentProgressionView();
		
		this.data = null;
		
		//insert view to game frame
		view.insertPanelToFrame(Main.gameFrame);
		
		//adds functionality to UI elements
		PopulateActionListener();
	}
	
	/**
	 * Adds action listener to UI elements
	 */
	private void PopulateActionListener() {
		
		//when back button is pressed, return to previous screen
		view.backButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.studentProgressionController.onExit();
				previousController.onEnter();
			}
		});
		
	}
	
	/**
	 * 
	 * @param userData
	 */
	public void setUserData(UserData userData) {
		this.data = new StudentProgressionData(userData);
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
	
	/**
	 * 
	 */
	public void onExit() {
		view.setVisibility(false);
	}

	/**
	 * 
	 */
	@Override
	public void onEnter() {
		view.setVisibility(true);
		Main.setColorblindOverlay();
		this.previousController = Main.mainMenuController;
	}
	
	/**
	 * 
	 */
	public void OnEnterSpecial(Controller previousController) {
		onEnter();
		this.previousController = previousController; // Override default back button behavior
		
	}

}
