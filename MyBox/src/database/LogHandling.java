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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import guis.ConnectionPage;

/**This Class perfrom's the writing to the log file used by the system to track it's action's
 * @author Ido Saroka 300830973
 **/

public class LogHandling {
	
	/**setUp - will run one time when the server establishes a connection with the database
	 * in order to create the necessary locations in which to save the logs
	 * <p>
	 * @exception se - security exception that will be thrown should the program encounter any problems
	 * creating the folders.
	 * **/
	
	static public void setUp(){
		 File files = new File("C:\\Program Files\\MyBox\\logs");

		 // if the directory does not exist, create it
		 
		 if (files.exists()) {
		     System.out.println("creating directory: MyBox");
		     boolean result = false;

		     try{
		    	 files.mkdirs();
		         result = true;
		     } 
		     catch(SecurityException se){
		         se.printStackTrace();
		     }        
		     if(result) {    
		         System.out.println("MyBox Installation finished");  
		     }
		 }


	}
	  
	  
	  static public void logging(String text) throws IOException {
		  
		  SimpleDateFormat dateFormatH = new SimpleDateFormat("HH:mm");	
		  Date dateH = new Date();
		  String time = dateFormatH.format(dateH);
		  SimpleDateFormat dateFormatD = new SimpleDateFormat("dd-MM-yyyy");
		  Date dateD = new Date();
		  String date = dateFormatD.format(dateD);
			
		  //display the message in the server GUI
		  guis.ConnectionPage.listModelServer.addElement(" "+date+"         " + "  "+time+"                " +  text);
		  
		  Logger logger = Logger.getLogger("MyLog");  
		  logger.setLevel(Level.INFO);
		  FileHandler fh; 
		 
		   try{
			   boolean success = (new File("C:\\MyBox\\")).mkdirs();
			   File file =new File("C:\\MyBox\\logFile.txt");
			   
	    		//if file doesnt exists, then create it
	    		if(file.exists()==false){
	    			file.createNewFile();
	    		}
	    	    /**
	    	     * If the file exists then the data will append to it
	    	     * **/
	    		fh = new FileHandler(file.getPath(),true); 
	    	
	    		logger.addHandler(fh);
	    		SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  
		        logger.info(text);
		        
		        
		   }catch (SecurityException e) {  
		        e.printStackTrace();  
		    }catch(IOException e){
			   e.printStackTrace();
		   }
		}


}
