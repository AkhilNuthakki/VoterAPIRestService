package demo;
import org.codehaus.jackson.annotate.JsonProperty;

public class VotingRecord {
	@JsonProperty("vr_id") public String record_id;
	@JsonProperty("m_id") public String motion_id;
	@JsonProperty("v_id") public String voter_id;
	@JsonProperty("vote_cast") public boolean vote_cast;
	@JsonProperty("voted_yes") public boolean voted_yes;
	public VotingRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
}
