package guic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import main.MyBoxGUI;
import java.awt.Font;


public class AdminPage extends MyBoxGUI
{
    /*
     * Here this is our third Card of CardLayout, which will
     * be added to the contentPane object of JPanel, which
     * has the LayoutManager set to CardLayout.
     * This card consists of Two JLabels and two JCheckBox
     * with GridLayout.
     */  
    private ActionListener state;

    public AdminPage()
    {
    	setLayout(null);
    	
    	JButton btnSignOut = new JButton("Sign-Out");
    	btnSignOut.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			adminpage.setVisible(false);
    			loginpage.setVisible(true);
    		}
    	});
    	btnSignOut.setBounds(513, 378, 89, 36);
    	add(btnSignOut);
    	
    	JButton btnHelp = new JButton("Help");
    	btnHelp.setBounds(130, 378, 89, 23);
    	add(btnHelp);
    	
    	JLabel lblGoi = new JLabel("GOI");
    	lblGoi.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblGoi.setBounds(166, 26, 89, 34);
    	add(lblGoi);
    	
    	JButton btnPendingRequest = new JButton("Pending Request");
    	btnPendingRequest.setBounds(211, 71, 123, 42);
    	add(btnPendingRequest);
    	
    	JButton btnEditAGoi = new JButton("Edit a GOI");
    	btnEditAGoi.setBounds(68, 208, 123, 42);
    	add(btnEditAGoi);
    	
    	JButton btnCreateAGoi = new JButton("Create a GOI");
    	btnCreateAGoi.setBounds(68, 71, 123, 42);
    	add(btnCreateAGoi);
    	
    	JButton btnSearchForA = new JButton("Search for a GOI");
    	btnSearchForA.setBounds(68, 140, 122, 42);
    	add(btnSearchForA);
    	
    	JButton btnDeleteAGoi = new JButton("Delete a GOI");
    	btnDeleteAGoi.setBounds(211, 140, 123, 42);
    	add(btnDeleteAGoi);
    	
    	JLabel lblFiles = new JLabel("Files");
    	lblFiles.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblFiles.setBounds(420, 27, 89, 33);
    	add(lblFiles);
    	
    	JLabel lblFolders = new JLabel("Folders");
    	lblFolders.setFont(new Font("Arial", Font.PLAIN, 36));
    	lblFolders.setBounds(554, 26, 123, 34);
    	add(lblFolders);
    	
    	JButton btnSharedFiles = new JButton("Shared Files");
    	btnSharedFiles.setBounds(406, 81, 89, 23);
    	add(btnSharedFiles);
    	
    	JButton btnUploadAFile = new JButton("Upload a File");
    	btnUploadAFile.setBounds(406, 140, 89, 23);
    	add(btnUploadAFile);
    	
    	JButton btnMyFiles = new JButton("My Files");
    	btnMyFiles.setBounds(406, 194, 89, 23);
    	add(btnMyFiles);
    	
    	JButton btnFolders = new JButton("Folders");
    	btnFolders.setBounds(568, 194, 89, 23);
    	add(btnFolders);
    	
    	JButton btnCreateAFolder = new JButton("Create a Folder");
    	btnCreateAFolder.setBounds(552, 71, 123, 42);
    	add(btnCreateAFolder);
        init();
    }
////////////////////////start delete////////////////// 
    public void init()
    {

        state = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
              //  if (maleBox == (JCheckBox) ae.getSource())
                {
                                           
                }
            }
        };
////////////////////////end delete////////////////// 
    }
}