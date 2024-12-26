package mvc;

import java.util.ArrayList;

/**
 * This model class stores the entries in the Student Progression Screen.<br>
 * At most 10 entries are stored at a time in the page. (lazy loading pattern)<br>
 * @version 1.0
 * @since Mar 14, 2024
 * @author Doyle Blacklock
 */
public class StudentProgressionData {
	/**
	 * Maximum number of entries to display in a page
	 */
	public static final int entriesPerPage = 10;
	/**
	 * List of all progression data of a user
	 */
	private ArrayList<ProgressionData> longProgressionList;
	/**
	 * Current page number
	 */
	private int page;

	/**
	 * Initialize the student progression data, based on the user.
	 */
	public StudentProgressionData(UserData userData) {
		longProgressionList = userData.getProgressionList();
		page = 1;
	}

	/**
	 * Access a progression data from the list. 
	 * @param index Index (0..9)
	 * @return ProgressionData at index, null if out-of-bound
	 */
	public ProgressionData getProgression(int index) {
		// Check index
		if (index < 0 || index > entriesPerPage - 1) {
			return null; // out-of-bound
		}
		
		try {
			return longProgressionList.get(index + (page - 1) * 10);
		} catch (IndexOutOfBoundsException e) {
			return null; // not found
		}
	}
	
	/**
	 * Access the page number.
	 * @return Page number
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Mutate the page number.
	 * @param page Page number (1..n)
	 * @return True if successful, false otherwise
	 */
	public Boolean setPage(int page) {
		// Check page number
		if (page < 1) {
			return false;
		}

		// Successful
		this.page = page;
		return true;
	}
}
