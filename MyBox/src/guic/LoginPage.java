package guic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.MyBoxGUI;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

import controllers.LoginController;
import client.ChatClient;
import client.ClientGUI;
import entities.User;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JProgressBar;

public class LoginPage extends MyBoxGUI
{
    /*
     * Here this is our first Card of CardLayout, which will
     * be added to the contentPane object of JPanel, which
     * has the LayoutManager set to CardLayout.
     */  
    private ActionListener action;
    private JTextField userNametxt;
    private JTextField Porttxt;
    private JPasswordField passwordField;
    private JTextField iptxt1;
    private JTextField iptxt2;
    private JTextField iptxt3;
    private JTextField iptxt4;
    private JLabel lblpoint1;
    private JLabel lblpoint2;
    private JLabel lblpoint3;
    private JLabel prtsight;
    private boolean ok = false;
    private String IPAddressVaild;
    int i = 0;
    private JLabel lblPicMBX;
    
    /*
     * Default for shimon's use
     * 
     */
    private String uName="Shimon_Ben_Alul";
    private String uPass="NoPassword#$@";
    private String uPort = "1254";
    
    
    /*
     * End of Shimon's default
     */
    
    public LoginPage() 
    {
        init();
    }

    private void init() 
    {
        final JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Franklin Gothic Book", Font.BOLD | Font.ITALIC, 18));
        btnLogin.setBounds(347, 298, 80, 36);
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == btnLogin)
                {    
                	IPAddressVaild = meth.Connecting(iptxt1.getText(), iptxt2.getText(), iptxt3.getText(), iptxt4.getText());
    				if( ( meth.isUserValid(userNametxt.getText()) && meth.isPasswordValid(passwordField.getText()) && meth.isPortValid(Porttxt.getText()) &&
    						meth.isIPValid(IPAddressVaild)) 
    						&& ( !userNametxt.getText().equals("")  && !passwordField.getText().equals("") && !Porttxt.getText().equals("")
    						&& !(iptxt1.getText().equals("") || iptxt2.getText().equals("") || iptxt3.getText().equals("") || iptxt4.getText().equals("") ))
    						&& iptxt1.getText().length()<=3 && iptxt2.getText().length()<=3 && iptxt3.getText().length()<=3 && iptxt4.getText().length()<=3 )
    				{
    					ok = true;
    					IPAddress = meth.Connecting(iptxt1.getText()+".", iptxt2.getText()+".", iptxt3.getText()+".", iptxt4.getText());	
    				}
    				else
    				{
    					if(!meth.isUserValid(userNametxt.getText()) || userNametxt.getText().equals(""))
    						JOptionPane.showMessageDialog(frmMyBox,"User Name Can Contain Only The Following Characters: ( 0-9 , a-z , A-Z , _ )"
    								+ " ","Invalid characters",JOptionPane.WARNING_MESSAGE);
    					else if(!meth.isPasswordValid(passwordField.getText()) || passwordField.getText().equals(""))
    						JOptionPane.showMessageDialog(frmMyBox,"Invalid characters","Error Message",JOptionPane.WARNING_MESSAGE);
    					else if(!meth.isPortValid(Porttxt.getText()) || Porttxt.getText().equals(""))
    						JOptionPane.showMessageDialog(frmMyBox,"Port Can Contain Only Numbers","Invalid characters",JOptionPane.WARNING_MESSAGE);
    					else if( !meth.isIPValid( IPAddressVaild) || iptxt1.getText().equals("") || iptxt2.getText().equals("") || iptxt3.getText().equals("") || iptxt4.getText().equals("") )
    						JOptionPane.showMessageDialog(frmMyBox,"IP Address Can Contain Only Numbers ","Invalid characters",JOptionPane.WARNING_MESSAGE);
    					else if (iptxt1.getText().length()<=3 || iptxt2.getText().length()<=3 || iptxt3.getText().length()<=3 || iptxt4.getText().length()<=3)
    						JOptionPane.showMessageDialog(frmMyBox,"Each Octet can contain up to 3 numbers ","Invalid characters",JOptionPane.WARNING_MESSAGE);
    				}
    				if(ok)
    				{
    					/*
    			        progressBar = new JProgressBar();
    			        progressBar.setBounds(301, 354, 146, 14); 
    			       	add(progressBar);
    		         	progressBar.setValue(15);
    		         	*/
    					ok = false;
    					txtUserName = userNametxt.getText();
    					txtPortNumber = Porttxt.getText();
    			        txtPassword = passwordField.getText();
    			        
    			        userNametxt.setText("");
    			        Porttxt.setText("");
    			        passwordField.setText("");
    			        iptxt1.setText("");
    			        iptxt2.setText("");
    			        iptxt3.setText("");
    			        iptxt4.setText("");
    			
    			        chat = new ClientGUI(IPAddress,txtPortNumber);
    			        try {
    						client= new ChatClient(IPAddress,Integer.parseInt(txtPortNumber),chat);
    					} catch (NumberFormatException | IOException e1) {
    						JOptionPane.showMessageDialog(loginpage,"Unable to connect to server");
    						e1.printStackTrace();
    					}
    			        LoginController login = new LoginController(txtUserName,txtPassword);
    			        try {
    						System.out.println("sending: "+login.toString());
    						login.sendLogin();
    						ArrayList<Object> message = (ArrayList) client.getMessage();
    						if(message.get(0) instanceof User)
    						{
    							boolean success = (new File("C:\\MyBox\\Downloaded Files")).mkdirs();
    							if(!success){
    								System.out.println("User already have this folder");
    							}
    							user=(User)message.get(0);
    							MyBoxGUI.setIsMsg((boolean)message.get(1));
    							System.out.println(user.toString()+" is connected");
    							
    							if(MyBoxGUI.getIsMsg())
    	    			        	lblNewMSG.setVisible(true);
    							
    							loginpage.setVisible(false);
    							userpage.setVisible(true);
    					//		if(user.getrole().equals("User"))
    					//			userpage.setVisible(true);
    					//		else if (user.getrole().equals("SystemAdmin"))
    					//			adminpage.setVisible(true);
    					//		else if (user.getRole().equals("FileOwner"))
    						//		fileownerpage.setVisible(true);
    	    					
    						}
    						else{
    							String str = (String)message.get(0);
    							JOptionPane.showMessageDialog(loginpage,str);
    						}
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
    			        
    			        if(MyBoxGUI.getIsMsg())
    			        	lblNewMSG.setVisible(true);

    			      //  adminpage.setVisible(true); // testing
    				}
                }
            }
        };

        btnLogin.addActionListener(action);
        setLayout(null);

        add(btnLogin);
        
        JLabel lblWelcomeToMybox = new JLabel("Welcome To MyBox");
        lblWelcomeToMybox.setFont(new Font("Forte", Font.PLAIN, 54));
        lblWelcomeToMybox.setBounds(153, 11, 501, 57);
        add(lblWelcomeToMybox);
        
        JLabel lblUserName = new JLabel("User Name:");
        lblUserName.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 18));
        lblUserName.setBounds(233, 92, 102, 36);
        add(lblUserName);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 18));
        lblPassword.setBounds(233, 139, 100, 22);
        add(lblPassword);
        
        userNametxt = new JTextField();
        userNametxt.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 18));
        userNametxt.setBounds(363, 102, 197, 20);
        userNametxt.setText(uName);
        add(userNametxt);
        userNametxt.setColumns(10);
        
        
        JLabel lblPortNumber = new JLabel("Port Number:");
        lblPortNumber.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 18));
        lblPortNumber.setBounds(233, 184, 120, 14);
        add(lblPortNumber);
        
        Porttxt = new JTextField();
        Porttxt.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 18));
        Porttxt.setBounds(363, 183, 197, 20);
        Porttxt.setText(uPort);
        add(Porttxt);
        Porttxt.setColumns(10);
        
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 18));
        passwordField.setBounds(363, 142, 197, 20);
        passwordField.setText(uPass);
        add(passwordField);
        
        
        JLabel lblIpAddress = new JLabel("IP Address:");
        lblIpAddress.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 18));
        lblIpAddress.setBounds(233, 225, 102, 14);
        add(lblIpAddress);
        
        iptxt1 = new JTextField();
        iptxt1.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 18));
        iptxt1.setBounds(363, 224, 42, 20);
        add(iptxt1);
        iptxt1.setColumns(10);

        
        iptxt2 = new JTextField();
        iptxt2.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 18));
        iptxt2.setBounds(415, 224, 42, 20);
        add(iptxt2);
        iptxt2.setColumns(10);
        
        iptxt3 = new JTextField();
        iptxt3.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 18));
        iptxt3.setBounds(467, 224, 42, 20);
        add(iptxt3);
        iptxt3.setColumns(10);
        
        iptxt4 = new JTextField();
        iptxt4.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 18));
        iptxt4.setBounds(518, 224, 42, 20);
        add(iptxt4);
        iptxt4.setColumns(10);
        
        lblpoint1 = new JLabel(".");
        lblpoint1.setFont(new Font("Arial", Font.PLAIN, 18));
        lblpoint1.setBounds(408, 230, 12, 14);
        add(lblpoint1);
        
        lblpoint2 = new JLabel(".");
        lblpoint2.setFont(new Font("Arial", Font.PLAIN, 18));
        lblpoint2.setBounds(459, 230, 12, 14);
        add(lblpoint2);
        
        lblpoint3 = new JLabel(".");
        lblpoint3.setFont(new Font("Arial", Font.PLAIN, 18));
        lblpoint3.setBounds(511, 230, 12, 14);
        add(lblpoint3);
        
        prtsight = new JLabel("(0 - 9999)");
        prtsight.setFont(new Font("Footlight MT Light", Font.PLAIN, 12));
        prtsight.setBounds(254, 199, 56, 14);
        add(prtsight);
        
        
        lblPicMBX = new JLabel("");
        lblPicMBX.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
        lblPicMBX.setBounds(10, 11, 780, 478);
        add(lblPicMBX);
        
  
    }
}
    
