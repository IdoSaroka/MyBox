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
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;


import entities.FileOwnerViewer;
//import main.ServerGUI;
import entities.User;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import entities.Messages;
import entities.Login;


/**This Class establish the connection to the DB and creates the server 
 * @author Ido Saroka 300830973
 * @param port - the port issued by the user to be opened and receive connections
 * @param DBConn - the connection to the Database
 * <p>
 * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
 * @exception IOException -
 * @return returnMsg - the function will return a message to the user if the log in process was successful or not
 * **/
public class EchoServer extends AbstractServer implements Serializable
{
  public static final int DEFAULT_PORT = 3306;
  
  private Connection DBConn;
  
  public static String serverUserName;
  
  public static String serverUserPassword;
  
  public static int serverPort = 1254 ;
  
  public EchoServer(int port, Connection DBConn)
  {
    super(port);
    this.DBConn = DBConn;
  }
  
  public Connection getDBConn()
  {
    return this.DBConn;
  }
  
  public void handleMessageFromClient(Object msg, ConnectionToClient client) throws IOException
  {
    
    Scanner sc = new Scanner(System.in);
    ArrayList<Object> message = (ArrayList)msg;
    ArrayList<Object> returnMsg = (ArrayList)msg;
   
    Connection conn = getDBConn();
    
    returnMsg =  new ArrayList<>();
    String str = (String)message.get(0);
    
    //Test
    for(int i=0; i<message.size();i++)
    	System.out.println(message.get(i));
    
    
    
    switch(str){
    
    
    
     //Login - check the case the user wish to perform a login operation
    case "Login": 
    	try {
			//checkLogin((String)message.get(1),(String)message.get(2),conn,client);
    		checkLogin((Login)message.get(1),conn,client);
		} catch (IOException e1) {
			client.sendToClient("an error has occured while trying to Access Mybox!");
			
			e1.printStackTrace();
		}
    	break;
    case "RetreiveMessages":
    	//String userName, Connection conn,ConnectionToClient client)
    	returnUserMessages((String)message.get(1),conn,client);
    	break;
    	
    	  //GOIBasic - is responsible for handling all of the GOI "Basic" functions (i.e. those that are available to all of MyBox users )
    case "GOIBasic":
    	GoiBasic handler = new GoiBasic(msg,client,conn); 
    	try { GoiBasic.options(); } 
    	catch (IOException e1) {
    		LogHandling.logging(e1.getMessage());
			e1.printStackTrace();
			client.sendToClient("an error has occured while trying to Access Mybox!");
		}
        break;
        
    //Admin - will contain all of MyBox Administrative functions - accessible only for the systems Admin	
    case "Admin":
    	 //This condition is a security check in case a user which is not a admin tries to access the system   	 
    	if(("SystemAdmin").equals((String)message.get(1))){
    		LogHandling.logging("Admin: " + ((User)message.get(2)).getUserName() + " is logged in the system");
    		GoiAdmin AdminHandler = new GoiAdmin(msg,client,conn); 
    		try { GoiAdmin.options(); } 
        	catch (IOException e1) {
        		LogHandling.logging(e1.getMessage());
    			e1.printStackTrace();
    			client.sendToClient("an error has occured while trying to Access Mybox!");
    		}
    	}
    	/*If a unauthorized access was attempted the system will register the user's details*/
    	else{ 		
    		LogHandling.logging("Security Violation: User: " + (String)message.get(3) + "Tried to access the system!");
    		client.sendToClient("an error has occured while trying to Access Mybox!");
    	}	
    	break;
        
    	
        
     //File - used when a file owner chooses to access and change his files 
    case "File":
    	FilesHandler fileHandler = new FilesHandler(msg,client,conn); 
    	FilesHandler.options();
        break;
        
        
         //Folder - will be used for the creation and deletion of folders by the users
    case "Folder":
    	FolderHandler folderHandler = new FolderHandler(msg,client,conn); 
    	FolderHandler.options();
    	break;
    	
    	
    	
        //SignOut - used when a logged in user chooses to sign out
    case "SignOut":
    	 /*Expected message from Shimon: string(0) = SignOut , string(1) = userName String(2) = UserName */
    	try { signOutUser((String)message.get(1),conn,client); } 
    	catch (SQLException e1) {
    		LogHandling.logging("Error:System could not sign out User " + (String)message.get(2));
    		e1.printStackTrace();
    		client.sendToClient("an error has occured while trying to Access Mybox!");
		}
    	break;
    	
   
        
    
         //default - when the server receive a message the deviates from the expected input
    default:
    	try {
    		returnMsg.add("Error:Invalid selection");
			client.sendToClient(returnMsg);
		}catch (IOException e) {
			e.printStackTrace();
		}
    	break;
    }
  }
  
 

protected void serverStarted()
  {
    System.out.println(
      "Server listening for connections on port " + getPort());
  }
  
