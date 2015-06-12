package controllers;

import java.io.IOException;

import entities.GOI;
import entities.GoiRequest;
import main.MyBoxGUI;

public class SysAdminController extends FileOwnerController {
	
	/**
	 * Empty constructor
	 */
	public SysAdminController(){
		super();
	}
	
	/**
	 * System administrator chooses weather to allow or deny a request from users
	 * @param boolean answer if to accept or decline
	 * @throws IOException 
	 */
	public void approveRequest(boolean answer) throws IOException{
		message.clear();
		message.add("approveRequest");
		if(answer==true)
			message.add("Approve");
		else
			message.add("Decline");
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to delete a GOI
	 * @param String that represent the GOI
	 * @throws IOException 
	 */
	public void removeGOI(String GOI) throws IOException{
		message.clear();
		message.add("removeGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to add/create a GOI
	 * @param String that represent the GOI
	 * @throws IOException 
	 */
	public void addGOI(GOI goi) throws IOException{
		message.clear();
		message.add("addGOI");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to change exist GOI
	 * @param String that represent the GOI
	 * @throws IOException 
	 */
	public void editGOI(GOI goi) throws IOException{
		message.clear();
		message.add("editGOI");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to search for a specific user
	 * @param String that represent the user
	 * @throws IOException 
	 */
	public void searchUser(String user) throws IOException{
		message.clear();
		message.add("searchUser");
		message.add(user);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to remove for a specific user from GOI
	 * @param String that represent the user and GOI
	 * @throws IOException 
	 */
	public void removeUser(String user, String goi) throws IOException{
		message.clear();
		message.add("removeUser");
		message.add(user);
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to reciew files in GOI
	 * @param String that represent the GOI
	 * @throws IOException 
	 */
	public void filesInGOI(String GOI) throws IOException{
		message.clear();
		message.add("filesInGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
}
