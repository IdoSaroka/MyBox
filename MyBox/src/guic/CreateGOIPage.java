package guic;

import main.MyBoxGUI;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateGOIPage extends MyBoxGUI
{
	private JTextField txtGOIName;
	private JTextField txtGOISubject;
	private JTextField txtGOIDescription;
	public CreateGOIPage()
	{
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				creategoipage.setVisible(false);
				adminpage.setVisible(true);
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		JLabel lblCreateNewGoi = new JLabel("Create New GOI");
		lblCreateNewGoi.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 28));
		lblCreateNewGoi.setBounds(248, 11, 268, 43);
		add(lblCreateNewGoi);
		
		JLabel lblGoiName = new JLabel("GOI Name");
		lblGoiName.setBounds(176, 123, 83, 14);
		add(lblGoiName);
		
		txtGOIName = new JTextField();
		txtGOIName.setBounds(277, 123, 231, 14);
		add(txtGOIName);
		txtGOIName.setColumns(10);
		
		JLabel lblGoiSubject = new JLabel("GOI Subject");
		lblGoiSubject.setBounds(176, 163, 83, 14);
		add(lblGoiSubject);
		
		txtGOISubject = new JTextField();
		txtGOISubject.setBounds(277, 163, 231, 14);
		add(txtGOISubject);
		txtGOISubject.setColumns(10);
		
		JLabel lblGoiDescription = new JLabel("GOI Description");
		lblGoiDescription.setBounds(176, 205, 83, 14);
		add(lblGoiDescription);
		
		txtGOIDescription = new JTextField();
		txtGOIDescription.setBounds(277, 205, 231, 14);
		add(txtGOIDescription);
		txtGOIDescription.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(346, 282, 89, 23);
		add(btnDone);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(128, 376, 102, 31);
		add(btnHelp);
		
		JButton btnSignout = new JButton("Sign-Out");
		btnSignout.setBounds(554, 376, 89, 31);
		add(btnSignout);
	}
}
