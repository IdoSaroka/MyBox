package database;

/**
 *Project MyBox - Software Engineering Lab 2015
 *@author Ido Saroka 300830973
 *@author Ran Azard 300819190
 *@author Sagi Sulimani 300338878
 *@author Shimon Ben Alol 201231818
**/
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import ocsf.server.ConnectionToClient;
import entities.FileOwnerViewer;
import entities.FileToView;
import entities.User;
import entities.Folder;



  /**This Class contains MyBox functions relevant for the "Folder structure  
   * @author Ido Saroka 300830973**/
	
public class FolderHandler implements Serializable{
	
	 static ArrayList<Object> msg= new ArrayList<>();
	 
	 static ArrayList<Object> returnMsg = new ArrayList<>();
	  
	 static ConnectionToClient client;
	   
	 static Connection conn;
	 
	public FolderHandler(Object message, ConnectionToClient client,Connection con) {
		this.msg =(ArrayList)message;
		this.client = client;
		this.conn = con;
	}
	
	
	public static void options() throws IOException{
	  	 returnMsg =  new ArrayList<>();
	  	 System.out.println("Got to Folder Handler options");
		 String str = (String)msg.get(1);
		   switch(str){
		   
		   case "DisplayCurrentDirectories":
			   returnFilesAndFolders((User)msg.get(2));
			   break;
		   
		   default:
			   LogHandling.logging("Error: User"+(String)msg.get(2)+ "selected an invalid search option");
			   returnMsg.add("Error: Invalid Selection");
			   client.sendToClient(returnMsg);
			   break;
		   }
	}
	
	public static void createAFolder(User userName, String folderToCreate){
		
		
	}
	
	public static void deleteAFolder(User userName, String folderToDelete){
			    //deleteDirectory(new File(args[0]));
	}

  /* private static boolean deleteDirectory(File path) {
			    if( path.exists() ) {
			      File[] files = path.listFiles();
			      for(int i=0; i<files.length; i++) {
			         if(files[i].isDirectory()) {
			           deleteDirectory(files[i]);
			         }
			         else {
			        	 try {
							deleteAFile((User)msg.get(2),(FileOwnerViewer)msg.get(3));
						} catch (IOException e) {
							e.printStackTrace();
						}
			         }
			      }
			    }
			    returnMsg.add("You have succesfuliy deleted the folder");
			    client.sendToClient(returnMsg);
	 }*/
		
