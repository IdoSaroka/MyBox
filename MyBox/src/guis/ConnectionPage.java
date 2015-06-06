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

public class ConnectionPage extends ServerGUI
{

	public ConnectionPage() 
	{
		setLayout(null);
		
		JButton btnCloseServer = new JButton("Close Server");
		btnCloseServer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				connectionpage.setVisible(false);
				servermainpage.setVisible(true);
			}
		});
		btnCloseServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCloseServer.setBounds(350, 291, 108, 44);
		add(btnCloseServer);
		
		textConnection = new JTextField();
		textConnection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textConnection.setBounds(196, 140, 425, 34);
		add(textConnection);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Ran\\Desktop\\MyBox.jpg"));
		label.setBounds(10, 11, 780, 478);
		add(label);
		textConnection.setColumns(10);
	}
}
