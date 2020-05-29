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
			if(op=="Set higher priority") {
				String targetObjectName = ai.getTrans().getPmap().get("target");
				this.changePriority(targetObjectName);
//				System.out.println(ai.getTime() + " Execute: " + op + " at " + targetObjectName);
			}
			else if(op=="Send an email to the case manager") {
				String targetObjectName = ai.getTrans().getPmap().get("target");
//				System.out.println(ai.getTime() + "Execute: " + op + " at " + targetObjectName);
			}
		}
	}
	
	
	public void addResource() {
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
	}
}
