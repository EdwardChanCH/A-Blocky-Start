package unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mvc.ProgressionData;
import mvc.UserData;
import mvc.UserTypeEnum;

/**
 * Unit test for UserData.
 * @version 0.5
 * @since Mar 14, 2024
 * @author Eunhak Kim
 */
class UserDataTest {

	/**
	 * Test method for {@link mvc.UserData#importData(java.lang.String)}.
	 */
	@Test
	void testImportDataUserType() {
		UserData ud = UserData.importData(UserData.toFilename("student"));
		assertEquals(UserTypeEnum.STUDENT, ud.getUserType());
	}
	
	/**
	 * Test method for {@link mvc.UserData#importData(java.lang.String)}.
	 */
	@Test
	void testImportDataUsername() {
		UserData ud = UserData.importData(UserData.toFilename("student"));
		assertEquals("student", ud.getUsername());
	}
	
	/**
	 * Test method for {@link mvc.UserData#importData(java.lang.String)}.
	 */
	@Test
	void testImportDataPassword() {
		UserData ud = UserData.importData(UserData.toFilename("student"));
		assertEquals("pass1234", ud.getPassword());	
	}
	
	/**
	 * Test method for {@link mvc.UserData#importData(java.lang.String)}.
	 */
	@Test
	void testImportDataTotalScore() {
		UserData ud = UserData.importData(UserData.toFilename("student"));
		assertEquals(6, ud.getTotalScore());
	}
	
	/**
	 * Test method for {@link mvc.UserData#importData(java.lang.String)}.
	 */
	@Test
	void testImportDataTotalTimeSpent() {
		UserData ud = UserData.importData(UserData.toFilename("student"));
		assertEquals(70, ud.getTotalTimeSpent());
	}
	
	/**
	 * Test method for {@link mvc.UserData#importData(java.lang.String)}.
	 */
	@Test
	void testImportDataTotalAttempts() {
		UserData ud = UserData.importData(UserData.toFilename("student"));
		assertEquals(10, ud.getTotalAttempts());
	}
	
	/**
	 * Test method for {@link mvc.UserData#importData(java.lang.String)}.
	 */
	@Test
	void testImportDataProgression() {
		UserData ud = UserData.importData(UserData.toFilename("student"));
		int result = ud.getProgressionAtIndex(0).getTimeSpent();
		assertEquals(20, result);
	}

	/**
	 * Test method for {@link mvc.UserData#exportUserData()}.
	 * @throws FileNotFoundException 
	 */
	@Test
	void testExportUserDataFirstLine() throws FileNotFoundException {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalScore(6);
		ud.addTotalTimeSpent(70);
		ud.addTotalAttempts(10);
		
		String stage1 = "1,TRUE,0,3,20,2,/,START,FORWARD,END";
		ProgressionData pd = ProgressionData.importData(stage1);
		ud.addProgressionData(pd);
		
		String stage2 = "2,TRUE,0,3,50,8,/,START,FORWARD,LEFT,FORWARD,END";
		pd = ProgressionData.importData(stage2);
		ud.addProgressionData(pd);
		
		ud.exportUserData();

		String filename = UserData.toFilename("student");
		FileReader fileIn = new FileReader(filename);
		Scanner scnr = new Scanner(fileIn);
		String firstLine = "STUDENT,student,pass1234,6,70,10";
		
		assertEquals(firstLine, scnr.nextLine());
		
		scnr.close();
	}
	
	/**
	 * Test method for {@link mvc.UserData#exportUserData()}.
	 * @throws FileNotFoundException 
	 */
	@Test
	void testExportUserDataSecondLine() throws FileNotFoundException {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalScore(6);
		ud.addTotalTimeSpent(70);
		ud.addTotalAttempts(10);
		
		String stage1 = "1,TRUE,0,3,20,2,/,START,FORWARD,END";
		ProgressionData pd = ProgressionData.importData(stage1);
		ud.addProgressionData(pd);
		
		String stage2 = "2,TRUE,0,3,50,8,/,START,FORWARD,LEFT,FORWARD,END";
		pd = ProgressionData.importData(stage2);
		ud.addProgressionData(pd);
		
		ud.exportUserData();

		String filename = UserData.toFilename("student");
		FileReader fileIn = new FileReader(filename);
		Scanner scnr = new Scanner(fileIn);
		scnr.nextLine();
		assertEquals(stage1, scnr.nextLine());
		
		scnr.close();
	}
	
	/**
	 * Test method for {@link mvc.UserData#exportUserData()}.
	 * @throws FileNotFoundException 
	 */
	@Test
	void testExportUserDataThirdLine() throws FileNotFoundException {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalScore(6);
		ud.addTotalTimeSpent(70);
		ud.addTotalAttempts(10);
		
		String stage1 = "1,TRUE,0,3,20,2,/,START,FORWARD,END";
		ProgressionData pd = ProgressionData.importData(stage1);
		ud.addProgressionData(pd);
		
		String stage2 = "2,TRUE,0,3,50,8,/,START,FORWARD,LEFT,FORWARD,END";
		pd = ProgressionData.importData(stage2);
		ud.addProgressionData(pd);
		
		ud.exportUserData();

		String filename = UserData.toFilename("student");
		FileReader fileIn = new FileReader(filename);
		Scanner scnr = new Scanner(fileIn);
		scnr.nextLine();
		scnr.nextLine();

		assertEquals(stage2, scnr.nextLine());
		
		scnr.close();
	}
	
