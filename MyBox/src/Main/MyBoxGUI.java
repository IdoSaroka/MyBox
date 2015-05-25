package Main;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

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

import controllers.*;

import java.awt.Toolkit;


public class MyBoxGUI {
	private Mtds mtd;
	private JFrame frmMybox;
	private static JTextField UserName;
	private static JTextField port;
	private static JTextField IP;
	private static JPasswordField passwordField;
	private LoginController user;
	
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
		private void initialize() {
		frmMybox = new JFrame();
		frmMybox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		frmMybox.setIconImage(Toolkit.getDefaultToolkit().getImage(MyBoxGUI.class.getResource("/images/gift-ideas-gift-card-bridal-gift-baby-shower-gift-gift-box-groom-gift-christmas-gift-party-gift-gift-for-wedding-friend-gift-birthday-gift-baby-gift-good-gift-box-ideas-for-friend-necklace-gift-box.jpg")));
		frmMybox.setBounds(100, 100, 450, 300);
		frmMybox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMybox.getContentPane().setLayout(new CardLayout(0, 0));
		frmMybox.setTitle("MyBox");
		frmMybox.getContentPane().add(Login, "name_124671031353011");
		Login.setLayout(null);
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
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
					frmMybox.setTitle("MyBox");
					MainPage.setVisible(true);
					Login.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(180, 204, 60, 34);
		Login.add(btnNewButton);
		
		JLabel lblWellcomeToMybox = new JLabel("Wellcome to MyBox");
		lblWellcomeToMybox.setForeground(new Color(51, 204, 255));
		lblWellcomeToMybox.setFont(new Font("Arial", Font.PLAIN, 18));
		lblWellcomeToMybox.setBounds(119, 23, 179, 20);
		Login.add(lblWellcomeToMybox);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(34, 66, 69, 14);
		Login.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(34, 91, 69, 14);
		Login.add(lblPassword);
		
		JLabel lblServerPort = new JLabel("Server port:");
		lblServerPort.setBounds(34, 140, 69, 14);
		Login.add(lblServerPort);
		
		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setBounds(34, 165, 69, 14);
		Login.add(lblServerIp);
		
		UserName = new JTextField();
		UserName.setBounds(119, 63, 96, 20);
		Login.add(UserName);
		UserName.setColumns(10);
		
		port = new JTextField();
		port.setBounds(119, 137, 96, 20);
		Login.add(port);
		port.setColumns(10);
		
		IP = new JTextField();
		IP.setBounds(119, 162, 96, 20);
		Login.add(IP);
		IP.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(119, 88, 96, 20);
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
		btnSignout.setBounds(293, 227, 131, 23);
		MainPage.add(btnSignout);
		
		JButton btnHelpme = new JButton("Help-Me");
		btnHelpme.setBounds(10, 227, 131, 23);
		MainPage.add(btnHelpme);
	}
		
	
		public static String getUserName(){
			return UserName.getText();
		}
		@SuppressWarnings("deprecation")
		public static String getPassword(){
			return passwordField.getText();
		}
		public static String getIP(){
			return IP.getText();
		}
		public static String getPort(){
			return port.getText();
		}
		
}
