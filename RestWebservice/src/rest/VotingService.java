package rest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

@Path("votingservice")

public class VotingService {
				
	@GET

    @Path("hello")   // this define the path to access hello method

    @Produces(MediaType.TEXT_PLAIN)  // this define the resource produce as plain text

public String hello(){

           return "Hello World :)";

    }
	
	public String VoterDatafile = "Voter";
	public ArrayList<Voter> VoterList = new ArrayList<Voter>();
	
	@GET

    @Path("createVoter/{voter_id}") // this define the path to access createVoter method

    @Produces(MediaType.TEXT_PLAIN)// this define the resource produce as plain text

public boolean createVoter(@PathParam("voter_id") String voterid){
        
	    File VoterFile = new File(VoterDatafile);
	   	//Check if the file exists	    
	    if(VoterFile.exists()) {
	    	try {
	    		//read the file using InputStream
	    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(VoterDatafile));
	    		//get the VoterList array from the file
	    		VoterList = (ArrayList<Voter>) inputStream.readObject();
	    		
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	} 	
	    	
	    	for(Voter voter: VoterList) {
	    		//checks if the voterid exists in the database
	    		 if(voter.v_id.equals(voterid)) {
	    			   return false;
	    		   }
	    	}    		    	    	
	    }
	  //assign voterid to voter object 
	    Voter voter = new Voter(voterid);
    	//add voter to the List
    	VoterList.add(voter);
    	
    	try {
    		//get the ObjectOutputStream Object to write the Object to file
    		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(VoterDatafile));
    		//write the VoterList Object to the file
    		outputStream.writeObject(VoterList);
    		outputStream.close();
    		
        } catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
	  return true;

}
	
	public String MotionDatafile = "Motion";
	public ArrayList<Motion> MotionList = new ArrayList<Motion>();
	
	@GET

    @Path("createMotion/{motion_id}/{text}") // this define the path to access createMotion method

    @Produces(MediaType.TEXT_PLAIN)// this define the resource produce as plain text

public boolean createMotion(@PathParam("motion_id") String motionid,@PathParam("text") String motiontext){
        
	    File MotionFile = new File(MotionDatafile); 
	    //check if the file exists
	    if(MotionFile.exists()) {
	    	
	    	try {
	    		//read the data using ObjectInputStream
	    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(MotionDatafile));
	    		//get the motionlist form inputstream object
	    		MotionList = (ArrayList<Motion>) inputStream.readObject();
	    		
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	} 	
	    	
	    	for(Motion motion : MotionList) {
	    		//check if the motion is already exits
	    		if(motion.motion_id.equals(motionid)) {
	    			   return false;
	    		   }
	    	}
	    		    			     		    	    	
	    }
	    //assign motionid to motion object
	    Motion motion = new Motion(motionid,motiontext,0,0);
	    //add motion to the list 
    	MotionList.add(motion);
    	
    	try {
    		//get the ObjectOutputStream Object to write the Object to file
    		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(MotionDatafile));
    		//write the MotionList Object to the file
    		outputStream.writeObject(MotionList);
    		outputStream.close();
    		
        } catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	  return true;

}	
	
	public String VotingRecordDatafile = "VotingRecord";
	public ArrayList<VotingRecord> VotingRecordList = new ArrayList<VotingRecord>();
	
	@GET

    @Path("createVotingRecord/{motion_id}/{voter_id}/{record_id}") // this define the path to access createVotingRecord method

    @Produces(MediaType.TEXT_PLAIN)// this define the resource produce as plain text

public boolean createVotingRecord(@PathParam("motion_id") String motionid,@PathParam("voter_id") String voterid,@PathParam("record_id") String recordid){
        
	    File VotingRecordFile = new File(VotingRecordDatafile);
	   
	    //check if the file exists
	    if(VotingRecordFile.exists()) {
	    	try {
	    		//read the data from the file using ObjectInputStream
	    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(VotingRecordDatafile));
	    		// get the list from the readObject
	    		VotingRecordList = (ArrayList<VotingRecord>) inputStream.readObject();
	    		
	    	} catch (FileNotFoundException e) {
	    	    e.printStackTrace();
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    	    e.printStackTrace();
	    	}	
	    	
	    	for(VotingRecord votingrecord: VotingRecordList) {
	    		//checking the given precondition
	    		if(votingrecord.record_id.equals(recordid) || (votingrecord.voter_id.equals(voterid) && votingrecord.motion_id.equals(motionid))) {
	    			   return false;
	    		   }
	    	}
	    		    		     		    	    	
	    }
	    //create a VotingRecord based on the given values
	    VotingRecord votingrecord = new VotingRecord(recordid,motionid,voterid,false,false);
	   	 //add the VotingRecord to the list
    	VotingRecordList.add(votingrecord);
    	try {
    		//get the objectOutputStream to write the data
    		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(VotingRecordDatafile));
    		//write the list to the file
    		outputStream.writeObject(VotingRecordList);
    		outputStream.close();
    		
        } catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	  return true;

}		

	@GET

    @Path("vote/{voter_id}/{record_id}/{vote}") // this define the path to access vote method

    @Produces(MediaType.TEXT_PLAIN)// this define the resource produce as plain text

