package guic;

import main.MyBoxGUI;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import controllers.FileOwnerController;
import controllers.UserController;
import entities.FileOwnerViewer;
import entities.FileToView;

public class FileOwnerPage extends MyBoxGUI
{
	private JButton btnHelp;
	private JButton btnsignout;

	private FileOwnerController temp= new FileOwnerController();
	static ArrayList<FileToView> filesToView = new ArrayList<>();
	static ArrayList<FileOwnerViewer> fileOwnerViewer = new ArrayList<>();
	
    public FileOwnerPage() 
    {
    	setLayout(null);
    	
    	JButton btnSearchgoi = new JButton("Search a GOI");
    	btnSearchgoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnSearchgoi.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			fileownerpage.setVisible(false);
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
    			fileownerpage.setVisible(false);
    			yourgois.setVisible(true);
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
						for(int i=1; i<msg.size();i++){
							filesToView.add((FileToView)msg.get(i)); 
						}
						
						for(int i=0;i<filesToView.size();i++){
							listSharedFlsModel.addElement(filesToView.get(i).getFileName());
						}
						
						fileownerpage.setVisible(false);
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
    			
    			fileownerpage.setVisible(false);
    			messages.setVisible(true);
    			
    			
    			if((boolean)msg.get(0)){
    				ArrayList<Messages> temp = new ArrayList<>();
    				for(int i=1;i<msg.size()+1;i++){
    					temp.add((Messages)msg.get(i));
        				ListModel.addElement(temp.get(i).toString());
        			}
    				/**Show message gif icon**/
    				lblNewMSG.setVisible(false);
    			}
    			
    			
    			
    			else
    			{
    				JOptionPane.showMessageDialog(frmMyBox,"No new messages","Message",JOptionPane.INFORMATION_MESSAGE);
    			}

    		}
    	});
    	btnMessages.setBounds(330, 300, 99, 36);
    	add(btnMessages);
    	
    	
    	JButton btnUploadAFile = new JButton("Upload a File");
    	btnUploadAFile.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnUploadAFile.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0) 
    		{
    			fileownerpage.setVisible(false);
    			uploadfilepage.setVisible(true);
    		}
    	});
    	btnUploadAFile.setBounds(315, 140, 123, 42);
    	add(btnUploadAFile);
    	
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
    			fileownerpage.setVisible(false);
    			folderspage.setVisible(true);
    		}
    	});
    	btnFolders.setBounds(534, 140, 123, 42);
    	add(btnFolders);
    	
    	JLabel lblGoi = new JLabel("GOI");
    	lblGoi.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
    	lblGoi.setBounds(136, 26, 89, 34);
    	add(lblGoi);
    	
    	JLabel lblFolders = new JLabel("Folders");
    	lblFolders.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
    	lblFolders.setBounds(534, 26, 123, 34);
    	add(lblFolders);
    	
    	JLabel lblFiles = new JLabel("Files");
    	lblFiles.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
    	lblFiles.setBounds(339, 27, 89, 33);
    	add(lblFiles);
    	
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
    	

       	btnHelp = new JButton("Help");
       	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Show all your options","Help",JOptionPane.INFORMATION_MESSAGE);
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
    	        	fileownerpage.setVisible(false);
        			loginpage.setVisible(true);
    	        }
    			
    		}
    	});
    	btnsignout.setBounds(595, 381, 99, 36);
    	add(btnsignout);
    	
    	lblNewMSG = new JLabel("");
    	lblNewMSG.setIcon(new ImageIcon(UserPage.class.getResource("/guic/NewMSG.gif")));
    	lblNewMSG.setBounds(422, 300, 31, 14);
    	add(lblNewMSG);
    	lblNewMSG.setVisible(false);

    	JButton btnMyFiles = new JButton("My Files");
    	btnMyFiles.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnMyFiles.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0) 
    		{
    			temp.myFiles();;
				fileOwnerViewer.clear();
				ArrayList<Object> msg = (ArrayList) MyBoxGUI.getClient().getMessage();
				
				for(int i=0;i<msg.size();i++){
					System.out.println(msg.get(i));
				}
				
				if((boolean)msg.get(0)==true){
					for(int i=1; i<msg.size();i++)
						fileOwnerViewer.add((FileOwnerViewer)msg.get(i)); 
					
					for(int i=0;i<fileOwnerViewer.size();i++){
						ListModel4.addElement(fileOwnerViewer.get(i).getFileName());
					}
					
					fileownerpage.setVisible(false);
					myfiles.setVisible(true);
				}
				else{
					String str = (String)msg.get(1);
					JOptionPane.showMessageDialog(frmMyBox,str);
				}
    			
    		}
    			
    			
    	});
    	btnMyFiles.setBounds(315, 193, 123, 42);
    	add(btnMyFiles);
    	
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
	}
    
    public static ArrayList<FileToView> getFilesToView(){
		return filesToView;
	}
    
    public static ArrayList<FileOwnerViewer> getFilesOwnerViewer(){
		return fileOwnerViewer;
	}
}
