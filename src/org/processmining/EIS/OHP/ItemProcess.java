package org.processmining.EIS.OHP;

public class ItemProcess extends Process {
	public ItemProcess() {
		current_act = "Start";
		double random = Math.random();
		if(random<0.99) {
			next_act = "check_availability";
		}else {
			next_act = "pick_item";
		}
	}
	
	public void setControlFlow() {
		control_flow.add("check_availability");
		control_flow.add("pick_item");
	}
	
	public void proceedCheckAvailability() {
		current_act = "check_availability";
		next_act = "pick_item";
		//available_at = available_at + 1;
				
	}
	
	public void proceedPickItem() {
		current_act = "pick_item";
		next_act = "Finish";
		//available_at = available_at + 1;
	}
	
	public void proceedNextActivity() {
		if(next_act=="check_availability") {
			proceedCheckAvailability();
		}else if(next_act=="pick_item") {
			proceedPickItem();
		}else {
			finished = true;
		}
	}
}
