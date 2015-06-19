package guic;

import main.MyBoxGUI;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowSearchedGOI extends MyBoxGUI
{
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
		btnHelp.setBounds(154, 290, 89, 23);
		add(btnHelp);
		
		JButton btnSignout = new JButton("Sign-out");
		btnSignout.setBounds(569, 290, 89, 23);
		add(btnSignout);
		
		JButton btnJoinGoi = new JButton("Join GOI");
		btnJoinGoi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				showsearchgoi.setVisible(false);
				joingoipage.setVisible(true);
			}
		});
		btnJoinGoi.setBounds(513, 137, 89, 37);
		add(btnJoinGoi);
		
		JButton btnRemoveYourself = new JButton("Remove Yourself");
		btnRemoveYourself.setBounds(612, 137, 113, 37);
		add(btnRemoveYourself);
		////////////////
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 104, 407, 169);
		add(scrollPane);
		
		list2 = new JList(ListModel2);
		scrollPane.setViewportView(list2);
		
		/////////////
		Label lblNameGOI = new Label("Name Of GOI");
		scrollPane.setColumnHeaderView(lblNameGOI);
	}
}
