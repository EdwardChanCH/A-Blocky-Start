package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
	
	private SettingsData settingsData;
	private static SettingsData backupData;
	
	@BeforeAll
	static void setUpClass() {
		backupData = SettingsData.importData();
	}
	
	@BeforeEach
    void setUp() {
        settingsData = SettingsData.importData();
    }
	
    @AfterEach
    void tearDown() {
    	backupData.exportData();
    }

	@Test
	void testImportData() {
		SettingsData settingsData = SettingsData.importData();
		assertNotNull(settingsData);
		
		 assertEquals(800, settingsData.getScreenWidth());
		 assertEquals(600, settingsData.getScreenHeight());
		 assertFalse(settingsData.getColourblindMode());
		 assertEquals(50, settingsData.getVolumePercentage());
	}
	
	@Test
	void testExportAndReImportSettings() {
		//SettingsData settingsData = SettingsData.importData();
	    // Modify settingsData
	    settingsData.setScreenHeight(settingsData.getScreenHeight() + 100);
	    settingsData.setScreenWidth(settingsData.getScreenWidth() + 100);
	    settingsData.setColourblindMode(!settingsData.getColourblindMode());
	    settingsData.setVolumeLevel(settingsData.getVolumePercentage() + 10);
	    
	    // Export modified settingsData
	    settingsData.exportData();

	    // Re-import to verify changes
	    SettingsData modifiedSettings = SettingsData.importData();
	    assertNotNull(modifiedSettings);
	    assertEquals(settingsData.getScreenHeight(), modifiedSettings.getScreenHeight());
	    assertEquals(settingsData.getScreenWidth(), modifiedSettings.getScreenWidth());
	    assertEquals(settingsData.getColourblindMode(), modifiedSettings.getColourblindMode());
	    assertEquals(settingsData.getVolumePercentage(), modifiedSettings.getVolumePercentage());
	    
	    modifiedSettings.setScreenHeight(settingsData.getScreenHeight() - 100);
	    modifiedSettings.setScreenWidth(settingsData.getScreenWidth() - 100);
	    modifiedSettings.setColourblindMode(!settingsData.getColourblindMode());
	    modifiedSettings.setVolumeLevel(settingsData.getVolumePercentage() - 10);
	    
	    modifiedSettings.exportData();
	}
	
	@Test
	void testGetScreenHeight() {
        int expectedHeight = 600;
        int actualHeight = settingsData.getScreenHeight();
        assertEquals(expectedHeight, actualHeight);
    }
	
	@Test
	void testSetScreenHeight() {
        int expectedHeight = 1400;
        settingsData.setScreenHeight(expectedHeight);
        int actualHeight = settingsData.getScreenHeight();
        assertEquals(expectedHeight, actualHeight);
    }
	
	@Test
	void testNegativeSetScreenHeight() {
        int expectedHeight = 600;
        int tryNegativeHeight = -1400;
        settingsData.setScreenHeight(tryNegativeHeight);
        int actualHeight = settingsData.getScreenHeight();
        assertEquals(expectedHeight, actualHeight);
    }
	
	
	@Test
    void testGetScreenWidth() {
        int expectedWidth = 800;
        int actualWidth = settingsData.getScreenWidth();
        assertEquals(expectedWidth, actualWidth);
    }
	
    @Test
    void testSetScreenWidth() {
        int expectedWidth = 800;
        settingsData.setScreenWidth(expectedWidth);
        int actualWidth = settingsData.getScreenWidth();
        assertEquals(expectedWidth, actualWidth);
    }
    
    @Test
    void testNegativeSetScreenWidth() {
        int expectedWidth = 800;
        int tryNegativeWidth = -800;
        settingsData.setScreenWidth(tryNegativeWidth);
        int actualWidth = settingsData.getScreenWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    void testGetColourblindMode() {
        boolean expectedMode = false;
        boolean actualMode = settingsData.getColourblindMode();
        assertEquals(expectedMode, actualMode);
    }
    
    @Test
    void testSetColourblindMode() {
        boolean expectedMode = false;
        settingsData.setColourblindMode(expectedMode);
        boolean actualMode = settingsData.getColourblindMode();
        assertEquals(expectedMode, actualMode);
    }

    @Test
    void testGetVolumeLevel() {
        int expectedVolume = 50;
        int actualVolume = settingsData.getVolumePercentage();
        assertEquals(expectedVolume, actualVolume);
    }
    
    @Test
    void testSetVolumeLevel() {
        int expectedVolume = 75;
        settingsData.setVolumeLevel(expectedVolume);
        int actualVolume = settingsData.getVolumePercentage();
        assertEquals(expectedVolume, actualVolume);
    }
    
    @Test
    void testNegativeSetVolumeLevel() {
    	int expectedVolume = 50;
        int tryNegativeVolume = -85;
        settingsData.setVolumeLevel(tryNegativeVolume);
        int actualVolume = settingsData.getVolumePercentage();
        assertEquals(expectedVolume, actualVolume);
    }
	
}
