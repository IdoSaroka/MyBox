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
	private boolean ok;
	private Browse browse;
	public serverMainPage()
	{
		setLayout(null);
		
		JLabel lblWellcomToMybox = new JLabel("Welcome to MyBox Server Application");
		lblWellcomToMybox.setFont(new Font("Forte", Font.PLAIN, 36));
		lblWellcomToMybox.setBounds(91, 11, 699, 37);
		add(lblWellcomToMybox);
		
		JLabel lblEnterUserName = new JLabel("User Name (for DB):");
		lblEnterUserName.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 22));
		lblEnterUserName.setBounds(163, 77, 226, 28);
		add(lblEnterUserName);
		
		JLabel lblEnterPasswordfor = new JLabel("Password (for DB):");
		lblEnterPasswordfor.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 22));
		lblEnterPasswordfor.setBounds(163, 144, 226, 19);
		add(lblEnterPasswordfor);
		
		JLabel lblEnterDbPort = new JLabel("Streaming Port:");
		lblEnterDbPort.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 22));
		lblEnterDbPort.setBounds(163, 202, 226, 30);
		add(lblEnterDbPort);
		
		JLabel label = new JLabel("(0-9999)");
		label.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		label.setBounds(218, 235, 57, 14);
		add(label);
		
		textUserName = new JTextField();
		textUserName.setFont(new Font("Footlight MT Light", Font.PLAIN, 20));
		textUserName.setColumns(10);
		textUserName.setBounds(399, 71, 224, 37);
		add(textUserName);
		
		JButton btnOk = new JButton("Connect");
		btnOk.setFont(new Font("Footlight MT Light", Font.PLAIN, 15));
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				database.EchoServer.serverUserName = textUserName.getText();
				database.EchoServer.serverUserPassword = textPassword.getText();
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
    								+ " ","Invalid charac ters",JOptionPane.WARNING_MESSAGE);
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
	                
					textConnection.setText("Server is now listening for connections on port: "+portNumber);
					servermainpage.setVisible(false);
					connectionpage.setVisible(true);
					
				}
			}
		});
		btnOk.setBounds(353, 285, 90, 43);
		add(btnOk);
		
		textPort = new JTextField();
		textPort.setFont(new Font("Footlight MT Light", Font.PLAIN, 20));
		textPort.setColumns(10);
		textPort.setBounds(399, 200, 224, 37);
		textPort.setText("1254");
		add(textPort);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Footlight MT Light", Font.PLAIN, 20));
		textPassword.setBounds(399, 133, 224, 37);
		add(textPassword);
		
		JLabel lblMyBox = new JLabel("");
		lblMyBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMyBox.setIcon(new ImageIcon(serverMainPage.class.getResource("/guis/MyBox.jpg")));
		lblMyBox.setBounds(10, 11, 790, 478);
		add(lblMyBox);
		
	}
}
