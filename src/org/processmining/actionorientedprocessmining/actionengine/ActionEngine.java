package org.processmining.actionorientedprocessmining.actionengine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.constraintcube.ConstraintCubeStructure;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstanceStream;
import org.processmining.actionorientedprocessmining.event.TimeMoment;

public class ActionEngine {
	public AEConfig aeConfig;
//	public Map<String,Set<Double>> agg_values = new LinkedHashMap<String,Set<Double>>();
	public String messages = "";

	public ActionEngine(AEConfig aeConfig) {
		this.aeConfig = aeConfig;
//		for(String afName: this.aeConfig.getAFNames()) {
//			agg_values.put(afName, new HashSet<Double>());
//		}
		
	}
	
	public Set<ActionInstance> engine(int currentTime, ConstraintInstanceStream cis, ConstraintCubeStructure ccs){
		Set<ActionInstance> outputs = new HashSet<ActionInstance>();
		if(cis.getCIs().size()!=0) {
			for(Action a : this.aeConfig.getActionSet()) {
				Set<Transaction> trans = new HashSet<Transaction>();
				for(TimeMoment tm: a.getTM()) {
					if(tm.getTime()==currentTime) {
						trans = a.getAF().apply(cis, ccs, tm.getTimeWindow());
					}
				}
				for(Transaction tran:trans) {
					ActionInstance ai = new ActionInstance(a.getAF().afName,tran, currentTime);
					System.out.println(this.execute(ai));
					outputs.add(ai);
				}
			}
		}
		this.recordAI(outputs, currentTime);
		return outputs;
	}




//	public double aggregateInstances(int t, Set<ConstraintInstance> cis, String constr, String agg) {
//		//if null return 0 so that no action can be taken
//		double result = 0.0;
//
//		Set<ConstraintInstance>violated = findViolated(t, cis, constr);
//		Set<String>normal = findNormal(t,constr);
//		if(!violated.isEmpty()) {
//			if(agg.equals("ratio")) {
//				result = ((double) violated.size())/(((double) normal.size()) + ((double) violated.size()));
//			}
//			if(agg.equals("count")) {
//				result = violated.size();
//			}
//
//		}
//		return result;
//	}

//	public ActionInstance generateActionInstance(String action, Transaction tr, int t) {
//		return new ActionInstance(action, tr, t);
//	}

//	public void generateAction(int currentTime, ConstraintInstanceStream cs) {
//		Set<ActionInstance> outputs = new HashSet<ActionInstance>();
//		for(Action a: this.aeConfig.getActionSet()) {
//			Set<Transaction> transSet=new HashSet<Transaction>();
//			for(TimeMoment tm: a.getTM()) {
//				if(tm.getTime()==currentTime) {
//					transSet = a.getAF().apply(cs.getCIS(), tm.getTimeWindow());
//				}
//			}
//		}
//
//	}

//	public Set<ActionInstance> generateInstanceLevelAction(int currentTime) {
//		Set<ActionInstance> outputs = new HashSet<ActionInstance>();
//		String messages ="";
//		for(Action a: this.aeConfig.getActionSet()) {
//			for(TimeMoment tm : a.getTM()) {
//				if(tm.getTime()==currentTime) {
//					ActionFormula af = a.getAF();
//					String output = "nok";
//					String constraintName = af.ccvDescription.get("cf").iterator().next();
//
//
//					//filter constraint instance stream
//					Set<ConstraintInstance> cis = this.cs.cis.stream().filter((x) -> (x.getTime() <= tm.getTimeWindow().getEnd()) && (x.getTime() > Math.max(0,tm.getTimeWindow().getStart()))).collect(Collectors.toSet());
//					//materialize
//					Map<String, Map<String,List<ConstraintInstance>>> matProcessCube = this.mat.materialize(cis,currentTime,af.ccvDescription);
//					boolean isSatisfied=false;
////					System.out.println(currentTime + " " + a.getAF()+ " " +a.getTM());
////					System.out.println(cis);
////					System.out.println(matProcessCube);
//					//Satisfied if action condition is not none - aggregated action
//					if(!af.actionPredicate.equals("empty")) {
//						String agg = af.actionPredicate.split(",")[0];
//						String relational = af.actionPredicate.split(",")[1];
//						double thre = Double.parseDouble(af.actionPredicate.split(",")[2]);
////							(TODO) visualization - calculate it using another function
//						double v = this.aggregateInstances(tm.getTime(), cis,constraintName,agg);
//						Map<String,String> cell = new LinkedHashMap<String,String>();
//						for(String cf: matProcessCube.keySet()) {
//							cell.put("cf", cf);
//							for(String pi: matProcessCube.get(cf).keySet()) {
//								cell.put("Order", pi);
//								isSatisfied=this.as.assess(matProcessCube.get(cf).get(pi), agg, output, relational, thre);
//								if(isSatisfied) {
//									Transaction tr = af.genTrans(cell);
//									ActionInstance ai1 = this.generateActionInstance(af.op, tr, tm.getTime());
//									outputs.add(ai1);
////									String new_message = this.execute(ai1);
////									messages = messages.concat(new_message + "\n");
//								}
//							}
//						}
//						agg_values.get(af.afName).add(v);
//					}else {
//						Map<String,String> cell = new LinkedHashMap<String,String>();
//						for(String cf: matProcessCube.keySet()) {
//							cell.put("cf", cf);
//							for(String pi: matProcessCube.get(cf).keySet()) {
//								cell.put("Order", pi);
//								Transaction tr = af.genTrans(cell);
//								ActionInstance ai1 = this.generateActionInstance(af.op, tr, currentTime);
//								outputs.add(ai1);
//								String new_message = this.execute(ai1);
//								messages = messages.concat(new_message + "\n");
//							}
//						}
//					}
//				}
//				}
//			}
//
//		this.recordAI(outputs, currentTime);
//		return outputs;
//	}

