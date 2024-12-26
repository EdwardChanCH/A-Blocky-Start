/**
 * 
 */
package unittest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mvc.LoginData;
import mvc.UserData;
import mvc.UserTypeEnum;

/**
 * Unit test for {@link mvc.LoginData}
 * @version 1.0
 * @since Mar 25, 2024
 * @author Eunhak Kim
 *
 */
class LoginDataTest {
	
	/**
	 * Test method for {@link mvc.LoginData#getMode()}.
	 */
	@Test
	void testGetMode() {
		LoginData ld = new LoginData();
		ld.setUsernameInput("tEAchEr");
		assertEquals(UserTypeEnum.TEACHER, ld.getMode());
	}

	/**
	 * Test method for {@link mvc.LoginData#setUsernameInput(java.lang.String)}.
	 */
	@Test
	void testSetUsernameInput() {
		LoginData ld = new LoginData();
		assertFalse(ld.setUsernameInput("$"));
	}

	/**
	 * Test method for {@link mvc.LoginData#setPasswordInput(java.lang.String)}.
	 */
	@Test
	void testSetPasswordInput() {
		LoginData ld = new LoginData();
		assertTrue(ld.setPasswordInput("#$*2"));
	}

	/**
	 * Test method for {@link mvc.LoginData#registerActiveUser()}.
	 */
	@Test
	void testRegisterActiveUser() {
		LoginData ld = new LoginData();
		ld.setUsernameInput("studentUnitTest");
		ld.setPasswordInput("pass1234");
		ld.registerActiveUser();
		UserData ud = new UserData(UserTypeEnum.STUDENT, "studentUnitTest", "pass1234");
		
		boolean equalness = true;
		equalness = equalness && (ud.getUserType().equals(ld.getActiveUserData().getUserType()));
		equalness = equalness && (ud.getUsername().equals(ld.getActiveUserData().getUsername()));
		equalness = equalness && (ud.getPassword().equals(ld.getActiveUserData().getPassword()));
		assertTrue(equalness);
	}

	/**
	 * Test method for {@link mvc.LoginData#loginActiveUser()}.
	 */
	@Test
	void testLoginActiveUser() {
		LoginData ld = new LoginData();
		ld.setUsernameInput("aliceliddell");
		ld.setPasswordInput("pass1234");
		ld.loginActiveUser();

		UserData ud = UserData.importData(UserData.toFilename("aliceliddell"));
		
		boolean equalness = true;
		equalness = equalness && (ud.getUserType().equals(ld.getActiveUserData().getUserType()));
		equalness = equalness && (ud.getUsername().equals(ld.getActiveUserData().getUsername()));
		equalness = equalness && (ud.getPassword().equals(ld.getActiveUserData().getPassword()));
		assertTrue(equalness);
	}

}
