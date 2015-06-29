package guic;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import controllers.UserController;
import entities.FileToView;
import entities.GOI;
import main.MyBoxGUI;

public class YourGOIs extends MyBoxGUI
{
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	static ArrayList<GOI> gois;
	static GOI selectedGoi;
	
	public YourGOIs()
	{
		setLayout(null);
		
		
		JButton btnJoinGoi = new JButton(" Files in GOI");
		btnJoinGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnJoinGoi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//added by ido - shimon continue 
				int i = list.getSelectedIndex();
				System.out.println(i+" is selected");
				gois=new ArrayList<>();
				if(MyBoxGUI.getUser().getrole().equals("User"))
					gois=UserPage.getGOIs();				
				else
					gois=FileOwnerPage.getGOIs();
				selectedGoi=gois.get(i);
				UserController moshe = new UserController();
				try {
					moshe.serachSharedFiles("Group",selectedGoi.getName());
				} catch (IOException e1) {
					System.out.println("Unable to send search from yourGOIs");
					e1.printStackTrace();
				}
				ArrayList<FileToView> filesToView=new ArrayList<>();
				filesToView.clear();
				ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
				
				int size=msg.size();
				
				System.out.println("msg size="+size);
				
				for(int i1=0; i1<size;i1++){
					System.out.println(msg.get(i1)); 
				}
				
				if((boolean)msg.get(0)==true){
					listSharedFlsModel.clear();
					for(int i1=1; i1<size;i1++){
						filesToView.add((FileToView)msg.get(i1)); 
					}
					for(int i1=0;i1<filesToView.size();i1++){
						listSharedFlsModel.addElement(filesToView.get(i1).getFileName());
					}
					
					yourgois.setVisible(false);
	    			sharedfilesrspage.setVisible(true);
				}
				else{
					String str = (String)msg.get(1);
					JOptionPane.showMessageDialog(frmMyBox,str);
				}
				
			}
		});
		btnJoinGoi.setBounds(456, 104, 123, 37);
		add(btnJoinGoi);
		
		
		JButton btnRemoveYourself = new JButton("Remove");
		btnRemoveYourself.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//added by ido - shimon continue 
				int i = list.getSelectedIndex();
				gois=new ArrayList<>();
				if(MyBoxGUI.getUser().getrole().equals("User")){
					gois=UserPage.getGOIs();
				}
				else
					gois=FileOwnerPage.getGOIs();
				selectedGoi=gois.get(i);
				UserController moshe = new UserController();
				try {
					moshe.LeaveGOI(selectedGoi);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
				String str = (String)msg.get(0);
				JOptionPane.showMessageDialog(frmMyBox,str);
			}
		});
		btnRemoveYourself.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnRemoveYourself.setBounds(589, 104, 117, 37);
		add(btnRemoveYourself);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 104, 407, 169);
		add(scrollPane);
		
		list = new JList(ListModel);
		scrollPane.setViewportView(list);
		Label lblNameGOI = new Label("Name Of GOI");
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
    	        	yourgois.setVisible(false);
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
    			yourgois.setVisible(false);
    			if(MyBoxGUI.getUser().getrole().equals("User")){
					userpage.setVisible(true);
				}
				else
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
}
