package guic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import main.MyBoxGUI;

import java.awt.Font;


public class AdminPage extends MyBoxGUI
{
	private JButton btnHelp;
	private JButton btnsignout;

    public AdminPage()
    {
    	setLayout(null);
    	
    	JButton btnSearchgoi = new JButton("Search a GOI");
    	btnSearchgoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnSearchgoi.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			adminpage.setVisible(false);
    			searchgoipage.setVisible(true);

    		}
    	});
    	btnSearchgoi.setBounds(327, 87, 122, 42);
    	add(btnSearchgoi);
    	
    	JButton btnYourGoi = new JButton("Create GOI");
    	btnYourGoi.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnYourGoi.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent arg0)
    		{
    			adminpage.setVisible(false);
    			creategoipage.setVisible(true);
    		}
    	});
    	btnYourGoi.setBounds(119, 87, 123, 42);
    	add(btnYourGoi);
    	
    	JLabel lblGoi = new JLabel("Group Of Interest");
    	lblGoi.setFont(new Font("Footlight MT Light", Font.BOLD | Font.ITALIC, 36));
    	lblGoi.setBounds(239, 18, 400, 34);
    	add(lblGoi);
    	
    	JSeparator separator = new JSeparator();
    	separator.setBounds(74, 63, 621, 26);
    	add(separator);
    	
    	JSeparator separator_2 = new JSeparator();
    	separator_2.setOrientation(SwingConstants.VERTICAL);
    	separator_2.setBounds(74, 11, 17, 154);
    	add(separator_2);
    	
    	JSeparator separator_3 = new JSeparator();
    	separator_3.setOrientation(SwingConstants.VERTICAL);
    	separator_3.setBounds(695, 11, 17, 154);
    	add(separator_3);
    	
    	JSeparator separator_5 = new JSeparator();
    	separator_5.setBounds(74, 164, 621, 26);
    	add(separator_5);
    	
    	JSeparator separator_6 = new JSeparator();
    	separator_6.setBounds(74, 11, 621, 26);
    	add(separator_6);
    	
    	/*
    	lblNewMSG = new JLabel("");
    	lblNewMSG.setIcon(new ImageIcon(UserPage.class.getResource("/guic/NewMSG.gif")));
    	lblNewMSG.setBounds(422, 300, 31, 14);
    	add(lblNewMSG);
    	lblNewMSG.setVisible(false);
    	*/
    	
    	JButton btnPendingRequest = new JButton("Pending Request");
    	btnPendingRequest.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnPendingRequest.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			//JOptionPane.showMessageDialog(frmMyBox,"This button not set yet","Help",JOptionPane.INFORMATION_MESSAGE);
    			adminpage.setVisible(false);
    					
    		}
    	});
    	btnPendingRequest.setBounds(531, 87, 122, 42);
    	add(btnPendingRequest);
    	
       	btnHelp = new JButton("Help");
       	btnHelp.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Here comes the help options","Help",JOptionPane.INFORMATION_MESSAGE);
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
    	        	adminpage.setVisible(false);
        			loginpage.setVisible(true);
    	        }
    			
    		}
    	});
    	btnsignout.setBounds(595, 381, 99, 36);
    	add(btnsignout);
    	
    	JLabel lblBackGround = new JLabel("");
    	lblBackGround.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
    	lblBackGround.setBounds(10, 11, 780, 478);
    	add(lblBackGround);
    }


}
