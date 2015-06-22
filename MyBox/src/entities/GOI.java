
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
	*Class GOI: Includes all the variables described in the class diagram
    *@author Sagi Sulimani 300338878
*/

public class GOI implements Serializable{
	/**
	 * allows GOI Object to be send via server-client communication
	 */
	private static final long serialVersionUID = 1L;
	private String Name; 
	private int ID;
	private String Subject;
	private String CreationDate;
	private int NumberOfUsers;
	private int currentUser;
	
/**
	* Constructor of the GOI class
	* The id is given by the server at first creation of this GOI
	* @param String name
	* @param String subject
	* @param int numberOfUsers
*/
	
	
	public GOI (String name, String subject, int numberOfUsers)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm  dd-MM-yyyy");
		Date date = new Date();
		this.Name = name;
		this.Subject = subject;
		this.CreationDate = dateFormat.format(date);
		this.NumberOfUsers = numberOfUsers;
		this.currentUser=0;
	}
	
	/**
	 * This constructor is meant for server use in order to send and receive GOI
	 * entity to SysAdmin
	 * @param String name
	 * @param int id
	 * @param String subject
 	 * @param String creationDate
 	 * @param String numberOfUsers
 	 * @param int current
	 */
	public GOI (int id, String name, String subject, String creation, int numberOfUsers,int current)
	{
		this.Name = name;
		this.Subject = subject;
		this.CreationDate = creation;
		this.NumberOfUsers = numberOfUsers;
		this.ID=id;
		this.currentUser=current;
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
	public int getID()
	{
		return ID;
	}
	
/**
	* @return String of Number Of Users
*/
	public int getNumberOfUsers()
	{
		return NumberOfUsers;
	}
	
/**
	* Setting the Number Of Users of the current GOI
	* @return void
*/
	public void setNumberOfUsers(int numberOfUsers)
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

	/**
	 * @return the currentUser
	 */
	public int getCurrentUser() {
		return currentUser;
	}


}
	