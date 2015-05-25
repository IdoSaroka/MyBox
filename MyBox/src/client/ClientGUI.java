package client;

import java.io.*;
import java.util.ArrayList;

import main.*;
import client.*;
import common.*;

public class ClientGUI implements ChatIF {

	
	public static int port;
	ChatClient client;
	
	  public void ClientConsole(String host, int port) 
	  {
	    try 
	    {
	      client= new ChatClient(host, port, this);
	    } 
	    catch(IOException exception) 
	    {
	      System.out.println("Error: Can't setup connection!" + " Terminating client.");
	      System.exit(1);
	    }
	  }
	  
	  
	  
	  public void accept() 
	  {
	    try
	    {
	      while (true) 
	      {
	        client.handleMessageFromClientUI(MyBoxGUI.getMsg());
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }
	  
	  
	  public void display(String message) 
	  {
	    System.out.println(message);
	  }



	@Override
	public void display(ArrayList<String> message) {
		// TODO Auto-generated method stub
		
	}
	  
}
