package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ConstraintInstanceStream {
	public Table<Object, Object, Object> table = HashBasedTable.create();
	public Set<ConstraintInstance> cis = new LinkedHashSet<ConstraintInstance>(); 
	
	public void addInstances(Set<ConstraintInstance> ci) {
		this.cis.addAll(ci);
	}
	
	public Set<ConstraintInstance> getCIs(){
		return this.cis;
	}
	
}
