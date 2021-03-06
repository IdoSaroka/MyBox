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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

import ocsf.server.ConnectionToClient;
import entities.FileToView;
import entities.GOI;
import entities.User;
import files.Browse;
import files.MyFile;
import files.Save;

/**This Class contains MyBox basic functions that are responsible for
 * accessing and searching inside the "Groups Of Interests" in the system.
 * @author Ido Saroka 300830973**/
public class GoiBasic implements Serializable{
	
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
    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
    * @exception SQLException e 
    * @exception IOException e 
    * @return 
    * **/   
   public static void options() throws IOException{
	  
	   returnMsg =  new ArrayList<>();  //Overwrites any existing messages send to the user 
	   String str = (String)msg.get(1);
	   System.out.println("Got to GOIBasic\n");
	   switch(str){
	              
	               //"Search" - Should the user chooses perform a search in the GOI database
	   
	              case "Search":
	            	  
	          	    /**Test**/
	            	    System.out.println("\nReached GOI Basic - searchAGOI \n");
	            	    System.out.println(((User)msg.get(2)).getUserName()+"\n");
	            	    System.out.println((String)msg.get(3)+"\n");
	            	    System.out.println((String)msg.get(4)+"\n");
	            	  searchAGOI((User)msg.get(2),(String)msg.get(3),(String)msg.get(4));
	              break;
		          
	              
	       
	              //ShowGoiFiles - will be used should the user wishes to search the shared files currently available in the system
	              case "ShowGoiFiles":
	            	  searchSharedFiles((User)msg.get(2),(String)msg.get(3),(String)msg.get(4));
	              break;
	            	  
	          
	               //MakeARequestToJoin - Handles the case where the user wishes to make a request to join a GOI
	              case "MakeARequestToJoin": 
	            	  
	            	    /**Test**/
	            	    System.out.println("\nReached GOI Basic - makeARequest \n");
	            	    System.out.println(((User)msg.get(2)).getUserName()+"\n");
	            	    System.out.println((String)msg.get(3)+"\n");
	            	    System.out.println((String)msg.get(4)+"\n");
	            	  makeARequest((User)msg.get(2),(String)msg.get(3),(String)msg.get(4));
			      break; 
	              
	              case "ReturnUserGois":
	            	  returnUserGois((User)msg.get(2));
	            	  break;
	            	  
	               //DownloadSharedFile - Handles the case where the user wishes to download a shared file
	              case "DownloadSharedFile":
	            	  downloadSharedFile((User)msg.get(2) , (int)msg.get(3),  (FileToView)msg.get(4));
	              break;
	              
	              
	               //EditSharedFile - Handles the case where the user wishes to edit a file shared with his GOI  
	              case "EditSharedFile":
	            	  //editSharedFile(int goiShared, User userName, FileToView sharedFile)
	            	  editSharedFile((int)msg.get(2), (User)msg.get(3), (FileToView)msg.get(4));
	            	  break;
	            	  
	               //RemoveAUserFromGOI - will be used by a user when he wishes to remove himself from a GOI
	              case "RemoveAUserFromGOI":
	            	  removeUserFromGOI((User)msg.get(2),(GOI)msg.get(3));
	            	  break;
			      
			      default:
			        LogHandling.logging("Error: User selected an invalid search option:" + (String)msg.get(1));
				    returnMsg.add("Error: Invalid Selection");
				    client.sendToClient(returnMsg);
			      break;
	   }
	   
   }
   
   
   /**searchAGOI - will be responsible for all the search options in the GOI database
    * @author Ido Saroka 300830973
    * @param userName - the user's user name in the MyBox system
    * @param option - the search option the user wishes the search to be performed by
    * @param searchParameter - the parameter by which to perform the search
    * <p>
    * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
    * @exception SQLException e 
    * @exception IOException e  
    * **/   
     private static void searchAGOI(User userName, String option,String searchParameter) throws IOException{
    	PreparedStatement stmt = null;
		GOI goiToReturn;
		boolean flag = false;
		ResultSet rs = null;
		try {
			
			switch(option){
			/*
	    	    * Columns Description:
	    	    * rs.getString(1) - GOI IDnn * rs.getString(2) - GOI Namec* rs.getString(3) - GOI Subject
	    	    * rs.getString(4) - Date Created * rs.getString(5) - Total Number Of Users  * rs.getString(6) - Current Number Of Users
	    	    * **/

		     //"Name" - Handles a search by name in the GOI database
			case "Name":
				stmt = conn.prepareStatement("SELECT * from GOI WHERE GOI_Name = ?");
				stmt.setString(1, searchParameter);
				rs = stmt.executeQuery();
				if(!rs.isBeforeFirst()){
					 returnMsg.add(false);
					 returnMsg.add("There is currentliy no GOI: "+ searchParameter+ " in MyBox");
					 client.sendToClient(returnMsg);
					 break;
				}
				rs.next();
				returnMsg.add(true);
				goiToReturn = new GOI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				returnMsg.add(goiToReturn);
				client.sendToClient(returnMsg);
                rs.close();      
			    break;
				 
			    
			 //"Subject" - Handles a search by subject in the GOI database    
			 case "Subject":
				 stmt = conn.prepareStatement("SELECT * From goi WHERE subject = ?");
				 stmt.setString(1, searchParameter);
					rs = stmt.executeQuery();
					if(!rs.isBeforeFirst()){
						 returnMsg.add(false);
						 returnMsg.add("There Are currentliy No Goi's with the subject: "+ searchParameter +" in MyBox!");
						 client.sendToClient(returnMsg);
						 break;
					}
			    returnMsg.add(true);
				while(rs.next()){ 		    
					goiToReturn = new GOI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
					returnMsg.add(goiToReturn);
				}
				rs.close();
				client.sendToClient(returnMsg);
			 break;
			   //"All" - Returns the user all the GOIs that currently exist in the system
			 case "All":
				 
                 /**Test**/
				 System.out.println("Reached All statement\n");
				 
				   stmt = conn.prepareStatement("SELECT * From GOI");
				   rs = stmt.executeQuery();
				    if(!rs.isBeforeFirst()){
				    	rs.close();
				    	returnMsg.add(false);
				    	returnMsg.add("There are currentliy no GOIs in the system!");
				    	client.sendToClient(returnMsg);
				    	break;
				    }
				    /**Test**/
				    System.out.println("Ended rs statement\n");
				    
				    returnMsg.add(true);
				    while(rs.next()){	   
				    	  goiToReturn = new GOI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
						  returnMsg.add(goiToReturn);
				       }
				     client.sendToClient(returnMsg);
				     rs.close();
			 break;	 
			  //Default case - handles all the invalid selections should they occur
			 default:
				 LogHandling.logging("Error: "+ userName.getUserName() +" User selected an invalid search option");
				 returnMsg.add(false);
				 returnMsg.add("Error: Invalid Selection");
				 client.sendToClient(returnMsg);
		     break;
			}
			
		}
		/*
		 * Handling of the SQLException - 1. Saving the appropriate data in the Log
		 *                                2. Sending a message to the user informing him of the problem and how to handle it
		 * */
		 catch (SQLException e) {
			 LogHandling.logging("Error:"+userName.getUserName() +"Encountered a problem while searching in the GOI Database");
			 LogHandling.logging("Serach Parmeter: " + option +"Serach Index: " + searchParameter);
			 e.printStackTrace(); 
			 returnMsg.add("MyBox Encountered an Error!");
		     returnMsg.add("Please Contact Technical Support");
		     client.sendToClient(returnMsg);
		}
     }   
     
     
     /**makeARequest - will be responsible for handling the users request's to join the GOI avilable in the system
      * @author Ido Saroka 300830973
      * @param userName - the user's user name in the MyBox system
      * @param goiName - the name of the Group of interest the user wish to join to
      * @param description - optional -> if the user wishes to add a description as to why he thinks hi's request should be authorized 
      * otherwise this value will be set to NULL
      * <p>
      * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
      * @exception SQLException e 
      * @exception IOException e  
      * **/  
     private static void makeARequest(User userName,String goiName,String description) throws IOException{
    			PreparedStatement statement = null;
    			ResultSet rs = null;
    			
    			/**TEst**/
    			System.out.println("reached the function makeARequest\n");
    			System.out.println(userName.getUserName()+"\n");
    			System.out.println(goiName+"\n");
    			System.out.println(description+"\n");
    			
    			try {
    				/*
    				 * The following statement checks if the GOI that the user is requesting to join actually exist, if
    				 * not an appropriate message will be sent to the user
    				 * */
    				 statement = conn.prepareStatement("SELECT GOI_id,GOI_Name From GOI WHERE GOI_Name = ?");
    				 statement.setString(1, goiName); 
    				 rs=statement.executeQuery();
    				 if(!rs.isBeforeFirst()){
    					 rs.close();
    					 returnMsg.add("Error: Goi does not exist");
    					 client.sendToClient(returnMsg);
    					 return;
    				 }
    				 rs.next();
    				 int goiId = rs.getInt(1);
    				 System.out.println("GOI Id: "+goiId+"\n");
    				 /*
     				 * The following statement checks if the user is already a member in the group of interest (GOI) he is requesting to join
     				 * If the user is already a member the system will sent him a message stating so
     				 * */		 
    				 statement = conn.prepareStatement("SELECT * FROM UsersInGOI WHERE GOI_id = ? AND user_Name = ?");
    				 statement.setInt(1, goiId);
    				 statement.setString(2, userName.getUserName());
    				 rs=statement.executeQuery();
    				 if(rs.isBeforeFirst()){
    					 System.out.println("Entered this loop");
    					 rs.close();
    					 returnMsg.add("Error: You are allready a memeber in this Group Of Interest");
    					 client.sendToClient(returnMsg);
    					 return;
    				 }
    				 				 
    				 System.out.println("Passed the check - use is not a member in this goi");
    				 /*
      				 * The following statement checks if the user has already made a request to join this GOI.
      				 * If the user has already sent a request the system will send him a message stating so.
      				 * */	
    				 statement = conn.prepareStatement("SELECT * From request WHERE userName = ? AND GOI_Name = ?");
    				 statement.setString(1, userName.getUserName()); 
    				 statement.setString(2, goiName);
    				 rs=statement.executeQuery();
    				 if(rs.isBeforeFirst()){
    					 rs.close();
    					 returnMsg.add("Error: You have already made a request to join this Group of interest," +
    					 		"Your request is pending for the Admin's answer");
    					 client.sendToClient(returnMsg);
    					 return;
    				 }
    				 
    				 /*
       				 * The following statement will insert the user's request to the appropriate mySQL table (request)
       				 * if all the previous checks were passed successfully.
       				 * */	
    				 System.out.println("Recehed the request insert statement \n");
    				 
    				 statement = conn.prepareStatement("INSERT INTO Request (userName,GOI_Name,description) VALUES (?,?,?)");
    				 statement.setString(1, userName.getUserName());
    			     statement.setString(2, goiName);	
    			     statement.setString(3, description);
    			     statement.executeUpdate();
    			     returnMsg.add("Request was sent successfully");
    			     client.sendToClient(returnMsg);
    			     return;
    			     
    			} 
    			/*
    			 * Handling of the SQLException - 1.saving the appropriate data in the Log
    			 *                                2. Sending a message to the user informing him of the problem and how to handle it
    			 * */
    			catch (SQLException e) {
    				   LogHandling.logging("Error:"+ userName +"trying to make a request to join GOI" + goiName);
    				   LogHandling.logging(e.getMessage());
    				   e.printStackTrace(); 
    				   returnMsg.add("MyBox Encountered an Error!");
    				   returnMsg.add("Please Contact Technical Support");
    				   client.sendToClient(returnMsg);
    			}
     }
     
     
     /**searchSharedFiles - will be responsible for searching the shared files in the Group of interests the user is a member in
      * @author Ido Saroka 300830973
      * @param userName - the user's user name in the MyBox system
      * @param option - the search option the user wishes the search to be performed by
      * @param searchParameter - the parameter by which to perform the search
      * <p>
      * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
      * @exception SQLException e - the function will throw an SQLException in case there will be a problem searching the database
      * @exception IOException e - the function will throw an IOException in case there is a problem sending the data back to the user
      * **/  
 	private static void searchSharedFiles(User userName,String option, String searchParameter) throws IOException{
 		FileToView newFileToView = null;
		Statement stmt = null;
		PreparedStatement statement = null;
		ArrayList<Integer> groupIds = new ArrayList<>();
		ResultSet rs = null;
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			switch(option){
			
			 //AllFiles - will return all the files currently shared with the user from all the groups of interests he is a member in
			case "AllFiles":
				/*
				* This loop will print all the files currently associated will all of MyBox Users
				  (GOI_id = 0 -> file is associated with all users)
				*/
				statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE GOI_id = 0");
				rs=statement.executeQuery();
				returnMsg.add(true);
				while(rs.next()){
					/*
					 * Description:
					 * rs.getInt(1) - GOI ID  * rs.getInt(2) - File ID  * rs.getString(3) - File Name  * rs.getString(4) - File's Suffix
					 * rs.getString(5) - File Owner  * rs.getString(6) - Virtual Path (Where is the file located in the server)
					 * rs.getString(7) - File's Description * rs.getString(8) - Does this Group have an edit permission for the file from the File's Owner? (boolean)
					 * */
					newFileToView = new FileToView(rs.getInt(1),rs.getInt(2),rs.getString(3),
							rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
							rs.getBoolean(8));
					returnMsg.add(newFileToView);
					flag = true;
				}
				statement = conn.prepareStatement("SELECT GOI_id From usersingoi WHERE user_Name = ?");
				statement.setString(1, userName.getUserName()); 
				rs=statement.executeQuery();
				
				 // Receive all the groups the user appears in
				while(rs.next()){
					groupIds.add(rs.getInt(1));
					flag = true;
				}		
				 //This loop will print all the files associated with the groups the user is a member 
				for (int var : groupIds){
					statement = conn.prepareStatement("SELECT * From FilesInGOI Where GOI_id = ?");
				    statement.setInt(1, var); 
					rs=statement.executeQuery();
					 while(rs.next()){
							/*
							 * Description:
							 * rs.getInt(1) - GOI ID * rs.getInt(2) - File ID  * rs.getString(3) - File Name
							 * rs.getString(4) - File's Suffix  * rs.getString(5) - File Owner  * rs.getString(6) - Virtual Path (Where is the file located in the server)
							 * rs.getString(7) - File's Description  * rs.getString(8) - Does this Group have an edit permission for the file from the File's Owner? (boolean)
							 * */
						     newFileToView = new FileToView(rs.getInt(1),rs.getInt(2),rs.getString(3),
							 rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
							 rs.getBoolean(8));
							 returnMsg.add(newFileToView);
						     flag = true;
						} 
				 }			
				//If the groups of interest the user is a member in have no fies in them
				 if(flag == false){
					    rs.close();
					    returnMsg.add(false);
					    returnMsg.add("No files are currently shared with you or the group of interests you are a member in!");
						client.sendToClient(returnMsg);
						break;
					 }
				 rs.close();
				 client.sendToClient(returnMsg);
				 break;
			 //Group - will be used should the user wants to only search inside the shared files of a specific group
			case "Group":
				statement = conn.prepareStatement("SELECT GOI_id,GOI_Name From GOI WHERE GOI_Name = ?");
				statement.setString(1, searchParameter); 
				rs=statement.executeQuery();	
				if(!rs.isBeforeFirst()){//Handles the case where the GOI inputed by the user does not exist
					 rs.close();
					 returnMsg.add(false);
					 returnMsg.add("Error: GOI"+ searchParameter +"does not exist!");
					 client.sendToClient(returnMsg);
					 return;
				}
				rs.next();
			    int groupNumber = rs.getInt(1); /**groupNumber - will hold the groups identification number as it appears in the database**/
			    
			     //Checks that the user is a member in this Group of Interests 
			    statement = conn.prepareStatement("SELECT GOI_id,user_Name From usersingoi WHERE GOI_id = ? AND user_Name = ?");
			    statement.setInt(1, groupNumber);
				statement.setString(2, userName.getUserName());
				rs=statement.executeQuery();
				if(!rs.isBeforeFirst()){/**Handles the case where the user is not a member of the group**/
					rs.close();
					returnMsg.add(false);
					returnMsg.add("Error: User "+ userName +" is not a member of Group " + searchParameter);
					client.sendToClient(returnMsg);
					break;
				}
				statement = conn.prepareStatement("SELECT * From FilesInGOI Where GOI_id = ?");
				    statement.setInt(1, groupNumber); 
					rs=statement.executeQuery();
					
					if(!rs.isBeforeFirst()){
						returnMsg.add(false);
						returnMsg.add("There are currentliy no Files shared with This group");
					}
					returnMsg.add(true);
					 //This while loop will print all the files currently shared with this group			
					while(rs.next()){
						/*
						 * Description:
						 * rs.getInt(1) - GOI ID * rs.getInt(2) - File ID * rs.getString(3) - File Name * rs.getString(4) - File's Suffix
						 * rs.getString(5) - File Owner * rs.getString(6) - Virtual Path (Where is the file located in the server)
						 * rs.getString(7) - File's Description * rs.getString(8) - Does this Group have an edit permission for the file from the File's Owner? (boolean)
						 * */
						newFileToView = new FileToView(rs.getInt(1),rs.getInt(2),rs.getString(3),
						                               rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
								                       rs.getBoolean(8));
								 returnMsg.add(newFileToView);
					}
					client.sendToClient(returnMsg);
					rs.close();
				break;
				
			    default:
			    	LogHandling.logging("Error: User"+ userName +"selected an invalid search option");
			    	rs.close();
			    	returnMsg.add(false);
					returnMsg.add("Error: Invalid Selection");
					client.sendToClient(returnMsg);
			     break;
			}
			/*
			 * Handling of the SQLException - 1.saving the appropriate data in the Log
			 *                                2. Sending a message to the user informing him of the problem and how to handle it
			 * */
	     }catch (SQLException e) {
	     LogHandling.logging("Error:"+ userName +"Encountered a problem while searching in the GOI Database");
		 LogHandling.logging("search Parmeter: " + option +"Serach Index: " + searchParameter);
		 LogHandling.logging(e.getMessage());
		 e.printStackTrace(); 
		 returnMsg.add(false);
		 returnMsg.add("MyBox Encountered an Error!");
	     returnMsg.add("Please Contact Technical Support");
	     client.sendToClient(returnMsg);
	     return;
	    }
	}
 	
    /**removeUserFromGOI - will be responsible for handling the request of a user to be removed from a GOI
     * @author Ido Saroka 300830973
     * @param userName - the user's user name in the MyBox system
     * @param option - the search option the user wishes the search to be performed by
     * @param searchParameter - the parameter by which to perform the search
     * <p>
     * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
     * @exception SQLException e - the function will throw an SQLException in case there will be a problem writing to the database
     * @exception IOException e - the function will throw an IOException in case there will be a problem writing to the log file
     * **/
	private static void removeUserFromGOI(User userName, GOI goiName) throws IOException{
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet rs = null;
		try{
			//this statement will check that the GOI received does indeed exist
			 statement = conn.prepareStatement("SELECT GOI_id,GOI_Name From GOI WHERE GOI_Name = ?");
			 statement.setString(1, goiName.getName()); 
			 rs=statement.executeQuery();	 
			 if(!rs.isBeforeFirst()){	//this condition will return the appropriate message to the user	 
				 returnMsg.add("Error: Goi does not exist");
				 client.sendToClient(returnMsg);
				 rs.close();
				 return;
			 } 
			 	 
			//this statement will check that the user is indeed a member in the mentioned GOI
			 statement = conn.prepareStatement("SELECT * From UsersInGOI WHERE GOI_id = ? AND user_Name = ?");
			 statement.setInt(1, goiName.getID());
			 statement.setString(2, userName.getUserName());
			 rs=statement.executeQuery();
			 if(rs.isBeforeFirst()){
					 statement2 = conn.prepareStatement("DELETE FROM UsersInGOI WHERE GOI_id = ? AND user_Name = ?");
					 statement2.setInt(1, goiName.getID());
					 statement2.setString(2, userName.getUserName());
					 statement2.executeUpdate();
					 returnMsg.add("You have been succesfulliy removed from GOI: "+ goiName.getName());
					 client.sendToClient(returnMsg);
					 rs.close();
					 return;
			}
			 returnMsg.add("Error: You are not a member of GOI: " + goiName.getName());
			 client.sendToClient(returnMsg);
			 rs.close();
			 return;
			 /*
			  * Handling of the SQLException | IOException - 1.saving the appropriate data in the Log
			  *                                              2. Sending a message to the user informing him of the problem and how to handle it
			  **/
		}catch (SQLException | IOException e){
		  LogHandling.logging("Error:"+ userName.getUserName() +"Encountered a problem while trying to remove himself from GOI: " + goiName.getName());
		  LogHandling.logging(e.getMessage());
		  returnMsg.add("MyBox Encountered an Error!");
		  returnMsg.add("Please Contact Technical Support");
		  client.sendToClient(returnMsg);  
		} 
	}

    /**downloadSharedFile - will allow a user in a GOI to download a file that is shared with him
     * @author Ido Saroka 300830973
     * @param goiShared - the group of interest the file is shared with
     * @param userName - the user's user name in the MyBox system
     * @param sharedFile - the details of the file the user wishes to download
     * <p>
     * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
     * @exception IOException e - the function will throw an IOException if there is a problem creating a File Object to send back to the user
     * **/
  private static void downloadSharedFile( User userName,int goiShared, FileToView sharedFile) throws IOException{
	PreparedStatement statement = null;
	ResultSet rs = null;
	String fullPath=sharedFile.getVirtualPath()+"\\"+sharedFile.getFileName()+"."+sharedFile.getFileSuffix();
	File f = new File(fullPath);
	
	try{
		//check if the file is shared with all the users in the system
		statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE GOI_id = 0 AND file_id = ?");
		statement.setInt(1, sharedFile.getFileID());
		rs =statement.executeQuery();
		if(rs.isBeforeFirst()){
			Browse newBrowse = new Browse(f, sharedFile.getFileName(),sharedFile.getFileSuffix());
			MyFile down = newBrowse.getFile(); 
			returnMsg.add(true);
			returnMsg.add(down);
			client.sendToClient(returnMsg);
			rs.close();
			return;
		}		
		//check if the file is shared with everyone
		if(goiShared == 0){
			//send the file back to the user
			Browse newBrowse = new Browse(f, sharedFile.getFileName(),sharedFile.getFileSuffix());
			MyFile down = newBrowse.getFile(); 
			returnMsg.add(true);
			returnMsg.add(down);
			client.sendToClient(returnMsg);
			return;
		}
		
		//check if the user is a member in this GOI
		if((isMemberOfGOI(goiShared,userName)==false)||(isFileSharedWithGOI(goiShared,sharedFile)== false)){
			LogHandling.logging("User: "+userName +" encountered a problem while trying to download file: "+sharedFile.getFileName()+sharedFile.getFileSuffix());
			LogHandling.logging("Authentication Problem");
			returnMsg.add(false);
			returnMsg.add("MyBox Encountered an Error!");
		    returnMsg.add("Please Contact Technical Support");
		    client.sendToClient(returnMsg);
		    return;
		}
		//send the file back to the user
		Browse newBrowse = new Browse(f, sharedFile.getFileName(),sharedFile.getFileSuffix());
		MyFile down = newBrowse.getFile(); 
		returnMsg.add(true);
		returnMsg.add(down);
		client.sendToClient(returnMsg);
		return;
	    }catch(SQLException |IOException e){
		LogHandling.logging("Error:"+ userName.getUserName() +"Encountered a problem while trying to retrieve his GOIs");
		returnMsg.add(false);
		returnMsg.add("MyBox Encountered an Error!");
	    returnMsg.add("Please Contact Technical Support");
	    client.sendToClient(returnMsg);
	    return;
	    }  
	}
  
    /**editSharedFile - will be used by a user in order to update a file 
     * @author Ido Saroka 300830973
     * @param goiShared - the group of interest the file is shared with
     * @param userName - the user's user name in the MyBox system
     * @param sharedFile - the details of the file the user wishes to update
     * <p>
     * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
     * @exception IOException e - the function will throw an IOException if there is a problem uploading the file to the server
     * **/
 	private static void editSharedFile(int goiShared, User userName, FileToView sharedFile) throws IOException{
 		System.out.println("Got to editSharedFile");
 		String path = sharedFile.getVirtualPath();
 		File f = new File(path+"\\"+sharedFile.getFileName()+"."+sharedFile.getFileSuffix());
 		PreparedStatement statement;  
 		ResultSet rs;
 		ArrayList<Integer> groupIdsToInform = new ArrayList<>();
 		HashSet<String> usersToInform = new HashSet<String>();
 		DateFormat dateFormat;
 		Date date = new Date();
 		String time;
 		try{
 			System.out.println("Got to editSharedFile - before if condition");
 			if(isMemberOfGOI(goiShared, userName)== false || isFileSharedWithGOI(goiShared, sharedFile)== false 
 					|| isAvilableToEdit(goiShared, sharedFile) == false || Security.secuirtyCheck(userName, conn) == false){
 				returnMsg.add(false);
 				returnMsg.add("MyBox Encountered an Error!");
 			    returnMsg.add("Please Contact Technical Support");
 			    client.sendToClient(returnMsg);
 			    return;
 			}
 			System.out.println("Got to editSharedFile - end if condition");
 			
 			Browse newBrowse = new Browse(f, sharedFile.getFileName(),sharedFile.getFileSuffix());
 			MyFile changedFile = newBrowse.getFile();
 			Save save=new Save(changedFile,path+"\\");
 			
 			statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE file_id = ?");// <- change
 			 statement.setInt(1, sharedFile.getFileID());
 			 rs = statement.executeQuery();
 			 while(rs.next()){
 				 groupIdsToInform.add(rs.getInt(1)); // get all the id's of the GOI the file is shared with
 			 }
 			//find all the users that the file is associated with 
 			 //Note that in case the file is shared with the same user from two different groups he will only be notified once 
 			 for (int var : groupIdsToInform){
 				 statement = conn.prepareStatement("SELECT * From UsersInGOI Where GOI_id = ?");
 				 statement.setInt(1, var); 
 				 rs = statement.executeQuery();
 				 while(rs.next()){
 					  usersToInform.add(rs.getString(2));
 				 }
 			 }
 				 //Receives the current date in order to save inside the message
 				 dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");	 
 				 time=dateFormat.format(date);
 				//Insert the message about the file changes to all relevant users
 					for (String user : usersToInform) {
 						 statement = conn.prepareStatement("INSERT INTO UserMessages (message_Date,userName,description) VALUES (?,?,?)");	
 						 statement.setString(1, time);
 						 statement.setString(2, user);
 						 statement.setString(3, "File: "+ sharedFile.getFileName() +" Was Changed by: "+userName.getUserName()+"\n "
 						 		+ "From GOI: "+ goiShared);
 						 statement.executeUpdate();
 					}
		
 			returnMsg.add(true);
 			returnMsg.add("File: "+sharedFile.getFileName()+" was successfully updated!");
 			client.sendToClient(returnMsg);
 			return;
 		}catch(SQLException |IOException e){
 			LogHandling.logging("Error:"+ userName.getUserName() +"Encountered a problem while trying to update file: "+ sharedFile.getFileName()+sharedFile.getFileSuffix());
 			LogHandling.logging("GOI: "+goiShared);
 			returnMsg.add(false);
 			returnMsg.add("MyBox Encountered an Error!");
 		    returnMsg.add("Please Contact Technical Support");
 		    client.sendToClient(returnMsg);
 		    return;
 		} 
 	}
 	
  /**returnUserGois - will return the user the GOIs he is currently a member in
   * @author Ido Saroka 300830973
   * @param userName - the user's user name in the MyBox system
   * <p>
   * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
   * @exception IOException e - the function will throw an IOException if there is a problem sending back the GOIs to the user
   * **/
  private static void returnUserGois(User userName) throws IOException{
	  ArrayList<Integer> gois= new ArrayList<>();
	  PreparedStatement statement = null;
	  ResultSet rs = null;
	  GOI goiToSend;
	  System.out.println("got to returnUserGois\n");
	  try{
		  statement = conn.prepareStatement("SELECT * FROM UsersInGOI WHERE user_Name = ?");
		  statement.setString(1, userName.getUserName());
		  rs = statement.executeQuery();
		  if(!rs.isBeforeFirst()){
			  returnMsg.add(false);
			  returnMsg.add("You are currentliy not a member in any Group Of Intreset");
			  client.sendToClient(returnMsg);
		  }
		  System.out.println("Got to While loop\n");
		  returnMsg.add(true); 
		  while(rs.next()){
			  gois.add(rs.getInt(1));	  
			  System.out.println("GOIS: "+ rs.getInt(1)+"\n");
		  }
		  System.out.println("Exit While loop\n");
		  for(Integer var : gois){
			  statement = conn.prepareStatement("SELECT * FROM GOI WHERE GOI_id = ?");
			  statement.setInt(1, var);
			  System.out.println("Var values is: "+var+"\n");
			  rs = statement.executeQuery();
			  rs.next();
			  goiToSend = new GOI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			  System.out.println(rs.getInt(1));
			  returnMsg.add(goiToSend);
		  }
		  
		  System.out.println("TEST returnUserGois");
		  System.out.println(returnMsg.get(0) + "\n");
		  System.out.println(((GOI)returnMsg.get(1)).getName());
		  
		  client.sendToClient(returnMsg);
	  }catch(SQLException | IOException e){	  
		  returnMsg.add(false);
		  returnMsg.add("MyBox Encountered an Error!");
		  returnMsg.add("Please Contact Technical Support");
		  client.sendToClient(returnMsg);
	  }
  }
 	
    /**isMemberOfGOI - will check if the user is a member of a specific GOI
     * @author Ido Saroka 300830973
     * @param goiShared - the name of the group of interest 
     * @param userName - the user's user name in MyBox
     * <p>
     * @throws SQLException - the function will throw an SQLException in case there is a problem searching the database 
     * @return boolean - the function will return true or false depending on the result
     * **/
	private static boolean isMemberOfGOI(int goiShared, User userName) throws IOException{
 		PreparedStatement statement = null;
 		ResultSet rs = null;
 		System.out.println("got to isMemberOfGOI");
 		try{
 		    statement = conn.prepareStatement("SELECT * From UsersInGOI WHERE GOI_id = ? AND user_Name = ?");
		    statement.setInt(1, goiShared);
		    statement.setString(2,userName.getUserName());
		    rs = statement.executeQuery();
		 if(!rs.isBeforeFirst()){
			System.out.println("Enterd to isMemberOfGOI fail condition");
			rs.close();
		 	return false;
		 }
		 System.out.println("passed the isMemberOfGOI condtion ");
		 return true;
 		}	
 		catch(SQLException e){
 			LogHandling.logging("Error:"+ userName.getUserName() +"Encountered a problem Performing security checks");
 			return false;
 		}
 	}
	
	   /**isFileSharedWithGOI - will check if a specific file is shared with a group of interest
     * @author Ido Saroka 300830973
     * @param goiShared - the name of the group of interest 
     * @param sharedFile - the details of the requested file
     * <p>
     * @throws SQLException - the function will throw an SQLException in case there is a problem searching the database 
     * @return boolean - the function will return true or false depending on the result
     * **/
	private static boolean isFileSharedWithGOI(int goiShared,FileToView sharedFile) throws IOException{
		System.out.println("got to isFileSharedWithGOI");
		System.out.println("goi Shared: "+ goiShared);
		PreparedStatement statement = null;
 		ResultSet rs = null;
 		try{
 			statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE GOI_id = ? AND file_id = ?");
 			statement.setInt(1, goiShared);
 			statement.setInt(2, sharedFile.getFileID());
 			rs = statement.executeQuery();
 			if(!rs.isBeforeFirst()){
 				System.out.println("Enterd to isFileSharedWithGOI fail condition");
 				LogHandling.logging("File: "+ sharedFile.getFileName()+sharedFile.getFileSuffix()+" is not shared with group: " + goiShared);
 				rs.close();
 				return false;
 			}
 			System.out.println("passed the isFileSharedWithGOI condtion ");
 			return true;
 		}catch(SQLException e){
 			LogHandling.logging("Error: My Box Encountered a problem Performing security checks for file: "+ sharedFile.getFileName()+sharedFile.getFileSuffix());
 			return false;
 		}
 	}
	
	  /**isAvilableToEdit - will check if a specific file is available for editing
     * @author Ido Saroka 300830973
     * @param goiShared - the name of the group of interest 
     * @param sharedFile - the details of the requested file
     * <p>
     * @throws SQLException - the function will throw an SQLException in case there is a problem searching the database 
     * @return boolean - the function will return true or false depending on the result
     * **/
	private static boolean isAvilableToEdit(int goiShared, FileToView sharedFile) throws IOException{
		PreparedStatement statement = null;
 		ResultSet rs = null;
 		System.out.println("got to isAvilableToEdit");
 		try{
 			statement = conn.prepareStatement("SELECT * From FilesInGOI WHERE GOI_id = ? AND file_id = ?");
 			statement.setInt(1, goiShared);
 			statement.setInt(2, sharedFile.getFileID());
 			rs = statement.executeQuery();
 			if(!rs.isBeforeFirst()){
 				System.out.println("Enterd to isAvilableToEdit fail condition");
 				LogHandling.logging("File: "+ sharedFile.getFileName()+sharedFile.getFileSuffix()+" is not shared with this group");
 				rs.close();
 				return false;
 			}
 			if(rs.getInt(8)==0){
 				LogHandling.logging("File: "+ sharedFile.getFileName()+sharedFile.getFileSuffix()+" is not avilable for editing for this group");
 				return false;
 			}
 			System.out.println("passed the isAvilableToEdit condtion ");
 			return true;
 		}catch(SQLException | IOException e){
 			LogHandling.logging("Error: My Box Encountered a problem Performing security checks for file: "+ sharedFile.getFileName()+sharedFile.getFileSuffix());
 			return false;
 		}		
	}
}
