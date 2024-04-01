package mvc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This model class stores and updates the game's settings. 
 * It loads/ saves data using the settings data file. 
 * All users share the same game settings.
 * @version 1.0
 * @since Mar 12, 2024
 * @author Simon McCabe
 */
public class SettingsData {
	/**
	 * Filename of the settings data file
	 */
	private static final String filename = "game_settingsdata.csv";
	/**
	 * Height of the screen
	 */
	private int screenHeight;
	/**
	 * Width of the screen
	 */
	private int screenWidth;
	/**
	 * Colourblind mode (true = enabled)
	 */
	private boolean colourblindMode;
	/**
	 * Volume of the game audio (percentage)
	 */
	private int volumePercentage;

	/**
	 * Initialize the settings data.
	 */
	public SettingsData(int screenWidth, int screenHeight, boolean colourblindMode, int volumePercentage) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.colourblindMode = colourblindMode;
		this.volumePercentage = volumePercentage;
	}

	/**
	 * Decode the encoded data stored in the settings data file.
	 * @see exportData() for details.
	 * @param filename Filename of settings file
	 * @return SettingsData, or null if the file does not exist
	 */
	public static SettingsData importData() {
		// Read from CSV file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			// Read the entire line of settings
			String line = reader.readLine();
			
			// Split the line by commas
			String[] parts = line.split(",");

			// Ensure there are at least 4 components
			int screenWidth;
			int screenHeight;
			boolean colourblindMode;
			int volumePercentage;
			if (parts.length < 4) {
				throw new Exception("Too few arguments.");
			} else {
				screenWidth = Integer.parseInt(parts[0]);
				screenHeight = Integer.parseInt(parts[1]);
				colourblindMode = Boolean.parseBoolean(parts[2]);
				volumePercentage = Integer.parseInt(parts[3]);
				
			}
			
			// Close the reader
			reader.close();
			
			// Call constructor
			return new SettingsData(screenWidth, screenHeight, colourblindMode, volumePercentage);
		} catch (Exception e) {
			Main.errorLogController.addError(e);
			return null;
		}
	}

	/**
	 * Export this user's data to the user data file.
	 * Format: (without spaces)<br>
	 * "screenHeight, screenWidth, colourblindMode, volumeLevel"<br>
	 */
	public void exportData() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			// Concatenate settings into a single line, separated by commas
			String settingsLine = "" + screenWidth + "," + screenHeight + "," + colourblindMode + "," + volumePercentage;
			
			 // Write the encoded data string
			writer.write(settingsLine);
			
			// Close the file
			writer.close();
			
		} catch (Exception e) {
			Main.errorLogController.addError(e);
		}
	}

	/**
	 * Access the screen width.
	 * @return Screen width
	 */
	public int getScreenWidth() {
		return screenWidth;
	}
	
	/**
	 * Access the screen height.
	 * @return Screen height
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * Access the colourblind mode.
	 * @return Colourblind mode (true = enabled)
	 */
	public boolean getColourblindMode() {
		return colourblindMode;
	}
	
	/**
	 * Access the volume level.
	 * @return Volume percentage (0..100)
	 */
	public int getVolumePercentage() {
		return volumePercentage;
	}

	/**
	 * Mutate the screen width (if it is non-negative).
	 * @param screenWidth Screen width
	 */
	public void setScreenWidth(int screenWidth) {
		if (screenWidth >= 0) {
			this.screenWidth = screenWidth;
		}
	}
	
	/**
	 * Mutate the screen height (if it is non-negative).
	 * @param screenHeight Screen width
	 */
	public void setScreenHeight(int screenHeight) {
		if (screenHeight >= 0) {
			this.screenHeight = screenHeight;
		}
	}
	
	/**
	 * Mutate the colourblind mode.
	 * @param colourblindMode Colourblind mode
	 */
	public void setColourblindMode(boolean colourblindMode) {
		this.colourblindMode = colourblindMode;
		System.out.println("Setter: " + this.colourblindMode + "," + colourblindMode);
	}

	/**
	 * Mutate the volume level (if it is non-negative).
	 * @param volumeLevel Volume level
	 */
	public void setVolumeLevel(int volumePercentage) {
		if ((volumePercentage >= 0) && (volumePercentage <= 100)) {
			this.volumePercentage = volumePercentage;
		}
	}
}
