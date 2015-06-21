package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
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
		
		JLabel lblAskToJoin = new JLabel("   Join a Group Of Interest");
		lblAskToJoin.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 28));
		lblAskToJoin.setBounds(188, 11, 376, 50);
		add(lblAskToJoin);
		
		JLabel lblWhyWouldYou = new JLabel("Why Would You Like To Join");
		lblWhyWouldYou.setFont(new Font("Footlight MT Light", Font.PLAIN, 18));
		lblWhyWouldYou.setBounds(33, 89, 236, 41);
		add(lblWhyWouldYou);
		
		textField = new JTextField();
		textField.setBounds(279, 89, 389, 32);
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
		btnHelp.setBounds(81, 381, 99, 36);
		add(btnHelp);
		
		JButton btnSignout = new JButton("Sign-Out");
		btnSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignout.setBounds(595, 381, 99, 36);
		add(btnSignout);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(579, 132, 89, 32);
		add(btnDone);
		
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
	}
}
