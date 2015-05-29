package controllers;

import java.io.IOException;
import java.util.ArrayList;

import main.MyBoxGUI;


public class UserController {

	ArrayList<Object> message = new ArrayList<>();
	

	/**
	 * user send a request to view files shared with him/her
	 * @param filePath, Primary Key for files table
	 * @throws IOException 
	 */

	public void serachSharedFiles(String filePath) throws IOException{
		message.clear();
		message.add("searchSharedFiles");
		message.add(filePath);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * user sends a request to join group of interest 
	 * @param GOI id is the primary key for GOI table
	 * @throws IOException 
	 * 
	 * 
	 */
	public void requestToJoinGOI(String GOI) throws IOException{
		message.clear();
		message.add("requestToJoinGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * user sends a request to join group of interest 
	 * @param filePath, Primary Key for files table
	 * @throws IOException 
	 * 
	 */
	public void editSharedFile(String filePath) throws IOException{
		message.clear();
		message.add("editSharedFile");
		message.add(filePath);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	
	/**
	 * user sends a request to search GOI by subject 
	 * @param GOI is the subject of the GOI
	 * @throws IOException 
	 * 
	 */
	public void searchGOI(String GOI) throws IOException{
		message.clear();
		message.add("searchGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}

	/**
	 * allow  user to become a file owner
	 * @param filePath, Primary Key for files table
	 * @throws IOException 
	 */
	/*////////////////////Will be in use when class file will be added
	public void uploadFile(File file) throws IOException{
		message.clear();
		message.add("uploadFile");
		message.add(file);
		MyBoxGUI.getClient().sendToServer(message);
	}*/
}
