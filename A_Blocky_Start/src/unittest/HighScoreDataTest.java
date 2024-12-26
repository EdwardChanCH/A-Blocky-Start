package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mvc.HighScoreData;

/**
 * Unit test for HighScoreData.
 * @version 1.0
 * @since March 12, 2024
 * @author Simon Mccabe
 */

class HighScoreDataTest {

	private HighScoreData highScoreData;
	private static HighScoreData backupData;
	
	@BeforeAll
	static void setUpClass() {
		backupData = HighScoreData.importData();
	}
	
    @BeforeEach
    void setUp() {
        highScoreData = HighScoreData.importData();
    }
    
    @AfterEach
    void tearDown() {
    	backupData.exportData();
    }  

    @Test
    void testInitialization() {
    	HighScoreData highScoreData = new HighScoreData();
        for (int i = 0; i < 5; i++) {
            assertEquals("(empty)", highScoreData.getUsername(i));
            assertEquals(0, highScoreData.getHighScore(i));
        }
    }

    @Test
    void testImportData() {
        HighScoreData highScoreData = HighScoreData.importData();
        assertNotNull(highScoreData);
        assertEquals("BruceLee", highScoreData.getUsername(0)); 
        assertEquals(15, highScoreData.getHighScore(0));
    }

    @Test
    void testExportHighScore() {
        highScoreData.addNameScore("TestUser", 999);
        highScoreData.exportData();

        HighScoreData importedData = HighScoreData.importData();
       
        assertEquals("TestUser", importedData.getUsername(0));
        assertEquals(999, importedData.getHighScore(0)); 
    }

    @Test
    void testGetUsername() {
        assertEquals("BruceLee", highScoreData.getUsername(0));
    }

    @Test
    void testGetHighScore() {
        assertEquals(15, highScoreData.getHighScore(0));
    }

    @Test
    void testAddNameScore() {
        assertTrue(highScoreData.addNameScore("NewUser", 500));
        assertEquals("NewUser", highScoreData.getUsername(0));
        assertEquals(500, highScoreData.getHighScore(0));
    }

    @Test
    void testIsIndexOutOfBound() {
        assertTrue(highScoreData.isIndexOutOfBound(5)); 
        assertFalse(highScoreData.isIndexOutOfBound(0));
    }


}
