package mvc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This controller class handles error logging. 
 * @version 1.0
 * @since Mar 17, 2024
 * @author Chun Ho Chan (Edward)
 * @author Eunhak Kim
 */
public class ErrorLogController {

	private static final String filename = "game_errorlog.txt";
	
	/**
	 * Called in Main
	 */
	public ErrorLogController() {
		// do nothing
	}
	
	/**
	 * Append an error message to the error log file.
	 * @param e Error message
	 * @throws IOException 
	 */
	public void addWarning(Exception e) {
		
		try {
			File log = new File(filename);
			log.createNewFile(); // create a new file if not found
			FileWriter writer = new FileWriter(filename, true);
			writer.write(">> Warning << " + e.toString() + '\n');
			System.out.println(">> Warning << " + e.toString());
			writer.close();
		} catch (IOException e1) {
			addError(e1);
		}
	}
	
	/**
	 * Append an error message to the error log file.
	 * @param e Error message
	 */
	public void addError(Exception e) {
		File log = new File(filename);
		
		try {
			log.createNewFile(); // create a new file if not found
			FileWriter writer = new FileWriter(filename, true);
			writer.write(">> Error << " + e.toString() + '\n');
			System.out.println(">> Error << " + e.toString());
			writer.close();
		} catch (IOException e1) {
			addError(e1);
		}
		
	}
	
	/**
	 * Clear the error log file.
	 */
	public void clearErrorLog() {
		File log = new File(filename);
		try {
			log.createNewFile(); // create a new file if not found
			FileWriter writer = new FileWriter(filename);	
			writer.write("");
			System.out.println("Error log cleared.");
			writer.close();
		} catch (IOException e1) {
			addError(e1);
		}
	}

}
