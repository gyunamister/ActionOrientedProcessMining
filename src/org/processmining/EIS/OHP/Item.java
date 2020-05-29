package org.processmining.EIS.OHP;

public class Item extends ObjectType {
	//public ItemProcess process;
	
	public Item(ObjectType o1, String name, int t) {
		this.order = (Order)o1;
		object_name = name;
		available_at = t;
		setProcess(new ItemProcess());
	}
	
	public void setProcess(ItemProcess p) {
		process = p;
	}
}
