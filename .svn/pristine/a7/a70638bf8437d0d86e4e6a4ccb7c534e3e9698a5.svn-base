package org.processmining.actionorientedprocessmining.actionengine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.processmining.actionorientedprocessmining.event.TimeMoment;
import org.processmining.actionorientedprocessmining.event.TimeWindow;

public class AEConfig {
	Set <Action> ActionSet;
	int timeLimit;
	
	public AEConfig(int limit) {
		ActionSet = new HashSet<Action>();
		this.timeLimit = limit;
	} 
	
	public void addAction(ActionFormula af, int offSet, int interval, TimeWindow tw) {
		List<TimeMoment> TM;
		TM = this.generateTM(offSet, interval, tw);
		Action a = new Action(af,TM);
		this.ActionSet.add(a);
//		System.out.println(a.getAF().afName + "\n" + TM);
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
	
	public Set<Action> getActionSet(){
		return this.ActionSet;
	}
	
	public Set<String> getAFNames(){
		Set<String> afNames = new HashSet<String>();
		for(Action a : this.ActionSet) {
			afNames.add(a.getAF().afName);
		}
		return afNames;
	}
}
