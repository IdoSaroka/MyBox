package main;

import guis.ConnectionPage;
import guis.serverMainPage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.io.IOException;

import database.EchoServer;

public class ServerGUI extends MyBoxGUI
{
	protected static JFrame frmServer;
	protected static Mtds meth;
	protected static JPanel contentPane;
	protected static serverMainPage servermainpage;
	protected static ConnectionPage connectionpage;
	
	protected static JTextField textConnection;
	protected static String portNumber = "5555";
	
	protected static JTextField textUserName;
	protected static JTextField textPort;
	protected static JPasswordField textPassword;
	
	protected static JList listServer;
	public static DefaultListModel listModelServer = new DefaultListModel();//for GOI
	
	public ServerGUI() 
	{

	}
	
    public static void createAndShowGUI()
    {
    	frmServer = new JFrame("MyBox - Server");
    	frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmServer.setResizable(false);
    	
    	
    	contentPane = new JPanel(); 
        contentPane.setLayout(new CardLayout(20, 20));

        servermainpage = new serverMainPage();
        contentPane.add(servermainpage);
        connectionpage = new ConnectionPage();
        contentPane.add(connectionpage);
        
        
    	frmServer.getContentPane().add(contentPane, BorderLayout.CENTER);
    	frmServer.pack();
        frmServer.setBounds(100, 100, 800, 500);
        frmServer.setVisible(true);
        
		
		
    	meth = new Mtds();
    }
	
	


public static void main(String[] args) 
	{
		
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();

            }
        });
        
	}

}
