package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class MainMenuController implements Controller{
	
	//Main menu view
	private static MainMenuView view = new MainMenuView();
	
	/**
	 * Main menu controller constructor
	 * Called in Main
	 */
	public MainMenuController() {
		view.insertPanelToFrame(Main.gameFrame);
		
		populateActionListener();
	}
	
	/**
	 * Adds functionality to UI elements
	 */
	private void populateActionListener() {
		
		//continue button
		view.continueButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//for debug purpose, continue button leads to gameplay view
				Main.mainMenuController.onExit();
				Main.gameplayController.onEnter();
				
				
				//TODO: implement this
			}
		});
		
		//new game button
		view.newGameButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add action
			}
		});
		
		//tutorial button
		view.tutorialButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.tutorialController.onEnter();
			}
		});
		
		//progression button
		view.progressionButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Main.mainMenuController.onExit();
				System.out.println(Main.loginController.getMode());
				if (Main.loginController.getMode() == UserTypeEnum.TEACHER) {
					Main.teacherProgressionController.onEnter();
				}else {
					Main.studentProgressionController.OnEnterSpecial(Main.mainMenuController);
				}
				
				
				
			}
		});
		
		//high score button
		view.highscoreButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.highScoreController.onEnter();
			}
		});
		
		//setting button
		view.settingsButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.settingsController.onEnter();
			}
		});
		
		//exit application when exit button is clicked
		view.exitButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * Is called when entering main menu
	 */
	@Override
	public void onEnter() {
		
		//shows the view
		view.setVisibility(true);
		Main.setColorblindOverlay();
	}

	/**
	 * Is called when exiting 
	 */
	@Override
	public void onExit() {
		
		//hides the view
		view.setVisibility(false);
	}
	
}
