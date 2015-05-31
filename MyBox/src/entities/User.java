package entities;
/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	* Ido Saroka 300830973
	* Ran Azard 300819190
	* Sagi Sulimani 300338878
	* Shimon Ben Alol 201231818
*/

/**
	*Class User: class User includes all the variables described in class diagram
    *@author Sagi Sulimani 300338878
*/

public class User
{
	private String userName;
	private String email;
	private String password;
	private String role;
	
/**
	* Constructor of the User class
	* @param String userName
	* @param String email
	* @param String password
*/
	public User(String userName, String email, String password,String role)
	{
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role=role;
	}
	
	public User(User user){
		userName = user.getUserName();
		email = user.getEmail();
		password = user.getPassword();
		role=user.getrole();
	}

	
/**
	* @return String of UserName
*/
	public String getUserName()
	{
		return userName;
	}

/**
	* @return String of Password
*/	
	public String getPassword()
	{
		return password;
	}

/**
	* @return String of Email
*/	
	public String getEmail()
	{
		return email;
	}

	/**
	* @return String of Role
*/	
	public String getrole()
	{
		return role;
	}
/**
	* @param String UserName
	* @return 'true' if the user is logged in, else return 'false'
*/
	public boolean isLoggedIn(String UserName)
	{
		
		return true; 
		
		//return false; 
	}

/**
	* @return 'true' if saving the data succeeded, else return 'false'.
*/
	public boolean saveInDB()
	{
		
		return true; 
		
		//return false;
	}
}
