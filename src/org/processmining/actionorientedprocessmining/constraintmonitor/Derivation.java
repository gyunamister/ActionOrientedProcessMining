package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.HashSet;
import java.util.Set;

import org.processmining.actionorientedprocessmining.event.Event;

public class Derivation {
		//Data ACQ
		public double calcThroughputTime(Set<Event> sublog,int twEnd) {
			for(Event e: sublog) {
				if(e.getAct().equals("deliver_package")) {
					return 0;
				}
			}
			Set<Integer> times = new HashSet<Integer>();
			for(Event e : sublog) {			
				times.add(e.getTimestamp());
			}
			int max=0;
			for(Integer i : times) {
				if(i>max) {
					max= i;
				}
			}
			int min=10000000;
			for(Integer i : times) {
				if(i<min) {
					min= i;
				}
			}
			double throughput_time = twEnd-min;
			return throughput_time;
		}
		
		public double calcExecutionTime(Set<Event> sublog) {
			Set<Integer> times = new HashSet<Integer>();
			
			for(Event e : sublog) {			
				times.add(e.getTimestamp());
			}
			double averageTime = this.calculateAverage(times);
			return averageTime;
		}
		
		private double calculateAverage(Set <Integer> marks) {
			  Integer sum = 0;
			  if(!marks.isEmpty()) {
			    for (Integer mark : marks) {
			        sum += mark;
			    }
			    return sum.doubleValue() / marks.size();
			  }
			  return sum;
		}
		
		public double calcProcessCapacity(Set<Event> sublog, String oc) {
			Set<String> objectSet = new HashSet<String>();
			
			for(Event e : sublog) {			
				objectSet.addAll(e.getOmap().get(oc));
			}
			return new Double(objectSet.size());
		}
		
		
	
}
