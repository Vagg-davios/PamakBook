import java.io.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<User> users = new ArrayList<>(); 
		ArrayList<Group> groups = new ArrayList<>();
		
		/* Creating the users. */
		
		User u1 = new User("Makis", "iis1998@uom.edu.gr"); 
		User u2 = new User("Petros", "ics1924@uom.edu.gr");
		User u3 = new User("Maria", "iis2012@uom.edu.gr");
		User u4 = new User("Gianna", "iis19133@uom.edu.gr");
		User u5 = new User("Nikos", "dai1758@uom.edu.gr");
		User u6 = new User("Babis", "ics19104@uom.edu.gr");
		User u7 = new User("Stella", "dai1827@uom.edu.gr");
		User u8 = new User("Eleni", "ics2086@gmail.com"); // User doesn't get registered because format is "@gmail.com" instead of "@uom.edu.gr"
		
		/* Creating the two groups. */
		
		Group g1 = new Group("WebGurus","A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions");
				
		groups.add(g1); 
		groups.add(g2);
		
		users.add(u1);
		users.add(u2);
		users.add(u3);	
		users.add(u4);
		users.add(u5);
		users.add(u6);
		users.add(u7);
		users.add(u8);
		
		System.out.println("CONSOLE: ");
		
		/* Validation check for the email. */
		
		boolean flag = true;
		
		for(User u : users) {
			if(!u.emailValidation(u.getName(), u.getEmail(), flag)) {
				users.remove(u);
				System.out.println("User " + u.getName() + " has not been created. Email format is not acceptable.");
				break;
			}				
		}
		
		/* The rest is kind of self explanatory. */
		
		System.out.println("\nCONSOLE: ");
		
		// Users making friends, obviously could be done through the ui,
		//  but it's only to indicate that it works fine without having to press any buttons
		
		u1.addFriend(u2);
		u1.addFriend(u5);
		u5.addFriend(u6);
		u3.addFriend(u4);
		u3.addFriend(u2);
		u4.addFriend(u6);
		u5.addFriend(u3);
		u1.addFriend(u6);
		u5.addFriend(u2);
		u7.addFriend(u1);
		
		// Printing the common friends of the object (user) u5 and u4
		
		u5.commonFriends(u4);
		u1.commonFriends(u5);
		
		// Printing the friends of users
		
		u1.printFriends();
		u3.printFriends();
		
		// Adding users to group 1
		
		g1.addMember(u4);
		g1.addMember(u3);
		g1.addMember(u2);
		
		// Adding users to group 2
		
		g2.addMember(u4);
		g2.addMember(u5);
		g2.addMember(u6);
		g2.addMember(u5);
		
		// Printing a list of the groups u4 (Gianna in this case) has been enrolled in
		
		u4.printUserGroups();	
		
		// Printing the members of each group
		
		g1.printGroupMembers();
		g2.printGroupMembers();
		
		// Printing the possibly infected users
		
		u4.printPossibleCarriers(u4.possibleCarriers());
		
		/* Reading from the file PamakBook.ser */
		
		try {
			FileInputStream fileIn = new FileInputStream("PamakBook.ser");		
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			users = (ArrayList<User>) in.readObject(); // Users, their friends and posts
			groups = (ArrayList<Group>) in.readObject(); // The groups plus the members of each group.
			
			in.close();
			fileIn.close();
			
		}catch(IOException exc) {
			exc.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
		/* Calling the main GUI */
		
		new GUI(users,groups);
		
		
	}
}
