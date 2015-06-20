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
	public SharedFilessPage()
	{
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			sharedfilesrspage.setVisible(false);
    			userpage.setVisible(true);
    			
    		}
    	});
    	btnBack.setBounds(3, 2, 55, 23);
    	add(btnBack);
		
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
	}
}
