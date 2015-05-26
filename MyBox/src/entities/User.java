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

import java.util.*;

public class User
{
	private String UserName;
	private String UserID;
	private String Email;
	private String Password;
	
/**
	* Constructor of the User class
	* @param String userName
	* @param String userID
	* @param String email
	* @param String password
*/
	public User(String userName, String userID, String email, String password)
	{
		UserName = userName;
		UserID = userID;
		Email = email;
		Password = password;
	}
	
/**
	* @return String of UserID
*/
	public String getUserID()
	{
		return UserID;
	}
	
/**
	* @param String UserID
*/
	public void setUserID(String userID)
	{
		UserID = userID;
	}
	
/**
	* @return String of UserName
*/
	public String getUserName()
	{
		return UserName;
	}
	
/**
	* @param String UserName
*/
	public void setUserName(String userName)
	{
		UserName = userName;
	}

/**
	* @return String of Password
*/	
	public String getPassword()
	{
		return Password;
	}

/////////////////////////////////////////////////////////////////////////// מכאן צריך לערוך	
/**
	* @param String UserName
	* @return 'true' is the user is logged in, else return 'false'
*/
	public boolean isLoggedIn(String UserName)
	{
		
		return true; 
		
		//return false; 
	}

/**
	* Creates folder in a specific GOI
*/
	public void createFolder()
	{
		String path = null;
		
		//path = getCurrentPath();// לממש ולהוסיף לקלאס
		// לברר איך יוצרים תיקיה בג'אווה
	}
	
/**
	* @return 'true' if saving the data succeded, else return 'false'.
*/
	public boolean saveInDB()
	{
		
		return true; 
		
		//return false;
	}
///////////////////////////////////////////////////////////////////////////
}
