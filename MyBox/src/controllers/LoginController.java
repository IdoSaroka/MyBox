
package controllers;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.MyBoxGUI;



public class LoginController {
	
	private String userName;
	private String port;
	private String IP;
	private String password;
	
	public  LoginController(){
		IP=MyBoxGUI.getIP();
		port=MyBoxGUI.getPort();
		password=MyBoxGUI.getPassword();
		userName=MyBoxGUI.getUserName();
		System.out.println(userName + " " +password + " " + port+ " " + IP);
	}
	
	
	
	
}
