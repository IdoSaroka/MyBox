
package entities;
/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	* Ido Saroka 300830973
	* Ran Azard 300819190
	* Sagi Sulimani 300338878
	* Shimon Ben Alol 201231818
*/

/**
	*Class Folder: Includes all the variables described in the class diagram. Extends the abstract class 'Entity'.
    *@author Sagi Sulimani 300338878
*/


public class Folder extends Entity
{
	private int NumberOfFiles;
	private long SizeOfFolder;
	
/**
	* Constructor of the Folder class
	* Parameters handle by this class ('Folder'):
	* @param int numberOfFiles
	* @param long sizeOfFolder
	* Parameters handle by the abstract class 'Entity':
	* @param String id
	* @param String name
	* @param String creationDate
	* @param String createdBy
	* @param String path
	* @param String modifiedAt
	* @param String modifiedBy
*/
	public Folder (int numberOfFiles, long sizeOfFolder, String id, String name, String creationDate, String createdBy, String path, String modifiedAt, String modifiedBy)
	{
		super(id, name, creationDate, createdBy, path, modifiedAt, modifiedBy);
		NumberOfFiles = numberOfFiles;
		SizeOfFolder = sizeOfFolder;
	}
	
/**
	* @return int of Number Of Files
*/
	public int getNumberOfFiles()
	{
		return NumberOfFiles;
	}
	
/**
	* @return long of Size Of Folder
*/
	public long getSizeOfFolder()
	{
		return SizeOfFolder;
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
	
	