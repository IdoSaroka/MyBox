package guic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import main.MyBoxGUI;
import files.Country;
import files.CountryTreeCellRenderer;
import java.awt.Font;
        
        
public class FoldersPage extends MyBoxGUI
{
	final DefaultMutableTreeNode MyBoxroot = new DefaultMutableTreeNode("MyBox");
	final JTree MyBoxTree = new JTree(MyBoxroot);
	final DefaultTreeModel model = (DefaultTreeModel)MyBoxTree.getModel();
	static DefaultMutableTreeNode[] folder = new DefaultMutableTreeNode[0];
	static DefaultMutableTreeNode[] file = new DefaultMutableTreeNode[0];

	
	int i = 1;
	private JTextField TxtAddFld;
	private JTextField txtFilename;
	
	private JButton btnBack;
	private JButton btnHelp;
	private JButton btnsignout;
	public FoldersPage() 
	{
		setLayout(null);
		MyBoxTree.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		
		MyBoxTree.setCellRenderer(new CountryTreeCellRenderer());
		
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(81, 93, 613, 277);
        add(scrollPane);
        scrollPane.setColumnHeaderView(MyBoxTree);
        
		 
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
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                

                Country country =  new Country(TxtAddFld.getText(), "/files/page_text.gif");
                
                folder = Arrays.copyOf(folder, folder.length+1);		
                folder[folder.length-1] = new DefaultMutableTreeNode(country);
            	node.add(folder[folder.length-1]);
            	
            	
        		model.reload(node);
        		
        		
        	}
        });
        btnAdd.setBounds(60, 60, 107, 23);
        add(btnAdd);
        
        JButton btnRemove = new JButton("Delete");
        btnRemove.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		
        		/** These lines use for get clicked path for operation  **/
                TreePath path = MyBoxTree.getSelectionPath();
                if(path == null)
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Please Choose Folder/File To Delete","Invalid Selection",JOptionPane.WARNING_MESSAGE);
        			return;
                }
                if(path.toString().equals("[MyBox]"))
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Can't Delete Parent Directory","Invalid Selection",JOptionPane.WARNING_MESSAGE);
        			return;
                }
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    model.removeNodeFromParent(node);
        		
        	}
        });
        btnRemove.setBounds(205, 24, 89, 23);
        add(btnRemove);
        
        JButton btnAddFolder = new JButton("Add Folder");
        btnAddFolder.addActionListener(new ActionListener() 
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
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                if(TxtAddFld.getText().contains("."))
                {
        			JOptionPane.showMessageDialog(frmMyBox,"Can't Create File With Suffix","File no Suffix",JOptionPane.WARNING_MESSAGE);
        			return;
                }
                
                Country country =  new Country(TxtAddFld.getText(), "/files/folder.gif");
                
        		file = Arrays.copyOf(file, file.length+1); 		
            	file[file.length-1] =  new DefaultMutableTreeNode(country);
            	node.add(file[file.length-1]);
        		model.reload(node);
        		
        		
        	}
        });
        btnAddFolder.setBounds(325, 24, 115, 23);
        add(btnAddFolder);
        
        TxtAddFld = new JTextField();
        TxtAddFld.setBounds(179, 58, 115, 23);
        add(TxtAddFld);
        TxtAddFld.setColumns(10);
        
        JButton btnChangeName = new JButton("Change Name");
        btnChangeName.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		if(txtFilename.getText().contains(" ") || txtFilename.getText().equals(""))
        		{
        			JOptionPane.showMessageDialog(frmMyBox,"No Name ","Invalid Name",JOptionPane.WARNING_MESSAGE);
        			return;
        		}
        		/** These lines use for get clicked path for operation  **/
                TreePath path = MyBoxTree.getSelectionPath();   
                if(path.toString().contains("."))
                	if(!txtFilename.getText().contains("."))
                	{
                		JOptionPane.showMessageDialog(frmMyBox,"Can't Change File name without Suffix","Invalid Location",JOptionPane.WARNING_MESSAGE);
                		return;
                	}
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                /** Selected path for operation **/
                node.setUserObject(txtFilename.getText());
                /** Reload the view for GUI **/
        		model.reload(node);
        		
        	}
        });
        btnChangeName.setBounds(468, 24, 128, 23);
        add(btnChangeName);
        
        txtFilename = new JTextField();
        txtFilename.setBounds(478, 58, 107, 23);
        add(txtFilename);
        txtFilename.setColumns(10);
        
       	btnHelp = new JButton("Help");
    	btnHelp.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			JOptionPane.showMessageDialog(frmMyBox,"Shows all your filed and folders","Help",JOptionPane.INFORMATION_MESSAGE);
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
    	        	if(user.getrole().equals("User"))
    	        		userpage.setVisible(false);
    	        	else if (user.getrole().equals("SystemAdmin"))
    	        		adminpage.setVisible(false);
    	        	else if (user.getrole().equals("FileOwner"))
    	        		fileownerpage.setVisible(false);
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
    			folderspage.setVisible(false);
    			if(user.getrole().equals("User"))
    				userpage.setVisible(true);
    			else if (user.getrole().equals("SystemAdmin"))
    				adminpage.setVisible(true);
    			else if (user.getrole().equals("FileOwner"))
    				fileownerpage.setVisible(true);
    			
    		}
    	});
    	btnBack.setBounds(3, 2, 68, 23);
    	add(btnBack);
	}
}

