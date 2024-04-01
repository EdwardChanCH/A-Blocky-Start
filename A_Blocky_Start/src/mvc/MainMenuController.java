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
		
		//continue button
		view.continueButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//for debug purpose, continue button leads to gameplay view
				Main.mainMenuController.onExit();
				Main.gameplayController.onEnter(Main.mainMenuController);
				
				
				//TODO: implement this
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		//new game button
		view.newGameButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO add action
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		//tutorial button
		view.tutorialButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.tutorialController.onEnter(Main.mainMenuController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		//progression button
		view.progressionButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Main.mainMenuController.onExit();
				System.out.println(Main.loginController.getMode());
				if (Main.loginController.getMode() == UserTypeEnum.STUDENT) {
					Main.studentProgressionController.onEnter(Main.mainMenuController);
				} else {
					Main.teacherProgressionController.onEnter(Main.mainMenuController);
				}
				Main.soundController.playSound(SoundController.buttonClick);
			}
			
		});
		
		//high score button
		view.highscoreButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.highScoreController.onEnter(Main.mainMenuController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		//setting button
		view.settingsButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuController.onExit();
				Main.settingsController.onEnter(Main.mainMenuController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		//exit application when exit button is clicked
		view.exitButtonAddActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.soundController.playSound(SoundController.buttonClick);
				System.exit(0);
			}
		});
	}
	
}
