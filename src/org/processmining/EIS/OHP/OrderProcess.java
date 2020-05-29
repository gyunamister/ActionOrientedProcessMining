package org.processmining.EIS.OHP;

public class OrderProcess extends Process {
	public Order order;
	public OrderProcess(Order o1) {
		order = o1;
		current_act = "Start";
		next_act = "place_order";
	}
	
	
	public void setControlFlow() {
		control_flow.add("place_order");
		control_flow.add("send_invoid");
		control_flow.add("receive_payment");
	}
	
	public void proceedPlaceOrder() {
		current_act = "place_order";
		order.order_placed = true;
		next_act = "send_invoice";
				
	}
	
	public void proceedSendInvoice() {
		current_act = "send_invoice";
		next_act = "receive_payment";
	}
	
	public void proceedReceivePayment() {
		current_act = "receive_payment";
		next_act = "Finish";
	}
	
	public void proceedNextActivity() {
		if(next_act=="place_order") {
			proceedPlaceOrder();
		}else if(next_act=="send_invoice") {
			proceedSendInvoice();
		}else if(next_act=="receive_payment") {
			proceedReceivePayment();
		}else {
			finished = true;
		}
	}
}
