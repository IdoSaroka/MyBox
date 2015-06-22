package database;

/**
 *Project MyBox - Software Engineering Lab 2015
 *@author Ido Saroka 300830973
 *@author Ran Azard 300819190
 *@author Sagi Sulimani 300338878
 *@author Shimon Ben Alol 201231818
**/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import ocsf.server.ConnectionToClient;

import entities.User;


/**This Class will be responsible for the security checks performed by MyBox to ensure access to file and functions are 
 * are only given to authorized users
 * **/
public class Security {

	
	public static boolean secuirtyCheck(User userToCheck,Connection con) throws IOException{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = con.prepareStatement("SELECT FORM Users * WHERE userName = ?");
			statement.setString(1, userToCheck.getUserName());
			rs = statement.executeQuery();
		//user does not exist	
		if(!rs.isBeforeFirst()){
			LogHandling.logging("Error: user " +userToCheck.getUserName() + " does not exist in the system");
			return false;
		}
		//password is not correct - illegal Access 
		if(!((userToCheck.getPassword()).equals(rs.getString(2)))){
			LogHandling.logging("Illegal Access was blocked by user: " + userToCheck.getUserName());
			return false;
		}
		//if all the checks are correct
		    return true;
		}catch(SQLException | IOException e){
			LogHandling.logging("Error: "+ userToCheck.getUserName() +" Encountered a problem Performing security checks");
			LogHandling.logging(e.getMessage());
			return false;
		}	
	}
	
	
	
	public static boolean checkFileOwner(User userToCheck,int fileId,String listedOwnerOfTheFile,Connection con) throws IOException{
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			if(secuirtyCheck(userToCheck,con) == true){
				statement = con.prepareStatement("SELECT FORM Files * WHERE fileId =? AND file_Owner = ?");
				statement.setInt(1, fileId);
				statement.setString(2, listedOwnerOfTheFile);
				rs = statement.executeQuery();
				if(!rs.isBeforeFirst()){
					LogHandling.logging("Encountered a problem while searching for file id: "+ fileId + " FileOwner: " + listedOwnerOfTheFile);
					return false;
				}
				if((rs.getString(4)).equals(userToCheck.getUserName())){
					LogHandling.logging("Error: Illegal Access from user: " + userToCheck.getUserName() + ", Tried to acces file ID: "+ fileId);
					return false;
				}
				return true;
			}
		} catch (SQLException | IOException e) {
			LogHandling.logging("Error:"+ userToCheck.getUserName() +"Encountered a problem Performing security checks");
			LogHandling.logging(e.getMessage());
		}
		return false; 
	}
}
