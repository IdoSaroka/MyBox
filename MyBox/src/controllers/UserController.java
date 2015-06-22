package controllers;
import java.awt.Desktop;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

















import javax.swing.JOptionPane;

import main.MyBoxGUI;
import entities.FileToView;
import entities.GOI;
import entities.User;
import files.*;
import guic.SharedFilessPage;
import guic.UploadFilePage;
import guic.UserPage;

/**
* Project MyBox - Software Engineering Lab 2015 - Group no.2
* Ido Saroka 300830973
* Ran Azard 300819190
* Sagi Sulimani 300338878
* Shimon Ben Alol 201231818
*/

/**
*Class UserController:  describe the user actions
*@author Shimon Ben-Alul 201231818
*/
public class UserController {

	ArrayList<Object> message = new ArrayList<>();
	private MyFile file;
	private String filePath;
	private String fileName;
	private String fileSuffix;
	ArrayList<FileToView> filesToView = new ArrayList<>();
	

	/**
	 * Empty constructor
	 */
	public UserController(){
	}
	
	/**
	 *  user searching for files shared with him
	 * @param option
	 * @param GOI
	 * @param userpage
	 * @throws IOException
	 */
	public void serachSharedFiles(String option, String GOI,UserPage userpage) throws IOException{
		message.clear();
		message.add("GOIBasic");
		message.add("ShoeGoiFiles");
		message.add(MyBoxGUI.getUser());
		message.add(option);
		message.add(GOI);//parameter for search
		MyBoxGUI.getClient().sendToServer(message);
		filesToView.clear();
		ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
		if((boolean)msg.get(0)==true){
			String str = (String)msg.get(1);
			JOptionPane.showMessageDialog(userpage,str);
			for(int i=1; i<msg.size();i++){
				filesToView.add((FileToView)msg.get(i)); 
			}
		}
		else{
			String str = (String)msg.get(1);
			JOptionPane.showMessageDialog(userpage,str);
		}		
	}
	
	public ArrayList<FileToView> getFilesToView(){
		return filesToView;
	}
	
	/**
	 * user sends a request to join group of interest 
	 * @param GOIID id is the primary key for GOI table
	 * @throws IOException 
	 * 
	 * 
	 */
	public void requestToJoinGOI(GOI goi,String description) throws IOException{
		message.clear();
		message.add("GOIBasic");
		message.add("MakeARequestToJoin");
		message.add(MyBoxGUI.getUserName());
		message.add(goi.getName());
		message.add(description);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	
	public void LeaveGOI(GOI goi) throws IOException{
		message.clear();
		message.add("GOIBasic");
		message.add("RemoveAUserFromGOI");
		message.add(MyBoxGUI.getUser());
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
		
	}
	
	/**
	 * user sends a request to join group of interest 
	 * @param filePath Primary Key for files table
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
	 * @param GOIName is the subject of the GOI
	 * @throws IOException 
	 */
	public void searchGOI(String GOIName) throws IOException{
		message.clear();
		message.add("searchGOI");
		message.add(GOIName);
		MyBoxGUI.getClient().sendToServer(message);
	}

	/**
	 * Creates the file that will be uploaded
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
	 * Upload the file created in uploadFile method
	 * @param uploadfilepage
	 * @param description
	 * @param privelege
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
	 * @param fileSend is the file to send to the server
	 * @throws IOException 
	 */
	public void getSharedFile(FileToView fileSend) throws IOException{
		message.clear();
		message.add("GoiBasic");
		message.add("DownloadSharedFile");
		message.add(MyBoxGUI.getUser());
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
		
	/**
	 * allow the user to open the file he wants from his library
	 * @param fileSend
	 * @throws IOException
	 */
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
	 * @param uploadfilepage for viewing in the right window
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
	
	public void getMSG() throws IOException{
		message.clear();
		message.add("RetreiveMessages");
		message.add(MyBoxGUI.getUserName());		
		MyBoxGUI.getClient().sendToServer(message);
	}

}
