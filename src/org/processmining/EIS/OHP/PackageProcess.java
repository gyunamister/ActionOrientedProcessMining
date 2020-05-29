package org.processmining.EIS.OHP;

public class PackageProcess extends Process {
	
	public PackageProcess() {
		current_act = "Start";
		next_act = "pack_items";
	}
	
	public void setControlFlow() {
		control_flow.add("pack_items");
		control_flow.add("store_package");
		control_flow.add("load_package");
		control_flow.add("deliver_package");
		control_flow.add("unload_package");
	}
	
	public void proceedPackItem() {
		current_act = "pack_items";
		next_act = "store_package";
	}
	
	public void proceedStorePackage() {
		current_act = "store_package";
		next_act = "Finish";
	}
	
	public void proceedNextActivity() {
		if(next_act=="pack_items") {
			proceedPackItem();
		}else if(next_act=="store_package") {
			proceedStorePackage();
		}else {
			finished = true;
		}
	}
}
