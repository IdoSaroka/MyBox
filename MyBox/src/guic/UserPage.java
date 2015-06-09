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
import javax.swing.ImageIcon;

public class UserPage extends MyBoxGUI
{

    public UserPage() 
    {
    	setLayout(null);
    	
    	JButton btnsignout = new JButton("Sign-Out");
    	btnsignout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Signing-out Successfully","Help",JOptionPane.YES_NO_CANCEL_OPTION);
    			userpage.setVisible(false);
    			loginpage.setVisible(true);
    			
    		}
    	});
    	btnsignout.setBounds(513, 378, 89, 36);
    	add(btnsignout);
    	JButton btnHelp = new JButton("Help");
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Here comes the help options","Help",JOptionPane.INFORMATION_MESSAGE);
    		}
    	});
    	btnHelp.setBounds(166, 378, 89, 36);
    	add(btnHelp);
    	
    	JButton btnJoingoi = new JButton("Join a GOI");
    	btnJoingoi.setBounds(211, 71, 123, 42);
    	add(btnJoingoi);
    	
    	JButton btnSearchgoi = new JButton("Search for a GOI");
    	btnSearchgoi.setBounds(68, 140, 122, 42);
    	add(btnSearchgoi);
    	
    	JButton btnLeaveAGoi = new JButton("Leave a GOI");
    	btnLeaveAGoi.setBounds(68, 71, 123, 42);
    	add(btnLeaveAGoi);
    	
    	JButton btnSharedFiles = new JButton("Shared Files");
    	btnSharedFiles.setBounds(403, 71, 123, 42);
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
    	btnUploadAFile.setBounds(403, 140, 123, 42);
    	add(btnUploadAFile);
    	
    	JButton btnCreateAFolder = new JButton("Create a Folder");
    	btnCreateAFolder.setBounds(552, 71, 123, 42);
    	add(btnCreateAFolder);
    	
    	JButton btnFolders = new JButton("Folders");
    	btnFolders.setBounds(552, 140, 123, 42);
    	add(btnFolders);
    	
    	JLabel lblGoi = new JLabel("GOI");
    	lblGoi.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblGoi.setBounds(166, 26, 89, 34);
    	add(lblGoi);
    	
    	JLabel lblFolders = new JLabel("Folders");
    	lblFolders.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblFolders.setBounds(554, 26, 123, 34);
    	add(lblFolders);
    	
    	JLabel lblFiles = new JLabel("Files");
    	lblFiles.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblFiles.setBounds(420, 27, 89, 33);
    	add(lblFiles);
    	
    	JLabel label = new JLabel(".");
    	label.setIcon(new ImageIcon("/guic/MyBox.jpg"));
    	label.setBounds(10, 11, 780, 478);
    	add(label);
    }

}
