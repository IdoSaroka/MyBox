package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;


//import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import client.ChatClient;
import client.ClientGUI;
import controllers.*;

import java.awt.Toolkit;
import GUIs.*;
//import ocsf.client.*;

//yes

public class MyBoxGUI 
{
	private Mtds mtd;
	private JFrame frmMybox;
	private static JTextField UserName;
	private static JTextField port;
	private static JTextField IP;
	private static JPasswordField passwordField;
	private static LoginController user;
	//private static  Msg msg;
	private static ClientGUI chat;
	private static ChatClient client;
	
	final JPanel MainPage = new JPanel();
	final JPanel Login = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyBoxGUI window = new MyBoxGUI();
					window.frmMybox.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public MyBoxGUI() 
	{
		mtd = new Mtds();
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
		private void initialize() 
		{
		frmMybox = new JFrame();
		frmMybox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		frmMybox.setIconImage(Toolkit.getDefaultToolkit().getImage(MyBoxGUI.class.getResource("/images/gift-ideas-gift-card-bridal-gift-baby-shower-gift-gift-box-groom-gift-christmas-gift-party-gift-gift-for-wedding-friend-gift-birthday-gift-baby-gift-good-gift-box-ideas-for-friend-necklace-gift-box.jpg")));
		frmMybox.setBounds(100, 100, 800, 500);
		frmMybox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMybox.getContentPane().setLayout(new CardLayout(0, 0));
		frmMybox.setTitle("MyBox");
		frmMybox.getContentPane().add(Login, "name_124671031353011");
		Login.setLayout(null);
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) 
			{
				Boolean ok = false;
				String UName, Pass, Prt, SIP;
				UName = UserName.getText();
				Pass = passwordField.getText();
				Prt = port.getText();
				SIP = IP.getText();
				if( ( mtd.isUserValid(UName) && mtd.isPasswordValid(Pass) && mtd.isPortValid(Prt) && mtd.isIPValid(SIP)) 
						&& ( !UName.equals("")  && !Pass.equals("") && !Prt.equals("")  && !SIP.equals("") ) )
					ok = true;
				else
				{
					if(!mtd.isUserValid(UName) || UName.equals(""))
						JOptionPane.showMessageDialog(frmMybox,"User Name Can Contain Only The Following Characters: ( 0-9 , a-z , A-Z , _ )"
								+ " ","Invalid characters",JOptionPane.WARNING_MESSAGE);
					else if(!mtd.isPasswordValid(Pass) || Pass.equals(""))
						JOptionPane.showMessageDialog(frmMybox,"Invalid characters","Error Message",JOptionPane.WARNING_MESSAGE);
					else if(!mtd.isPortValid(Prt) || Prt.equals(""))
						JOptionPane.showMessageDialog(frmMybox,"Port Can Contain Only Numbers","Invalid characters",JOptionPane.WARNING_MESSAGE);
					else if(!mtd.isIPValid(SIP) || SIP.equals(""))
						JOptionPane.showMessageDialog(frmMybox,"IP Address Can Contain Only Numbers seperated By Dots '.' ","Invalid characters",JOptionPane.WARNING_MESSAGE);
				}
				if(ok)
				{
					
					LoginController user = new LoginController();
					//chat= new ClientGUI(IP.getText(),port.getText());
					//client = ClientGUI.getClient();
					//msg=new Msg("login",user);
					//client.handleMessageFromClientUI(msg);
					MainPage.setVisible(true);
					Login.setVisible(false);
				}
			}
		});
		
		
		btnNewButton.setBounds(344, 313, 82, 48);
		Login.add(btnNewButton);
		
		JLabel lblWellcomeToMybox = new JLabel("Wellcome to MyBox");
		lblWellcomeToMybox.setForeground(new Color(51, 204, 255));
		lblWellcomeToMybox.setFont(new Font("Arial", Font.PLAIN, 36));
		lblWellcomeToMybox.setBounds(224, 23, 327, 42);
		Login.add(lblWellcomeToMybox);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(268, 109, 89, 14);
		lblUserName.setFont(new Font("Arial", Font.PLAIN, 14));
		Login.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(268, 139, 69, 14);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		Login.add(lblPassword);
		
		JLabel lblServerPort = new JLabel("Server port:");
		lblServerPort.setBounds(268, 197, 82, 14);
		lblServerPort.setFont(new Font("Arial", Font.PLAIN, 14));
		Login.add(lblServerPort);
		
		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setBounds(266, 222, 71, 14);
		lblServerIp.setFont(new Font("Arial", Font.PLAIN, 14));
		Login.add(lblServerIp);
		
		UserName = new JTextField();
		UserName.setBounds(406, 107, 96, 20);
		Login.add(UserName);
		UserName.setColumns(10);
		
		port = new JTextField();
		port.setBounds(406, 195, 96, 20);
		Login.add(port);
		port.setColumns(10);
		
		IP = new JTextField();
		IP.setBounds(406, 220, 96, 20);
		Login.add(IP);
		IP.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(406, 137, 96, 20);
		Login.add(passwordField);
		
		
		frmMybox.getContentPane().add(MainPage, "name_124673255185302");
		MainPage.setLayout(null);
		
		JButton LeaveGOI = new JButton("Leave a GOI");
		LeaveGOI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		LeaveGOI.setBounds(10, 66, 153, 23);
		MainPage.add(LeaveGOI);
		
		JButton btnJoinAGoi = new JButton("Join a GOI");
		btnJoinAGoi.setBounds(10, 100, 153, 23);
		MainPage.add(btnJoinAGoi);
		
		JButton btnSearchForA = new JButton("Search for a GOI");
		btnSearchForA.setBounds(10, 134, 153, 23);
		MainPage.add(btnSearchForA);
		
		JButton btnCreateAGoi = new JButton("Create a GOI");
		btnCreateAGoi.setBounds(10, 32, 153, 23);
		MainPage.add(btnCreateAGoi);
		
		JButton btnFilesSharedWith = new JButton("Files shared with you");
		btnFilesSharedWith.setBounds(271, 66, 153, 23);
		MainPage.add(btnFilesSharedWith);
		
		JButton btnUploadAFile = new JButton("Upload a file");
		btnUploadAFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnUploadAFile.setBounds(271, 32, 153, 23);
		MainPage.add(btnUploadAFile);
		
		JButton btnSignout = new JButton("Sign-Out");
		btnSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialog=JOptionPane.showConfirmDialog(Login, getUserName()+", are you sure you wants to leave?", "Do you want to leave?", JOptionPane.YES_NO_OPTION);
				if(dialog==0){
					//client.quit();
					
				}
			}
		});
		btnSignout.setBounds(293, 227, 131, 23);
		MainPage.add(btnSignout);
		
		JButton btnHelpme = new JButton("Help-Me");
		btnHelpme.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JOptionPane.showMessageDialog(Login,"Hello "+ getUserName()+", wellcom to Mybox!\n"
						+ "Inside GOIs buttons you can ask to join, leave and even create a GOI.\n"
						+ "Inside Files buttons you can check and edit files people shared with you.\n"
						+ "You can even become an uploader and share files you own.\n"
						+ "We wish you good luck and have fun using MyBox!");
			}
		});
		btnHelpme.setBounds(10, 227, 131, 23);
		MainPage.add(btnHelpme);
	}
		
	
		public static  String getUserName(){
			return UserName.getText();
		}
		@SuppressWarnings("deprecation")
		public static  String getPassword(){
			return passwordField.getText();
		}
		public static   String getIP(){
			return IP.getText();
		}
		public static   String getPort(){
			return port.getText();
		}
		public static LoginController getLoginInfo(){
			return user;
		}
		/*public static  Msg getMsg(){
			return msg;
		}*/
		public static ChatClient getClient(){
			return client;
		}
		public static ClientGUI getChat(){
			return chat;
		}
		
}
