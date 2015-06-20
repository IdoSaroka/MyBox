package entities;

import java.io.Serializable;

public class FileOwnerViewer implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private int fileID;
		private String fileName;
		private String fileSuffix;
		private String fileOwner;
		private String virtualPath;
		private int privilege;
		private String fileDescription;
		

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
}
