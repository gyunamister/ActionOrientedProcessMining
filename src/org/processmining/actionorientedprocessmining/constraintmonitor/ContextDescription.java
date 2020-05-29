package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.Map;
import java.util.Set;

public class ContextDescription {
	Set<String> procSet;
	Set<String> actSet;
	Set<String> resSet;
	Map<String,Set<String>> omap;
	Map<String,Set<String>> vmap;
	
	public ContextDescription(Set<String> procSet, Set<String> actSet, Set<String> resSet, Map<String,Set<String>> omap, Map<String,Set<String>> vmap) {
		this.procSet = procSet;
		this.actSet = actSet;
		this.resSet = resSet;
		this.omap = omap;
		this.vmap= vmap;
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
	
	public Map<String,Set<String>> getOmap(){
		return this.omap;
	}
	
	public Map<String,Set<String>> getVmap(){
		return this.vmap;
	}
	
	public String toString() {
		return "Proc: " + this.procSet + "Act: " + this.actSet + "Res: " + this.resSet + "omap: " + this.omap + "vmap: " + this.vmap;
	}
}
