package org.processmining.EIS.OHP;

import java.util.ArrayList;
import java.util.List;

public class Order extends ObjectType{
	//public OrderProcess process;
	public boolean order_placed;
	public boolean pack_prepared;
	public boolean pack_done;
	public List<Item> items = new ArrayList<Item>();
	
	public Order(String name, int t) {
		this.order = this;
		object_name = name;
		available_at = t;
		setProcess(new OrderProcess(this));
		order_placed=false;
		pack_prepared=false;
		pack_done=false;
	}
	
	public void setProcess(OrderProcess p) {
		//define process
		process = p;
	}
	
	public void setItems(List<Item> i) {
		items = i;
	}
	
	public void setPackPrepared(boolean b) { 
		pack_prepared = b;
		System.out.println(object_name + " is prepared");
	}
		
}
