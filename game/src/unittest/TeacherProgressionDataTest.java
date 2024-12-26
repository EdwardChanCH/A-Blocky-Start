package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mvc.TeacherProgressionData;

class TeacherProgressionDataTest {

	@Test
	void ConstructEmpty() {
		assertAll(() -> new TeacherProgressionData());
	}
	
	@Test
	void getUserDataNegativeIndex()
	{
		TeacherProgressionData data = new TeacherProgressionData();
		assertEquals(null, data.getUserData(-1));
	}
	
	@Test
	void getUserDataTooLargeIndex()
	{
		TeacherProgressionData data = new TeacherProgressionData();
		assertEquals(null, data.getUserData(100));
	}
	
	@Test
	void getUserData()
	{
		// TODO
	}
	
	@Test
	void getPageCheck()
	{
		TeacherProgressionData data = new TeacherProgressionData();
		assertEquals(1, data.getPage());
	}
	
	@Test
	void setPageOutOfBounds()
	{
		TeacherProgressionData data = new TeacherProgressionData();
		assertEquals(false, data.setPage(-1));
	}
	
	@Test
	void checkSetPage()
	{
		TeacherProgressionData data = new TeacherProgressionData();
		data.setPage(2);
		assertEquals(2, data.getPage());
	}

}
