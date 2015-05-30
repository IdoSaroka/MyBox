
package entities;
/**
	* Project MyBox - Software Engineering Lab 2015 - Group no.2
	* Ido Saroka 300830973
	* Ran Azard 300819190
	* Sagi Sulimani 300338878
	* Shimon Ben Alol 201231818
*/

/**
	*Class Entity: Abstract class Entity includes all the variables described in the class diagram
    *@author Sagi Sulimani 300338878
*/

public abstract class Entity
{
	protected String ID;
	protected String Name;
	protected String CreationDate;
	protected String CreatedBy;
	protected String Path;
	protected String ModifiedAt;
	protected String ModifiedBy;
	
/**
	* Constructor of the Entity class
	* @param String id
	* @param String name
	* @param String creationDate
	* @param String createdBy
	* @param String path
	* @param String modifiedAt
	* @param String modifiedBy
*/

	public Entity (String id, String name, String creationDate, String createdBy, String path, String modifiedAt, String modifiedBy)
	{
		ID = id;
		Name = name;
		CreationDate = creationDate;
		CreatedBy = createdBy;
		Path = path;
		ModifiedAt = modifiedAt;
		ModifiedBy = modifiedBy;
	}
	
/**
	* @return String of ID
*/
	public String getID()
	{
		return ID;
	}
	
/**
	* @return String of Name
*/
	public String getName()
	{
		return Name;
	}

/**
	* @return String of Creation Date
*/
	public String getCreationDate()
	{
		return CreationDate;
	}
	
/**
	* @return String of Created By
*/
	public String getCreatedBy()
	{
		return CreatedBy;
	}
	
/**
	* @return String of Path
*/
	public String getPath()
	{
		return Path;
	}
	
/**
	* @return String of Modified At
*/
	public String getModifiedAt()
	{
		return ModifiedAt;
	}
	
/**
	* @return String of Modified By
*/
	public String getModifiedBy()
	{
		return ModifiedBy;
	}
}
	
	