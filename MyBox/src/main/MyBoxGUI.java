package main;

import guic.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
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
	protected static FileOwnerPage fileownerpage;
	protected static Messages messages;
	protected static SearchGOIPage searchgoipage;
	protected static JoinGOIPage joingoipage;
	protected static ShowSearchedGOI showsearchgoi;
	protected static CreateGOIPage creategoipage;
	protected static SharedFilessPage sharedfilesrspage;
	protected static FoldersPage folderspage;
	protected static YourGOIs yourgois;
	protected static UsersInGOI usersingoi;
	protected static Myfiles myfiles;
	protected static PendingRequest pendingrequest;
	protected static MannageGOI managegoi;
	
	protected static JFrame frmMyBox;
	protected Mtds meth = new Mtds();
	protected static JTextField txtPath;
	protected static String Path = "";
	protected static ClientGUI chat;
	protected static ChatClient client;
	protected static String txtPortNumber;
	protected static String txtUserName;
	protected static String txtPassword;
	protected static String IPAddress;
	
	protected static JLabel lblNewMSG;
	protected static JProgressBar progressBar;
	
	protected static boolean isMsg;

	protected static JList list;
	protected static JList list2;
	protected static JList list3;
	protected static JList list4;
	protected static JList listRequest;
	protected static JList listSharedFls;
	protected static JList listGoi;
	protected static DefaultListModel ListModelGoi = new DefaultListModel();
	protected static DefaultListModel ListModel = new DefaultListModel();//for msgs
	protected static DefaultListModel ListModel2 = new DefaultListModel();//for GOI
	protected static DefaultListModel ListModel3 = new DefaultListModel();//for YourGOI
	protected static DefaultListModel ListModel4 = new DefaultListModel();//for UsersInGOi
	protected static DefaultListModel listSharedFlsModel = new DefaultListModel();//for Folders
	protected static DefaultListModel ListModelRequest= new DefaultListModel();
	//protected static ArrayList<String> LsitArray = new ArrayList<String>();
	////////////////////////
	
	
	protected static String portNumber = "1245";
	
	protected static User user;
	
    private static void createAndShowGUI()
    {
        frmMyBox = new JFrame("My Box");
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
        
        fileownerpage = new FileOwnerPage();
        contentPane.add(fileownerpage);
        
        messages = new Messages();
        contentPane.add(messages);
        
        searchgoipage = new SearchGOIPage();
        contentPane.add(searchgoipage);
        
        joingoipage = new JoinGOIPage();
        contentPane.add(joingoipage);
        
        showsearchgoi = new ShowSearchedGOI();
        contentPane.add(showsearchgoi);
        
        creategoipage = new CreateGOIPage();
        contentPane.add(creategoipage);
        
        sharedfilesrspage = new SharedFilessPage();
        contentPane.add(sharedfilesrspage); 
        
        folderspage = new FoldersPage();
        contentPane.add(folderspage);
        
        yourgois = new YourGOIs();
        contentPane.add(yourgois);
        
        usersingoi = new UsersInGOI();
        contentPane.add(usersingoi);
        
        myfiles = new Myfiles();
        contentPane.add(myfiles);
        
        pendingrequest = new PendingRequest();
        contentPane.add(pendingrequest);
        
        managegoi = new MannageGOI();
        contentPane.add(managegoi);
        
        JPanel mainPanel = new JPanel(); 
        
        final JButton previousButton = new JButton("PREVIOUS");
        
        final JButton nextButton = new JButton("NEXT");
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

	public static User getUser(){
		   return user;
	}
	
	public static boolean getIsMsg(){
		return isMsg;		
	}
	
	public static void setIsMsg(boolean msgStatus){
		isMsg=msgStatus;
	}
	
	public static void byeBye(){
		ArrayList<Object> msg = new ArrayList<>();
		msg.add("SignOut");
		msg.add(user.getUserName());
		try {
			client.sendToServer(msg);
		} catch (IOException e) {
			System.out.println("Unable to send to server");
			e.printStackTrace();
		}
		
	}
	public static void setUser(User usr){
		user=usr;
	}
} 



