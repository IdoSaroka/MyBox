package guic;

import main.MyBoxGUI;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import controllers.UserController;
import entities.FileToView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

public class SharedFilessPage extends MyBoxGUI
{
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	private ArrayList<FileToView> filesToView = new ArrayList<>();
	
	public SharedFilessPage()
	{
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 87, 663, 235);
		add(scrollPane);
		
		listSharedFls = new JList(listSharedFlsModel);
		scrollPane.setViewportView(listSharedFls);
		
		JLabel lblNewLabel = new JLabel("File");
		lblNewLabel.setFont(new Font("Footlight MT Light", Font.PLAIN, 18));
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int i = listSharedFls.getSelectedIndex();
				
				if(MyBoxGUI.getUser().getrole().equals("User"))
					filesToView=UserPage.getFilesToView();
				else
					filesToView=FileOwnerPage.getFilesToView();
				FileToView fileToOpen = filesToView.get(i);
				
				System.out.println(fileToOpen.getFileName()+"."+fileToOpen.getFileSuffix());
				
				UserController moshe = new UserController();
				try {
					moshe.openSharedFile(fileToOpen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOpenFile.setBounds(285, 22, 200, 50);
		add(btnOpenFile);
		/*
		JButton btnRemoveFile = new JButton("Remove File");
		btnRemoveFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				listSharedFlsModel.removeElementAt(listSharedFls.getSelectedIndex());
			}
		});
		btnRemoveFile.setBounds(521, 22, 200, 50);
		add(btnRemoveFile);
		
		JButton btnAddFile = new JButton("Add File");
		btnAddFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				listSharedFlsModel.addElement("This Objet Type - mean any kind of type you want to show here");
			}
		});
		btnAddFile.setBounds(285, 22, 200, 50);
		add(btnAddFile);
		*/
		
	   	btnHelp = new JButton("Help");
	   	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"All Shared Files you share of","Help",JOptionPane.INFORMATION_MESSAGE);
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
    	        	sharedfilesrspage.setVisible(false);
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
    			sharedfilesrspage.setVisible(false);
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
}
