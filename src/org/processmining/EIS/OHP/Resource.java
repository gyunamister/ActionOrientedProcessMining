package org.processmining.EIS.OHP;

import java.util.HashMap;
import java.util.Map;

public class Resource {
	public int duration;
	public String resource_name;
	public int available_at;
	public Map<String,Integer> tasks = new HashMap<String,Integer>();
	public String status;
	
	public Resource(String name) {
		this.resource_name = name;
		this.duration = 1;
		//this.tasks = t;
		this.status = "ready_to_assign";
		this.available_at = 0;
	}
	
	public void setTask(String task_name, int duration) {
		this.tasks.put(task_name, duration);
	}
	
	public void setStatus(String s) {
		this.status = s;
	}
	
	public String toString() {
		return this.resource_name;
	}
}
