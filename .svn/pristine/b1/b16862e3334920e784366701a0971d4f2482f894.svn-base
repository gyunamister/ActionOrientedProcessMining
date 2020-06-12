package org.processmining.actionorientedprocessmining.event;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.constraintmonitor.Context;

public class Filter {
	
		public Set<Event> eventFilter(Set<Event> eventSet, TimeWindow tw, Context ctx){
			Set<Event> ftEventSet = eventSet.stream().filter((x) -> (x.getTimestamp() <= tw.getEnd()) && (x.getTimestamp() >= Math.max(0,tw.getStart()))).collect(Collectors.toSet());
			if(ctx.getProcSet().size()!=0) {
				ftEventSet = ftEventSet.stream().filter((x) -> (ctx.getProcSet().contains(x.getProcess()))).collect(Collectors.toSet());
			}
			if(ctx.getActSet().size()!=0) {
				ftEventSet = ftEventSet.stream().filter((x) -> (ctx.getActSet().contains(x.getAct()))).collect(Collectors.toSet());
			}
			if(ctx.getResSet().size()!=0) {
				ftEventSet = ftEventSet.stream().filter((x) -> (ctx.getResSet().contains(x.getRes()))).collect(Collectors.toSet());
			}		
						
			for(String oc : ctx.getOmap().keySet()) {
				if(ctx.getOmap().get(oc).size()!=0) {
					Set<Event> temp = new HashSet<Event>();
					for(String oi : ctx.getOmap().get(oc)) {
						temp.addAll(ftEventSet.stream().filter((x) -> (x.getOmap().get(oc).contains(oi))).collect(Collectors.toSet()));
					}
					ftEventSet=temp;
				}		
			}
			return ftEventSet;
		}
		
		public Set<Event> objectFilter(Set<Event> eventSet, TimeWindow tw, Context ctx){
			Set<Event> eventFtEventSet = eventFilter(eventSet, tw, ctx); 
			
			for(String oc: ctx.getOmap().keySet()) { 
				Set<Map<String,Set<String>>> omapSet = eventFtEventSet.stream().map(Event::getOmap).collect(Collectors.toSet());
				Set<String> oiSet = new HashSet<String>();
				for(Map<String,Set<String>> omap : omapSet) {
					oiSet.addAll(omap.get(oc));
				}
				for(String oi:oiSet) {
					eventFtEventSet.addAll(eventSet.stream().filter((x) -> (x.getOmap().get(oc).contains(oi))).collect(Collectors.toSet()));
				}
				
			}
			return eventFtEventSet;
		}
		
		
}
