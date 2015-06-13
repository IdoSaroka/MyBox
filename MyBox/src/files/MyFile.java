package files;


import java.io.Serializable;


/**
 * This class is the file controller
 */
public class MyFile implements Serializable {
  
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Description;
	private String path;
	private String name;
	private String suffix;
	private int size;
	private int privelege;
	private  byte[] mybytearray;
	private String owner;
		
		
		/**
		 * 
		 * @param fileName
		 */
		public MyFile( String fileName ){
			this.name = fileName;
		}
		
		
		
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}



		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

	
		

		/**
		 * @return the description
		 */
		public String getDescription() {
			return Description;
		}



		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			Description = description;
		}



		/**
		 * @return the path
		 */
		public String getPath() {
			return path;
		}



		/**
		 * @param path the path to set
		 */
		public void setPath(String path) {
			this.path = path;
		}



		/**
		 * @return the size
		 */
		public int getSize() {
			return size;
		}



		/**
		 * @param size the size to set
		 */
		public void setSize(int size) {
			this.size = size;
		}



		public void initArray (int s)
		{
			mybytearray = new byte [s];
		}
		

		public byte getMybytearray(int i) 
		{
			return mybytearray[i];
		}

		public void setMybytearray(byte[] bytearray) {
	
			for(int i=0;i<bytearray.length;i++)
				this.mybytearray[i] = bytearray[i];
		}


		/**
		 * @return the mybytearray
		 */
		public byte[] getMybytearray() {
			return mybytearray;
		}



		public String getSuffix() {
			return suffix;
		}



		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}



		public void setPrivelege(int privelege) {
			this.privelege=privelege;
			
		}
		public int getPrivelege() {
			return this.privelege;
			
		}



		public String getOwner() {
			return owner;
		}



		public void setOwner(String owner) {
			this.owner = owner;
		}

		
}