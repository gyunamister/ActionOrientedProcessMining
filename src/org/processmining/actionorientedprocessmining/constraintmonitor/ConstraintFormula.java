package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.event.Event;
import org.processmining.actionorientedprocessmining.event.Filter;
import org.processmining.actionorientedprocessmining.event.TimeWindow;

public class ConstraintFormula {
	public String cfName;
	public ContextDescription ctxdesc;
	public String filter;
	public String predicate;
	public Filter ft = new Filter();
	public Derivation dv = new Derivation();
	public Evaluation evaluator = new Evaluation();
	
	public ConstraintFormula(String cfName, ContextDescription ctxdesc, String predicate) {
		this.ft = new Filter();
		this.cfName = cfName;
		this.ctxdesc = ctxdesc;
		this.predicate = predicate;
	}
	
	public Set<EvalResult> apply(Set<Event> eventSet,TimeWindow tw){
		Set<EvalResult> results = new HashSet<EvalResult>();
		Set<Context> ctxSet = this.genContextSet(eventSet,tw);
		for(Context ctx:ctxSet) {
			String outcome = this.eval(eventSet, tw, ctx);
			EvalResult evalResult = new EvalResult(ctx,outcome);
			results.add(evalResult);
		}
		return results;
	}
	
	public Set<Context> genContextSet(Set<Event> eventSet, TimeWindow tw){
		Set<Context> ctxSet = new HashSet<Context>();
		Set<Event> twFtEventSet = eventSet.stream().filter((x) -> (x.getTimestamp() <= tw.getEnd()) && (x.getTimestamp() >= Math.max(0,tw.getStart()))).collect(Collectors.toSet());
		Set<Set<String>> procSetCollection = new HashSet<Set<String>>();
		Set<Set<String>> actSetCollection = new HashSet<Set<String>>();
		Set<Set<String>> resSetCollection = new HashSet<Set<String>>();
		Set<Map<String,Set<String>>> objectMapCollection = new HashSet<Map<String,Set<String>>>();
		
		if(this.ctxdesc.getProcSet().contains("foreach")) {
			Set<String> temp = twFtEventSet.stream().map(Event::getProcess).collect(Collectors.toSet());
			for(String p:temp) {
				Set<String> procSet = new HashSet<String>(); 
				procSet.add(p);
				procSetCollection.add(procSet);
			}
		}else {
			procSetCollection.add(this.ctxdesc.getProcSet());
		}
		if(this.ctxdesc.getActSet().contains("foreach")) {
			Set<String> temp = twFtEventSet.stream().map(Event::getAct).collect(Collectors.toSet());
			for(String p:temp) {
				Set<String> actSet = new HashSet<String>(); 
				actSet.add(p);
				actSetCollection.add(actSet);
			}
		}else {
			actSetCollection.add(this.ctxdesc.getActSet());
		}
		if(this.ctxdesc.getResSet().contains("foreach")) {
			Set<String> temp = twFtEventSet.stream().map(Event::getRes).collect(Collectors.toSet());
			for(String p:temp) {
				Set<String> resSet = new HashSet<String>(); 
				resSet.add(p);
				resSetCollection.add(resSet);
			}
		}else {
			resSetCollection.add(this.ctxdesc.getResSet());
		}
//		Assume foreach is allowed for just one property
		for(String object: this.ctxdesc.getOmap().keySet()) { 
			if(this.ctxdesc.getOmap().get(object).contains("foreach")) {
				Set<Map<String,Set<String>>> temp = twFtEventSet.stream().map(Event::getOmap).collect(Collectors.toSet());
				Set<String> objectSet = new HashSet<String>();
				for(Map<String,Set<String>> omap : temp) {
					objectSet.addAll(omap.get(object));
				}
				for(String o:objectSet) {
					Set<String> tempObjectSet = new HashSet<String>(); 
					tempObjectSet.add(o);
					Map<String,Set<String>> tempOmap = new LinkedHashMap<String,Set<String>>(); 
					tempOmap.put(object,tempObjectSet);
					objectMapCollection.add(tempOmap);
				}
			}else if(this.ctxdesc.getOmap().get(object).contains("forall")) {
				Set<Map<String,Set<String>>> temp = twFtEventSet.stream().map(Event::getOmap).collect(Collectors.toSet());
				Set<String> objectSet = new HashSet<String>();
				for(Map<String,Set<String>> omap : temp) {
					objectSet.addAll(omap.get(object));
				}
				Map<String,Set<String>> tempOmap = new LinkedHashMap<String,Set<String>>(); 
				tempOmap.put(object,objectSet);
				objectMapCollection.add(tempOmap);
			}else {
				objectMapCollection.add(this.ctxdesc.getOmap());
			}
		}
		
		for(Set<String> p : procSetCollection) {
			for(Set<String> a:actSetCollection) {
				for(Set<String> r: resSetCollection) {
					if(objectMapCollection.size()==0) {
						Context ctx = new Context(p,a,r,new LinkedHashMap<String,Set<String>>(),new LinkedHashMap<String,Set<String>>());
						ctxSet.add(ctx);
					}else {
						for(Map<String,Set<String>> o : objectMapCollection) {
							Context ctx = new Context(p,a,r,o,new LinkedHashMap<String,Set<String>>());
							ctxSet.add(ctx);
						}
					}
				}
			}
		}
		return ctxSet;
	}
	