public boolean vote(@PathParam("voter_id") String voterid,@PathParam("record_id") String recordid,@PathParam("vote") boolean vote){
        
	    File VotingRecordFile = new File(VotingRecordDatafile);
	    File MotionFile = new File(MotionDatafile);
	    
	    //check if the file exists
	    if(VotingRecordFile.exists()) {
	    	try {
	    		//read the data from the file using ObjectInputStream
	    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(VotingRecordDatafile));
	    		//get the list from the readObject
	    		VotingRecordList = (ArrayList<VotingRecord>) inputStream.readObject();
	    		
	    	} catch (FileNotFoundException e) {
	    	    e.printStackTrace();
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    	    e.printStackTrace();
	    	} 	
	    	
	    	for(VotingRecord votingrecord: VotingRecordList) {
	    		//Checking the preCondtion and vote based on the provided values
		        if(votingrecord.record_id.equals(recordid) && votingrecord.voter_id.equals(voterid) && votingrecord.vote_cast == false) {
		        	votingrecord.vote_cast = true;
		        	votingrecord.voted_yes = vote;
		    	    try {
		    		       //read the file using ObjectoutputStream
		    				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(VotingRecordDatafile));
		    				//write the list data to the file
		    				outputStream.writeObject(VotingRecordList);
		    				outputStream.close();
		    			
		    			} catch (FileNotFoundException e) {
		    				   e.printStackTrace();
		    			} catch (IOException e) {
		    			       e.printStackTrace();
		    		    }
		    	    //update the motion details
			        if(MotionFile.exists()) {
				    	try {
				    		//read the data from the file using ObjectInputStream
				    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(MotionDatafile));
				    		//get the list from the readObject
				    		MotionList = (ArrayList<Motion>) inputStream.readObject();
				    		
				    	} catch (FileNotFoundException e) {
				    	    e.printStackTrace();
				    	} catch (IOException e) {
				    	    e.printStackTrace();
				    	} catch (ClassNotFoundException e) {
				    	    e.printStackTrace();
				    	} 	
				    	
				    for(Motion motion: MotionList)
				    {
				    	if(motion.motion_id.equals(votingrecord.motion_id)) {
				    		motion.votes = motion.votes + 1;
				    		if(vote == true)
				    		motion.in_favour = motion.in_favour + 1;
				    		try {
				    		       //read the file using ObjectoutputStream
				    				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(MotionDatafile));
				    				//write the list data to the file
				    				outputStream.writeObject(MotionList);
				    				outputStream.close();
				    			
				    			} catch (FileNotFoundException e) {
				    				   e.printStackTrace();
				    			} catch (IOException e) {
				    			       e.printStackTrace();
				    		    }
				    		return true;
				    	}
				    }
				    	
			        }
		    			   return true;
		    		}
		        	       
		    	 }
	    	}
	   return false;
	  
}

	@GET

    @Path("clearDatabase") // this define the path to access createDatabase method

    @Produces(MediaType.TEXT_PLAIN)// this define the resource produce as plain text

