
package controllers;
import main.MyBoxGUI;



public class LoginController {
	

	private String userName;

	private String password;
	
	public  LoginController(){
		password=MyBoxGUI.getPassword();
		userName=MyBoxGUI.getUserName();
	}
	
	
	
	
}
