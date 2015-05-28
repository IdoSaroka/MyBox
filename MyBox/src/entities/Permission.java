
package entities;
/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	* Ido Saroka 300830973
	* Ran Azard 300819190
	* Sagi Sulimani 300338878
	* Shimon Ben Alol 201231818
*/

/**
	*Class Permission: Includes all the variables described in the class diagram
    *@author Sagi Sulimani 300338878
*/

public class Permission 
{
	private String LevelID; 
	private String LevelName;
	
/**
	* Constructor of the Permission class
	* @param String levelID
	* @param String levelName
*/
	public Permission (String levelID, String levelName)
	{
		LevelID = levelID;
		LevelName = levelName;
	}
	
/**
	* @return String of Level ID
*/
	public String getLevelID()
	{
		return LevelID;
	}
	
/**
	* @return String of Level Name
*/
	public String getLevelName()
	{
		return LevelName;
	}
}
	