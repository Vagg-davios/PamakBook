import java.io.Serializable;
import java.util.ArrayList;

public class ClosedGroup extends Group implements Serializable{
	private ArrayList<User> closedGroupMembers;
	
	public ClosedGroup(String name, String description) {
		super(name,description);
		closedGroupMembers = new ArrayList<>();
	}
	
	/* 	
	 * 	Adds the user to the group, but with a few filters. 
	 * 
	 *  If the user is the first one to join the group, join without any restrictions.
	 *  
	 *  If not, the loop checks if the user wanting to be added is friends with any of the
	 * members in the group. If they are indeed friends with anyone, and aren't already in the group,
	 * they get added, otherwise they do not.
	 */
	
	public void addMember(User user) {
		
		if(closedGroupMembers.size()<1)	{
			super.addMember(user);
			closedGroupMembers.add(user);
		}
		else 
			for(int i=0; i<closedGroupMembers.size(); i++) 
				if(!user.equals(closedGroupMembers.get(i)))
						if(user.checkFriendship(closedGroupMembers.get(i).getName())) {
							super.addMember(user);
							closedGroupMembers.add(user);
						}
			if(!closedGroupMembers.contains(user))
				System.out.println("FAILED: " + user.getName() + " cannot be enrolled in group " + name);
		
	}
}
		



