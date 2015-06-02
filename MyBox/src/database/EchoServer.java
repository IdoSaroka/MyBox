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

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**This Class establish the connection to the DB and creates the server 
 * @author Ido Saroka 300830973
 * @param port - the port issued by the user to be opened and receive connections
 * @param DBConn - the connection to the Database
 * <p>
 * @exception IOException -
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
  
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	break;
    	
    case "GOIBasic":
    	GoiBasic handler = new GoiBasic(msg,client); 
    	handler.options();
        break;
        
        /**
         * File - used when a file owner chooses to access his files
         * **/
    case "File":
    	FilesHandler fileHandler = new FilesHandler(msg,client,conn); 
    	FilesHandler.options();
        break;
        
    	/**
         * SignOut - used when a logged in user chooses to sign out
         * **/
    case "SignOut":
    	signOutUser((String)message.get(1));
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
   * @exception SQLException e -
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
      LogHandling.logging(ex.getMessage());
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    } catch (IOException e) {
		// TODO Auto-generated catch block
    	LogHandling.logging(e.getMessage());
		e.printStackTrace();
	}
    try
    {
    	
      /**add a GUI elemnet that recives a port from the user**/
    	
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
    }
  }
  
  
  /**checkLogin - will check the that details (user name and password) inputed by  
   * the users corresponds with the one found inside MyBox Database
   * @author Ido Saroka 300830973
   * @param login - 
   * @param password -
   * @param conn -
   * @param client -
   * <p>
   * @exception SQLException e -
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
	   
	      ResultSet rs = stmt.executeQuery("SELECT userName,password,loggedIn From users");
	      
	      while (rs.next()) { /**search the current users in the system**/
	        if ((login.equals(rs.getString(1)))){ /**User Name appears in the Database**/
	        	if(password.equals(rs.getString(2))){/**Password is correct**/
	        		if(rs.getBoolean(3)== false){  /**loggedIn == false -> user is not logged in**/
	        		
	        		    /**add the data to the log file**/
	      
	        		    preparedStatement = conn.prepareStatement(query);
	        		    preparedStatement.setInt(1, 1);
	        	    	preparedStatement.setString(2, login);
	        		    preparedStatement.executeUpdate();
	        		    
	        		    /*need to return a user object*/
	        		    
	        	    	//System.out.println("User Successfully logged in");
	        		    LogHandling.logging("User: " + login +" logged in");
	        	    	returnMsg.add("you have Successfully logged in");
	        	    	
	        	      	rs.close();
	        		    client.sendToClient(returnMsg);
	        		    
	        	    	//logging();
	        		    
	        		     return;
	                	}
	            /*	else{
	        		    returnMsg.add("Error:The password you have entered is incorrect");
	        		    rs.close();
	        		    client.sendToClient(returnMsg);
	        		    return;*/
	        	   else{ /**loggedIn == true -> user is already logged in**/
		        		returnMsg.add("Error:User is allready logged in to the system");
		        		rs.close();
		        		client.sendToClient(returnMsg);
		        		return;
	        	     }
	        	}
	        	else{/**Password is incorrect**/
        		    returnMsg.add("Error:The password you have entered is incorrect");
        		    rs.close();
        		    client.sendToClient(returnMsg);
        		    return;
	        	}
	        }     
	      }
	      returnMsg.add("Error:No Such User Exist"); /**User Name doesn't appears in the Database**/
	      rs.close();
  		  client.sendToClient(returnMsg);
	      return;
	    }
	    catch (SQLException | IOException e)
	    {
	      LogHandling.logging(e.getMessage());
	      e.printStackTrace();
	    }
    }
   /**signOutUser - will be responsiable for handling the user's
    * request to log out from the system
   * @author Ido Saroka 300830973
   * @param userName - 
   * <p>
   * @exception SQLException e -
   * @exception IOException e -
   * **/   
    public static void signOutUser(String userName){
    	
    }
    
  
  
}
