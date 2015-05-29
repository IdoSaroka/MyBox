package entities;




public class Login {
	
	
	private String userName;
	private String password;
	
	/**
	 * Login constructor is used for initial login for the user
	 */
	public  Login(String userName, String password){
		this.password = password;
		this.userName = userName;
	}
	
	/**
	 * Login getter, is used for getting user name out of this class
	 */
	public  String getUserName(){
		return userName;
	}
	/**
	 * Login getter, is used for getting user's password out of this class
	 */
	public  String getPassword(){
		return password;
	}

}
