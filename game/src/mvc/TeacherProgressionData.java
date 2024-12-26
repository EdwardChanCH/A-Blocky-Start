package mvc;

import java.io.File;
import java.util.ArrayList;

/**
 * This model class stores the entries in the Teacher Progression Screen. 
 * At most 10 entries are stored at a time in the page. (lazy loading pattern)
 * @version 1.0
 * @since Mar 14, 2024
 * @author Doyle Blacklock
 */
public class TeacherProgressionData {
	/**
	 * Maximum number of entries to display in a page
	 */
	public static final int entriesPerPage = 10;
	/**
	 * List of all filenames in the user data directory
	 */
	private ArrayList<String> longFilenameList;
	/**
	 * List currently loaded user data files
	 */
	private ArrayList<String> shortFilenameList;
	/**
	 * Current page number
	 */
	private int page;

	/**
	 * Initialize the teacher progression data. 
	 */
	public TeacherProgressionData() {
		longFilenameList = new ArrayList<String>();
		shortFilenameList = new ArrayList<String>();
		page = 1;
		
		// Open the directory
		String path = UserData.filenamePrefix;
		path = path.substring(0, path.length() - 1);
		File folder = new File(path);
		
		// Load all filenames into the list
		File[] fileList = folder.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				// Add strings of folder path + filename
				longFilenameList.add(UserData.filenamePrefix + fileList[i].getName());
			}
		}
	}

	/**
	 * Access a user data from the list.
	 * @param index Index (0..9)
	 * @return UserData at index, null if not found.
	 */
	public UserData getUserData(int index) {
		// Check index
		if ((index < 0) || (index > entriesPerPage - 1) || index > shortFilenameList.size() -1) {
			return null; // out-of-bound
		}
		
		try {
			return UserData.importData(shortFilenameList.get(index));
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
	 * Update the entries to the current page number.
	 * @see setPage() to update the page number first
	 */
	public void updateEntries() {
		// Remove all previous entries
		shortFilenameList.clear();

		// Starting index's offset
		int offset = (page - 1) * 10;

		// Load new entries
		for (int i = 0; i < entriesPerPage; i++) {
			if (offset + i < longFilenameList.size()) {
				shortFilenameList.add(longFilenameList.get(offset + i));
			} else {
				break; // no more entries to load
			}
		}
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
