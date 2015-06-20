package controllers;
import java.awt.Desktop;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;














import javax.swing.JOptionPane;

import main.MyBoxGUI;
import entities.FileToView;
import entities.User;
import files.*;
import guic.UploadFilePage;


public class UserController {

	ArrayList<Object> message = new ArrayList<>();
	private MyFile file;
	private String filePath;
	private String fileName;
	private String fileSuffix;
	ArrayList<Object> filesToView = new ArrayList<>();

	/**
	 * Empty constructor
	 */
	public UserController(){
	}
	
	/**
	 * user searching for files shared with him
	 * @param String that define the file name
	 * @throws IOException 
	 */
	public void serachSharedFiles(String option, String GOI) throws IOException{
		message.clear();
		message.add("GOIBasic");
		message.add("ShoeGoiFiles");
		message.add(option);
		message.add(GOI);//parameter for search
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
		this.filePath = b.getPath();
		this.fileName = b.getName();
		this.fileSuffix = b.getSuffix();
	}
	
	/**
	 * allow  user to become a file owner
	 * @param uploadfilepage 
	 * @param filePath, Primary Key for files table
	 * @throws IOException 
	 */
	public void sendFile(UploadFilePage uploadfilepage, String description, int privelege) throws IOException{
		message.clear();
		file.setDescription(description);
		file.setPrivelege(privelege);
		file.setOwner(MyBoxGUI.getUserName());
		message.add("File");
		message.add("UploadAFile");
		message.add(MyBoxGUI.getUser());
		message.add(file);
		
		MyBoxGUI.getClient().sendToServer(message);
		ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
		if((boolean)msg.get(0)==true){
			System.out.println(msg.get(1));
			String str = (String)msg.get(1);
			JOptionPane.showMessageDialog(uploadfilepage,str);
			if(MyBoxGUI.getUser().getrole().equals("User")){
				MyBoxGUI.setUser((User)msg.get(2));
			}
		}
		else{
			String str = (String)msg.get(1);
			JOptionPane.showMessageDialog(uploadfilepage,str);
		}
		/*
		 * To-Do
		 * if file succesful, changes user role to fileOwner
		 */
	}
	/**
	 * allow  user to download files
	 * @param String file id to send the server
	 * @throws IOException 
	 */
	public void getSharedFile(FileToView fileSend) throws IOException{
		message.clear();
		message.add("GoiBasic");
		message.add("DownloadSharedFile");
		message.add(fileSend);
		MyBoxGUI.getClient().sendToServer(message);
		ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
		if((boolean)msg.get(0)){
			file = (MyFile)msg.get(1);
			System.out.println(file.getName());
			String path="C:\\MyBox\\Downloaded Files";
			Save save=new Save(file,path);
		}
		else{
			System.out.println("Unable to save file");
		}
	}
		
	public void openSharedFile(FileToView fileSend) throws IOException{
		message.clear();
		message.add("GoiBasic");
		message.add("DownloadSharedFile");
		message.add(fileSend);
		MyBoxGUI.getClient().sendToServer(message);
		ArrayList<Object> msg =  (ArrayList) MyBoxGUI.getClient().getMessage();
		if((boolean)msg.get(0)==true){
			file = (MyFile)msg.get(1);
			System.out.println(file.getName());
			String path="C:\\MyBox\\Downloaded Files";
			Save save=new Save(file,path);
			 try {  
				    File fileOpen = new File(path+"\\"+file.getName()+"."+file.getSuffix());  
				    if (fileOpen.exists()) {  		     
				     if (Desktop.isDesktopSupported()) {  
				      Desktop.getDesktop().open(fileOpen);  
				     } 
				     else  
				      System.out.println("Awt Desktop is not supported!");  
				    } else  
				     System.out.println("File is not exists!");  
				    System.out.println("Done");  
			   } catch (Exception ex) {  
				    ex.printStackTrace();  
			   } 
		}
		else{
			System.out.println("Unable to open file");
			/*
			 * TO-Do
			 * what happens if the file can't be downloaded
			 */
		}
		
		
		//To-Do
	}
	
	/**
	 * users with the right permission will be able to update the file
	 * @param UploadFilePage for viewing in the right window
	 * @throws IOException 
	 */
	public void updateFile(UploadFilePage uploadfilepage) throws IOException{
		message.clear();
		uploadFile();
		message.add("updateFile");
		message.add(file);
		MyBoxGUI.getClient().sendToServer(message);
		ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
		String str = (String)msg.get(0);
		JOptionPane.showMessageDialog(uploadfilepage,str);
		//if file doesnt exist, an appropriate msg appears
		//if the file updated, the user will note it
	}

}
