import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

/**
 * LoginScreen allows the user to login to a given system
 * @author MLP
 *
 */
public class LoginScreen extends JFrame
{
	
	private JPanel containerPanel;
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel pwdLabel;
	private JPasswordField pwdField;
	private JButton loginButton;
    
	/**
	 * First screen the user will see
	 * @param title of the program
	 */
	public LoginScreen(String title) {
		super(title);
		containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		
		usernameLabel = new JLabel("Username: ");
		pwdLabel = new JLabel("Password: ");
		
		usernameField = new JTextField(10);
		pwdField = new JPasswordField(10);
		pwdField.setEchoChar('*');
		
		loginButton = new JButton("Login");
		
		containerPanel.add(usernameLabel);
		containerPanel.add(usernameField);
		containerPanel.add(pwdLabel);
		containerPanel.add(pwdField);
		containerPanel.add(loginButton);
		
		this.add(containerPanel);
		this.pack();
		
		setSize(200, 150);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE /*Change to DISPOSE_ON_CLOSE once Fully implemented*/);
		setLocationRelativeTo(null);

		loginButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String username = usernameField.getText();
				  char[] password = pwdField.getPassword();
				    User Users[] = FileManager.getUsers();
				    for (int i = 0; i < Users.length; i++) {
						if (username.equals(username)) {
							User user = Users[i];
							//gets stuck here:
							user.Login(password);
							if (user.getLoginState()) {
								FileManager.saveUser(user);
								Main.setUser(user);
							}
								System.out.println("Login Failed");	
							break;	
							
						}
					}
			  } 
			} );	
	}
	
}
