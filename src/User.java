import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.*;
import java.util.Arrays;

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
	public User(String Username, char[] Password) {
		this.Username = Username;
		this.pwdHash = Hasher(Password);
	}
	
	/**
	 * Hashes the string input which is likely to be the password
	 * @param Input
	 * @return 		returns hashed (SHA-256) input
	 */
	private String Hasher(char[] Input) {
		try
		{
			byte[] PasswordBytes = charArrayToBytes(Input);
			MessageDigest md = MessageDigest.getInstance("SHA-256");	
			md.update(PasswordBytes);
			return new BigInteger(1, md.digest()).toString(16);
		}
		catch (Exception e)
		{
			System.out.println("Error: Hashing failure: " + e);
			return null;
		}
	}
	/**
	 * takes in char[] and spits out byte[]
	 * Credit to "Conner": https://stackoverflow.com/questions/30560830/generating-an-md5-hash-with-a-char
	 * @param chars
	 * @return
	 */
	private byte[] charArrayToBytes(char[] chars) {
	    CharBuffer charBuffer = CharBuffer.wrap(chars);
	    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
	    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
	            byteBuffer.position(), byteBuffer.limit());
	    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
	    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
	    return bytes;
	}
	/**
	 * Checks password and returns a value that determines access
	 * @param pwd
	 * @return 	returns boolean that indicates access granted
	 */
	public boolean CheckPwd(char[] pwd) {
		String Hash = Hasher(pwd);
		if (Hash == null)
		{
			return false;
		}
		if (Hash.equals(this.pwdHash))
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
	public void setpassword(char[] newPassword, char[] oldPassword) {
		if (CheckPwd(oldPassword))
		{
			String Hash = Hasher(newPassword);
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
