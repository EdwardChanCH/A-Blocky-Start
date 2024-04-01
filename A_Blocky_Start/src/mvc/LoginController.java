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

	private static final String TEACHERPassword = "GradeOurPorject100%";
	private static final String DEVELOPERPassword = "TooManyMergeConflicts!";
	
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
		System.out.println("LoginData.getMode:: " + data.getActiveUserData().getUserType().toString() + " usernameInput: " + data.getActiveUserData().getUsername() + ", usernamePassword: " + data.getActiveUserData().getPassword());
	}

	/**
	 * Help to insert action listeners to UI elements.
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
				Main.mainMenuController.onEnter(Main.loginController);
				//print login info
				System.out.println("Logged in with Username: " + view.getUsername() + ", Password: " + view.getPassword());
				
				Main.soundController.playSound(SoundController.buttonClick);
		}});
	}
	
	/**
	 * Access the game mode.
	 * @return The mode of the game
	 */
	public UserTypeEnum getMode() {
		return data.getMode();
	}

}
