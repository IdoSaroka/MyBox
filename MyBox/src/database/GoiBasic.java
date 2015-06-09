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
   static ArrayList<Object> returnMsg = new ArrayList<>();
  
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
	              /**
	               * Should the user chooses perform a search in the GOI database
	               **/
	              case "Search":
	              break;
		          
	              case "ShowGoiFiles":
	            	  searchSharedFiles((String)msg.get(2),(String)msg.get(3),(String)msg.get(4));
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
    * @param userName - the user's user name in the MyBox system
    * @param option - the search option the user wishes the search to be performed by
    * @param searchParameter - the parameter by which to perform the search
    * <p>
    * @exception SQLException e -
    * @exception IOException e - 
    * **/   
     public static void searchAGOI(String userName, String option,String searchParameter) throws IOException{
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
                       rs.close();
                       client.sendToClient(returnMsg);
				 	   break;
				 }
			    }
			    rs.close();
				returnMsg.add("No Such GOI in the system");
				client.sendToClient(returnMsg);
				break;
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
				rs.close();
				if(!flag)
					returnMsg.add("There Are currentliy No Goi's with this subject in MyBox!");
				client.sendToClient(returnMsg);
			 break;
			  /**
			    * Returns the user all the GOIs that currently exist in the system
			    * **/
			 case "All":
				    if(!rs.isBeforeFirst()){
				    	rs.close();
				    	returnMsg.add("There are currentliy no GOIs in the system!");
				    	client.sendToClient(returnMsg);
				    	break;
				    }
				    while(rs.next()){	   
				    	   returnMsg.add(rs.getString(1) +" "+ rs.getString(2) +" "+rs.getString(3)+
				    			   " "+rs.getString(4)+" "+rs.getString(5)+ " "+rs.getString(6));
				       }
				     rs.close();
				     client.sendToClient(returnMsg);
			 break;
			 /**
			  * Default case - handles all the invalid selections should they occur
			  * **/
			 default:
				 LogHandling.logging("Error: "+ userName +" User selected an invalid search option");
				 returnMsg.add("Error: Invalid Selection");
				 client.sendToClient(returnMsg);
		     break;
			}
			
		}
		 catch (SQLException e) {
			 LogHandling.logging("Error:"+userName +"Encountered a problem while searching in the GOI Database");
			 LogHandling.logging("Serach Parmeter: " + option +"Serach Index: " + searchParameter);
			 LogHandling.logging(e.getMessage());
			 e.printStackTrace(); 
			 returnMsg.add("MyBox Encounterd an Error!");
		     returnMsg.add("Please Contact Technical Support");
		}
     }   
     
     public static void makeARequest(){
    	 
     }
     
     /**searchSharedFiles - will be responsible for searching the shared files in the Group of interests the user is a member in
      * @author Ido Saroka 300830973
      * @param userName - the user's user name in the MyBox system
      * @param option - the search option the user wishes the search to be performed by
      * @param searchParameter - the parameter by which to perform the search
      * <p>
      * @exception SQLException e -
      * @exception IOException e - 
      * **/  
 	public static void searchSharedFiles(String userName,String option, String searchParameter) throws IOException{
		Statement stmt = null;
		PreparedStatement statement = null;
		ArrayList<Integer> groupIds = new ArrayList<>();
		ResultSet rs = null;
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			switch(option){
			/**
			 * AllFiles - will return all the files currently shared with the user from all the groups of interests he is a member in
			 * **/
			case "AllFiles":
				/**
				*This loop will print all the files currently associated will all of MyBox Users
				(GOI_id = 0 -> file is associated with all users)
				**/
				statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE GOI_id = 0");
				rs=statement.executeQuery();
				while(rs.next()){
					/**
					 * Description:
					 * rs.getString(1) - GOI ID
					 * rs.getString(2) - File ID
					 * rs.getString(3) - File Name
					 * rs.getString(4) - File's Suffix
					 * rs.getString(5) - File Owner
					 * rs.getString(6) - Virtual Path (Where is the file located in the server)
					 * rs.getString(7) - File's Description
					 * rs.getString(8) - Does this Group have an edit permission for the file from the File's Owner? (boolean)
					 * **/
				     returnMsg.add("GOI ID: "+rs.getString(1) + " "+rs.getString(2) +" "+rs.getString(3) +
    			            " "+rs.getString(4) +" "+rs.getString(5) +" " + rs.getString(6) + 
    			                       " " + rs.getString(7) +" " + rs.getString(8));
				}
				statement = conn.prepareStatement("SELECT GOI_id From usersingoi WHERE user_Name = ?");
				statement.setString(1, userName); 
				rs=statement.executeQuery();
				/**
				 * Receive all the groups the user appears in
				 **/
				while(rs.next()){
					groupIds.add(rs.getInt(1));
					flag = true;
				}
				if(!flag){
					rs.close();
					returnMsg.add("You are currently not a member in any Group Of Interests!\n");
					client.sendToClient(returnMsg);
					return;
				}
				/**
				 * This loop will print all the files associated with the groups the user is a member 
				 **/
				for (int var : groupIds){
					statement = conn.prepareStatement("SELECT * From FilesInGOI Where GOI_id = ?");
				    statement.setInt(1, var); 
					rs=statement.executeQuery();
					 while(rs.next()){
							/**
							 * Description:
							 * rs.getString(1) - GOI ID
							 * rs.getString(2) - File ID
							 * rs.getString(3) - File Name
							 * rs.getString(4) - File's Suffix
							 * rs.getString(5) - File Owner
							 * rs.getString(6) - Virtual Path (Where is the file located in the server)
							 * rs.getString(7) - File's Description
							 * rs.getString(8) - Does this Group have an edit permission for the file from the File's Owner? (boolean)
							 * **/
						     returnMsg.add("GOI ID: "+rs.getString(1) + " "+rs.getString(2) +" "+rs.getString(3) +
		    			            " "+rs.getString(4) +" "+rs.getString(5) +" " + rs.getString(6) + 
		    			                       " " + rs.getString(7) +" " + rs.getString(8));
						     flag = true;
						} 
				 }
				/**
				 * If the groups of interest the user is a member in have no fies in them
				 **/
				 if(flag == false){
					    rs.close();
					    returnMsg.add("The Groups of intersets you are currentliy a member in have no files in them!\n");
						client.sendToClient(returnMsg);
						break;
					 }
				 rs.close();
				 client.sendToClient(returnMsg);
				 break;
			/**
			 * Group - will be used should the user wants to only search inside the shared files of a specific group
			**/	
			case "Group":
				statement = conn.prepareStatement("SELECT GOI_id,GOI_Name From GOI WHERE GOI_Name = ?");
				statement.setString(1, searchParameter); 
				rs=statement.executeQuery();
				
				if(!rs.next()){/**Handles the case where the GOI inputed by the user does not exist**/
					 rs.close();
					 returnMsg.add("Error: GOI"+ searchParameter +"does not exist!");
					 client.sendToClient(returnMsg);
					 return;
				}
			    int groupNumber = rs.getInt(1); /**groupNumber - will hold the groups identification number as it appears in the database**/
			    /**
			     *Checks that the user is a member in this Group of Interests 
			     **/
			    statement = conn.prepareStatement("SELECT GOI_id,user_Name From usersingoi WHERE GOI_id = ? AND user_Name = ?");
			    statement.setInt(1, groupNumber);
				statement.setString(2, userName);
				rs=statement.executeQuery();
				if(!rs.isBeforeFirst()){/**Handles the case where the user is not a member of the group**/
					rs.close();
					returnMsg.add("Error: User "+ userName +" is not a member of Group " + searchParameter);
					client.sendToClient(returnMsg);
					break;
				}
				statement = conn.prepareStatement("SELECT * From FilesInGOI Where GOI_id = ?");
				    statement.setInt(1, groupNumber); 
					rs=statement.executeQuery();
					/**
					 * This while loop will print all the files currently shared with this group
					 **/
					while(rs.next()){
						/**
						 * Description:
						 * rs.getString(1) - GOI ID
						 * rs.getString(2) - File ID
						 * rs.getString(3) - File Name
						 * rs.getString(4) - File's Suffix
						 * rs.getString(5) - File Owner
						 * rs.getString(6) - Virtual Path (Where is the file located in the server)
						 * rs.getString(7) - File's Description
						 * rs.getString(8) - Does this Group have an edit permission for the file from the File's Owner? (boolean)
						 * **/
						returnMsg.add(rs.getString(1) +" "+rs.getString(2) + " "+rs.getString(3) +
	    			                    " "+rs.getString(4) +" "+rs.getString(5) +" " + rs.getString(6) + 
	    			                           " " + rs.getString(7) +" " + rs.getString(8));
					}
					rs.close();
					client.sendToClient(returnMsg);
				break;
				
			    default:
			    	LogHandling.logging("Error: User"+ userName +"selected an invalid search option");
					returnMsg.add("Error: Invalid Selection");
					client.sendToClient(returnMsg);
			     break;
			}
	     }catch (SQLException e) {
	     LogHandling.logging("Error:"+ userName +"Encountered a problem while searching in the GOI Database");
		 LogHandling.logging("Serach Parmeter: " + option +"Serach Index: " + searchParameter);
		 LogHandling.logging(e.getMessage());
		 e.printStackTrace(); 
		 returnMsg.add("MyBox Encounterd an Error!");
	     returnMsg.add("Please Contact Technical Support");
	     client.sendToClient(returnMsg);
	    }
	}

}
