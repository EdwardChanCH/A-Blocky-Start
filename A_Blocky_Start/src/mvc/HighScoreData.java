package mvc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This model class stores the top 5 high scores. 
 * It loads/ saves data using the high-score data file. 
 * @version 1.0
 * @since Mar 12, 2024
 * @author Simon McCabe
 */
public class HighScoreData {
	/**
	 * Filename of the settings data file
	 */
	private static final String filename = "game_highscoredata.csv";
	/**
	 * Maximum number of entries
	 */
	private static final int entriesMax = 5;
	/**
	 * List of usernames
	 */
	private String[] usernameList;
	/**
	 * List of high scores
	 */
	private int[] highScoreList;

	/**
	 * Initialize the high score data. 
	 */
	public HighScoreData() {
		usernameList = new String[entriesMax];
		highScoreList = new int[entriesMax];
		
		for (int i = 0; i < entriesMax; i++) {
			usernameList[i] = "(empty)";
			highScoreList[i] = 0;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static HighScoreData importData() {
		// Call constructor
		HighScoreData highScoreData = new HighScoreData();
		
		// Read the CSV file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			// Read each line
			while ((line = reader.readLine()) != null) {
				// Decode each line
				String[] parts = line.split(",");
				if (parts.length >= 2) {
					highScoreData.addNameScore(parts[0], Integer.parseInt(parts[1]));
				}
			}
			
			// Close the reader
			reader.close();
			
		} catch (Exception e) {
			Main.errorLogController.addError(e);
		}
		
		return highScoreData;
	}

	/**
	 * Export the high score data to the high score data data file.
	 * Each line contains a username and a high score. <br><br>
	 * Format: (without spaces)<br>
	 * "username1, highScore1"<br>
	 * "username2, highScore2"<br>
	 * "..."<br>
	 */
	public void exportHighScore() {
		// Write to the CSVfile
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			// Write each line
			for (int i = 0; i < entriesMax; i++) {
				writer.write(usernameList[i] + ',' + highScoreList[i] + '\n');
			}
			
			// Close the writer
			writer.close();
			
		} catch (Exception e) {
			Main.errorLogController.addError(e);
		}
	}

	/**
	 * Access the username.
	 * @param index Index
	 * @return Username
	 */
	public String getUsername(int index) {
		if (!isIndexOutOfBound(index)) {
			return usernameList[index];
		}

		return "(empty)";
	}

	/**
	 * Access the high score. 
	 * @param index Index
	 * @return High score
	 */
	public int getHighScore(int index) {
		if (!isIndexOutOfBound(index)) {
			return highScoreList[index];
		}
		
		return 0;
	}

	/**
	 * Add a new entry to the high score table. 
	 * @param username Username
	 * @param highScore High score
	 * @return True if successful, false if the new entry does not have a high enough score
	 */
	public boolean addNameScore(String username, int highScore) {
		//System.out.println(username + highScore);
		// Check new entry score
		if (highScore <= highScoreList[entriesMax - 1]) {
			return false; // score is lower than the last high score entry
		}
		
		// Insert new entry
		usernameList[entriesMax - 1] = username;
		highScoreList[entriesMax - 1] = highScore;
		
		// Swap it into the right place
		for (int i = entriesMax - 1; i > 0; i--) {
			if (highScoreList[i] > highScoreList[i - 1]) {
				swapEntries(i, i - 1); // score is higher
			} else if ((highScoreList[i] == highScoreList[i - 1]) && (usernameList[i].compareTo(usernameList[i - 1]) < 0)) {
				swapEntries(i, i - 1); // score is the same but username is smaller
			} else {
				break; // finished sorting
			}
		}
		
		// Success
		return true;
	}
	
	/**
	 * Help to swap two entries.
	 * @param index1 Index 1
	 * @param index2 Index 2
	 * @return True if successful, false otherwise
	 */
	private boolean swapEntries(int index1, int index2) {
		// Check indices
		if (isIndexOutOfBound(index1) || isIndexOutOfBound(index2)) {
			return false;
		}
		
		// Swap the two entries
		String tempUsername = usernameList[index1];
		int tempHighScore = highScoreList[index1];
		usernameList[index1] = usernameList[index2];
		highScoreList[index1] = highScoreList[index2];
		usernameList[index2] = tempUsername;
		highScoreList[index2] = tempHighScore;
		
		// Success
		return true;
	}
	
	/**
	 * Help to check if the index is out-of-bound, i.e. 0 <= index < entriesMax.
	 * @param index Index
	 * @return True if index is out-of-bound, false otherwise
	 */
	public boolean isIndexOutOfBound(int index) {
		return ((index < 0) || (index >= entriesMax));
	}

}
