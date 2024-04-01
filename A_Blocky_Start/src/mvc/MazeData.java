package mvc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This model class represents a maze layout. Each cell is represented by a MazeItem. The maze and inventory are updated in real time by the GameplayScreen. 
 * @version 1.0
 * @since March 11, 2024
 * @author Chun Ho Chan (Edward)
 */
public class MazeData {

	/**
	 * Filename prefix of user data files/ Directory name
	 */
	public static final String filenamePrefix = "./mazedata/";
	/**
	 * Filename infix of user data files
	 */
	public static final String filenameInfix = "stage";
	/**
	 * Filename suffix of user data files
	 */
	public static final String filenameSuffix = "_mazedata.csv";
	/**
	 * Maximum number of rows of tiles
	 */
	public static final int MAX_ROWS = 9;
	/**
	 * Maximum number of columns of tiles
	 */
	public static final int MAX_COLUMNS = 9;
	/**
	 * Points for collecting a key
	 */
	public static final int POINTS_FOR_KEY = 20;
	/**
	 * Points for reaching an exit
	 */
	public static final int POINTS_FOR_EXIT = 40;
	/**
	 * Stage ID
	 */
	int stageID;
	/**
	 * 2D grid of maze items
	 */
	MazeTypeEnum[][] mazeItemGrid;
	/**
	 * Row number of robot's current position
	 */
	int robotRow;
	/**
	 * Column number of robot's current position
	 */
	int robotColumn;
	/**
	 * Row offset of robot's next forward position<br>
	 * -1 = up, 1 = down
	 */
	int robotRowOffset;
	/**
	 * Column offset of robot's next forward position<br>
	 * -1 = left, 1 = right
	 */
	int robotColumnOffset;
	/**
	 * Current player score
	 */
	int score;
	/**
	 * Objectives text
	 */
	String objectives;
	/**
	 * Hints text
	 */
	String hints;
	
	/**
	 * Construct a maze data object.
	 * @param stageID Stage ID
	 * @param mazeItemGrid 2D grid of maze items
	 * @param robotRow Row number of robot's current position
	 * @param robotColumn Column number of robot's current position
	 */
	public MazeData(int stageID, MazeTypeEnum[][] mazeItemGrid, int robotRow, int robotColumn) {
		this.mazeItemGrid = mazeItemGrid;
		this.robotRow = robotRow;
		this.robotColumn = robotColumn;
		this.robotRowOffset = 0;
		this.robotColumnOffset = 1;
		this.score = 0;
	}
	
	/**
	 * Decode the encoded data stored in a stage data file.<br>
	 * @see exportData() for details. 
	 * @param filename Filename of the stage data (prefix + infix + username + suffix)<br>
	 * @return MazeData, or null if the file does not exist.
	 * Format: (without spaces)<br>
	 * "row1"<br>
	 * "row2"<br>
	 * "..."<br>
	 * "Objectives"<br>
	 * "Hints"<br>
	 */
	public static MazeData importData(String filename) {
		
		try {
			FileReader fileIn = new FileReader(filename);
			Scanner scnr = new Scanner(fileIn);
			
			// Decode the maze
			int spawnRow = 0;
			int spawnColumn = 0;
			MazeTypeEnum[][] maze = new MazeTypeEnum[MAX_ROWS][MAX_COLUMNS];
			for (int i = 0; i < MAX_ROWS; i++) {
				String dataStr = scnr.nextLine();
				String[] row = dataStr.split(",");
				
				// Removes invisible characters, such as 'BOM'
				for (int j = 0; j < MAX_COLUMNS; j++) {
					maze[i][j] = MazeTypeEnum.fromString(row[j]);
					
					if (maze[i][j] == MazeTypeEnum.SPAWN) {
						spawnRow = i;
						spawnColumn = j;
					}
				}
			}
			
			// Read objectives
			String objectives = scnr.nextLine();
			objectives = objectives.substring(1, objectives.indexOf('"', 1)); // discard the quotation marks
			
			// Read hints
			String hints = scnr.nextLine();
			hints = hints.substring(1, hints.indexOf('"', 1)); // discard the quotation marks
			
			// Extract stage ID from filename
			String stageID = filename.substring(filenamePrefix.length() + filenameInfix.length(), filename.length() - filenameSuffix.length());
			
			// Call constructor
			MazeData mazeData = new MazeData(Integer.parseInt(stageID), maze, spawnRow, spawnColumn);
			
			// Add the rest of the attributes
			mazeData.setObjectives(objectives);
			mazeData.setHints(hints);
			
			scnr.close();
			
 			return mazeData;
			
		} catch (FileNotFoundException e) {
			Main.errorLogController.addWarning(e);
			return null;
		}
	}
	
	
	/*
	// Maze data cannot be exported to file since it is read-only.
	public void exportData() {
	}
	*/
	
	
	public int getStageID() {
		return stageID;
	}
	
	public MazeTypeEnum getMazeItem(int row, int column) {
		if (!isIndexOutOfBound(row, column)) {
			return mazeItemGrid[row][column];
		}
		
		return MazeTypeEnum.WALL; // out-of-bound areas are considered walls
	}
	