	public String convertWithStream(Map<String, ?> map) {
	    String mapAsString = map.keySet().stream()
	      .map(key -> key + "=" + map.get(key))
	      .collect(Collectors.joining(", ", "{", "}"));
	    return mapAsString;
	}

	public String execute(ActionInstance ai) {
		String new_message = "";
		new_message = ai.afName + " with " + this.convertWithStream(ai.tr.vmap) + " at " + ai.time;
		messages = messages.concat(new_message + "\n");
		if(ai.afName.equals("a1: set higher priority for delayed orders")) {
			//this.alertCaseManager(constraint, info);
//			new_message = "Execute action: " + action;
			new_message = ai.afName + " with " + this.convertWithStream(ai.tr.vmap) + " at " + ai.time;
//			System.out.println(new_message);
//			for( ObjectType pi : simulator.mp.object_Set) {
//				if(pi.object_name.equals(processEntity)) {
//					pi.initialPriority =100;
//				}
//			}
		}
		/*
		else if(action.equals("a2: alert operation director")) {
			this.alertOperationDirector(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a3: alert managing director")) {
			this.alertManagingDirector(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a4: alert case manager with suggestion")) {
			this.alertCaseManagerWithS(constraint, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a5: alert operation director with suggestion")) {
			this.alertOperationDirectorWithS(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a6: alert managing director with suggestion")) {
			this.alertManagingDirectorWithS(constraint, action_condition, info);
			new_message = "Execute action: " + action;
		}else if(action.equals("a7: add resource")) {
			this.addResource();
			new_message = "Execute action: " + action;
		}
		*/
		return new_message;
	}

	public void recordAI(Set<ActionInstance> ais, int currentTime) {
		if(currentTime == 1) {
			String dirName = String.format("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/action-log-%s.csv",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Path file = Paths.get(dirName);
			try {
				String temp="";
				List<String> lines = Arrays.asList(temp);
				Files.write(file, lines, StandardCharsets.UTF_8);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
		for(ActionInstance ai:ais) {
			List<String> lines = Arrays.asList(ai.toString());
			String dirName = String.format("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/action-log-%s.csv",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Path file = Paths.get(dirName);
			try {
				Files.write(file, lines, StandardOpenOption.APPEND);
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}

//	public Set<ConstraintInstance> findViolated(int t, Set<ConstraintInstance> cis, String constr){
//		Set<ConstraintInstance> violated_instances = new HashSet<ConstraintInstance>();
//		if(!cis.isEmpty()) {
//			violated_instances = cis.stream().filter((x) -> "nok"
//			.equals(x.getoutc())).collect(Collectors.toSet());
////			Set<String> nok_wo_dup = new HashSet<String>(nok_w_dup);
////			violated_instances = new HashSet<String>(nok_wo_dup);
//		}
//		return violated_instances;
//	}

//	public Set<String> findNormal(int t, String constr){
//		Set<String> normal_instances = new HashSet<String>();
//		Set<ConstraintInstance> cInstanceSet = this.cs.cis.stream()
//				.filter((x) -> constr.equals(x.getcfName())).collect(Collectors.toSet());
//		if(!cInstanceSet.isEmpty()) {
//			Set<String> ok_w_dup = cInstanceSet.stream().filter((x) -> "ok"
//			.equals(x.getoutc()))
//					.map(ConstraintInstance::getOrder).collect(Collectors.toSet());
//			Set<String> ok_wo_dup = new HashSet<String>(ok_w_dup);
//			normal_instances = new HashSet<String>(ok_wo_dup);
//		}
//		return normal_instances;
//	}
}
