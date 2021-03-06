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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

import ocsf.server.ConnectionToClient;
import entities.GOI;
import entities.User;
import entities.Request;

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
		   returnMsg =  new ArrayList<>();
		   Scanner sc = new Scanner(System.in);
		   String str = (String)msg.get(3);
		   System.out.println("Got to GOI controller: "+(String)msg.get(3)+"\n");
		   switch(str){
		   
		   //"CreateGOI" - will be used by the admin in order to create a new goi in the system
		   case "CreateGOI":
			   System.out.println("Got to create a goi call to function");
			   createAGOI((GOI)msg.get(4));
			   break;
			   
			 //"DeleteAGOI" - will be used by the admin in delete a goi in the system  
		   case "DeleteAGOI":
			   deleteAGOI((GOI)msg.get(4));
			   break;
		
		  //"returnAllUsersToAdmin" - will be used by the admin in order to find all the current users in the system    
		   case "ReturnSystemUsers":   
			   returnAllUsersToAdmin();
			   break;
			   
		   //"ReturnCurrentGOIs" - will be used by the admin in order to find all the GOIs currently in the system
		   case "ReturnCurrentGOIs":
			   returnAllGoiToAdmin();
			   break;
			   
		   case "AddUserToGOINoRequest": 
			   addUserToGOINoRequest((GOI)msg.get(4), (String)msg.get(5));
			   break;
			   
		   // "RetriveCurrentRequests" - will be used to retrieve all the current users requests to join GOIs
		   case "RetriveCurrentRequests":
			   getRequests();
			   break;
			   
		   //DecideAboutRequest - will be used by the Admin in order to decide about the current requests avilable in the system   
		   case "DecideAboutRequest":
			   decideAboutARequest((Request)msg.get(4), (String)msg.get(5));
			   break;
			   
		   //"RemoveAUserFromAGOI" - will be used by the admin in order to remove a user from a specific GOI
		   case "RemoveAUserFromAGOI":
			   deleteAUserFromAGOI((GOI)msg.get(4), (String)msg.get(5));
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
		private static void getRequests() throws IOException{
			 PreparedStatement stmt;
			 Request requestToSend;
			 System.out.println("Got to get requests..\n");
			   try{
			       stmt = conn.prepareStatement("SELECT * FROM request");
			       ResultSet rs = stmt.executeQuery();
			       if(!rs.isBeforeFirst()){
			    	   returnMsg.add(false);
			    	   returnMsg.add("There are currentliy no request in the system!");
			    	   client.sendToClient(returnMsg);
			       }
			       //rs.next(); //<- check if needed
			       returnMsg.add(true);
			       while(rs.next()){
			    	   
			    	    //Description:
			    	    //rs.getString(1) - Request ID  * rs.getString(2) - User Name * rs.getString(3) - GOI Name * rs.getString(4) - Description
			    	   requestToSend = new Request(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
			    	   returnMsg.add(requestToSend);
			       }
			       System.out.println("Ended get Reqeusts\n");
			       System.out.println("Returned Value is"+returnMsg.get(0)+"\n");
			       System.out.println(((Request)returnMsg.get(1)).getRequestID()+"\n");
			       System.out.println("Returned Message size is: "+returnMsg.size()+"\n");
			       client.sendToClient(returnMsg);
			       rs.close();
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
		private static void createAGOI(GOI newGOI) throws IOException{
			PreparedStatement statement;
			ResultSet rs;
			 System.out.println("Got to Create a GOI");

			try{
				 statement = conn.prepareStatement("SELECT GOI_id,GOI_Name From GOI WHERE GOI_Name = ?");
				 statement.setString(1, newGOI.getName());
				 System.out.println(newGOI.getName());
				 rs=statement.executeQuery();
				 if(rs.isBeforeFirst()){
					 System.out.println("GOI allready exist condition");
					 LogHandling.logging("GoI allready exist");
					 returnMsg.add("Error:Could not create GOI, There is allready a GOI with this name in the system");
					 client.sendToClient(returnMsg);
					 rs.close();
					 return;
				 }
				 if(newGOI.getNumberOfUsers()>200 || 0>newGOI.getNumberOfUsers()){
					 LogHandling.logging("Illegal number of users");
					 returnMsg.add("Error: number of users must be an Integer between 0 and 200");
					 client.sendToClient(returnMsg);
					 rs.close();
					 return;
				 }
				 System.out.println("Before rs.next()");
				 rs.next();
				 System.out.println("After rs.next()");
				 System.out.println("Got to the first check - GOI does not exist");
				 statement = conn.prepareStatement("INSERT INTO GOI (GOI_Name,subject,creation_Date,number_Of_Users,current_Num_Of_Users) VALUES (?,?,?,?,?)");
				 statement.setString(1, newGOI.getName());
			     statement.setString(2, newGOI.getSubject());	
			     statement.setString(3, newGOI.getCreationDate());
			     statement.setInt(4, newGOI.getNumberOfUsers());
			     statement.setInt(5, 0);
			     statement.executeUpdate();
			     
			     
				 System.out.println("The GOI was added to the database");

				 rs.close();
				 LogHandling.logging("GOI: " + newGOI.getName() + " was created by Admin");
			     returnMsg.add("GOI" + newGOI.getName() + "was successfully created!" );
			     client.sendToClient(returnMsg);
			     return;
				 
			}catch(SQLException | IOException e){
				   LogHandling.logging("Error: Admin ecnounterd a problem while trying to create GOI: "+ newGOI.getName());
				   LogHandling.logging(e.getMessage());
				   e.printStackTrace(); 
				   returnMsg.add("MyBox Encounterd an Error!");
				   returnMsg.add("Please Contact Technical Support");
				   client.sendToClient(returnMsg);
				   return;
			}
		}
		
		
		 /**returnAllUsersToAdmin - will be used by the admin in order to retrieve all the current users in the system
		    * @author Ido Saroka 300830973
		    * <p>
		    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
		    * @exception SQLException e - the function will throw an IOException in case there will be a problem searching the database users
		    * @exception IOException e - the function will throw an IOException in case there is a problem sending the message back to the client
		    * **/ 
		private static void returnAllUsersToAdmin() throws IOException{
			PreparedStatement statement = null;
			ResultSet rs = null;
			ArrayList<String> systemUsers = new ArrayList<>();
			try{
				statement = conn.prepareStatement("SELECT * FROM Users");
				rs = statement.executeQuery();
				if(!rs.isBeforeFirst()){
					returnMsg.add(false);
					returnMsg.add("Error: There are currentliy no listed users in MyBox");
					client.sendToClient(returnMsg);
					rs.close();
				}
				returnMsg.add(true);
				while(rs.next()){
					systemUsers.add(rs.getString(1));
				}
				returnMsg.add(systemUsers);
				client.sendToClient(returnMsg);
				rs.close();
			}catch (SQLException | IOException e){
				 LogHandling.logging("Error: Admin ecnounterd a problem while trying retrieve the current users in the system");
				 LogHandling.logging(e.getMessage());
				 e.printStackTrace(); 
				 returnMsg.add(false);
				 returnMsg.add("MyBox Encounterd an Error!");
				 returnMsg.add("Please Contact Technical Support");
				 client.sendToClient(returnMsg);
			}
		}
		
		
		 /**returnAllGoiToAdmin - will be responsible for retrieving all of the current GOIs in the system
		    * @author Ido Saroka 300830973
		    * <p>
		    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
		    * @exception SQLException e - the function will throw an SQLException in case there is a problem searching the database
		    * @exception IOException e - the function will throw an IOException in case there is a problem sending the message back to the client
		    * **/  
		private static void returnAllGoiToAdmin() throws IOException{
			PreparedStatement statement = null;
			ResultSet rs = null;
			GOI goiToSend;
			try{
				statement = conn.prepareStatement("SELECT * FROM GOI");
				rs = statement.executeQuery();
				if(!rs.isBeforeFirst()){
					returnMsg.add(false);
					returnMsg.add("Error: There are currentliy no GOIs in the system");
					client.sendToClient(returnMsg);
					rs.close();
				}
				returnMsg.add(true);
				while(rs.next()){
					//(int id, String name, String subject, int numberOfUsers, String creation,int current)
					goiToSend = new GOI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
					returnMsg.add(goiToSend);
				}
				client.sendToClient(returnMsg);
				rs.close();
			}catch(SQLException | IOException e){
				 LogHandling.logging("Error: Admin ecnounterd a problem while trying retrieve the current GOIs in the system");
				 LogHandling.logging(e.getMessage());
				 e.printStackTrace(); 
				 returnMsg.add(false);
				 returnMsg.add("MyBox Encounterd an Error!");
				 returnMsg.add("Please Contact Technical Support");
				 client.sendToClient(returnMsg);
			}
		}
		
		 /**returnAllGoiToAdmin - will be responsible for the deletion of a GOI, the function will use the function: "deleteAUserFromAGOI"
		  * in order to delete each user
		  * @author Ido Saroka 300830973
		  * <p>
		  * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
		  * @exception SQLException e - the function will throw an SQLException in case there is a problem searching the database
		  * @exception IOException e - the function will throw an IOException in case there is a problem sending the message back to the client
		  * **/  
		private static void deleteAGOI(GOI goiToDelete) throws IOException{
			PreparedStatement statement = null;
			ResultSet rs = null;
			HashSet<String> usersToInform = new HashSet<String>();
			try{
				statement = conn.prepareStatement("SELECT * From GOI WHERE GOI_Name = ?");
				statement.setString(1, goiToDelete.getName());
				rs = statement.executeQuery();
				if(!rs.isBeforeFirst()){
					returnMsg.add(false);
					returnMsg.add("Error - GOI: "+goiToDelete.getName()+" does not exist in the system");
				}
				statement = conn.prepareStatement("SELECT * From UsersInGOI WHERE GOI_id = ?");
				statement.setInt(1, goiToDelete.getID());
				rs = statement.executeQuery();
				while(rs.next()){
					usersToInform.add(rs.getString(2));
				}
				for(String user : usersToInform){
					deleteAUserFromAGOI(goiToDelete, user);
				}
				
				//delete statement
				statement = conn.prepareStatement("DELETE FROM GOI WHERE GOI_id = ?");
				statement.setInt(1,goiToDelete.getID());
				statement.executeUpdate();

				LogHandling.logging("Admin has deleted the GOI: "+goiToDelete.getName()+" all the relevent users have been notified");
				returnMsg.add(true);
				returnMsg.add("You have succesfuliy deleted GOI: "+goiToDelete.getName());
				client.sendToClient(returnMsg);
			}catch(SQLException | IOException e){
				LogHandling.logging("Error: Admin ecnounterd a problme while trying to Delete GOI: " +
						goiToDelete.getName());
				LogHandling.logging(e.getMessage());
				e.printStackTrace(); 
				returnMsg.add(false);
				returnMsg.add("MyBox Encounterd an Error!");
				returnMsg.add("Please Contact Technical Support");
				client.sendToClient(returnMsg);
			}
	
		}
		
		 /**addUserToGOI - will be used by the admin when he wishes to add a user to a GOI without the user having made the request to join
		  * @author Ido Saroka 300830973
		  * @param goi - the details of the requested GOI
		  * @param userToAdd - the name of the user which will be added to the GOI
		  * <p>
		  * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
		  * @exception SQLException e - the function will throw an SQLException in case there is a problem searching the database
		  * @exception IOException e - the function will throw an IOException in case there is a problem sending the message back to the client
		  * **/ 
		private static void addUserToGOINoRequest(GOI goi, String userToAdd) throws IOException{
			PreparedStatement statement = null;
			ResultSet rs = null;
			DateFormat dateFormat;
	 		Date date = new Date();
	 		String time;
	 		try{
				statement = conn.prepareStatement("SELECT * From Users Where userName = ?");
				statement.setString(1,userToAdd);
				rs = statement.executeQuery();
				if(!rs.isBeforeFirst()){
					 LogHandling.logging("Admin encountered an error while trying to delete the user: " + userToAdd + "from GOI: " + goi.getName());
					 returnMsg.add(false);
					 returnMsg.add("Error - the user: " + userToAdd + " is not a registered user in MyBox");
					 client.sendToClient(returnMsg);
					 rs.close();
				}
				statement = conn.prepareStatement("SELECT * From UsersInGOI Where GOI_id = ? AND user_Name = ?");
				statement.setString(1,goi.getName());
				statement.setString(2,userToAdd);
				rs = statement.executeQuery();
				if(rs.isBeforeFirst()){	
					LogHandling.logging("Admin encountered an error while trying to delete the user: " + userToAdd + "from GOI: " + goi.getName());
					returnMsg.add(false);
					returnMsg.add("Error - the user: " + userToAdd + " is allready a member in GOI: " + goi.getName());
					client.sendToClient(returnMsg);
				}
				 //Add the user to the requested GOI 
				 statement = conn.prepareStatement("INSERT INTO UsersInGoi (GOI_id,user_Name) VALUES (?,?)");
				 statement.setString(1, userToAdd);
			     statement.setString(2, goi.getName());			
			     statement.executeQuery();
			     
			    //insert the appropriate message into the user messages
				 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");
				 statement.setString(1, userToAdd);
			     statement.setString(2, goi.getName());	
			     statement.setString(3, "The Sytsem Admin has accepted your request to join GOI: " + goi.getName()); 
			     statement.executeQuery();
		}catch(SQLException | IOException e){
			LogHandling.logging("Error: Admin ecnounterd a problme while trying to Add the user: "+ userToAdd +" from GOI: " +
		    goi.getName());
			LogHandling.logging(e.getMessage());
			e.printStackTrace(); 
			returnMsg.add(false);
			returnMsg.add("MyBox Encounterd an Error!");
			returnMsg.add("Please Contact Technical Support");
			client.sendToClient(returnMsg);
	     	}
		}
		
		
		 /**returnAllGoiToAdmin - will be responsible for the deletion of a GOI, the function will use the function: "deleteAUserFromAGOI"
		  * in order to delete each user
		  * @author Ido Saroka 300830973
		  * <p>
		  * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
		  * @exception SQLException e - the function will throw an SQLException in case there is a problem searching the database
		  * @exception IOException e - the function will throw an IOException in case there is a problem sending the message back to the client
		  * **/  
		private static void deleteAUserFromAGOI(GOI goi, String userToDelete) throws IOException{
			PreparedStatement statement = null;
			ResultSet rs = null;
			DateFormat dateFormat;
	 		Date date = new Date();
	 		String time;
			try{
				statement = conn.prepareStatement("SELECT * From Users Where userName = ?");
				statement.setString(1,userToDelete);
				rs = statement.executeQuery();
				if(!rs.isBeforeFirst()){
					 LogHandling.logging("Admin encountered an error while trying to delete the user: " + userToDelete + "from GOI: " + goi.getName());
					 returnMsg.add(false);
					 returnMsg.add("Error - the user: " + userToDelete + " is not a registered user in MyBox");
					 client.sendToClient(returnMsg);
					 rs.close();
				}
				statement = conn.prepareStatement("DELETE FROM UsersInGOI WHERE GOI_id = ? AND user_Name = ?");
				statement.setInt(1,goi.getID());
				statement.setString(2,userToDelete);
				statement.executeUpdate();
				
				dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");	 
 				time=dateFormat.format(date);
				statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");	
				statement.setString(1, time);
				statement.setString(2, userToDelete);
				statement.setString(3, "You have been reomved from GOI: "+goi.getName()+" By the system Admin");
				statement.executeUpdate();
				
			}catch(SQLException | IOException e){
				LogHandling.logging("Error: Admin ecnounterd a problme while trying to Delete the user: "+ userToDelete +" from GOI: " +
						goi.getName());
				LogHandling.logging(e.getMessage());
				e.printStackTrace(); 
				returnMsg.add(false);
				returnMsg.add("MyBox Encounterd an Error!");
				returnMsg.add("Please Contact Technical Support");
				client.sendToClient(returnMsg);
			}
			
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
		 //@SuppressWarnings("resource")
	     private static void decideAboutARequest(Request requestId,String decision) throws IOException{
				String userName,goiName;
				
				PreparedStatement statement;
				ResultSet rs;
				
				String time;
				DateFormat dateFormat;
				Date date = new Date();
				 dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");	 
				 time=dateFormat.format(date);
				 System.out.println("Got to decide about");
				 //The following statement will Check to see that a request was indeed made by this specific user
				 try{
					 statement = conn.prepareStatement("SELECT * From request WHERE request_id = ?");
					 statement.setInt(1, requestId.getRequestID());
					 System.out.println("Request ID: "+ requestId.getRequestID());
					 rs = statement.executeQuery();
					 
					 if(!rs.isBeforeFirst()){
						 System.out.println("Entered the if condition - no request");
						 LogHandling.logging("Error: Admin ecnounterd a problem while Deciding about request : "+ requestId+" request does not exist");
						 returnMsg.add(false);
						 returnMsg.add("Error: No such Request exist in the database!");
						 client.sendToClient(returnMsg);
						 rs.close();
						 return;
					 }
					 rs.next();//<-check if needed
					 System.out.println("End first if condition");
					 //rs.getString(2) = userName , rs.getString(3) = GOI_Name
					 userName = rs.getString(2);
					 System.out.println(userName);
					
					 goiName = rs.getString(3);
					 System.out.println(goiName);	 
					 
					 statement = conn.prepareStatement("SELECT * From Users WHERE userName = ?");
					 statement.setString(1, requestId.getUserName());
					 rs = statement.executeQuery();
					 if(!rs.isBeforeFirst()){
						 statement = conn.prepareStatement("DELETE FROM Request WHERE request_id = ?");
					     statement.setInt(1, requestId.getRequestID());
					     statement.executeUpdate();
						 returnMsg.add(false);
						 returnMsg.add("Error: No such User exist in the database!");
						 client.sendToClient(returnMsg);
						 rs.close();
						 return;
					 }				 					 
					 statement = conn.prepareStatement("SELECT * From GOI WHERE GOI_Name = ?");
					 statement.setString(1, goiName);
					 rs = statement.executeQuery();
					 if(!rs.isBeforeFirst()){
						 statement = conn.prepareStatement("DELETE FROM Request WHERE request_id = ?");
					     statement.setInt(1, requestId.getRequestID());
					     statement.executeUpdate();
						 returnMsg.add(false);
						 returnMsg.add("Error: No such Group exist in the database!");
						 client.sendToClient(returnMsg);
						 rs.close();
						 return;
					 }
					 rs.next();
					 System.out.println("End Seconed if condition");

                     int goi_id = rs.getInt(1);
					 System.out.println("GOI ID: "+ goi_id);
					// rs.close();
					 //This condition handles the situation where the Admin choose to Accept the user request to  join  a GOI
					 int currentNumOfUsers = rs.getInt(6);
					 System.out.println("currentNumOfUsers: "+ rs.getInt(6));
					 int maxNumOfUsers = rs.getInt(5);
					 System.out.println("maxNumOfUsers: "+ rs.getInt(5));
					 
					 
					 statement = conn.prepareStatement("SELECT * From usersingoi WHERE GOI_id = ? AND user_Name = ?");
					 statement.setInt(1, goi_id);
					 statement.setString(2, requestId.getUserName());
					 rs = statement.executeQuery();
					 if(rs.isBeforeFirst()){
						 statement = conn.prepareStatement("DELETE FROM Request WHERE request_id = ?");
					     statement.setInt(1, requestId.getRequestID());
					     statement.executeUpdate();
						 returnMsg.add(false);
						 returnMsg.add("Error: The user is allready a member in this Group");
						 client.sendToClient(returnMsg);
						 rs.close();
						 return;
					 }
					 
					 
					 if(decision.equals("accept")){//Request Approved	 
						 //Add the user to the requested GOI 
						 System.out.println("Got to accept");
						 if(currentNumOfUsers == maxNumOfUsers){
							 System.out.println("Error: ");
							 LogHandling.logging("Error: Illegal Operation on request: " +  requestId+ " Group is allready full");	
							 returnMsg.add(false);
							 returnMsg.add("Error: Could not add user: "+ userName + " To group: "+ goiName + " the group is allready full");
							 client.sendToClient(returnMsg);
							 rs.close();
							 return;
						 }						 						 
						 statement = conn.prepareStatement("INSERT INTO UsersInGoi (GOI_id,user_Name) VALUES (?,?)");
						 statement.setInt(1, goi_id);
					     statement.setString(2, userName);			
					     statement.executeUpdate();
					     
					     //update the current number of users
					     statement = conn.prepareStatement("UPDATE goi SET current_Num_Of_Users = ? WHERE GOI_id = ?");
					     statement.setInt(1, currentNumOfUsers+1);
					     statement.setInt(2, goi_id);
					     statement.executeUpdate();
					     
					     
					    //insert the appropriate message into the user messages
						 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");
						 statement.setString(1, time);
					     statement.setString(2, userName);	
					     statement.setString(3, "The Sytsem Admin has accepted your request to join GOI: " + goiName); 
					     statement.executeUpdate();
					     
					     //Deleting the message from the request database
					     statement = conn.prepareStatement("DELETE FROM Request WHERE request_id = ?");
					     statement.setInt(1, requestId.getRequestID());
					     statement.executeUpdate();

					     LogHandling.logging("user: " + userName+ " was added to GOI: "+ goiName +" by Admin");
					     returnMsg.add(true);
					     returnMsg.add("User request was Approved user: " + userName+ " was added to GOI: "+ goiName);
					     client.sendToClient(returnMsg);
					     rs.close();
					     
					     return;

					 } //end if statement - Request Approved
					 
					 //This condition handles the situation where the Admin choose to decline the user request to  join  a GOI
					 else if(decision.equals("reject")){//Request Declined
						 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");
						 statement.setString(1, time);
					     statement.setString(2, userName);	
					     statement.setString(3, "The Sytsem Admin has rejected your request to join GOI: " + goiName); 
					     statement.executeUpdate();
					     
					     //Deleting the request
					     statement = conn.prepareStatement("DELETE FROM Request WHERE request_id = ?");
					     statement.setInt(1, requestId.getRequestID());
					     statement.executeUpdate();
					     
					     LogHandling.logging("Reuqest: " + requestId.getRequestID() + " was declined by Admin");
					     returnMsg.add(true);
					     returnMsg.add("Request: " + requestId.getRequestID() + " by user: "+ userName +" was Rejected");
					     client.sendToClient(returnMsg);
					     rs.close();
					     return;
					 }
					 
					 //Illegal Decision - This condition is meant to handle a situation where the function received an illegal parameter
					 
					 else{//Illegal Decision
						//send a notification to the Admin about an illegal request and recored it in the log
						 LogHandling.logging("Error: Illegal Operation on request: " +  requestId);		
						 LogHandling.logging("Invlaid option was selected");
						 returnMsg.add(false);
						 returnMsg.add("Error: Illegal Option was selected");
						 client.sendToClient(returnMsg);
						 return;
					 }
					 
				 }
				 catch(SQLException | IOException e){
					    LogHandling.logging("Admin has encounterd a problem while makeing a decision aboud request: "+ requestId);
					    returnMsg.add("MyBox Encounterd an Error!");
					    returnMsg.add("Please Contact Technical Support");
					    client.sendToClient(returnMsg); 
					    return;
					 } 
	   }
		

}
