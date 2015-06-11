package controllers;

import java.io.IOException;

import entities.GOI;
import main.MyBoxGUI;

public class SysAdminController extends UserController {
	
	public SysAdminController(){
		super();
	}
	
	public void approveRequest(Request req, boolean answer) throws IOException{
		message.clear();
		message.add("approveRequest");
		if(answer==true)
			message.add("Approve");
		else
			message.add("Decline");
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void removeGOI(String GOI) throws IOException{
		message.clear();
		message.add("removeGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void addGOI(GOI goi) throws IOException{
		message.clear();
		message.add("addGOI");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void editGOI(GOI goi) throws IOException{
		message.clear();
		message.add("editGOI");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void searchUser(String user) throws IOException{
		message.clear();
		message.add("searchUser");
		message.add(user);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void removeUser(String user) throws IOException{
		message.clear();
		message.add("removeUser");
		message.add(user);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void filesInGOI(String GOI) throws IOException{
		message.clear();
		message.add("filesInGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
}
