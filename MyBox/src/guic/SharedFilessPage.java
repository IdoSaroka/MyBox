package guic;

import main.MyBoxGUI;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SharedFilessPage extends MyBoxGUI
{
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	public SharedFilessPage()
	{
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 87, 663, 235);
		add(scrollPane);
		
		listSharedFls = new JList(listSharedFlsModel);
		scrollPane.setViewportView(listSharedFls);
		
		JLabel lblNewLabel = new JLabel("File");
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frmMyBox,"Open message: "+listSharedFlsModel.getElementAt(listSharedFls.getSelectedIndex()),null, JOptionPane.YES_NO_CANCEL_OPTION);
			}
		});
		btnOpenFile.setBounds(58, 22, 200, 50);
		add(btnOpenFile);
		
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
		
		
	   	btnHelp = new JButton("Help");
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Here comes the help options","Help",JOptionPane.INFORMATION_MESSAGE);
    		}
    	});
    	btnHelp.setBounds(81, 381, 99, 36);
    	add(btnHelp);

    	btnsignout = new JButton("Sign-Out");
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
    	
    	btnBack = new JButton("Back");
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			uploadfilepage.setVisible(false);
    			userpage.setVisible(true);
    			
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
