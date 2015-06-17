package controllers;

import java.io.IOException;
import java.util.ArrayList;

import main.MyBoxGUI;

public class GOIController {

	
	ArrayList<Object> message = new ArrayList<>();
	
	/**
	 * Empty constructor
	 */
	public GOIController(){
		
	}
	
	/**
	 * used to get GOI entity in order to edit, change etc
	 * @param GOI id, primary key
	 * @throws IOException 
	 */
	public void getGOI(String GOI) throws IOException{
		message.clear();
		message.add("getGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	
	
	/**
	 * checks if a specific user in a specific GOI
	 * @param user name, primary key for users, and GOI id, PK for GOI
	 * @throws IOException 
	 */
	public void isUserInGOI (String GOI, String user ) throws IOException{
		message.clear();
		message.add("isUserInGOI");
		message.add(GOI);
		message.add(user);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	
	
	/**
	 * Used to check if a specific GOI exit
	 * @param GOI id is primary key in GOI table
	 * @throws IOException 
	 */
	public void isGOIExist(String GOI) throws IOException{
		message.clear();
		message.add("isGOIExist");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	
	/**
	 * Used to search for GOI or GOIs
	 * @param 
	 * @throws IOException 
	 */
	public void searchGOI(String param, String option) throws IOException{
		message.clear();
		message.add("GOIBasic");
		message.add("Search");
		message.add(MyBoxGUI.getUserName());
		message.add(option);
		message.add(param);
		MyBoxGUI.getClient().sendToServer(message);
	}
}
