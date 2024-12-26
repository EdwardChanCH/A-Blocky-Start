package mvc;

import java.util.ArrayList;

/**
 * This model class stores a user's saved action chain in a stage. It loads/ saves data by communicating with the ProgressionData object. Each ActionBlock is stored as a separate object.
 * @version 1.0
 * @since March 11, 2024
 * @author Chun Ho Chan (Edward)
 */
public class ActionChainData {
	
	/**
	 * List of action blocks
	 */
	private ArrayList<ActionBlockData> actionBlockList;
	
	/**
	 * Index (pointer) of the "Start" action block
	 */
	private int indexStart;
	
	/**
	 * Index (pointer) of the "End" action block
	 */
	private int indexEnd;
	
	/**
	 * Index (pointer) of the current instruction
	 */
	private int indexCurrent;
	
	/**
	 * Index (pointer) of the next instruction
	 */
	private int indexNext;
	
	/**
	 * Construct a clean action chain, includes padding and "Start" and "End" blocks.<br>
	 * <br>
	 * Note: Index 0 is "Unknown" block as padding, since "Start" block is at index 1.
	 */
	public ActionChainData() {
		this.actionBlockList = new ArrayList<ActionBlockData>();
		actionBlockList.add(new ActionBlockData(ActionTypeEnum.UNKNOWN, new ArrayList<Integer>()));
		actionBlockList.add(new ActionBlockData(ActionTypeEnum.START, new ArrayList<Integer>()));
		actionBlockList.add(new ActionBlockData(ActionTypeEnum.END, new ArrayList<Integer>()));
		this.indexStart = 1;
		this.indexEnd = 2;
		this.indexCurrent = indexStart;
		this.indexNext = indexStart;
	}
	
	/**
	 * Decode the encoded data string and call the constructor.
	 * @see exportData() for details.
	 * @param data Encoded data string
	 * @return ActionChainData
	 */
	public static ActionChainData importData(String data) {
		// Call constructor
		ActionChainData actionChain = new ActionChainData();
		
		// Split the data
		String[] dataList = data.split(",");
		
		// Decode data and add to action chain
		for (int i = 0; i < dataList.length; i++) {
			actionChain.addBeforeEnd(ActionBlockData.importData(dataList[i]));
		}
		
		// Return action chain
		return actionChain;
	}
	
	/**
	 * Export this action chain as an encoded data string.<br>
	 * The string always begins with "Start" and ends with "End", with each action block separated with ",".<br>
	 * <br>
	 * Format: (without whitespace)<br>
	 * "actionBlock1, actionBlock2, ..."<br>
	 * <br>
	 * Examples: (without whitespace)<br>
	 * "Start, End"<br>
	 * "Start, Forward, End"<br>
	 * "Start, Loop_2_0_5, Forward, Goto_2, End"<br>
	 * @return Encoded data string
	 */
	public String exportData() {
		String result = actionBlockList.get(1).exportData(); // "Start" block
		
		// Append all blocks from the "Start" block to the "End" block
		for (int i = indexStart + 1; i <= indexEnd; ++i) {
			result = result + ',' + actionBlockList.get(i).exportData();
		}
		
		return result;
	}
	
	/**
	 * Access list of action blocks.
	 * @return list of action blocks
	 */
	public ArrayList<ActionBlockData> getActionBlockList() {
		return actionBlockList;
	}
	
	/**
	 * Access the action block at index. 
	 * @param index Index
	 * @return Action block at index, or an "Unknown" block if index is out-of-bound
	 */
	public ActionBlockData getActionBlock(int index) {
		if (isIndexOutOfBound(index)) {
			return new ActionBlockData(ActionTypeEnum.UNKNOWN, new ArrayList<Integer>());
		}
		
		return actionBlockList.get(index);
	}
	
	/**
	 * Access index of the "Start" action block.
	 * @return Index of the "Start" action block
	 */
	public int getIndexStart() {
		return indexStart;
	}
	
	/**
	 * Access index of the "End" action block.
	 * @return Index of the "End" action block
	 */
	public int getIndexEnd() {
		return indexEnd;
	}
	
	/**
	 * Access index of the current instruction.
	 * @return Index of the current instruction
	 */
	public int getIndexCurrent() {
		return indexCurrent;
	}
	
	/**
	 * Access index of the next instruction.
	 * @return Index of the next instruction
	 */
	public int getIndexNext() {
		return indexNext;
	}
	
	/**
	 * Insert an action block at the index. Any indices originally at or after that index shifts by +1. 
	 * @param actionBlock Action block
	 * @param index Index
	 * @return True if successful, false otherwise
	 */
	public boolean addAtIndex(ActionBlockData actionBlock, int index) {
		// Check action block type
		if (actionBlock.getType() == ActionTypeEnum.UNKNOWN) {
			return false; // cannot insert "Unknown" block
		} else if (actionBlock.getType() == ActionTypeEnum.START) {
			return false; // cannot insert "Start" block
		} else if (actionBlock.getType() == ActionTypeEnum.END) {
			return false; // cannot insert "End" block
		}
		
		// Check index range
		if (isIndexOutOfBound(index)) {
			return false; // out of bound
		} else if (index == indexStart) {
			return false; // cannot insert at "Start"
		}
		
		// Add action block and update index of the "End" block
		actionBlockList.add(index, actionBlock);
		++indexEnd;
		
		// Successful
		return true;
	}
	
