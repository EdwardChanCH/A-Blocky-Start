package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * 
 */
public class TeacherProgressionController implements Controller {

	private static Controller previous;
	private TeacherProgressionView view = new TeacherProgressionView();
	private TeacherProgressionData data = new TeacherProgressionData();

	/**
	 * Constructor.
	 */
	public TeacherProgressionController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
	}

	@Override
	public void onEnter(Controller previous) {
		TeacherProgressionController.previous = previous;
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
				Main.teacherProgressionController.onExit();
				TeacherProgressionController.previous.onEnter(Main.teacherProgressionController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		//add functionality to selector
		view.selectorAddChangeListener(new ChangeListener() {
			
			//when the number is changed
			@Override
			public void stateChanged(ChangeEvent e) {
				
				//refresh view
				refreshView();
			}
		});
	}
	
	/**
	 * This function loads user data into and refreshes teacher progression view
	 */
	private void refreshView() {
		
		//updates data, sets page
		data.setPage(view.getPage());
		data.updateEntries();
		
		//add the entries on the page to view
		//some entries may not exist
		for (int i = 0; i < 10; i++) {
			
			UserData currData = data.getUserData(i);
			
			//if this entry exist, update
			if (currData != null) {
				view.setEntry(
						i, 
						currData.getUsername().substring(0, currData.getUsername().length() - 13), 
						currData.getTotalTimeSpent(), 
						currData.getTotalAttempts(), 
						currData.getTotalScore(), 
						-1
						);
			}else {  //if this entry does not exist, update with a blank
				view.setEntry(i, "Empty", 0, 0, 0, 0);
			}
			
			view.refreshPanel();
		}
	}

}
