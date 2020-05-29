package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.processmining.actionorientedprocessmining.event.Event;
import org.processmining.actionorientedprocessmining.event.Filter;
import org.processmining.actionorientedprocessmining.event.TimeWindow;

public class Evaluation {
	public Derivation dv = new Derivation();
	public Filter ft = new Filter();
	
	//Evaluate
	public String evalRelational(double a, String relation, double b) {
		String result = "ok";
		if(relation.equals(">")) {
			if(a<=b) {
				result = "nok";
			}
		}else if(relation.equals("=")) {
			if(a!=b) {
				result = "nok";
			}
		}else if(relation.equals("<")) {
			if(a>=b) {
				result = "nok";
			}
		}
		return result;
	}
	
	public String evalThroughput(String comp, int thres, Set<Event> eventSet, TimeWindow tw, Context ctx) {
		Set<Event> ec = this.ft.objectFilter(eventSet, tw, ctx);
		double val = this.dv.calcThroughputTime(ec,tw.getEnd());
		
		String outcome = this.evalRelational(val,comp,thres);
		return outcome;
	}
	
	public String evalExecution(String comp, int thres, Set<Event> eventSet, TimeWindow tw, Context ctx) {
		Set<Event> ec = this.ft.eventFilter(eventSet, tw, ctx);
		double val = this.dv.calcExecutionTime(ec);
		
		String outcome = this.evalRelational(val,comp,thres);
		return outcome;
	}
	
	public String evalBoundedExistence(String targetAct, int numExecution, Set<Event> eventSet, TimeWindow tw, Context ctx) {
		Set<Event> ec = this.ft.objectFilter(eventSet, tw, ctx);
//		String targetAct = predicate.split(",")[1];
//		int numExecution = Integer.valueOf(predicate.split(",")[2]);
		Set<Event> targetEventSet = new HashSet<Event>();
		for(Event e:ec) {
			if(e.getAct().equals(targetAct)) {
				targetEventSet.add(e);
			}
		}
		String outcome = "ok";
		if(targetEventSet.size()<numExecution) {
			outcome = "nok";
		}
		return outcome;
	}
	
	public String evalBoundedNonExistence(String targetAct, int numExecution, Set<Event> eventSet, TimeWindow tw, Context ctx) {
		Set<Event> ec = this.ft.objectFilter(eventSet, tw, ctx);
//		String targetAct = predicate.split(",")[1];
//		int numExecution = Integer.valueOf(predicate.split(",")[2]);
		Set<Event> targetEventSet = new HashSet<Event>();
		for(Event e:ec) {
			if(e.getAct().equals(targetAct)) {
				targetEventSet.add(e);
			}
		}
		String outcome = "ok";
		if(targetEventSet.size()>numExecution) {
			outcome = "nok";
		}
		return outcome;
	}
	
	public String evalBoundedDependentExistence(String precedingAct, String targetAct, int seqLength, int numExecution, Set<Event> eventSet, TimeWindow tw, Context ctx) {
		Set<Event> ec = this.ft.objectFilter(eventSet, tw, ctx);
//		String precedingAct = predicate.split(",")[1];
//		String targetAct = predicate.split(",")[2];
//		int seqLength = Integer.valueOf(predicate.split(",")[3]);
//		int numExecution = Integer.valueOf(predicate.split(",")[4]);
		List<Event> eventList = new ArrayList<Event>(ec);
		eventList.sort(Comparator.comparing(Event::getTimestamp));
		List<Integer> precActIndex = new ArrayList<Integer>();
		List<Integer> actIndex = new ArrayList<Integer>();
		for(Event e : eventList) {
			if(e.getAct().equals(precedingAct)) {
				precActIndex.add(eventList.indexOf(e));
			}else if(e.getAct().equals(targetAct)) {
				actIndex.add(eventList.indexOf(e));
			}
		}
		String outcome = "ok";
		//in case # of the target activity is more than # of the preceding activity: always violated
		if(precActIndex.size()<actIndex.size()) {
			return "nok";
		}
		//in case # of the preceding activity is more than # of the target activity: okay if target activities are later than the matching preceding activity. Others will happen or not
		else if(precActIndex.size()>=actIndex.size()) {
			if(actIndex.size() > numExecution) {
				return "nok";
			}
			for(int j=0; j<actIndex.size();j++) {
				if(precActIndex.get(j)+seqLength>actIndex.get(j)) {
					return "nok";
				}
			}
		}
 
		
		return outcome;
	}
	
	public String evalBoundedDependentNonExistence(String precedingAct, String act, int seqLength, int numExecution, Set<Event> eventSet, TimeWindow tw, Context ctx) {
		Set<Event> ec = this.ft.objectFilter(eventSet, tw, ctx);
//		String precedingAct = predicate.split(",")[1];
//		String act = predicate.split(",")[2];
//		int seqLength = Integer.valueOf(predicate.split(",")[3]);
//		int numExecution = Integer.valueOf(predicate.split(",")[4]);
		List<Event> eventList = new ArrayList<Event>(ec);
		eventList.sort(Comparator.comparing(Event::getTimestamp));
		List<Integer> precActIndex = new ArrayList<Integer>();
		List<Integer> actIndex = new ArrayList<Integer>();
		for(Event e : eventList) {
			if(e.getAct().equals(precedingAct)) {
				precActIndex.add(eventList.indexOf(e));
			}else if(e.getAct().equals(act)) {
				actIndex.add(eventList.indexOf(e));
			}
		}
		String outcome = "ok";
		//in case # of the target activity is more than # of the preceding activity: always violated
		if(precActIndex.size()<actIndex.size()) {
			return "nok";
		}
		//in case # of the preceding activity is more than # of the target activity: okay if target activities are later than the matching preceding activity. Others will happen or not
		else if(precActIndex.size()>=actIndex.size()) {
			if(actIndex.size() > precActIndex.size()+numExecution) {
				return "nok";
			}
			for(int j=0; j<actIndex.size();j++) {
				if(precActIndex.get(precActIndex.size()-1-j)+seqLength>actIndex.get(j)) {
					return "nok";
				}
			}
		}
 
		
		return outcome;
	}
	//(TODO) now only can consider the first occurrence of the condition activity
	public String evalPrecedence(String precedingAct, String conditionAct, int numExecution, Set<Event> eventSet, TimeWindow tw, Context ctx) {
		Set<Event> ec = this.ft.objectFilter(eventSet, tw, ctx);
//		String precedingAct = predicate.split(",")[1];
//		String conditionAct = predicate.split(",")[2];
//		int numExecution = Integer.valueOf(predicate.split(",")[3]);
		List<Event> eventList = new ArrayList<Event>(ec);
		eventList.sort(Comparator.comparing(Event::getTimestamp));
		Set<Event> precedingEventSet = new HashSet<Event>();
		int conditionIndex = -1;
		for(Event e : eventList) {
			if(e.getAct().equals(conditionAct)) {
				conditionIndex = eventList.indexOf(e);
			}
		}
		if(conditionIndex!=-1) {
			for(int i=0;i<conditionIndex;i++) {
				if(eventList.get(i).getAct().equals(precedingAct)) {
					precedingEventSet.add(eventList.get(i));
				}
			}
		}
		String outcome = "ok";
		if(precedingEventSet.size()>numExecution) {
			outcome = "nok";
		}
		return outcome;
	}
	
}
