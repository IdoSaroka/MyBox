
package entities;
/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	* Ido Saroka 300830973
	* Ran Azard 300819190
	* Sagi Sulimani 300338878
	* Shimon Ben Alol 201231818
*/

/**
	*Class Account: Includes all the variables described in the class diagram
    *@author Sagi Sulimani 300338878
*/

public class Account 
{
	private String ID;
	private String Description;
	private long MaxStorage;
	private int MaxGOI;
	
/**
	* Constructor of the Account class
	* @param String id
	* @param String description
	* @param long maxStorage
	* @param int maxGOI
*/
	public Account (String id, String description, long maxStorage, int maxGOI)
	{
		ID = id;
		Description = description;
		MaxStorage = maxStorage;
		MaxGOI = maxGOI;
	}

/**
	* @return String of ID
*/
	public String getID()
	{
		return ID;
	}
	
/**
	* @return String of Description
*/
	public String getDescription()
	{
		return Description;
	}
	
/**
	* Setting the description of the current account
	* @return void
*/
	public void setNumberOfUsers(String description)
	{
		Description = description;
	}

/**
	* @return String of Max Storage
*/
	public long getMaxStorage()
	{
		return MaxStorage;
	}

/**
	* @return String of Max GOI 
*/
	public int getMaxGOI()
	{
		return MaxGOI;
	}
}
	