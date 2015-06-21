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

import entities.User;


/**This Class will be responsible for the security checks performed by MyBox to ensure access to file and functions are 
 * are only given to authorized users
 * **/
public class Security {

	
	public static boolean secuirtyCheck(User userToCheck,Connection con){
		PreparedStatement preparedStatement = null;
	/*	try{
		if(userToCheck.getPassword() == ){
			
		}
		}catch(SQLException | IOException e){
			
		}*/
		return true;
	}
	
	
	public static boolean checkFileOwner(User userToCheck, String listedOwnerOfTheFile,Connection con){
		
		return true;
	}
}
