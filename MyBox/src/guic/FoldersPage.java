package guic;

import main.MyBoxGUI;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoldersPage extends MyBoxGUI
{
	final DefaultMutableTreeNode root = new DefaultMutableTreeNode("MyBox");
	final JTree MyBoxTree = new JTree(root);
	final DefaultTreeModel model = (DefaultTreeModel)MyBoxTree.getModel();;
	public FoldersPage() 
	{
		setLayout(null);
        MyBoxTree.setBounds(60, 94, 497, 289);
        add(MyBoxTree);
        
		 
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		root.add(new DefaultMutableTreeNode("another_child"));
        		model.reload(root);
        		
        	}
        });
        btnAdd.setBounds(221, 23, 200, 50);
        add(btnAdd);
	}
	
	
	
	
	
}
