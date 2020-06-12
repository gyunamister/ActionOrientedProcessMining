package org.processmining.EIS.OHP;

public class Route extends ObjectType {
	//public RouteProcess process;
	public Package packages;
	public boolean delivery_done = false;
	public boolean delivery_falied=false;
	
	public Route(String name, Package p, int t) {
		this.order = p.order;
		this.object_name = name;
		this.packages = p;
		this.packages.re_route=false;
		available_at = t;
		this.setProcess(new RouteProcess(this));
	}
	
	public void setProcess(RouteProcess p) {
		//define process
		process = p;
	}
}