	private static void deleteAFile(User userName,FileOwnerViewer fileToDelete) throws IOException{
		System.out.println("Got to delete a file");
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
			/*if((Security.checkFileOwner(userName,fileToDelete.getFileID(),fileToDelete.getFileOwner(),conn))==false){
				returnMsg.add(false);
				returnMsg.add("MyBox Encountered a problem deleting the file: "+fileToDelete.getFileName() +"\n please contact Techincal Support!");
			}*/
			//checking that the file exists
			
		    System.out.println("if f.exists - test");
			if( !f.exists() ){
				 LogHandling.logging("Error - User: "+ fileToDelete.getFileOwner()+"Ecnounterd a problem deleting the file: "+fileToDelete.getFileName()+fileToDelete.getFileSuffix()+ " from path:"+fileToDelete.getVirtualPath());
				 LogHandling.logging("Error:  File does not exist");
				 //returnMsg.add(false);
				 returnMsg.add("Error: Cann't Delete the file "+ fileToDelete.getFileName() +" specified does not exist");
				 client.sendToClient(returnMsg);
				 rs.close();
				 return;
		   	}
			//checking that the file is not a directory
			if(f.isDirectory()){
				LogHandling.logging("Error - User: "+ fileToDelete.getFileOwner()+"Ecnounterd a problem deleting the file: "+fileToDelete.getFileName()+fileToDelete.getFileSuffix()+ " from path:"+fileToDelete.getVirtualPath());
				LogHandling.logging("Error: User tried to delete a Direcotry using 'deleteAFile'");
				//returnMsg.add(false);
				returnMsg.add("Error: Cann't delete a folder using this button, please use the delete a folder option.");
				client.sendToClient(returnMsg);
				rs.close();
				return;
			}
			//delete the file and handle the case where a problem occurred during the deletion 
			boolean temp=f.delete();
			if(temp==false){
				LogHandling.logging("Error - User: "+ fileToDelete.getFileOwner()+"Ecnounterd a problem deleting the file: "+fileToDelete.getFileName()+fileToDelete.getFileSuffix()+ " from path:"+fileToDelete.getVirtualPath());
				LogHandling.logging("Error: System failed to delete the file - f.delete encounterd a problem");
				//returnMsg.add(false);
				returnMsg.add("Error: Could not Delete the file "+ fileToDelete.getFileName() +" MyBox encounterd an internal Error!\n "
						+ "Please Contact Technical Support");
				client.sendToClient(returnMsg);
				return;
			}
			System.out.println("File is deleted");
			if(fileToDelete.getPrivilege() == 2){ //Handling the case where the file is shared with some of the Group of interests but not all of them (Privilege level = 2) 
			 statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE file_id = ?");// <- change
			 statement.setInt(1, fileToDelete.getFileID());
			 rs = statement.executeQuery();
			 while(rs.next()){
				 System.out.println("Group to inform: ");
				 groupIdsToInform.add(rs.getInt(1)); // get all the id's of the GOI the file is shared with
			 }
			 
			 //find all the users that the file is associated with 
			 //Note that in case the file is shared with the same user from two different groups he will only be notified once 
			 for (Integer var : groupIdsToInform){
				  statement = conn.prepareStatement("SELECT * From UsersInGOI Where GOI_id = ?");
				  statement.setInt(1, var); 
				  rs = statement.executeQuery();  
				  //add all the users (Only unique names will added - in order to avoid duplicate messages to the same user)
				  while(rs.next()){
					  System.out.println(rs.getString(2));
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
				 statement.executeUpdate();
				}
			    
			//Remove the file listings from the "FilesInGOI" Database
			 for (Integer var : groupIdsToInform){
					statement = conn.prepareStatement("DELETE FROM FilesInGOI Where GOI_id = ? AND file_id = ?");
					statement.setInt(1, var);
					statement.setInt(2, fileToDelete.getFileID());
					statement.executeUpdate();
				  }
			}//End of if statement (Privilege level == 2)
			
			if(fileToDelete.getPrivilege() == 3){//Handling the case where the file is shared with all the users in MyBox
				statement = conn.prepareStatement("SELECT * From Users");
				System.out.println("Insert priv 3");
				rs = statement.executeQuery();
				//save all the users in MyBox
				while(rs.next()){
					  usersToInform.add(rs.getString(1));
				  }	
				System.out.println("Ended the while");
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
					 statement.executeUpdate();
				}
				System.out.println("ended the for loop");
				//Delete the file listing from FilesInGOI (File will only appear once if he is shared will all the Groups in the system
				statement = conn.prepareStatement("DELETE FROM FilesInGOI Where GOI_id = 0 AND file_id = ?");
				statement.setInt(1, fileToDelete.getFileID());
				statement.executeUpdate();
			}//End of if statement (Privilege level == 3)
			 System.out.println("Reached the deletion condition");

			//Remove the file listings from the "Files" Database
			statement = conn.prepareStatement("DELETE FROM Files Where file_id = ? AND file_Name = ? AND suffix = ? AND file_Owner = ?");
			statement.setInt(1, fileToDelete.getFileID());
			statement.setString(2, fileToDelete.getFileName());
			statement.setString(3, fileToDelete.getFileSuffix());
			statement.setString(4, fileToDelete.getFileOwner());
			statement.executeUpdate();
			 System.out.println("Ended the deletion condition");
			//check if user has more files
			statement = conn.prepareStatement("SELECT * From Files Where file_Owner = ?");
			statement.setString(1, userName.getUserName());
			rs = statement.executeQuery();
			
			System.out.println("check if no files condition");
			if(!rs.isBeforeFirst()){
				User user=new User(userName.getUserName(),userName.getPassword(),userName.getEmail(),"User");
				returnMsg.add(user);
				statement = conn.prepareStatement("UPDATE Users SET role = 'User' WHERE userName = ?");
				statement.setString(1, userName.getUserName());
				statement.executeUpdate();
				System.out.println("end upadATE user");
			}
			
			//return the message to the file owner
			//returnMsg.add(true);
			returnMsg.add("File: "+fileToDelete.getFileName() +" Was successfully removed from your virtual MyBox Space");
			client.sendToClient(returnMsg);
			return;
			
		}catch(SQLException | IOException e){
			 LogHandling.logging("Error: "+ userName +" Encountered a problem while trying to delete a file: " + fileToDelete.getFileName());
			 LogHandling.logging("Error: given path is: " + fileToDelete.getVirtualPath());
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add("MyBox Encountered an Error!");
			 returnMsg.add("Please Contact Technical Support");
			 client.sendToClient(returnMsg);
			 return;
		}
	}
	
	
	  /**returnFilesAndFolders - this function will be used by the file owner to reterive his \ hers files and folders in MyBox
	    * @author Ido Saroka 300830973
		* @param userName - a User Object used to determine the user is actually the file owner
	    * <p>
	    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
	    * @throws SQLException - the function will throw an IOException in case there will be a problem writing to the the Database: "Users"
	    * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
	    * @exception IOException e - the function will throw an IOException in case there will be a problem sending the file back to the user
	    * **/ 
	public static void returnFilesAndFolders(User userName) throws IOException{
		System.out.println("got to returnFilesAndFolders");
		File currentDir = new File("C:\\MyBox\\Files\\" + userName.getUserName() );
		
		System.out.println("The path is: "+ currentDir.getAbsolutePath());
		System.out.println("The path is: "+ currentDir.getCanonicalPath());
		
		displayDirectoryContents(currentDir);
		System.out.println("Ended the use of display directory");
		try {
			for(int i=0;i<returnMsg.size();i++){
				System.out.println(returnMsg.get(i));
			}
			client.sendToClient(returnMsg);
			return;
		} catch (IOException e) {
			 LogHandling.logging("Error: "+ userName.getUserName() +" Encountered a problem while trying to Retrieve his file");
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			// returnMsg.add(false);
			 returnMsg.add("MyBox Encountered an Error!");
		     returnMsg.add("Please Contact Technical Support");
		     client.sendToClient(returnMsg);
		     return;
		}
	}
	
	private static void displayDirectoryContents(File usersDirectory) throws IOException{
		System.out.println("got to displayDirectoryContents");
		int i=0;
		try {
			System.out.println("before file");
			File[] files = usersDirectory.listFiles();
			
			System.out.println("after file");
			
			for (File file : files) {
				System.out.println("This is run: " + i++ );
				if (file.isDirectory()) {
					//in case it is a directory
					//System.out.print(b);
					String temp=file.getCanonicalPath();
					returnMsg.add(temp);
					displayDirectoryContents(file);
				} else {
					//in case it is a file
					String temp=file.getCanonicalPath();
					returnMsg.add(temp);
							
				}				
			}
			System.out.println("This is before ending run: " + i );
			return;
		} catch (IOException e) {
			 LogHandling.logging("Error: the user has Encountered a problem while trying to Retrieve his file");
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			// returnMsg.add(false);
			 returnMsg.add("MyBox Encountered an Error!");
		     returnMsg.add("Please Contact Technical Support");
		     client.sendToClient(returnMsg);
		     return;
		}
	}
	
	//returns to the user if a folder allready exist
	private static boolean isFOlderExist(File usersDirectory){
		return (usersDirectory.isDirectory());
	}
	

}
