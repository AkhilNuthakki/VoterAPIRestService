package rest;
import java.io.Serializable;

public class VotingRecord implements Serializable {
	public String record_id;
	public String motion_id;
	public String voter_id;
	public boolean vote_cast;
	public boolean voted_yes;
	public VotingRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VotingRecord(String record_id,String motion_id,String voter_id,boolean vote_cast,boolean voted_yes) {
		this.record_id = record_id;
		this.motion_id = motion_id;
		this.voter_id = voter_id;
		this.vote_cast = vote_cast;
		this.voted_yes = voted_yes;
	}
	 
}