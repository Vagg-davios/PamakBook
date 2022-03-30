import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable{
	
	protected String name;
	protected String description;
	private ArrayList<User> groupMembers;

	public Group(String name, String description) {
		this.name = name;
		this.description = description;
		groupMembers = new ArrayList<User>();
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public ArrayList<User> getMembers(){
		return groupMembers;
	}
	
	/* Checks if the user in the parameter is a member of the group, if so, it returns true. */ 
	
	public boolean checkIfMember(User user) {
		
		boolean flag = false;
		
		for(int i=0; i<groupMembers.size(); i++)
			if(user.equals(groupMembers.get(i)))
				flag = true;
		
		return flag;
	}
	
	/*	
	 * 	Adds the user to the group member list,
	 * and then adds the group to the user's group list. 
	 */
	
	public void addMember(User user) {
		
		if(!checkIfMember(user)) {
			groupMembers.add(user);
			System.out.println(user.getName() + " has been successfully enrolled in group " + name);
		}
		user.setGroups(this);
	}
	
	/* Prints the members of a group. */
	
	public void printGroupMembers() {
		System.out.println("*******************************");
		System.out.println("Members of group " + name);
		System.out.println("*******************************");
		for(int i=0; i<groupMembers.size(); i++) 
			System.out.println((i+1) + ": " + groupMembers.get(i).getName()); 
		
		System.out.println("-----------------------------");
	}
	
}
