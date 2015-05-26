package GUIs;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SystemAdmin extends JPanel 
{

	/**
	 * Create the panel.
	 */
	public SystemAdmin() 
	{
		setBounds(100, 100, 800, 500);
		setLayout(null);
		
		JLabel lblGoi = new JLabel("GOI");
		lblGoi.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblGoi.setBounds(177, 21, 101, 57);
		add(lblGoi);
		
		JLabel lblFiles = new JLabel("Files");
		lblFiles.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblFiles.setBounds(464, 27, 129, 44);
		add(lblFiles);
		
		JLabel lblFolders = new JLabel("Folders");
		lblFolders.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblFolders.setBounds(631, 27, 139, 44);
		add(lblFolders);
		
		JButton btnPendingRequest = new JButton("Pending Request");
		btnPendingRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPendingRequest.setBounds(46, 89, 155, 50);
		add(btnPendingRequest);
		
		JButton btnEditGoi = new JButton("Edit GOI");
		btnEditGoi.setBounds(223, 89, 155, 50);
		add(btnEditGoi);
		
		JButton btnCreateGoi = new JButton("Create GOI");
		btnCreateGoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateGoi.setBounds(46, 148, 155, 44);
		add(btnCreateGoi);
		
		JButton btnSerchForGoi = new JButton("Serch for GOI");
		btnSerchForGoi.setBounds(223, 148, 155, 44);
		add(btnSerchForGoi);
		
		JButton btnDeleteGoi = new JButton("Delete GOI");
		btnDeleteGoi.setBounds(46, 203, 155, 44);
		add(btnDeleteGoi);
		
		JButton btnSharedFiles = new JButton("Shared Files");
		btnSharedFiles.setBounds(420, 91, 155, 47);
		add(btnSharedFiles);
		
		JButton btnMyFiles = new JButton("My Files");
		btnMyFiles.setBounds(420, 147, 155, 47);
		add(btnMyFiles);
		
		JButton btnUploadAFile = new JButton("Upload a File");
		btnUploadAFile.setBounds(420, 202, 155, 47);
		add(btnUploadAFile);
		
		JButton btnCreateFolder = new JButton("Create Folder");
		btnCreateFolder.setBounds(605, 91, 155, 47);
		add(btnCreateFolder);
		
		JButton btnFolders = new JButton("Folders");
		btnFolders.setBounds(605, 147, 155, 47);
		add(btnFolders);
		
		JButton btnHelpMe = new JButton("Help Me");
		btnHelpMe.setBounds(192, 422, 155, 47);
		add(btnHelpMe);
		
		JButton btnSignout = new JButton("Sign-Out");
		btnSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignout.setBounds(457, 422, 155, 47);
		add(btnSignout);

	}
}
