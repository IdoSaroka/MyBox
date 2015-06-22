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

public class ShowSearchedGOI extends MyBoxGUI
{
	
	static ArrayList<GOI> gois;
	UserController temp=new UserController();
	static GOI goiToJoin;
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	
	public ShowSearchedGOI()
	{
		setLayout(null);
		
		JButton btnJoinGoi = new JButton(" Join");
		btnJoinGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnJoinGoi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int i = list2.getSelectedIndex();
				System.out.println(i + ": is the seleced index");
				gois=SearchGOIPage.getGOIS();
				goiToJoin=gois.get(i);
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
    	        	
    	        	try {
						temp.LeaveGOI(gois.get(i));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	        	ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
    	        	JOptionPane.showMessageDialog(frmMyBox, msg.get(0));
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
		Label lblNameGOI = new Label("Name Of GOI");
		lblNameGOI.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 14));
		scrollPane.setColumnHeaderView(lblNameGOI);
		
		
		
    	btnHelp = new JButton("Help");
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Show all you GOI youre in","Help",JOptionPane.INFORMATION_MESSAGE);
    		}
    	});
    	btnHelp.setBounds(81, 381, 99, 36);
    	add(btnHelp);

    	btnsignout = new JButton("Sign-Out");
    	btnsignout.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{	
    	        int reply = JOptionPane.showConfirmDialog(frmMyBox, "Are you sure?", "Signing out...", JOptionPane.YES_NO_OPTION);
    	        if (reply == JOptionPane.YES_OPTION) 
    	        {
    	        	byeBye();
    	        	if(user.getrole().equals("User"))
    	        		userpage.setVisible(false);
    	        	else if (user.getrole().equals("SystemAdmin"))
    	        		adminpage.setVisible(false);
    	        	else if (user.getrole().equals("FileOwner"))
    	        		fileownerpage.setVisible(false);
        			loginpage.setVisible(true);
    	        }
    			
    		}
    	});
    	btnsignout.setBounds(595, 381, 99, 36);
    	add(btnsignout);
    	
    	btnBack = new JButton("Back");
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			showsearchgoi.setVisible(false);
    			searchgoipage.setVisible(true);
    			
    		}
    	});
    	btnBack.setBounds(3, 2, 68, 23);
    	add(btnBack);
		
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
    	
	}
	
	protected static GOI getGoiToJoin(){
		return goiToJoin;
	}
}
