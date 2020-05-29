package org.processmining.EIS.OHP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

public class OrderHandlingProcess {
	public int graph_cnt = 1;
	public List<String> activity_list;
	public List<String> resource_list;
	public Map<String, Map<String, Object>> eventlog;
	public Map<String, List<ObjectType>> object_graph;
	public List<ObjectType> object_list;
	public List<ObjectType> ready_object_list;

	public OrderHandlingProcess() {
		activity_list = new ArrayList<String>();
		activity_list.add("place_order");
		activity_list.add("send_invoice");
		activity_list.add("receive_payment");
		activity_list.add("check_availability");
		activity_list.add("pick_item");
		activity_list.add("pack_items");
		activity_list.add("store_package");
		activity_list.add("start_route");
		activity_list.add("load_package");
		activity_list.add("deliver_package");
		activity_list.add("unload_package");
		activity_list.add("end_route");

		eventlog = new HashMap<String, Map<String, Object>>();
		//		object_graph_list = new ArrayList<ObjectGraph>();
		object_graph = new LinkedHashMap<String, List<ObjectType>>();
		object_list = new ArrayList<ObjectType>();
		ready_object_list = new ArrayList<ObjectType>();
	}

	//generate new order and object graph to store it
	public void generateOrder(int label, int t) {
		//		double random = Math.random();
		Random generator = new Random(t*1000);
		float random = generator.nextFloat();
		if (random < 0.3) {
			String order_name = "o" + label;
			ObjectType o1 = this.initOrder(order_name, t);
			String graph_name = "g" + label;
			this.initObjectGraph(graph_name);
			object_graph.get(graph_name).add(o1);
			this.graph_cnt += 1;
		}
	}

	public void run(int t) {
		this.generateOrder(graph_cnt, t);
		this.initItems(2, t);
		this.checkPackPrepared(t);
		this.initPackage(t);
		this.checkDeliveryPrepared(t);
		this.initRoute(t);
		this.reInitRoute(t);
		this.update_status(t);
		this.removeObjects();
		//		this.updateObjectGraph();
	}

	public void setResourceNames(List<String> r_names) {
		resource_list = r_names;
	}

	public void initObjectGraph(String name) {
		//		ObjectGraph<String> g = new ObjectGraph<String>(name);
		object_graph.put(name, new ArrayList<ObjectType>());
		//		g.addVertex(o1.getObjectName());
	}

	public ObjectType initOrder(String name, int t) {
		ObjectType o1 = new Order(name, t);
		object_list.add(o1);
		return o1;
	}

	public void initItems(int num_items, int t) {
		List<Item> items = new ArrayList<Item>();
		for (ObjectType o1 : this.object_list) {
			if (o1 instanceof Order) {
				if (((Order) o1).order_placed) {
					List<Item> temp_items = new ArrayList<Item>();
					//add items to object graph
					String graph_name = this.getObjectGraphKey(object_graph, o1);
					for (int i = 0; i < num_items; i++) {
						String name = o1.getObjectName() + "-i" + i;
						Item i1 = new Item(o1, name, t);
						object_graph.get(graph_name).add(i1);
						temp_items.add(i1);
					}
					items.addAll(temp_items);
					//add items to order
					((Order) o1).setItems(temp_items);
					((Order) o1).order_placed = false;
				}
			}
		}
		object_list.addAll(items);
	}

