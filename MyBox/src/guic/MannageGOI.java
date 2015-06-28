package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import controllers.SysAdminController;
import controllers.UserController;
import entities.GOI;
import entities.Request;
import entities.User;

import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

public class MannageGOI extends MyBoxGUI
{

	static GOI selectedGOI;
	static ArrayList<GOI> gois;
	static ArrayList<String> userInGOI;
	
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	
	public MannageGOI()
	{
		setLayout(null);
		
		JButton btnJoinGoi = new JButton("Users in GOI");
		btnJoinGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnJoinGoi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				SysAdminController moshe = new SysAdminController();
				int i = list2.getSelectedIndex();
				System.out.println(i + ": is the seleced index");
				gois=SearchGOIPage.getGOIS();
				selectedGOI=gois.get(i);
				
				try {
					moshe.UsersInGOI(selectedGOI);
					ArrayList<Object> msg = (ArrayList)MyBoxGUI.getClient().getMessage();
					int size=msg.size();
					ListModel4.clear();
					for(int j=0; j<size; j++)
	    				System.out.println("msg "+j + ": " + msg.get(j));
					userInGOI = new ArrayList<>();
	    			if((boolean)msg.get(0)==true){
	    				for(int j=1;j<size;j++)
	    					userInGOI.add((String)msg.get(j));
	    				
	    				ListModelRequest.clear();
	    				for(int j=0;j<userInGOI.size();j++)
	    					ListModel4.addElement(userInGOI.get(j));
		    			
	    				managegoi.setVisible(false);
	    				usersingoi.setVisible(true);

	    			}
	    			else
	    				JOptionPane.showMessageDialog(frmMyBox, msg.get(1));
	    			
				} catch (IOException e1) {
					System.out.println("Catch in mannage goi - users in goi");
					e1.printStackTrace();
				}
				

			}
		});
		btnJoinGoi.setBounds(456, 104, 116, 37);
		add(btnJoinGoi);
		
		JButton btnRemoveYourself = new JButton("Delete GOI");
		btnRemoveYourself.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SysAdminController moshe = new SysAdminController();
				int i = list2.getSelectedIndex();
				System.out.println(i + ": is the seleced index");
				gois=SearchGOIPage.getGOIS();
				selectedGOI=gois.get(i);
				
    	        int reply = JOptionPane.showConfirmDialog(frmMyBox, "Are you sure?", "Delete From GOI...", JOptionPane.YES_NO_OPTION);
    	        if (reply == JOptionPane.YES_OPTION) 
    	        {
    	        	try {
						moshe.removeGOI(selectedGOI);
						
						ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
	    	        	JOptionPane.showMessageDialog(frmMyBox, msg.get(1));
						/*
						 * in get(0) you have true or false 
						 * in get(1) you got the string to show
						 */
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	        	
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
		
		list2 = new JList(ListModel2);
		scrollPane.setViewportView(list2);
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
    	        	managegoi.setVisible(false);
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
	
	public static ArrayList<String> getUserInGOI(){
		return userInGOI;
	}
	
	public static GOI getSelectedGOI(){
		return selectedGOI;
	}
	
}
