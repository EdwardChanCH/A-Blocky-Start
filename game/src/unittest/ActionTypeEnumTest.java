package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mvc.ActionTypeEnum;

/**
 * Unit test for ActionEnum.
 * @version 1.0
 * @since March 12, 2024
 * @author Chun Ho Chan (Edward)
 */
class ActionTypeEnumTest {
	
	@Test
	void StartToEnum() {
		String action = "START";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.START);
	}
	
	@Test
	void EndToEnum() {
		String action = "END";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.END);
	}
	
	@Test
	void ForwardToEnum() {
		String action = "FORWARD";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.FORWARD);
	}
	
	@Test
	void BackToEnum() {
		String action = "BACK";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.BACK);
	}
	
	@Test
	void LeftToEnum() {
		String action = "LEFT";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.LEFT);
	}
	
	@Test
	void RightToEnum() {
		String action = "RIGHT";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.RIGHT);
	}
	
	@Test
	void GotoToEnum() {
		String action = "GOTO";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.GOTO);
	}
	
	@Test
	void LoopToEnum() {
		String action = "LOOP";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.LOOP);
	}
	
	@Test
	void UnknownToEnum() {
		String action = "UNKNOWN";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.UNKNOWN);
	}
	
	@Test
	void ErrorToEnum() {
		String action = "ERROR";
		assertEquals(ActionTypeEnum.fromString(action), ActionTypeEnum.UNKNOWN);
	}
	
	@Test
	void StartToString() {
		ActionTypeEnum action = ActionTypeEnum.START;
		assertEquals(action.toString(), "START");
	}
	
	@Test
	void UnknownToString() {
		ActionTypeEnum action = ActionTypeEnum.UNKNOWN;
		assertEquals(action.toString(), "UNKNOWN");
	}

}
