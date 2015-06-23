package entities;

import java.io.Serializable;

/**
* Project MyBox - Software Engineering Lab 2015 - Group no.2
* Ido Saroka 300830973
* Ran Azard 300819190
* Sagi Sulimani 300338878
* Shimon Ben Alol 201231818
*/

/**
*Class FileOwnerViewer: A class that meant to represent the file that  sent
*from the server and view then for their owner 
*@author Shimon Ben-Alul 201231818
*/
public class FileOwnerViewer implements Serializable {


	/**
	 * implements serializable as this class will sent from the server or to the server
	 */
	private static final long serialVersionUID = 1L;
	
		private int fileID;
		private String fileName;
		private String fileSuffix;
		private String fileOwner;
		private String virtualPath;
		private int privilege;
		private String fileDescription;
		

		/**
		 * constructor for the server in order to build this class
		 * so that the client will be able to extract information
		 * @param fileID
		 * @param fileName
		 * @param fileSuffix
		 * @param fileOwner
		 * @param virtualPath
		 * @param privilege
		 * @param fileDescription
		 */
		public FileOwnerViewer(int fileID, String fileName, String fileSuffix, String fileOwner, String virtualPath, int privilege,
				String fileDescription){
			this.fileID=fileID;
			this.fileName=fileName;
			this.fileSuffix=fileSuffix;
			this.fileOwner=fileOwner;
			this.virtualPath=virtualPath;
			this.fileDescription=fileDescription;
			this.privilege=privilege;
		}




		/**
		 * @return the fileID
		 */
		public int getFileID() {
			return fileID;
		}


		/**
		 * @return the fileName
		 */
		public String getFileName() {
			return fileName;
		}


		/**
		 * @return the fileSuffix
		 */
		public String getFileSuffix() {
			return fileSuffix;
		}

		/**
		 * @return the fileOwner
		 */
		public String getFileOwner() {
			return fileOwner;
		}

		/**
		 * @return the virtualPath
		 */
		public String getVirtualPath() {
			return virtualPath;
		}


		/**
		 * @return the fileDescription
		 */
		public String getFileDescription() {
			return fileDescription;
		}

		/**
		 * @return the privilege
		 */
		public int getPrivilege() {
			return privilege;
		}
		
		/**
		 * @return the String to view the file
		 */
		@Override
		public String toString(){
			return "File name: "+ fileName+"."+fileSuffix+ "\nLocation in the server: "+virtualPath+",\n"
					+ "file descripion: "+fileDescription+"\nfile privilege: "+privilege; 
		}
}
