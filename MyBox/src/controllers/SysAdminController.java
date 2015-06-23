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
		message.add("DecideAboutRequest");
		message.add(req.getRequestID());
		message.add(decision);//accept OR reject
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to delete a GOI
	 * @param GOI 
	 *  String that represent the GOI
	 * @throws IOException 
	 */
	public void removeGOI(String GOI) throws IOException{
		message.clear();
		message.add("removeGOI");
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
		message.add("addGOI");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to change exist GOI
	 * @param goi String that represent the GOI
	 * @throws IOException 
	 */
	public void editGOI(GOI goi) throws IOException{
		message.clear();
		message.add("editGOI");
		message.add(goi);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to search for a specific user
	 * @param user String that represent the user
	 * @throws IOException 
	 */
	public void searchUser(String user) throws IOException{
		message.clear();
		message.add("searchUser");
		message.add(user);
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to remove for a specific user from GOI
	 * @param user
	 * @param goi
	 * @throws IOException
	 */
	public void removeUser(User user, GOI goi) throws IOException{
		message.clear();
		message.add("Admin");
		message.add("DeleteUserFromGOI");
		message.add(goi);
		message.add(user);		
		MyBoxGUI.getClient().sendToServer(message);
	}
	
	/**
	 * System administrator wants to review files in GOI
	 * @param GOI  String that represent the GOI
	 * @throws IOException 
	 */
	public void filesInGOI(String GOI) throws IOException{
		message.clear();
		message.add("filesInGOI");
		message.add(GOI);
		MyBoxGUI.getClient().sendToServer(message);
	}
}
