package guic;

import main.MyBoxGUI;

import javax.swing.ImageIcon;
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
import entities.GOI;

public class SearchGOIPage extends MyBoxGUI 
{
	private JRadioButton rdbtnSerachBySubject;
	private JRadioButton rdbtnSerachByName;
	private JRadioButton rdbtnSearchAllGoi;
	private JTextField txtSearchName;
	private JTextField txtSerachSubject;
	
	private int isPressed = 0;
	private JButton btnSearch;
	
	private String option;
	private String param;
	
	static ArrayList<GOI> gois;
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	
	public SearchGOIPage(){
		setLayout(null);
		
		JLabel lblSearchForA = new JLabel("Search For A New GOI");
		lblSearchForA.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 28));
		lblSearchForA.setBounds(219, 11, 349, 41);
		add(lblSearchForA);
		
		rdbtnSerachByName = new JRadioButton("Search By Name");
		rdbtnSerachByName.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		
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
		rdbtnSerachByName.setBounds(10, 83, 133, 23);
		add(rdbtnSerachByName);
		
		rdbtnSerachBySubject = new JRadioButton("Search By Subject");
		rdbtnSerachBySubject.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
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
		rdbtnSerachBySubject.setBounds(10, 122, 133, 23);
		add(rdbtnSerachBySubject);
		
		rdbtnSearchAllGoi = new JRadioButton("Search All GOI");
		rdbtnSearchAllGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
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
		rdbtnSearchAllGoi.setBounds(10, 161, 133, 23);
		add(rdbtnSearchAllGoi);
		
		txtSearchName = new JTextField();
		txtSearchName.setBounds(195, 83, 301, 23);
		txtSearchName.disable();
		add(txtSearchName);
		txtSearchName.setColumns(10);
		
		txtSerachSubject = new JTextField();
		txtSerachSubject.setBounds(195, 120, 301, 23);
		txtSerachSubject.disable();
		add(txtSerachSubject);
		txtSerachSubject.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
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
				//	JOptionPane.showMessageDialog(frmMyBox,"Searching for a name","Name",JOptionPane.INFORMATION_MESSAGE);
					option="Name";
					param=txtSearchName.getText();
					break;
				case 2: 
				//	JOptionPane.showMessageDialog(frmMyBox,"Searching for a subject","subject",JOptionPane.INFORMATION_MESSAGE);
					option="Subject";
					param=txtSerachSubject.getText();
					break;
				case 3: 
				//	JOptionPane.showMessageDialog(frmMyBox,"Seraching in all GOI","Entire GOI",JOptionPane.INFORMATION_MESSAGE);
					option="All";
					param=null;
					break;
			
				}
				if(isPressed!=0){
					GOIController send = new GOIController();
					try {
						send.searchGOI(param, option);
					} catch (IOException e1) {
						System.out.println("Unable to send search terms in Search GOI Page");
						e1.printStackTrace();
					}
					
	    			ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
	    			gois = new ArrayList<>();
	    			
	    			int size=msg.size();
	    			
	    			System.out.println("Returned value Value: " + msg.get(0));
	    			System.out.println("Returned GOI is: " + ((GOI)msg.get(1)).getName());
	    			
	    			if((boolean)msg.get(0)==true){
	    				for(int i=1;i<size;i++){
	    					gois.add((GOI)msg.get(i));
	    				}
	    				ListModel2.clear();
	    				for(int i=0;i<size-1;i++){
		    				ListModel2.addElement(gois.get(i).getName());
		    			}
	    				searchgoipage.setVisible(false);
	    				if(! ( MyBoxGUI.getUser().getrole().equals("SystemAdmin") ) )
	    					showsearchgoi.setVisible(true);
	    				else
	    					managegoi.setVisible(true);
	    			}
	    			else{
	    				JOptionPane.showMessageDialog(frmMyBox,(String)msg.get(1));
	    			}
					
				}
			}
		});
		btnSearch.setBounds(417, 160, 79, 23);
		add(btnSearch);
		
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
    	        	searchgoipage.setVisible(false);
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
    			searchgoipage.setVisible(false);
    			if(user.getrole().equals("User"))
    				userpage.setVisible(true);
    			else if (user.getrole().equals("SystemAdmin"))
    				adminpage.setVisible(true);
    			else if (user.getrole().equals("FileOwner"))
    				fileownerpage.setVisible(true);
    			
    		}
    	});
    	btnBack.setBounds(3, 2, 68, 23);
    	add(btnBack);
		
		
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
    	
	}
	
	protected static ArrayList<GOI> getGOIS(){
		return gois;
	}
}
