package entities;

import java.io.Serializable;

public class GoiRequest implements Serializable {

	/**
	 * default serial number for sending
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String goiName;
	private String description;
	
	/**
	* Constructor for making a request
	*/
	public GoiRequest(String userName,String goiName,String description){
		this.userName=userName;
		this.goiName=goiName;
		this.description=description;
	}

	/**
	* @return String of UserName
	*/
	public String getUserName(){
		return userName;
	}
	
	/**
	* @return String of goi Name
	*/
	public String getGoiName(){
		return goiName;
	}
	
	/**
	* @return String of description
	*/
	public String getDescription(){
		return description;
	}
	
}
