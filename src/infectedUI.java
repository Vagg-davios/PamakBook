import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class infectedUI extends JFrame{
	
	private JPanel panel2 = new JPanel();
	private JTextArea infectedU = new JTextArea(10,20);
	private JButton bLButton = new JButton("Back to Login Screen");
	
	/* 
	 * 	This function is what allows us to show the possible carriers of a user by basically
	 * taking the function from the User class and just appending it to a text area in a panel. 
	 */
	
	public void infectedGUI(User u, ArrayList<User> infected) {
		
		//JTextArea
		infectedU.append("**********************************************************************\n");
		infectedU.append(u.getName() + " has been infected. The following users have to be tested\n");
		infectedU.append("**********************************************************************\n");
		
		
		//Inserting the users
		for( User i : infected)
			infectedU.append(i.getName() + "\n");
		
		
		//Adding them to the panel
		infectedU.setEditable(false);
		panel2.add(infectedU);
		
		
		//JButton
		ButtonListener listener = new ButtonListener();
		panel2.add(bLButton);
		bLButton.addActionListener(listener);
		
		
		//Miscellaneous
		setContentPane(panel2);
		setTitle("Πιθανή Μετάδοση Ιού"); // Should change to english 
		setVisible(true);
		setSize(450,340);
	}
	
	public class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(bLButton)) {
				dispose();
				
			}
		}		
	}
}