	/**
	 * Insert an action block after the "Start" block. 
	 * @param actionBlock Action block
	 * @return True if successful, false otherwise
	 */
	public boolean addAfterStart(ActionBlockData actionBlock) {
		return addAtIndex(actionBlock, indexStart + 1);
	}
	
	/**
	 * Insert an action block before the "End" block.
	 * @param actionBlock Action block
	 * @return True if successful, false otherwise
	 */
	public boolean addBeforeEnd(ActionBlockData actionBlock) {
		return addAtIndex(actionBlock, indexEnd);
	}
	
	/**
	 * Swap two action blocks using indices. Cannot swap with "Start" or "End" blocks.
	 * @param index1 First index
	 * @param index2 Second index
	 * @return True if successful, false otherwise
	 */
	public boolean swap(int index1, int index2) {
		// Check indices range
		if (isIndexOutOfBound(index1) || isIndexOutOfBound(index2)) {
			return false; // index out of bound
		} else if ((index1 == indexStart) || (index2 == indexStart)) {
			return false; // cannot swap with "Start"
		} else if ((index1 == indexEnd) || (index2 == indexEnd)) {
			return false; // cannot swap with "End"
		}
		
		// Swap action blocks
		ActionBlockData block1 = actionBlockList.get(index1);
		ActionBlockData block2 = actionBlockList.get(index2);
		actionBlockList.set(index1, block2);
		actionBlockList.set(index2, block1);
		
		// Successful
		return true;
	}
	
	/**
	 * Remove an action block at the index. Any indices originally after that index shifts by -1.
	 * @param index Index
	 * @return True if successful, false otherwise
	 */
	public boolean remove(int index) {
		// Check indices range
		if (isIndexOutOfBound(index)) {
			return false; // index out of bound
		} else if (index == indexStart) {
			return false; // cannot remove "Start"
		} else if (index == indexEnd) {
			return false; // cannot remove "End"
		}
		
		// Remove action block
		actionBlockList.remove(index);
		--indexEnd;
		
		// Successful
		return true;
	}
	
	/**
	 * Reset instruction pointers and internal counters.
	 */
	public void executeReset() {
		// Reset instruction pointers to the start
		indexCurrent = indexStart;
		indexNext = indexStart;
		
		// Reset internal counters
		for (int i = indexStart; i <= indexEnd; ++i) {
			actionBlockList.get(i).resetCounter();
		}
	}
	
	/**
	 * Execute 1 step and update program counters and internal counters.
	 * @return True if successful, false if there is no next step, i.e. has finished or crashed
	 */
	public boolean executeStep() {
		// Check if action chain has finished
		if (isExecuteFinished()) {
			return false;
		}
		
		// Update current instruction pointer
		indexCurrent = indexNext;
		
		// Update next instruction pointer
		ActionBlockData currentBlock = getActionBlock(indexCurrent);
		if (currentBlock.isJump()) {
			indexNext = currentBlock.getJumpLine(); // "Goto" block or end of "Loop" block
			currentBlock.resetCounter(); // reset internal counter
		} else if (indexCurrent == indexEnd) {
			indexNext = indexEnd; // "End" block reached
		} else {
			// Decrement internal counter
			currentBlock.decCounter();
			
			// Set next action block in line
			++indexNext;
		}
		
		// Check if action chain has crashed
		if (isExecuteCrashed()) {
			return false;
		}
		
		// Successful
		return true;
	}
	
	/**
	 * Check if the action chain has been reset.
	 * @return
	 */
	public boolean isExecuteReady() {
		return ((indexCurrent == indexStart) && (indexNext == indexStart));
	}
	
	/**
	 * Check if the action chain has finished properly.
	 * @return True if has finished, false otherwise
	 */
	public boolean isExecuteFinished() {
		return ((indexCurrent == indexEnd) && (indexNext == indexEnd));
	}
	
	/**
	 * Check if the action chain has crashed unexpectedly.
	 * @return True if has crashed, false otherwise
	 */
	public boolean isExecuteCrashed() {
		return (isIndexOutOfBound(indexCurrent) || isIndexOutOfBound(indexNext));
	}
	
	/**
	 * Help to check if the index is out-of-bound, i.e. indexStart <= index <= indexEnd.
	 * @param index Index
	 * @return True if index is out-of-bound, false otherwise
	 */
	private boolean isIndexOutOfBound(int index) {
		if ((index < indexStart) || (index > indexEnd)) {
			return true; // index out of bound
		}
		
		return false; // index within bound
	}

}
