package files;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import main.MyBoxGUI;

/**
 * This class is the browse controller
 */
public class Browse extends MyBoxGUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyFile myFile;
	private String filePath;
	private String fileName;
	private String fileSuffix;
	
	/**
	 * Initializing the File Open window
	 */
	public Browse()
	{
		super();
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/**
 * change the file to bits array
 * 
 * @param f from file type to save the file data
 */
	public Browse(File f)
	{
		myFile = new MyFile(f.getName());
		long length = f.length(); 
		  if (length > Integer.MAX_VALUE) { 
	            try {
					throw new IOException("File is too large!");
				} catch (IOException e) {
					e.printStackTrace();
				} 
	        } 
	        byte[] b = new byte[(int)length]; 
	        b = getFile(f);
	        if (b!=null){
	        	myFile.initArray((int)length);
	        	myFile.setSize((int)length);
	        	myFile.setMybytearray(b);
	        }
	        else
	        	myFile.setName("null");
	}
	/**
	 * @return the file
	 */
	public MyFile getFile() {
		return myFile;
	}
	/***
	 * @param file the file to set
	 */
	public void setFile(MyFile file) {
		this.myFile = file;
	}

	public void init() throws IOException {
		JFileChooser fc = new JFileChooser(); 
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(this); 
		if (returnVal == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			Path = file.toString();
			txtPath.setText(Path);
			
			int dot = Path.lastIndexOf('.');
			
			String name=file.getName();
			String suff=Path.substring(dot+1);
			myFile = new MyFile(name);
			
			myFile.setPath(file.getParent());
			name=name.substring(0, Math.min(name.length(), suff.length()-1));
			myFile.setName(name);
			myFile.setSuffix(suff);
			
			long length = file.length(); 
			if (length > Integer.MAX_VALUE)  
		    throw new IOException("File is too large!"); 	  
		    byte[] b = new byte[(int)length]; 
		    b = getFile(file);
		    myFile.initArray((int)length);
		    myFile.setSize((int)length);
		    myFile.setMybytearray(b);
		}
		this.setSize(400, 321);
		this.setLayout(null);	
	}
	
	/**
	 * @param myFile
	 * @return
	 */
	private static byte[] getFile(File myFile)
	{
		try {
		    byte [] mybytearray  = new byte [(int)myFile.length()]; 		    
		    if(!myFile.exists())
		    	System.out.println("file not here!");		    	
		    if(!myFile.canRead())
		    	System.out.println("file can not be read!");    	
		    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile)); //get Buffered Stream		    
		    bis.read(mybytearray,0,mybytearray.length); // read file into array
		    bis.close();
		    return mybytearray;	//return array
			
		} catch (FileNotFoundException e) {
			System.out.println("Error getting file "+ myFile.getPath()+": "+e.getMessage());		
		} catch(IOException e) {
			System.out.println("Error getting file "+ myFile.getPath()+": "+e.getMessage());	
		}
		return null;
	}
	
	
	
	/**
	 * converting file into bytes
	 * @param file
	 * @return byte that represent the file
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {          
        long length = file.length(); 
        if (length > Integer.MAX_VALUE)
            throw new IOException("File is too large!"); 
        byte[] bytes = new byte[(int)length]; 
        int offset = 0; 
        try { 
        	FileInputStream fis = new FileInputStream(file);
        	@SuppressWarnings("resource")
			BufferedInputStream bis = new BufferedInputStream(fis);
  	      	bis.read(bytes,offset,(int)length);
  	      System.out.println("byteeeeeeeeeeeeee:        " + bytes);
            }catch (Exception e) {
    			System.out.println("Error send (Files)msg to Server");
    		}     
        return bytes; 
    } 
	
	
	public String getName(){
		return this.fileName;
	}
	
	public String getPath(){
		return this.filePath;
	}
	
	public String getSuffix(){
		return this.fileSuffix;
	}
}
