
package controllers;
import java.io.IOException;
import java.util.ArrayList;

import entities.Login;
import main.MyBoxGUI;


/**
* Project MyBox - Software Engineering Lab 2015 - Group no.2
* Ido Saroka 300830973
* Ran Azard 300819190
* Sagi Sulimani 300338878
* Shimon Ben Alol 201231818
*/

/**
*Class LoginController: send the login details to the server for authorization
*@author Shimon Ben-Alul 201231818
*/
public class LoginController {
	

	ArrayList<Object> message = new ArrayList<>();
	Login user;
	

	/**
	 * constructor for Login for the User
	 * @param userName
	 * @param password
	 */
	public LoginController(String userName, String password){
		 
		user=new Login(userName,password);
	}
	
	/**
	 * sendLogin getting user name and password and send a message
	 * to the server in order to confirm this user
	 * @throws IOException 
	 */
	public void sendLogin() throws IOException{
		message.clear();
		message.add("Login");
		message.add(user);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * @return String 
	 * that represent the user and password
	 */
	@Override
	public String toString(){
		return "Login " + user.getUserName() +" "+ user.getPassword() + "\n";
	}
}
