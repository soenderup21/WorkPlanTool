import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

public class CreateUser extends JFrame {
	private JPanel containerPanel;
	private JLabel promtLabel;
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton createUserButton;
	
	public CreateUser(String title) {
super(title);
		
		containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		promtLabel = new JLabel("Enter login details:");
		
		usernameField = new JTextField(0);
		passwordField = new JPasswordField(0);
		passwordField.setEchoChar('*');
		
		createUserButton = new JButton("Create User");
		
		containerPanel.add(promtLabel);
		containerPanel.add(usernameLabel);
		containerPanel.add(usernameField);
		containerPanel.add(passwordLabel);
		containerPanel.add(passwordField);
		containerPanel.add(createUserButton);
		
		this.add(containerPanel);
		
		setSize(200, 150);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createUserButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 User user = new User(usernameLabel.getText(), passwordField.getPassword());			
				 FileManager.saveUser(user);
				 user.Login(passwordField.getPassword());
				 if (user.getLoginState()) {
					 Window window = new Window(user, title);
					 dispose();	
				 }
			  } 
			} );
		
	}
	
}
