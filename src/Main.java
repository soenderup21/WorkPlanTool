
public class Main
{
	
	private static User user;
	private static CreateUser createUser;
	private static LoginScreen LoginFrame;
	private static String title = "WorkPlanTool 0.1.1";
	public static void main(String[] args)
	{
		//execute: Start program and login screen
		if ((FileManager.getUsers().length < 1)) {
			createUser = new CreateUser("Create User");
		}
		else {
			LoginFrame = new LoginScreen(title);   
		}
	}
	public static void setUser(User User) {
		user = User;
		createUser.dispose();
		LoginFrame.dispose();
	}
	public static void startApplication() {
		Window window = new Window(user, title);
	}
   
}
