package org.processmining.EIS.OHP;

import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.actionorientedprocessmining.actionengine.ActionInstance;

public class ActionGateway {
	public ProcessSimulation pSimulator;
	
	public ActionGateway(ProcessSimulation pSimulator) {
		this.pSimulator = pSimulator;
	}
	
	public void apply(Set<ActionInstance> ais) {
		for(ActionInstance ai: ais) {
			String op = ai.getTrans().getOp();
			if(op.equals("Change priority")) {
				String targetObjectName = ai.getTrans().getPmap().get("target");
				this.changePriority(targetObjectName);
			}
			else if(op.equals("Send an email to the case manager")) {
				String targetObjectName = ai.getTrans().getPmap().get("target");
//				System.out.println(ai.getTime() + "Execute: " + op + " at " + targetObjectName);
			}else if(op.equals("Add resource")) {
				this.changeItemSkipProb(0.01);
			}else if(op.equals("Send an email")) {
				this.changeItemRepeatProb(0.01);
			}else if(op.equals("Change maximum capacity")) {
				double reductionRate = Double.valueOf(ai.getTrans().getPmap().get("change-rate"));
				this.changeOrderGenProb(reductionRate);
			}
		}
	}
	
	public void changeItemSkipProb(double skipProb) {
		this.pSimulator.mp.itemSkipProb+=skipProb;
	}
	
	public void changeItemRepeatProb(double repeatProb) {
		this.pSimulator.mp.itemSkipProb+=repeatProb;
	}
	
	public void changeOrderGenProb(double orderGenProb) {
		this.pSimulator.mp.orderGenProb-=orderGenProb;
	}
	
	
	public void addResource(String resourceID, String taskName) {
		Resource r = new Resource(resourceID);
		r.setTask(taskName, 5);
	}
	
	public void old_addResource() {
		JFrame f = new JFrame();
		String resource_info=JOptionPane.showInputDialog(f,"Add resource?");
		if(resource_info!=null) {
			String[] parsed_info = resource_info.split(",");
			Resource r = new Resource(parsed_info[0]);
			for(int i=1;i<parsed_info.length;i++) {
				String task_info = parsed_info[i];
				String[] parsed_task_info = task_info.split("-");
				r.setTask(parsed_task_info[0], Integer.parseInt(parsed_task_info[1]));
			}
//			simulator.resource_list.add(r);
		}
	}
	
	public void changePriority(String targetObjectName) {
		List<ObjectType> objectSet = this.pSimulator.getProcess().getObjectSet();
		for(ObjectType oi : objectSet) {
			if(oi.getObjectName().equals(targetObjectName)) {
				oi.initialPriority = 1000;
			}
		}
		System.out.println("CHANGE " + targetObjectName + "to 1000");
	}
}
