package controllers;

import java.io.IOException;

import main.MyBoxGUI;

public class FileOwnerController extends UserController {
	
	public void deleteFile(String filePath) throws IOException{
		message.clear();
		message.add("deleteFile");
		message.add(filePath);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void setPath(String file,String newPath) throws IOException{
		message.clear();
		message.add("setPath");
		message.add(file);
		message.add(newPath);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void editPermission(String file, String perm, String goi) throws IOException{
		message.clear();
		message.add("editPermission");
		message.add(file);
		message.add(goi);
		message.add(perm);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void retrieveFile(String file) throws IOException{
		message.clear();
		message.add("retrieveFile");
		message.add(file);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void setFileName(String file,String newName) throws IOException{
		message.clear();
		message.add("setFileName");
		message.add(file);
		message.add(newName);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	public void deleteFolder(String folderName) throws IOException{
		message.clear();
		message.add("deleteFolder");
		message.add(folderName);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
}
