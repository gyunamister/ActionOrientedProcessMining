package org.processmining.EIS.OHP;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.event.Event;

public class Scheduler {
	public List<Resource> resourceList; 
	public int eventNumber = 1;

	public List<String> getResourceNames(){
		List<String> r_names = new ArrayList<String>();
		for (Resource r : this.resourceList) {
			r_names.add(r.resource_name);
		}
		return r_names;
	}

	/*
	public void old_generateResource(int num_res) {
		for(int i=0;i<num_res;i++) {
			this.order_simulator.resourceList.add(new Resource("RO"+i,1));
			this.item_simulator.resourceList.add(new Resource("RI"+i,1));
			this.package_simulator.resourceList.add(new Resource("RP"+i,1));
			this.route_simulator.resourceList.add(new Resource("RR"+i,1));
		}
	}
	*/

	public Scheduler(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public Set<Event> assign(Map<String, Map<String,Object>> eventlog, List<ObjectType> rot, int t) {
		Set<Event> eventSet = new HashSet<Event>();
		Random random = new Random(t);
		for(Resource r : this.resourceList) {
			if(r.status.equals("ready_to_assign")) {
				int index=0;
				if(rot.size()>0) {
					outerloop:
					for(int i=0;i<rot.size();i++) {
						for(String task : r.tasks.keySet()) {
							if(rot.get(i).process.next_act.equals(task)) {
								index=i;
								//e was incremented in generateEvent -> need to deduct 1
								rot.get(index).setHistory("event"+eventNumber);
								//assign task and adjust values
								rot.get(index).process.proceedNextActivity();
								rot.get(index).prev_sojourn_time = t-rot.get(index).available_at;
								int processingTime = ( (int) Math.round(random.nextGaussian()) + r.tasks.get(task));
								rot.get(index).available_at = t+processingTime;
								rot.get(index).status = "work_in_process";

								r.available_at = t+processingTime;
								r.setStatus("work_in_process");

								//generate event
								Event event;
								if(rot.get(index) instanceof Order) {
									String orderName = ((Order)rot.get(index)).getObjectName();
									List<String> items = ((Order)rot.get(index)).items.stream().map(Item::getObjectName).collect(Collectors.toList());
									String itemNames = null;
									if(items.size()!=0) {
										itemNames = String.join("&", items);
									}
									event = rot.get(index).process.generateEvent(eventlog, orderName, itemNames, null, null,
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, eventNumber);
									eventNumber+=1;
									eventSet.add(event);
								}else if(rot.get(index) instanceof Item) {
									String orderName = ((Item)rot.get(index)).order.getObjectName();
									String itemName = ((Item)rot.get(index)).getObjectName();
									event = rot.get(index).process.generateEvent(eventlog, orderName, itemName, null, null,
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, eventNumber);
									eventNumber+=1;
									eventSet.add(event);
								}else if(rot.get(index) instanceof Package) {
									String orderName = ((Package)rot.get(index)).order.getObjectName();
									List<String> items = ((Package)rot.get(index)).items.stream().map(Item::getObjectName).collect(Collectors.toList());
									String itemNames = String.join("&", items);
									String packageName = ((Package)rot.get(index)).getObjectName();
									event = rot.get(index).process.generateEvent(eventlog, orderName, itemNames, packageName, null,
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, eventNumber);
									eventNumber+=1;
									eventSet.add(event);
								}else if(rot.get(index) instanceof Route) {
									String orderName = ((Route)rot.get(index)).order.getObjectName();
									List<String> items = ((Route)rot.get(index)).packages.items.stream().map(Item::getObjectName).collect(Collectors.toList());
									String itemNames = String.join("&", items);
									String packageName = ((Route)rot.get(index)).packages.getObjectName();
									String routeName = ((Route)rot.get(index)).getObjectName();
									event = rot.get(index).process.generateEvent(eventlog, orderName, itemNames, packageName, routeName,
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, eventNumber);
									eventNumber+=1;
									eventSet.add(event);
								}else {
									event = rot.get(index).process.generateEvent(eventlog, null, null, null, null,
											rot.get(index).process.current_act, r.resource_name, t, rot.get(index).available_at, eventNumber);
									eventSet.add(event);
								}
								rot.remove(rot.get(index));
								break outerloop;
							}
						}

					}
				}
			}
		}
	return eventSet;
	}

	public void updateResourceStatus(int t) {
		for(Resource r : this.resourceList) {
			if(r.available_at<=t) {
				r.setStatus("ready_to_assign");
			}
		}
	}
}
