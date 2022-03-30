import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI2 extends JFrame{
	private JPanel panel1 = new JPanel(null);
	private JTextField uName = new JTextField(10);
	private JTextField email = new JTextField(15);
	private JTextField addFriend = new JTextField(15);
	private JTextArea postPage = new JTextArea(10,25);
	private JTextArea posts = new JTextArea();
	private JTextArea sFriends = new JTextArea();
	private JLabel recent = new JLabel("Recent Posts by Friends");
	private JLabel suggested = new JLabel("Suggested Friends");
	private JLabel friend = new JLabel("Add a friend");
	private JLabel enroll = new JLabel("Enroll to a group");
	private JButton bLButton = new JButton("Back to Login Screen");
	private JButton pButton = new JButton("Post");
	private JButton fButton = new JButton("Add friend");
	private JButton enButton = new JButton("Enroll");
	private HashSet<String> sList;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private Date timestamp = new Date();
	private String[] choices = {"WebGurus","ExamSolutions"};
	private ArrayList<Group> grouplist1;
	private User u1;
	private String selected;
	private Group g;
	private boolean flag = false;
	
	public void userPage(User u, ArrayList<Group> grouplist, ArrayList<User> uList) {
		
		//Declaration of the two variables so they can be used in the Listener
		grouplist1 = grouplist;
		u1 = u;
				
		/* I have labeled each component in order for the code to be more readable. Haven't explained thoroughly every
		 * single thing since a few functions are self explanatory. */
		
		/*The first JPanel has been set to null, thus we set the bounds of every component that's going on that panel*/
		
		//TextFields
		uName.setText(u.getName());
		uName.setEditable(false);
		uName.setBounds(25,10,117,20);
		panel1.add(uName);
		
		email.setText(u.getEmail());
		email.setEditable(false);
		email.setBounds(148,10,173,20);
		panel1.add(email);
		
		addFriend.setBounds(25,67,160,20);
		panel1.add(addFriend);
		
		//JButtons
		bLButton.setBounds(327,5,161,27);
		pButton.setBounds(370,224,66,29);
		fButton.setBounds(55,92,100,25);
		enButton.setBounds(350,92,100,25);
		panel1.add(bLButton);
		panel1.add(pButton);
		panel1.add(fButton);
		panel1.add(enButton);
		
		//Back to login screen button
		ButtonListener listener = new ButtonListener();
		bLButton.addActionListener(listener);
		
		
		
		//Post button + Action listener
		
		/* 	What this does is once you hit the "Post button", it will save the post to user's post list and 
		 * afterwards insert a new post at the top of the postPage. The code below the action listener
		 * is what allows us to see the posts once we log in as the same or different user.  */
		
		pButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Post p1 = new Post(u.getName(), posts.getText(),timestamp);
				
				u.addPost(p1);
				postPage.insert(p1.getName() + ", " + p1.getDate() + ", " + posts.getText() + "\n", 0);
			}
		}); 
		
		for(int i=0; i<u.getPosts().size(); i++) {
			postPage.append(u.getPosts().get(i).getName() + ", " + u.getPosts().get(i).getDate() + ", " + u.getPosts().get(i).getText() + "\n");
			
		}
		
		
		//JTextAreas
		Border border = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.gray);
		
		posts.setBorder(border);
		posts.setLineWrap(true);
		posts.setWrapStyleWord(true);
		scrollPane = new JScrollPane(posts);
		
		postPage.setBorder(border);
		postPage.setLineWrap(true);
		postPage.setWrapStyleWord(true);
		scrollPane1 = new JScrollPane(postPage);
		
		scrollPane.setBounds(110,150,254,145);
		scrollPane1.setBounds(200,300,254,145);
		panel1.add(scrollPane);
		panel1.add(scrollPane1);
		
		
		//Suggested friends, using an Iterator to go through the list and append them into the text area dynamically
		sList = u.suggestedFriends();
		sFriends.setBounds(275,451,40,(sList.size() * 17));
		sFriends.setFont(sFriends.getFont().deriveFont(Font.BOLD, sFriends.getFont().getSize()));
		
		Iterator<String> itr = sList.iterator();
		
		while(itr.hasNext())
			sFriends.append(itr.next().toString() + "\n");

		sFriends.setEditable(false);
		panel1.add(sFriends);
		
		//JLabels
		recent.setBounds(60,360,173,20);
		suggested.setBounds(163,455,173,20);
		friend.setBounds(70,47,160,20);
		enroll.setBounds(355,47,160,20);
		panel1.add(recent);
		panel1.add(suggested);
		panel1.add(friend);
		panel1.add(enroll);
		
		
		//JComboBox and the Listener for it
		
		JComboBox<String> gc = new JComboBox<String>(choices);
		
		enButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*	
				 * This essentially takes the selected item, checks if it's group WebGurus
				 * and after some filtering you are shown a message. No need to check further 
				 * since some filtering is already done in the "addMember" function. Same
				 * goes for the group ExamSolutions but this group needs more filtering 
				 * as it is a little more complex.
				 */
				
				selected = gc.getSelectedItem().toString();
				
				if(selected.equals("WebGurus")) {
					g = grouplist1.get(0);
					if(!grouplist1.get(0).checkIfMember(u1)) {
						grouplist1.get(0).addMember(u1);
						JOptionPane.showMessageDialog(null, "User " + u1.getName() + " has been sucessfully enrolled in group WebGurus.");
					}
					else if(grouplist1.get(0).checkIfMember(u1))
						JOptionPane.showMessageDialog(null, "User " + u1.getName() + " is already a member of the group WebGurus.");
					
				}
				else if(selected.equals("ExamSolutions")) {
				
					g = grouplist1.get(1);
					
					for(int i=0; i<g.getMembers().size(); i++) {
						if(u1.checkFriendship(g.getMembers().get(i).getName()))
							flag = true;
						else
							flag = false;
					}
					
					if(flag) {
						grouplist1.get(1).addMember(u1);
						JOptionPane.showMessageDialog(null, "User " + u1.getName() + " has been sucessfully enrolled in group ExamSolutions.");
					}
					else if(!flag && !grouplist.get(1).checkIfMember(u1))
						
						JOptionPane.showMessageDialog(null, "FAILED: " + u1.getName() + " cannot be enrolled in group ExamSolutions.");
						
					else 
						JOptionPane.showMessageDialog(null, "User " + u1.getName() + " is already a member of the group ExamSolutions.");
			
								
				}
			}});

		gc.setBounds(320,67,160,20);
		panel1.add(gc);	
		
		
		//Add friend button and the Listener for it
		
		/* 
		 * 	What this Listener does is first it matches the string
		 * with the user from the user list (uList), and afterwards
		 * returns false if they're already friends. If it does 
		 * return true it either means that they're not friends
		 * or that the user does not exist. 
		 * 
		 * 	If they do exist, the aFriend variable is no longer null,
		 * therefore leading to a message saying that they are now friends.
		 * 	If not, they get a message that the user couldn't be found.
		 */
		fButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User aFriend=null;
				String f = addFriend.getText();
				
				for(int i=0; i<uList.size(); i++)
					if(u1.checkFriendship(f)) 
						flag = false;
					else 
						flag = true;
				
				for(int i=0; i<uList.size(); i++)
					if(f.equals(uList.get(i).getName())) {
						aFriend = uList.get(i);
						break;
					}
				
				if(flag) {
					if(aFriend!=null) {
						u1.addFriend(aFriend);
						JOptionPane.showMessageDialog(null, "User " + u1.getName() + " and " + f + " are now friends.");
					}
					else
						JOptionPane.showMessageDialog(null, "User " + f + " could not be found.");
				}
				else
					JOptionPane.showMessageDialog(null, "User " + u1.getName() + " is already friends with user " + f + ".");
			}
		});
		
		//Panel
		add(panel1);
		setContentPane(panel1);
		
		//Miscellaneous
		postPage.setEditable(false);
		setTitle("Σελίδα Χρήστη");
		setVisible(true);
		setSize(520,610);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	/* 
	 * 	A simple listener that's used to dispose the window 
	 * if the source is the "Back to login screen" button 
	 */
	
	public class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(bLButton)) {
				dispose();
			}
		}		
	}
	
}
