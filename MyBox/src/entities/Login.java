package entities;

import java.io.Serializable;



/**
* Project MyBox - Software Engineering Lab 2015 - Group no.2
* Ido Saroka 300830973
* Ran Azard 300819190
* Sagi Sulimani 300338878
* Shimon Ben Alol 201231818
*/

/**
*Class Login: Creates Login to send to the server and get authorization
*@author Shimon Ben-Alul 201231818
*/
public class Login implements Serializable{
	
	
	/**
	 * implements serializable as this class will sent from the server or to the server
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	
	/**
	 * Login constructor is used for initial login for the user
	 * @param userName
	 * @param password
	 */
	public  Login(String userName, String password){
		this.password = password;
		this.userName = userName;
	}
	
	/**
	 * @return String userName
	 * Login getter, is used for getting user name out of this class
	 */
	public  String getUserName(){
		return userName;
	}
	/**
	 * @return String password
	 * Login getter, is used for getting user's password out of this class
	 */
	public  String getPassword(){
		return password;
	}

}
