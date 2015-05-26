
package controllers;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.MyBoxGUI;



public class LoginController {
	
	private String userName;
	private String password;
	
	public  LoginController(){
		password=MyBoxGUI.getPassword();
		userName=MyBoxGUI.getUserName();
		//System.out.println(userName + " " +password + " " + port+ " " + IP);
	}
	
	
	
	
}
