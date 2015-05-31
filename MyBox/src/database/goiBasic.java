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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import ocsf.server.ConnectionToClient;

/**This Class contains MyBox basic functions that are responsible for
 * accessing and searching inside the "Groups Of Interests" in the system.
 * @author Ido Saroka 300830973**/
public class goiBasic {
	
   ArrayList<Object> msg= new ArrayList<>();
   ArrayList<String> returnMsg = new ArrayList<>();
  
   ConnectionToClient connection;
 		
   public goiBasic(Object message, ConnectionToClient client) {
		this.msg =(ArrayList)message;
		this.connection = client;
   }
   
   
   
   public static void options(){
	   
	   
   }
   
   public static void printGOIs(Connection conn){
		 Statement stmt;
		   try{
		       stmt = conn.createStatement();
		       ResultSet rs = stmt.executeQuery("SELECT * FROM goi;");
			   System.out.println("-----------------------------------Current Gois in the system:-------------------------------------");
		       while(rs.next()){
		    	   System.out.println("GOI ID: "+rs.getString(1) +
		    			               " GOI Name: "+rs.getString(2) +
		    			                  " GOI Subject: "+rs.getString(3) +
		    			                    " Created: "+rs.getString(4) +
		    			                      " Created: "+rs.getString(5) +
		    			                        " Current Number Of Users: " + rs.getString(6) );
		       }
		       rs.close();
		       System.out.println("--------------------------------------------------------------------------------------------------\n");
		   }catch(SQLException e) {e.printStackTrace(); }  
	}
   
	

}
