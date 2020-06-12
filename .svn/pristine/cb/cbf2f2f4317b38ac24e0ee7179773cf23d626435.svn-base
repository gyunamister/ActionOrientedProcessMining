package org.processmining.EIS.OHP;

public class ItemProcess extends Process {
	double skipProb;
	double repeatProb;
	
	
	public void setSkipProb(int skipProb) {
		this.skipProb=skipProb;
	}
	
	public void setRepeatProb(int repeatProb) {
		this.repeatProb=repeatProb;
	}
	
	public ItemProcess(double skipProb, double repeatProb) {
		this.skipProb=skipProb;
		this.repeatProb = repeatProb;
		
		current_act = "Start";
		double random = Math.random();
		if(random<this.skipProb) {
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
		double random = Math.random();
		if(random<this.repeatProb) {
			next_act = "pick_item";
		}else {
			next_act = "check_availability";
		}
		//available_at = available_at + 1;
				
	}
	
	public void proceedPickItem() {
		current_act = "pick_item";
		next_act = "Finish";
		//available_at = available_at + 1;
	}
	
	public void proceedNextActivity() {
		if(next_act.equals("check_availability")) {
			proceedCheckAvailability();
		}else if(next_act.equals("pick_item")) {
			proceedPickItem();
		}else {
			finished = true;
		}
	}
}
