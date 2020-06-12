package org.processmining.EIS.OHP;

import java.util.Map;
import java.util.Set;

public class OrderHandlingEvent {
	String e;
	String proc;
	String act;
	String res;
	int completeTimestamp;
	Map<String,Set<String>> omap;
	
	public OrderHandlingEvent(String e, String process, String act, String res, int complete, Map<String,Set<String>> omap) {
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
		String s = this.e + "," + this.proc + "," + this.act + "," + this.res + "," + this.completeTimestamp;
		Object[] keys = this.omap.keySet().toArray();
		for(Object key : keys) {
			s+="," + this.omap.get(key);
		}
		return s;
	}
}
