package org.processmining.EIS.OHP;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjectType {
	// Variable
	public Process process;
	public int available_at;
	public int initialPriority=0;
	public int priority;
	public String object_name;
	public int prev_sojourn_time;
	public Order order;
	// Status: ready_to_assign, work_in_process, finished
	public String status;
	// History: historical events
	public List<String> history = new ArrayList<String>();
	
	public String getObjectName() {
		return object_name;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	public void setHistory(String e) {
		history.add(e);
	}
	
	public String toString() {
		return object_name ;
	}
	
	public void setAvailableAt(int t) {
		available_at=t;
	}
	/*
	public void setItems(ArrayList<Item> i) {
		items=i;
	}
	*/
	
}
