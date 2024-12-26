package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mvc.MazeTypeEnum;
import mvc.MazeData;

/**
 * Unit test for MazeData.
 * @version 1.0
 * @since March 12, 2024
 * @author Chun Ho Chan (Edward)
 */
class MazeDataTest {

	@Test
	void Constructor() {
		MazeTypeEnum[][] grid = null;
		assertAll(() -> new MazeData(1, grid, 1, 1));
	}
	
	@Test
	void ImportData() {
		boolean success = true;
		MazeData data = MazeData.importData(MazeData.toFilename(999));
		success = success && true;
		assertTrue(success);
	}
	
	@Test
	void ToFilenameStage999() {
		assertEquals("./mazedata/stage999_mazedata.csv", MazeData.toFilename(999));
	}
	
	@Test
	void IsIndexOutOfBound0_8() {
		assertFalse(MazeData.isIndexOutOfBound(0, 8));
	}

}
