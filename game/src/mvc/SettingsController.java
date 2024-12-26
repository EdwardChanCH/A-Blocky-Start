package mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	}
	
	/**
	 * Help to insert action listeners to UI elements.
	 */
	private void populateActionListener() {
		
		view.backButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.settingsController.onExit();
				SettingsController.previous.onEnter(Main.settingsController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.resolutionComboBox(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				data.setScreenWidth(800);
				data.setScreenHeight(600);
				data.exportData();
			}
			
		});
		
		view.colourBlindComboBox(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				data.setColourblindMode(view.getColourBlindField().equalsIgnoreCase("On"));
				data.exportData();
			}
			
		});
		
		view.volumeLevelSlider(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				data.setVolumeLevel(view.getVolumeLevelField());
				Main.soundController.updateVolume((float) data.getVolumePercentage());
				data.exportData();
			}
			
		});
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
	
	/**
	 * get the volume from the data
	 */
	public int getVolumePercentage() {
		return data.getVolumePercentage();
	}

}
