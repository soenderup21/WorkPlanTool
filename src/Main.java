
public class Main
{
   public static void main(String[] args)
   {
	   //execute: Start program and login screen
	   if (!(FileManager.getUsers().length > 0)) {
		CreateUser createUser =new CreateUser("Create User");
	}
	   LoginScreen LoginFrame = new LoginScreen("WorkPlanTool 0.1.1");
	   
   }
}
