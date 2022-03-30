import java.util.Comparator;

public class DateComparator implements Comparator<Post> {

		/* 	
		 * 	The name of the class says it all, it essentially overrides
		 * the compareTo function using the Post object and then getters
		 * to make the comparison. 
		 */
	    public int compare(Post o1, Post o2) 
	    {
	        return (o2.getDate()).compareTo(o1.getDate());
	    }
	}