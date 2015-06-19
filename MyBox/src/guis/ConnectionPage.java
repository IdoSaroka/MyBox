package guis;

import main.ServerGUI;

import javax.swing.JProgressBar;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ConnectionPage extends ServerGUI
{
//	protected static JTextField textConnection;
	JScrollPane scrollPane;
	JButton btnCloseServer;
	private JLabel lblDate;
	
	public ConnectionPage() 
	{
		setLayout(null);
		
		btnCloseServer = new JButton("Close");
		btnCloseServer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			//	listModelServer.addElement(" 19/06/2015         " + " 09:28                 " + "");
				connectionpage.setVisible(false);
				servermainpage.setVisible(true);
			}
		});
		btnCloseServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCloseServer.setBounds(361, 399, 84, 30);
		add(btnCloseServer);
		
		textConnection = new JTextField();
		textConnection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textConnection.setBounds(10, 23, 407, 34);
		add(textConnection);
		textConnection.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 732, 308);
		add(scrollPane);
		
		listServer = new JList(listModelServer);
		scrollPane.setViewportView(listServer);
		
		lblDate = new JLabel(" Date                | Hour                | Message");
		scrollPane.setColumnHeaderView(lblDate);
		
		listModelServer.addElement(" 19/06/2015         " + " 09:28                 " + "");
	}
}
