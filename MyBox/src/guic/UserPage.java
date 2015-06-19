package guic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import main.MyBoxGUI;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserPage extends MyBoxGUI
{

    public UserPage() 
    {
    	setLayout(null);
    	
    	JButton btnsignout = new JButton("Sign-Out");
    	btnsignout.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    	        int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Signing out...", JOptionPane.YES_NO_OPTION);
    	        if (reply == JOptionPane.YES_OPTION) 
    	        {
    	        	byeBye();
        			userpage.setVisible(false);
        			loginpage.setVisible(true);
    	        }
    			
    		}
    	});
    	btnsignout.setBounds(503, 378, 99, 36);
    	add(btnsignout);
    	JButton btnHelp = new JButton("Help");
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Here comes the help options","Help",JOptionPane.INFORMATION_MESSAGE);
    		}
    	});
    	btnHelp.setBounds(166, 378, 99, 36);
    	add(btnHelp);
    	
    	JButton btnSearchgoi = new JButton("Search a GOI");
    	btnSearchgoi.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			userpage.setVisible(false);
    			searchgoipage.setVisible(true);
    		}
    	});
    	btnSearchgoi.setBounds(115, 140, 122, 42);
    	add(btnSearchgoi);
    	
    	JButton btnYourGoi = new JButton("Your GOIs");
    	btnYourGoi.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    		}
    	});
    	btnYourGoi.setBounds(114, 87, 123, 42);
    	add(btnYourGoi);
    	
    	JButton btnSharedFiles = new JButton("Shared Files");
    	btnSharedFiles.setBounds(315, 87, 123, 42);
    	add(btnSharedFiles);
    	
    	JButton btnUploadAFile = new JButton("Upload a File");
    	btnUploadAFile.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0) 
    		{
    			userpage.setVisible(false);
    			uploadfilepage.setVisible(true);
    			
    		}
    	});
    	btnUploadAFile.setBounds(315, 140, 123, 42);
    	add(btnUploadAFile);
    	
    	JButton btnCreateAFolder = new JButton("Create a Folder");
    	btnCreateAFolder.setBounds(534, 87, 123, 42);
    	add(btnCreateAFolder);
    	
    	JButton btnFolders = new JButton("Folders");
    	btnFolders.setBounds(534, 140, 123, 42);
    	add(btnFolders);
    	
    	JLabel lblGoi = new JLabel("GOI");
    	lblGoi.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblGoi.setBounds(136, 26, 89, 34);
    	add(lblGoi);
    	
    	JLabel lblFolders = new JLabel("Folders");
    	lblFolders.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblFolders.setBounds(534, 26, 123, 34);
    	add(lblFolders);
    	
    	JLabel lblFiles = new JLabel("Files");
    	lblFiles.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblFiles.setBounds(339, 27, 89, 33);
    	add(lblFiles);
    	
    	JButton btnMessages = new JButton("Messages");
    	btnMessages.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			/*
    			 * 
    			 * This is meant for client server work
    			try {
					MyBoxGUI.getClient().sendToServer("Messages");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			//if there is no new msgs, you will get "No new messages"
    			
    			ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
    			for(int i=0;i<msg.size();i++){
    				ListModel.addElement((String)msg.get(i));
    			}
    			*/
    			userpage.setVisible(false);
    			messages.setVisible(true);
    		}
    	});
    	btnMessages.setBounds(339, 378, 99, 36);
    	add(btnMessages);
    	
    	JLabel lblNewMessage = new JLabel("");
    	lblNewMessage.setIcon(new ImageIcon("C:\\Users\\Ran\\Desktop\\NewMessage.gif"));
    	lblNewMessage.setBounds(360, 356, 55, 23);
    	add(lblNewMessage);
    	
    }
}
