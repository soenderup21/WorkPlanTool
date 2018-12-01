
public class Main
{
	private static CreateUser createUser;
	private static LoginScreen LoginFrame;
	private static String title = "WorkPlanTool 0.1.1";
	public static void main(String[] args)
	{
		if ((FileManager.getUsers().length < 1)) {
			createUser = new CreateUser("Create User");
		}
		else {
			LoginFrame = new LoginScreen(title);   
		}
	}
	public static void InitializeWithUser(User user) {
		Window window = new Window(user, title);
		if (createUser == null) {
			LoginFrame.dispose();
		}
		else {
			createUser.dispose();
		}
	}
  
}
