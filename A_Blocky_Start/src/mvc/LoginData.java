package mvc;

import java.io.File;

import javax.swing.JOptionPane;

/**
 * This model class stores, updates, and checks the current login data in the login screen.
 * @version 1.0
 * @since Mar 17, 2024
 * @author Doyle Blacklock
 * @author Chun Ho Chan (Edward)
 */
public class LoginData {
	private static final String TEACHER_USERNAME = "TEACHER";
	// TEACHERPassword = "GradeOurPorject100%"
	private static final String DEVELOPER_USERNAME = "DEVELOPER";
	// DEVELOPERPassword = "TooManyMergeConflicts!"
	private String usernameInput;
	private String passwordInput;
	private UserData activeUserData;

	/**
	 * Initialize the login data. 
	 */
	public LoginData() {
		this.usernameInput = "";
		this.passwordInput = "";
		this.activeUserData = null;
	}

	/**
	 * Access the active user. 
	 * @return active UserData, or null if not registered/ logged in
	 */
	public UserData getActiveUserData() {
		return activeUserData;
	}
	
	/**
	 * Access the mode of the game, i.e. STUDENT (default), TEACHER, DEVELOPER. 
	 * Only checks username input, does not check password input. 
	 * @return Active user type
	 */
	public UserTypeEnum getMode() {
		
		if (usernameInput.equalsIgnoreCase(TEACHER_USERNAME)) {
			return UserTypeEnum.TEACHER;
		} else if (usernameInput.equalsIgnoreCase(DEVELOPER_USERNAME)) {
			return UserTypeEnum.DEVELOPER;
		}
		return UserTypeEnum.STUDENT; // default mode
	}
	
	/**
	 * Mutate the username input. 
	 * @param username Username
	 * @return True if successful, false if not accepted
	 */
	public boolean setUsernameInput(String username) {

		if (!isAcceptableUsername(username)) {
			return false;
		}

		// Successful
		usernameInput = username;
		return true;
	}

	/**
	 * Mutate the password input. 
	 * @param password Password
	 * @return True if successful, false if not accepted
	 */
	public boolean setPasswordInput(String password) {

		if (!isAcceptablePassword(password)) {
			return false;
		}

		// Successful
		passwordInput = password;
		return true;
	}
	
	/**
	 * Register a new user account and load a user data object. 
	 * @return True if successful, false if account already exists
	 */
	public boolean registerActiveUser() {
		// Check if file exists
		if (isExistingUser()) {
			return false; // cannot register repeated username
		}
		
		// Check user type
		if (getMode() == UserTypeEnum.TEACHER) {
			return false; // cannot register a new TEACHER account
		} else if (getMode() == UserTypeEnum.DEVELOPER) {
			return false; // cannot register a new DEVELOPER account
		}
		
		// Create a new user data file
		activeUserData = new UserData(UserTypeEnum.STUDENT, usernameInput, passwordInput);
		activeUserData.resetProgressionData();
		activeUserData.exportData();
		
		// Successful
		return true;
	}
	
	/**
	 * Login to an existing user account and load a user data object. 
	 * @return True if successful, false if account does not exist or incorrect password
	 */
	public boolean loginActiveUser() {
		// Check if file exists
		if (!isExistingUser()) {
			return false; // cannot log into non-existing account
		}
		
		// Try opening the user data file
		UserData userData = UserData.importData(UserData.toFilename(usernameInput));
		
		// Verify password
		if (!userData.getPassword().equals(passwordInput)) {
			return false; // incorrect password
		}
		
		// Load user data
		activeUserData = userData;
		
		// Successful
		return true;
	}
	
	/**
	 * Help to check if the username is acceptable. 
	 * @return True if only contains letters and digits
	 */
	private boolean isAcceptableUsername(String username) {
		if (username.matches("[a-zA-Z0-9]+")) {
			return true;
		}
		System.out.println("Unacceptable username: " + username);
		return false;
	}
	
	/**
	 * Help to check if the password is acceptable. 
	 * @return True if the password is not empty, false otherwise
	 */
	private boolean isAcceptablePassword(String password) {
		if (password.length() > 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Help to check if the user already exists. 
	 * @param username
	 * @return
	 */
	public boolean isExistingUser() {
		try {
			// Try opening the user data file
			String filename = UserData.toFilename(usernameInput);
			File file = new File(filename);
			if (file.exists() && file.isFile()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
}