	public String getObjectGraphKey(Map<String, List<ObjectType>> map, ObjectType value) {
		for (Map.Entry<String, List<ObjectType>> entry : map.entrySet()) {
			if (entry.getValue().contains(value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	//check if all items in an order are packed. Then, make the order proceed the next activities.
	public void checkPackPrepared(int t) {
		for (ObjectType o1 : object_list) {
			//			(TODO) if items = 0 skip
			if (o1 instanceof Order && ((Order) o1).pack_prepared == false) {
				if (((Order) o1).items.size() == 0) {
					break;
				}
				boolean check = true;
				for (int i = 0; i < ((Order) o1).items.size(); i++) {
					if (((Order) o1).items.get(i).available_at > t
							|| !((Order) o1).items.get(i).process.next_act.equals("Finish")) {
						check = false;
					}
				}
				if (check) {
					((Order) o1).pack_prepared = true;
				}
			}
		}
	}

	public void initPackage(int t) {
		ListIterator<ObjectType> iter = object_list.listIterator();
		while (iter.hasNext()) {
			ObjectType n = iter.next();
			if (n instanceof Order) {
				if (((Order) n).pack_prepared == true && ((Order) n).pack_done == false) {
					Package p1 = new Package(((Order) n).getObjectName() + "-p1", ((Order) n).items, t);
					iter.add(p1);
					String graph_name = this.getObjectGraphKey(object_graph, n);
					object_graph.get(graph_name).add(p1);
					((Order) n).pack_done = true;
				}
			}
		}
	}

	public void checkDeliveryPrepared(int t) {
		for (ObjectType o1 : object_list) {
			if (o1 instanceof Package && ((Package) o1).delivery_prepared == false) {
				if (o1.available_at <= t && o1.process.next_act.equals("Finish")) {
					((Package) o1).delivery_prepared = true;
				}
			}
		}
	}

	public void initRoute(int t) {
		ListIterator<ObjectType> iter = object_list.listIterator();
		while (iter.hasNext()) {
			ObjectType n = iter.next();
			if (n instanceof Package) {
				if (((Package) n).delivery_prepared == true && ((Package) n).delivery_prep_done == false) {
					Route r1 = new Route(((Package) n).getObjectName() + "-r1", ((Package) n), t);
					iter.add(r1);
					((Package) n).delivery_prep_done = true;
					String graph_name = this.getObjectGraphKey(object_graph, n);
					object_graph.get(graph_name).add(r1);
				}
			}
		}
	}

	public void reInitRoute(int t) {
		ListIterator<ObjectType> iter = object_list.listIterator();
		while (iter.hasNext()) {
			ObjectType n = iter.next();
			if (n instanceof Package) {
				if (((Package) n).re_route == true) {
					((Package) n).num_fail += 1;
					Route r1 = new Route(((Package) n).getObjectName() + "-r" + ((Package) n).num_fail, ((Package) n),
							t);
					iter.add(r1);
					String graph_name = this.getObjectGraphKey(object_graph, n);
					object_graph.get(graph_name).add(r1);
				}
			}
		}
	}

	public void removeObjects() {
		List<ObjectType> finished_o = new ArrayList<ObjectType>();
		for (ObjectType o1 : this.object_list) {
			if (o1 instanceof Route) {
				if (((Route) o1).delivery_done) {
					String graph_name = this.getObjectGraphKey(object_graph, o1);
					List<ObjectType> removed = object_graph.remove(graph_name);
					finished_o.addAll(removed);
					//					System.out.println("Removed: " + graph_name + removed + object_graph.size());
				}
			}
		}
		object_list.removeAll(finished_o);
	}

	public void update_status(int t) {
		List<ObjectType> rot = new ArrayList<ObjectType>();
		//empty ready object list
		this.ready_object_list = rot;

		for (ObjectType o1 : object_list) {
			//In case of Order, proceed if items are packed
			//Otherwise, just proceed
			if (o1.available_at <= t) {
				if (o1 instanceof Order && o1.process.current_act.equals("place_order")) {
					if (((Order) o1).pack_prepared) {
						o1.status = "ready_to_assign";
						//Action - increase the priority of that order and its relevant objects
						o1.priority = t - o1.available_at + o1.order.initialPriority;
						//NOISE: for every 10th orders - only enable priority get higher!
						if ((Integer.parseInt(o1.order.getObjectName().replace("o", ""))) % 10 == 0) {
							if (o1.priority > 30) {
								this.ready_object_list.add(o1);
							}
						} else {
							this.ready_object_list.add(o1);
						}

					}
				} else {
					o1.status = "ready_to_assign";
					//Action - increase the priority of that order and its relevant objects
					o1.priority = t - o1.available_at + o1.order.initialPriority;
					//NOISE: for every 10th orders - only enable priority get higher!
					if ((Integer.parseInt(o1.order.getObjectName().replace("o", ""))) % 10 == 0) {
						if (o1.priority > 30) {
							this.ready_object_list.add(o1);
						}
					} else {
						this.ready_object_list.add(o1);
					}
				}
			}
		}
		//sort ready_object_list based on the priority
		Collections.sort(this.ready_object_list, new Comparator<ObjectType>() {
			@Override
			public int compare(ObjectType lhs, ObjectType rhs) {
				// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				return lhs.priority > rhs.priority ? -1 : (lhs.priority < rhs.priority) ? 1 : 0;
			}
		});
	}
	
	public List<ObjectType> getReadyObjectSet() {
		return this.ready_object_list; 
	}
	
	public List<ObjectType> getObjectSet() {
		return this.object_list; 
	}
}
