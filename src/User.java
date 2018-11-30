import java.security.*;

public class User
{
	private String Username;
	private String pwdHash;
	private boolean LoginState = false;
	
	/**
	 * Constructor that takes username and password when creating a new user
	 * @param Username
	 * @param Password
	 */
	public User(String Username, String Password) {
		this.Username = Username;
		this.pwdHash = Hasher(Password);
	}
	
	/**
	 * Hashes the string input which is likely to be the password
	 * @param Input
	 * @return 		returns hashed (SHA-256) input
	 */
	private String Hasher(String Input) {
		try
		{
			byte[] PasswordBytes = Input.getBytes("Latin1");
			MessageDigest md = MessageDigest.getInstance("SHA-256");	
			md.update(PasswordBytes);
			return byteArrayToString(md.digest(PasswordBytes));
		}
		catch (Exception e)
		{
			System.out.println("Error: Hashing failure");
			return null;
		}
	}
	/**
	 * Checks password and returns a value that determines access
	 * @param pwd
	 * @return 	returns boolean that indicates access granted
	 */
	public boolean CheckPwd(String pwd) {
		String Hash = Hasher(pwd);
		if (Hash.equals(null))
		{
			return false;
		}
		if (Hasher(pwd).equals(pwdHash))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Set password using old and new password
	 * @param newPassword
	 * @param oldPassword
	 */
	public void setpassword(String newPassword, String oldPassword) {
		if (CheckPwd(oldPassword))
		{
			String Hash = Hasher(newPassword);
			pwdHash = Hash;	
		}
	}
	
	/**
	 * Set username using new username and password
	 * @param newUsername
	 * @param Password
	 */
	public String getUsername() {
		return this.Username;
	}
	
	public void setUsername(String newUsername, String Password) {
		if (CheckPwd(Password))
		{
			this.Username = newUsername;
		}
	}
	
	/**
	 * Converts bytearray to String
	 * @param _bytes
	 * @return
	 */
	private String byteArrayToString(byte[] _bytes)
	{
	    String file_string = "";

	    for(int i = 0; i < _bytes.length; i++)
	    {
	        file_string += (char)_bytes[i];
	    }

	    return file_string;    
	}
	public boolean getLoginState() {
		return this.LoginState;
	}
	
	
	public void Login(char[] Password) {
		String password = Password.toString();
		if (Hasher(password) == this.pwdHash) {
			this.LoginState = true;
		}
	}
}
