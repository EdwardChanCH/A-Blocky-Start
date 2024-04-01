package mvc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameplayController implements Controller {

	private static final int ACTION_CHAIN_SPEED = 500; // millisecond
	
	private static Controller previous;
	private static GameplayView view = new GameplayView();
	private static UserData user = UserData.importData(UserData.toFilename("brucelee")); // TODO remove this
	private static ProgressionData progress = user.getProgressionAtIndex(4); // TODO use a setter
	private static ActionChainData chain = progress.getActionChain();
	private static MazeData maze = MazeData.importData(MazeData.toFilename(999)); // TODO use a setter
	private static int stageID = 999; // TODO use a setter
	
	private Timer actionChainTimer;
	
	/**
	 * Constructor.
	 */
	public GameplayController() {
		view.insertPanelToFrame(Main.gameFrame);
		populateActionListener();
		rebuildActionChainUI();
		rebuildMazeUI();
		
		actionChainTimer = new Timer(ACTION_CHAIN_SPEED, this.new ActionChainTimerListener());
		actionChainTimer.setInitialDelay(0);
	}
	
	@Override
	public void onEnter(Controller previous) {
		Main.resizeGameFrame(new Dimension(815, 640));
		view.refreshPanel();
		
		GameplayController.previous = previous;
		view.setVisibility(true);
		view.setPauseMenuVisibility(false);
		view.setActionChainDisable(false);
		view.setActionBuffetDisable(false);
		resetActionChain();
		resetMaze();

		Main.refreshColorblindOverlay();
	}


	@Override
	public void onExit() {
		Main.resizeGameFrame(Main.getGameFrameDimension());
		view.refreshPanel();
		
		view.setVisibility(false);
		view.setPauseMenuVisibility(false);
		view.setActionChainDisable(false);
		view.setActionBuffetDisable(false);
		resetActionChain();
		resetMaze();
	}
	
	/**
	 * Help to insert action listeners to UI elements.
	 */
	private void populateActionListener() {
		
		// Pause menu buttons
		
		view.backButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setPauseMenuVisibility(false);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.saveButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("saveButton"); // TODO
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.tutorialButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.gameplayController.onExit();
				Main.tutorialController.onEnter(Main.gameplayController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.settingsButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.gameplayController.onExit();
				Main.settingsController.onEnter(Main.gameplayController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.mainMenuButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.gameplayController.onExit();
				Main.mainMenuController.onEnter(Main.gameplayController);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		// Icon buttons
		
		view.pauseMenuButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pauseActionChain();
				view.setRunPauseButtonVisibility(true);
				
				view.setPauseMenuVisibility(true);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.runChainButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runActionChain();
				view.setActionChainDisable(true);
				view.setActionBuffetDisable(true);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.pauseChainButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pauseActionChain();
				view.setActionChainDisable(true);
				view.setActionBuffetDisable(true);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.resetChainButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetActionChain();
				resetMaze();
				view.setActionChainDisable(false);
				view.setActionBuffetDisable(false);
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.objectivesButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("iconObjectivesButton"); // TODO
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.hintsButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("iconHintsButton"); // TODO
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.debugChainButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.refreshActionChainUI(); // TODO delete this line
				System.out.println(chain.exportData()); // TODO delete this line
				System.out.println("iconTypeChainButton"); // TODO
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		// Action buffet buttons
		
		view.addForwardButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTypeEnum type = ActionTypeEnum.FORWARD;
				ArrayList<Integer> args = new ArrayList<Integer>();
				chain.addBeforeEnd(new ActionBlockData(type, args));
				
				rebuildActionChainUI();
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.addBackButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTypeEnum type = ActionTypeEnum.BACK;
				ArrayList<Integer> args = new ArrayList<Integer>();
				chain.addBeforeEnd(new ActionBlockData(type, args));
				
				rebuildActionChainUI();
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.addLeftButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTypeEnum type = ActionTypeEnum.LEFT;
				ArrayList<Integer> args = new ArrayList<Integer>();
				chain.addBeforeEnd(new ActionBlockData(type, args));
				
				rebuildActionChainUI();
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.addRightButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTypeEnum type = ActionTypeEnum.RIGHT;
				ArrayList<Integer> args = new ArrayList<Integer>();
				chain.addBeforeEnd(new ActionBlockData(type, args));
				
				rebuildActionChainUI();
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.addGotoButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTypeEnum type = ActionTypeEnum.GOTO;
				ArrayList<Integer> args = new ArrayList<Integer>();
				args.add(1); // jump line
				chain.addBeforeEnd(new ActionBlockData(type, args));
				
				rebuildActionChainUI();
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
		
		view.addLoopButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionTypeEnum type = ActionTypeEnum.LOOP;
				ArrayList<Integer> args = new ArrayList<Integer>();
				args.add(0); // end point
				args.add(0); // start point
				args.add(1); // jump line
				chain.addBeforeEnd(new ActionBlockData(type, args));
				
				rebuildActionChainUI();
				Main.soundController.playSound(SoundController.buttonClick);
			}
		});
	}
	
	public void rebuildActionChainUI() {
		view.clearActionChainUI();
		
		for (int index = 0; index <= chain.getIndexEnd(); index++) {
			ActionBlockData block = chain.getActionBlock(index);
			ActionBlockUI blockUI = new ActionBlockUI(index, block.getType(), block.getEndPoint(), block.getStartPoint(), block.getJumpLine());
			
			blockUI.removeBlockButton(new RemoveBlockListener(index));
			blockUI.lineNumberField(new LineNumberListener(blockUI, index));
			blockUI.endPointField(new EndPointListener(blockUI, block));
			blockUI.startPointField(new StartPointListener(blockUI, block));
			blockUI.jumpLineField(new JumpLineListener(blockUI, block));
			
			view.appendActionBlockUI(index, blockUI);
		}
		
		view.refreshActionChainUI();
	}
	
	/**
	 * Set progression data for this stage.
	 * @param progressionData progression data for this stage
	 */
	public void setProgressionData(ProgressionData progressionData) {
		progress = progressionData;
	}
	
	public void runActionChain() {
		view.setRunPauseButtonVisibility(false);
		actionChainTimer.restart();
	}
	
	public void pauseActionChain() {
		view.setRunPauseButtonVisibility(true);
		actionChainTimer.stop();
	}
	
	public void resetActionChain() {
		pauseActionChain();
		chain.executeReset();
		rebuildActionChainUI();
	}
	
	public void rebuildMazeUI() {
		for (int i = 0; i < MazeData.MAX_ROWS; i++) {
			for (int j = 0; j < MazeData.MAX_COLUMNS; j++) {
				view.updateMazeItemIcon(i, j, maze.getMazeItem(i, j));
			}
		}
		view.updateMazeRobotIcon(maze.getRobotRow(), maze.getRobotColumn(), maze.getRobotRowOffset(), maze.getRobotColumnOffset());
		
		view.refreshMazeUI();
	}
	
	public void resetMaze() {
		maze = MazeData.importData(MazeData.toFilename(stageID));
		rebuildMazeUI();
	}
	
	/**
	 * Execute one step in the action chain and update the maze accordingly.
	 * @return True if executed, false otherwise (action chain ended or crashed)
	 */
	public boolean stepActionChain() {
		// Update action chain UI
		int indexPrevious = chain.getIndexCurrent();
		boolean success = chain.executeStep();
		int indexCurrent = chain.getIndexCurrent();
		int indexNext = chain.getIndexNext();
		view.setActionBlockUIStatus(indexPrevious, indexCurrent, indexNext);
		view.setActionBlockUICounter(indexCurrent, chain.getActionBlock(indexCurrent).getCounter());
		
		// Update maze UI
		ActionTypeEnum action = chain.getActionBlock(indexCurrent).getType();
		if (action == ActionTypeEnum.FORWARD || action == ActionTypeEnum.BACK) {
			boolean forward = (action == ActionTypeEnum.FORWARD);
			maze.moveRobot(forward);
			view.updateMazeRobotIcon(maze.getRobotRow(), maze.getRobotColumn(), maze.getRobotRowOffset(), maze.getRobotColumnOffset());
			view.updateMazeItemIcon(maze.getRobotRow(), maze.getRobotColumn(), maze.getMazeItem(maze.getRobotRow(), maze.getRobotColumn()));
		} else if (action == ActionTypeEnum.LEFT || action == ActionTypeEnum.RIGHT) {
			boolean counterClockwise = (action == ActionTypeEnum.LEFT);
			maze.rotateRobot(counterClockwise);
			view.updateMazeRobotIcon(maze.getRobotRow(), maze.getRobotColumn(), maze.getRobotRowOffset(), maze.getRobotColumnOffset());
		}
		
		// Successful
		return success;
	}
	
	// Action Listeners for Timer
	
	private class ActionChainTimerListener implements ActionListener {
		
		public ActionChainTimerListener() {
			// do nothing
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Execute action chain by 1 step
			boolean success = stepActionChain();
			
			// Check if action chain ended or crashed
			if (!success) {
				pauseActionChain(); 
			}
		}
	}
	
	// Action Listeners for ActionBlockUI
	
	private class RemoveBlockListener implements ActionListener {

		private int blockIndex;
		
		public RemoveBlockListener(int blockIndex) {
			this.blockIndex = blockIndex;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			chain.remove(blockIndex);
			
			// Rebuild the action chain UI
			rebuildActionChainUI();
		}
		
	}
	
	// Change Listeners for ActionBlockUI
	
	private class LineNumberListener implements ChangeListener {

		private ActionBlockUI blockUI;
		private int blockIndex;
		
		public LineNumberListener(ActionBlockUI blockUI, int blockIndex) {
			this.blockUI = blockUI;
			this.blockIndex = blockIndex;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int valueOld = blockIndex;
			int valueNew = blockUI.getLineNumberField();
			
			if (valueOld - valueNew == 0) {
				// Do nothing
			} else if (valueOld - valueNew > 1) {
				// Insert action block several lines above itself
				
				ActionBlockData block = chain.getActionBlock(valueOld);
				chain.remove(valueOld);
				
				boolean success = chain.addAtIndex(block, valueNew);
				if (!success) {
					chain.addAtIndex(block, valueOld);
					blockUI.setLineNumberField(valueOld);
					return;
				}
				
			} else if (valueOld - valueNew < -1) {
				// Insert action block several lines below itself
				
				ActionBlockData block = chain.getActionBlock(valueOld);
				chain.remove(valueOld);
				
				boolean success = chain.addAtIndex(block, valueNew);
				if (!success) {
					chain.addAtIndex(block, valueOld);
					blockUI.setLineNumberField(valueOld);
					return;
				}
			} else  {
				// Swap with adjacent action block
				
				boolean success = chain.swap(valueOld, valueNew);
				if (!success) {
					blockUI.setLineNumberField(valueOld);
					return;
				}
			}
			
			// Rebuild the action chain UI
			rebuildActionChainUI();
		}
		
	}
	
	private class EndPointListener implements ChangeListener {

		private ActionBlockUI blockUI;
		private ActionBlockData block;
		
		public EndPointListener(ActionBlockUI blockUI, ActionBlockData block) {
			this.blockUI = blockUI;
			this.block = block;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int valueOld = block.getEndPoint();
			int valueNew = blockUI.getEndPointField();
			
			boolean success = block.setEndPoint(valueNew);
			if (!success) {
				blockUI.setEndPointField(valueOld);
				return;
			}
			
			blockUI.setCounterText(block.getCounter());
		}
		
	}
	
	private class StartPointListener implements ChangeListener {

		private ActionBlockUI blockUI;
		private ActionBlockData block;
		
		public StartPointListener(ActionBlockUI blockUI, ActionBlockData block) {
			this.blockUI = blockUI;
			this.block = block;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int valueOld = block.getStartPoint();
			int valueNew = blockUI.getStartPointField();
			
			boolean success = block.setStartPoint(valueNew);
			if (!success) {
				blockUI.setStartPointField(valueOld);
				return;
			}

			blockUI.setCounterText(block.getCounter());
		}
		
	}
	
	private class JumpLineListener implements ChangeListener {

		private ActionBlockUI blockUI;
		private ActionBlockData block;
		
		public JumpLineListener(ActionBlockUI blockUI, ActionBlockData block) {
			this.blockUI = blockUI;
			this.block = block;
		}
		
		@Override
		public void stateChanged(ChangeEvent e) {
			int valueOld = block.getJumpLine();
			int valueNew = blockUI.getJumpLineField();
			
			boolean success = block.setJumpLine(valueNew);
			if (!success) {
				blockUI.setJumpLineField(valueOld);
				return;
			}
			
			success = (valueNew >= chain.getIndexStart() && valueNew <= chain.getIndexEnd());
			if (!success) {
				block.setJumpLine(valueOld);
				blockUI.setJumpLineField(valueOld);
				return;
			}
		}
		
	}

}
