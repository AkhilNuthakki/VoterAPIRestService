package demo;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class RecordList {
	@JsonProperty("VotingRecord")
   public List<VotingRecord> VotingRecord1 ;

	public RecordList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
