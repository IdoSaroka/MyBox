package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import controllers.SysAdminController;
import entities.GOI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class CreateGOIPage extends MyBoxGUI
{
	private JTextField txtGOIName;
	private JTextField txtGOISubject;
	private JTextField txtGOIDescription;
	
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	public CreateGOIPage()
	{
		setLayout(null);
		
		JLabel lblCreateNewGoi = new JLabel("Create New GOI");
		lblCreateNewGoi.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
		lblCreateNewGoi.setBounds(221, 11, 314, 43);
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
		
		JLabel lblGoiDescription = new JLabel("Maximum users:");
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
		btnDone.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			GOI temp=new GOI(txtGOIName.getText(),txtGOISubject.getText(),Integer.parseInt(txtGOIDescription.getText()));
        		SysAdminController sys=new SysAdminController();
        		try {
					sys.addGOI(temp);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		ArrayList<Object> msg =  (ArrayList) MyBoxGUI.getClient().getMessage();
    	        JOptionPane.showMessageDialog(frmMyBox, (String)msg.get(0));
    	        
    			creategoipage.setVisible(false);
        		adminpage.setVisible(true);
    	    }
    	});
		btnDone.setBounds(336, 264, 79, 25);
		add(btnDone);
		
	   	btnHelp = new JButton("Help");
	   	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Here comes the help options","Help",JOptionPane.INFORMATION_MESSAGE);
    		}
    	});
    	btnHelp.setBounds(81, 381, 99, 36);
    	add(btnHelp);

    	btnsignout = new JButton("Sign-Out");
    	btnsignout.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnsignout.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{	
    	        int reply = JOptionPane.showConfirmDialog(frmMyBox, "Are you sure?", "Signing out...", JOptionPane.YES_NO_OPTION);
    	        if (reply == JOptionPane.YES_OPTION) 
    	        {
    	        	byeBye();
        			creategoipage.setVisible(false);
        			loginpage.setVisible(true);
    	        }
    			
    		}
    	});
    	btnsignout.setBounds(595, 381, 99, 36);
    	add(btnsignout);
    	
    	btnBack = new JButton("Back");
    	btnBack.setFont(new Font("Footlight MT Light", Font.PLAIN, 11));
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			creategoipage.setVisible(false);
        		adminpage.setVisible(true);
    		}
    	});
    	btnBack.setBounds(3, 2, 68, 23);
    	add(btnBack);
		
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
	}
}
