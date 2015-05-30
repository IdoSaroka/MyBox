package entities;
/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	* Ido Saroka 300830973
	* Ran Azard 300819190
	* Sagi Sulimani 300338878
	* Shimon Ben Alol 201231818
*/

/**
	*Class File: Includes all the variables described in class diagram. Extends the abstract class 'Entity'.
    *@author Sagi Sulimani 300338878
*/

public class File extends Entity
{
	private long Size;
	private String Suffix;
	private int PermissionLevel;
	private boolean IsDeleted;
	
/**
	* Constructor of the File class
	* Parameters handle by this class ('Folder'):
	* @param long size
	* @param String suffix
	* @param int permissionLevel
	* @param boolean isDeleted
	* Parameters handle by the abstract class 'Entity':
	* @param String id
	* @param String name
	* @param String creationDate
	* @param String createdBy
	* @param String path
	* @param String modifiedAt
	* @param String modifiedBy
*/
	public File(long size, String suffix, int permissionLevel, String id, String name, String creationDate, String createdBy, String path, String modifiedAt, String modifiedBy)
	{
		super(id, name, creationDate, createdBy, path, modifiedAt, modifiedBy);
		Size = size;
		Suffix = suffix;
		PermissionLevel = permissionLevel;
		IsDeleted = false;
	}
	
/**
	* @return long Size: The size of the file in bytes
*/
	public long getSize()
	{
		return Size;
	}
	
/**
	* @return int PermissionLevel: The Permission Level of the file: 1/2/3
*/
	public int getLevelOfPermission(Object GOI)
	{
		return PermissionLevel;
	}
	
/**
	* @return String Suffix: The suffix (type) of the file
*/
	public String getSuffix()
	{
		return Suffix;
	}
	
/**
	* @return boolean isFileDeleted: 'true' if deletion the file succeeded, else return 'false'.
*/
	public boolean isFileDeleted(String Path)
	{
		return IsDeleted;
	}

/**
	* Notify the Observer
*/
	public void notifyObserver()
	{
		
	}
	
/**
	* @return 
*/
//	public void notify()	// <<<<<<<<<<<<<<<<<<<<<<<<<<< Error: Cannot override the final method from Object
	{
		
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
	* @param String Name
	* @return 'true' if creation file succeeded, else return 'false'
*/
	public boolean createFile(String Name)
	{
		return true; 
		
		//return false;	
	}
}