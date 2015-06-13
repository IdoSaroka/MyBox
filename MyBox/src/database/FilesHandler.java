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
		 
		 default:
			 break;
		 }
	}
	//Privilege
	public static void uploadAFile(MyFile file) throws SQLException, IOException{
		System.out.println(file.getName()+"\n");
		PreparedStatement statement = null;
		ResultSet rs = null;
		File newFile;
		
	     /*input test to check that path is legal*/
		 statement = conn.prepareStatement("SELECT file_Name,suffix,file_Owner From Files WHERE file_Name = ? AND suffix = ? AND file_Owner = ? AND virtual_path = ?");
		 statement.setString(1, file.getName()); 
		 statement.setString(2, file.getSuffix());
		 statement.setString(3, file.getOwner());
		 statement.setString(4, file.getPath());
			

		 rs=statement.executeQuery();
		 if(rs.isBeforeFirst()){
			 returnMsg.add("Error File allready exist");
			 connection.sendToClient(returnMsg);
			 rs.close();
			 return;
		 }
		 
		 String path=null;
		 Save save=new Save(file,path);
		// newFile = save.saveFile(file); // <- ?
		 
		 
		 statement = conn.prepareStatement("INSERT INTO Files (file_Name,suffix,file_Owner,virtual_path,privilege_level,description) VALUES (?,?,?,?,?,?)");
		 statement.setString(1, file.getName());
		 statement.setString(2, file.getSuffix());	
		 statement.setString(3, file.getOwner() );	
		 statement.setString(4, file.getPath() );
		 statement.setInt(5, file.getPrivelege());
		 statement.setString(6, file.getDescription());

		 returnMsg.add("Success");
		 connection.sendToClient(returnMsg);
		}
	


	
}
