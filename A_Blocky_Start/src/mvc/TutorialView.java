package mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */
public class TutorialView implements View {
	
	private Dimension viewSize = Main.getGameFrameDimension();
	
	// Contains all the tutorial prompts
	private Dimension tutorialContainerSize = new Dimension(800, 500);
	
	private Dimension backButtonSize = new Dimension(30, 30);
	
	private Dimension tutorialTitleSize = new Dimension(120,36);
	
	
	private Point containerLocation = new Point(0,50);
	
	private Point backButtonLocation = new Point(10, 10);
	
	private Point tutorialTitleLocation = new Point(375,10);
	
	
	private JPanel contentContainer = new PanelUI(containerLocation, tutorialContainerSize, Color.white);
	private ButtonUI backButton = new ButtonUI(backButtonLocation, backButtonSize, "", IconUI.backButtonIcon);
	private JLabel tutorialTitle = new LabelUI(tutorialTitleLocation, tutorialTitleSize, "Tutorial");
	
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
		
		contentContainer.setBackground(IconUI.lightOrange);
		
		rootPanel.add(backButton);
		rootPanel.add(contentContainer);
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
