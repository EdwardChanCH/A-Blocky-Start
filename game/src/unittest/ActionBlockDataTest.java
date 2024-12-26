package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import mvc.ActionBlockData;

import mvc.ActionTypeEnum;

/**
 * Unit test for ActionBlockData.
 * @version 1.0
 * @since March 12, 2024
 * @author Chun Ho Chan (Edward)
 */
class ActionBlockDataTest {

	@Test
	void ConstructEmpty() {
		ActionTypeEnum type = ActionTypeEnum.UNKNOWN;
		ArrayList<Integer> args = new ArrayList<Integer>();
		assertAll(() -> new ActionBlockData(type, args));
	}
	
	@Test
	void ConstructStart() {
		ActionTypeEnum type = ActionTypeEnum.START;
		ArrayList<Integer> args = new ArrayList<Integer>();
		assertAll(() -> new ActionBlockData(type, args));
	}
	
	@Test
	void ConstructGoto() {
		ActionTypeEnum type = ActionTypeEnum.GOTO;
		ArrayList<Integer> args = new ArrayList<Integer>();
		args.add(10); // Line to jump)
		assertAll(() -> new ActionBlockData(type, args));
	}
	
	@Test
	void ConstructLoop() {
		ActionTypeEnum type = ActionTypeEnum.LOOP;
		ArrayList<Integer> args = new ArrayList<Integer>();
		args.add(5);  // End point
		args.add(3);  // Start point
		args.add(10); // Line to jump
		assertAll(() -> new ActionBlockData(type, args));
	}
	
	@Test
	void ImportEmpty() {
		String data = "";
		assertAll(() -> ActionBlockData.importData(data));
	}
	
	@Test
	void ImportStart() {
		String data = "START";
		assertAll(() -> ActionBlockData.importData(data));
	}
	
	@Test
	void ImportGoto() {
		String data = "GOTO_10";
		assertAll(() -> ActionBlockData.importData(data));
	}
	
	@Test
	void ImportLoop() {
		String data = "LOOP_5_3_10";
		assertAll(() -> ActionBlockData.importData(data));
	}
	
	@Test
	void ImportWrongGotoWeirdArguments() {
		String data = "GOTO__IO";
		assertAll(() -> ActionBlockData.importData(data));
	}
	
	@Test
	void ExportEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals("UNKNOWN", block.exportData());
	}
	
	@Test
	void ExportStart() {
		String data = "START";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(data, block.exportData());
	}
	
	@Test
	void ExportGoto() {
		String data = "GOTO_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(data, block.exportData());
	}
	
	@Test
	void ExportLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(data, block.exportData());
	}
	
	@Test
	void ExportWrongGotoWeirdArguments() {
		String data = "GOTO__IO";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals("UNKNOWN", block.exportData());
	}
	
	@Test
	void ExportWrongLoopTooFewArguments() {
		String data = "LOOP_1_2";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals("UNKNOWN", block.exportData());
	}
	
	@Test
	void ExportWrongLoopTooManyArguments() {
		String data = "LOOP_1_2_3_4";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals("UNKNOWN", block.exportData());
	}
	
	@Test
	void GetTypeLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(ActionTypeEnum.LOOP, block.getType());
	}
	
	@Test
	void GetArgsEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		ArrayList<Integer> list = new ArrayList<Integer>();
		assertEquals(list, block.getArgs());
	}
	
	@Test
	void GetArgsStart() {
		String data = "START";
		ActionBlockData block = ActionBlockData.importData(data);
		ArrayList<Integer> list = new ArrayList<Integer>();
		assertEquals(list, block.getArgs());
	}
	
	@Test
	void GetArgsGoto() {
		String data = "GOTO_10";
		ActionBlockData block = ActionBlockData.importData(data);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(10);
		assertEquals(list, block.getArgs());
	}
	
	@Test
	void GetArgsLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(3);
		list.add(10);
		assertEquals(list, block.getArgs());
	}
	
	@Test
	void GetArgsGotoWrongArguments() {
		String data = "GOTO__IO";
		ActionBlockData block = ActionBlockData.importData(data);
		ArrayList<Integer> list = new ArrayList<Integer>();
		assertEquals(list, block.getArgs());
	}
	
	@Test
	void GetEndPointEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(-1, block.getEndPoint());
	}
	
	@Test
	void GetEndPointLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(5, block.getEndPoint());
	}
	
	@Test
	void GetStartPointEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(-1, block.getStartPoint());
	}
	
	@Test
	void GetStartPointLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(3, block.getStartPoint());
	}
	
	@Test
	void GetJumpLineEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(-1, block.getJumpLine());
	}
	
	@Test
	void GetJumpLineGoto() {
		String data = "GOTO_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(10, block.getJumpLine());
	}
	
	@Test
	void GetJumpLineLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(10, block.getJumpLine());
	}
	
	@Test
	void GetCounterEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(-1, block.getCounter());
	}
	
	@Test
	void GetCounterLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(5 - 3, block.getCounter());
	}
	
	@Test
	void DecCounterEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		int oldCounter = block.getCounter();
		block.decCounter();
		assertEquals(oldCounter, block.getCounter());
	}
	
	@Test
	void DecCounterLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		int oldCounter = block.getCounter();
		block.decCounter();
		assertEquals(oldCounter - 1, block.getCounter());
	}
	
	@Test
	void ResetCounterEmpty() {
		String data = "";
		ActionBlockData block = ActionBlockData.importData(data);
		int oldCounter = block.getCounter();
		block.decCounter();
		block.resetCounter();
		assertEquals(oldCounter, block.getCounter());
	}
	
	@Test
	void ResetCounterLoop() {
		String data = "LOOP_5_3_10";
		ActionBlockData block = ActionBlockData.importData(data);
		int oldCounter = block.getCounter();
		block.decCounter();
		block.resetCounter();
		assertEquals(oldCounter, block.getCounter());
	}
	
	@Test
	void IsJumpStart() {
		String data = "START";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(false, block.isJump());
	}
	
	@Test
	void IsJumpGoto() {
		String data = "GOTO_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(true, block.isJump());
	}
	
	@Test
	void IsJumpLoopCountZero() {
		String data = "Loop_0_0_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(true, block.isJump());
	}
	
	@Test
	void IsJumpLoopCountOne() {
		String data = "Loop_1_0_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(false, block.isJump());
	}
	
	@Test
	void IsJumpLoopCountMinusOne() {
		String data = "Loop_-1_0_10";
		ActionBlockData block = ActionBlockData.importData(data);
		assertEquals(true, block.isJump());
	}
	
	@Test
	void IsJumpLoopCountEnded() {
		String data = "Loop_1_0_10";
		ActionBlockData block = ActionBlockData.importData(data);
		block.decCounter(); // counter = 0
		assertEquals(true, block.isJump());
	}
	
	@Test
	void IsJumpLoopCountReset() {
		String data = "Loop_1_0_10";
		ActionBlockData block = ActionBlockData.importData(data);
		block.decCounter(); // counter = 0
		block.resetCounter(); // counter = 1 - 0
		assertEquals(false, block.isJump());
	}

}
