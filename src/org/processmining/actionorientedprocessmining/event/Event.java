package org.processmining.actionorientedprocessmining.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Event {
	String eventID;
	String proc;
	String act;
	String res;
	String timestamp;
	Map<String,Set<String>> omap;
	
	public Event() {
		
	}
	
	public Event(String eventID, String process, String act, String res, String complete, Map<String,Set<String>> omap) {
		this.eventID = eventID;
		this.proc = process;
		this.act = act;
		this.res = res;
		this.omap = omap;
		this.timestamp = complete;
	}
	
	public String getEventID() {
		return this.eventID;
	}
	
	public String getProcess() {
		return this.proc;  
	}
	
	public String getAct() {
		return this.act;  
	}
	
	public String getRes() {
		return this.res;  
	}
	
	public int getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date theDate;
		long timestampInMinutes = 0;
		try {
			theDate = sdf.parse(this.timestamp);;
			timestampInMinutes = theDate.getTime() / 60000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int output = Math.toIntExact(timestampInMinutes);
		
		return output;  
	}
	
	public Map<String,Set<String>> getOmap() {
		return this.omap;  
	}
	
	public String toString() {
		return "Event ID: " + this.eventID + ", \n" + "Process: " + this.proc + ", \n" + "Activity: " + this.act + ", \n" + "Resource: " + this.res + ", \n" + "Omap: " + this.omap + ", \n" + "Timestamp: " + this.timestamp; 
	}
}
