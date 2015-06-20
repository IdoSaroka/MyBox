package files;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JPanel;



/**
 * 
 * this class to save FILES
 *
 */
public class Save extends JPanel {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private MyFile myFile;
		private File ff;
		private String path;
		/**
		 * 
		 * @param f
		 */
		public Save(MyFile f,String path)
		{
			super();
			myFile = f;
			this.path=path;
			try 
			{
				init(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void init(String Path) throws IOException {
			ff = saveFile(myFile, Path);

		
		}
	
	/**
	 * @return the ff
	 */
	public File getFf() {
		return ff;
	}

	/**
	 * @param file
	 */
	public void setFf(File file) {
		this.ff = file;
	}

	/**
	 * @param f
	 */
	public static File saveFile(MyFile f, String path)
	{
		String strFilePath = path+f.getName()+"."+f.getSuffix();
			 File file = null; 
		     try{ 
		          file = new File(strFilePath);
		          byte mybytearray[] = f.getMybytearray();
		          // set output stream to write file in file system
		          
		          BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file)); 
		         // System.out.println("here we are:");
		          bos.write(mybytearray, 0 , mybytearray.length); //write out file
		          bos.flush(); //close buffer
		          bos.close();
		          
		          return file;
			} catch (FileNotFoundException e) {
				System.out.println("Error saving file "+ strFilePath+": "+e.getMessage()+ "  FileNotFoundException");		}
			catch(IOException e) {
				System.out.println("Error saving file "+ strFilePath+": "+e.getMessage()+ "  IOException");	
			}
				return file;
		}
}
