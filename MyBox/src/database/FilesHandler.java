package database;
/**
 *Project MyBox - Software Engineering Lab 2015
 *@author Ido Saroka 300830973
 *@author Ran Azard 300819190
 *@author Sagi Sulimani 300338878
 *@author Shimon Ben Alol 201231818
**/

import java.io.File;
import java.io.FileNotFoundException;
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

import entities.FileToView;
import entities.FileOwnerViewer;
import files.Browse;
import files.MyFile;
import files.Save;
import ocsf.server.ConnectionToClient;

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
		String str = (String)msg.get(1);
		 switch(str){
		 case "UploadAFile":
			 try {
				uploadAFile((MyFile)msg.get(2));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 break;
			 
		 case "DownloadAFile":
			 downloadUserFile((String)msg.get(2),(FileOwnerViewer)msg.get(3));
			 break;
			 
		 case "ReturnFileOwnerFiles":
			 returnUseFile((String)msg.get(2));
			 break;
			 
		 
		 default:
			 break;
		 }
	}

	public static void serachUserFile(){
		
	}
	
	
	public static void uploadAFile(MyFile file) throws SQLException, IOException{
		/**Impotent ask shimon to send me the user type**/
		System.out.println(file.getName()+"\n");
		PreparedStatement statement = null;
		ResultSet rs = null;
		File newFile;	
		
		/*Check that the path the user inputed exists and it is a folder*/
		File f = new File(file.getPath());
		if(! (f.exists() && f.isDirectory() )){
			 LogHandling.logging("Error - User:"+ file.getOwner() +"Ecnounterd a problem saving file: "+file.getName()+file.getSuffix()+" to path:"+file.getPath());
			 LogHandling.logging("Error:  Illegal Path");
			 returnMsg.add(false);
			 returnMsg.add("Error: Illegal Path");
			 connection.sendToClient(returnMsg);
			 rs.close();
			 return;
		}
		/*check that the file privilege is a legal value (1,2 or 3)*/
		if( ( 1 > file.getPrivelege() ) || ( file.getPrivelege() > 3) ){
			 LogHandling.logging("Error - User:"+ file.getOwner() +"Ecnounterd a problem saving file: "+file.getName()+file.getSuffix()+" to path:"+file.getPath());
			 LogHandling.logging("Error:  Illegal privilege level");
			 returnMsg.add(false);
			 returnMsg.add("Error: Invalid privilege level");
			 connection.sendToClient(returnMsg);
			 rs.close();
		}
        
		 statement = conn.prepareStatement("SELECT file_Name,suffix,file_Owner From Files WHERE file_Name = ? AND suffix = ? AND file_Owner = ? AND virtual_path = ?");
		 statement.setString(1, file.getName()); 
		 statement.setString(2, file.getSuffix());
		 statement.setString(3, file.getOwner());
		 statement.setString(4, file.getPath());
			

		 rs=statement.executeQuery();
		 if(rs.isBeforeFirst()){
			 LogHandling.logging("Error - User:"+ file.getOwner() +"Ecnounterd a problem saving file: "+file.getName()+file.getSuffix()+" to path:"+file.getPath());
			 LogHandling.logging("Error: File allready Exists");
			 returnMsg.add(false);
			 returnMsg.add("Error File allready exist");
			 connection.sendToClient(returnMsg);
			 rs.close();
			 return;
		 }
		 
		 String path=null;
		 Save save=new Save(file,path);
	
		 
		 /*Adding the file to the database and copying it to the user directory*/
		 statement = conn.prepareStatement("INSERT INTO Files (file_Name,suffix,file_Owner,virtual_path,privilege_level,description) VALUES (?,?,?,?,?,?)");
		 statement.setString(1, file.getName());
		 statement.setString(2, file.getSuffix());	
		 statement.setString(3, file.getOwner() );	
		 statement.setString(4, file.getPath() );
		 statement.setInt(5, file.getPrivelege());
		 statement.setString(6, file.getDescription());
		 statement.executeUpdate();
		 returnMsg.add(true);
		 LogHandling.logging("User:"+ file.getOwner() +"Added the file: "+file.getName()+file.getSuffix()+" to path:"+file.getPath());
		 connection.sendToClient(returnMsg);
		}
	
	
	public static void retriveAFile(String userName,int fileID){
		/**Do we want the function to receive an int or a MyFile Object **/
		
	}
	
	
	public static void downloadUserFile(String userName, FileOwnerViewer file){
		System.out.println(file.getFileName()+"\n");
		PreparedStatement statement = null;
		ResultSet rs = null;
        
		File f = new File(file.getVirtualPath());
		try{
		if( !f.exists() ){
			 LogHandling.logging("Error - User:"+ file.getFileOwner()+"Ecnounterd a problem downloading file: "+file.getFileName()+file.getFileSuffix()+ " from path:"+file.getVirtualPath());
			 LogHandling.logging("Error:  File does not exist");
			 returnMsg.add(false);
			 returnMsg.add("Error: File specified does not exist");
			 connection.sendToClient(returnMsg);
			 rs.close();
			 return;
	   	}
		if(f.isDirectory()){
			LogHandling.logging("Error - User:"+ file.getFileOwner()+"Ecnounterd a problem downloading file: "+file.getFileName()+file.getFileSuffix()+ " from path:"+file.getVirtualPath());
			LogHandling.logging("Error:  File is a Direcotry");
			returnMsg.add(false);
			returnMsg.add("Error: Cann't download a folder!");
			connection.sendToClient(returnMsg);
			rs.close();
			return;
		}
		File fileToDownload = new File(file.getVirtualPath());
		Browse newBrowse = new Browse(fileToDownload);
		MyFile down =newBrowse.getFile();
		returnMsg.add(true);
		returnMsg.add(down);
		connection.sendToClient(returnMsg);
		
		}catch(SQLException e){
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void changeFilePermission(String userName, MyFile fileToChange){
		
	}
	
	public static void deleteAFile(MyFile fileToDelete){
		/**impoertent ask shimon to send me the user's role in the system**/
		
		/**impoertent add the appropriate messages to all the user's the file is shared with**/
	}
	
	   /**returnUseFile - this function will return the user all the files he currently 
	    * @author Ido Saroka 300830973
		* @param userName - the 
	    * @param decision - the Admin's decision regarding this specific request (Approved, Declined or illegal input)
		* @param client - the function will receive a connection to the client
	    * <p>
	    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
	    * @throws SQLException - the function will throw an IOException in case there will be a problem writing to the the Database: "Users"
	    * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
	    * @exception IOException e - 
	    * **/ 
	public static void returnUseFile(String userName){
		PreparedStatement statement = null;
		FileOwnerViewer fileToReturn;
		ResultSet rs = null;
		 try{
			 statement = conn.prepareStatement("SELECT * From files WHERE file_Owner = ?");
			 statement.setString(1, userName);
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
			 
		 }catch(SQLException e){
			 
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
