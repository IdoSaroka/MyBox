package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import entities.GOI;

import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;

public class ShowSearchedGOI extends MyBoxGUI
{
	
	static ArrayList<GOI> gois;
	
	public ShowSearchedGOI()
	{
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showsearchgoi.setVisible(false);
				searchgoipage.setVisible(true);
			}
		});
		btnBack.setBounds(10, 21, 89, 23);
		add(btnBack);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(81, 381, 99, 36);
		add(btnHelp);
		
		JButton btnSignout = new JButton("Sign-out");
		btnSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignout.setBounds(595, 381, 99, 36);
		add(btnSignout);
		
		JButton btnJoinGoi = new JButton(" Join");
		btnJoinGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnJoinGoi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				showsearchgoi.setVisible(false);
				joingoipage.setVisible(true);
			}
		});
		btnJoinGoi.setBounds(456, 104, 89, 37);
		add(btnJoinGoi);
		
		JButton btnRemoveYourself = new JButton(" Remove");
		btnRemoveYourself.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
    	        int reply = JOptionPane.showConfirmDialog(frmMyBox, "Are you sure?", "Delete From GOI...", JOptionPane.YES_NO_OPTION);
    	        if (reply == JOptionPane.YES_OPTION) 
    	        {
    	        	int i = list2.getSelectedIndex();
    	        	gois=SearchGOIPage.getGOIS();
    	        	System.out.println(i + ": is the seleced index");
    	        	ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
    	        	System.out.println(msg.get(0));
    	        	//JOptionPane.showConfirmDialog(frmMyBox, "Done", JOptionPane.OK_OPTION);
    	        }
			}
		});
		btnRemoveYourself.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnRemoveYourself.setBounds(547, 104, 89, 37);
		add(btnRemoveYourself);
		////////////////
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 104, 407, 169);
		add(scrollPane);
		
		list2 = new JList(ListModel2);
		scrollPane.setViewportView(list2);
		
		/////////////
		Label lblNameGOI = new Label("Name Of GOI");
		lblNameGOI.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 14));
		scrollPane.setColumnHeaderView(lblNameGOI);
		
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
    	
	}
}
