package guic;

import main.MyBoxGUI;
import javax.swing.JButton;
import javax.swing.JLabel;
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
				userpage.setVisible(true);
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