	public String eval(Set<Event> eventSet, TimeWindow tw, Context ctx){
		//We simply assume that we already have predefined functions. So, what we need to is just to call the function.
		String outcome; 
		
		String cleanedPredicate = this.predicate.replace("\"", "");
		cleanedPredicate = cleanedPredicate.replace(" ", "");
		String [] parsedPredicate = cleanedPredicate.split(",");
		String funtionName = parsedPredicate[0];
		if(funtionName.equals("Throughput")) {
			String comp=parsedPredicate[1];
			int thres=Integer.parseInt(parsedPredicate[2]);
			outcome = this.evaluator.evalThroughput(comp, thres, eventSet, tw, ctx);
		}else if(funtionName.equals("Execution")) {
			String comp=parsedPredicate[1];
			int thres=Integer.parseInt(parsedPredicate[2]);
			outcome = this.evaluator.evalExecution(comp, thres, eventSet, tw, ctx);
		}else if(funtionName.equals("Existence")) {
			String targetAct = parsedPredicate[1];
			outcome = this.evaluator.evalBoundedExistence(targetAct,1, eventSet, tw, ctx);
		}else if(funtionName.equals("Non-Existence")) {
			String targetAct = parsedPredicate[1];
			outcome = this.evaluator.evalBoundedNonExistence(targetAct,0, eventSet, tw, ctx);
		}else if(funtionName.equals("Bounded-Existence")) {
			String targetAct = parsedPredicate[1];
			int numExecution = Integer.valueOf(parsedPredicate[2]);
			outcome = this.evaluator.evalBoundedExistence(targetAct,numExecution, eventSet, tw, ctx);
		}else if(funtionName.equals("Bounded-Non-Existence")) {
			String targetAct = parsedPredicate[1];
			int numExecution = Integer.valueOf(parsedPredicate[2]);
			outcome = this.evaluator.evalBoundedNonExistence(targetAct,numExecution, eventSet, tw, ctx);
		}else if(funtionName.equals("Sequence-Existence")) {
			String precedingAct = parsedPredicate[1];
			String targetAct = parsedPredicate[2];
			outcome = this.evaluator.evalBoundedDependentExistence(precedingAct,targetAct,0,1,eventSet, tw, ctx);
		}else if(funtionName.equals("Sequence-Non-Existence")) {
			String precedingAct = parsedPredicate[1];
			String targetAct = parsedPredicate[2];
			outcome = this.evaluator.evalBoundedDependentNonExistence(precedingAct,targetAct,0,0,eventSet, tw, ctx);
		}else if(funtionName.equals("Dependent-Existence")) {
			String precedingAct = parsedPredicate[1];
			String targetAct = parsedPredicate[2];
			int seqLength = Integer.valueOf(parsedPredicate[3]);
			outcome = this.evaluator.evalBoundedDependentExistence(precedingAct,targetAct,seqLength,0,eventSet, tw, ctx);
		}else if(funtionName.equals("Dependent-Non-Existence")) {
			String precedingAct = parsedPredicate[1];
			String targetAct = parsedPredicate[2];
			int seqLength = Integer.valueOf(parsedPredicate[3]);
			outcome = this.evaluator.evalBoundedDependentNonExistence(precedingAct,targetAct,seqLength,0,eventSet, tw, ctx);
		}else if(funtionName.equals("Bounded-Dependent-Existence")) {
			String precedingAct = parsedPredicate[1];
			String targetAct = parsedPredicate[2];
			int seqLength = Integer.valueOf(parsedPredicate[3]);
			int numExecution = Integer.valueOf(parsedPredicate[4]);
			outcome = this.evaluator.evalBoundedDependentExistence(precedingAct,targetAct,seqLength,numExecution,eventSet, tw, ctx);
		}else if(funtionName.equals("Bounded-Non-Dependent-Existence")) {
			String precedingAct = parsedPredicate[1];
			String targetAct = parsedPredicate[2];
			int seqLength = Integer.valueOf(parsedPredicate[3]);
			int numExecution = Integer.valueOf(parsedPredicate[4]);
			outcome = this.evaluator.evalBoundedDependentNonExistence(precedingAct,targetAct,seqLength,numExecution,eventSet, tw, ctx);
		}else if(funtionName.equals("Precedence")) {
			String precedingAct = parsedPredicate[1];
			String conditionAct = parsedPredicate[2];
			int numExecution = Integer.valueOf(parsedPredicate[3]);
			outcome = this.evaluator.evalPrecedence(precedingAct,conditionAct,numExecution,eventSet, tw, ctx);
		}else if(funtionName.equals("Object-Capacity")) {
			String oc = parsedPredicate[1];
			String comp=parsedPredicate[2];
			int thres=Integer.parseInt(parsedPredicate[3]);
			outcome = this.evaluator.evalCapacity(oc, comp, thres, eventSet, tw, ctx);
		}else {
			outcome="undefined";
			System.out.println("At this constraint formula, " + "Constraint Not Defined");
		}
		
		return outcome;
	}
	
	public String toString() {
		return this.ctxdesc + this.filter + this.predicate;
	}
}
