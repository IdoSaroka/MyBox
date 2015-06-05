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
public class GoiBasic {
	
   static ArrayList<Object> msg= new ArrayList<>();
   static ArrayList<String> returnMsg = new ArrayList<>();
  
   static ConnectionToClient client;
   
   static Connection conn;
 		
   public GoiBasic(Object message, ConnectionToClient client,Connection con) {
		this.msg =(ArrayList)message;
		this.client = client;
		this.conn = con;
   }
   
   
   /**options - will direct the user's request to the appropriate function
    * in the system
    * @author Ido Saroka 300830973
    * <p>
    * @exception SQLException e -
    * @exception IOException e -
    * **/   
   public static void options() throws IOException{
	   Scanner sc = new Scanner(System.in);
	   String str = (String)msg.get(1);
	   switch(str){
	              /****
	               * Should the user chooses perform a search in the GOI database
	               */
	              case "Search":
	            	  searchAGOI((String)msg.get(2),(String)msg.get(3));
	              break;
		          
	              case "ShowGoiFiles":
	            	  break;
	              
	              case "MakeARequestToJoin": 
	            	  makeARequest();
			      break; 
	              
			      
			      default:
			        LogHandling.logging("Error: User selected an invalid search option");
				    returnMsg.add("Error: Invalid Selection");
			      break;
	   }
	   
   }
   
   
   /**searchAGOI - will be responsible for all the search options in the GOI database
    * @author Ido Saroka 300830973
    * @param option - the search option the user wishes the search to be performed by
    * @param searchParameter - the parameter by which to perform the search
    * <p>
    * @exception SQLException e -
    * @exception IOException e - 
    * **/   
     public static void searchAGOI(String option,String searchParameter) throws IOException{
		Statement stmt = null;
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * From goi");	
			switch(option){
			/**
	    	    * Columns Description:
	    	    * rs.getString(1) - GOI ID
	    	    * rs.getString(2) - GOI Name
	    	    * rs.getString(3) - GOI Subject
	    	    * rs.getString(4) - Date Created
	    	    * rs.getString(5) - Total Number Of Users
	    	    * rs.getString(6) - Current Number Of Users
	    	    * **/
	
			  /**
		       * Handles a search by name in the GOI database
		       * **/
			case "Name":
			    while(rs.next()){ 
				   if( searchParameter.equals(rs.getString(2)) ){ /**GOI Name**/
                      returnMsg.add(rs.getString(1) +" "+ rs.getString(2) +" "+rs.getString(3)+
			    			   " "+rs.getString(4)+" "+rs.getString(5)+ " "+rs.getString(6)); 
				 	   return;
				 }
				 returnMsg.add("No Such GOI in the system");
			    }
			    System.out.println("No Such GOI in the system");
			    /**
			     * Handles a search by subject in the GOI database
			     * **/
			 case "Subject":
				while(rs.next()){ 
					if( searchParameter.equals(rs.getString(3)) ){ /**GOI Subject**/
						 returnMsg.add(rs.getString(1) +" "+ rs.getString(2) +" "+rs.getString(3)+
				    			   " "+rs.getString(4)+" "+rs.getString(5)+ " "+rs.getString(6)); 
						 flag = true;
					}
				}
				if(!flag)
					returnMsg.add("There Are currentliy No Goi's with this subject in MyBox!");
			 break;
			  /**
			    * Returns the user all the GOIs that currently exist in the system
			    * **/
			 case "All":
				    while(rs.next()){	   
				    	   returnMsg.add(rs.getString(1) +" "+ rs.getString(2) +" "+rs.getString(3)+
				    			   " "+rs.getString(4)+" "+rs.getString(5)+ " "+rs.getString(6));
				       }
			 break;
			 /**
			  * Default case - handles all the invalid selections should they occur
			  * **/
			 default:
				 LogHandling.logging("Error: User selected an invalid search option");
				 returnMsg.add("Error: Invalid Selection");
		     break;
			}
			
		}
		 catch (SQLException e) {
			 LogHandling.logging("Error: Encountered a problem while searching in the GOI Database");
			 LogHandling.logging("Serach Parmeter: " + option +"Serach Index: " + searchParameter);
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add("MyBox Encounterd an Error!");
		     returnMsg.add("Please Contact Technical Support");
		}
     }   
     
     public static void makeARequest(){
    	 
     }

}
