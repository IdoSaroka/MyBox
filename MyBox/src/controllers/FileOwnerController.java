package controllers;

import java.io.IOException;
import java.util.ArrayList;

import entities.FileOwnerViewer;
import entities.FileToView;
import files.MyFile;
import files.Save;
import main.MyBoxGUI;

/**
* Project MyBox - Software Engineering Lab 2015 - Group no.2
* Ido Saroka 300830973
* Ran Azard 300819190
* Sagi Sulimani 300338878
* Shimon Ben Alol 201231818
*/

/**
*Class FileOwnerController: Class the actually makes all of the actions the
*user want to perform
*@author Shimon Ben-Alul 201231818
*/
public class FileOwnerController extends UserController {
	
	
	
	/**
	 * Empty constructor uses the user controller as all of
	 * the user options are available to the file owner, and extra 
	 */
	public FileOwnerController(){
		super();
	}
	

	/**
	 * User wants to delete file's he\she owns
	 * @param filePath String to represent file path
	 * @throws IOException
	 */
	public void deleteFile(String filePath) throws IOException{
		message.clear();
		message.add("deleteFile");
		message.add(filePath);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * User wants to change file path he\she owns
	 * @param file
	 * @param newPath
	 * @throws IOException
	 */
	public void setPath(String file,String newPath) throws IOException{
		message.clear();
		message.add("setPath");
		message.add(file);
		message.add(newPath);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * User wishes to change a file's permission
	 * @param file
	 * @param perm
	 * @param goi
	 * @throws IOException
	 */
	public void editPermission(String file, String perm, String goi) throws IOException{
		message.clear();
		message.add("editPermission");
		message.add(file);
		message.add(goi);
		message.add(perm);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * user wants to see file's details
	 * @param file
	 * @throws IOException
	 */
	public void retrieveFile(String file) throws IOException{
		message.clear();
		message.add("retrieveFile");
		message.add(file);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * User wishes to change a file's name
	 * @param file
	 * @param newName
	 * @throws IOException
	 */
	public void setFileName(String file,String newName) throws IOException{
		message.clear();
		message.add("setFileName");
		message.add(file);
		message.add(newName);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * User asks to delete a complete folder
	 * @param folderName
	 * @throws IOException
	 */
	public void deleteFolder(String folderName) throws IOException{
		message.clear();
		message.add("deleteFolder");
		message.add(folderName);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * Asks the server to see the files he owns
	 */
	public void myFiles(){
		message.clear();
		message.add("File");
		message.add("MyFiles");
		try {
			MyBoxGUI.getClient().sendToServer(message);
		} catch (IOException e) {
			System.out.println("Could not ask server to get owner's file");
			e.printStackTrace();
		}
	}
	
	
	/***************
	 * This is just for testing!!!!!
	 */
	
	public void testDown(){
		message.clear();
		message.add("File");
		message.add("DownloadAFile");
		message.add(MyBoxGUI.getUser());
		FileOwnerViewer dummy = new FileOwnerViewer(1,"Choclate Cookies","txt", "Shimon_Ben_Alul", "C:\\MyBox\\Files\\Shimon_Ben_Alul",3, "A chocolate chip cookies recipe");
		message.add(dummy);
		System.out.println(dummy);
		try {
			MyBoxGUI.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
		if((boolean)msg.get(0)==true){
			MyFile file = (MyFile)msg.get(1);
			System.out.println(file.getName());
			String path="C:\\MyBox\\Downloaded Files\\";
			Save save=new Save(file,path);
		}
		else{
			System.out.println("Unable to save file");
		}
	}
	
}
