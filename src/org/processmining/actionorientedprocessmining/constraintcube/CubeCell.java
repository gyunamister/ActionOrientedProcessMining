package org.processmining.actionorientedprocessmining.constraintcube;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstance;

public class CubeCell {
	Map<String,Set<String>> elem;
	
	public CubeCell(Map<String, Set<String>> elem) {
		this.elem = elem;
	}
	
	public void setDim(String d, Set<String> v) {
		this.elem.put(d, v);
	}
	
	public Set<String> getValues(String d){
		return elem.get(d);
	}
	
	public Map<String,Set<String>> getElem(){
		return this.elem;
	}
	
	public boolean contains(ConstraintInstance ci) {
		Set<String> singleElemTypeSet = new LinkedHashSet<String>();
		singleElemTypeSet.add("cf");
		singleElemTypeSet.add("time");
		singleElemTypeSet.add("outc");
		
		for(String d:elem.keySet()) {
			if(singleElemTypeSet.contains(d)) {
				if(this.getValues(d).contains(ci.get(d))==false) {
					return false;
				}
			}else {
				if(this.getValues(d).containsAll(ci.getCtx(d))==false) {
					return false;
				};
			}
		}
		return true;
	}
	
	public String toString() {
		return "" + elem;
	}
}
