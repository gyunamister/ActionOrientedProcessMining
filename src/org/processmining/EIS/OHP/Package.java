package org.processmining.EIS.OHP;

import java.util.List;

public class Package extends ObjectType {
	//public PackageProcess process;
	public List<Item> items;
	public boolean delivery_prepared;
	public boolean delivery_prep_done;
	public int num_fail = 0;
	public boolean re_route;
	public boolean delivery_done;
	
	public Package(String name, List<Item> i, int t) {
		this.order = i.get(0).order;
		object_name = name;
		items = i;
		available_at = t;
		delivery_prepared=false;
		re_route=false;
		setProcess(new PackageProcess());
	}
	
	public void setProcess(PackageProcess p) {
		//define process
		process = p;
	}
}
