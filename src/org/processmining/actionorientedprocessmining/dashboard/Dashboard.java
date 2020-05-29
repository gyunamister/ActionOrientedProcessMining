package org.processmining.actionorientedprocessmining.dashboard;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.constraintmonitor.CMConfig;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstance;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstanceStream;
public class Dashboard {
	public Map<String,List<Double>> vMap;
	public ConstraintInstanceStream cs;
	public Set<String> constraintNames;
	public String agg;
	
	
	public Dashboard(CMConfig cmConfig, ConstraintInstanceStream cs) {
		this.vMap = new LinkedHashMap<String,List<Double>>();
		this.cs = cs;
		this.constraintNames = cmConfig.getCFNames();
		this.agg="count";
		for(String c:this.constraintNames) {
			vMap.put(c, new ArrayList<Double>());
		}
	}
	
	public void updateVMap(int t) {
		for(String c:this.constraintNames) {
			double resultAtT = this.aggCIs(this.cs.cis, t, c, this.agg);
			this.vMap.get(c).add(resultAtT);
		}
	}
	
	public double aggCIs(Set<ConstraintInstance> cis, int t, String constr, String agg) {
		double result = 0.0;
		Set<ConstraintInstance>violated = findViolated(cis, t, constr);
		if(!violated.isEmpty()) {
			if(agg.equals("count")) {
				result = violated.size();
			}
		}
		return result;
	}
	
	public Set<ConstraintInstance> findViolated(Set<ConstraintInstance> cis, int t, String constr){
		Set<ConstraintInstance> violated_instances = new HashSet<ConstraintInstance>();
		Set<ConstraintInstance> cInstanceList = cis.stream() 
				.filter((x) -> constr.equals(x.getcfName()) && t==Integer.valueOf(x.getTime())).collect(Collectors.toSet());
		if(!cInstanceList.isEmpty()) {
			List<ConstraintInstance> nok_w_dup = cInstanceList.stream().filter((x) -> "nok"
			.equals(x.getoutc())).collect(Collectors.toList());
//			Set<String> nok_wo_dup = new HashSet<String>(nok_w_dup);			
			violated_instances = new HashSet<ConstraintInstance>(nok_w_dup);
		}
		return violated_instances;
	}
}
