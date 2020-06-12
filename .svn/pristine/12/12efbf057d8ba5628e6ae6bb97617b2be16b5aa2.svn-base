package org.processmining.EIS.OHP;

public class Item extends ObjectType {
	//public ItemProcess process;
	
	public Item(ObjectType o1, String name, int t, double skipProb, double repeatProb) {
		this.order = (Order)o1;
		object_name = name;
		available_at = t;
		this.setProcess(new ItemProcess(skipProb,repeatProb));
	}
	
	public void setProcess(ItemProcess p) {
		process = p;
	}
}
