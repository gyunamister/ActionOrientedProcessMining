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
					String new_message = ai.afName + " with " + this.convertWithStream(ai.tr.vmap) + " at " + ai.time;
					this.messages = this.messages.concat(new_message + "\n");
					outputs.add(ai);
				}
			}
		}
		this.recordAI(outputs, currentTime);
		return outputs;
	}

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
