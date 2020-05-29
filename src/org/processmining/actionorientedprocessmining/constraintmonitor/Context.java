package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.Map;
import java.util.Set;

public class Context {
	Set<String> procSet;
	Set<String> actSet;
	Set<String> resSet;
	Map<String,Set<String>> omap;
	Map<String,Set<String>> vmap;
	
	public Context(Set<String> procSet, Set<String> actSet, Set<String> resSet, Map<String,Set<String>> omap, Map<String,Set<String>> vmap) {
		this.procSet = procSet;
		this.actSet = actSet;
		this.resSet = resSet;
		this.omap = omap;
		this.vmap = vmap;
	}
	
	public Set<String> get(String d){
		if(d.equals("Proc")) {
			return this.procSet;
		}else if(d.equals("Act")) {
			return this.actSet;
		}else if(d.equals("Res")) {
			return this.resSet;
		}else {
			return this.omap.get(d);
		}
	}
	
	public Set<String> getProcSet(){
		return this.procSet;
	}
	
	public Set<String> getActSet(){
		return this.actSet;
	}
	
	public Set<String> getResSet(){
		return this.resSet;
	}
	
	public Map<String,Set<String>> getOmap() {
		return this.omap;
	}
	
	public Map<String,Set<String>> getVmap() {
		return this.vmap;
	}
	
	public String toString() {
		String p = this.procSet.toString();
		String a = this.actSet.toString();
		String r = this.actSet.toString();
		String o = this.omap.toString();
		String v = this.vmap.toString();
		return p + a + r + o + v;
	}
}
