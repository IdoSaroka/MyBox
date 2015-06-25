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
import java.util.Scanner;

import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import ocsf.server.ConnectionToClient;
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
	
	public static void createAFolder(User userName, Folder folderToCreate){
		
	}
	
	public static void deleteAFolder(User userName , Folder folderToDelete){
		
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
		File currentDir = new File("C:\\MyBox\\Files\\" + userName.getUserName() + "\\");
		
		System.out.println("The path is: "+ currentDir.getPath());
		
		displayDirectoryContents(currentDir);
		System.out.println("Ended the use of display directory");
		try {
			client.sendToClient(returnMsg);
			return;
		} catch (IOException e) {
			 LogHandling.logging("Error: "+ userName.getUserName() +" Encountered a problem while trying to Retrieve his file");
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add(false);
			 returnMsg.add("MyBox Encountered an Error!");
		     returnMsg.add("Please Contact Technical Support");
		     client.sendToClient(returnMsg);
		     return;
		}
	}
	
	private static void displayDirectoryContents(File usersDirectory) throws IOException{
		System.out.println("got to displayDirectoryContents");
		try {
			File[] files = usersDirectory.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					returnMsg.add("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file);
				} else {
					returnMsg.add("     file:" + file.getCanonicalPath());
				}
			}
			return;
		} catch (IOException e) {
			 LogHandling.logging("Error: the user has Encountered a problem while trying to Retrieve his file");
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add(false);
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
