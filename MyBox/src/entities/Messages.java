package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Messages implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String content;
	private String time;
	
	/**
	 * for creating a message
	 * @param content
	 */
	public Messages(String content){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");
		Date date = new Date();
		this.time=dateFormat.format(date);
		this.content=content;	
	}
	
	/**
	 * for initializing a message from the db
	 * @param content
	 * @param time
	 */
	public Messages(String content,String time){
		this.time=time;
		this.content=content;	
	}
	
	
}
