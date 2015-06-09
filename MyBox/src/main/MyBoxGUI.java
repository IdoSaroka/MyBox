package main;

import guic.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controllers.LoginController;
import client.ChatClient;
import client.ClientGUI;
import entities.User;
import files.Browse;

/* Here we are first declaring our class that will act as the
 * base for other panels or in other terms the base for CardLayout.
 */

public class MyBoxGUI extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyBoxGUI() 
	{
	}
	
	protected static LoginPage loginpage;
	protected static UserPage userpage;
	protected static AdminPage adminpage;
	protected static UploadFilePage uploadfilepage;
	protected static JFrame frmMyBox;
	protected Mtds meth = new Mtds();
	
	protected static JTextField txtPath;
	protected static String IPAddress;
	protected static String Path = "";
	
	protected static ClientGUI chat;
	protected static ChatClient client;
	protected static String txtPortNumber;
	protected static String txtUserName;
	protected static String txtPassword;
	
	protected static User user;
	
    private static void createAndShowGUI()
    {
         frmMyBox = new JFrame("My Box");
//        frmMyBox.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Ran\\workspace\\MyBoxTesting\\bin\\main\\MyBox.jpg")));
        frmMyBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMyBox.setResizable(false);
        // This JPanel is the base for CardLayout for other JPanels.
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new CardLayout(20, 20));

        /* Here we be making objects of the Window Series classes
         * so that, each one of them can be added to the JPanel 
         * having CardLayout. 
         */
        
        loginpage = new LoginPage();
        contentPane.add(loginpage);
        userpage = new UserPage();
        contentPane.add(userpage);
        adminpage = new AdminPage();
        contentPane.add(adminpage);
        uploadfilepage = new UploadFilePage();
        contentPane.add(uploadfilepage);

	         
        JPanel mainPanel = new JPanel(); 
        
        final JButton previousButton = new JButton("PREVIOUS");
        
        final JButton nextButton = new JButton("NEXT");
 //       mainPanel.add(previousButton);
 //       mainPanel.add(nextButton);

        /* Adding the ActionListeners to the JButton,
         * so that the user can see the next Card or
         * come back to the previous Card, as desired.
         */
       previousButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.previous(contentPane);
            }
        });
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.next(contentPane);   
            }
        });

    //     Adding the contentPane (JPanel) and buttonPanel to JFrame.
        frmMyBox.getContentPane().add(contentPane, BorderLayout.CENTER);
        frmMyBox.getContentPane().add(mainPanel, BorderLayout.PAGE_END);
        frmMyBox.pack();
        frmMyBox.setBounds(100, 100, 800, 500);
        frmMyBox.setVisible(true);
        
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
    
	public static  String getUserName(){
		return txtUserName;
	}
	@SuppressWarnings("deprecation")
	public static  String getPassword(){
		return txtPassword;
	}
	public static   String getIP(){
		return IPAddress;
	}
	public static   String getPort(){
		return txtPortNumber;
	}
	
	public static LoginController getLoginInfo(){
		return loginpage.getLoginInfo();
	}
	
	public static ChatClient getClient(){
		return client;
	}
	public static ClientGUI getChat(){
		return chat;
	}

	public User getUser(){
		   return user;
	}
} 



