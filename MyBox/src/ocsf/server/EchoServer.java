package ocsf.server;
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
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import database.dbAdapter;

/**This Class establish the server 
 * @author Ido Saroka 300830973**/


public class EchoServer
  extends AbstractServer
{
  public static final int DEFAULT_PORT = 3306;
  
  private Connection DBConn;
  
  public EchoServer(int port, Connection DBConn)
  {
    super(port);
    this.DBConn = DBConn;
  }
  
  public Connection getFilesConn()
  {
    return this.DBConn;
  }
  
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  {
    int check = 0;
    Scanner sc = new Scanner(System.in);
    ArrayList<String> message = (ArrayList)msg;
    
    Connection filesconn = getFilesConn();
    
    String str = (String)message.get(0);
    char letter = str.charAt(0);
   /* switch (letter)
    {
    case 'p': 
      printFiles(filesconn, client);
      break;
    case 'a': 
      addNewValue(filesconn, (String)message.get(1), (String)message.get(2), client);
      break;
    case 'q': 
      System.out.println("Thank you and goodbye!");
      break;
    default: 
      System.out.println("invalid selection");
    }*/
  }
  
 /* public void printFiles(Connection conn, ConnectionToClient client)
  {
    ArrayList<String> returnMsg = new ArrayList();
    try
    {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM files;");
      returnMsg.add("------------------------Current files in the system:--------------------------");
      while (rs.next()) {
        returnMsg.add("File Name: " + rs.getString(1) + "\t\t" + "File Path: " + rs.getString(2));
      }
      rs.close();
      returnMsg.add("------------------------------------------------------------------------------\n");
      client.sendToClient(returnMsg);
    }
    catch (SQLException|IOException e)
    {
      e.printStackTrace();
    }
  }*/
  
  /*public static void addNewValue(Connection conn, String fileName, String filePath, ConnectionToClient client)
  {
    Statement stmt = null;
    ArrayList<String> returnMsg = new ArrayList();
    PreparedStatement preparedStatement = null;
    String insertTableRecored = "INSERT INTO files (fileName,path) VALUES (?,?)";
    try
    {
      stmt = conn.createStatement();
      
      ResultSet rs = stmt.executeQuery("SELECT fileName,path From files");
      while (rs.next()) {
        if ((fileName.equals(rs.getString(1))) && 
          (filePath.equals(rs.getString(2))))
        {
          returnMsg.add("Error: file allready exists in this folder");
          client.sendToClient(returnMsg);
        }
      }
      preparedStatement = conn.prepareStatement(insertTableRecored);
      preparedStatement.setString(1, fileName);
      preparedStatement.setString(2, filePath);
      preparedStatement.executeUpdate();
      returnMsg.add("File " + fileName + " was Successfully added!");
      client.sendToClient(returnMsg);
    }
    catch (SQLException|IOException e)
    {
      e.printStackTrace();
    }
  }*/
  
  protected void serverStarted()
  {
    System.out.println(
      "Server listening for connections on port " + getPort());
  }
  
  protected void serverStopped()
  {
    System.out.println(
      "Server has stopped listening for connections.");
  }
  
  public static void main(String[] args)
  {
    Connection conn = null;
    int port = 0;
    dbAdapter adapter = new dbAdapter();
    String dbUserName, dbPassword, dbName;
    
    try
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    }
    catch (Exception localException1) {}
    try
    {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/myboxdatabase", "root", "Braude");
      
      /**insert GUI OBJECT**/
      //conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + '"',dbUserName,dbPassword);
      
      System.out.println("SQL connection succeed\n");
    }
    catch (SQLException ex)
    {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    try
    {
      //add a reciver for the port from the GUI	
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
}
