package mvc;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;


/**
 * @author Yuting
 * This class contains all the icons and colours used in the game.
 */
public class IconUI {
	
	// Game colour scheme
	
	public static final Color lightOrange = new Color(255, 220, 120, 255);
	public static final Color mediumOrange = new Color(250, 150, 50, 255);
	public static final Color darkOrange = new Color(200, 100, 50, 255);
	
	public static final Color colorblindColor = new Color(255, 0, 255, 100);
	
	public static final Color darkenColor = new Color(0, 0, 0, 150);
	
	public static final Color transparentColor = new Color(0, 0, 0, 0);
	
	public static final Color actionBlockHeader = mediumOrange;
	public static final Color actionBlockIdle = lightOrange;
	public static final Color actionBlockCurrent = Color.GREEN;
	public static final Color actionBlockNext = Color.CYAN;
	
	// Size of various UI elements
	
	public static final Dimension TEXT_BUTTON_SIZE = new Dimension(150, 40);
	public static final Dimension ICON_BUTTON_SIZE = new Dimension(50, 50);
	public static final Dimension BACK_BUTTON_SIZE = new Dimension(30, 30);
	public static final Dimension ACTION_BUTTON_SIZE = new Dimension(100, 130);
	public static final Dimension MAZE_TILE_SIZE = new Dimension(50, 50);
	public static final int MAZE_TILE_WIDTH = (int) MAZE_TILE_SIZE.getWidth();
	public static final int MAZE_TILE_HEIGHT = (int) MAZE_TILE_SIZE.getHeight();
	
	// Game Title Background Image
	
	// 600 x 600
	public static final ImageIcon gameTitleIcon = new ImageIcon("img/GameTitle.png");
	
	// Button Images
	
	// 150 x 40
	public static final ImageIcon loginButtonIcon = new ImageIcon("img/MenuLogin.png");
	public static final ImageIcon registerButtonIcon = new ImageIcon("img/MenuRegister.png");
	public static final ImageIcon continueButtonIcon = new ImageIcon("img/MenuContinue.png");
	public static final ImageIcon newGameButtonIcon = new ImageIcon("img/MenuNewGame.png");
	public static final ImageIcon tutorialButtonIcon = new ImageIcon("img/MenuTutorial.png");
	public static final ImageIcon progressionButtonIcon = new ImageIcon("img/MenuProgression.png");
	public static final ImageIcon highscoreButtonIcon = new ImageIcon("img/MenuHighScores.png");
	public static final ImageIcon settingsButtonIcon = new ImageIcon("img/MenuSettings.png");
	public static final ImageIcon exitButtonIcon = new ImageIcon("img/MenuExit.png");
	public static final ImageIcon saveButtonIcon = new ImageIcon("img/MenuBlank.png"); // TODO
	public static final ImageIcon mainMenuButtonIcon = new ImageIcon("img/MenuBlank.png"); // TODO
	public static final ImageIcon blankButtonIcon = new ImageIcon("img/MenuBlank.png"); // TODO
	public static final ImageIcon playButtonIcon = new ImageIcon("img/menuPlay.png");

	// 30 x 30
	public static final ImageIcon backButtonIcon = new ImageIcon("img/IconBack.png");
	
	// 50 x 50
	public static final ImageIcon pauseMenuButtonIcon = new ImageIcon("img/IconMenu.png");
	public static final ImageIcon runChainButtonIcon = new ImageIcon("img/IconRun.png");
	public static final ImageIcon pauseChainButtonIcon = new ImageIcon("img/IconPause.png");
	public static final ImageIcon resetChainuttonIcon = new ImageIcon("img/IconReset.png");
	public static final ImageIcon objectivesButtonIcon = new ImageIcon("img/IconObjectives.png");
	public static final ImageIcon hintsButtonIcon = new ImageIcon("img/IconHints.png");
	public static final ImageIcon debugChainButtonIcon = new ImageIcon("img/IconDebug.png");
	public static final ImageIcon removeBlockButtonIcon = new ImageIcon("img/IconTrash.png");
	
	// 50 x 50
	public static final ImageIcon startBlockIcon = new ImageIcon("img/ActionStart.png");
	public static final ImageIcon endBlockIcon = new ImageIcon("img/ActionEnd.png");
	public static final ImageIcon forwardBlockIcon = new ImageIcon("img/ActionForward.png");
	public static final ImageIcon backBlockIcon = new ImageIcon("img/ActionBack.png");
	public static final ImageIcon leftBlockIcon = new ImageIcon("img/ActionLeft.png");
	public static final ImageIcon rightBlockIcon = new ImageIcon("img/ActionRight.png");
	public static final ImageIcon gotoBlockIcon = new ImageIcon("img/ActionGoto.png");
	public static final ImageIcon loopBlockIcon = new ImageIcon("img/ActionLoop.png");
	
	// 50 x 50
	public static final ImageIcon mazeSpawnIcon = new ImageIcon("img/MazeSpawn.png");
	public static final ImageIcon mazeExitIcon = new ImageIcon("img/MazeExit.png");
	public static final ImageIcon mazePathIcon = new ImageIcon("img/MazePath.png");
	public static final ImageIcon mazeBarrierIcon = new ImageIcon("img/MazeBarrier.png");
	public static final ImageIcon mazePackageIcon = new ImageIcon("img/MazePackage.png");
	public static final ImageIcon mazeBombIcon = new ImageIcon("img/MazeBomb.png");
	
	public static final ImageIcon mazeRobotNorthIcon = new ImageIcon("img/MazeRobotNorth.png");
	public static final ImageIcon mazeRobotEastIcon = new ImageIcon("img/MazeRobotEast.png");
	public static final ImageIcon mazeRobotSouthIcon = new ImageIcon("img/MazeRobotSouth.png");
	public static final ImageIcon mazeRobotWestIcon = new ImageIcon("img/MazeRobotWest.png");
	public static final ImageIcon mazeRobotDeadIcon = new ImageIcon("img/MazeRobotDead.png");
	
	 // 100 x 130
	public static final ImageIcon addForwardButtonIcon = new ImageIcon("img/BuffetForward.png");
	public static final ImageIcon addBackButtonIcon = new ImageIcon("img/BuffetBack.png");
	public static final ImageIcon addLeftButtonIcon = new ImageIcon("img/BuffetLeft.png");
	public static final ImageIcon addRightButtonIcon = new ImageIcon("img/BuffetRight.png");
	public static final ImageIcon addGotoButtonIcon = new ImageIcon("img/BuffetGoto.png");
	public static final ImageIcon addLoopButtonIcon = new ImageIcon("img/BuffetLoop.png");
	
}
