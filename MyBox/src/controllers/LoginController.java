
package controllers;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ocsf.client.*;
import Main.*;


public class LoginController {
	
	private String userName;
	private String port;
	private String IP;
	private String password;
	
	public LoginController(){
		this.IP=MyBoxGUI.getIP();
		this.port=MyBoxGUI.getPort();
		this.password=MyBoxGUI.getPassword();
		this.userName=MyBoxGUI.getUserName();
		System.out.println(IP + " " + port + " " + password + " " + userName);
	}
	
	
	
	
}
