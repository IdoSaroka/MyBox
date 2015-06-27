package guic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import main.MyBoxGUI;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import controllers.FileOwnerController;
import controllers.GOIController;
import controllers.UserController;
import entities.FileToView;
import entities.GOI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class UserPage extends MyBoxGUI
{
	//gois - added by ido
	static ArrayList<GOI> gois;
	static ArrayList<String> filesAndFolders;
	
	UserController temp= new UserController();
	static ArrayList<FileToView> filesToView = new ArrayList<>();

    public UserPage() 
    {
    	setLayout(null);
    	
    	JButton btnsignout = new JButton("Sign-Out");
    	btnsignout.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnsignout.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{	
    	        int reply = JOptionPane.showConfirmDialog(frmMyBox, "Are you sure?", "Signing out...", JOptionPane.YES_NO_OPTION);
    	        if (reply == JOptionPane.YES_OPTION) 
    	        {
    	        	byeBye();
        			userpage.setVisible(false);
        			loginpage.setVisible(true);
    	        }
    			
    		}
    	});
    	btnsignout.setBounds(595, 381, 99, 36);
    	add(btnsignout);
    	JButton btnHelp = new JButton("Help");
    	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"User options","Help",JOptionPane.INFORMATION_MESSAGE);
    		}
    	});
    	btnHelp.setBounds(81, 381, 99, 36);
    	add(btnHelp);
    	
    	JButton btnSearchgoi = new JButton("Search a GOI");
    	btnSearchgoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
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
    	btnYourGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnYourGoi.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			UserController send = new UserController();
				try {
					send.getYourGOI();
				} catch (IOException e1) {
					System.out.println("Unable to send search terms in Search GOI Page");
					e1.printStackTrace();
				}
				
    			ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
    			gois = new ArrayList<>();
    			
    			int size=msg.size();
    			
    			System.out.println("Returned value Value: " + msg.get(0));
    			//System.out.println("Returned GOI is: " + ((GOI)msg.get(1)).getName());
    			
    			if((boolean)msg.get(0)==true){
    				for(int i=1;i<size;i++){
    					gois.add((GOI)msg.get(i));
    				}
    				ListModel.clear();
    				for(int i=0;i<size-1;i++){
	    				ListModel.addElement(gois.get(i).getName());
	    			}
    				userpage.setVisible(false);
        			yourgois.setVisible(true);
    			}
    			else{
    				JOptionPane.showMessageDialog(frmMyBox,(String)msg.get(1));
    			}
    			
    		}
    	});
    	btnYourGoi.setBounds(114, 87, 123, 42);
    	add(btnYourGoi);
    	
    	JButton btnSharedFiles = new JButton("Shared Files");
    	btnSharedFiles.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnSharedFiles.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0) 
    		{
    			try {
					temp.serachSharedFiles("AllFiles", null);
					filesToView.clear();
					ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
					if((boolean)msg.get(0)==true){
						listSharedFlsModel.clear();
						for(int i=1; i<msg.size();i++){
							filesToView.add((FileToView)msg.get(i)); 
						}
						for(int i=0;i<filesToView.size();i++){
							listSharedFlsModel.addElement(filesToView.get(i).getFileName());
						}
						
						userpage.setVisible(false);
		    			sharedfilesrspage.setVisible(true);
					}
					else{
						String str = (String)msg.get(1);
						JOptionPane.showMessageDialog(frmMyBox,str);
					}	
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
    	});
    	btnSharedFiles.setBounds(315, 87, 123, 42);
    	add(btnSharedFiles);
    	
    	JButton btnUploadAFile = new JButton("Upload a File");
    	btnUploadAFile.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
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
    	/*
    	JButton btnCreateAFolder = new JButton("Create a Folder");
    	btnCreateAFolder.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnCreateAFolder.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			
    		}
    	});
    	btnCreateAFolder.setBounds(534, 87, 123, 42);
    	add(btnCreateAFolder);
    	
    	JButton btnFolders = new JButton("Folders");
    	btnFolders.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnFolders.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			
    			UserController moshe = new UserController();
    			try {
					moshe.getFolders();
					
					/*
					 * Sending
					 * "Folder"
					 * "DisplayCurrentDirectories"
					 * User    entity of user
					 
					ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
					int size=msg.size();
					filesAndFolders.clear();
					for(int i=0; i<size ; i++)
						filesAndFolders.add((String)msg.get(i));
					
					/*
					 * For Ido And Ran to continue
					 * 
					 * Inside this filesAndFolders list you will be able to view all of the
					 * string the server sent
					 * 
					 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			userpage.setVisible(false);
    			folderspage.setVisible(true);
    		}
    	});
    	btnFolders.setBounds(534, 140, 123, 42);
    	add(btnFolders);
    	*/
    	JLabel lblGoi = new JLabel("GOI");
    	lblGoi.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
    	lblGoi.setBounds(136, 26, 89, 34);
    	add(lblGoi);
    	/*
    	JLabel lblFolders = new JLabel("Folders");
    	lblFolders.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
    	lblFolders.setBounds(534, 26, 123, 34);
    	add(lblFolders);
    	*/
    	JLabel lblFiles = new JLabel("Files");
    	lblFiles.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
    	lblFiles.setBounds(339, 27, 89, 33);
    	add(lblFiles);
    	
    	JButton btnMessages = new JButton("Messages");
    	btnMessages.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnMessages.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{

    			try {
					temp.getMSG();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			//if there is no new msgs, you will get "No new messages"
    			
    			ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
    			
    			if((boolean)msg.get(0)==true){
    				ListModel.clear();
    				ArrayList<entities.Messages> temp = new ArrayList<>();
    				int size=msg.size();
    				for(int i=1;i<size;i++)
    					temp.add((entities.Messages)msg.get(i));
        			
    				for(int i=0;i<temp.size();i++)
        				ListModel.addElement(temp.get(i).toString());
    				userpage.setVisible(false);
        			messages.setVisible(true);
    				/**Show message gif icon**/
    				lblNewMSG.setVisible(false);
    			}
    			else{
    				JOptionPane.showMessageDialog(frmMyBox,"No new messages","Message",JOptionPane.INFORMATION_MESSAGE);
    			}

    		}
    	});
    	btnMessages.setBounds(330, 300, 99, 36);
    	add(btnMessages);
    	
    	JSeparator separator = new JSeparator();
    	separator.setBounds(74, 63, 621, 26);
    	add(separator);
    	
    	JSeparator separator_1 = new JSeparator();
    	separator_1.setOrientation(SwingConstants.VERTICAL);
    	separator_1.setBounds(265, 11, 17, 285);
    	add(separator_1);
    	
    	JSeparator separator_2 = new JSeparator();
    	separator_2.setOrientation(SwingConstants.VERTICAL);
    	separator_2.setBounds(74, 11, 17, 285);
    	add(separator_2);
    	
    	JSeparator separator_3 = new JSeparator();
    	separator_3.setOrientation(SwingConstants.VERTICAL);
    	separator_3.setBounds(483, 11, 17, 285);
    	add(separator_3);
    	
    	JSeparator separator_4 = new JSeparator();
    	separator_4.setOrientation(SwingConstants.VERTICAL);
    	separator_4.setBounds(694, 11, 17, 285);
    	add(separator_4);
    	
    	JSeparator separator_5 = new JSeparator();
    	separator_5.setBounds(74, 296, 621, 26);
    	add(separator_5);
    	
    	JSeparator separator_6 = new JSeparator();
    	separator_6.setBounds(74, 11, 621, 26);
    	add(separator_6);
    	
    	lblNewMSG = new JLabel("");
    	lblNewMSG.setIcon(new ImageIcon(UserPage.class.getResource("/guic/NewMSG.gif")));
    	lblNewMSG.setBounds(422, 300, 31, 14);
    	add(lblNewMSG);
    	lblNewMSG.setVisible(false);
    	
    	
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
    	
    }
    public static ArrayList<FileToView> getFilesToView(){
		return filesToView;
	}
	public static ArrayList<GOI> getGOIs() {
		// TODO Auto-generated method stub
		return gois;
	}
	public static ArrayList<String> getFilesAndFoldersList() {
		// TODO Auto-generated method stub
		return filesAndFolders;
	}
}
