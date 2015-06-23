package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import controllers.UserController;
import entities.GOI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class JoinGOIPage extends MyBoxGUI
{
	private JTextField textField;
	UserController temp=new UserController();
	GOI goi=ShowSearchedGOI.getGoiToJoin();
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	public JoinGOIPage()
	{
		setLayout(null);
		
		JLabel lblAskToJoin = new JLabel("   Join a Group Of Interest");
		lblAskToJoin.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
		lblAskToJoin.setBounds(141, 11, 569, 50);
		add(lblAskToJoin);
		
		JLabel lblWhyWouldYou = new JLabel("Why Would You Like To Join");
		lblWhyWouldYou.setFont(new Font("Footlight MT Light", Font.PLAIN, 18));
		lblWhyWouldYou.setBounds(33, 89, 236, 41);
		add(lblWhyWouldYou);
		
		textField = new JTextField();
		textField.setBounds(279, 89, 389, 32);
		add(textField);
		textField.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String decription = textField.getText();
				try {
					goi=ShowSearchedGOI.getGoiToJoin();
					temp.requestToJoinGOI(goi, decription);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
	        	JOptionPane.showMessageDialog(frmMyBox, msg.get(0));
			}
		});
		btnDone.setBounds(579, 132, 89, 32);
		add(btnDone);
		
	   	btnHelp = new JButton("Help");
	   	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Choose GOI to Join of","Help",JOptionPane.INFORMATION_MESSAGE);
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
    	        	joingoipage.setVisible(false);
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
    			joingoipage.setVisible(false);
    			showsearchgoi.setVisible(true);
    			
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
