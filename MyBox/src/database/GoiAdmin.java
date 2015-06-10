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
import ocsf.server.ConnectionToClient;
public class GoiAdmin implements Serializable{

  /**This Class contains MyBox Advanced functions responsible for the creation and maintenance of
   * the GOIs in the system.
   * This Class will only be accessible to the system's Admin
   * @author Ido Saroka 300830973**/
	
	   static ArrayList<Object> msg= new ArrayList<>();
	   static ArrayList<Object> returnMsg = new ArrayList<>();
	  
	   static ConnectionToClient client;
	   
	   static Connection conn;
	   
	   public GoiAdmin(Object message, ConnectionToClient client,Connection con) {
			this.msg =(ArrayList)message;
			this.client = client;
			this.conn = con;
	   }
	   
	   public static void options() throws IOException{
		   Scanner sc = new Scanner(System.in);
		   String str = (String)msg.get(1);
		   switch(str){
		   
		   case "CreateGOI":
			   break;
			   
		   case "DeleteAGOI":
			   break;
			   
		   /**
		    * "RetriveCurrentRequests" - will be used
		    * **/   
		   case "RetriveCurrentRequests":
			   getRequests();
			   break;
			   
		   case "DecideAboutRequest":
			   break;
		   
		   default:
			   break;
	       }
	   }
	   
	   
	   /**getRequests - will be responsible for retrieving all of the current requests made by the user
	    * @author Ido Saroka 300830973
	    * @param userName - the user's user name in the MyBox system
	    * @param option - the search option the user wishes the search to be performed by
	    * @param searchParameter - the parameter by which to perform the search
	    * <p>
	    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
	    * @exception SQLException e 
	    * @exception IOException e  
	    * **/   
		public static void getRequests() throws IOException{
			 Statement stmt;
			   try{
			       stmt = conn.createStatement();
			       ResultSet rs = stmt.executeQuery("SELECT * FROM request;");
			       while(rs.next()){
			    	   /**
			    	    * Description:
			    	    * rs.getString(1) - Request ID
			    	    * rs.getString(2) - User Name
			    	    * rs.getString(3) - GOI Name
			    	    * rs.getString(4) - Description
			    	    * **/
			    	   returnMsg.add(rs.getString(1) +
			    			               " "+rs.getString(2) +
			    			                  " "+rs.getString(3) +
			    			                    " "+rs.getString(4));
			       }
			       rs.close();
			       client.sendToClient(returnMsg);
			   }
				/**
				 * Handling of the SQLException - 1. Saving the appropriate data in the Log
				 *                                2.The Admin will receive a notification regarding the problem
				 * 
				 * Handling of the IOException - 1. Saving the appropriate data in the Log
				 *                               2. The Admin will receive a notification regarding the problem
				 * **/                          
			   catch(SQLException | IOException e) {
				   LogHandling.logging("Error: Admin ecnounterd a problme while trying to retrive current reqeusts");
				   LogHandling.logging(e.getMessage());
				   e.printStackTrace(); 
				   returnMsg.add("MyBox Encounterd an Error!");
				   returnMsg.add("Please Contact Technical Support");
				   client.sendToClient(returnMsg);
				   } 
		}
		
		
		

}