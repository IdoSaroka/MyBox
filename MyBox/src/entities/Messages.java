package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Project MyBox - Software Engineering Lab 2015 - Group no.2
* Ido Saroka 300830973
* Ran Azard 300819190
* Sagi Sulimani 300338878
* Shimon Ben Alol 201231818
*/

/**
*Class Messages: Describe the message that the user will get from the system
*like "You were authorized to join Study Software group of interest" etc. 
*@author Shimon Ben-Alul 201231818
*/
public class Messages implements Serializable{

	/**
	 * implements serializable as this class will sent from the server or to the server
	 */
	private static final long serialVersionUID = 1L;

	private String content;
	private String time;
	
	/**
	 * for creating a message, gets the actual time of creation live from the System clock
	 * @param content
	 * Described the content of the message send
	 */
	public Messages(String content){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");
		Date date = new Date();
		this.time=dateFormat.format(date);
		this.content=content;	
	}
	
	/**
	 * for initializing a message from the database 
	 * initialized by the server and being sent to the client
	 * @param content
	 * @param time
	 */
	public Messages(String content,String time){
		this.time=time;
		this.content=content;	
	}
	
	/**
	 * 
	 * @return the content of the message
	 */
	public String getContent(){
		return this.content;
	}
	
	/**
	 * 
	 * @return the time of the message
	 */
	public String getTime(){
		return this.time;
	}
	
	
}
