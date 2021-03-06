package controllers;

import java.io.IOException;

import entities.GOI;
import entities.Request;
import entities.User;
import main.MyBoxGUI;


/**
* Project MyBox - Software Engineering Lab 2015 - Group no.2
* Ido Saroka 300830973
* Ran Azard 300819190
* Sagi Sulimani 300338878
* Shimon Ben Alol 201231818
*/

/**
*Class SysAdminController: describe the actions the sys admin can perform
*@author Shimon Ben-Alul 201231818
*/
public class SysAdminController extends FileOwnerController {
	
	/**
	 * Empty constructor from file owner
	 */
	public SysAdminController(){
		super();
		message.add("Admin");
	}
	
	/**returns the current requests**/
    public void getCurrentRequests() throws IOException{
    	message.clear();
    	message.add("Admin");
    	message.add(MyBoxGUI.getUser().getrole());
    	message.add(MyBoxGUI.getUser());
    	message.add("RetriveCurrentRequests");
    	MyBoxGUI.getClient().sendToServer(message);
    }
    
	/**
	 * System administrator chooses weather to allow or deny a request from users
	 * @param boolean 
	 * answer if to accept or decline
	 * @throws IOException 
	 */
	public void approveRequest(Request req, String decision) throws IOException{
		message.clear();
		message.add("Admin");
		message.add(MyBoxGUI.getUser().getrole());
		message.add(MyBoxGUI.getUser());
		message.add("DecideAboutRequest");
		message.add(req);
		message.add(decision);//accept OR reject
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to delete a GOI
	 * @param GOI 
	 *  String that represent the GOI
	 * @throws IOException 
	 */
	public void removeGOI(GOI GOI) throws IOException{
		message.clear();
		message.add("Admin");
		message.add(MyBoxGUI.getUser().getrole());
		message.add(MyBoxGUI.getUser());
		message.add("DeleteAGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to add/create a GOI
	 * @param  goi String that represent the GOI
	 * @throws IOException 
	 */
	public void addGOI(GOI goi) throws IOException{
		message.clear();
		message.add("Admin");
		message.add(MyBoxGUI.getUser().getrole());
		//message.add(MyBoxGUI.getUser().getUserName());
		message.add(MyBoxGUI.getUser());
		message.add("CreateGOI");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to change exist GOI
	 * @param goi String that represent the GOI
	 * @throws IOException 
	 */
	/*
	public void editGOI(GOI goi) throws IOException{
		message.clear();
		message.add("Admin");
		message.add(MyBoxGUI.getUser().getrole());
		message.add(MyBoxGUI.getUser().getUserName());
		message.add("DecideAboutRequest");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	*/
	/**
	 * System administrator wants to search for a specific user
	 * @param user String that represent the user
	 * @throws IOException 
	 */
	/*
	public void searchUser(String user) throws IOException{
		message.clear();
		message.add("Admin");
		message.add(MyBoxGUI.getUser().getrole());
		message.add("DecideAboutRequest");
		message.add(user);
		MyBoxGUI.getClient().sendToServer(message);
	}
	*/
	/**
	 * System administrator wants to remove for a specific user from GOI
	 * @param selectedUser
	 * @param goi
	 * @throws IOException
	 */
	public void removeUser(String selectedUser, GOI goi) throws IOException{
		message.clear();
		message.add("Admin");
		message.add(MyBoxGUI.getUser().getrole());
		message.add(MyBoxGUI.getUser());
		message.add("DeleteUserFromGOI");
		message.add(goi);
		message.add(selectedUser);		
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to review files in GOI
	 * @param GOI  String that represent the GOI
	 * @throws IOException 
	 */
	public void UsersInGOI(GOI GOI) throws IOException{
		message.clear();
		message.add("Admin");
		message.add(MyBoxGUI.getUser().getrole());
		message.add(MyBoxGUI.getUser());
		message.add("UsersInGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
}
