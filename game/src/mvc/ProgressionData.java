package mvc;

/**
 * This model class stores a user's progress in a stage. It loads/ saves data by communicating with the UserData object. The ActionChain is stored as a separate object.
 * @version 1.0
 * @since March 11, 2024
 * @author Chun Ho Chan (Edward), Doyle Blacklock
 */
public class ProgressionData {
	
	/**
	 * Separator between progression data and action chain data
	 */
	private static final char separator = '/';
	/**
	 * ID of this stage
	 */
	private int stageID;
	/**
	 * Completion status
	 */
	private boolean completed;
	/**
	 * Shortest number of steps
	 */
	private int shortestSteps;
	/**
	 * Highest score achieved
	 */
	private int highestScore;
	/**
	 * Time spent (in minutes)
	 */
	private int timeSpent;
	/**
	 * Number of attempts
	 */
	private int attempts;
	/**
	 * Stored action chain
	 */
	private ActionChainData actionChain;
	
	/**
	 * Construct a stage progression entry. Stage ID must be a unique, non-negative number.
	 * @param stageID ID of this stage
	 * @param completed Completion status
	 */
	public ProgressionData(int stageID) {
		this.stageID = stageID;
		this.completed = false;
		this.shortestSteps = 1000;
		this.highestScore = 0;
		this.timeSpent = 0;
		this.attempts = 0;
		this.actionChain = new ActionChainData();
	}
	
	/**
	 * Decode the encoded data string and call the constructor.
	 * @see exportData() for details.
	 * @param data Encoded data string
	 * @return ProgressionData, or an empty instance with stage ID = -1 if data is corrupted
	 */
	public static ProgressionData importData(String data) {
		// Find the position of separator character
		int separatorIndex = data.indexOf(separator);
		
		// Split the data into two parts
		String progressionPart = data.substring(0, separatorIndex);
		String actionChainPart = data.substring(separatorIndex + 1, data.length());
		
		// Remove leading commas before splitting data
		int i = 0;
		while ((i < progressionPart.length()) && (progressionPart.charAt(i) == ',')) {
			++i;
		}
		progressionPart.substring(i, progressionPart.length());
		String[] dataList = progressionPart.split(",");
		
		// Decode the data
		try {
			if (dataList.length < 6) {throw new Exception("Too few arguments.");}
			int stageID = Integer.parseInt(dataList[0]);
			boolean completed = Boolean.parseBoolean(dataList[1]);
			int shortestSteps = Integer.parseInt(dataList[2]);
			int highestScore = Integer.parseInt(dataList[3]);
			int timeSpent = Integer.parseInt(dataList[4]);
			int attempts = Integer.parseInt(dataList[5]);
			ActionChainData actionChain = ActionChainData.importData(actionChainPart);
			
			// Call constructor
			ProgressionData progression = new ProgressionData(stageID);
			
			// Add the data
			if (completed) {progression.markAsCompleted();}
			progression.updateShortestSteps(shortestSteps);
			progression.updateHighestScore(highestScore);
			progression.addTimeSpent(timeSpent);
			progression.addAttempts(attempts);
			progression.setActionChain(actionChain);
			
			// Return progression data
			return progression;
			
		} catch (Exception e) {
			// Return empty progression data
			Main.errorLogController.addWarning(e);
			return new ProgressionData(-1);
		}
	}
	
	/**
	 * Export this stage's progression data as an encoded data string.<br>
	 * The string always begins with "Start" and ends with "End", with each action block separated with ",".<br>
	 * <br>
	 * Format: (without whitespace)<br>
	 * "stageID, completed, shortestSteps, highestScore, timeSpent, attempts, /, actionChain"<br>
	 * @return Encoded data string
	 */
	public String exportData() {
		String result = "";
		
		// Append all progression data
		result += String.valueOf(stageID);
		result += ',';
		result += String.valueOf(completed).toUpperCase();
		result += ',';
		result += String.valueOf(shortestSteps);
		result += ',';
		result += String.valueOf(highestScore);
		result += ',';
		result += String.valueOf(timeSpent);
		result += ',';
		result += String.valueOf(attempts);
		result += ',';
		result += separator;
		result += ',';
		
		// Append action chain
		result += actionChain.exportData();
		
		return result;
	}
	
	/**
	 * Access the ID of this stage.
	 * @return stage ID
	 */
	public int getStageID() {
		return stageID;
	}

	/**
	 * Access the completion status of this stage.
	 * @return Completion status
	 */
	public boolean getCompleted() {
		return completed;
	}

	/**
	 * Access the shortest number of steps.
	 * @return Shortest number of steps
	 */
	public int getShortestSteps() {
		return shortestSteps;
	}

	/**
	 * Access the highest score.
	 * @return Highest score
	 */
	public int getHighestScore() {
		return highestScore;
	}

	/**
	 * Access the time spent (in minutes).
	 * @return Time spent
	 */
	public int getTimeSpent() {
		return timeSpent;
	}

	/**
	 * Access the number of attempts.
	 * @return Number of attempts
	 */
	public int getAttempts() {
		return attempts;
	}

	/**
	 * Access the stored action chain.
	 * @return Action chain
	 */
	public ActionChainData getActionChain() {
		return actionChain;
	}

	/**
	 * Mark this stage as completed.
	 */
	public void markAsCompleted() {
		this.completed = true;
	}

	/**
	 * Update the shortest number of steps.
	 * @param steps Number of steps
	 * @return True if successful, false if steps are greater than/ equal to shortest steps
	 */
	public boolean updateShortestSteps(int steps) {
		if (steps >= shortestSteps) {
			return false;
		}
		
		// Successful
		shortestSteps = steps;
		return true;
	}

	/**
	 * Update the highest score achieved.
	 * @param score Score
	 * @return True if successful, false if score smaller than/ equal to highest score
	 */
	public boolean updateHighestScore(int score) {
		if (score <= highestScore) {
			return false;
		}
		
		// Successful
		highestScore = score;
		return true;
	}

	/**
	 * Add to the time spent.
	 * @param time Additional time spent
	 */
	public void addTimeSpent(int timeSpent) {
		this.timeSpent += timeSpent;
	}

	/**
	 * Add to the number of attempts.
	 * @param attempts Number of attempts
	 */
	public void addAttempts(int attempts) {
		this.attempts += attempts;
	}
	
	/**
	 * Mutate the action chain of this stage.
	 */
	public void setActionChain(ActionChainData actionChain) {
		this.actionChain = actionChain;
	}

}
