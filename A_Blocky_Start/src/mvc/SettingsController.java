package mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class SettingsController implements Controller {
	
	private static Controller previous;
	private static SettingsView view = new SettingsView();
	private static SettingsData data = SettingsData.importData();
	
	/**
	 * Constructor.
	 */
	public SettingsController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
		view.setResolutionField(data.getScreenWidth(), data.getScreenHeight());
		view.setColourBlindField(data.getColourblindMode());
		view.setVolumeLevelField(data.getVolumePercentage());
	}
	
	@Override
	public void onEnter(Controller previous) {
		SettingsController.previous = previous;
		view.setVisibility(true);
		Main.refreshColorblindOverlay();
	}

	@Override
	public void onExit() {
		view.setVisibility(false);
		updateSettings(); // TODO
	}
	
	/**
	 * Help to insert action listeners to UI elements.
	 */
	private void populateActionListener() {
		
		//when back button is pressed
		view.backButtonAddActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.settingsController.onExit();
				SettingsController.previous.onEnter(Main.settingsController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
	}
	
	/**
	 * This function updates setting data 
	 * @author Simon
	 */
	private void updateSettings() {
		data.setScreenWidth(800);
		data.setScreenHeight(600);
		data.setColourblindMode(view.getColourBlindField().equalsIgnoreCase("On"));
		data.setVolumeLevel(view.getVolumeLevelField());
		
		data.exportData();
	}
	public boolean isColourblindActive() {		
		return data.getColourblindMode();
	}
	
	/**
	 * get the dimension from the data
	 */
	public Dimension getDimension() {
		return new Dimension(data.getScreenWidth(), data.getScreenHeight());
	}

}
