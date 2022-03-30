import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame {
	private JPanel panel;
	private JButton uButton;
	private JButton iButton;
	private JButton nButton;
	private JButton sButton;
	private JTextField uName;
	private JTextField uEmail;
	private ArrayList<User> uList;
	private ArrayList<Group> grouplist1;
	private Login l = new Login();
	
	public GUI(ArrayList<User> users, ArrayList<Group> grouplist) {
		
		//Declaration of the variable so it can be used in the Listener
		grouplist1 = grouplist;
		
		//Panel and listener
		uList = users;
		ButtonListener listener = new ButtonListener();
		panel = new JPanel();
		
		//New User Button, has to be before the others
		nButton = new JButton("New User");
		panel.add(nButton);
		nButton.addActionListener(listener);
		
		//Textfield
		uName = new JTextField("user name",6);
		panel.add(uName);
		
		uEmail = new JTextField("user email",6);
		panel.add(uEmail);
		
		//Buttons
		
		uButton = new JButton("Enter User Page");
		panel.add(uButton);
		uButton.addActionListener(listener);
		
		iButton = new JButton("Show Potential Infections");
		panel.add(iButton);
		iButton.addActionListener(listener);
		
		sButton = new JButton("Save PamakBook");
		panel.add(sButton);
		sButton.addActionListener(listener);
		
		
		//Setting up the panel
		setContentPane(panel);
		
		
		//Miscellaneous 
		setVisible(true);
		setTitle("Κεντρική Σελίδα");
		setSize(365, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/* 
	 * 	A listener that either allows the user to enter the user page (uButton)
	 * or shows the possible carriers (iButton). 
	 * 
	 * 	The (nButton) creates a new user while the final button (sButton),
	 * saves the data in the PamakBook.ser file. 
	 */
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String name = uName.getText();
			String email = uEmail.getText();
			
			if(e.getSource().equals(uButton)) {
				l.login(name,uList,grouplist1);
			}
			else if(e.getSource().equals(iButton)) {
				l.iLogin(name, uList);
			}
			else if(e.getSource().equals(nButton)){
				l.newUser(name, email, uList);
			}
			else
			{	
				try {
					FileOutputStream fileOut = new FileOutputStream("PamakBook.ser");		
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(uList);
					out.writeObject(grouplist1);
					out.close();
					fileOut.close();
					JOptionPane.showMessageDialog(null, "PamakBook has been saved!");
				}catch(IOException exc) {
					exc.printStackTrace();
				}
			}
		}
	}		 
}
