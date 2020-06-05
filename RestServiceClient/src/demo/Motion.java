package demo;
import org.codehaus.jackson.annotate.JsonProperty;

public class Motion {
	@JsonProperty("m_id") public String motion_id;
	@JsonProperty("text") public String text;
	@JsonProperty("votes") public int votes;
	@JsonProperty("in_favour") public int in_favour;
}
