package mvc;
import java.util.ArrayList;

public class StudentProgressionData {
	private ArrayList<ProgressionData> arrayList;
	

	StudentProgressionData(String data) {
		
	}

	public String exportProgressionData() {
		return null;
	}

	public int getStageID() {
		return stageID;
	}

	public boolean getCompleted() {
		return completed;
	}

	public int getShortestSteps() {
		return shortestSteps;
	}

	public int getHighestScore() {
		return highestScore;
	}

	public int getTimeSpent() {
		return timeSpent;
	}

	public int getAttempts() {
		return attempts;
	}

	public ActionChainData getActionChain() {
		return actionChain;
	}

	public void setStageID(int id) {
		stageID = id;
	}

	public void setCompleted(boolean c) {
		completed = c;
	}

	public void setShortestSteps(int s) {
		shortestSteps = s;
	}

	public void setHighestScore(int h) {
		highestScore = h;
	}

	public void setTimeSpent(int t) {
		timeSpent = t;
	}

	public void setAttempts(int a) {
		attempts = a;
	}

	public void setActionChain(ActionChainData a) {
		actionChain = a;
	}
}