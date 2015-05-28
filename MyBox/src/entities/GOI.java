
package entities;
/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	* Ido Saroka 300830973
	* Ran Azard 300819190
	* Sagi Sulimani 300338878
	* Shimon Ben Alol 201231818
*/

/**
	*Class GOI: Includes all the variables described in the class diagram
    *@author Sagi Sulimani 300338878
*/

public class GOI 
{
	private String Name; 
	private String ID;
	private String Subject;
	private String CreationDate;
	private String NumberOfUsers;
	
/**
	* Constructor of the GOI class
	* @param String name
	* @param String id
	* @param String subject
	* @param String creationDate
	* @param String numberOfUsers
*/
	public GOI (String name, String id, String subject, String creationDate, String numberOfUsers)
	{
		Name = name;
		ID = id;
		Subject = subject;
		CreationDate = creationDate;
		NumberOfUsers = numberOfUsers;
	}
	
/**
	* @return String of Name
*/
	public String getName()
	{
		return Name;
	}
	
/**
	* @return String of Subject
*/
	public String getSubject()
	{
		return Subject;
	}	
	
/**
	* @return String of Creation Date
*/
	public String getCreationDate()
	{
		return CreationDate;
	}
	
/**
	* @return String of ID
*/
	public String getID()
	{
		return ID;
	}
	
/**
	* @return String of Number Of Users
*/
	public String getNumberOfUsers()
	{
		return NumberOfUsers;
	}
	
/**
	* Setting the Number Of Users of the current GOI
	* @return void
*/
	public void setNumberOfUsers(String numberOfUsers)
	{
		NumberOfUsers = numberOfUsers;
	}
	
/**
	* @return 'true' if checking some data in the DataBase succeeded, else return 'false'.
*/
	public boolean checkDB()
	{
		return true; 
		
		//return false;
	}
	
/**
	* @return 'true' if saving the data in the DataBase succeeded, else return 'false'.
*/
	public boolean saveInDB()
	{		
		return true; 
		
		//return false;
	}
}
	