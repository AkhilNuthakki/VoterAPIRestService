package rest;
import java.io.Serializable;

public class Motion implements Serializable{
	public String motion_id;
	 public String text;
	 public int votes;
	public int in_favour;
	
	public Motion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Motion(String motion_id,String text,int votes, int in_favour) {
		this.motion_id = motion_id;
		this.text = text;
		this.votes = votes;
		this.in_favour = in_favour;
	}
}
