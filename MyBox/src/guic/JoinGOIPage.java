package guic;

import main.MyBoxGUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinGOIPage extends MyBoxGUI
{
	private JTextField textField;
	
	public JoinGOIPage()
	{
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				joingoipage.setVisible(false);
				showsearchgoi.setVisible(true);
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		JLabel lblAskToJoin = new JLabel("Ask to join a Group Of Interest");
		lblAskToJoin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAskToJoin.setBounds(267, 46, 316, 41);
		add(lblAskToJoin);
		
		JLabel lblWhyWouldYou = new JLabel("Why would you like to join");
		lblWhyWouldYou.setBounds(80, 152, 152, 41);
		add(lblWhyWouldYou);
		
		textField = new JTextField();
		textField.setBounds(214, 161, 316, 23);
		add(textField);
		textField.setColumns(10);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(frmMyBox,"Here you send a request to the system administrator for joining a specific GOI (Group of Interest).\n"
				+" In the blank text field you need to explain to the system administrator why you want to join to the GOI.\n"
				+ "Please give resonable reasons to convice the system administrator that you deserved to be a part of the GOI.","Help",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnHelp.setBounds(130, 291, 89, 23);
		add(btnHelp);
		
		JButton btnSignout = new JButton("Sign-Out");
		btnSignout.setBounds(516, 291, 89, 23);
		add(btnSignout);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(357, 225, 89, 23);
		add(btnDone);
	}
}
