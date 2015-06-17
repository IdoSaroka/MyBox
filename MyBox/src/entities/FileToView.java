package entities;

import java.io.Serializable;

public class FileToView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private String goiID;
		private String fileID;
		private String fileName;
		private String fileSuffix;
		private String fileOwner;
		private String virtualPath;
		private String fileDescription;
		private boolean goiPermission; 
		
		/**
		 * full constructor
		 */
		public FileToView(String goiID, String fileID, String fileName, String fileSuffix, String fileOwner, String virtualPath,
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
		public String getGoiID() {
			return goiID;
		}

		/**
		 * @return the fileID
		 */
		public String getFileID() {
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
