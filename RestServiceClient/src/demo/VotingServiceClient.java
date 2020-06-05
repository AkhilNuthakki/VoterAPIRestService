package demo;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;


public class VotingServiceClient {
	//Change this URL to your localhost server URL
	private static final String BASE_URI ="http://localhost:8080/CW3ServerService/rest/votingservice/";
	
	public static void main(String[] args) {
		
		
		 System.out.println("==========================" +"================" );
		 System.out.println("Say Hello!!!");
		 System.out.println("===*****************************************************==============" );
		 System.out.println(getHello());  // hello method call
		 System.out.println();
		 System.out.println("==========================" +"================" );
		 System.out.println("Clear Database");
		 System.out.println("===*****************************************************==============" );
		 //Create 10 Voters
		 System.out.println("Clear database : "+ clearDatabase());
		 System.out.println();
		 System.out.println("==========================" +"================" );
		 System.out.println("Voters Creation");
		 System.out.println("===*****************************************************==============" );
		 for (int i=1;i<=10;i++){
			 String voter ="v"+ Integer.toString(i);
		     System.out.println("Voter ID: "+ voter +" : " +createVoter(voter));
		     System.out.println(getVoter(voter));
		     ObjectMapper mapper1 = new ObjectMapper();
			  try {
				  Voter theVoter = mapper1.readValue(getVoter(voter), Voter.class);
				  System.out.println("Voter "+theVoter.v_id  + " has been created");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 System.out.println();
		 // Create 3 motion
		 List<String> motionList = new ArrayList<String>();
		 System.out.println("==========================" +"================" );
		 System.out.println("Motions Creation");
		 System.out.println("===*****************************************************==============" );
		 for (int i=1;i<=3;i++){
			 String motion_d ="m"+ Integer.toString(i);
			 String motion_text = "motion"+ Integer.toString(i);
			 System.out.println("Motion ID: "+  motion_d +" : " +createMotion(motion_d, motion_text));
			 System.out.println(getMotion(motion_d));
			 ObjectMapper mapper2 = new ObjectMapper();
			  try {
				  Motion theMotion = mapper2.readValue(getMotion(motion_d), Motion.class);
				  System.out.println("Motion "+theMotion.motion_id  + " has been created");
				  motionList.add(theMotion.motion_id);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 System.out.println();
		 System.out.println("==========================" +"================" );
		 System.out.println("Voting Record Creation");
		 System.out.println("===*****************************************************==============" );
		 //String motion_id ="m1", voter_id="v1",record_id="rd1";
		 System.out.println("Create Vrecord : rd1: "+createVotingRecord("m1","v1","rd1"));
		 System.out.println("Create Vrecord : rd2 "+createVotingRecord("m1","v2","rd2"));
		 System.out.println("Create Vrecord : rd3 "+createVotingRecord("m1","v3","rd3"));
		 System.out.println("Create Vrecord : rd4: "+createVotingRecord("m1","v4","rd4"));
		 System.out.println("Create Vrecord : rd5 "+createVotingRecord("m1","v5","rd5"));
		 System.out.println("Create Vrecord : rd6 "+createVotingRecord("m1","v6","rd6"));
		 System.out.println("Create Vrecord : rd7: "+createVotingRecord("m1","v7","rd7"));
		 System.out.println("Create Vrecord : rd8 "+createVotingRecord("m1","v8","rd8"));
		 System.out.println("Create Vrecord : rd9 "+createVotingRecord("m1","v9","rd9"));
		 System.out.println("Create Vrecord : rd10: "+createVotingRecord("m1","v10","rd10"));
		 System.out.println("Create Vrecord : rd11 "+createVotingRecord("m2","v1","rd11"));
		 System.out.println("Create Vrecord : rd12 "+createVotingRecord("m2","v2","rd12"));
		 System.out.println("Create Vrecord : rd13: "+createVotingRecord("m2","v3","rd13"));
		 System.out.println("Create Vrecord : rd14 "+createVotingRecord("m2","v4","rd14"));
		 System.out.println("Create Vrecord : rd15 "+createVotingRecord("m2","v5","rd15"));
		 System.out.println("Create Vrecord : rd16: "+createVotingRecord("m3","v6","rd16"));
		 System.out.println("Create Vrecord : rd17 "+createVotingRecord("m3","v7","rd17"));
		 System.out.println("Create Vrecord : rd18 "+createVotingRecord("m3","v8","rd18"));
		 System.out.println("Create Vrecord : rd19: "+createVotingRecord("m3","v9","rd19"));
		 System.out.println("Create Vrecord : rd20 "+createVotingRecord("m3","v10","rd20"));
		 System.out.println();
		 System.out.println("==========================" +"================" );
		 System.out.println("Voting Starting");
		 System.out.println("===*****************************************************==============" );
		 //Motion 1
		 System.out.println("v1 has voted :  "  + vote("v1","rd1",false));
		 System.out.println("v2 has voted :  "  + vote("v2","rd2",true));
		 System.out.println("v3 has voted :  "  + vote("v3","rd3",false));
		 System.out.println("v4 has voted :  "  + vote("v4","rd4",true));
		 System.out.println("v5 has voted :  "  + vote("v5","rd5",false));
		 System.out.println("v6 has voted :  "  + vote("v6","rd6",true));
		 System.out.println("v7 has voted :  "  + vote("v7","rd7",false));
		 System.out.println("v8 has voted :  "  + vote("v8","rd8",true));
		 System.out.println("v9 has voted :  "  + vote("v9","rd9",true));
		 System.out.println("v10 has voted :  "  + vote("v10","rd10",true));
		 
		 //Motion 2
		 System.out.println("v1 has voted :  "  + vote("v1","rd11",false));
		 System.out.println("v2 has voted :  "  + vote("v2","rd12",true));
		 System.out.println("v3 has voted :  "  + vote("v3","rd13",false));
		 System.out.println("v4 has voted :  "  + vote("v4","rd14",true));
		 System.out.println("v5 has voted :  "  + vote("v5","rd15",true));
		 System.out.println("v6 has voted :  "  + vote("v6","rd16",false));
		 System.out.println("v7 has voted :  "  + vote("v7","rd17",true));
		 System.out.println("v8 has voted :  "  + vote("v8","rd18",false));
		 System.out.println("v9 has voted :  "  + vote("v9","rd19",true));
		 System.out.println("v10 has voted :  "  + vote("v10","rd20",true));
		 System.out.println();
		 System.out.println("==========================" +"================" );
		 System.out.println("Voting Completed");
		 System.out.println("===*****************************************************==============" );
		
		 
		 
		 
		///Solution  
		 // 1. get voters of each motion
		 
		 System.out.println();
		 System.out.println();
		 for(String m: motionList) {
			 System.out.println();
			 System.out.println("==========================" +"================" );
			 System.out.println(" Calculating Total votes and  votes in favour for "+m);
			 System.out.println("===*****************************************************==============" );
			 int total_VoteCast =0,total_votesInfavor=0,totalvotes_against=0;
			 String pickMotion = m;
			 
			 List<String>  votersList = new ArrayList<String>();
			 votersList.add("v1");
			 votersList.add("v2");
			 votersList.add("v3");
			 votersList.add("v4");
			 votersList.add("v5");
			 votersList.add("v6");
			 votersList.add("v7");
			 votersList.add("v8");
			 votersList.add("v9");
			 votersList.add("v10");
			 // for each voter in the list of voters for that motion
			 System.out.println("Voters-List "+ votersList );
			 for (String v :votersList){
				  
				  String record =getVotingRecordsForVoter(v.trim());  // .trim remove leading and trailing spaces the may result from split

				  
				  System.out.println("Voter- "+  v.trim() +" : " + record);
					
				  ObjectMapper mapper = new ObjectMapper();
					  try {
						  RecordList V_record = mapper.readValue(record, RecordList.class);
						List<VotingRecord> V =  V_record.VotingRecord1;
						for (VotingRecord vr : V){
							if(vr.motion_id.equals(pickMotion)) {
								if(vr.vote_cast)	total_VoteCast++; 
								  if(vr.voted_yes){total_votesInfavor++;}
								  if(vr.vote_cast && !vr.voted_yes){totalvotes_against++;}
							}
								  
								
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
			 }
			 
			 System.out.println("Motion: " + m +", Total Votes: " + total_VoteCast+ ", Votes in Favour: " + total_votesInfavor);
		 }
		 
		// submit result
		 //System.out.println(submitResults(pickMotion,total_VoteCast,total_votesInfavor)); 
				 
	}
	
	public static int totalVoteCast() {
		
		return 0;
		
	}
	
	
	public static String getHello() {    

	       Client client = ClientBuilder.newClient();

	        WebTarget target =client.target(BASE_URI+MessageFormat.format("hello",new Object[] {}));

	        return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class); //resquest TEXT_PLAIN String as defined in the Server side

	 }
	

	public static String createVoter(String voter_id){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("createVoter/{0}",new Object[] {voter_id}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);

		
		
	}
	
	public static String createMotion(String motion_id,String motion_text){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("createMotion/{0}/{1}",new Object[] {motion_id,motion_text}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);
	}
	
	public static String createVotingRecord(String motion_id,String voter_id,String record_id){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("createVotingRecord/{0}/{1}/{2}",new Object[] {motion_id,voter_id,record_id}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);
	}
	
	
	
	
	
	public static String vote(String voter_id,String record_id,boolean vote_yes){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("vote/{0}/{1}/{2}",new Object[] {voter_id,record_id,vote_yes}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);
	}
	
	
	
	public static String getVotingRecordsForVoter(String voter_id){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("getVotingRecordsForVoter/{0}",new Object[] {voter_id}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);
	}
	
	public static String getVoter(String voter_id){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("getVoter/{0}",new Object[] {voter_id}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);
	}
	
	public static String getMotion(String motion_d){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("getMotion/{0}",new Object[] {motion_d}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);
	}
	
	public static String clearDatabase(){
		Client client = ClientBuilder.newClient();
	    

	     WebTarget target = client.target(BASE_URI+MessageFormat.format("clearDatabase",new Object[] {}));
	     //resquest APPLICATION_JSON String as  defined in the Server side
	     return target.request(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN,MediaType.TEXT_HTML, MediaType.TEXT_XML, MediaType.TEXT_PLAIN).get(String.class);
	}
			
}
