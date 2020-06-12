package org.processmining.actionorientedprocessmining.actionengine;

import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstance;

public class Assessment {
	public boolean assess(Set<ConstraintInstance> cInstanceList, String agg, String relational, double thre) {
		boolean isSatisfied=false;
		double aggResult = aggregateCIs(cInstanceList, agg);
//		value_list.add(aggResult);
		if(relational.equals("=")) {
			if(aggResult==thre) {
//				System.out.println("Alert manager that " + value + "=" + thre);
				isSatisfied=true;
			}
		}else if(relational.equals(">")) {
			if(aggResult>thre) {
//				System.out.println("Alert manager that " + value + ">" + thre);
				isSatisfied=true;
			}
		}else if(relational.equals("<")) {
			if(aggResult<thre) {
//				System.out.println("Alert manager that " + value + "<" + thre);
				isSatisfied=true;
			}
		}
		return isSatisfied;
	}
	
	public double aggregateCIs(Set<ConstraintInstance> cInstanceList, String agg) {
		Set<ConstraintInstance> filteredCInstanceList = cInstanceList.stream().filter((x) -> x.getoutc().equals("nok")).collect(Collectors.toSet());
		//if null return 0 so that no action can be taken
		double result = 0.0;
		if(!cInstanceList.isEmpty()) {
			if(agg.equals("ratio")) {
				result = ((double) filteredCInstanceList.size())/(((double) cInstanceList.size()) + ((double) filteredCInstanceList.size()));
			}
			if(agg.equals("count")) {
				result = filteredCInstanceList.size();
			}
			
		}
		return result;
	}
}
