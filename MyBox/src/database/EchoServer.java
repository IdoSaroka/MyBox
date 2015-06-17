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
import entities.User;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**This Class establish the connection to the DB and creates the server 
 * @author Ido Saroka 300830973
 * @param port - the port issued by the user to be opened and receive connections
 * @param DBConn - the connection to the Database
 * <p>
 * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
 * @exception IOException -
 * @return returnMsg - the function will return a message to the user if the log in process was successfull or not
 * **/
public class EchoServer extends AbstractServer implements Serializable
{
  public static final int DEFAULT_PORT = 3306;
  
  private Connection DBConn;
  
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
    ArrayList<String> message = (ArrayList)msg;
    ArrayList<String> returnMsg = (ArrayList)msg;
    
    Connection conn = getDBConn();
    
    String str = (String)message.get(0);
    
    switch(str){
    /**
     * Login - check the case the user wish to perform a login operation
     * **/
    case "Login": 
    	try {
			checkLogin((String)message.get(1),(String)message.get(2),conn,client);
		} catch (IOException e1) {
			client.sendToClient("an error has occured while trying to Access Mybox!");
			
			e1.printStackTrace();
		}
    	break;
    	
    	/**
    	 * GOIBasic - is responsible for handling all of the GOI "Basic" functions (i.e. those that are available to all
    	 * of MyBox users )
    	 * **/
    case "GOIBasic":
    	GoiBasic handler = new GoiBasic(msg,client,conn); 
    	try { handler.options(); } 
    	catch (IOException e1) {
    		LogHandling.logging(e1.getMessage());
			e1.printStackTrace();
			client.sendToClient("an error has occured while trying to Access Mybox!");
		}
        break;
        
        
        /**
    	 * Admin - will contain all of MyBox Administrative functions - accessible only for the systems Admin
    	 * **/
        /*Expected message from Shimon: string(0) = Admin string(1) = SystemAdmin(user.role) , string(2) = Option String(3) = UserName
         * String(4) = */
    case "Admin":
    	/**
    	 * This condition is a security check in case a user which is not a admin tries to access the system
    	 * **/
    	if(("SystemAdmin").equals((String)message.get(1))){
    		LogHandling.logging("Admin: " + (String)message.get(3) + " is logged in the system");
    		GoiAdmin AdminHandler = new GoiAdmin(msg,client,conn); 
    		try { AdminHandler.options(); } 
        	catch (IOException e1) {
        		LogHandling.logging(e1.getMessage());
    			e1.printStackTrace();
    			client.sendToClient("an error has occured while trying to Access Mybox!");
    		}
    	}
    	/**If a unauthorized access was attempted the system will register the user's details**/
    	else{ 		
    		LogHandling.logging("Security Violation: User: " + (String)message.get(3) + "Tried to access the system!");
    		client.sendToClient("an error has occured while trying to Access Mybox!");
    	}	
    	break;
        
    	
        /**
         * File - used when a file owner chooses to access his files
         * **/
    case "File":
    	FilesHandler fileHandler = new FilesHandler(msg,client,conn); 
    	FilesHandler.options();
        break;
        
        /**
         * Folder - will be used for the creation and deletion of folders by the users
         * **/
    case "Folder":
    	
    	break;
    	
    	
    	/**
         * SignOut - used when a logged in user chooses to sign out
         * **/
    case "SignOut":
    	 /*Expected message from Shimon: string(0) = SignOut , string(1) = userName String(2) = UserName */
    	try { signOutUser((String)message.get(2),conn,client); } 
    	catch (SQLException e1) {
    		LogHandling.logging("Error:System could not sign out User " + (String)message.get(2));
    		e1.printStackTrace();
    		client.sendToClient("an error has occured while trying to Access Mybox!");
		}
    	break;
    	
   
        
    	/**
         * default - when the server receive a message the deviates from the expected input
         * **/
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
  public static void main(String[] args) throws IOException
  {
	ArrayList<String> returnMsg = new ArrayList();
    Connection conn = null;
    int port = 0;
    DbAdapter adapter = new DbAdapter();
    /**
     * setUp - create the necessary folders used by MyBox
     * **/
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
      conn = DriverManager.getConnection("jdbc:mysql://localhost/myboxdatabase", "root", "Braude");
      //conn = DriverManager.getConnection("jdbc:mysql://localhost/myboxdatabase", "root", "123456"); <- need to remove connection to Ido's DataBase
      
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
    	
      port = Integer.parseInt(args[0]);
    }
    catch (Throwable t)
    {
      port = 1254;
    }
    EchoServer sv = new EchoServer(port, conn);
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
   * @param login - the function will receive the user's name
   * @param password - the function will receive the user's password
   * @param conn - the function will receive a connection to the database
   * @param client - the function will receive a connection to the client
   * <p>
   * @throws IOException - the function will throw an IOException in case there will be a problem writing to the log file
   * @exception SQLException e - the function will throw an SQLException in case there will be a problem accessing MyBox Database
   * @exception IOException e -
   * **/   
  public static void checkLogin(String login, String password, Connection conn, ConnectionToClient client) throws IOException{
	    Statement stmt = null;
	    ArrayList<Object> returnMsg = new ArrayList();
	    PreparedStatement preparedStatement = null;
	    
	    String query = "Update users set loggedIn = ? where userName = ?";
	    try
	    {
	      stmt = conn.createStatement();
	   
	      ResultSet rs = stmt.executeQuery("SELECT * From users");
	      while (rs.next()) { /*search the current users in the system*/
	        if ((login.equals(rs.getString(1)))){ /*User Name appears in the Database*/
	        	if(password.equals(rs.getString(2))){/*Password is correct*/
	        	
	        		  if(rs.getBoolean(5)== false){  /*loggedIn == false -> user is not logged in*/
	        		    /*add the data to the log file*/
	      
	        		    preparedStatement = conn.prepareStatement(query);
	        		    preparedStatement.setInt(1, 1);
	        	    	preparedStatement.setString(2, login);
	        		    preparedStatement.executeUpdate();
	        		    /*
	        		     * The function will return a "User object" to the logged in user this is to better help with security issues 
	        		     * Description:
	        		     * rs.getString(1) - userName
	        		     * rs.getString(2) - email
	        		     * rs.getString(3) - password
	        		     * rs.getString(4) - role
	        		     * */
	        		    Object detailsToSend = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
	        		    
		
	        		    LogHandling.logging("User: " + login +" logged in");
	        	    	returnMsg.add(detailsToSend);
	        		    
	        	    	
	        	      	rs.close();
	        		    client.sendToClient(returnMsg);
	        		     return;
	                	}
	        	   else{ /*loggedIn == true -> user is already logged in*/
		        		returnMsg.add("Error:User is allready logged in to the system");
		        		rs.close();
		        		client.sendToClient(returnMsg);
		        		return;
	        	     }
	        	}
	        	else{/*Password is incorrect*/
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
	      LogHandling.logging("User: " + login +" could not log in due to a system failure");
	      LogHandling.logging(e.getMessage());
	      returnMsg.add("Error Logging in");
	      returnMsg.add("Please Contact Technical Support");
	      client.sendToClient(returnMsg);
	      e.printStackTrace();
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
		 //this condition handles the situation where a user who is not logged in tries to perfrom a logout
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
 			LogHandling.logging("Error: " + userName +"Is not logged in yet tried to sign out");
 			LogHandling.logging(e.getMessage());
 			returnMsg.add("Error: Could not sign out from MyBox!");
    		returnMsg.add("Please Contact Technical Support");
    		client.sendToClient(returnMsg);
 			e.printStackTrace();
   }
}
    
  
  
}
