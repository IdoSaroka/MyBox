package database;
/**
 *Project MyBox - Software Engineering Lab 2015
 *@author Ido Saroka 300830973
 *@author Ran Azard 300819190
 *@author Sagi Sulimani 300338878
 *@author Shimon Ben Alol 201231818
**/
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import ocsf.server.ConnectionToClient;

/**This Class contains MyBox functions that are responsible for
 * accessing and handling the "Groups Of Interests" in the system.
 * @author Ido Saroka 300830973**/
public class GoiManagment {
	ArrayList<Object> msg= new ArrayList<>();
	ArrayList<String> returnMsg = new ArrayList<>();
	
	public GoiManagment(Object message, ConnectionToClient client) {
		this.msg =(ArrayList)message;
	}

}
