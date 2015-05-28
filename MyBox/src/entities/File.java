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
	* @return 
*/
	public long getSize()
	{
		return Size;
	}
	
/**
	* @return 
*/
	public int getLevelOfPermission(Object GOI)
	{
		return PermissionLevel;
	}
	
/**
	* @return 
*/
	public String getSuffix()
	{
		return Suffix;
	}
	
/**
	* @return 
*/
	public boolean isFileDeleted(String Path)
	{
		return IsDeleted;
	}

/**
	* @return 
*/
	public void notifyObservesr()
	{
		return ;
	}
	
/**
	* @return 
*/
//	public void notify()	// <<<<<<<<<<<<<<<< Error: Cannot override the final method from Object
	{
		
	}
	
/**
	* @return 
*/
	public boolean checkDB()
	{
		return true; 
		
		//return false;
	}
	
/**
	* @return 'true' if saving the data succeeded, else return 'false'.
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