	/**
	 * Test method for {@link mvc.UserData#exportUserData()}.
	 * @throws Exception 
	 */
	@Test
	void testExportUserDataNotStudent() {
		UserData ud = new UserData(UserTypeEnum.TEACHER, "teacher", "GradeUs100%");
		assertThrows(Exception.class, ()->{ud.exportUserData();});
	}

	/**
	 * Test method for {@link mvc.UserData#toFilename(String)}.
	 */
	@Test
	void testToFilename() {
		String filename = "./userdata/student_userdata.csv";
		assertEquals(filename, UserData.toFilename("student"));
	}

	/**
	 * Test method for {@link mvc.UserData#getUserType()}.
	 */
	@Test
	void testGetUserType() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		assertEquals(UserTypeEnum.STUDENT, ud.getUserType());
	}

	/**
	 * Test method for {@link mvc.UserData#getUsername()}.
	 */
	@Test
	void testGetUsername() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		assertEquals("student", ud.getUsername());
	}

	/**
	 * Test method for {@link mvc.UserData#getPassword()}.
	 */
	@Test
	void testGetPassword() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		assertEquals("pass1234", ud.getPassword());
	}

	/**
	 * Test method for {@link mvc.UserData#getTotalScore()}.
	 */
	@Test
	void testGetTotalScore() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalScore(6);
		assertEquals(6, ud.getTotalScore());
	}

	/**
	 * Test method for {@link mvc.UserData#getTotalTimeSpent()}.
	 */
	@Test
	void testGetTotalTimeSpent() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalTimeSpent(70);
		assertEquals(70, ud.getTotalTimeSpent());
	}

	/**
	 * Test method for {@link mvc.UserData#getTotalAttempts()}.
	 */
	@Test
	void testGetTotalAttempts() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalAttempts(10);
		assertEquals(10, ud.getTotalAttempts());
	}

	/**
	 * Test method for {@link mvc.UserData#getProgressionList()}.
	 */
	@Test
	void testGetProgressionList() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ArrayList<ProgressionData> progList = new ArrayList<ProgressionData>();

		String stage1 = "1,TRUE,1,3,20,2,/,Start,Forward,End";
		ProgressionData pd = ProgressionData.importData(stage1);
		ud.addProgressionData(pd);
		progList.add(pd);
		
		String stage2 = "2,TRUE,3,3,50,8,/,Start,Forward,Left,Forward,End";
		pd = ProgressionData.importData(stage2);
		ud.addProgressionData(pd);
		progList.add(pd);
		
		assertEquals(progList, ud.getProgressionList());
	}

	/**
	 * Test method for {@link mvc.UserData#getProgressionAtIndex(int)}.
	 */
	@Test
	void testGetProgressionAtIndex1() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ArrayList<ProgressionData> progList = new ArrayList<ProgressionData>();

		String stage1 = "1,TRUE,1,3,20,2,/,Start,Forward,End";
		ProgressionData pd = ProgressionData.importData(stage1);
		ud.addProgressionData(pd);
		progList.add(pd);
		
		String stage2 = "2,TRUE,3,3,50,8,/,Start,Forward,Left,Forward,End";
		pd = ProgressionData.importData(stage2);
		ud.addProgressionData(pd);
		progList.add(pd);
		
		assertEquals(progList.get(1), ud.getProgressionAtIndex(1));
	}
	
	/**
	 * Test method for {@link mvc.UserData#getProgressionAtIndex(int)}.
	 */
	@Test
	void testGetProgressionAtIndexNull() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		assertNull(ud.getProgressionAtIndex(-1));
	}

	/**
	 * Test method for {@link mvc.UserData#addTotalScore(int)}.
	 */
	@Test
	void testAddTotalScore() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalScore(7);
		assertEquals(7, ud.getTotalScore());
	}

	/**
	 * Test method for {@link mvc.UserData#addTotalTimeSpent(int)}.
	 */
	@Test
	void testAddTotalTimeSpent() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalTimeSpent(100);
		assertEquals(100, ud.getTotalTimeSpent());
		}

	/**
	 * Test method for {@link mvc.UserData#addTotalAttempts(int)}.
	 */
	@Test
	void testAddTotalAttempts() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		ud.addTotalAttempts(3);
		assertEquals(3, ud.getTotalAttempts());
		}

	/**
	 * Test method for {@link mvc.UserData#updateTotalStats()}.
	 */
	@Test
	void testUpdateTotalStats() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");

		String stage1 = "1,TRUE,1,3,20,2,/,Start,Forward,End";
		ProgressionData pd = ProgressionData.importData(stage1);
		ud.addProgressionData(pd);
		
		String stage2 = "2,TRUE,3,3,50,8,/,Start,Forward,Left,Forward,End";
		pd = ProgressionData.importData(stage2);
		ud.addProgressionData(pd);
		
		ud.updateTotalStats();
		assertEquals(10, ud.getTotalAttempts());
	}

	/**
	 * Test method for {@link mvc.UserData#isStudent()}.
	 */
	@Test
	void testIsStudent() {
		UserData ud = new UserData(UserTypeEnum.STUDENT, "student", "pass1234");
		assertTrue(ud.isSTUDENT());
	}

	/**
	 * Test method for {@link mvc.UserData#isTeacher()}.
	 */
	@Test
	void testIsTeacher() {
		UserData ud = new UserData(UserTypeEnum.TEACHER, "teacher", "pass1234");
		assertTrue(ud.isTEACHER());
		}

	/**
	 * Test method for {@link mvc.UserData#isDeveloper()}.
	 */
	@Test
	void testIsDeveloper() {
		UserData ud = new UserData(UserTypeEnum.DEVELOPER, "developer", "pass1234");
		assertTrue(ud.isDEVELOPER());
	}

}
