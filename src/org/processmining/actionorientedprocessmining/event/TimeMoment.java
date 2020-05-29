package org.processmining.actionorientedprocessmining.event;

public class TimeMoment {
	public int t;
	public TimeWindow tw;
	
	public TimeMoment(int time, TimeWindow timeWindow) {
		this.t=time;
		this.tw=timeWindow;
	}
	
	public int getTime() {
		return this.t;
	}
	
	public TimeWindow getTimeWindow() {
		return this.tw;
	}
	
	public String toString() {
		return this.t + " " + this.tw; 
	}
}
