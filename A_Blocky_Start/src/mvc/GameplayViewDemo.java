package mvc;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class GameplayViewDemo implements View {
	
	// List of action block UI
	private ArrayList<ActionBlockUI> actionBlockUIList;
	
	// List of maze item UI
	private ArrayList<ArrayList<LabelUI>> mazeItemUIGrid;
	private LabelUI mazeRobot;

	// Gameplay screen panels
	private PanelUI rootPanel;
	private ScrollPaneUI actionChainArea;
	private ScrollPaneUI actionBuffetArea;
	private LayeredPaneUI mazeArea;
	private PanelUI iconArea;
	private LayeredPaneUI actionChainContent;
	private PanelUI actionBuffetContent;
	private ButtonUI actionChainDarkArea; // it is used as a panel to disable all buttons layered below it
	private ButtonUI actionBuffetDarkArea; // it is used as a panel to disable all buttons layered below it
	
	// Pause menu panels
	private PanelUI pauseMenuSideArea;
	private ButtonUI pauseMenuDarkArea; // it is used as a panel to disable all buttons layered below it
	
	// Gameplay screen buttons
	private ButtonUI pauseMenuButton;
	private ButtonUI runChainButton;
	private ButtonUI pauseChainButton;
	private ButtonUI resetChainButton;
	private ButtonUI objectivesButton;
	private ButtonUI hintsButton;
	private ButtonUI debugChainButton;
	private ButtonUI addForwardButton;
	private ButtonUI addBackButton;
	private ButtonUI addLeftButton;
	private ButtonUI addRightButton;
	private ButtonUI addGotoButton;
	private ButtonUI addLoopButton;
	
	// Pause menu buttons
	private ButtonUI backButton;
	private ButtonUI saveButton;
	private ButtonUI tutorialButton;
	private ButtonUI settingsButton;
	private ButtonUI mainMenuButton;
	
	/**
	 * Constructor.
	 */
	public GameplayViewDemo() {
		initPanel();
		setRunPauseButtonVisibility(true);
	}
	
	@Override
	public void initPanel() {
		
		// Initialize list of action block UI
		actionBlockUIList = new ArrayList<ActionBlockUI>();
		
		// Initialize action buffet buttons
		addForwardButton = new ButtonUI(true, IconUI.ACTION_BUTTON_SIZE, null, IconUI.addForwardButtonIcon);
		addBackButton = new ButtonUI(true, IconUI.ACTION_BUTTON_SIZE, null, IconUI.addBackButtonIcon);
		addLeftButton = new ButtonUI(true, IconUI.ACTION_BUTTON_SIZE, null, IconUI.addLeftButtonIcon);
		addRightButton = new ButtonUI(true, IconUI.ACTION_BUTTON_SIZE, null, IconUI.addRightButtonIcon);
		addGotoButton = new ButtonUI(true, IconUI.ACTION_BUTTON_SIZE, null, IconUI.addGotoButtonIcon);
		addLoopButton = new ButtonUI(true, IconUI.ACTION_BUTTON_SIZE, null, IconUI.addLoopButtonIcon);
		
		// Initialize icon panel buttons
		pauseMenuButton = new ButtonUI(true, IconUI.ICON_BUTTON_SIZE, new Point(0, 0), IconUI.pauseMenuButtonIcon);
		runChainButton = new ButtonUI(true, IconUI.ICON_BUTTON_SIZE, new Point(0, 50), IconUI.runChainButtonIcon);
		pauseChainButton = new ButtonUI(true, IconUI.ICON_BUTTON_SIZE, new Point(0, 50), IconUI.pauseChainButtonIcon);
		resetChainButton = new ButtonUI(true, IconUI.ICON_BUTTON_SIZE, new Point(0, 100), IconUI.resetChainuttonIcon);
		objectivesButton = new ButtonUI(true, IconUI.ICON_BUTTON_SIZE, new Point(0, 150), IconUI.objectivesButtonIcon);
		hintsButton = new ButtonUI(true, IconUI.ICON_BUTTON_SIZE, new Point(0, 200), IconUI.hintsButtonIcon);
		debugChainButton = new ButtonUI(true, IconUI.ICON_BUTTON_SIZE, new Point(0, 250), IconUI.debugChainButtonIcon);
		
		// Initialize action chain
		actionChainContent = new LayeredPaneUI(true, null, null, IconUI.lightOrange);
		actionChainContent.setLayout(new BoxLayout(actionChainContent, BoxLayout.Y_AXIS));
		actionChainArea = new ScrollPaneUI(actionChainContent, 1, true, new Dimension(300, 600), new Point(0, 0));
		
		// Initialize action buffet
		actionBuffetContent = new PanelUI(true, null, null, IconUI.lightOrange);
		actionBuffetContent.setLayout(new BoxLayout(actionBuffetContent, BoxLayout.X_AXIS));
		actionBuffetContent.add(addForwardButton);
		actionBuffetContent.add(addBackButton);
		actionBuffetContent.add(addLeftButton);
		actionBuffetContent.add(addRightButton);
		actionBuffetContent.add(addGotoButton);
		actionBuffetContent.add(addLoopButton);
		actionBuffetArea = new ScrollPaneUI(actionBuffetContent, 0, true, new Dimension(500, 150), new Point(300, 450));
		
		// Initialize maze (tiles + robot icons)
		mazeArea = new LayeredPaneUI(true, new Dimension(450, 450), new Point(300, 0), IconUI.lightOrange);
		
		mazeItemUIGrid = new ArrayList<ArrayList<LabelUI>>();
		for (int i = 0; i < MazeData.MAX_ROWS; i++) {
			ArrayList<LabelUI> row = new ArrayList<LabelUI>();
			
			for (int j = 0; j < MazeData.MAX_COLUMNS; j++) {
				LabelUI tile = new LabelUI(IconUI.MAZE_TILE_SIZE, new Point(j * IconUI.MAZE_TILE_WIDTH, i * IconUI.MAZE_TILE_HEIGHT), IconUI.mazePathIcon);
				tile.setToolTipText(toToolTipText("Path", -1, -1));
				
				row.add(tile);
				mazeArea.add(tile, JLayeredPane.DEFAULT_LAYER);
			}
			
			mazeItemUIGrid.add(row);
		}
		
		mazeRobot = new LabelUI(IconUI.MAZE_TILE_SIZE, new Point(0, 0), IconUI.mazeRobotDeadIcon);
		mazeRobot.setToolTipText(toToolTipText("Robot", -1, -1));
		mazeArea.add(mazeRobot, JLayeredPane.PALETTE_LAYER);
		
		// Initialize icon buttons
		iconArea = new PanelUI(true, new Dimension(100, 450), new Point(750, 0), IconUI.lightOrange);
		iconArea.add(pauseMenuButton);
		iconArea.add(runChainButton);
		iconArea.add(pauseChainButton);
		iconArea.add(resetChainButton);
		iconArea.add(objectivesButton);
		iconArea.add(hintsButton);
		iconArea.add(debugChainButton);
		
		// Initialize pause menu
		pauseMenuSideArea = new PanelUI(false, new Dimension(300, 600), new Point(0, 0), IconUI.lightOrange);
		pauseMenuDarkArea = new ButtonUI(false, Main.getGameFrameDimension(), new Point(0, 0), IconUI.darkenColor);
		
		// Initialize button input blockers (used when action chain is running)
		actionChainDarkArea = new ButtonUI(false, new Dimension(285, 600), new Point(0, 0), IconUI.transparentColor);
		actionBuffetDarkArea = new ButtonUI(false, new Dimension(500, 135), new Point(300, 450), IconUI.transparentColor);
		
		// Panels that get added earlier are on top of later panels
		rootPanel = new PanelUI(false, new Dimension(800, 600), new Point(0, 0), IconUI.lightOrange);
		rootPanel.add(pauseMenuSideArea);
		rootPanel.add(pauseMenuDarkArea);
		rootPanel.add(actionChainDarkArea);
		rootPanel.add(actionBuffetDarkArea);
		rootPanel.add(actionChainArea);
		rootPanel.add(actionBuffetArea);
		rootPanel.add(mazeArea);
		rootPanel.add(iconArea);
		
		// Initialize pause menu buttons
		backButton = new ButtonUI(true, IconUI.BACK_BUTTON_SIZE, new Point(10, 10), IconUI.backButtonIcon);
		saveButton = new ButtonUI(true, IconUI.TEXT_BUTTON_SIZE, new Point(75, 100), IconUI.saveButtonIcon);
		tutorialButton = new ButtonUI(true, IconUI.TEXT_BUTTON_SIZE, new Point(75, 150), IconUI.tutorialButtonIcon);
		settingsButton = new ButtonUI(true, IconUI.TEXT_BUTTON_SIZE, new Point(75, 200), IconUI.settingsButtonIcon);
		mainMenuButton = new ButtonUI(true, IconUI.TEXT_BUTTON_SIZE, new Point(75, 250), IconUI.mainMenuButtonIcon);
		
		pauseMenuSideArea.add(backButton);
		pauseMenuSideArea.add(saveButton);
		pauseMenuSideArea.add(tutorialButton);
		pauseMenuSideArea.add(settingsButton);
		pauseMenuSideArea.add(mainMenuButton);
	}
	
	@Override
	public void refreshPanel() {
		rootPanel.repaint();
		rootPanel.revalidate();
	}
	
	@Override
	public void insertPanelToFrame(JFrame frame) {
		frame.add(rootPanel);
	}
	
	@Override
	public void setVisibility(boolean visibility) {
		rootPanel.setVisible(visibility);
	}
	
	public void setPauseMenuVisibility(boolean visibility) {
		actionChainArea.setVisible(!visibility);
		pauseMenuSideArea.setVisible(visibility);
		pauseMenuDarkArea.setVisible(visibility);
	}
	
	public void setActionChainDisable(boolean disable) {
		actionChainDarkArea.setVisible(disable);
	}
	
	public void setActionBuffetDisable(boolean disable) {
		actionBuffetDarkArea.setVisible(disable);
	}
	
	public void setActionBlockUIStatus(int indexPrevious, int indexCurrent, int indexNext) {
		actionBlockUIList.get(indexPrevious).setStatus(0);
		actionBlockUIList.get(indexNext).setStatus(2);
		actionBlockUIList.get(indexCurrent).setStatus(1);
	}
	
	public void setActionBlockUICounter(int index, int counter) {
		actionBlockUIList.get(index).setCounterText(counter);
	}
	
	/**
	 * Choose whether to show the Run Chain or Pause Chain button.
	 * @param showRun 1 = set as Run, 0 = set as Pause
	 */
	public void setRunPauseButtonVisibility(boolean showRun) {
		runChainButton.setEnabled(showRun);
		runChainButton.setVisible(showRun);
		pauseChainButton.setEnabled(!showRun);
		pauseChainButton.setVisible(!showRun);
	}
	
	public void refreshActionChainUI() {
		actionChainArea.repaint();
		actionChainArea.revalidate();
	}
	
	public void clearActionChainUI() {
		actionBlockUIList.clear();
		actionChainContent.removeAll();
	}
	
	public void appendActionBlockUI(int lineNumber, ActionBlockUI blockUI) {
		actionBlockUIList.add(lineNumber, blockUI);
		actionChainContent.add(blockUI, -1);
	}
	
	public void refreshMazeUI() {
		mazeArea.repaint();
		mazeArea.revalidate();
	}
	
	/**
	 * Update the icon of a maze tile.
	 * @param row Row number (0..MAX_ROWS)
	 * @param column Column number (0..MAX_COLUMNS)
	 * @param type Maze item type
	 */
	public void updateMazeItemIcon(int row, int column, MazeTypeEnum type) {
		Icon icon;
		String text;
		
		switch (type) {
		case WALL:
			icon = IconUI.mazeBarrierIcon;
			text = "Barrier";
			break;
		case PATH:
			icon = IconUI.mazePathIcon;
			text = "Path";
			break;
		case SPAWN:
			icon = IconUI.mazeSpawnIcon;
			text = "Spawn";
			break;
		case EXIT:
			icon = IconUI.mazeExitIcon;
			text = "Exit";
			break;
		case KEY:
			icon = IconUI.mazePackageIcon;
			text = "Package";
			break;
		case TRAP:
			icon = IconUI.mazeBombIcon;
			text = "Bomb";
			break;
		default:
			icon = IconUI.mazePathIcon;
			text = "Path";
			break;
		}
		
		mazeItemUIGrid.get(row).get(column).setIcon(icon);
		mazeItemUIGrid.get(row).get(column).setToolTipText(toToolTipText(text, row, column));
		mazeArea.repaint();
		mazeArea.revalidate();
	}
	
	/**
	 * Update the icon and position of the maze robot.<br>
	 * Shows robot explosion icon when rowOffset = 0 and columnOffset = 0.<br>
	 * @param row Row number (0..MAX_ROWS)
	 * @param column Column number (0..MAX_COLUMNS)
	 * @param rowOffset -1 = up, 1 = down
	 * @param columnOffset -1 = left, 1 = right
	 */
	public void updateMazeRobotIcon(int row, int column, int rowOffset, int columnOffset) {
		Icon icon;
		
		if ((rowOffset == -1) && (columnOffset == 0)) {
			icon = IconUI.mazeRobotNorthIcon;
		} else if ((rowOffset == 1) && (columnOffset == 0)) {
			icon = IconUI.mazeRobotSouthIcon;
		} else if ((rowOffset == 0) && (columnOffset == -1)) {
			icon = IconUI.mazeRobotWestIcon;
		} else if ((rowOffset == 0) && (columnOffset == 1)) {
			icon = IconUI.mazeRobotEastIcon;
		} else {
			icon = IconUI.mazeRobotDeadIcon;
		}
		
		mazeRobot.setIcon(icon);
		mazeRobot.setToolTipText(toToolTipText("Robot", row, column));
		mazeRobot.setLocation(new Point(column * IconUI.MAZE_TILE_WIDTH, row * IconUI.MAZE_TILE_HEIGHT));
		mazeArea.repaint();
		mazeArea.revalidate();
	}
	
	/**
	 * Generate a tool tip text.
	 * @param type Type of icon
	 * @param row (0..MAX_ROWS)
	 * @param column (0..MAX_COLUMNS)
	 * @return Tool Tip Text
	 */
	private String toToolTipText(String type, int row, int column) {
		int xCoor = column;
		int yCoor = MazeData.MAX_ROWS - row - 1;
		
		return type + " (" + xCoor + ", " + yCoor + ")";
	}
	
	// Action Listeners - Icon Buttons
	
	public void pauseMenuButton(ActionListener actionListener) {
		pauseMenuButton.addActionListener(actionListener);;
	}
	
	public void runChainButton(ActionListener actionListener) {
		runChainButton.addActionListener(actionListener);
	}
	
	public void pauseChainButton(ActionListener actionListener) {
		pauseChainButton.addActionListener(actionListener);
	}
	
	public void resetChainButton(ActionListener actionListener) {
		resetChainButton.addActionListener(actionListener);
	}
	
	public void objectivesButton(ActionListener actionListener) {
		objectivesButton.addActionListener(actionListener);
	}
	
	public void hintsButton(ActionListener actionListener) {
		hintsButton.addActionListener(actionListener);
	}
	
	public void debugChainButton(ActionListener actionListener) {
		debugChainButton.addActionListener(actionListener);
	}
	
	// Action Listeners - Action Buffet
	public void addForwardButton(ActionListener actionListener) {
		addForwardButton.addActionListener(actionListener);
	}
	
	public void addBackButton(ActionListener actionListener) {
		addBackButton.addActionListener(actionListener);
	}
	
	public void addLeftButton(ActionListener actionListener) {
		addLeftButton.addActionListener(actionListener);
	}
	
	public void addRightButton(ActionListener actionListener) {
		addRightButton.addActionListener(actionListener);
	}
	
	public void addGotoButton(ActionListener actionListener) {
		addGotoButton.addActionListener(actionListener);
	}
	
	public void addLoopButton(ActionListener actionListener) {
		addLoopButton.addActionListener(actionListener);
	}
	
	// Action Listeners - Pause Menu
	
	public void backButton(ActionListener actionListener) {
		backButton.addActionListener(actionListener);
	}
	
	public void saveButton(ActionListener actionListener) {
		saveButton.addActionListener(actionListener);
	}
	
	public void tutorialButton(ActionListener actionListener) {
		tutorialButton.addActionListener(actionListener);
	}
	
	public void settingsButton(ActionListener actionListener) {
		settingsButton.addActionListener(actionListener);
	}
	
	public void mainMenuButton(ActionListener actionListener) {
		mainMenuButton.addActionListener(actionListener);
	}

}
