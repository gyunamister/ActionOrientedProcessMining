package org.processmining.actionorientedprocessmining.constraintcube;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstance;

public class ConstraintCubeStructure {
	Set<ConstraintInstance> cis;
	Set<String> dim;
	Set<String> defaultDim;
	Set<String> objectDim;
	Set<String> attrDim;
	public Map<String,Set<String>> elem;
	public Map<String,Set<Set<String>>> hier;
	
	public ConstraintCubeStructure(Set<ConstraintInstance> cis, Set<String> defaultProp, Set<String> objectProp, Set<String> attrProp) {
		this.cis = cis;
		this.setDim(defaultProp, objectProp, attrProp);
		
		this.elem = new LinkedHashMap<String,Set<String>>();
		for(String defaultName:this.defaultDim) {
			this.elem.put(defaultName, new LinkedHashSet<String>());
		}
		for(String objName:this.objectDim) {
			this.elem.put(objName, new LinkedHashSet<String>());
		}
		for(String attrName:this.attrDim) {
			this.elem.put(attrName, new LinkedHashSet<String>());
		}
		
		this.hier = new LinkedHashMap<String,Set<Set<String>>>();
		for(String defaultName:this.defaultDim) {
			this.hier.put(defaultName, new LinkedHashSet<Set<String>>());
		}
		for(String objName:this.objectDim) {
			this.hier.put(objName, new LinkedHashSet<Set<String>>());
		}
		for(String attrName:this.attrDim) {
			this.hier.put(attrName, new LinkedHashSet<Set<String>>());
		}
	}
	
	public void setDim(Set<String> defaultProp, Set<String> objectProp, Set<String> attrProp) {
		this.defaultDim = defaultProp;
		this.objectDim = objectProp;
		this.attrDim=attrProp;
		this.dim = new LinkedHashSet<String>();
		this.dim.addAll(defaultDim);
		this.dim.addAll(objectDim);
		this.dim.addAll(attrDim);
	}
	
	public Set<String> getDim(){
		return this.dim;
	}
	
	public void updateElem(int t) {
		Set<ConstraintInstance> tempCIS = this.cis.stream().filter(x-> Integer.valueOf(x.getTime())==t).collect(Collectors.toSet());
		for(ConstraintInstance ci : tempCIS) {
			this.elem.get("cf").add(ci.getcfName());
			for(String proc : ci.getContext().getProcSet()) {
				this.elem.get("proc").add(proc);
			}
			for(String act : ci.getContext().getActSet()) {
				this.elem.get("act").add(act);
			}
			for(String res : ci.getContext().getResSet()) {
				this.elem.get("res").add(res);
			}
			this.elem.get("time").add(ci.getTime());
			for(String objName:this.objectDim) {
				if(ci.getContext().getOmap().get(objName)!=null) {
					for(String obj:ci.getContext().getOmap().get(objName)) {
						this.elem.get(objName).add(obj);
					}
				}
			}
			for(String attrName:this.attrDim) {
				for(String attr:ci.getContext().getVmap().get(attrName)) {
					this.elem.get(attrName).add(attr);
				}
			}
		}
	}
	
	public void updateHier(int t) {
		Set<ConstraintInstance> tempCIS = this.cis.stream().filter(x-> Integer.valueOf(x.getTime())==t).collect(Collectors.toSet());
		for(ConstraintInstance ci : tempCIS) {
			Set<String> cfSet = new LinkedHashSet<String>();
			cfSet.add(ci.getcfName());
			this.hier.get("cf").add(cfSet);
			for(String proc : ci.getContext().getProcSet()) {
				Set<String> procSet = new LinkedHashSet<String>();
				procSet.add(proc);
				hier.get("proc").add(procSet);
			}
			for(String act : ci.getContext().getActSet()) {
				Set<String> actSet = new LinkedHashSet<String>();
				actSet.add(act);
				hier.get("act").add(actSet);
			}
			for(String res : ci.getContext().getResSet()) {
				Set<String> resSet = new LinkedHashSet<String>();
				resSet.add(res);
				hier.get("res").add(resSet);
			}
			Set<String> timeSet = new LinkedHashSet<String>();
			timeSet.add(ci.getTime());
			hier.get("time").add(timeSet);
			
			for(String objName:this.objectDim) {
				if(ci.getContext().getOmap().get(objName)!=null) {
					for(String obj:ci.getContext().getOmap().get(objName)) {
						Set<String> objSet = new LinkedHashSet<String>();
						objSet.add(obj);
						this.hier.get(objName).add(objSet);
					}
				}
			}
			
			for(String attrName:this.attrDim) {
				if(ci.getContext().getVmap().get(attrName)!=null) {
					for(String attr:ci.getContext().getVmap().get(attrName)) {
						Set<String> attrSet = new LinkedHashSet<String>();
						attrSet.add(attr);
						this.hier.get(attrName).add(attrSet);
					}
				}
			}
		}
	}

}
