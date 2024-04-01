package mvc;

/**
 * Enumerator of all possible types of maze item.
 * @version March 17, 2024
 * @since March 17, 2024
 * @author Chun Ho Chan (Edward)
 */
public enum MazeTypeEnum {
	/**
	 * Enumerator of maze items
	 */
	WALL, PATH, SPAWN, EXIT, KEY, TRAP;
	
	/**
	 * Convert encoded string to an enumerator.
	 * @param data Encoded data string
	 * @return Enumerator of maze item type
	 */
	public static MazeTypeEnum fromString(String data) {
		// Remove whitespace
		data = data.strip().toUpperCase();
		
		// Remove leading invisible characters, such as the Byte-Order-Mark character
		data = String.valueOf(data.charAt(data.length() - 1));
		
		// Check data of action
		if (data.equals("W")) {
			return WALL;
		} else if (data.equals("_")) {
			return PATH;
		} else if (data.equals("S")) {
			return SPAWN;
		} else if (data.equals("E")) {
			return EXIT;
		} else if (data.equals("K")) {
			return KEY;
		} else if (data.equals("T")) {
			return TRAP;
		}
		
		// Invalid encoded string
		return PATH;
	}
	
	/**
	 * Convert an enumerator to an encoded string.
	 * @param data Maze item type
	 * @return Encoded data string
	 */
	public static String toString(MazeTypeEnum type) {
		// Check data of action
		switch (type) {
		case WALL:
			return "W";
		case PATH:
			return "_";
		case SPAWN:
			return "S";
		case EXIT:
			return "E";
		case KEY:
			return "K";
		case TRAP:
			return "T";
		default:
			return "_";
		}
	}
}
