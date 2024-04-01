package mvc;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * @author Yuting
 * @author Simon
 */
public class SettingsView implements View {
	
	private Dimension viewSize = new Dimension(800, 600);
	
	// define sizes
	private Dimension backButtonSize = new Dimension(30,30);
	private Dimension labelSize = new Dimension(120,36);
	private Dimension comboBoxSize = new Dimension(130,40);
	private Dimension settingsTitleSize = new Dimension(120,36);
	
	// define locations
	private Point backButtonLocation = new Point(10,10);
	private Point colourBlindComboBoxLocation = new Point(450, 100);
	private Point colourBlindLabelLocation = new Point(300,100);
	private Point resolutionComboBoxLocation = new Point(450,200);
	private Point resolutionLabelLocation = new Point(300,200);
	private Point volumeLevelSliderLocation = new Point(450,300);
	private Point volumeLevelLabelLocation = new Point(300,300);
	private Point settingsTitleLocation = new Point(375,10);
	
	private JPanel rootPanel;
	
	private ButtonUI backButton = new ButtonUI(backButtonLocation, backButtonSize, "", IconUI.backButtonIcon);
	
	private JLabel colourBlindLabel = new LabelUI(colourBlindLabelLocation, labelSize, "Colourblind mode:");
	
	private JLabel resolutionLabel = new LabelUI(resolutionLabelLocation, labelSize, "Resolution:");
	
	private JLabel volumeLevelLabel = new LabelUI(volumeLevelLabelLocation, labelSize, "Volume Level:");
	
	private JLabel settingsLabel = new LabelUI(settingsTitleLocation, settingsTitleSize, "Settings");
	
	// colourblind selection 
	private String[] colourblindComboboxOptions = {"Off", "On"};
	private JComboBox<String> colourBlindComboBox = new JComboBox<String>(colourblindComboboxOptions);
	
	// resolution selection
	private String[] resolutionComboboxOptions = {"800 x 600"};
	private JComboBox<String> resolutionComboBox = new JComboBox<String>(resolutionComboboxOptions);
	
	// volume level selection
	private JSlider volumeLevelSlider = new JSlider(0, 100, 0);
	
	/**
	 * Constructor.
	 */
	public SettingsView() {
		initPanel();
		setVisibility(false);
	}

	@Override
	public void initPanel() {
		
		// set up root panel
		rootPanel = new JPanel();
		rootPanel.setSize(viewSize);
		rootPanel.setLayout(null);
		rootPanel.setVisible(false);
		rootPanel.setBackground(IconUI.mediumOrange);

		
		// add colourblind mode selection combobox
		colourBlindComboBox.setVisible(true);
		colourBlindComboBox.setSize(comboBoxSize);
		colourBlindComboBox.setLocation(colourBlindComboBoxLocation);
		rootPanel.add(colourBlindComboBox);
		
		// add resolution selection combobox
		resolutionComboBox.setVisible(true);
		resolutionComboBox.setSize(comboBoxSize);
		resolutionComboBox.setLocation(resolutionComboBoxLocation);
		rootPanel.add(resolutionComboBox);
		
		volumeLevelSlider.setVisible(true);
		volumeLevelSlider.setSize(comboBoxSize);
		volumeLevelSlider.setLocation(volumeLevelSliderLocation);
		volumeLevelSlider.setPaintTrack(true);
		volumeLevelSlider.setMajorTickSpacing(50);
		volumeLevelSlider.setPaintLabels(true);
		rootPanel.add(volumeLevelSlider);
		
		
		// colourblind combobox label
		rootPanel.add(colourBlindLabel);
		
		// resolution combobox label
		rootPanel.add(resolutionLabel);
		
		// volume level label
		rootPanel.add(volumeLevelLabel);
		
		// setting label
		rootPanel.add(settingsLabel);
		
		// back button
		rootPanel.add(backButton);
	}
	
	@Override
	public void refreshPanel() {
		rootPanel.repaint();
		rootPanel.revalidate();
	}
	
	@Override
	public void insertPanelToFrame(JFrame frame) {
		frame.add(rootPanel);
	}

	@Override
	public void setVisibility(boolean visibility) {
		rootPanel.setVisible(visibility);
		backButton.setVisible(visibility);
		colourBlindComboBox.setVisible(visibility);
		resolutionComboBox.setVisible(visibility);
		volumeLevelSlider.setVisible(visibility);
	}
	
	/**
	 * 
	 * @return the selected option in color blind combobox
	 */
	public String getResolutionField() {
		return (String) resolutionComboBox.getSelectedItem();
	}
	
	/**
	 * 
	 * @return the selected option in colourblind combobox
	 */
	public String getColourBlindField() {
		return (String) colourBlindComboBox.getSelectedItem();
	}
	
	/**
	 * 
	 * @return the selected level in volume level slider
	 */
	public int getVolumeLevelField() {
		return volumeLevelSlider.getValue();
	}
	
	public void setResolutionField(int width, int height) {
		int option = -1;
		if ((width == 800) && (height == 600)) {
			option = 0;
		}
		resolutionComboBox.setSelectedItem(option);
	}
	
	public void setColourBlindField(boolean active) {
		int option = -1;
		if (active) {
			option = 1;
		} else {
			option = 0;
		}
		colourBlindComboBox.setSelectedIndex(option);
	}
	
	public void setVolumeLevelField(int value) {
		volumeLevelSlider.setValue(value);
	}
	
	// Action Listeners
	
	public void backButtonAddActionListener(ActionListener actionListener) {
		backButton.addActionListener(actionListener);
	}

}
