package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * LoginController controls LoginData and LoginView
 * @version 0.2
 * @author Yuting Hou
 * @author Eunhak Kim
 */
public class LoginController implements Controller {

	private static Controller previous;
	private static LoginView view = new LoginView();
	private static LoginData data = new LoginData();
	
	/**
	 * Constructor.
	 */
	public LoginController() {
		view.insertPanelToFrame(Main.gameFrame);

		populateActionListener();
	}
	
	@Override
	public void onEnter(Controller previous) {
		LoginController.previous = previous;
		view.setVisibility(true);
		Main.refreshColorblindOverlay();
	}

	@Override
	public void onExit() {
		view.setVisibility(false);
		// TODO
	}

	/**
	 * Help to insert action listeners to UI elements.
	 */
	private void populateActionListener() {

		view.loginButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// check if username is acceptable
				if (!data.setUsernameInput(view.getUsername())) {
					Main.errorLogController.addPopup(">> Prompt <<", "Invalid username. Username can only contain English characters and numbers. Please try again!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// check if password is acceptable
				if (!data.setPasswordInput(view.getPassword())) {
					Main.errorLogController.addPopup(">> Prompt <<", "Invalid password. Password must be at least 1 character long. Please try again!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// check if existing user
				if (!data.isExistingUser()) {
					Main.errorLogController.addPopup(">> Prompt <<", "This account does not exist. Please register instead!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// log in with the provided username and password
				if (!data.loginActiveUser()) {
					Main.errorLogController.addPopup(">> Prompt <<", "Login failed. Please check the password!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// print login info
				Main.errorLogController.addPopup(">> Prompt <<", "You are logged in as: " + data.getActiveUserData().getUsername(), JOptionPane.INFORMATION_MESSAGE, null);
				
				// go to main menu
				Main.loginController.onExit();
				Main.mainMenuController.onEnter(Main.loginController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
			
		});
		
		view.registerButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// check if username is acceptable
				if (!data.setUsernameInput(view.getUsername())) {
					Main.errorLogController.addPopup(">> Prompt <<", "Invalid username. Username can only contain English characters and numbers. Please try again!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// check if password is acceptable
				if (!data.setPasswordInput(view.getPassword())) {
					Main.errorLogController.addPopup(">> Prompt <<", "Invalid password. Password must be at least 1 character long. Please try again!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// check if existing user
				if (data.isExistingUser()) {
					Main.errorLogController.addPopup(">> Prompt <<", "This account already exists. Please login instead!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// register in with the provided username and password
				if (!data.registerActiveUser()) {
					Main.errorLogController.addPopup(">> Prompt <<", "Registration failed. Only students can register new accounts!", JOptionPane.INFORMATION_MESSAGE, null);
					return;
				}
				
				// print login info
				Main.errorLogController.addPopup(">> Prompt <<", "You are registered as: " + data.getActiveUserData().getUsername(), JOptionPane.INFORMATION_MESSAGE, null);
				
				// go to main menu
				Main.loginController.onExit();
				Main.mainMenuController.onEnter(Main.loginController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
			
		});
	}
	
	/**
	 * Access the game mode.
	 * @return The mode of the game
	 */
	public UserTypeEnum getMode() {
		return data.getMode();
	}
	
	/**
	 * Access the active user data.
	 * @return Active user data
	 */
	public UserData getActiveUserData() {
		return data.getActiveUserData();
	}
	
	/**
	 * Reset the active user data.
	 */
	public void resetActiveUserData() {
		data.getActiveUserData().resetProgressionData();
		data.getActiveUserData().exportData();
	}

}
