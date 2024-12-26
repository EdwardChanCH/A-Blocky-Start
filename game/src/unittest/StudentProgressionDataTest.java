package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mvc.StudentProgressionData;
import mvc.UserData;
import mvc.UserTypeEnum;

class StudentProgressionDataTest {

	@Test
	void ConstructFull() {
		UserData user = new UserData(UserTypeEnum.STUDENT, "User", "1234");
		assertAll(() -> new StudentProgressionData(user));
	}

	@Test
	void ConstructParameters() {
		UserData user = new UserData(UserTypeEnum.STUDENT, "User", "1234");
		StudentProgressionData studentData = new StudentProgressionData(user);
		assertEquals(1, studentData.getPage());
	}

	@Test
	void TestGetPage() {
		UserData user = new UserData(UserTypeEnum.STUDENT, "User", "1234");
		StudentProgressionData studentData = new StudentProgressionData(user);
		studentData.setPage(5);
		assertEquals(5, studentData.getPage());
	}

	@Test
	void TestSetPage() {
		UserData user = new UserData(UserTypeEnum.STUDENT, "User", "1234");
		StudentProgressionData studentData = new StudentProgressionData(user);
		studentData.setPage(6);
		assertEquals(6, studentData.getPage());
	}

	@Test
	void TestSetPageOutOfBounds() {
		UserData user = new UserData(UserTypeEnum.STUDENT, "User", "1234");
		StudentProgressionData studentData = new StudentProgressionData(user);
		assertEquals(false, studentData.setPage(-1));
	}

	@Test
	void TestGetProgressionIndexTooSmall() {
		UserData user = new UserData(UserTypeEnum.STUDENT, "User", "1234");
		StudentProgressionData studentData = new StudentProgressionData(user);
		assertEquals(null, studentData.getProgression(-1));
	}

	@Test
	void TestGetProgressionTooBig() {
		UserData user = new UserData(UserTypeEnum.STUDENT, "User", "1234");
		StudentProgressionData studentData = new StudentProgressionData(user);
		assertEquals(null, studentData.getProgression(100));
	}

	@Test
	void TestGetProgression() {

	}
}