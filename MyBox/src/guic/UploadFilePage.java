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

public class UploadFilePage extends MyBoxGUI
{
	
	//private Browse browse;
	UserController local=new UserController();
	private String txtDescription=null;
	private int privelege=3;
	
    public UploadFilePage() 
    {
    	setLayout(null);      
    	JButton btnBack = new JButton("Back");
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			uploadfilepage.setVisible(false);
    			userpage.setVisible(true);
    			
    		}
    	});
    	btnBack.setBounds(3, 2, 68, 23);
    	add(btnBack);
    	
    	JButton btnBrowse = new JButton("Browse");
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
    	btnBrowse.setBounds(181, 184, 89, 23);
    	add(btnBrowse);
    	
    	JButton btnDone = new JButton("Done");
    	btnDone.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			try {
					local.sendFile(uploadfilepage, txtDescription, privelege);
					uploadfilepage.setVisible(false);
	    			userpage.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	});
    	btnDone.setBounds(323, 255, 89, 38);
    	add(btnDone);
    	
    	txtPath = new JTextField();
    	txtPath.setBounds(305, 185, 372, 20);
    	txtPath.setEditable(false);
    	add(txtPath);
    	
    	JFormattedTextField frmtdtxtDescription = new JFormattedTextField();
    	frmtdtxtDescription.setText("Enter file description here");
    	txtDescription = frmtdtxtDescription.getText();
    	frmtdtxtDescription.setBounds(244, 116, 148, 20);
    	add(frmtdtxtDescription);
    	
    	JRadioButton rdbtnPrivate = new JRadioButton("Private file");
    	rdbtnPrivate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e)
    		{
    			privelege=1;
    		}
    	});
    	rdbtnPrivate.setBounds(123, 72, 109, 23);
    	add(rdbtnPrivate);
    	
    	JRadioButton rdbtnNewRadioButton = new JRadioButton("Share with group of interest");
    	rdbtnNewRadioButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			privelege=2;
    		}
    	});
    	rdbtnNewRadioButton.setBounds(234, 72, 109, 23);
    	add(rdbtnNewRadioButton);
    	
    	JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Share with everyone");
    	rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			privelege=3;
    		}
    	});
    	rdbtnNewRadioButton_1.setBounds(348, 72, 136, 23);
    	add(rdbtnNewRadioButton_1);
    	txtPath.setColumns(10);
    	
    	
    	JButton btnsignout = new JButton("Sign-Out");
    	
    }
}
