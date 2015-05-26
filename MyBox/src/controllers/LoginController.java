
package controllers;
import main.MyBoxGUI;



public class LoginController {
	

	@SuppressWarnings("unused")
	private String userName;
	@SuppressWarnings("unused")
	private String password;
	
	public  LoginController(){
		password=MyBoxGUI.getPassword();
		userName=MyBoxGUI.getUserName();
	}
	
	
	
	
}
