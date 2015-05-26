
package client;
/**
 * Class MyBox ProtoType - simulates the work of a user with the file system
 * @authors: Ido Saroka 300830973
 *           Ran Azarad 300819190
 *           Sagi Sulmani 300338878
 *           Shimon Ben Alul 201231818
 * @Version 1.00
 */
import main.*;
import ocsf.client.*;
import common.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port);
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  
  
  public void handleMessageFromServer(Object msg) 
  {
	  clientUI.display((String) msg);
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
 * @return 
   */
  
  public void handleMessageFromClientUI(Msg message){
	  
    try
    {
    	sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
