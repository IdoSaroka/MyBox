
package controllers;
import java.io.IOException;
import java.util.ArrayList;

import entities.Login;
import main.MyBoxGUI;



public class LoginController {
	

	ArrayList<Object> message = new ArrayList<>();
	Login user;
	

	/**
	 * constructor for Login for the User
	 * @param Strings userName and Password for identify a user
	 * @throws IOException 
	 * 
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
		message.add(user.getUserName());
		message.add(user.getPassword());
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	@Override
	public String toString(){
		return "Login " + user.getUserName() +" "+ user.getPassword() + "\n";
	}
}
