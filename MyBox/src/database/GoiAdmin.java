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
import entities.GOI;

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
			   createAGOI((GOI)msg.get(2));
			   break;
			   
		   case "DeleteAGOI":
			   
			   break;
			   
		   
		   // "RetriveCurrentRequests" - will be used to retrieve all the current users requests to join GOIs
		   case "RetriveCurrentRequests":
			   getRequests();
			   break;
			   
		   case "DecideAboutRequest":
			   decideAboutARequest((String)msg.get(2), (String)msg.get(3));
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
			    	   /*
			    	    * Description:
			    	    * rs.getString(1) - Request ID
			    	    * rs.getString(2) - User Name
			    	    * rs.getString(3) - GOI Name
			    	    * rs.getString(4) - Description
			    	    * */
			    	   returnMsg.add(rs.getString(1) +
			    			               " "+rs.getString(2) +
			    			                  " "+rs.getString(3) +
			    			                    " "+rs.getString(4));
			       }
			       rs.close();
			       client.sendToClient(returnMsg);
			   }
				/*
				 * Handling of the SQLException | IOException - 1. Saving the appropriate data in the Log
				 *                                2.The Admin will receive a notification regarding the problem
				 */                          
			   catch(SQLException | IOException e) {
				   LogHandling.logging("Error: Admin ecnounterd a problme while trying to retrive current reqeusts");
				   LogHandling.logging(e.getMessage());
				   e.printStackTrace(); 
				   returnMsg.add("MyBox Encounterd an Error!");
				   returnMsg.add("Please Contact Technical Support");
				   client.sendToClient(returnMsg);
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
		    * @exception IOException e - the function will throw an IOException in case there is a problem sending the message back to the client
		    * **/  
		public static void createAGOI(GOI newGOI) throws IOException{
			PreparedStatement statement = null;
			ResultSet rs = null;
			
			try{
				 statement = conn.prepareStatement("SELECT GOI_id,GOI_Name From GOI WHERE GOI_Name = ?");
				 statement.setString(1, newGOI.getName()); 
				 rs=statement.executeQuery();
				 if(rs.isBeforeFirst()){
					 rs.close();
					 returnMsg.add("Error:Could not create GOI, There is allready a GOI with this name in the system");
					 client.sendToClient(returnMsg);
					 return;
				 }
				 statement = conn.prepareStatement("INSERT INTO GOI (GOI_Name,subject,creation_Date,number_Of_Users,current_Num_Of_Users) VALUES (?,?,?,?,?)");
				 statement.setString(1, newGOI.getName());
			     statement.setString(2, newGOI.getSubject());	
			     statement.setString(3, newGOI.getCreationDate());
			     statement.setInt(4, newGOI.getNumberOfUsers());
			     statement.setInt(5, 0);
			     
			     statement.executeUpdate();
				 rs.close();
				 LogHandling.logging("GOI " + newGOI.getName() + " was created by Admin");
			     returnMsg.add("GOI" + newGOI.getName() + "was successfully created!" );
			     client.sendToClient(returnMsg);
				 
			}catch(SQLException | IOException e){
				   LogHandling.logging("Error: Admin ecnounterd a problme while trying to create GOI: "+ newGOI.getName());
				   LogHandling.logging(e.getMessage());
				   e.printStackTrace(); 
				   returnMsg.add("MyBox Encounterd an Error!");
				   returnMsg.add("Please Contact Technical Support");
				   client.sendToClient(returnMsg);
			}
		}

		
		public static void deleteAGOI(GOI newGOI){
			/**should user deleteAUserFromAGOI() for the removal of all the users in the goi**/
			PreparedStatement statement = null;
			ResultSet rs = null;
	
		}
		
		public static void deleteAUserFromAGOI(){
			/**Important add a message to this user in the message database**/
		}
		
		/**decideAboutARequest - this function will be used by the Admin in order to handle the systems users to join different Groups Of Interest
		 * @author Ido Saroka 300830973
	     * @param requestId - the identification number of the request as it appears in the Database: "Request"
		 * @param decision - the Admin's decision regarding this specific request (Approved, Declined or illegal input)
	     * @param client - the function will receive a connection to the client
		 * <p>
		 * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
		 * @throws SQLException - the function will throw an IOException in case there will be a problem writing to the the Database: "Users"
		 * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
		 * @exception IOException e 
		 * @return 
		 * **/  
		 @SuppressWarnings("resource")
	     public static void decideAboutARequest(String requestId,String decision) throws IOException{
				String userName,goiName;
				
				PreparedStatement statement = null;
				ResultSet rs = null;
				
				 //The following statement will Check to see that a request was indeed made by this specific user
				 try{
					 statement = conn.prepareStatement("SELECT * From request WHERE request_id = ?");
					 statement.setString(1, requestId);
					 rs = statement.executeQuery();
					 if(!rs.isBeforeFirst()){
						 LogHandling.logging("Error: Admin ecnounterd a problme while Deciding about request : "+ requestId+" request does not exist");
						 returnMsg.add(false);
						 returnMsg.add("Error: No such Request exist in the database!");
						 client.sendToClient(returnMsg);
						 return;
					 }
					 //rs.getString(2) = userName , rs.getString(3) = GOI_Name
					 userName = rs.getString(2);
					 goiName = rs.getString(3);
					 
					 //This condition handles the situation where the Admin choose to Accept the user request to  join  a GOI
					 if(decision.equals("accept")){//Request Approved	 
						 //Add the user to the requested GOI 
						 statement = conn.prepareStatement("INSERT INTO UsersInGoi (GOI_id,user_Name) VALUES (?,?)");
						 statement.setString(1, userName);
					     statement.setString(2, goiName);			
					     statement.executeQuery();
					     
					    //insert the appropriate message into the user messages
						 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");
						 statement.setString(1, userName);
					     statement.setString(2, goiName);	
					     statement.setString(3, "The Sytsem Admin has accepted your request to join GOI: " + goiName); 
					     statement.executeQuery();
					     
					     //Deleting the message from the request database
					     statement = conn.prepareStatement("DELETE FROM Request WHERE request_id = ?");
					     statement.setString(1, requestId);
					     statement.executeQuery();
					     rs.close();
					     LogHandling.logging("user: " + userName+ " was added to GOI: "+ goiName +" by Admin");
					     returnMsg.add(true);
					     returnMsg.add("User request was Approved user: " + userName+ " was added to GOI: "+ goiName);
					     client.sendToClient(returnMsg);
					     

					 } //end if statement - Request Approved
					 
					 //This condition handles the situation where the Admin choose to decline the user request to  join  a GOI
					 else if(decision.equals("reject")){//Request Declined
						 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");
						 statement.setString(1, userName);
					     statement.setString(2, goiName);	
					     statement.setString(3, "The Sytsem Admin has rejected your request to join GOI: " + goiName); 
					     statement.executeQuery();
					     rs.close();
					     LogHandling.logging("Reuqest: " + requestId + " was declined by Admin");
					     returnMsg.add(true);
					     returnMsg.add("Request: " + requestId + " by user: "+ userName +" was Rejected");
					     client.sendToClient(returnMsg);
					 }
					 
					 //Illegal Decision - This condition is meant to handle a situation where the function received an illegal parameter
					 
					 else{//Illegal Decision
						//send a notification to the Admin about an illegal request and recored it in the log
						 LogHandling.logging("Error: Illegal Operation on request: " +  requestId);		
						 LogHandling.logging("Invlaid option was selected");
						 returnMsg.add(false);
						 returnMsg.add("Error: Illegal Option was selected");
						 client.sendToClient(returnMsg);
					 }
					 
				 }
				 catch(SQLException | IOException e){
					    LogHandling.logging("Admin has encounterd a problem while makeing a decision aboud request: "+ requestId);
					    returnMsg.add("MyBox Encounterd an Error!");
					    returnMsg.add("Please Contact Technical Support");
					    client.sendToClient(returnMsg); 
					 } 
	   }
		

}
