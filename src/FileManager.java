
public class FileManager {
	
	public static User[] getUsers(){
		User user[] = new User[5];
		user[0] = new User("username", "password".toCharArray());
		return user;
	}
	
	public static void saveUser(User user) {
		
	}	
}
