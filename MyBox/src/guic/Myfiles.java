package guic;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import main.MyBoxGUI;

public class Myfiles extends MyBoxGUI
{
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	
	public Myfiles()
	{
		setLayout(null);
		
		JButton btnRemoveYourself = new JButton("Remove");
		btnRemoveYourself.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

    	        
			}
		});
		btnRemoveYourself.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		btnRemoveYourself.setBounds(456, 103, 92, 37);
		add(btnRemoveYourself);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 104, 407, 169);
		add(scrollPane);

		Label lblNameGOI = new Label("File Name");
		lblNameGOI.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 14));
		scrollPane.setColumnHeaderView(lblNameGOI);
		
		
    	btnHelp = new JButton("Help");
    	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Show all of your Files youre in","Help",JOptionPane.INFORMATION_MESSAGE);
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
    	        	myfiles.setVisible(false);
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
    			myfiles.setVisible(false);
    			fileownerpage.setVisible(true);
    			
    		}
    	});
    	btnBack.setBounds(3, 2, 68, 23);
    	add(btnBack);
    	
    	
    	JLabel lblMyFiles = new JLabel("My Files");
    	lblMyFiles.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 38));
    	lblMyFiles.setBounds(297, 22, 171, 50);
    	add(lblMyFiles);
    	JButton btnRetrive = new JButton("Retrieve");
    	btnRetrive.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    		}
    	});
    	btnRetrive.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnRetrive.setBounds(558, 103, 92, 37);
    	add(btnRetrive);
    	
    	JButton btnUpdate = new JButton("Update");
    	btnUpdate.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    		}
    	});
    	btnUpdate.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnUpdate.setBounds(456, 151, 92, 37);
    	add(btnUpdate);
    	
    	JButton btnDelete = new JButton("Delete");
    	btnDelete.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    		}
    	});
    	btnDelete.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnDelete.setBounds(558, 151, 92, 37);
    	add(btnDelete);
    	
    	JButton btnShare = new JButton("Share");
    	btnShare.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    		}
    	});
    	btnShare.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnShare.setBounds(456, 199, 92, 37);
    	add(btnShare);
    	
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
	}
}
