package guic;

import main.MyBoxGUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;

import controllers.GOIController;

public class SearchGOIPage extends MyBoxGUI 
{
	private JRadioButton rdbtnSerachBySubject;
	private JRadioButton rdbtnSerachByName;
	private JRadioButton rdbtnSearchAllGoi;
	private JTextField txtSearchName;
	private JTextField txtSerachSubject;
	
	private int isPressed = 0;
	private JButton btnSearch;
	private JButton btnHelp;
	private JButton btnSignOut;
	
	private String option;
	private String param;
	
	
	public SearchGOIPage(){
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				searchgoipage.setVisible(false);
				userpage.setVisible(true);
			}
		});
		btnBack.setBounds(10, 11, 79, 23);
		add(btnBack);
		
		JLabel lblSearchForA = new JLabel("Search For A New GOI");
		lblSearchForA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchForA.setBounds(292, 11, 198, 41);
		add(lblSearchForA);
		
		rdbtnSerachByName = new JRadioButton("Serach By Name");
		
		rdbtnSerachByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(isPressed == 1)
					isPressed = 0;
				else isPressed =1;
				rdbtnSerachBySubject.setSelected(false);
				rdbtnSearchAllGoi.setSelected(false);
				txtSearchName.setEnabled(true);
				txtSerachSubject.setText("");
				txtSerachSubject.setEnabled(false);
			}
		});
		rdbtnSerachByName.setBounds(28, 83, 141, 23);
		add(rdbtnSerachByName);
		
		rdbtnSerachBySubject = new JRadioButton("Serach By Subject");
		rdbtnSerachBySubject.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(isPressed == 2)
					isPressed = 0;
				else isPressed =2;
				rdbtnSerachByName.setSelected(false);
				rdbtnSearchAllGoi.setSelected(false);
				txtSerachSubject.setEnabled(true);
				txtSearchName.setText("");
				txtSearchName.setEnabled(false);
			}
		});
		rdbtnSerachBySubject.setBounds(28, 122, 141, 23);
		add(rdbtnSerachBySubject);
		
		rdbtnSearchAllGoi = new JRadioButton("Search All GOI");
		rdbtnSearchAllGoi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(isPressed == 3)
					isPressed = 0;
				else isPressed =3;
				rdbtnSerachByName.setSelected(false);
				rdbtnSerachBySubject.setSelected(false);
				txtSerachSubject.setText("");
				txtSerachSubject.setEnabled(false);
				txtSearchName.setText("");
				txtSearchName.setEnabled(false);
			}
		});
		rdbtnSearchAllGoi.setBounds(28, 161, 109, 23);
		add(rdbtnSearchAllGoi);
		
		txtSearchName = new JTextField();
		txtSearchName.setBounds(173, 85, 301, 23);
		txtSearchName.disable();
		add(txtSearchName);
		txtSearchName.setColumns(10);
		
		txtSerachSubject = new JTextField();
		txtSerachSubject.setBounds(173, 122, 301, 23);
		txtSerachSubject.disable();
		add(txtSerachSubject);
		txtSerachSubject.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				switch(isPressed)
				{
				case 0: 
					JOptionPane.showMessageDialog(frmMyBox,"Please chose an option to search","Error Message",JOptionPane.WARNING_MESSAGE);
					break;
				case 1: 
					JOptionPane.showMessageDialog(frmMyBox,"Searching for a name","Name",JOptionPane.INFORMATION_MESSAGE);
					option="Name";
					param=txtSearchName.getText();
					break;
				case 2: 
					JOptionPane.showMessageDialog(frmMyBox,"Searching for a subject","subject",JOptionPane.INFORMATION_MESSAGE);
					option="Subject";
					param=txtSerachSubject.getText();
					break;
				case 3: 
					JOptionPane.showMessageDialog(frmMyBox,"Seraching in all GOI","Entire GOI",JOptionPane.INFORMATION_MESSAGE);
					option="All";
					param=null;
					break;
			
				}
				if(isPressed!=0)
				{
					/*
					 * For Client Server Only!!
 
					 
					GOIController send = new GOIController();
					try {
						send.searchGOI(param, option);
					} catch (IOException e1) {
						System.out.println("Unable to send search terms in Search GOI Page");
						e1.printStackTrace();
					}
					//if there is no new msgs, you will get "No new messages"
	    			
	    			ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
	    			for(int i=0;i<msg.size();i++){
	    				ListModel2.addElement((String)msg.get(i));
	    			}
	    			*/
					searchgoipage.setVisible(false);
					showsearchgoi.setVisible(true);
				}
			}
		});
		btnSearch.setBounds(353, 193, 79, 23);
		add(btnSearch);
		
		btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(frmMyBox,"Help me","Help",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnHelp.setBounds(166, 378, 89, 36);
		add(btnHelp);
		
		btnSignOut = new JButton("Sign-Out");
		btnSignOut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frmMyBox,"ByeBye","signout",JOptionPane.INFORMATION_MESSAGE);
				searchgoipage.setVisible(false);
				loginpage.setVisible(true);
			}
		});
		btnSignOut.setBounds(513, 378, 89, 36);
		add(btnSignOut);
	}
}