	public int getRobotRow() {
		return robotRow;
	}
	
	public int getRobotColumn() {
		return robotColumn;
	}
	
	public int getRobotRowOffset() {
		return robotRowOffset;
	}
	
	public int getRobotColumnOffset() {
		return robotColumnOffset;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getObjectives() {
		return objectives;
	}
	
	public String getHints() {
		return hints;
	}
	
	/**
	 * Access the next tile that the robot is facing.
	 * @param forward True if forward, false if back
	 * @return MazeTypeEnum
	 */
	private MazeTypeEnum getFacingTile(boolean forward) {
		int r = (forward ? robotRowOffset : robotRowOffset * -1);
		int c = (forward ? robotColumnOffset : robotColumnOffset * -1);
		
		return getMazeItem(robotRow + r, robotColumn + c);
	}
	
	public void setObjectives(String text) {
		objectives = text;
	}
	
	public void setHints(String text) {
		hints = text;
	}
	
	/**
	 * Move the robot forward/ back.
	 * @param forward True if forward, false if back
	 * @return True if moved, false otherwise (blocked by wall or dead)
	 */
	public boolean moveRobot(boolean forward) {
		// Check if dead or exited
		if (isRobotDead() || isRobotExited()) {
			return false;
		}
		
		// Check if walking into special tiles
		MazeTypeEnum next = getFacingTile(forward);
		if (next == MazeTypeEnum.WALL) {
			return false; // cannot walk into walls
		} else if (next == MazeTypeEnum.KEY) {
			score += POINTS_FOR_KEY;
		} else if (next == MazeTypeEnum.EXIT) {
			score += POINTS_FOR_EXIT;
		} else if (next == MazeTypeEnum.TRAP) {
			score += 0; // no points deducted
		}
		
		// Move the robot
		int r = (forward ? robotRowOffset : robotRowOffset * -1);
		int c = (forward ? robotColumnOffset : robotColumnOffset * -1);
		robotRow += r;
		robotColumn += c;
		
		// Replace collectible item with an path tile
		if ((next == MazeTypeEnum.KEY) || (next == MazeTypeEnum.TRAP)) {
			mazeItemGrid[robotRow][robotColumn] = MazeTypeEnum.PATH;
		}
		
		// Kill robot if hit a trap
		if (next == MazeTypeEnum.TRAP) {
			setRobotDead();
		}
		
		// Successful
		return true;
	}
	
	/**
	 * Rotate the robot counter-clockwise/ clockwise.
	 * @param counterClockwise True if counter-clockwise, false if clockwise
	 * @return True if rotated, false otherwise (dead)
	 */
	public boolean rotateRobot(boolean counterClockwise) {
		// Check if dead or exited
		if (isRobotDead() || isRobotExited()) {
			return false;
		}
		
		if (counterClockwise) {
			if (robotRowOffset != 0) {
				// Up --> Left, Down --> Right
				robotColumnOffset = robotRowOffset;
				robotRowOffset = 0;
			} else if (robotColumnOffset != 0) {
				// Left --> Down, Right --> Up
				robotRowOffset = robotColumnOffset * -1;
				robotColumnOffset = 0;
			}
		} else {
			if (robotRowOffset != 0) {
				// Up --> Right, Down --> Left
				robotColumnOffset = robotRowOffset * -1;
				robotRowOffset = 0;
			} else if (robotColumnOffset != 0) {
				// Left --> Up, Right --> Down
				robotRowOffset = robotColumnOffset;
				robotColumnOffset = 0;
			}
		}
		
		// Successful
		return true;
	}
	
	/**
	 * Set the robot as dead.
	 */
	private void setRobotDead() {
		robotRowOffset = 0;
		robotColumnOffset = 0;
	}
	
	/**
	 * Check if the robot is dead.
	 * @return True if dead, false otherwise
	 */
	private boolean isRobotDead() {
		return ((robotRowOffset == 0) && (robotColumnOffset == 0));
	}
	
	/**
	 * Check if the robot has reached the exit.
	 * @return True if exited, false otherwise
	 */
	private boolean isRobotExited() {
		return (mazeItemGrid[robotRow][robotColumn] == MazeTypeEnum.EXIT);
	}
	
	/**
	 * Convert username to the filename of this maze's data file. 
	 * @param stageID Stage ID
	 * @return Filename of that stage data (prefix + infix + stageID + suffix)
	 */
	public static String toFilename(int stageID) {
		return filenamePrefix + filenameInfix + stageID + filenameSuffix;
	}
	
	/**
	 * Help to check if the indices are out-of-bound, i.e. 0 <= row < MAX_ROWS and 0 <= column < MAX_COLUMNS.
	 * @param row Row number
	 * @param column Column number
	 * @return True if index is out-of-bound, false otherwise
	 */
	public static boolean isIndexOutOfBound(int row, int column) {
		if ((row < 0) || (row >= MAX_ROWS) || (column < 0) || (column >= MAX_COLUMNS)) {
			return true; // index out of bound
		}
		
		return false; // index within bound
	}
	
}
