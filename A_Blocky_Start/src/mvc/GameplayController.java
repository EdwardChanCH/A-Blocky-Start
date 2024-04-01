package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This controller class synchronizes the ActionChain, MazeData, and GameplayView. 
 * @version March 12, 2024
 * @since March 11, 2024
 * @author Chun Ho Chan (Edward)
 * @author Yuting Hou
 */
public class GameplayController implements Controller{
	
	/** the view*/
	private static GameplayView view = new GameplayView();

	/**
	 * Constructor for this class
	 */
	public GameplayController() {
		
		//insert root panel to game frame
		view.insertPanelToFrame(Main.gameFrame);
		
		//adds functionality to UI elements
		populateActionListener();
	}
	
	/**
	 * Adds functionality to UI elements
	 */
	private void populateActionListener() {
		
		view.pauseButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.showPauseMenu();
			}
		});
		
		view.resumeButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.hidePauseMenu();
			}
		});
		
		//TODO: implement save action
		view.saveButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//implement here
			}
		});
		
		view.exitButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.hidePauseMenu();
				Main.gameplayController.onExit();
				Main.mainMenuController.onEnter();
			}
		});
		
		view.forwardButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.addAction("Forward");
			}
		});
		
		view.backwardButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.addAction("Backward");
			}
		});
		
		view.leftButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.addAction("Left");
			}
		});
		
		view.rightButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.addAction("Right");
			}
		});
	}
	
	/**
	 * @return the view
	 */
	public static GameplayView getView() {
		return view;
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
