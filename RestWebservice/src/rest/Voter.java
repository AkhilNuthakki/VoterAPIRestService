package rest;
import java.io.Serializable;

public class Voter implements Serializable {
	public String v_id;
	
	public Voter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Voter(String v_id) {
		this.v_id = v_id;
	}
}