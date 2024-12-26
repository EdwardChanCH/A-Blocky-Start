package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mvc.SettingsData;

/**
 * Unit test for SettingsData.
 * @version 1.0
 * @since March 12, 2024
 * @author Simon Mccabe
 */
class SettingsDataTest {
	
	private SettingsData settings;
	
	@BeforeEach
    void setUp() {
        //new SettingsData();
		// Initialize or reset settings before each test
        settings = SettingsData.importData();
    }

	@Test
	void testImportData() {
		SettingsData settings = SettingsData.importData();
		assertNotNull(settings);
		
		 assertEquals(800, settings.getScreenWidth());
		 assertEquals(600, settings.getScreenHeight());
		 assertFalse(settings.getColourblindMode());
		 assertEquals(50, settings.getVolumePercentage());
	}
	
	@Test
	void testExportAndReImportSettings() {
		//SettingsData settings = SettingsData.importData();
	    // Modify settings
	    settings.setScreenHeight(settings.getScreenHeight() + 100);
	    settings.setScreenWidth(settings.getScreenWidth() + 100);
	    settings.setColourblindMode(!settings.getColourblindMode());
	    settings.setVolumeLevel(settings.getVolumePercentage() + 10);
	    
	    // Export modified settings
	    settings.exportData();

	    // Re-import to verify changes
	    SettingsData modifiedSettings = SettingsData.importData();
	    assertNotNull(modifiedSettings);
	    assertEquals(settings.getScreenHeight(), modifiedSettings.getScreenHeight());
	    assertEquals(settings.getScreenWidth(), modifiedSettings.getScreenWidth());
	    assertEquals(settings.getColourblindMode(), modifiedSettings.getColourblindMode());
	    assertEquals(settings.getVolumePercentage(), modifiedSettings.getVolumePercentage());
	    
	    modifiedSettings.setScreenHeight(settings.getScreenHeight() - 100);
	    modifiedSettings.setScreenWidth(settings.getScreenWidth() - 100);
	    modifiedSettings.setColourblindMode(!settings.getColourblindMode());
	    modifiedSettings.setVolumeLevel(settings.getVolumePercentage() - 10);
	    
	    modifiedSettings.exportData();
	}
	
	@Test
	void testGetScreenHeight() {
        int expectedHeight = 600;
        int actualHeight = settings.getScreenHeight();
        assertEquals(expectedHeight, actualHeight);
    }
	
	@Test
	void testSetScreenHeight() {
        int expectedHeight = 1400;
        settings.setScreenHeight(expectedHeight);
        int actualHeight = settings.getScreenHeight();
        assertEquals(expectedHeight, actualHeight);
    }
	
	@Test
	void testNegativeSetScreenHeight() {
        int expectedHeight = 600;
        int tryNegativeHeight = -1400;
        settings.setScreenHeight(tryNegativeHeight);
        int actualHeight = settings.getScreenHeight();
        assertEquals(expectedHeight, actualHeight);
    }
	
	
	@Test
    void testGetScreenWidth() {
        int expectedWidth = 800;
        int actualWidth = settings.getScreenWidth();
        assertEquals(expectedWidth, actualWidth);
    }
	
    @Test
    void testSetScreenWidth() {
        int expectedWidth = 800;
        settings.setScreenWidth(expectedWidth);
        int actualWidth = settings.getScreenWidth();
        assertEquals(expectedWidth, actualWidth);
    }
    
    @Test
    void testNegativeSetScreenWidth() {
        int expectedWidth = 800;
        int tryNegativeWidth = -800;
        settings.setScreenWidth(tryNegativeWidth);
        int actualWidth = settings.getScreenWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    void testGetColourblindMode() {
        boolean expectedMode = false;
        boolean actualMode = settings.getColourblindMode();
        assertEquals(expectedMode, actualMode);
    }
    
    @Test
    void testSetColourblindMode() {
        boolean expectedMode = false;
        settings.setColourblindMode(expectedMode);
        boolean actualMode = settings.getColourblindMode();
        assertEquals(expectedMode, actualMode);
    }

    @Test
    void testGetVolumeLevel() {
        int expectedVolume = 50;
        int actualVolume = settings.getVolumePercentage();
        assertEquals(expectedVolume, actualVolume);
    }
    
    @Test
    void testSetVolumeLevel() {
        int expectedVolume = 75;
        settings.setVolumeLevel(expectedVolume);
        int actualVolume = settings.getVolumePercentage();
        assertEquals(expectedVolume, actualVolume);
    }
    
    @Test
    void testNegativeSetVolumeLevel() {
    	int expectedVolume = 50;
        int tryNegativeVolume = -85;
        settings.setVolumeLevel(tryNegativeVolume);
        int actualVolume = settings.getVolumePercentage();
        assertEquals(expectedVolume, actualVolume);
    }
	
}
