package org.processmining.actionorientedprocessmining.actionengine;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.processmining.actionorientedprocessmining.constraintcube.ConstraintCubeStructure;
import org.processmining.actionorientedprocessmining.constraintcube.ConstraintCubeView;
import org.processmining.actionorientedprocessmining.constraintcube.CubeCell;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstance;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstanceStream;
import org.processmining.actionorientedprocessmining.event.TimeWindow;

public class ActionFormula {
	public String afName;
	public Map<String, Set<String>> ccvDescription;
	public String actionPredicate;
	public String op;
	public Map<String, String> pmap;
	ConstraintCubeStructure ccs;
	ConstraintCubeView ccv;
	Assessment as;
	
	public ActionFormula(String afName, Map<String, Set<String>> ccvDescription, String actionPredicate, String op, Map<String, String> pmap) {
		this.afName=afName;
		if(actionPredicate!=null) {
			this.actionPredicate = actionPredicate;
		}else {
			this.actionPredicate = "empty";
		}
		this.ccvDescription=ccvDescription;
		this.op = op;
		this.pmap = pmap;
		this.as = new Assessment();
	}
	
	public Set<Transaction> apply(ConstraintInstanceStream cis, ConstraintCubeStructure ccs,TimeWindow tw){
		Set<Transaction> trans = new HashSet<Transaction>();
		ConstraintCubeView ccv = new ConstraintCubeView(ccs,this.ccvDescription);
		ccv.sliceTime(tw);
		Map<CubeCell,Set<ConstraintInstance>> mat = ccv.materialize(cis.getCIs());
		for(CubeCell cc: mat.keySet()) {
			Set<ConstraintInstance> ciSet = mat.get(cc);
			if(this.assess(ciSet)) {
				Set<Transaction> tempTrans = this.genTrans(cc);
				trans.addAll(tempTrans);
			}
		}
		
		return trans;
	}
	
	public boolean assess(Set<ConstraintInstance> ciSet) {
		boolean result = false;
		String c = this.actionPredicate;
		if(c.contains(">") | c.contains("=") | c.contains("<")) {
			String valueName=c.split(",")[0];
			String comp=c.split(",")[1];
			int thres=Integer.parseInt(c.split(",")[2]);
			result=this.as.assess(ciSet, valueName, comp, thres);
		}
		return result;
	}
	
	public String toString() {
		return this.afName + this.ccvDescription + this.actionPredicate + this.op + this.pmap;
	}
	
	public Set<Transaction> genTrans(CubeCell cc){
		Set<Transaction> trans = new HashSet<Transaction>();
		for(Entry<String, String> param : this.pmap.entrySet()) {
			for(String v:cc.getValues(this.pmap.get(param.getKey()))) {
				Map<String,String> vmap = new LinkedHashMap<String,String>();
				vmap.put(param.getKey(), v);
				Transaction tr = new Transaction(this.op,vmap);
				trans.add(tr);
			}
		}
		return trans;
	}
	

	public String getAction() {
		return this.op;
	}
}
