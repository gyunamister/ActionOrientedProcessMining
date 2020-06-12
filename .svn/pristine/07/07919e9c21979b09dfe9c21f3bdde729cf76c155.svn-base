package org.processmining.actionorientedprocessmining.event;

import java.util.Map;
import java.util.Set;

public class Event {
	String e;
	String proc;
	String act;
	String res;
	int completeTimestamp;
	Map<String,Set<String>> omap;
	
	public Event(String e, String process, String act, String res, int complete, Map<String,Set<String>> omap) {
		this.e = e;
		this.proc = process;
		this.act = act;
		this.res = res;
		this.omap = omap;
		this.completeTimestamp = complete;
	}
	
	public String getEventID() {
		return this.e;
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
		return this.completeTimestamp;  
	}
	
	public Map<String,Set<String>> getOmap() {
		return this.omap;  
	}
	
	public String toString() {
		return this.e + ", " + this.proc + ", " + this.act + ", " + this.res + ", " + this.omap + ", " + this.completeTimestamp; 
	}
}
