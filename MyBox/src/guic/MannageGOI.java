package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import controllers.UserController;
import entities.GOI;

import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

public class MannageGOI extends MyBoxGUI
{
	
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	
	public MannageGOI()
	{
		setLayout(null);
		
		JButton btnJoinGoi = new JButton("User in GOI");
		btnJoinGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnJoinGoi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{

			}
		});
		btnJoinGoi.setBounds(456, 104, 116, 37);
		add(btnJoinGoi);
		
		JButton btnRemoveYourself = new JButton("Delete GOI");
		btnRemoveYourself.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
    	        int reply = JOptionPane.showConfirmDialog(frmMyBox, "Are you sure?", "Delete From GOI...", JOptionPane.YES_NO_OPTION);
    	        if (reply == JOptionPane.YES_OPTION) 
    	        {
    	        	//Here you can use a Yes No Window for question before a delete you can erase it if not necessary 
    	        }
			}
		});
		btnRemoveYourself.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnRemoveYourself.setBounds(582, 104, 112, 37);
		add(btnRemoveYourself);
		////////////////
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 104, 407, 169);
		add(scrollPane);
		
		listGoi = new JList(ListModelGoi);
		scrollPane.setViewportView(listGoi);
		Label lblNameGOI = new Label("Group of interest");
		lblNameGOI.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 14));
		scrollPane.setColumnHeaderView(lblNameGOI);
		
		
		
    	btnHelp = new JButton("Help");
    	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Show all you GOI youre in","Help",JOptionPane.INFORMATION_MESSAGE);
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
    	        	showsearchgoi.setVisible(false);
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
