package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
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
		lblGoiName.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 14));
		lblGoiName.setBounds(134, 123, 125, 14);
		add(lblGoiName);
		
		txtGOIName = new JTextField();
		txtGOIName.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		txtGOIName.setBounds(269, 119, 231, 23);
		add(txtGOIName);
		txtGOIName.setColumns(10);
		
		JLabel lblGoiSubject = new JLabel("GOI Subject");
		lblGoiSubject.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 14));
		lblGoiSubject.setBounds(134, 163, 125, 14);
		add(lblGoiSubject);
		
		txtGOISubject = new JTextField();
		txtGOISubject.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		txtGOISubject.setBounds(269, 159, 231, 23);
		add(txtGOISubject);
		txtGOISubject.setColumns(10);
		
		JLabel lblGoiDescription = new JLabel("GOI Description");
		lblGoiDescription.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 14));
		lblGoiDescription.setBounds(134, 205, 125, 14);
		add(lblGoiDescription);
		
		txtGOIDescription = new JTextField();
		txtGOIDescription.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		txtGOIDescription.setBounds(269, 201, 231, 23);
		add(txtGOIDescription);
		txtGOIDescription.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
		btnDone.setBounds(336, 264, 79, 25);
		add(btnDone);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
	}
}