  protected void serverStopped()
  {
    System.out.println("Server has stopped listening for connections.");
  }
  
  
  
  /**main function - will establish a connection with MyBox Database and will open
   * a listing port for the program's users
   * @author Ido Saroka 300830973
   * <p>
   * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
   * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
   * @exception IOException e -
   * **/   
  //public static void main(String[] args) throws IOException
  public static void createConnection() throws IOException
  {
	  	  
	ArrayList<String> returnMsg = new ArrayList();
    Connection conn = null;
    int port = 0;
    //DbAdapter adapter = new DbAdapter();
    
     //setUp - create the necessary folders used by MyBox
    LogHandling.setUp(); 
    try
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    }
    catch (Exception localException1) {
    	LogHandling.logging(localException1.getMessage());
    }
    try
    {
       conn = DriverManager.getConnection("jdbc:mysql://localhost/myboxdatabase", serverUserName, serverUserPassword);
      //conn = DriverManager.getConnection("jdbc:mysql://localhost/myboxdatabase", "root", "123456"); //<- need to remove connection to Ido's DataBase
      
      /**insert GUI OBJECT that recives user name and password for a DataBase**/
      //conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + '"',dbUserName,dbPassword);
      
      LogHandling.logging("Successfully connected to MyBox Database");
      System.out.println("SQL connection succeed\n");
      
      //Successfully
    }
    catch (SQLException ex)
    {
      LogHandling.logging("SQLException: " + ex.getMessage());
      System.out.println("SQLException: " + ex.getMessage());
      
      LogHandling.logging("SQLState: " + ex.getSQLState());
      System.out.println("SQLState: " + ex.getSQLState());
      
      LogHandling.logging("VendorError: " + ex.getErrorCode());
      System.out.println("VendorError: " + ex.getErrorCode());
      
    } catch (IOException e) {
    	LogHandling.logging("IOException: " + e.getMessage());
		e.printStackTrace();
	}
    try
    {
    	
      /*add a GUI elemnet that recives a port from the user*/
    	
      //port = ();
    	port = serverPort;
    	
    	System.out.println(" Server port is: " + serverPort);
      
    }
    catch (Throwable t)
    {
      port = 1254;
    }
    //EchoServer sv = new EchoServer(port, conn);
    EchoServer sv = new EchoServer(serverPort, conn);
    try
    {
      sv.listen();
    }
    catch (Exception ex)
    {
      System.out.println("ERROR - Could not listen for clients!");
      LogHandling.logging("ERROR - Could not listen for clients!");
      LogHandling.logging(ex.getMessage());
    }
  }
  

  /**checkLogin - will check the that details (user name and password) inputed by  
   * the users corresponds with the one found inside MyBox Database
   * @author Ido Saroka 300830973
   * @param login - a Login object containing the inputed user name and password
   * @param conn - the function will receive a connection to the database
   * @param client - the function will receive a connection to the client
   * <p>
   * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
   * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
   * @exception IOException e -
   * **/   
  public static void checkLogin(Login login, Connection conn, ConnectionToClient client) throws IOException{
	    PreparedStatement stmt = null;
	    ArrayList<Object> returnMsg = new ArrayList();
	    PreparedStatement preparedStatement = null;
	    
	    if(isUserValid(login.getUserName()) == false){
	    	returnMsg.add("Error:Invalid Charcters in inputed user name");
      		client.sendToClient(returnMsg);
      		return;
	      }
	    
	    if(isPasswordValid(login.getPassword()) == false){
	    	 returnMsg.add("Error:Invalid Charcters in inputed password");
	      	 client.sendToClient(returnMsg);
	      	 return;
	      }
	    
	    try
	    {
	      stmt = conn.prepareStatement("SELECT * From users");
	      String query = "Update users set loggedIn = ? where userName = ?";
	      ResultSet rs = stmt.executeQuery();
	      while (rs.next()) { /*search the current users in the system*/
	        if ((login.getUserName().equals(rs.getString(1)))){ /*User Name appears in the Database*/
	        	if(login.getPassword().equals(rs.getString(2))){/*Password is correct*/
	        	
	        		  if(rs.getBoolean(5)== false){  // loggedIn == false -> user is not logged in
	        		    /*add the data to the log file*/
	      
	        		    preparedStatement = conn.prepareStatement(query);
	        		    preparedStatement.setInt(1, 1);
	        	    	preparedStatement.setString(2, login.getUserName());
	        		    preparedStatement.executeUpdate();
	        		    /*
	        		     * The function will return a "User object" to the logged in user this is to better help with security issues 
	        		     * Description:
	        		     * rs.getString(1) - userName   rs.getString(2) - email   rs.getString(3) - password  rs.getString(4) - role
	        		     * */
	        		    Object detailsToSend = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
	        		    boolean success = (new File("C:\\MyBox\\Files\\"+login.getUserName())).mkdirs();
	        		    LogHandling.logging("User: " + login.getUserName() +" logged in");
	        	    	returnMsg.add(detailsToSend);
	        	    	returnMsg.add(userHasMessages(rs.getString(1),conn));
	        		    
	        	    	
	        	      	rs.close();
	        		    client.sendToClient(returnMsg);
	        		     return;
	                	}
	        	   else{ //loggedIn == true -> user is already logged in
		        		returnMsg.add("Error:User is allready logged in to the system");
		        		rs.close();
		        		client.sendToClient(returnMsg);
		        		return;
	        	     }
	        	}
	        	else{//Password is incorrect
        		    returnMsg.add("Error:The password you have entered is incorrect");
        		    rs.close();
        		    client.sendToClient(returnMsg);
        		    return;
	        	}
	        }     
	      }
	      returnMsg.add("Error:No Such User Exist"); /*User Name doesn't appears in the Database*/
	      rs.close();
  		  client.sendToClient(returnMsg);
	      return;
	    }
	    /*
		* Handling of the SQLException | IOException- 1.saving the appropriate data in the Log
		*                                             2. Sending a message to the user informing him of the problem and how to handle it 
	    */
	    catch (SQLException | IOException e)
	    {
	      LogHandling.logging("User: " + login.getUserName() +" could not log in due to a system failure");
	      LogHandling.logging(e.getMessage());
	      returnMsg.add("Error Logging in");
	      returnMsg.add("Please Contact Technical Support");
	      client.sendToClient(returnMsg);
	      e.printStackTrace();
	    }
    }
  
  /**userHasMessages - will check if the user has any new messages he needs to view 
   * @author Ido Saroka 300830973
   * @param userName - the function will receive the user's name
   * @param conn - the function will receive a connection to the database
   * <p>
   * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
   * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
   * @exception IOException e -
   * @return boolean - the function will return true or false depending if the user has any messages or not
   * **/
	public static boolean userHasMessages(String userName, Connection conn){
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement("SELECT * From usermessages WHERE userName = ?");
			statement.setString(1,userName);
			rs=statement.executeQuery();
			if(!rs.isBeforeFirst()){
				return false;
			}
			return true;
		}catch(SQLException e){
			return false;
		}	
	}
	
	  /**returnUserMessages - will return all the new messages the user has
	   * @author Ido Saroka 300830973
	   * @param userName - the function will receive the user's name
	   * @param conn - the function will receive a connection to the database
	   * @param client - the connection to the client used to send messages back to the client
	   * <p>
	   * @throws IOException - the function will throw an IOException in case there will be a problem sending the data back to the user
	   * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
	   * @exception IOException e - the function will throw an IOException in case there will be a problem writing to the log file
	   * **/
	public static void returnUserMessages(String userName, Connection conn,ConnectionToClient client) throws IOException{
		ArrayList<Object> returnMsg = new ArrayList();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Messages userMessage;
		try{
			//Retrieving all the user's messages from the server
			statement = conn.prepareStatement("SELECT * From usermessages WHERE userName = ?");
			statement.setString(1,userName);
			rs=statement.executeQuery();
			if(!rs.isBeforeFirst()){
				returnMsg.add(false);
				returnMsg.add("You have no new messages");
				client.sendToClient(returnMsg);
				rs.close();
				return;
			}
			//rs.next();
			returnMsg.add(true);
			//adding all the user's message to the returned message
			System.out.println("Start of while loop\n");
			int i=0;
			while(rs.next()){
				System.out.println("This is run #"+i);
				//return message.add
			    userMessage = new Messages(rs.getString(4),rs.getString(2));
			    returnMsg.add( userMessage);
			}
			System.out.println("End of while loop\n");
			statement = conn.prepareStatement("DELETE FROM usermessages WHERE userName = ?");
			statement.setString(1,userName);
			statement.executeUpdate();
			client.sendToClient(returnMsg);
			System.out.println("End of Deletion condition\n");
			rs.close();
			return;
		}catch(SQLException | IOException e){
			LogHandling.logging("Error: " + userName +" Encountered an error while trying to receive his messages");
 			LogHandling.logging(e.getMessage());
 			returnMsg.add("Error: Could not retrieve messages please contact technical support!");
    		returnMsg.add("Please Contact Technical Support");
    		client.sendToClient(returnMsg);
		} 
	}
  
  
  /**signOutUser - will be responsible for handling the user's
   * request to log out from the system
  * @author Ido Saroka 300830973
  * @param userName - the function will receive the user's name in the MyBox system
  * @param conn - the function will receive a connection to the database
  * @param client - the function will receive a connection to the client
  * <p>
  * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
  * @throws SQLException - the function will throw an IOException in case there will be a problem writing to the the Database: "Users"
  * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
  * @exception IOException e - the function throws an throw an IOException in case there will be a problem writing to the log file
  * @return returnMsg - the function returns a message to the user should the logout process was successful or if any errors occurred
  * **/   
