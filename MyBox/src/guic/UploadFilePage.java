package guic;

import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import main.MyBoxGUI;

import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import files.Browse;

import javax.swing.ImageIcon;

import controllers.UserController;
import javax.swing.JRadioButton;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class UploadFilePage extends MyBoxGUI
{
	UserController local=new UserController();
	JRadioButton rdbtnEveryOneShare;
	JRadioButton rdbtnGroupShare;
	JRadioButton rdbtnPrivate;
	JButton btnBack;
	JButton btnBrowse;
	JButton btnDone;
	JScrollPane scrollPane;
	JLabel lblFileDescription;
	JButton btnHelp;
	JButton btnsignout;
	JLabel lblPicMBX;
	private int privelege=3;
	static JTextPane textPane;
    public UploadFilePage() 
    {
    	setLayout(null);      
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
    	
    	btnBrowse = new JButton("Browse");
    	btnBrowse.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	btnBrowse.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) 
    		{
    			try {
					local.uploadFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
    	btnBrowse.setBounds(78, 215, 89, 23);
    	add(btnBrowse);
    	btnDone = new JButton("Done");
    	btnDone.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
    	btnDone.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    			{
    			/**Check field not null**/
    			if(textPane.getText().equals("") || textPane.getText().contains(" ") )
    			{
    				JOptionPane.showMessageDialog(frmMyBox,"No input inserted", "Wrong input",JOptionPane.WARNING_MESSAGE);
    				return;
    				
    			}
    			try {
					local.sendFile(uploadfilepage, textPane.getText(), privelege);
					
					/**Clear fields**/
					txtPath.setText("");
					textPane.setText("");
					uploadfilepage.setVisible(false);
	    			userpage.setVisible(true);
	    			/**Clear to Deafult Selection Radio Button**/
	    			rdbtnEveryOneShare.setSelected(true);
	    			privelege = 3;
	    			rdbtnGroupShare.setSelected(false);
	    			rdbtnPrivate.setSelected(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	});
    	btnDone.setBounds(323, 296, 89, 38);
    	add(btnDone);
    	txtPath = new JTextField();
    	txtPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	txtPath.setBounds(208, 215, 449, 23);
    	txtPath.setEditable(false);
    	add(txtPath);
    	
    	rdbtnPrivate = new JRadioButton("Private File");
    	rdbtnPrivate.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	rdbtnPrivate.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			if(privelege!=1)
    			{
    				privelege=1;
    				rdbtnPrivate.setSelected(true);
    				rdbtnGroupShare.setSelected(false);
    				rdbtnEveryOneShare.setSelected(false);
    			}
    			else
    			{
    				privelege = 0;
    				rdbtnPrivate.setSelected(false);
    			}
    		}
    	});
    	rdbtnPrivate.setBounds(78, 255, 109, 23);
    	add(rdbtnPrivate);
    	
    	rdbtnGroupShare = new JRadioButton("Share With Group Of Interest");
    	rdbtnGroupShare.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	rdbtnGroupShare.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			if(privelege!=2)
    			{
    				privelege=2;
    				rdbtnGroupShare.setSelected(true);
    				rdbtnEveryOneShare.setSelected(false);
    				rdbtnPrivate.setSelected(false);
    			}
    			else
    			{
    				privelege = 0;
    				rdbtnGroupShare.setSelected(false);
    			}
    		}
    	});
    	rdbtnGroupShare.setBounds(258, 255, 205, 23);
    	add(rdbtnGroupShare);
    	
    	rdbtnEveryOneShare = new JRadioButton("Share With Everyone");
    	rdbtnEveryOneShare.setSelected(true);
    	rdbtnEveryOneShare.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	rdbtnEveryOneShare.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			if(privelege!=3)
    			{
    				privelege=3;
    				rdbtnEveryOneShare.setSelected(true);
    				rdbtnGroupShare.setSelected(false);
    				rdbtnPrivate.setSelected(false);
    			}
    			else
    			{
    				privelege = 0;
    				rdbtnEveryOneShare.setSelected(false);
    			}
    		}
    	});
    	rdbtnEveryOneShare.setBounds(504, 255, 153, 23);
    	add(rdbtnEveryOneShare);
    	txtPath.setColumns(10);
    	
    	scrollPane = new JScrollPane();
    	scrollPane.setBounds(78, 63, 616, 141);
    	add(scrollPane);
    	
    	textPane = new JTextPane();
    	textPane.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
    	scrollPane.setViewportView(textPane);
    	
    	lblFileDescription = new JLabel("File Description");
    	lblFileDescription.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
    	scrollPane.setColumnHeaderView(lblFileDescription);
    	
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
    	
    	lblPicMBX = new JLabel("");
        lblPicMBX.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
        lblPicMBX.setBounds(10, 11, 780, 478);
        add(lblPicMBX);
    }
}
