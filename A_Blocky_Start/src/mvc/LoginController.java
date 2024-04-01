package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * LoginController controls LoginData and LoginView
 * @version 0.2
 * @author Yuting Hou
 * @author Eunhak Kim
 */
public class LoginController implements Controller {

	// the view
	private static LoginView view = new LoginView();
	// the model
	private static LoginData data = new LoginData();
	
	static final String TEACHERPassword = "GradeOurPorject100%";
	static final String DEVELOPERPassword = "TooManyMergeConflicts!";
	/**
	 * Constructor
	 */
	public LoginController() {
		view.insertPanelToFrame(Main.gameFrame);

		populateActionListener();
	}

	/**
	 * Add action listener to UI elements
	 */
	private void populateActionListener() {
		
		//add action listener to the login button
		view.loginButtonAddAction(new ActionListener() {

			@Override
			//switch from login view to main menu view 
			public void actionPerformed(ActionEvent e) {
				
				//check if username is acceptable
				if (!(data.setUsernameInput(view.getUsername()))) {
					System.out.println("Bad username");
				}
				//check if password is acceptable
				if (!(data.setPasswordInput(view.getPassword()))) {
					System.out.println("Enter password");
				}
				//register if new user
				if (data.registerActiveUser()) {
					System.out.println("New user registered");
				}
				//logs in with the provided username and password
				if (!data.loginActiveUser()) {
					System.out.println("Login failed");
				}
				
				//if password == teacher password then enable teacher mode
				if (view.getPassword().compareTo(TEACHERPassword) == 0) {
					System.out.println("Logged in as teacher");
				}
				//if password matches developer password enable developer mode
				else if (view.getPassword().compareTo(DEVELOPERPassword) == 0) {
					System.out.println("Logged in as developer");	
				}
				
				//TODO: check condition, switch if save exist
				Main.loginController.onExit();	
				Main.mainMenuController.onEnter();
				//print login info
				System.out.println("Logged in with Username: " + view.getUsername() + ", Password: " + view.getPassword());
		}});
	}
	
	/**
	 * Access the game mode
	 * @return the mode of the game
	 */
	public UserTypeEnum getMode() {
		return data.getMode();
	}

	/**
	 * Show the screen
	 */
	public void onEnter() {
		view.setVisibility(true);
		Main.setColorblindOverlay();
	}

	/**
	 * Close the screen
	 */
	public void onExit() {
		view.setVisibility(false);
		System.out.println("LoginData.getMode:: " + data.getActiveUserData().getUserType().toString() + " usernameInput: " + data.getActiveUserData().getUsername() + ", usernamePassword: " + data.getActiveUserData().getPassword());

	}

}
