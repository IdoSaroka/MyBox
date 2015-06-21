package database;

/**
 *Project MyBox - Software Engineering Lab 2015
 *@author Ido Saroka 300830973
 *@author Ran Azard 300819190
 *@author Sagi Sulimani 300338878
 *@author Shimon Ben Alol 201231818
**/
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
		 String str = (String)msg.get(1);
		   switch(str){
		   
		   default:
			   LogHandling.logging("Error: User"+(String)msg.get(2)+ "selected an invalid search option");
			   returnMsg.add("Error: Invalid Selection");
			   client.sendToClient(returnMsg);
			   break;
		   }
	}
	
	public static void createAFolder(User userName){
		
	}
	
	public static void deleteAFolder(User userName , Folder folderToDelete){
		/**Important add a condition about deleting the user's folder in MyBox**/
		
	}
	
	public static void returnFilesAndFolders(User userName){
		
	}
	
	

}
