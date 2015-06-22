package guis;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.ServerGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import files.Browse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JPasswordField;

import database.EchoServer;

public class serverMainPage extends ServerGUI
{
	private JTextField textUserName;
	private boolean ok;
	private JTextField textPort;
	private Browse browse;
	private JPasswordField textPassword;
	public serverMainPage()
	{
		setLayout(null);
		
		JLabel lblWellcomToMybox = new JLabel("Welcom to MyBox Server Application");
		lblWellcomToMybox.setFont(new Font("Forte", Font.PLAIN, 36));
		lblWellcomToMybox.setBounds(91, 11, 699, 37);
		add(lblWellcomToMybox);
		
		JLabel lblEnterDatabasLocation = new JLabel("DB Location:");
		lblEnterDatabasLocation.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 20));
		lblEnterDatabasLocation.setBounds(175, 84, 196, 14);
		add(lblEnterDatabasLocation);
		
		JLabel lblEnterUserName = new JLabel("User Name (for DB):");
		lblEnterUserName.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 20));
		lblEnterUserName.setBounds(175, 130, 196, 28);
		add(lblEnterUserName);
		
		JLabel lblEnterPasswordfor = new JLabel("Password (for DB):");
		lblEnterPasswordfor.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 20));
		lblEnterPasswordfor.setBounds(175, 183, 196, 19);
		add(lblEnterPasswordfor);
		
		JLabel lblEnterDbPort = new JLabel("DB Port Number:");
		lblEnterDbPort.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 20));
		lblEnterDbPort.setBounds(175, 231, 196, 19);
		add(lblEnterDbPort);
		
		JLabel label = new JLabel("(0-9999)");
		label.setFont(new Font("Footlight MT Light", Font.PLAIN, 12));
		label.setBounds(220, 247, 57, 14);
		add(label);
		
		txtPath = new JTextField();
		txtPath.setFont(new Font("Footlight MT Light", Font.PLAIN, 20));
		txtPath.setBounds(381, 75, 224, 37);
		add(txtPath);
		txtPath.setColumns(10);
		
		textUserName = new JTextField();
		textUserName.setFont(new Font("Footlight MT Light", Font.PLAIN, 20));
		textUserName.setColumns(10);
		textUserName.setBounds(381, 124, 224, 37);
		add(textUserName);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				database.EchoServer.serverUserName = textUserName.getText();
				database.EchoServer.serverUserPassword = textPassword.getText();
				//database.EchoServer.serverPort = textConnection.getText();
				System.out.println(textPort.getText());
				database.EchoServer.serverPort = Integer.parseInt(textPort.getText());
				
				
				ok = false;
				portNumber = textPort.getText();
				if( (meth.isUserValid(textUserName.getText()) && meth.isPasswordValid(textPassword.getText()) && meth.isPortValid(portNumber))
						&&  ( !textUserName.getText().equals("")  && !textPassword.getText().equals("") && !portNumber.equals("") ))
					ok = true;
				else
    				{
    					if(!meth.isUserValid(textUserName.getText()) || textUserName.getText().equals(""))
    						JOptionPane.showMessageDialog(frmServer,"User Name Can Contain Only The Following Characters: ( 0-9 , a-z , A-Z , _ )"
    								+ " ","Invalid characters",JOptionPane.WARNING_MESSAGE);
    					else if(!meth.isPasswordValid(textPassword.getText()) || textPassword.getText().equals(""))
    						JOptionPane.showMessageDialog(frmServer,"Invalid characters","Error Message",JOptionPane.WARNING_MESSAGE);
    					else if(!meth.isPortValid(portNumber) || portNumber.equals(""))
    						JOptionPane.showMessageDialog(frmServer,"Port Can Contain Only Numbers","Invalid characters",JOptionPane.WARNING_MESSAGE);
    				}
				
				
				
				
				
				if(ok)
				{
	                try {
	                	
						database.EchoServer.createConnection();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
					textConnection.setText("Server now listing for connections on port: "+portNumber);
					servermainpage.setVisible(false);
					connectionpage.setVisible(true);
					
				}
			}
		});
		btnOk.setBounds(310, 290, 82, 37);
		add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnCancel.setBounds(394, 290, 82, 37);
		add(btnCancel);
		

		
		JButton btnBrowse = new JButton("...");
		btnBrowse.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				browse = new Browse();
			}
		});
		btnBrowse.setBounds(617, 78, 38, 28);
		add(btnBrowse);
		
		textPort = new JTextField();
		textPort.setFont(new Font("Footlight MT Light", Font.PLAIN, 20));
		textPort.setColumns(10);
		textPort.setBounds(381, 220, 224, 37);
		textPort.setText(portNumber);
		add(textPort);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Footlight MT Light", Font.PLAIN, 20));
		textPassword.setBounds(381, 172, 224, 37);
		add(textPassword);
		
		JLabel lblMyBox = new JLabel("");
		lblMyBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMyBox.setIcon(new ImageIcon(serverMainPage.class.getResource("/guis/MyBox.jpg")));
		lblMyBox.setBounds(10, 11, 790, 478);
		add(lblMyBox);
		
	}
}
