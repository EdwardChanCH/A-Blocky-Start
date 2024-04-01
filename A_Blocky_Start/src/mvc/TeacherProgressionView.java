 package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

/**
 * 
 */
public class TeacherProgressionView implements View {

	private Dimension viewSize = Main.getGameFrameDimension();

	private Dimension backButtonSize = new Dimension(30,30);
	private Dimension containerSize = new Dimension(785, 1000);
	private Dimension entryContainerSize = new Dimension(700, 50);
	private Dimension labelSize = new Dimension(200, 20);
	private Dimension selectorSize = new Dimension(50, 20);

	//define locations
	private Point backButtonLocation = new Point(10,10);
	private Point containerLocation = new Point(0, 50);  //contains all student's progression
	private Point selectorLocation = new Point(700, 20);  //page selector
	
	//define UI
	private JPanel rootPanel;
	private ButtonUI backButton;
	private PanelUI container;
	private JScrollPane scrollPane;

	private SpinnerNumberModel model;
	private JSpinner pageSelector;
	
	//10 entries per page
	private ArrayList<PanelUI> entries = new ArrayList<PanelUI>();

	/**
	 * Constructor.
	 */
	public TeacherProgressionView() {
		initPanel();
		setVisibility(false);
	}
	
	@Override
	public void initPanel() {

		// sets up root panel
		rootPanel = new JPanel();
		rootPanel.setSize(viewSize);
		rootPanel.setBackground(Color.orange);
		rootPanel.setLayout(null);
		
		// added go back button to progression
		backButton = new ButtonUI(backButtonLocation, backButtonSize, "", IconUI.backButtonIcon);
		
		// adds page selector
		model = new SpinnerNumberModel(1, 1, 10, 1);
		pageSelector = new JSpinner(model);
		pageSelector.setLocation(selectorLocation);
		pageSelector.setSize(selectorSize);
		pageSelector.setVisible(false);  //hides unwanted appearance
		
		// adds back button
		rootPanel.add(backButton);
		rootPanel.add(pageSelector);
		backButton.setVisible(true);
		
		// container
		container = new PanelUI(containerLocation, containerSize, Color.WHITE);
		
		// add 10 entries
		for (int i = 0; i < 10; i++) {
			PanelUI curr = newEntry();
			
			entries.add(curr);
			container.add(curr);
			
		}
		
		// convert container into scroll view, add scroll view
		scrollPane = new JScrollPane(container);
		scrollPane.setSize(785, 490);
		scrollPane.setLocation(containerLocation);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rootPanel.add(scrollPane);
		
		rootPanel.setVisible(false);
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
		pageSelector.setVisible(visibility);
	}

	public void setEntry(int index, String studentName, int timeSpent, int totalAttempts, int totalScore, int completedStage) {
		
		LabelUI nameLabel = new LabelUI(labelSize, "Name: " + studentName);
		LabelUI timeSpentLabel = new  LabelUI(labelSize, "Time Spent: " + timeSpent);
		LabelUI totalAttemptsLabel = new LabelUI(labelSize, "Total Attempts: " + totalAttempts);
		LabelUI totalScoreLabel = new LabelUI(labelSize, "Total Score: " + totalScore);
		LabelUI completedStageLabel = new LabelUI(labelSize, "Label Size: " + completedStage);
		
		PanelUI entry = entries.get(index);
		FlowLayout layout = new FlowLayout();
		layout.setHgap(20);
		entry.setLayout(layout);
		
		entry.setBackground(Color.LIGHT_GRAY);
		entry.removeAll();
		entry.repaint();
		entry.revalidate();
		entry.add(nameLabel);
		entry.add(timeSpentLabel);
		entry.add(totalAttemptsLabel);
		entry.add(totalScoreLabel);
		entry.add(completedStageLabel);
		
		
		
	}

	public PanelUI newEntry() {
		
		PanelUI newEntry = new PanelUI(entryContainerSize);
		
		return newEntry;
		
	}
	
	public int getPage() {
		return (int) pageSelector.getValue();
	}
	
	// Action Listeners

	public void backButtonAddActionListener(ActionListener actionListener) {
		backButton.addActionListener(actionListener);
	}
	
	public void selectorAddChangeListener(ChangeListener changeListener) {
		pageSelector.addChangeListener(changeListener);
	}

	

}
