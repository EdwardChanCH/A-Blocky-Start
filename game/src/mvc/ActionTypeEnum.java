package mvc;

/**
 * Enumerator of all possible types of action.
 * @version 1.0
 * @since March 11, 2024
 * @author Chun Ho Chan (Edward)
 */
public enum ActionTypeEnum {
	/**
	 * Enumerator of actions
	 */
	UNKNOWN, START, END, FORWARD, BACK, LEFT, RIGHT, GOTO, LOOP;
	
	/**
	 * Convert encoded string to an enumerator.
	 * @param data Encoded data string
	 * @return Enumerator of actions
	 */
	public static ActionTypeEnum fromString(String data) {
		// Remove whitespace
		data = data.strip().toUpperCase();
		
		// Check data of action
		if (data.equals("START")) {
			return START;
		} else if (data.equals("END")) {
			return END;
		} else if (data.equals("FORWARD")) {
			return FORWARD;
		} else if (data.equals("BACK")) {
			return BACK;
		} else if (data.equals("LEFT")) {
			return LEFT;
		} else if (data.equals("RIGHT")) {
			return RIGHT;
		} else if (data.indexOf("GOTO") != -1) {
			return GOTO;
		} else if (data.indexOf("LOOP") != -1) {
			return LOOP;
		}
		
		// Invalid encoded string
		return UNKNOWN;
	}
}
