package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * 
 */
public class TeacherProgressionController implements Controller {

	private TeacherProgressionView view = new TeacherProgressionView();
	
	private TeacherProgressionData data = new TeacherProgressionData();

	/**
	 * 
	 */
	public TeacherProgressionController() {

		view = new TeacherProgressionView();

		data = new TeacherProgressionData();

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
				Main.teacherProgressionController.onExit();
				Main.mainMenuController.onEnter();
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

	/**
	 * 
	 */
	@Override
	public void onEnter() {
		view.setVisibility(true);
		Main.setColorblindOverlay();
	}

	/**
	 * 
	 */
	@Override
	public void onExit() {
		view.setVisibility(false);
	}

}
