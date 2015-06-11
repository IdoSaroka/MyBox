package entities;

import java.io.Serializable;

/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	*@author Ido Saroka 300830973
	*@author Ran Azard 300819190
	*@author Sagi Sulimani 300338878
	*@author Shimon Ben Alol 201231818
*/

/**
	*Class Request: class Request includes all the variables described in class diagram
    *@author Sagi Sulimani 300338878
*/
public class Request implements Serializable
{
	private String userName;
	private String goiName;
	private String description;
	
/**
	* Constructor of the Request class
	* @param String userName
	* @param String goiName
	* @param String description
*/
	public Request(String userName, String goiName, String description)
	{
		this.userName = userName;
		this.goiName = goiName;
		this.description = description;
	}
	
/**
	* @return String of User Name
*/
	public String getUserName()
	{
		return userName;
	}

/**
	* @return String of GOI Name
*/	
	public String getGoiName()
	{
		return goiName;
	}

/**
	* @return String of Description
*/	
	public String getDescription()
	{
		return description;
	}
}
