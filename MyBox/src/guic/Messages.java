package guic;

import main.MyBoxGUI;

import javax.swing.JButton;
import javax.swing.JScrollBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

//import com.sun.org.apache.bcel.internal.generic.LNEG;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


import java.awt.Font;
import java.awt.Label;

public class Messages extends MyBoxGUI
{
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	public Messages() 
	{
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				messages.setVisible(false);
    			userpage.setVisible(true);
			}
		});
		btnBack.setBounds(59, 299, 74, 36);
		add(btnBack);
		
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(frmMyBox,"Open message: "+ListModel.getElementAt(list.getSelectedIndex()),null, JOptionPane.YES_NO_CANCEL_OPTION);
			}
		});
		btnOpen.setBounds(564, 299, 74, 36);
		add(btnOpen);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ListModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnDelete.setBounds(648, 299, 74, 36);
		add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 57, 663, 235);
		add(scrollPane);
		
	    list = new JList(ListModel);
		scrollPane.setViewportView(list);
		
		Label lblMessage = new Label(" Sender                                | Date");
		lblMessage.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
		scrollPane.setColumnHeaderView(lblMessage);
		
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
