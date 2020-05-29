package org.processmining.actionorientedprocessmining.constraintmonitor;

import java.util.Set;

public class ConstraintInstance {
	public String cfName;
	public Context ctx;
	public String outc;
	public String time;
	
	public ConstraintInstance(String cfName, Context ctx, int t, String outcome) {
		this.cfName = cfName;
		this.ctx = ctx;
		this.outc = outcome;
		this.time = Integer.toString(t);
	}
	
	public String get(String d) {
		if(d.equals("cf")) {
			return this.cfName;
		}else if(d.equals("outc")) {
			return this.outc;
		}else if(d.equals("time")) {
			return this.time;
		}else {
			return "Not defined";
		}
	}
	
	public Set<String> getCtx(String d){
		return this.ctx.get(d);
	}
	
	public ConstraintInstance getThis() {
		return this;
	}
	
	public String getcfName() {
		return this.cfName;
	}
	
	public Context getContext() {
		return this.ctx;
	}
	
	public String getoutc() {
		return this.outc;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String getOrder() {
		return this.ctx.getOmap().get("Order").iterator().next();
	}
	
	public String getNull() {
		return "null";
	}
	
	public Set<String> projectContext(String d){
		if(d=="proc") {
			return this.ctx.getProcSet();
		}else if(d=="act") {
			return this.ctx.getActSet();
		}else if(d=="res") {
			return this.ctx.getResSet();
		}else {
			return this.ctx.getOmap().get(d);
		}
	}
	
	public String toString() {
//		return this.cfName + "," + this.ctx + "," + this.time + "," + this.outc;
		return this.ctx.getOmap().get("Order").iterator().next() + "," + this.time + "," + this.outc;
	}
}
