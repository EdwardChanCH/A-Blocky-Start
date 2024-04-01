package mvc;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This is the main menu of the game
 * @author Yuting
 */
public class MainMenuView implements View {
	
	// size of this view
	private Dimension viewSize = Main.getGameFrameDimension();
	
	// locations
	final Point asidenPanelLocation = new Point(600, 0);
	final Point gameTitleLocation = new Point(0, 0);  // game title should be top of screen
	final Point mainMenuLabelLocation = new Point(670, 40);
	final Point creditLocation = new Point(200, 500);
	
	// sizes
	final Dimension asidePanelSize = new Dimension(200, 600);
	final Dimension gameTitleSize = new Dimension(600, 600); 
	final Dimension labelSize = new Dimension(100, 100);
	final Dimension creditSize = new Dimension(400, 100);
	
	/**
	 * The size of Main menu buttons
	 */ 
	final Dimension ButtonSize = new Dimension(150, 40); 	
		
	/**
	 * Amount of vertical space between each button
	 */
	final int buttonSpacing = 5;
	
	// root panel
	private JPanel rootPanel;
	

	/** panel for visual effect*/
	PanelUI asidePanel 	= new PanelUI(asidenPanelLocation, asidePanelSize, IconUI.lightOrange);
	
	// game title
	private LabelUI title = new LabelUI(gameTitleLocation, gameTitleSize, IconUI.gameTitleIcon);
	private LabelUI mainMenuLabel = new LabelUI(mainMenuLabelLocation, labelSize, "Main Menu");
	private LabelUI creditLabel = new LabelUI(creditSize, creditLocation, "CS2212B \n Doyle, Edward, Eunhak, Simon, Yuting, Build 1.0");
			
	// buttons declaration
	private ButtonUI continueButton 	= new ButtonUI(ButtonSize, "", IconUI.continueButtonIcon);
	private ButtonUI newGamButton 		= new ButtonUI(ButtonSize, "", IconUI.newGameButtonIcon);
	private ButtonUI tutorialButton  	= new ButtonUI(ButtonSize, "", IconUI.tutorialButtonIcon);
	private ButtonUI progressionButton 	= new ButtonUI(ButtonSize, "", IconUI.progressionButtonIcon);
	private ButtonUI highScoreButton 	= new ButtonUI(ButtonSize, "", IconUI.highscoreButtonIcon);
	private ButtonUI settingsButton		= new ButtonUI(ButtonSize, "", IconUI.settingsButtonIcon);
	private ButtonUI exitButton			= new ButtonUI(ButtonSize, "", IconUI.exitButtonIcon);

	
	/**
	 * Constructor.
	 */
	public MainMenuView() {
		initPanel();
		setVisibility(false);
	}
	
	@Override
	public void initPanel() {
		// TODO deleted: switch panel button action, add this back in in in the controller
		
		// set up root panel
		rootPanel = new JPanel();
		rootPanel.setSize(viewSize);
		rootPanel.setLayout(null);	
		rootPanel.setVisible(false);
		rootPanel.setBackground(Color.lightGray);
		rootPanel.add(mainMenuLabel);
		rootPanel.add(title);
		rootPanel.add(creditLabel);
		
		// populate buttons
		asidePanel.setLayout(new FlowLayout());
		asidePanel.setBorder(new EmptyBorder(new Insets(100, 10, 10, 10)));  //Space buttons from border
		asidePanel.add(continueButton);
		asidePanel.add(newGamButton);
		asidePanel.add(tutorialButton);
		asidePanel.add(progressionButton);
		asidePanel.add(highScoreButton);
		asidePanel.add(settingsButton);
		asidePanel.add(exitButton);
		
		rootPanel.add(asidePanel);
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
	}
	
	// Action Listeners
	
	public void continueButtonAddActionListener(ActionListener actionListener) {
		continueButton.addActionListener(actionListener);
	}
	
	public void newGameButtonAddActionListener(ActionListener actionListener) {
		newGamButton.addActionListener(actionListener);
	}
	
	public void tutorialButtonAddActionListener(ActionListener actionListener) {
		tutorialButton.addActionListener(actionListener);
	}
	
	public void progressionButtonAddActionListener(ActionListener actionListener) {
		progressionButton.addActionListener(actionListener);
	}
	
	public void highscoreButtonAddActionListener(ActionListener actionListener) {
		highScoreButton.addActionListener(actionListener);
	}
	
	public void settingsButtonAddActionListener(ActionListener actionListener) {
		settingsButton.addActionListener(actionListener);
	}
	
	public void exitButtonAddActionListener(ActionListener actionListener) {
		exitButton.addActionListener(actionListener);
	}

}
