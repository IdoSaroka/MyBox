package guic;

import main.MyBoxGUI;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JScrollBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.xml.stream.events.Namespace;

import files.Country;
import files.CountryTreeCellRenderer;
import guic.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ScrollPaneConstants;
public class FoldersPage extends MyBoxGUI
{
	final static DefaultMutableTreeNode MyBoxroot = new DefaultMutableTreeNode("MyBox");
	final static JTree MyBoxTree = new JTree(MyBoxroot);
	final static DefaultTreeModel model = (DefaultTreeModel)MyBoxTree.getModel();
	static DefaultMutableTreeNode[] files_dir = new DefaultMutableTreeNode[1];
	
	static String[] names = new String[1];
	static String[] CodeName = new String[1];
	static String[] Path_main = new String[1];
	static ArrayList<String> filesAndFolders;
	
	int i = 1;
	private JTextField TxtAddFld;
	private JTextField txtFilename;
	public FoldersPage() 
	{
		setLayout(null);

		names[0] = "MyBox";
		CodeName[0] = "MyBox";
		Path_main[0] = "C:\\MyBox";
		files_dir[0] = MyBoxroot;
		MyBoxTree.setCellRenderer(new CountryTreeCellRenderer());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 26, 408, 312);
        add(scrollPane);
        scrollPane.setViewportView(MyBoxTree);

        filesAndFolders = FileOwnerPage.getFilesAndFoldersList();
        
        JButton btnAdd = new JButton("Add File");
        btnAdd.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{	
        		if(TxtAddFld.getText().contains(" ") || TxtAddFld.getText().equals(""))
        		{
        			JOptionPane.showMessageDialog(frmMyBox,"No Name ","Invalid Name",JOptionPane.WARNING_MESSAGE);
        			return;
        		}
        		/** These lines use for get clicked path for operation  **/
                TreePath path = MyBoxTree.getSelectionPath(); 
                if(path == null)
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Please Choose Folder Location","Invalid Location",JOptionPane.WARNING_MESSAGE);
        			return;
                }
          //      DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                

                Country country =  new Country(TxtAddFld.getText(), "/flags/page_text.gif");
                
                files_dir = Arrays.copyOf(files_dir, files_dir.length+1);		
                files_dir[files_dir.length-1] = new DefaultMutableTreeNode(country);
                MyBoxroot.add(files_dir[files_dir.length-1]);
            	
            	
            	
