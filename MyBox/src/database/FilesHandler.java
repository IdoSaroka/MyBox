package database;
/**
 *Project MyBox - Software Engineering Lab 2015
 *@author Ido Saroka 300830973
 *@author Ran Azard 300819190
 *@author Sagi Sulimani 300338878
 *@author Shimon Ben Alol 201231818
**/

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

import entities.FileToView;
import entities.FileOwnerViewer;
import entities.GOI;
import entities.User;
import files.Browse;
import files.MyFile;
import files.Save;
import ocsf.server.ConnectionToClient;
import database.*;

/**This Class contains MyBox functions responsible for the
 * creation and the sharing of the files stored on the server
 * @author Ido Saroka 300830973**/

public class FilesHandler implements Serializable {

	 static ArrayList<Object> msg= new ArrayList<>();
	 static ArrayList<Object> returnMsg = new ArrayList<>();
	 static ConnectionToClient connection;
	 static Connection conn;
	 
	public FilesHandler(Object message, ConnectionToClient client,Connection con){
				this.msg =(ArrayList)message;
				this.connection = client;
				this.conn = con;
	}
	
	public static void options() throws IOException{
		System.out.println("This is sent to print from FilesHandler - options");
		returnMsg =  new ArrayList<>();
		String str = (String)msg.get(1);
		 switch(str){
		 case "UploadAFile":
			 uploadAFile((User)msg.get(2),(MyFile)msg.get(3));
			 break;
			 
		 case "DownloadAFile":
			 downloadUserFile((User)msg.get(2),(FileOwnerViewer)msg.get(3));
			 break;
			 
		 case "ReturnFileOwnerFiles":
			 returnUseFile((User)msg.get(2));
			 break;
			 
		 case "AddSharedFileToGOI":
			 break;
			 
		 case "ChangeFilePermission":
			 changeFilePrivelge((User)msg.get(2), (FileOwnerViewer)msg.get(3),(int)msg.get(4));
			 break;
	     
		 case "DeleteAFile":
			 deleteAFile((User)msg.get(2),(FileOwnerViewer)msg.get(3));
			 break;
			 
		 default:
			 LogHandling.logging("Error: User selected an invalid search option:" + (String)msg.get(1));
			 returnMsg.add("Error: Invalid Selection");
			 connection.sendToClient(returnMsg);
			 break;
		 }
	}

