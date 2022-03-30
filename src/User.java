import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class User implements Serializable {
	private String name;
	private String email;
	private ArrayList<User> friendlist;
	private ArrayList<Group> groups;
	private ArrayList<Post> postList;

	public User(String name, String email) {
		this.name = name;
		this.email = email;
		friendlist = new ArrayList<User>();
		groups = new ArrayList<Group>();
		postList = new ArrayList<Post>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setFriend(User u) {
		friendlist.add(u);
	}
	public void setGroups(Group groups) {
		this.groups.add(groups);
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public ArrayList<User> getFriends(){
		return friendlist;
	}

	/* 	
	 * 	Checks for email validation. 
	 * 
	 *  Firstly, it checks if the first three characters, are "iis", "ics" or "dai", and then if they're followed by a digit. If so, the #1 flag is true.
	 *  
	 * 	Then, it checks for the place of the '@', taking into consideration that you should be using only 3-5 digits for your email to be valid,
	 * the '@' should be in the 5th, 6th, 7th or 8th spot of the character array, starting from 0. If so, the #2 flag is true.
	 *   
	 *  After that, it checks the final part of the validation, which is if the host name format is correct. 
	 *  
	 * 	If all the flags are marked true, it proceeds to return the value TRUE. 
	 */
	
	public boolean emailValidation(String name, String email, boolean flag) {
		
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		
		char[] ch = email.toCharArray();
		
		if(ch != null && ch.length > 0)
			if(ch[0] == 105 && ch[1] == 105 && ch[2] == 115 && java.lang.Character.isDigit(ch[3])
			|| ch[0] == 105 && ch[1] == 99 && ch[2] == 115  && java.lang.Character.isDigit(ch[3])
			|| ch[0] == 100 && ch[1] == 97 && ch[2] == 105  && java.lang.Character.isDigit(ch[3]))  {
				flag1 = true;
			}
		
		if(ch.length > 5) {
			if(ch[5] == 64 || ch[6] == 64
			|| ch[7] == 64 || ch[8] == 64)
				flag2 = true; 
			}
		
		String[] lastPart = email.split("@");
		
		if(email.contains("@")) {
			if (lastPart[1].equals("uom.edu.gr")) // could also add uom.edu.com 
				flag3 = true;
			}
		
			if(flag1 && flag2 && flag3)
				flag = true;
			else 
				flag = false;
			
			
			return flag;
	}
	
	/* Checks if the user is friends with the user in the parameter. */
	
	public boolean checkFriendship(String name) {
		
		boolean flag = false;
		
		for(int i=0; i<friendlist.size(); i++) {
			if(this.name.equals(name))
				break;
			if(friendlist.get(i).getName().equals(name)) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	/* 	
	 * 	Adds each other as a friend for both parties. 
	 * 
	 * 	Unless the user is trying to friend himself. 
	 */
	
	public void addFriend(User user) {
		
		if(name.equals(user.getName()))
			System.out.println(name + " cannot be friends with themselves.");
		else {
			friendlist.add(user);
			System.out.println(name + " and " + user.getName() + " are now friends.");
			user.setFriend(this);
		}
	}
	
	/* 
	 * 	This function basically returns the common friends of two users.
	 * 
	 * 	It does that using a nested loop which essentially compares the user's friends, one by one,
	 * with all of the given user's friends, in order to determine their common friends.
	 * 
	 * 	Once a common friend is found, they get added into the common friends list.  
	 */
	
	public ArrayList<User> commonFriends(User user) {
			
		ArrayList<User> cFriends= new ArrayList<>();
		int counter=0;
		
		System.out.println("**************************************");
		System.out.println("Common friends of " + name + " and " + user.getName() );
		System.out.println("**************************************");
		
		for(int i=0; i<user.friendlist.size(); i++) 
			for(int k=0; k<friendlist.size(); k++) 
				if(user.friendlist.get(i).equals(friendlist.get(k))) {
					System.out.println((counter+1) + ": " + friendlist.get(k).getName());
					cFriends.add(user.friendlist.get(i));
					counter++;
				}
			
		System.out.println("--------------------------------------");
		return cFriends;
	}
	
	/* 	The first function prints the friends of the user its called for.
	 * 
	 * 	The second one prints the groups the user has been enrolled in. 
	 */
	
	public void printFriends() {
		
		System.out.println("************************");
		System.out.println("Friends of " + name);
		System.out.println("************************");
		for(int i=0; i<friendlist.size(); i++)
			System.out.println((i+1) + ": " + friendlist.get(i).getName());
		System.out.println("-----------------------");
	}
	
	public void printUserGroups() {
		
		System.out.println("**************************************");
		System.out.println("Groups that " + name + " has been enrolled in");
		System.out.println("**************************************");
		for(int i=0; i<groups.size(); i++)
			System.out.println((i+1) + ": " + groups.get(i).getName());		
		
		System.out.println("--------------------------------------");
	}
	
	/* 	
	 * 	Loops through the friends of the user its called for,
	 * finds their friends' friends and adds them into the
	 * possible carriers list, after they're filtered out
	 * for duplicates. 
	 *  
	 * 	Once that's done, a list with the possible carriers is printed. 
	 */
	
	public ArrayList<User> possibleCarriers() {
		
		ArrayList<User> carriers = new ArrayList<>();
		
		for(int i=0; i<friendlist.size(); i++)
			carriers.add(friendlist.get(i));
		
		for(int i=0; i<friendlist.size(); i++) 
			for(int j=0; j<friendlist.get(i).friendlist.size(); j++)
					if(!friendlist.get(i).friendlist.get(j).getName().equals(this.getName()))
						if(!carriers.contains(friendlist.get(i).friendlist.get(j)))
							carriers.add(friendlist.get(i).friendlist.get(j));
		
		return carriers;
	}
	
	public void printPossibleCarriers(ArrayList<User> infectedU){
		
		System.out.println("*******************************");
		System.out.println(name + " has been infected. The following users have to be tested");
		System.out.println("*******************************");
		
		for(int i=0; i<infectedU.size(); i++)
			System.out.println(infectedU.get(i).getName());
		
		System.out.println("-----------------------------");
	}
	
	/*Adds a post to the user's postList */
	
	public void addPost(Post p) {

		postList.add(p);
		
	}
	
	/* 	
	 * 	Returns an ArrayList, adds the user's posts into it,
	 * and then adds his friends' posts. Once the loops are done,
	 * the ArrayList is then sorted using a date comparator in order
	 * to properly show the posts to the user. 
	 * 
	 * 	Afterwards, we return the ArrayList in order to append it into the GUI.   
	 */
	
	public ArrayList<Post> getPosts() {
		
		ArrayList<Post> sortedL = new ArrayList<Post>();
		
			for(int i=0; i<postList.size(); i++)
				sortedL.add(postList.get(i));
			
			for(int i=0; i<friendlist.size(); i++)
				for(int j=0; j<friendlist.get(i).postList.size(); j++)
					sortedL.add(friendlist.get(i).postList.get(j));
		
		Collections.sort(sortedL, new DateComparator());
			
		return sortedL;
		
	}
	
	/* 	
	 * 	Does almost the same thing as the "possibleCarriers" function, except in this one 
	 * we try to find the user's suggested friends by looping through their friends' friends
	 * and afterwards suggesting whoever isn't friends with the original user. I hope this makes sense. 
	 */
	
	public HashSet<String> suggestedFriends() {
		
		HashSet<String> suggestedFriends = new HashSet<String>();
		
		for(int i=0; i<friendlist.size(); i++) 
			for(int j=0; j<friendlist.get(i).friendlist.size(); j++)
					if(!friendlist.get(i).friendlist.get(j).getName().equals(this.getName()))
						if(!friendlist.contains(friendlist.get(i).friendlist.get(j)))
							suggestedFriends.add(friendlist.get(i).friendlist.get(j).getName());
		
		return suggestedFriends;
	}
}