public static void signOutUser(String userName, Connection conn, ConnectionToClient client) throws SQLException, IOException{
	ArrayList<Object> returnMsg = new ArrayList();
	PreparedStatement statement = null;
	ResultSet rs = null;
	 try{
		 //this statement will return the matching user details
		 statement = conn.prepareStatement("SELECT * From Users WHERE userName = ?");
		 statement.setString(1, userName);
		 rs = statement.executeQuery();
		//this condition handles the situation where a user that does not exist in the system tries to log out from it
		 if(!rs.next()){
			 LogHandling.logging("Error: User"+ userName +"does not exist yet recived access to the system");
			 returnMsg.add("Error: Could not sign out from MyBox!");
   		 returnMsg.add("Please Contact Technical Support");
			 rs.close();
			 return;
		 }
		 //this condition handles the situation where a user who is not logged in tries to perform a logout
		 if(rs.getBoolean(5) == false){
			 LogHandling.logging("Error - User: " + userName +"Is not logged in yet tried to sign out");
   		 returnMsg.add("Error: Could not sign out from MyBox!");
   		 returnMsg.add("Please Contact Technical Support");
   		 client.sendToClient(returnMsg);
			 rs.close();
			 return;
		 }
		 statement = conn.prepareStatement("Update users set loggedIn = false where userName = ?");
		 statement.setString(1, userName);
		 statement.executeUpdate();
		 returnMsg.add("Succesfulliy logged out");
		 client.sendToClient(returnMsg);
		 client.close(); // closes the connection to this client
		 rs.close();
		   /*
			* Handling of the SQLException | IOException - 1. saving the appropriate data in the Log
			*                                              2. Sending a message to the user informing him of the problem and how to handle it
			*/
   } catch (SQLException | IOException e) {
 			LogHandling.logging("Error: " + userName +" is not logged in yet tried to sign out");
 			LogHandling.logging(e.getMessage());
 			returnMsg.add("Error: Could not sign out from MyBox!");
    		returnMsg.add("Please Contact Technical Support");
    		client.sendToClient(returnMsg);
 			e.printStackTrace();
   }
}

private static Boolean isUserValid(String str)
{
	for(int i = 0;i<str.length();i++)
	{
		if( (str.charAt(i)>='0' && str.charAt(i)<='9' ) || ( str.charAt(i)=='_' ) || 
				(str.charAt(i)>='A' && str.charAt(i)<='Z') ||( str.charAt(i)>='a' && str.charAt(i)<='z' ))
			continue;
		else
			return false;
	}
	return true;
}

private static Boolean isPasswordValid(String str)
{
	for(int i = 0;i<str.length();i++)
		if( str.charAt(i)==' ' || str.charAt(i)=='\\' ||  str.charAt(i)=='/' )
			return false;
	return true;
}
    
  
  
}
