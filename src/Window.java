import javax.swing.JFrame;

public class Window extends JFrame
{
	User user;
	
	public Window(User user, String title) {
		super(title);
		this.user = user;
		if (!user.getLoginState()) {
			this.dispose();
		}
	}
}
