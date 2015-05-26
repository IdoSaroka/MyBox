package GUIs;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class UploadFile extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public UploadFile() 
	{

		setBounds(100, 100, 800, 500);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Click Here to upload a file");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(126, 145, 205, 30);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(409, 150, 300, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDone.setBounds(365, 223, 89, 30);
		add(btnDone);
		
		JButton btnHelpMe = new JButton("Help Me");
		btnHelpMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHelpMe.setBounds(188, 390, 122, 52);
		add(btnHelpMe);
		
		JButton SignOut = new JButton("Sign-Out");
		SignOut.setBounds(501, 392, 122, 48);
		add(SignOut);
	}
}
