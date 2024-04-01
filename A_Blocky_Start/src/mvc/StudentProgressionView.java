package mvc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;


/**
 * This class display the progression of a player
 * @author Yuting <br>
 * 
 */
public class StudentProgressionView implements View {
	
	//Define sizes
	private Dimension viewSize = Main.getGameFrameDimension();
	private Dimension backButtonSize = new Dimension(30,30);

	private Dimension containerSize = new Dimension(785, 800);
	private Dimension scrollPanelSize = new Dimension(785, 495);
	private Dimension entryContainerSize = new Dimension(500, 100);

	private Dimension labelSize = new Dimension(400, 20);
	
	//define locations
	private Point backButtonLocation = new Point(10,10);
	private Point containerLocation = new Point(0, 50);  //contains all student's progression
	private Point progressionTitleLocation = new Point(375,10);

	//define UI
	private JPanel rootPanel;
	private ButtonUI backButton;
	private PanelUI container;
	private JScrollPane scrollPane;
	
	//
	private ArrayList<PanelUI> entries = new ArrayList<PanelUI>();
	private JLabel progressionTitleLabel;
	
	/**
	 * Constructor.
	 */
	public StudentProgressionView() {
		initPanel();
		setVisibility(false);
	}
	
	@Override
	public void initPanel() {
		
		// set up progression panel
		rootPanel = new JPanel();
		rootPanel.setSize(viewSize);

		rootPanel.setBackground(IconUI.mediumOrange);

		rootPanel.setLayout(null);
		
		// added go back button to progression
		backButton = new ButtonUI(backButtonLocation, backButtonSize, "", IconUI.backButtonIcon);
		rootPanel.add(backButton);

		// add progression label
		progressionTitleLabel = new LabelUI(progressionTitleLocation, labelSize, "Continue Game");
		rootPanel.add(progressionTitleLabel);

		// set up container 
		container = new PanelUI(containerLocation, containerSize, IconUI.lightOrange);
		container.setBorder(new EmptyBorder(new Insets(0, 100, 0, 100)));
		FlowLayout layout = new FlowLayout();
		container.setLayout(new FlowLayout());  // set layout

		
		// adds 10 progression to container
		for (int i = 0; i < 10; i++) {
			
			PanelUI curr = newEntry();  // create empty container
			curr.setBackground(IconUI.darkOrange);
			
			entries.add(curr);  // add empty container to list
			container.add(entries.get(i));  // add the containers from list to screen
			container.add(Box.createVerticalStrut(20));  // spacing between each progression
			
		}

		
		// initialize scroll bar, container is converted
		scrollPane = new JScrollPane(container);
		
		// change scroll bar settings
		scrollPane.setSize(scrollPanelSize);
		scrollPane.setLocation(containerLocation);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVisible(false);
		
		// adding scrollable container to progression panel
		rootPanel.add(scrollPane);
		
		setVisibility(false);  // stops unwanted panel appearance
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
		backButton.setVisible(visibility);
		scrollPane.setVisible(visibility);
	}
	
	//@param index the index of the progression
	//TODO: this function needs to display progression data
	public void setEntry(int index, int stageID, boolean completed, int shortestSteps, int highScore, int timeSpent, int attempts) {
		
		PanelUI entry = entries.get(index);
		
		BorderLayout layout = new BorderLayout();
		
		PanelUI stats1 = new PanelUI(new Dimension(200, 200), IconUI.darkOrange);
		PanelUI stats2 = new PanelUI(new Dimension(200, 200), IconUI.darkOrange);
		stats1.setLayout(new BoxLayout(stats1, BoxLayout.Y_AXIS));
		stats2.setLayout(new BoxLayout(stats2, BoxLayout.Y_AXIS));
		
		entry.setLayout(layout);
		entry.setSize(entryContainerSize);
		entry.setBackground(IconUI.darkOrange);
		
		LabelUI stageIDUI = new LabelUI(labelSize, "Level " + stageID);
		LabelUI completedUI = new LabelUI(labelSize, "Completed: " + completed);
		LabelUI shortestStepsUI = new LabelUI(labelSize, "Shortest Steps: " + shortestSteps);
		LabelUI highestScoreUI = new LabelUI(labelSize, "High Score: " + highScore);
		LabelUI timeSpentUI = new LabelUI(labelSize, "Time Spent: " + timeSpent);
		LabelUI attemptsUI = new LabelUI(labelSize, "Attempts: " + attempts);
		ButtonUI playButton = new ButtonUI(labelSize, "", IconUI.playButtonIcon);
		
		stats1.setBorder(new EmptyBorder(new Insets(20, 20, 20, 0)));
		stats1.add(stageIDUI);
		stats1.add(Box.createGlue());
		stats1.add(completedUI);
		stats1.add(Box.createGlue());
		stats1.add(shortestStepsUI);
		stats2.setBorder(new EmptyBorder(new Insets(20, 20, 20, 0)));
		stats2.add(highestScoreUI);
		stats2.add(Box.createGlue());
		stats2.add(timeSpentUI);
		stats2.add(Box.createGlue());
		stats2.add(attemptsUI);
		entry.add(stats1, BorderLayout.WEST);
		entry.add(stats2, BorderLayout.CENTER);
		entry.add(playButton, BorderLayout.EAST);
		
	}
	
	/**
	 * This method adds an empty progression record container to the screen.
	 */
	public PanelUI newEntry() {
		
		//create a new PanelUI
		PanelUI entry = new PanelUI(entryContainerSize);
		
		return entry;
	}
	
	// Action Listeners
	
	public void backButtonAddActionListener(ActionListener actionListener) {
		backButton.addActionListener(actionListener);
	}


	

}
