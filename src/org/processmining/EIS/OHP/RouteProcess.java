package org.processmining.EIS.OHP;



public class RouteProcess extends Process {
	
	public Route route;
	
	public RouteProcess(Route r) {
		current_act = "Start"; 
		next_act = "start_route";
		route = r;
	}
	
	public void setControlFlow() {
		control_flow.add("start_route");
		control_flow.add("load_package");
		control_flow.add("deliver_package");
		control_flow.add("unload_package");
		control_flow.add("end_route");
	}
	
	public void proceedStartRoute() {
		current_act = "start_route";
		next_act = "load_package";
	}
	
	public void proceedLoadPackage() {
		current_act = "load_package";
		double random = Math.random();
		if(random<0.9) {
			next_act = "deliver_package";
		}else {
			next_act = "failed_delivery";
			route.delivery_falied=true;
		}
	}
	
	public void proceedDeliverPackage() {
		current_act = "deliver_package";
		next_act = "end_route";
	}
	
	public void proceedFailedDelivery() {
		current_act = "failed_delivery";
		next_act = "unload_package";
	}
	
	public void proceedUnloadPackage() {
		current_act = "unload_package";
		next_act = "end_route";
	}
	
	public void proceedEndRoute() {
		current_act = "end_route";
		if(route.delivery_falied) {
			route.packages.re_route=true;
		}else {
			route.delivery_done=true;
		}
		next_act = "Finish";
	}
	
	public void proceedNextActivity() {
		if(next_act=="start_route") {
			proceedStartRoute();
		}else if(next_act=="load_package") {
			proceedLoadPackage();
		}else if(next_act=="deliver_package") {
			proceedDeliverPackage();
		}else if(next_act=="failed_delivery") {
			proceedFailedDelivery();
		}else if(next_act=="unload_package") {
			proceedUnloadPackage();
		}else if(next_act=="end_route") {
			proceedEndRoute();
		}else {
			finished = true;
		}
	}
}