public boolean clearDatabase(){
        

		 
		try {
			//Clear Voter Database
			PrintWriter VoterFile = new PrintWriter(VoterDatafile);
			VoterFile.close();
			
			 //Clear Motion Database
			PrintWriter MotionFile = new PrintWriter(MotionDatafile);
		    MotionFile.close(); 
		    
		    //Clear VotingRecord Database
		    PrintWriter VotingRecordFile = new PrintWriter(VotingRecordDatafile);
		    VotingRecordFile.close();
		    
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	
	  	
	 	
	  	
	 
	    
	    return true;

}	
		
	@GET

    @Path("getVoter/{voter_id}") // this define the path to access getVoter method

    @Produces(MediaType.APPLICATION_JSON)// this define the resource produce as Application_JSON

public String getVoter(@PathParam("voter_id") String voterid){
	  
	   String VoterDetails = null;
	   File VoterFile = new File(VoterDatafile);
	   //check if the file exists
	   if(VoterFile.exists()) {
		   try {
			   //read the data using the ObjectInputStream
	    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(VoterDatafile));
	    		//get the List from the data
	    		VoterList = (ArrayList<Voter>) inputStream.readObject();
	    		
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	} 
		   
		    for(Voter voter: VoterList) {
		    	 //Check if the voterid exists   
	    		   if(voter.v_id.equals(voterid)) {
	    			   //create a JSON Object from the provided details
	    			   JSONObject VoterJSONObject = new JSONObject();
	    			   VoterJSONObject.put("v_id", voterid);
	    			   //convert the JSONObject to encoded string
	    			   VoterDetails = VoterJSONObject.toString();
	    			   return VoterDetails;
	    		   }
		    }
	    	
	 }
	   	   	   return VoterDetails;
}

	@GET

    @Path("getVotingRecordsForVoter/{voter_id}") // this define the path to access getVotingRecordsForVoter method

    @Produces(MediaType.APPLICATION_JSON)// this define the resource produce as APPLICATION_JSON

public String getVotingRecordsForVoter(@PathParam("voter_id") String voterid){
        
		String VotingRecordDetails = null;
	    File VotingRecordFile = new File(VotingRecordDatafile);
	   	JSONArray VotingRecordJSONArray = new JSONArray();
		JSONObject VotingRecordJSONObject = new JSONObject();
	    //Check if the file exists
	    if(VotingRecordFile.exists()) {
	    	try {
	    		//get the data using ObjectInputStream
	    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(VotingRecordDatafile));
	    		//get the list from the data
	    		VotingRecordList = (ArrayList<VotingRecord>) inputStream.readObject();
	    		
	    	} catch (FileNotFoundException e) {
	    	    e.printStackTrace();
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    	    e.printStackTrace();
	    	}
	    	
	    	for(VotingRecord votingrecord: VotingRecordList) {
	    		//check if the record exists with the provided voterid
		    	if(votingrecord.voter_id.equals(voterid)) {
		    		      //create a JSONObject using the fetched details
		    			 JSONObject VRJSONObj = new JSONObject();
		    			 VRJSONObj.put("vr_id",votingrecord.record_id);
		    			 VRJSONObj.put("m_id",votingrecord.motion_id);
		    			 VRJSONObj.put("v_id",votingrecord.voter_id);
		    			 VRJSONObj.put("vote_cast",votingrecord.vote_cast);
		    			 VRJSONObj.put("voted_yes",votingrecord.voted_yes);
		    			 //adding JSONObject to array
		    			 VotingRecordJSONArray.add(VRJSONObj);
		    		  }
	    	  }
	    	//add the entire JSONArray to the Object
	    	VotingRecordJSONObject.put("VotingRecord", VotingRecordJSONArray);
	    	//convert the JSONObject to string
	    	VotingRecordDetails = VotingRecordJSONObject.toString();
	    }
	    return VotingRecordDetails;
}
	
	@GET

    @Path("getMotion/{motion_id}") // this define the path to access getMotion method

    @Produces(MediaType.APPLICATION_JSON)// this define the resource produce as APPLICATION_JSON

public String getMotion(@PathParam("motion_id") String motionid){
	   
		String MotionDetails = null;
		File MotionFile = new File(MotionDatafile);
	     //Check if the file exists
	   	 if (MotionFile.exists()) {
	   		try {
	   			//get the data using ObjectInputStream
	    		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(MotionDatafile));
	    		//get the list from the data
	    		MotionList = (ArrayList<Motion>) inputStream.readObject();
	    		
	    	} catch (FileNotFoundException e) {
	    		e.printStackTrace();
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	} catch (ClassNotFoundException e) {
	    		e.printStackTrace();
	    	} 
	   		
	   		for(Motion motion: MotionList) {
	   		// check if the motion id exists
	    		   if(motion.motion_id.equals(motionid)) {
	    			   //create a JSONObject using the details
	    			   JSONObject MotionJSONObject = new JSONObject();
	    			   MotionJSONObject.put("m_id", motionid);
	    			   //convert the JSON Object to String
	    			   MotionDetails = MotionJSONObject.toString();
	    			   return MotionDetails;
	    		   }	
	   		}
	    		    	
	    }
	   
	   return MotionDetails;
}
		
}
