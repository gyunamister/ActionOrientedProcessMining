package org.processmining.actionorientedprocessmining.test;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.actionorientedprocessmining.actionengine.AEConfig;
import org.processmining.actionorientedprocessmining.actionengine.ActionFormula;
import org.processmining.actionorientedprocessmining.constraintmonitor.CMConfig;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintFormula;
import org.processmining.actionorientedprocessmining.constraintmonitor.ContextDescription;
import org.processmining.actionorientedprocessmining.event.TimeWindow;
import org.processmining.actionorientedprocessmining.models.AOPMProgram;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main {
	public static void main (String[] args) throws JsonParseException, JsonMappingException, IOException{		
		
		CMConfig cmConfig = new CMConfig(10000);
		Map<String, Object> constraint = new LinkedHashMap<String, Object>();
		
		String cfName = "Delivery in 72 hours";
		Set<String> procSet = new HashSet<String>();
		Set<String> actSet = new HashSet<String>();
		Set<String> resSet = new HashSet<String>();
		Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
		Map<String,Set<String>> vmap = new LinkedHashMap<String,Set<String>>();
		Set<String> objSet = new HashSet<String>();
		objSet.add("foreach");
		omap.put("Order", objSet);
		ContextDescription ctxdesc = new ContextDescription(procSet,actSet,resSet,omap,vmap);
		String predicate = "\"Throughput\",<,72";
		ConstraintFormula cf = new ConstraintFormula(cfName, ctxdesc, predicate);
		constraint.put(cfName, cf);
		cmConfig.addConstraint(cf, 9, 24, new TimeWindow(-24,0));
//		
//		String cfName2 = "Don't skip check availability";
//		Set<String> procSet2 = new HashSet<String>();
//		Set<String> actSet2 = new HashSet<String>();
//		Set<String> resSet2 = new HashSet<String>();
//		Map<String,Set<String>> omap2 = new LinkedHashMap<String,Set<String>>();
//		Map<String,Set<String>> vmap2 = new LinkedHashMap<String,Set<String>>();
//		Set<String> objSet2 = new HashSet<String>();
//		objSet2.add("foreach");
//		omap2.put("Item", objSet2);
//		ContextDescription ctxdesc2 = new ContextDescription(procSet2,actSet2,resSet2,omap2,vmap2);
//		String predicate2 = "\"Existence\", \"check_availability\"";
//		ConstraintFormula cf2 = new ConstraintFormula(cfName2, ctxdesc2, predicate2);
//		constraint.put(cfName2, cf2);
//		cmConfig.addConstraint(cf2, 24, 24, new TimeWindow(-24,0));
		
//		String cfName3 = "Efficient item packing";
//		Set<String> procSet3 = new HashSet<String>();
//		procSet3.add("order-handling-process");
//		Set<String> actSet3 = new HashSet<String>();
//		actSet3.add("pack_items");
//		Set<String> resSet3 = new HashSet<String>();
//		Map<String,Set<String>> omap3 = new LinkedHashMap<String,Set<String>>();
//		Map<String,Set<String>> vmap3 = new LinkedHashMap<String,Set<String>>();
//		ContextDescription ctxdesc3 = new ContextDescription(procSet3,actSet3,resSet3,omap3,vmap3);
//		String predicate3 = "\"Execution\", <, 2";
//		ConstraintFormula cf3 = new ConstraintFormula(cfName3, ctxdesc3, predicate3);
//		constraint.put(cfName3, cf3);
//		cmConfig.addConstraint(cf3, 24, 24, new TimeWindow(-24,0));
		
//		Map<String, Set<String>> ccvDescription = new LinkedHashMap<String, Set<String>>();
		AEConfig aeConfig = new AEConfig(10000);
		String afName = "a1: set higher priority for delayed orders";
		Map<String, Set <String>> ccvDescription = new LinkedHashMap<String, Set <String>>();
		Set<String> cfs = new HashSet<String>();
		cfs.add("Delivery in 72 hours");
		ccvDescription.put("cf", cfs);
		Set<String> orders = new HashSet<String>();
		orders.add("foreach");
		ccvDescription.put("Order", orders);
		Set<String> timeSet = new HashSet<String>();
		timeSet.add("all");
		ccvDescription.put("time", timeSet);
		String actionPredicate = "count,>,0";
		String operation = "Set higher priority";
		Map<String, String> pmap = new LinkedHashMap<String, String>();
		pmap.put("target", "Order");
		ActionFormula af = new ActionFormula(afName, ccvDescription, actionPredicate, operation, pmap);
		aeConfig.addAction(af, 24, 24, new TimeWindow(-24,0));

		String afName2 = "a2: alert case manager for the delayed orders";
		Map<String, Set <String>> ccvDescription2 = new LinkedHashMap<String, Set <String>>();
		Set<String> cfs2 = new HashSet<String>();
		cfs2.add("Delivery in 72 hours");
		ccvDescription2.put("cf", cfs2);
		Set<String> orders2 = new HashSet<String>();
		orders2.add("foreach");
		ccvDescription2.put("Order", orders2);
		Set<String> timeSet2 = new HashSet<String>();
		timeSet2.add("all");
		ccvDescription2.put("time", timeSet2);
		String actionPredicate2 = "count,>,1";
		String operation2 = "Send an email to the case manager";
		Map<String, String> pmap2 = new LinkedHashMap<String, String>();
		pmap2.put("target", "Order");
		ActionFormula af2 = new ActionFormula(afName2, ccvDescription2, actionPredicate2, operation2, pmap2);
		aeConfig.addAction(af2, 24, 24, new TimeWindow(-48,0));
		
		ProcessSimulation pSim = new ProcessSimulation();
		String AISdir = "/Users/GYUNAM/Documents/ActionOrientedProcessMining/output";
		boolean record = false;
		AOPMProgram sm = new AOPMProgram(cmConfig,aeConfig,pSim,AISdir);
		for(int t=0;t<720;t++) {
			pSim.simulateProcess(t,record);
			sm.simulateWithIS(t,record);
		}
	}
}
