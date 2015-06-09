package controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;







import javax.swing.JOptionPane;

import main.MyBoxGUI;
import files.*;
import guic.UploadFilePage;


public class UserController {

	ArrayList<Object> message = new ArrayList<>();
	MyFile file;
	

	/**
	 * user send a request to view files shared with him/her
	 * @param filePath, Primary Key for files table
	 * @throws IOException 
	 */

	public UserController(){
	}
	public void serachSharedFiles(String FileName) throws IOException{
		message.clear();
		message.add("searchSharedFiles");
		message.add(FileName);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * user sends a request to join group of interest 
	 * @param GOI id is the primary key for GOI table
	 * @throws IOException 
	 * 
	 * 
	 */
	public void requestToJoinGOI(String GOIID) throws IOException{
		message.clear();
		message.add("requestToJoinGOI");
		message.add(GOIID);
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
	 * user sends a request to search GOI by name 
	 * @param GOI is the subject of the GOI
	 * @throws IOException 
	 * 
	 */
	public void searchGOI(String GOIName) throws IOException{
		message.clear();
		message.add("searchGOI");
		message.add(GOIName);
		MyBoxGUI.getClient().sendToServer(message);
	}

	/**
	 * allow  user to become a file owner
	 * @param filePath, Primary Key for files table
	 * @throws IOException 
	 */
	public void uploadFile() throws IOException{
		Browse b = new Browse();
		this.file = b.getFile();
	}
	
	/**
	 * allow  user to become a file owner
	 * @param uploadfilepage 
	 * @param filePath, Primary Key for files table
	 * @throws IOException 
	 */
	public void sendFile(UploadFilePage uploadfilepage) throws IOException{
		message.clear();
		message.add("uploadFile");
		message.add(file);
		MyBoxGUI.getClient().sendToServer(message);
		ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
		String str = (String)msg.get(0);
		JOptionPane.showMessageDialog(uploadfilepage,str);
	}
	/**
	 * allow  user to download files
	 * @param String file path to send the server
	 * @throws IOException 
	 */
	public void getFile(String filePath) throws IOException{
		message.clear();
		message.add("getFile");
		message.add(filePath);
		MyBoxGUI.getClient().sendToServer(message);
	}

}
