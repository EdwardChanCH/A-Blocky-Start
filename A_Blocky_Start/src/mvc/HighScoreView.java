package mvc;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yuting Hou
 */
public class HighScoreView implements View {

	// Sizes
	private Dimension viewSize = new Dimension(800, 600);
	private Dimension backButtonSize = new Dimension(30, 30); 
	private Dimension entriesContainerSize = new Dimension(600, 500);
	private Dimension entriesSize = new Dimension(600, 50);
	private Dimension entriesLabelSize = new Dimension(100, 20);
	private Dimension highScoreTitleSize = new Dimension (120,36);
	
	// spacing between high score entries
	private int margin = 20;
	
	// locations
	private Point backButtonLocation = new Point(10, 10);
	private Point entriesContainerLocation = new Point(100, 50);
	private Point highScoreTitleLocation = new Point(375,10);
	// panel containing all UI elements
	private JPanel rootPanel;
	
	// button
	private ButtonUI backButton = new ButtonUI(backButtonLocation, backButtonSize, "", IconUI.backButtonIcon);
	
	// container for entries
	private PanelUI entriesContainer = new PanelUI(entriesContainerLocation, entriesContainerSize, IconUI.darkOrange);
	
	private ArrayList<PanelUI> entries = new ArrayList<PanelUI>();
	
	private ArrayList<LabelUI> nameEntries = new ArrayList<LabelUI>();
	
	private ArrayList<LabelUI> highScoreEntries = new ArrayList<LabelUI>();
	
	private JLabel highScoreTitleLabel = new LabelUI(highScoreTitleLocation, highScoreTitleSize, "High Scores");
	
	/**
	 * Constructor.
	 */
	public HighScoreView() {
		initPanel();
		setVisibility(false);
	}
	
	@Override
	public void initPanel() {
		
		// set up root panel
		rootPanel = new JPanel();
		rootPanel.setSize(viewSize);
		rootPanel.setLayout(null);
		rootPanel.setLayout(null);
		rootPanel.setVisible(false);
		rootPanel.setBackground(IconUI.mediumOrange);
		
		// buttons
		rootPanel.add(backButton);
		
		rootPanel.add(highScoreTitleLabel);
		
		// set up grid with 5 rows and 1 column
		GridLayout layout = new GridLayout(5, 1);
		layout.setVgap(margin);
		
		// entries
		entriesContainer.setLayout(layout);
		
		// adds 5 high score entries
		for (int i = 0; i < 5; i++) {
			
			// create name and score label
			LabelUI currName = new LabelUI(entriesLabelSize, "DEFAULT_NAME");
			LabelUI currScore = new LabelUI(entriesLabelSize, "0");
			
			// create entry
			PanelUI curr  = newEntryUI(currName, currScore);
			
			// add entry, name and score label to list
			entries.add(curr);
			nameEntries.add(currName);
			highScoreEntries.add(currScore);
			
			entriesContainer.add(entries.get(i));
			
		}
		
		// add container to root
		rootPanel.add(entriesContainer);
		
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
	}
	
	/**
	 * Adds a high score to the table.
	 * @param index 0 to 4, 0 being at the top
	 * @param name the player's name
	 * @param score the high score entry
	 */
	public void addHighscore(int index, String name, int score) {
		
		entries.get(index);
		nameEntries.get(index).setText(name);
		highScoreEntries.get(index).setText("" + score);
		
	}
	
	/**
	 * Creates a high score entry.
	 * @param name the player's name
	 * @param score the player's score
	 * @return entry a Panel containing the player's name and score
	 */
	public PanelUI newEntryUI(JLabel name, JLabel score) {
		PanelUI entry = new PanelUI(entriesSize, IconUI.lightOrange);
		entry.setLayout(new BoxLayout(entry, BoxLayout.X_AXIS));
		entry.add(Box.createHorizontalStrut(20));
		entry.add(name);
		entry.add(Box.createGlue());
		entry.add(score);
		entry.add(Box.createHorizontalStrut(20));
		return entry;
	}
	
	// Action Listeners

	public void backButton(ActionListener actionListener) {
		backButton.addActionListener(actionListener);
	}

}