        		model.reload(MyBoxroot);
        		
        		
        	}
        });
        btnAdd.setBounds(460, 109, 107, 23);
        add(btnAdd);
        
        JButton btnRemove = new JButton("Print");
        btnRemove.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		
        		/** These lines use for get clicked path for operation  **/
              
        		
        	//	TreePath path = MyBoxTree.getSelectionPath();
            /*    if(path == null)
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Please Choose Folder/File To Delete","Invalid Selection",JOptionPane.WARNING_MESSAGE);
        			return;
                }
                if(path.toString().equals("[MyBox]"))
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Can't Delete Parent Directory","Invalid Selection",JOptionPane.WARNING_MESSAGE);
        			return;
                }*/
              //  DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        		File currentDir = new File("C:\\MyBox"); // current directory
        		displayDirectoryContents(currentDir);
 
        	}
        });
        btnRemove.setBounds(471, 24, 89, 23);
        add(btnRemove);
        
        JButton btnAddFolder = new JButton("Add Folder");
        btnAddFolder.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		/*
        		if(TxtAddFld.getText().contains(" ") || TxtAddFld.getText().equals(""))
        		{
        			JOptionPane.showMessageDialog(frmMyBox,"No Name ","Invalid Name",JOptionPane.WARNING_MESSAGE);
        			return;
        		}*/
        		/** These lines use for get clicked path for operation  **/
                
             /*   if(path == null)
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Please Choose Folder Location","Invalid Location",JOptionPane.WARNING_MESSAGE);
        			return;
                }
                if(TxtAddFld.getText().contains("."))
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Can't Create File With Suffix","File no Suffix",JOptionPane.WARNING_MESSAGE);
        			return;
                }
                */
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        		
        		File currentDir = new File("C:\\MyBox"); // current directory
        		//displayDirectoryContents(currentDir);
        	
        		try {
        			File[] files = currentDir.listFiles();
        			for (File file : files) 
        			{
        				if (file.isDirectory()) 
        				{
        	                int begin = 0, i;
        	                for( i = file.toString().length()-1; i >0 ; i--)
        	                {
        	                	if( file.toString().charAt(i) == '\\')
        	                	{
        	                		begin = ++i;
        	                		names = Arrays.copyOf(names, names.length+1);											/**Resize Array by 1**/
        	                		names[names.length-1] = file.toString().substring(begin);
        	                		break;
        	                	}
        	                }
        					System.out.println(file.getCanonicalPath() + " " + names[names.length-1]);
        					
        				} else 
        				{
        					System.out.println(file.getCanonicalPath());
        				}
        			}
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}

        		
        		
                TreePath path = MyBoxTree.getSelectionPath();											/**path is selection mouse path**/
                Country country =  new Country(TxtAddFld.getText(), "/flags/folder.gif");				/**Country has a unique ID and Name**/
                
                files_dir = Arrays.copyOf(files_dir, files_dir.length+1); 												/**Resize Array by 1**/
                files_dir[files_dir.length-1] =  new DefaultMutableTreeNode(country);								/**New Node with id and name**/
            	
            	CodeName = Arrays.copyOf(CodeName, CodeName.length+1);									/**Resize Array by 1**/
            	Path_main = Arrays.copyOf(Path_main, Path_main.length+1);								/**Resize Array by 1**/
            	CodeName[CodeName.length-1] = country.toString();  										/**set location**/
            	names[names.length-1] = TxtAddFld.getText();											/**set Name**/
            	((DefaultMutableTreeNode) path.getLastPathComponent()).add(files_dir[files_dir.length-1]);		/**add this node**/
        		model.reload(((DefaultMutableTreeNode) path.getLastPathComponent()));					/**Refresh**/
        		
        		System.out.println(CodeName[CodeName.length-1] + " " + names[names.length-1]);
        		
        	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        	

        });
        btnAddFolder.setBounds(450, 78, 115, 23);
        add(btnAddFolder);
        
        TxtAddFld = new JTextField();
        TxtAddFld.setBounds(574, 78, 115, 23);
        add(TxtAddFld);
        TxtAddFld.setColumns(10);
        
        JButton btnChangeName = new JButton("Change Name");
        btnChangeName.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		/*
        		if(txtFilename.getText().contains(" ") || txtFilename.getText().equals(""))
        		{
        			JOptionPane.showMessageDialog(frmMyBox,"No Name ","Invalid Name",JOptionPane.WARNING_MESSAGE);
        			return;
        		}
        		*/
        		/** These lines use for get clicked path for operation  **/
                TreePath path = MyBoxTree.getSelectionPath();   
               /* if(path.toString().contains("."))
                	if(!txtFilename.getText().contains("."))
                	{
                		JOptionPane.showMessageDialog(frmMyBox,"Can't Change File name without Suffix","Invalid Location",JOptionPane.WARNING_MESSAGE);
                		return;
                	}
                	*/
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                /** Selected path for operation **/
                	
              //  Country country =  new Country(TxtAddFld.getText(), "/flags/page_text.gif");
                node.setUserObject(txtFilename.getText());
                /** Reload the view for GUI **/
        		model.reload(node);
        		
        	}
        });
        btnChangeName.setBounds(460, 184, 128, 23);
        add(btnChangeName);
        
        txtFilename = new JTextField();
        txtFilename.setBounds(593, 184, 107, 23);
        add(txtFilename);
        txtFilename.setColumns(10);
        
    	JLabel lblPicMBX = new JLabel("");
        lblPicMBX.setIcon(new ImageIcon(LoginPage.class.getResource("/guic/MyBox.jpg")));
        lblPicMBX.setBounds(10, 11, 780, 478);
        add(lblPicMBX);
	}
	

   	
	public static void displayDirectoryContents(File currentDir)
	{
	try {
		File[] files = currentDir.listFiles();
		for (File file : files) 
		{
			int begin = 0, i;
			Path_main = Arrays.copyOf(Path_main ,Path_main.length+1);
        	Path_main[Path_main.length-1] = file.toString();										/**Set path**/
        	
               for( i = Path_main[Path_main.length-1].length()-1; i >0 ; i--)
            {
               	if( file.toString().charAt(i) == '\\')
            	{
               		begin = ++i;
               		break;	
            	}
            }
               
        	names = Arrays.copyOf(names, names.length+1);									/**Resize Array by 1**/
    		names[names.length-1] = file.toString().substring(begin);						/**Set Name of added file/folder**/
    		
    		
			if (file.isDirectory()) 
			{
				String temp = null;
                Country country =  new Country(names[names.length-1], "/flags/folder.gif");
           		files_dir = Arrays.copyOf(files_dir, files_dir.length+1); 							/**Resize Array by 1**/
         		files_dir[files_dir.length-1] =  new DefaultMutableTreeNode(country);				/**New Node with id and name**/
         		for(i = begin-2; i > 0; i--)
         		{
         			if(Path_main[Path_main.length-1].charAt(i) =='\\')
         			{
         				temp = Path_main[Path_main.length-1].substring(i+1, begin-1);
         				break;
         			}
         		}
         		if(temp.equals(names[0]))
         			files_dir[0].add(files_dir[files_dir.length-1]);
         		else
         			{
         				for(i = 1; i< names.length;i++)
         				{
         					if(temp.equals(names[i]))
         							files_dir[i].add(files_dir[files_dir.length-1]);
         				}
         			}
         		
         		displayDirectoryContents(file);	

				
			} else /**Mean "file" is FILE**/
			{		
				String temp = null;
            	Country country =  new Country(names[names.length-1], "/flags/page_text.gif");		
                files_dir = Arrays.copyOf(files_dir, files_dir.length+1); 												/**Resize Array by 1**/
                files_dir[files_dir.length-1] =  new DefaultMutableTreeNode(country);								/**New Node with id and name**/
                
                
         		for(i = begin-2; i > 0; i--)
         		{
         			if(Path_main[Path_main.length-1].charAt(i) =='\\')
         			{
         				temp = Path_main[Path_main.length-1].substring(i+1, begin-1);
         				break;
         			}
         		}
         		if(temp.equals(names[0]))
         			files_dir[0].add(files_dir[files_dir.length-1]);
         		else
         			{
         				for(i = 1; i< names.length;i++)
         				{
         					if(temp.equals(names[i]))
         							files_dir[i].add(files_dir[files_dir.length-1]);
         				}
         			}
			}
			
			
			
			model.reload(files_dir[0]);
			System.out.println( file.getCanonicalPath() );
		}
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	
}
	
	
}

