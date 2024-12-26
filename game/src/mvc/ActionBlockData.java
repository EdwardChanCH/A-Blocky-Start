package mvc;

import java.util.ArrayList;

/**
 * This model class represents an action block. It loads/ saves data by communicating with the ActionChain object.
 * @version 1.0
 * @since March 11, 2024
 * @author Chun Ho Chan (Edward)
 */
public class ActionBlockData {
	/**
	 * Type of action
	 */
	private ActionTypeEnum type;
	/**
	 * List of arguments
	 */
	private ArrayList<Integer> args;
	/**
	 * Internal counter (in a loop)
	 */
	private int counter;
	
	/**
	 * Construct an action block.
	 * @param type Type of action
	 * @param args List of arguments
	 */
	public ActionBlockData(ActionTypeEnum type, ArrayList<Integer> args) {
		this.type = type;
		this.args = args;
		this.counter = -1; // none value
		
		if (type == ActionTypeEnum.LOOP) {
			// loop (end - start) times
			this.counter = args.get(0) - args.get(1);
		}
	}
	
	/**
	 * Decode the encoded data string and call the constructor.
	 * @see exportData() for details.
	 * @param data Encoded data string
	 * @return ActionBlockData
	 */
	public static ActionBlockData importData(String data) {
		// Split the data
		String[] dataList = data.split("_");
		
		// Decode type
		ActionTypeEnum type = ActionTypeEnum.fromString(dataList[0]);
		
		// Check type
		if (type == ActionTypeEnum.UNKNOWN) {
			return new ActionBlockData(ActionTypeEnum.UNKNOWN, new ArrayList<Integer>());
		}
		
		// Decode list of arguments
		ArrayList<Integer> args = new ArrayList<Integer>();
		for (int i = 1; i < dataList.length; i++) {
			try {
				args.add(Integer.parseInt(dataList[i]));
			} catch (NumberFormatException e) {
				// do nothing
			}
		}
		
		// Check number of arguments
		if ((type == ActionTypeEnum.GOTO) && (args.size() != 1)) {
			return new ActionBlockData(ActionTypeEnum.UNKNOWN, new ArrayList<Integer>());
		} else if ((type == ActionTypeEnum.LOOP) && (args.size() != 3)) {
			return new ActionBlockData(ActionTypeEnum.UNKNOWN, new ArrayList<Integer>());
		}
		
		// Call constructor
		return new ActionBlockData(type, args);
	}
	
	/**
	 * Export this action block as an encoded data string.<br>
	 * The string always begins with its type, followed by its arguments separated with "_".<br>
	 * <br>
	 * Examples:<br>
	 * "Left"<br>
	 * "Goto_X" : X is the line number for jump.<br>
	 * "Loop_X_Y_Z" : Loop (X - Y) times, Z is the line number for jump.<br>
	 * @return Encoded data string
	 */
	public String exportData() {
		String result = type.toString(); // block type
		
		// Append all block arguments
		for (int i = 0; i < args.size(); i++) {
			result = result + '_' + args.get(i).toString(); 
		}
		
		return result;
	}
	
	/**
	 * Access type of action.
	 * @return Type of action.
	 */
	public ActionTypeEnum getType() {
		return type;
	}
	
	/**
	 * Access list of arguments.
	 * @return List of arguments
	 */
	public ArrayList<Integer> getArgs() {
		return args;
	}
	
	/**
	 * Access end point of loop. Return -1 if none.
	 * @return End point
	 */
	public int getEndPoint() {
		if (type == ActionTypeEnum.LOOP) {
			return args.get(0);
		}
		
		return -1; // none value
	}
	
	/**
	 * Access start point of loop. Return -1 if none.
	 * @return Start point
	 */
	public int getStartPoint() {
		if (type == ActionTypeEnum.LOOP) {
			return args.get(1);
		}
		
		return -1; // none value
	}
	
	/**
	 * Access line number for jump. Return -1 if none.
	 * @return Line number for jump
	 */
	public int getJumpLine() {
		if (type == ActionTypeEnum.GOTO) {
			return args.get(0);
		} else if (type == ActionTypeEnum.LOOP) {
			return args.get(2);
		}
		
		return -1; // none value
	}
	
	/**
	 * Access internal counter. Return -1 if none.
	 * @return Internal counter
	 */
	public int getCounter() {
		if (type == ActionTypeEnum.LOOP) {
			return counter;
		}
		
		return -1; // none value
	}
	
	/**
	 * Mutate end point of loop.
	 * @param endPoint End point
	 * @return True if successful, false otherwise.
	 */
	public boolean setEndPoint(int endPoint) {
		if (type == ActionTypeEnum.LOOP) {
			args.set(0, endPoint);
			resetCounter();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Mutate start point of loop.
	 * @param startPoint Start point
	 * @return True if successful, false otherwise.
	 */
	public boolean setStartPoint(int startPoint) {
		if (type == ActionTypeEnum.LOOP) {
			args.set(1, startPoint);
			resetCounter();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Mutate line number for jump.
	 * @param jumpLine Jump Line
	 * @return True if successful, false otherwise.
	 */
	public boolean setJumpLine(int jumpLine) {
		if (type == ActionTypeEnum.GOTO) {
			args.set(0, jumpLine);
			return true;
		} else if (type == ActionTypeEnum.LOOP) {
			args.set(2, jumpLine);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Decrement internal counter by 1.
	 */
	public void decCounter() {
		if (type == ActionTypeEnum.LOOP) {
			--counter;
		}
	}
	
	/**
	 * Reset internal counter.
	 */
	public void resetCounter() {
		if (type == ActionTypeEnum.LOOP) {
			// loop (end - start) times
			counter = args.get(0) - args.get(1);
		}
	}
	
	/**
	 * Check if this instruction is a jump, i.e. "Goto" or "Loop" block.
	 * @return True if the action block needs to jump, false otherwise
	 */
	public boolean isJump() {
		if (type == ActionTypeEnum.GOTO) {
			return true;
		} else if ((type == ActionTypeEnum.LOOP) && (counter <= 0)) {
			return true; // "Loop" is finished
		}
		
		return false;
	}

}
