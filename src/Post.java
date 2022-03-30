import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/* This class basically only servers as a date format and a getter for our variables*/ 

public class Post implements Serializable{
	
	private String text;
	private String name;
	private Date date = new Date();
	private SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
	
	public Post(String name, String text, Date date) {
		this.text = text;
		this.name = name;
	}

	public String getDate() {
		return ft.format(date);
	}

	public String getName() {
		return name;
	}
	
	public String getText() {
		return text;
	}
	
}
