package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class MainMenuController implements Controller {
	
	private static Controller previous;
	private static MainMenuView view = new MainMenuView();
	
	/**
	 * Constructor.
	 */
	public MainMenuController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
	}

	@Override
	public void onEnter(Controller previous) {
		MainMenuController.previous = previous;
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
		
		view.continueButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Main.loginController.getMode() == UserTypeEnum.STUDENT) {
					// Access the last unlocked stage
					UserData activeUser = Main.loginController.getActiveUserData();
					int lastestStage = activeUser.getProgressionList().size() - 1;
					Main.gameplayController.setupStage(activeUser, lastestStage);
					
					Main.mainMenuController.onExit();
					Main.gameplayController.onEnter(Main.mainMenuController);
				} else {
					// Access the level selection screen
					Main.mainMenuController.onExit();
					Main.studentProgressionController.setUserData(Main.loginController.getActiveUserData());
					Main.studentProgressionController.onEnter(Main.mainMenuController);
				}
				
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.newGameButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.loginController.resetActiveUserData();
				int lastestStage = 0;
				UserData activeUser = Main.loginController.getActiveUserData();
				Main.gameplayController.setupStage(activeUser, lastestStage);
				
				Main.mainMenuController.onExit();
				Main.gameplayController.onEnter(Main.mainMenuController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.tutorialButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.tutorialController.onEnter(Main.mainMenuController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.progressionButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Main.mainMenuController.onExit();
				
				if (Main.loginController.getMode() == UserTypeEnum.STUDENT) {
					Main.studentProgressionController.setUserData(Main.loginController.getActiveUserData());
					Main.studentProgressionController.onEnter(Main.mainMenuController);
				} else {
					Main.teacherProgressionController.onEnter(Main.mainMenuController);
				}
				
				Main.soundController.playSound(SoundController.buttonClick);
			}
			
		});
		
		view.highscoreButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.highScoreController.onEnter(Main.mainMenuController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.settingsButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.settingsController.onEnter(Main.mainMenuController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.exitButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.soundController.playSound(SoundController.buttonClick);
				System.exit(0);
			}
		});
	}
	
}
