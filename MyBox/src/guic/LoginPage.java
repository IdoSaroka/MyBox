package guic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.MyBoxGUI;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

public class LoginPage extends MyBoxGUI
{
    /*
     * Here this is our first Card of CardLayout, which will
     * be added to the contentPane object of JPanel, which
     * has the LayoutManager set to CardLayout.
     */  
    private ActionListener action;
    private JTextField userNametxt;
    private JTextField Porttxt;
    private JPasswordField passwordField;
    private JTextField iptxt1;
    private JTextField iptxt2;
    private JTextField iptxt3;
    private JTextField iptxt4;
    private JLabel lblpoint1;
    private JLabel lblpoint2;
    private JLabel lblpoint3;
    private JLabel prtsight;
    private boolean ok = false;
    private String IPAddressVaild;
    public LoginPage() 
    {
        init();
    }

    private void init() 
    {
        final JButton btnOK = new JButton("OK");
        btnOK.setBounds(333, 362, 79, 50);
        action = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() == btnOK)
                {
                	IPAddressVaild = meth.Connecting(iptxt1.getText(), iptxt2.getText(), iptxt3.getText(), iptxt4.getText());
    				if( ( meth.isUserValid(userNametxt.getText()) && meth.isPasswordValid(passwordField.getText()) && meth.isPortValid(Porttxt.getText()) &&
    						meth.isIPValid(IPAddressVaild)) 
    						&& ( !userNametxt.getText().equals("")  && !passwordField.getText().equals("") && !Porttxt.getText().equals("")
    						&& !(iptxt1.getText().equals("") || iptxt2.getText().equals("") || iptxt3.getText().equals("") || iptxt4.getText().equals("") ))
    						&& iptxt1.getText().length()<=3 && iptxt2.getText().length()<=3 && iptxt3.getText().length()<=3 && iptxt4.getText().length()<=3 )
    				{
    					ok = true;
    					IPAddress = meth.Connecting(iptxt1.getText()+".", iptxt2.getText()+".", iptxt3.getText()+".", iptxt4.getText());	
    				}
    				else
    				{
    					if(!meth.isUserValid(userNametxt.getText()) || userNametxt.getText().equals(""))
    						JOptionPane.showMessageDialog(frmMyBox,"User Name Can Contain Only The Following Characters: ( 0-9 , a-z , A-Z , _ )"
    								+ " ","Invalid characters",JOptionPane.WARNING_MESSAGE);
    					else if(!meth.isPasswordValid(passwordField.getText()) || passwordField.getText().equals(""))
    						JOptionPane.showMessageDialog(frmMyBox,"Invalid characters","Error Message",JOptionPane.WARNING_MESSAGE);
    					else if(!meth.isPortValid(Porttxt.getText()) || Porttxt.getText().equals(""))
    						JOptionPane.showMessageDialog(frmMyBox,"Port Can Contain Only Numbers","Invalid characters",JOptionPane.WARNING_MESSAGE);
    					else if( !meth.isIPValid( IPAddressVaild) || iptxt1.getText().equals("") || iptxt2.getText().equals("") || iptxt3.getText().equals("") || iptxt4.getText().equals("") )
    						JOptionPane.showMessageDialog(frmMyBox,"IP Address Can Contain Only Numbers ","Invalid characters",JOptionPane.WARNING_MESSAGE);
    					else if (iptxt1.getText().length()<=3 || iptxt2.getText().length()<=3 || iptxt3.getText().length()<=3 || iptxt4.getText().length()<=3)
    						JOptionPane.showMessageDialog(frmMyBox,"Each Octet can contain up to 3 numbers ","Invalid characters",JOptionPane.WARNING_MESSAGE);
    				}
    				if(ok)
    				{
    					ok = false;
    					txtUserName = userNametxt.getText();
    					txtPortNumber = Porttxt.getText();
    			        txtPassword = passwordField.getText();
    					userpage.setVisible(true);
    					loginpage.setVisible(false);
    				}
                }
            }
        };

        btnOK.addActionListener(action);
        setLayout(null);

        add(btnOK);
        
        JLabel lblWelcomeToMybox = new JLabel("Welcome To MyBox");
        lblWelcomeToMybox.setFont(new Font("Forte", Font.PLAIN, 54));
        lblWelcomeToMybox.setBounds(156, 11, 501, 57);
        add(lblWelcomeToMybox);
        
        JLabel lblUserName = new JLabel("User Name:");
        lblUserName.setFont(new Font("Arial", Font.PLAIN, 18));
        lblUserName.setBounds(233, 117, 102, 36);
        add(lblUserName);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPassword.setBounds(233, 167, 100, 22);
        add(lblPassword);
        
        userNametxt = new JTextField();
        userNametxt.setBounds(363, 127, 129, 20);
        add(userNametxt);
        userNametxt.setColumns(10);
        
        
        JLabel lblPortNumber = new JLabel("Port Number:");
        lblPortNumber.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPortNumber.setBounds(233, 245, 120, 14);
        add(lblPortNumber);
        
        Porttxt = new JTextField();
        Porttxt.setBounds(363, 244, 129, 20);
        add(Porttxt);
        Porttxt.setColumns(10);
        
        
        passwordField = new JPasswordField();
        passwordField.setBounds(363, 170, 129, 20);
        add(passwordField);
        
        
        JLabel lblIpAddress = new JLabel("IP Address:");
        lblIpAddress.setFont(new Font("Arial", Font.PLAIN, 18));
        lblIpAddress.setBounds(233, 297, 102, 14);
        add(lblIpAddress);
        
        iptxt1 = new JTextField();
        iptxt1.setBounds(363, 296, 24, 20);
        add(iptxt1);
        iptxt1.setColumns(10);

        
        iptxt2 = new JTextField();
        iptxt2.setBounds(399, 296, 24, 20);
        add(iptxt2);
        iptxt2.setColumns(10);
        
        iptxt3 = new JTextField();
        iptxt3.setBounds(434, 296, 24, 20);
        add(iptxt3);
        iptxt3.setColumns(10);
        
        iptxt4 = new JTextField();
        iptxt4.setBounds(468, 296, 24, 20);
        add(iptxt4);
        iptxt4.setColumns(10);
        
        lblpoint1 = new JLabel(".");
        lblpoint1.setFont(new Font("Arial", Font.PLAIN, 18));
        lblpoint1.setBounds(390, 302, 12, 14);
        add(lblpoint1);
        
        lblpoint2 = new JLabel(".");
        lblpoint2.setFont(new Font("Arial", Font.PLAIN, 18));
        lblpoint2.setBounds(425, 302, 12, 14);
        add(lblpoint2);
        
        lblpoint3 = new JLabel(".");
        lblpoint3.setFont(new Font("Arial", Font.PLAIN, 18));
        lblpoint3.setBounds(460, 302, 12, 14);
        add(lblpoint3);
        
        prtsight = new JLabel("(0 - 9999)");
        prtsight.setFont(new Font("Arial", Font.PLAIN, 11));
        prtsight.setBounds(254, 260, 56, 14);
        add(prtsight);
        
        JLabel label = new JLabel(".");
        label.setIcon(new ImageIcon("C:\\Users\\Ran\\Desktop\\mbox.jpg"));
        label.setBounds(10, 11, 780, 478);
        add(label);
    }
}
