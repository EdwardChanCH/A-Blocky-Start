package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class HighScoreController implements Controller {
	
	private static Controller previous;
	private static HighScoreView view = new HighScoreView();
	private static HighScoreData data = HighScoreData.importData();
	
	/**
	 * Constructor.
	 */
	public HighScoreController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
	}
	
	@Override
	public void onEnter(Controller previous) {
		HighScoreController.previous = previous;
		view.setVisibility(true);
		Main.refreshColorblindOverlay();
		updateHighScore();
	}
	
	@Override
	public void onExit() {
		view.setVisibility(false);
	}
	
	/**
	 * Help to insert action listeners to UI elements.
	 */
	private void populateActionListener() {
		
		// back button
		view.backButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.highScoreController.onExit();
				HighScoreController.previous.onEnter(Main.highScoreController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
	}
	
	private void updateHighScore() {
		for(int i = 0; i < 5; i++) {
			view.addHighscore(i, data.getUsername(i), data.getHighScore(i));
		}
		data.exportData();
	}
	
	public void addNameScore(String username, int highScore){
		data.addNameScore(username, highScore);
		data.exportData();
	}

}
