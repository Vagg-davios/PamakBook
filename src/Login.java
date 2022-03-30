import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Login {
	
	public void login(String u, ArrayList<User> uList, ArrayList<Group> group) {
		
		GUI2 n = new GUI2();
		int counter = 0;
		
		/* 	Loops through the user list and once it finds a user,it opens the user page,
		 * otherwise it'll show an error message as the user wasn't found. 
		 */
		
		for(int i=0; i<uList.size(); i++) {
			counter++;
			
			if(uList.get(i).getName().equals(u)) {
				n.userPage(uList.get(i),group,uList);
				break;
			}
			else if(counter==uList.size()) {
				JOptionPane.showMessageDialog(null, "User " + u + " Not Found");
				break;
			}
		}	
	}	
	
	public void iLogin(String u, ArrayList<User> uList) {
		
		/* 
		 * 	Does exactly what the function above does but instead of opening the user page
		 * it opens the infectedUI.
		 */
		
		infectedUI iUI = new infectedUI();
		int counter = 0;
		
		for(int i=0; i<uList.size(); i++) {
			counter++;
	
			if(uList.get(i).getName().equals(u)) {
				iUI.infectedGUI(uList.get(i),uList.get(i).possibleCarriers());
				break;
			}
			else if(counter == uList.size()){
				JOptionPane.showMessageDialog(null, "User " + u + " Not Found");
				break;
			}
		}
	}
	
	/*	
	 * This function basically creates a new user, after searching if the
	 * user has already registered. 
	 * 
	 * 	Afterwards is an email format check to make sure that the email format
	 * is right. 
	 */

	public void newUser(String uName, String email, ArrayList<User> uList) {
		
		boolean flag = true;
		
		if(search(uName,uList)) {
			JOptionPane.showMessageDialog(null, "User " + uName + " already exists");
		}
		else {
			User u = new User(uName,email);
			if(u.emailValidation(uName, email, flag)) {
				uList.add(u);
				JOptionPane.showMessageDialog(null, "User " + uName + " has been created successfully!");
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid email format. Email '" +  email + "' is invalid.");
		}
	}
	
	/*	A simple function to search through the 
	 * user list. Returns true if they are in there
	 * otherwise returns false.
	 */
	
	public boolean search(String name, ArrayList<User> uList){
		
		for(User u : uList)
			if(u.getName().equals(name))
				return true;
		
		return false;
		
	}
	
}