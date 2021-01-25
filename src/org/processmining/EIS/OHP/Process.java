package org.processmining.EIS.OHP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.processmining.actionorientedprocessmining.event.Event;

public abstract class Process {
	public String prev_act;
	public String current_act;
	public String next_act;
	public int available_at;
	public boolean finished;

	public List<String> control_flow = new ArrayList<String>();

	public abstract void proceedNextActivity();

	public Event generateEvent(Map<String, Map<String, Object>> eventlog, String o1, String i1, String p1, String r1,
			String act, String res, int start, int complete, int eventNumber) {
		String event_id = "event" + eventNumber;
		String process = "order-handling-process";
		eventlog.put(event_id, new HashMap<String, Object>());
		if (o1 != null) {
			eventlog.get(event_id).put("order_id", o1);
		}
		if (i1 != null) {
			eventlog.get(event_id).put("item_id", i1);
		}
		if (p1 != null) {
			eventlog.get(event_id).put("package_id", p1);
		}
		if (r1 != null) {
			eventlog.get(event_id).put("route_id", r1);
		}
		eventlog.get(event_id).put("activity", act);
		eventlog.get(event_id).put("resource", res);
//		eventlog.get(event_id).put("start_timestamp", start);
		eventlog.get(event_id).put("complete_timestamp", complete);

		Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
		Set<String> orderSet = new HashSet<String>();
		orderSet.add(o1);
		Set<String> itemSet = new HashSet<String>();
		itemSet.add(i1);
		Set<String> packageSet = new HashSet<String>();
		packageSet.add(p1);
		Set<String> routeSet = new HashSet<String>();
		routeSet.add(r1);
		omap.put("Order", orderSet);
		omap.put("Item", itemSet);
		omap.put("Package", packageSet);
		omap.put("Route", routeSet);
		Event event = new Event(event_id, process, act, res, String.valueOf(complete), omap);

		return event;
	}
}
