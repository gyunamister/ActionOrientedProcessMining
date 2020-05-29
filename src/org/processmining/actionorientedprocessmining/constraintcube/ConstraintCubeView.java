package org.processmining.actionorientedprocessmining.constraintcube;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstance;
import org.processmining.actionorientedprocessmining.event.TimeWindow;

public class ConstraintCubeView {
	ConstraintCubeStructure ccs;
	Map<String, Set<String>> ccvDesc;
	Set<String> selDim;
	public Map<String,Set<Set<String>>> sel;
	
	public ConstraintCubeView(ConstraintCubeStructure ccs,Map<String, Set <String>> ccvDesc) {
		this.ccs=ccs;
		sel = new LinkedHashMap<String,Set<Set<String>>>();
		selDim = new LinkedHashSet<String>();
		for(String d:ccvDesc.keySet()) {
			selDim.add(d);
//			(TODO) at the moment, assume that we have exactly one value in each dimension
			Set<Set<String>> temp = new LinkedHashSet<Set<String>>();
			if(ccvDesc.get(d).contains("foreach")) {
				temp = ccs.hier.get(d);
			}
			
			temp.add(ccvDesc.get(d));
			sel.put(d,temp);
		}
	}
	
	public void sliceTime(TimeWindow tw) {
		String d = "time";
		Set<Set<String>> timeHier = new LinkedHashSet<Set<String>>();
		Set<String> temp = new LinkedHashSet<String>();
		for(Set<String> v : ccs.hier.get(d)) {
			for(String s: v) {
				int time = Integer.valueOf(s);
				if(time>tw.getStart() && time<=tw.getEnd()) {
					temp.add(s);
				}
			}
		}
		timeHier.add(temp);
		sel.put(d, timeHier);
	}
	
	public Set<CubeCell> genCubeCells() {
		List<CubeCell> cubeCellList = new ArrayList<CubeCell>();
//		Map<String, Set<String>> cubeCell = new LinkedHashMap<String, Set<String>>();
		
		int size=1;
		for(String d : this.sel.keySet()) {
			size = size*this.sel.get(d).size();
		}
		for(int i=0;i<size;i++) {
			Map<String, Set<String>> temp = new LinkedHashMap<String, Set<String>>();
			CubeCell cc = new CubeCell(temp);
			cubeCellList.add(cc);
		}
		
		//change sel into list to use indexing
		for(String d : this.sel.keySet()) {
			int dsize=this.sel.get(d).size();
			int repetition = size/dsize;
			int index=0;
			for(Set<String> v: this.sel.get(d)) {
				for(int i=index;i<repetition+index;i++) {
					cubeCellList.get(i).setDim(d,v);
					
				}
				index += repetition;
			}
		}
		Set<CubeCell> cubeCellSet = cubeCellList.stream().collect(Collectors.toSet());
		return cubeCellSet;
	}
	
	public Map<CubeCell,Set<ConstraintInstance>> materialize(Set<ConstraintInstance> cis){
		Map<CubeCell,Set<ConstraintInstance>> mat = new LinkedHashMap<CubeCell,Set<ConstraintInstance>>();
		Set<CubeCell> cubeCellSet = this.genCubeCells();
		for(CubeCell cc:cubeCellSet) {
			 Set<ConstraintInstance> inst = cis.stream().filter(x -> cc.contains(x)).collect(Collectors.toSet());
			 mat.put(cc, inst);
		}
		return mat;
	}
	
	public Map<String,Set<Set<String>>> getSel() {
		return this.sel;
	}
	
	public String toString() {
		return "" + this.sel;
	}
}
