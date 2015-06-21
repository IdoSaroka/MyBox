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
*Class xxxx: 
*@author Shimon Ben-Alul 201231818
*/
public class FileToView implements Serializable {

	/**
	 * implements serializable as this class will sent from the server or to the server
	 */
	private static final long serialVersionUID = 1L;
	
		private int goiID;
		private int fileID;
		private String fileName;
		private String fileSuffix;
		private String fileOwner;
		private String virtualPath;
		private String fileDescription;
		private boolean goiPermission; 
		
		/**
		 * full constructor
		 * used by the server to sent to the client and view files
		 * that are shared with you
		 * @param goiID
		 * @param fileID
		 * @param fileName
		 * @param fileSuffix
		 * @param fileOwner
		 * @param virtualPath
		 * @param fileDescription
		 * @param goiPermission
		 */
		public FileToView(int goiID, int fileID, String fileName, String fileSuffix, String fileOwner, String virtualPath,
				String fileDescription, boolean goiPermission){
			this.goiID=goiID;
			this.fileID=fileID;
			this.fileName=fileName;
			this.fileSuffix=fileSuffix;
			this.fileOwner=fileOwner;
			this.virtualPath=virtualPath;
			this.fileDescription=fileDescription;
			this.goiPermission=goiPermission;
		}


		/**
		 * @return the goiID
		 */
		public int getGoiID() {
			return goiID;
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
		 * @return the goiPermission
		 */
		public boolean isGoiPermission() {
			return goiPermission;
		}

}