	public static void serachUserFile(User userName, MyFile file, GOI goi){
		
	}
	
	
	public static void addSharedFileToGOI(){
		
	}
	   /**returnUseFile - this function will return the user all the files he currently has 
	    * @author Ido Saroka 300830973
		* @param userName - a User Object used to determine the user is actually the file owner
	    * <p>
	    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
	    * @throws SQLException - the function will throw an IOException in case there will be a problem writing to the the Database
	    * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
	    * @exception IOException e - the function will throw an INExpcetion in case there will be a problem  returning the file to the user
	    * **/ 
	public static void returnUseFile(User userName) throws IOException{
		PreparedStatement statement = null;
		FileOwnerViewer fileToReturn;
		ResultSet rs = null;
		 try{	
			 //security checks - to ensure that it is the user trying to access the system
			 statement = conn.prepareStatement("SELECT * From Users WHERE userName = ?");
			 statement.setString(1, userName.getUserName());
			 rs = statement.executeQuery();
			 //check that the user exists in the system
			 if(!rs.isBeforeFirst()){
				 LogHandling.logging("Authorization Violation: Illegal Access was blocked!");
				 returnMsg.add("MyBox Encountered an error, Please Contact Technical Support!");
				 rs.close();
				 connection.sendToClient(returnMsg);
			 }	 
			 if(!(userName.getPassword().equals(rs.getString(2)))){
				 LogHandling.logging("Authorization Violation: Illegal Access was blocked! - Incorrect Password");
				 returnMsg.add("MyBox Encountered an error, Please Contact Technical Support!");
				 rs.close();
				 connection.sendToClient(returnMsg);
			 }
			 statement = conn.prepareStatement("SELECT * From files WHERE file_Owner = ?");
			 statement.setString(1, userName.getUserName());
			 rs = statement.executeQuery();
			 if(!rs.isBeforeFirst()){
				 returnMsg.add(false);
				 System.out.println("You have no files in the system!");
				 return;
			 }
			 returnMsg.add(true);
			 while(rs.next()){
				 System.out.println();
				/* fileToReturn = new FileToView(null,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)
						 ,rs.getString(7),null);*/
				 fileToReturn = new FileOwnerViewer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),
						 rs.getString(7));
				 returnMsg.add(fileToReturn);
				 connection.sendToClient(returnMsg);
			 } 
			 rs.close();
			
		 }catch(SQLException | IOException e){
			 LogHandling.logging("Error: "+ userName +" Encountered a problem while trying to retrieve his files");
			   LogHandling.logging(e.getMessage());
			   e.printStackTrace(); 
			   returnMsg.add("MyBox Encountered an Error!");
			   returnMsg.add("Please Contact Technical Support");
			   connection.sendToClient(returnMsg);
		 }
	}
	
	   /**uploadAFile - this function will be used by the user to upload a file into the MyBox System
	    * @author Ido Saroka 300830973
		* @param userName - a User Object used to determine the user is actually the file owner
		* @param file - the file the user wishes to upload
	    * <p>
	    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
	    * @throws SQLException - the function will throw an IOException in case there will be a problem writing to the the Database
	    * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
	    * @exception IOException e - the function will throw an INExpcetion in case there will be a problem saving the file in the server
	    * **/ 
	public static void uploadAFile(User userName, MyFile file) throws IOException{		
		PreparedStatement statement = null;
		ResultSet rs = null;
		File newFile;	
		User temp=null;
		String path = "C:\\MyBox\\Files\\"+userName.getUserName();
		try{
		//Check that the path the user inputed exists and it is a folder (User can not save a file on another file..)

		File f = new File(path+"\\"+file.getName()+"."+file.getSuffix());
		if( f.isDirectory() ){
			 LogHandling.logging("Error - User: "+ file.getOwner() +" Ecnounterd a problem saving file: "+file.getName()+"."+file.getSuffix()+" to path: "+path);
			 LogHandling.logging("Error: Can not save a directory in MyBox!");
			 returnMsg.add(false);
			 returnMsg.add("Error:  Can not save a directory in MyBox!");
			 connection.sendToClient(returnMsg);
			 rs.close();
			 return;
		}
		/*check that the file privilege is a legal value (1,2 or 3)*/
		if( ( 1 > file.getPrivelege() ) || ( file.getPrivelege() > 3) ){
			 LogHandling.logging("Error - User: "+ file.getOwner() +"Ecnounterd a problem saving file: "+file.getName()+"."+file.getSuffix()+" to path:"+path);
			 LogHandling.logging("Error:  Illegal privilege level");
			 returnMsg.add(false);
			 returnMsg.add("Error: Invalid privilege level");
			 connection.sendToClient(returnMsg);
			 rs.close();
		}
		 
		 statement = conn.prepareStatement("SELECT * WHERE file_Name = ? AND suffix = ? AND file_Owner = ? AND virtual_path = ?");
		 statement.setString(1, file.getName()); 
		 statement.setString(2, file.getSuffix());
		 statement.setString(3, file.getOwner());
		 statement.setString(4, path);
		 rs=statement.executeQuery();
		  
		 //checks that the User did not already uploaded this file to MyBox in this specific location
		 if(rs.isBeforeFirst()){
			 LogHandling.logging("Error - User:"+ file.getOwner() +"Ecnounterd a problem saving file: "+file.getName()+"."+file.getSuffix()+" to path:"+path);
			 LogHandling.logging("Error: File allready Exists");
			 returnMsg.add(false);
			 returnMsg.add("Error File allready exist");
			 connection.sendToClient(returnMsg);
			 rs.close();
			 return;
		 }
		
		 Save save=new Save(file,path+"\\");
	
		 /*Adding the file to the database and copying it to the user directory*/
		 statement = conn.prepareStatement("INSERT INTO Files (file_Name,suffix,file_Owner,virtual_path,privilege_level,description) VALUES (?,?,?,?,?,?)");
		 statement.setString(1, file.getName());
		 statement.setString(2, file.getSuffix());	
		 statement.setString(3, file.getOwner() );	
		 statement.setString(4, path);
		 statement.setInt(5, file.getPrivelege());
		 statement.setString(6, file.getDescription());
		 statement.executeUpdate();
		 
		 //check if the user is defined as a file owner or not
		 if(userName.getrole().equals("User")){
			 statement = conn.prepareStatement("UPDATE Users SET role = 'FileOwner' WHERE userName = ?");
			 statement.setString(1, userName.getUserName()); 
			 statement.executeQuery();
			 //return the new user object to the user
			 statement = conn.prepareStatement("SELECT * FROM  Users WHERE userName= ?");
			 statement.setString(1, userName.getUserName()); 
			 rs = statement.executeQuery();
			 temp=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
		 }
		 rs.close();
		 //if the files privilege is 3 then make the file available to all the users in the system
		 if(file.getPrivelege() == 3){
			 statement = conn.prepareStatement("SELECT * FROM Files WHERE file_Name =? AND suffix = ? AND file_Owner = ? AND virtual_path = ?");
			 statement.setString(1, file.getName());
			 statement.setString(2, file.getSuffix());
			 statement.setString(3, file.getOwner());
			 statement.setString(4, path);
			 rs = statement.executeQuery();
			 if(!rs.isBeforeFirst()){			 
				 LogHandling.logging("File: "+file.getName()+"."+file.getSuffix()+" Was not succesfuliy updated in Files Database");
				 LogHandling.logging("Owner: "+file.getOwner());
				 returnMsg.add(false);
				 returnMsg.add("File was not successfuliy uploaded to MyBox, Please contact Techincal Supoort");			
				 connection.sendToClient(returnMsg);
				 rs.close();
			 }
			 rs.next();
			 int fileId = rs.getInt(1);
			 statement = conn.prepareStatement("INSERT INTO FilesInGOI (GOI_id,file_id,file_Name,suffix,file_Owner,virtual_path,description,canEdit) VALUES (?,?,?,?,?,?,?,?)");
			 statement.setInt(1, 0);
			 statement.setInt(2, fileId);
			 statement.setString(3, file.getName());
			 statement.setString(4, file.getSuffix());
			 statement.setString(5, file.getOwner());
			 statement.setString(6, path);
			 statement.setString(7, file.getDescription());
			 statement.setInt(8, 0);
			 statement.executeUpdate(); 
		 }
		 returnMsg.clear();
		 returnMsg.add(true);
		 returnMsg.add("You have successfully uploaded the file " + file.getName() + " To your virtual MyBox space!");
		 returnMsg.add(temp);
		 LogHandling.logging("User: "+ file.getOwner() +" Added the file: "+file.getName()+"."+file.getSuffix()+" to path: "+path);
		 connection.sendToClient(returnMsg);
		 
		}
		 catch(SQLException | IOException e){
			   LogHandling.logging("Error: "+ userName +" Encountered a problem while trying to upload a file " + file.getName());
			   LogHandling.logging("Error: given path is: " + path );
			   LogHandling.logging(e.getMessage());
			   e.printStackTrace(); 
			   returnMsg.add("MyBox Encountered an Error! \n Please Contact Technical Support");
			   connection.sendToClient(returnMsg);
		 }
		 
		}
	
	
	public static void retriveAFile(User userName,int fileID){
		/**Do we want the function to receive an int or a MyFile Object **/
		
	}
	
	  /**downloadUserFile - this function will be used by the file owner to download his \ hers files
	    * @author Ido Saroka 300830973
		* @param userName - a User Object used to determine the user is actually the file owner
		* @param file - the requested file to download
	    * <p>
	    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
	    * @throws SQLException - the function will throw an IOException in case there will be a problem writing to the the Database: "Users"
	    * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
	    * @exception IOException e - the function will throw an IOException in case there will be a problem sending the file back to the user
	    * **/ 
	public static void downloadUserFile(User userName, FileOwnerViewer file) throws IOException{
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		String fullPath=file.getVirtualPath()+"\\"+file.getFileName()+"."+file.getFileSuffix();
		
		File f = new File(fullPath);
		try{
			
		if( !f.exists() ){
			 LogHandling.logging("Error - User: "+ file.getFileOwner()+"Ecnounterd a problem downloading file: "+file.getFileName()+file.getFileSuffix()+ " from path:"+file.getVirtualPath());
			 LogHandling.logging("Error: File does not exist");
			 returnMsg.add(false);
			 returnMsg.add("Error: File specified does not exist");
			 connection.sendToClient(returnMsg);
			 rs.close();
			 return;
	   	}
		if(f.isDirectory()){
			LogHandling.logging("Error - User: "+ file.getFileOwner()+"Ecnounterd a problem downloading file: "+file.getFileName()+file.getFileSuffix()+ " from path:"+file.getVirtualPath());
			LogHandling.logging("Error: File is a Direcotry");
			returnMsg.add(false);
			returnMsg.add("Error: Cann't download a folder!");
			connection.sendToClient(returnMsg);
			rs.close();
			return;
		}
		//check that it is a file owner trying to access the file
		if((Security.checkFileOwner(userName,file.getFileID(),file.getFileOwner() ,conn)) == false){
			LogHandling.logging("Illegal Access was trying by user :" + userName.getUserName());
			returnMsg.add(false);
			returnMsg.add("MyBox Encountered an Error!");
			returnMsg.add("Please Contact Technical Support");
			connection.sendToClient(returnMsg);
			rs.close();
		}
		Browse newBrowse = new Browse(f, file.getFileName(),file.getFileSuffix());
		returnMsg.add(true);
		MyFile down = newBrowse.getFile();
	
		returnMsg.add(down);
		connection.sendToClient(returnMsg);
		
		}catch(SQLException | IOException e){
			 LogHandling.logging("Error: "+ userName +" Encountered a problem while trying to download a file: " + file.getFileName());
			 LogHandling.logging("Error: given path is: " + file.getVirtualPath());
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add("MyBox Encountered an Error!");
			 returnMsg.add("Please Contact Technical Support");
			 connection.sendToClient(returnMsg);
		}
		
	}
	
	public static void changeFilePrivelge(User userName, FileOwnerViewer fileToChange,int newPrivelgeLevel) throws IOException{
		ArrayList<Integer> groupIdsToInform = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		HashSet<String> usersToInform = new HashSet<String>();
		DateFormat dateFormat;
		Date date = new Date();
		String time;
		try{
			 statement= conn.prepareStatement("SELECT FROM Files Where file_id = ?");
			 statement.setInt(1, fileToChange.getFileID());
			 
			if(fileToChange.getPrivilege() == newPrivelgeLevel){
			    LogHandling.logging("Error - User: "+ fileToChange.getFileOwner()+"Ecnounterd a problem Changing the file: "+fileToChange.getFileName()+fileToChange.getFileSuffix()+ " Privelge level");
			    LogHandling.logging("Reason: Privelge level is the current file privelge level");
				returnMsg.add(false);
				returnMsg.add("Error: File is allready at the requested privelge level");
				connection.sendToClient(returnMsg);
			}
			 //check that the user is the owner of the file - otherwise it is an unauthorized access
			 if((Security.checkFileOwner(userName, fileToChange.getFileID(), fileToChange.getFileOwner(), conn))==false){
				 LogHandling.logging("Illegal Access was trying by user :" + userName.getUserName());
				    returnMsg.add(false);
					returnMsg.add("MyBox Encountered an Error!");
					returnMsg.add("Please Contact Technical Support");
					connection.sendToClient(returnMsg);
					rs.close();
			 }
			 statement = conn.prepareStatement("UPDATE Files SET privilege_level =? WHERE file_id=?");
			 statement.setInt(1, newPrivelgeLevel);
			 statement.setInt(2, fileToChange.getFileID());
			 if(fileToChange.getPrivilege() == 1){			 
				 //Add the file to FilesInGOI as available to read by all users
				 if(newPrivelgeLevel == 3){
					 statement = conn.prepareStatement("INSERT INTO FilesInGOI (GOI_id,file_id,file_Name,suffix,file_Owner,virtual_path,description,canEdit) VALUES (?,?,?,?,?,?,?,?)");
					 statement.setInt(1, 0);
					 statement.setInt(2, fileToChange.getFileID());
					 statement.setString(3, fileToChange.getFileName());
					 statement.setString(4, fileToChange.getFileSuffix());
					 statement.setString(5, fileToChange.getFileOwner());
					 statement.setString(6, fileToChange.getVirtualPath());
					 statement.setString(7, fileToChange.getFileDescription());
					 statement.setInt(8, 0);
					 statement.executeUpdate(); 
				 }
			 }
			 if(fileToChange.getPrivilege() == 2){
				 if(newPrivelgeLevel == 3){
					 statement = conn.prepareStatement("INSERT INTO FilesInGOI (GOI_id,file_id,file_Name,suffix,file_Owner,virtual_path,description,canEdit) VALUES (?,?,?,?,?,?,?,?)");
					 statement.setInt(1, 0);
					 statement.setInt(2, fileToChange.getFileID());
					 statement.setString(3, fileToChange.getFileName());
					 statement.setString(4, fileToChange.getFileSuffix());
					 statement.setString(5, fileToChange.getFileOwner());
					 statement.setString(6, fileToChange.getVirtualPath());
					 statement.setString(7, fileToChange.getFileDescription());
					 statement.setInt(8, 0);
					 statement.executeUpdate(); 
				 }
				 if(newPrivelgeLevel == 1){
					//find all the users that the file is associated with 
					 //Note that in case the file is shared with the same user from two different groups he will only be notified once 
					 for (int var : groupIdsToInform){
						  statement = conn.prepareStatement("SELECT * From UsersInGOI Where GOI_id = ?");
						  statement.setInt(1, var); 
						  rs = statement.executeQuery();  
						  //add all the users (Only unique names will added - in order to avoid duplicate messages to the same user)
						  while(rs.next()){
							  usersToInform.add(rs.getString(2));
						  }	  
					 }
					 //Receives the current date in order to save inside the message
					 dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");	 
					 time=dateFormat.format(date);
					 //Insert the message about deletion to all relevant users
					 for (String user : usersToInform) {
						 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");	
						 statement.setString(1, time);
						 statement.setString(2, user);
						 statement.setString(3, "File: "+ fileToChange.getFileName() +" Is now a private File , Access is now limitd to it:\n "
							 		+ "Please Delete the file from you virtual MyBox Workspace");
						 statement.executeQuery();
						}
						//Delete the file listing from FilesInGOI (File will only appear once if he is shared will all the Groups in the system
						statement = conn.prepareStatement("DELETE FROM FilesInGOI Where file_id = ?");
						statement.setInt(1, fileToChange.getFileID());
						statement.executeQuery();
					    //End of if statement (Privilege level == 3)
			     	 }
				 }//end of handling for old privilege level = 2
			 if(fileToChange.getPrivilege() == 3){
				 if(newPrivelgeLevel == 1){
					 statement = conn.prepareStatement("SELECT * From Users");
						statement.executeQuery();
						//save all the users in MyBox
						while(rs.next()){
							  usersToInform.add(rs.getString(1));
						  }	
					   //Receives the current date in order to save inside the message
					    dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");	 
						time=dateFormat.format(date);
						//Insert the message about deletion to all relevant users
						for (String user : usersToInform) {
							 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");	
							 statement.setString(1, time);
							 statement.setString(2, user);
							 statement.setString(3, "File: "+ fileToChange.getFileName()+ fileToChange.getFileSuffix()+" Is now a private File , Access is now limitd to it:\n "
								 		+ "Please Delete the file from you virtual MyBox Workspace");
						}
						//Delete the file listing from FilesInGOI (File will only appear once if he is shared will all the Groups in the system
						statement = conn.prepareStatement("DELETE FROM FilesInGOI Where GOI_id = 0 AND file_id = ?");
						statement.setInt(1, fileToChange.getFileID());
						statement.executeQuery();
					}//End of if statement (Privilege level == 3) 
		     }
			 } catch(SQLException | IOException e){
			 LogHandling.logging("Error: "+ userName.getUserName() +" Encountered a problem while trying to change the file: " + fileToChange.getFileName() + fileToChange.getFileSuffix()+" Privelge level");
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add("MyBox Encountered an Error!");
			 returnMsg.add("Please Contact Technical Support");
			 connection.sendToClient(returnMsg);
		}
	}
	
	
	public static void deleteAFile(User userName,FileOwnerViewer fileToDelete) throws IOException{
		
		String fullPath=fileToDelete.getVirtualPath()+"\\"+fileToDelete.getFileName()+"."+fileToDelete.getFileSuffix();
		String time;
		File f = new File(fullPath);
		
		ArrayList<Integer> groupIdsToInform = new ArrayList<>();
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;

		//HashSet is used in order to avoid inserting duplicate entries to the array
		HashSet<String> usersToInform = new HashSet<String>();
		DateFormat dateFormat;
		Date date = new Date();
		ResultSet rs = null;
		try{
			
			
			//checkFileOwner(User userToCheck,int fileId,String listedOwnerOfTheFile,Connection con) 
			if((Security.checkFileOwner(userName,fileToDelete.getFileID(),fileToDelete.getFileOwner(),conn))==false){
				returnMsg.add(false);
				returnMsg.add("MyBox Encountered a problem deleting the file: "+fileToDelete.getFileName() +"\n please contact Techincal Support!");
			}
			//checking that the file exists
			if( !f.exists() ){
				 LogHandling.logging("Error - User: "+ fileToDelete.getFileOwner()+"Ecnounterd a problem deleting the file: "+fileToDelete.getFileName()+fileToDelete.getFileSuffix()+ " from path:"+fileToDelete.getVirtualPath());
				 LogHandling.logging("Error:  File does not exist");
				 returnMsg.add(false);
				 returnMsg.add("Error: Cann't Delete the file "+ fileToDelete.getFileName() +" specified does not exist");
				 connection.sendToClient(returnMsg);
				 rs.close();
				 return;
		   	}
			//checking that the file is not a directory
			if(f.isDirectory()){
				LogHandling.logging("Error - User: "+ fileToDelete.getFileOwner()+"Ecnounterd a problem deleting the file: "+fileToDelete.getFileName()+fileToDelete.getFileSuffix()+ " from path:"+fileToDelete.getVirtualPath());
				LogHandling.logging("Error: User tried to delete a Direcotry using 'deleteAFile'");
				returnMsg.add(false);
				returnMsg.add("Error: Cann't delete a folder using this button, please use the delete a folder option.");
				connection.sendToClient(returnMsg);
				rs.close();
				return;
			}
			//delete the file and handle the case where a problem occurred during the deletion 
			if(f.delete() == false){
				LogHandling.logging("Error - User: "+ fileToDelete.getFileOwner()+"Ecnounterd a problem deleting the file: "+fileToDelete.getFileName()+fileToDelete.getFileSuffix()+ " from path:"+fileToDelete.getVirtualPath());
				LogHandling.logging("Error: System failed to delete the file - f.delete encounterd a problem");
				returnMsg.add(false);
				returnMsg.add("Error: Could not Delete the file "+ fileToDelete.getFileName() +" MyBox encounterd an internal Error!\n "
						+ "Please Contact Technical Support");
				connection.sendToClient(returnMsg);
			}
			if(fileToDelete.getPrivilege() == 2){ //Handling the case where the file is shared with some of the Group of interests but not all of them (Privilege level = 2) 
			 statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE file_id = ?");// <- change
			 statement.setInt(1, fileToDelete.getFileID());
			 rs = statement.executeQuery();
			 while(rs.next()){
				 groupIdsToInform.add(rs.getInt(1)); // get all the id's of the GOI the file is shared with
			 }
			 //find all the users that the file is associated with 
			 //Note that in case the file is shared with the same user from two different groups he will only be notified once 
			 for (int var : groupIdsToInform){
				  statement = conn.prepareStatement("SELECT * From UsersInGOI Where GOI_id = ?");
				  statement.setInt(1, var); 
				  rs = statement.executeQuery();  
				  //add all the users (Only unique names will added - in order to avoid duplicate messages to the same user)
				  while(rs.next()){
					  usersToInform.add(rs.getString(2));
				  }	  
			 }
			 //Receives the current date in order to save inside the message
			 dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");	 
			 time=dateFormat.format(date);
			 //Insert the message about deletion to all relevant users
			 for (String user : usersToInform) {
				 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");	
				 statement.setString(1, time);
				 statement.setString(2, user);
				 statement.setString(3, "File: "+ fileToDelete.getFileName() +" Was deleted by his file Owner: "+userName.getUserName()+"\n "
				 		+ "Please Delete the file from you virtual MyBox Workspace");
				 statement.executeQuery();
				}
			    
			//Remove the file listings from the "FilesInGOI" Database
			 for (int var : groupIdsToInform){
					statement = conn.prepareStatement("DELETE FROM FilesInGOI Where GOI_id = ? AND file_id = ?");
					statement.setInt(1, var);
					statement.setInt(2, fileToDelete.getFileID());
					statement.executeQuery();
				  }
			}//End of if statement (Privilege level == 2)
			
			if(fileToDelete.getPrivilege() == 3){//Handling the case where the file is shared with all the users in MyBox
				statement = conn.prepareStatement("SELECT * From Users");
				statement.executeQuery();
				//save all the users in MyBox
				while(rs.next()){
					  usersToInform.add(rs.getString(1));
				  }	
			   //Receives the current date in order to save inside the message
			    dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");	 
				time=dateFormat.format(date);
				//Insert the message about deletion to all relevant users
				for (String user : usersToInform) {
					 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");	
					 statement.setString(1, time);
					 statement.setString(2, user);
					 statement.setString(3, "File: "+ fileToDelete +" Was deleted by his file Owner: "+userName.getUserName()+"\n "
					 		+ "Please Delete the file from you virtual MyBox Workspace");
				}
				//Delete the file listing from FilesInGOI (File will only appear once if he is shared will all the Groups in the system
				statement = conn.prepareStatement("DELETE FROM FilesInGOI Where GOI_id = 0 AND file_id = ?");
				statement.setInt(1, fileToDelete.getFileID());
				statement.executeQuery();
			}//End of if statement (Privilege level == 3)
			
			//Remove the file listings from the "Files" Database
			statement = conn.prepareStatement("DELETE FROM Files Where file_id = ? AND file_Name = ? AND suffix = ? AND file_Owner = ?");
			statement.setInt(1, fileToDelete.getFileID());
			statement.setString(2, fileToDelete.getFileName());
			statement.setString(3, fileToDelete.getFileSuffix());
			statement.setString(4, fileToDelete.getFileOwner());
			statement.executeQuery();
			
			//return the message to the file owner
			returnMsg.add(true);
			returnMsg.add("File: "+fileToDelete.getFileName() +" Was successfully removed from your virtual MyBox Space");
			connection.sendToClient(returnMsg);
			
		}catch(SQLException | IOException e){
			 LogHandling.logging("Error: "+ userName +" Encountered a problem while trying to delete a file: " + fileToDelete.getFileName());
			 LogHandling.logging("Error: given path is: " + fileToDelete.getVirtualPath());
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add("MyBox Encountered an Error!");
			 returnMsg.add("Please Contact Technical Support");
			 connection.sendToClient(returnMsg);
		}
	}
	
	
	
}
