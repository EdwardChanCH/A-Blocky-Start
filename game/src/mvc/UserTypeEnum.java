package mvc;

/**
 * Enumerator of all possible types of users.
 * @version 1.0
 * @since March 14, 2024
 * @author Eunhak Kim
 * @author Chun Ho Chan
 */
public enum UserTypeEnum {
	STUDENT, TEACHER, DEVELOPER;
	
	/**
	 * Convert encoded string to an enumerator.
	 * @param data Encoded data string
	 * @return Enumerator of actions
	 */
	public static UserTypeEnum fromString(String data) {
		// Remove whitespace
		data = data.strip().toUpperCase();
		
		// Check data of action
		if (data.equals("TEACHER")) {
			return TEACHER;
		} else if (data.equals("DEVELOPER")) {
			return DEVELOPER;
		}
		
		// Invalid encoded string
		return STUDENT;
	}
}
