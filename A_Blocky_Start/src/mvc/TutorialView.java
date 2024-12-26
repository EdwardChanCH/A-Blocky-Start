package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 */
public class TutorialView implements View {
	
	private Dimension viewSize = Main.getGameFrameDimension();
	
	// Contains all the tutorial prompts
	private Dimension containerSize = new Dimension(800, 600);
	private Dimension backButtonSize = new Dimension(30, 30);
	private Dimension tutorialTitleSize = new Dimension(120,36);
	
	
	private Point containerLocation = new Point(0,50);
	private Point backButtonLocation = new Point(10, 10);
	private Point tutorialTitleLocation = new Point(375,10);
	
	
	private JPanel container = new PanelUI(containerLocation, new Dimension(800, 6000), Color.white);
	private ButtonUI backButton = new ButtonUI(backButtonLocation, backButtonSize, "", IconUI.backButtonIcon);
	private JLabel tutorialTitle = new LabelUI(tutorialTitleLocation, tutorialTitleSize, "Tutorial");
	private ScrollPaneUI scrollPane;
	
	private JPanel rootPanel;
	
	/**
	 * Constructor.
	 */
	public TutorialView() {
		initPanel();
		setVisibility(false);
	}
	
	@Override
	public void initPanel() {
		rootPanel = new JPanel();
		rootPanel.setSize(viewSize);
		rootPanel.setLayout(null);
		rootPanel.setVisible(false); // hide unwanted occurrence
		rootPanel.setBackground(IconUI.mediumOrange);
		
		container.setBackground(IconUI.lightOrange);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(new ButtonUI(true, containerSize, null, IconUI.tutorial1));
		container.add(Box.createVerticalStrut(20));
		container.add(new ButtonUI(true, containerSize, null, IconUI.tutorial2));
		container.add(Box.createVerticalStrut(20));
		container.add(new ButtonUI(true, containerSize, null, IconUI.tutorial3));
		container.add(Box.createVerticalStrut(20));
		container.add(new ButtonUI(true, containerSize, null, IconUI.tutorial4));
		container.add(Box.createVerticalStrut(20));
		container.add(new ButtonUI(true, containerSize, null, IconUI.tutorial5));
		container.add(Box.createVerticalStrut(20));
		container.add(new ButtonUI(true, containerSize, null, IconUI.tutorial6));
		container.add(Box.createVerticalStrut(20));
		container.add(new ButtonUI(true, containerSize, null, IconUI.tutorial7));
		container.add(Box.createVerticalStrut(20));
		scrollPane = new ScrollPaneUI(container, 1, true, containerSize, containerLocation);
		
		rootPanel.add(backButton);
		rootPanel.add(scrollPane);
		rootPanel.add(tutorialTitle);
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
	
	// Action Listeners
	
	public void backButtonAddActionListener(ActionListener actionListener) {
		backButton.addActionListener(actionListener);
	}
	
}
