import java.security.*;

public class User
{
	private String Username;
	private byte[] pwdHash;
	private boolean LoginState = false;
	
	/**
	 * Constructor that takes username and password when creating a new user
	 * @param Username
	 * @param Password
	 */
	public User(String Username, char[] Password) {
		this.Username = Username;
		this.pwdHash = Hasher(Password);
	}
	
	/**
	 * Hashes the string input which is likely to be the password
	 * @param Input
	 * @return 		returns hashed (SHA-256) input
	 */
	private byte[] Hasher(char[] Input) {
		try
		{
			byte[] PasswordBytes = Input.toString().getBytes("Latin1");
			MessageDigest md = MessageDigest.getInstance("SHA-256");	
			md.update(PasswordBytes);
			return md.digest(PasswordBytes);
		}
		catch (Exception e)
		{
			System.out.println("Error: Hashing failure: " + e);
			return null;
		}
	}
	/**
	 * Checks password and returns a value that determines access
	 * @param pwd
	 * @return 	returns boolean that indicates access granted
	 */
	public boolean CheckPwd(char[] pwd) {
		byte[] Hash = Hasher(pwd);
		if (Hash == null)
		{
			return false;
		}

		if (/*TODO Check if password matches*/ true)
		{
			System.out.println("Login Successful");
			return true;
		}
		return false;
	}
	
	/**
	 * Set password using old and new password
	 * @param newPassword
	 * @param oldPassword
	 */
	public void setpassword(char[] newPassword, char[] oldPassword) {
		if (CheckPwd(oldPassword))
		{
			byte[] Hash = Hasher(newPassword);
			pwdHash = Hash;	
		}
	}
	
	public String getUsername() {
		return this.Username;
	}
	
	public void setUsername(String newUsername, char[] Password) {
		if (CheckPwd(Password))
		{
			this.Username = newUsername;
		}
	}
	
	public boolean getLoginState() {
		return this.LoginState;
	}
	
	
	public void Login(char[] password) {
		if (CheckPwd(password)) {
			this.LoginState = true;
			return;
		}
	}
}
