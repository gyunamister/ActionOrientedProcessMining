package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.processmining.actionorientedprocessmining.event.TimeMoment;
import org.processmining.actionorientedprocessmining.event.TimeWindow;
import org.processmining.actionorientedprocessmining.constraintmonitor.Constraint;

public class CMConfig {
	Set <Constraint> ConstraintSet;
	int timeLimit;
	
	public CMConfig(int limit) {
		ConstraintSet = new HashSet<Constraint>();
		this.timeLimit = limit;
	} 
	
	public void addConstraint(ConstraintFormula cf, int offSet, int interval, TimeWindow tw) {
		List<TimeMoment> TM;
		TM = this.generateTM(offSet, interval, tw);
		Constraint c = new Constraint(cf,TM);
		this.ConstraintSet.add(c);
		System.out.println(c.getCF().cfName + "\n" + TM);
	}
	
	public List<TimeMoment> generateTM(int offSet, int interval, TimeWindow tw) {
		List<TimeMoment> TM = new ArrayList<TimeMoment>();
		int i=0;
		while(i+offSet<this.timeLimit) {
			int time = i+offSet;
			TimeWindow timeWindow = new TimeWindow(time+tw.getStart(),time+tw.getEnd());
			TimeMoment tm = new TimeMoment(time,timeWindow);
			i=i+interval;
			TM.add(tm);
		}
		return TM;
	}
	
	public Set<Constraint> getConstraintSet(){
		return this.ConstraintSet;
	}
	
	public Set<String> getCFNames(){
		Set<String> cfNames = new HashSet<String>();
		for(Constraint c : this.ConstraintSet) {
			cfNames.add(c.getCF().cfName);
		}
		return cfNames;
	}
}
