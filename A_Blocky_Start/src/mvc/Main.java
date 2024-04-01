package mvc;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class launches the game. 
 * @version 1.0
 * @since ???
 * @author Yuting
 */
public class Main {
	
	/**
	 * The JFrame that the game runs in.
	 */
	public static JFrame gameFrame = new JFrame("A Blocky Start");
	
	// Screen controllers
	public static SettingsController settingsController = new SettingsController(); // must be first to be initialized
	public static LoginController loginController = new LoginController();
	public static MainMenuController mainMenuController = new MainMenuController();
	public static StudentProgressionController studentProgressionController = new StudentProgressionController();
	public static TeacherProgressionController teacherProgressionController = new TeacherProgressionController();
	public static GameplayController gameplayController = new GameplayController();
	public static TutorialController tutorialController = new TutorialController();
	public static HighScoreController highScoreController = new HighScoreController();
	
	// Non-Screen controllers
	public static ErrorLogController errorLogController = new ErrorLogController();
	public static SoundController soundController = new SoundController();
	
	/**
	 * Colourblind overlay JPanel.
	 */
	private static PanelUI colourblindPanel = new PanelUI(new Point(0,0), getGameFrameDimension(), IconUI.colorblindColor);
	
	/**
	 * Main method, launch the game from here.
	 * @param args Arguments, should be empty
	 */
	public static void main(String[] args) {

		// Sets up the game frame
		configureGameFrame();
		
		// Resize the game frame
		resizeGameFrame(getGameFrameDimension());
		
		// Load initial screen, which is the login screen
		loginController.onEnter(Main.loginController);
		
		// Play background music
		soundController.playSound(SoundController.backgroundMusic);
		soundController.updateVolume(100); // TODO load from settings
	}
	
	/**
	 * Set up the game frame.
	 */
	private static void configureGameFrame() {
		// set up the game frame
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(new Dimension(1000, 1000)); // must be larger than the largest possible resolution
		gameFrame.setResizable(false);
		gameFrame.setLayout(null);
		gameFrame.setVisible(true);
		
		gameFrame.repaint();
		gameFrame.revalidate();
		
		// add colourblind overlay
		gameFrame.add(colourblindPanel);
	}
	
	/**
	 * Get the size of the game frame.
	 */
	public static Dimension getGameFrameDimension() {
		return settingsController.getDimension();
	}
	
	/**
	 * Show colourblind overlay (if enabled).
	 */
	public static void refreshColorblindOverlay() {
		colourblindPanel.setVisible(Main.settingsController.isColourblindActive());
		gameFrame.setComponentZOrder(colourblindPanel, 0);
		gameFrame.repaint();
		gameFrame.revalidate();
	}
	
	/**
	 * Resize game frame.
	 */
	public static void resizeGameFrame(Dimension size) {
		gameFrame.setSize(size);
		colourblindPanel.setSize(size);
		
		refreshColorblindOverlay();
	}
	
}
