package mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class SettingsController implements Controller {
	
	private static SettingsView view = new SettingsView();
	
	private static SettingsData data = SettingsData.importData();
	
	/**
	 * 
	 */
	public SettingsController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
		view.setResolutionField(data.getScreenWidth(), data.getScreenHeight());
		view.setColourBlindField(data.getColourblindMode());
		view.setVolumeLevelField(data.getVolumePercentage());
	}
	
	/**
	 * 
	 */
	private void populateActionListener() {
		
		//when back button is pressed
		view.backButtonAddActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.settingsController.onExit();
				Main.mainMenuController.onEnter();
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
		updateSettings();
	}
	
	/**
	 * get the dimension from the data
	 */
	public Dimension getDimension() {
		return new Dimension(data.getScreenWidth(), data.getScreenHeight());
	}

}
