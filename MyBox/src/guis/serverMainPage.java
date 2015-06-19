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
import javax.swing.JPasswordField;

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
		lblWellcomToMybox.setFont(new Font("Arial", Font.PLAIN, 32));
		lblWellcomToMybox.setBounds(138, 11, 699, 37);
		add(lblWellcomToMybox);
		
		JLabel lblEnterDatabasLocation = new JLabel("Enter DB Location:");
		lblEnterDatabasLocation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEnterDatabasLocation.setBounds(138, 85, 207, 14);
		add(lblEnterDatabasLocation);
		
		JLabel lblEnterUserName = new JLabel("Enter User Name (for DB):");
		lblEnterUserName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEnterUserName.setBounds(138, 154, 217, 28);
		add(lblEnterUserName);
		
		JLabel lblEnterPasswordfor = new JLabel("Enter Password (for DB):");
		lblEnterPasswordfor.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEnterPasswordfor.setBounds(138, 234, 217, 19);
		add(lblEnterPasswordfor);
		
		JLabel lblEnterDbPort = new JLabel("Enter DB Port Number:");
		lblEnterDbPort.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEnterDbPort.setBounds(138, 310, 207, 19);
		add(lblEnterDbPort);
		
		JLabel label = new JLabel("(0-9999)");
		label.setFont(new Font("Arial", Font.PLAIN, 11));
		label.setBounds(213, 328, 46, 14);
		add(label);
		
		txtPath = new JTextField();
		txtPath.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPath.setBounds(413, 75, 224, 37);
		add(txtPath);
		txtPath.setColumns(10);
		
		textUserName = new JTextField();
		textUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textUserName.setColumns(10);
		textUserName.setBounds(413, 152, 224, 37);
		add(textUserName);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
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
					textConnection.setText("Server now listing for connections on port: "+portNumber);
					servermainpage.setVisible(false);
					connectionpage.setVisible(true);
					
				}
			}
		});
		btnOk.setBounds(307, 392, 82, 37);
		add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(399, 392, 82, 37);
		add(btnCancel);
		

		
		JButton btnBrowse = new JButton("...");
		btnBrowse.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				browse = new Browse();
			}
		});
		btnBrowse.setBounds(660, 75, 46, 37);
		add(btnBrowse);
		
		textPort = new JTextField();
		textPort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPort.setColumns(10);
		textPort.setBounds(413, 302, 224, 37);
		textPort.setText(portNumber);
		add(textPort);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPassword.setBounds(413, 227, 224, 37);
		add(textPassword);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(serverMainPage.class.getResource("/guis/MyBox.jpg")));
		label_2.setBounds(10, 11, 780, 478);
		add(label_2);
	}
}
