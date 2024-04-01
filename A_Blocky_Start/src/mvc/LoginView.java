package mvc;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Yuting
 * This is the login page
 */
public class LoginView implements View {
	
	// declaring bounds for components for this panel
	private Dimension viewSize = Main.getGameFrameDimension();
	
	// root panel
	private static JPanel rootPanel;
	
	// declaring location of UI elements 
	private Point usernameLabelLocation = new Point(200, 100);
	private Point usernameTextFieldLocation = new Point(400, 100);
	private Point passwordLabelLocation = new Point(200, 200);
	private Point passwordTextFieldLocation = new Point(400, 200);
	private Point loginButtonLocation = new Point(425, 300);
	private Point registerButtonLocation = new Point(200, 300);
	private Point loginTitleLabelLocation = new Point(375,10);
	private Point reminderLabelLocation = new Point(400, 80);
	
	// declaring size of UI elements
	private Dimension labelSize = new Dimension(100,20);
	private Dimension textFieldSize = new Dimension(200,20);
	private Dimension loginButtonSize = new Dimension(150, 40);
	private Dimension loginTitleSize = new Dimension(120,36);
	private Dimension longLabelSize = new Dimension(200, 20);
	
	// declaring UI elements for this panel
	LabelUI usernameFieldLabel = new LabelUI(usernameLabelLocation, labelSize, "Username:");
	TextFieldUI usernameTextField = new TextFieldUI(usernameTextFieldLocation, textFieldSize);
	LabelUI passwordFieldLabel = new LabelUI(passwordLabelLocation, labelSize, "Password:");
	TextFieldUI passwordTextField = new TextFieldUI(passwordTextFieldLocation, textFieldSize);
	ButtonUI loginButton = new ButtonUI(loginButtonLocation, loginButtonSize, "", IconUI.loginButtonIcon);
	ButtonUI registerButton = new ButtonUI(registerButtonLocation, loginButtonSize, "", IconUI.registerButtonIcon);
	LabelUI loginTitleLabel = new LabelUI(loginTitleLabelLocation, loginTitleSize, "Login / Register");
	LabelUI reminderLabel = new LabelUI(reminderLabelLocation, longLabelSize, "Username cannot be empty");
	
	/**
	 * Constructor.
	 */
	public LoginView() {
		initPanel();
		setVisibility(false);
	}

	@Override
	public void initPanel() {
		
		// set up root panel
		rootPanel = new JPanel();
		rootPanel.setSize(viewSize);
		rootPanel.setLayout(null);		
		rootPanel.setVisible(false);
		rootPanel.setBackground(IconUI.mediumOrange);
		
		// adding UI elements
		rootPanel.add(usernameFieldLabel);
		rootPanel.add(usernameTextField);
		rootPanel.add(passwordFieldLabel);
		rootPanel.add(passwordTextField);
		rootPanel.add(reminderLabel);
		rootPanel.add(registerButton);
		rootPanel.add(loginButton);
		rootPanel.add(registerButton);
		rootPanel.add(loginTitleLabel);
		
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
	
	/**
	 * 
	 * @return the string in the username field
	 */
	public String getUsername() {
		return usernameTextField.getText();
	}
	
	/**
	 * 
	 * @return the string in the password field
	 */
	public String getPassword() {
		return passwordTextField.getText();
	}
	
	// Action Listeners

	public void loginButtonAddAction(ActionListener actionListener) {
		loginButton.addActionListener(actionListener);
	}
	
	public void registerButtonAddAction(ActionListener actionListener) {
		registerButton.addActionListener(actionListener);
	}
	
}
