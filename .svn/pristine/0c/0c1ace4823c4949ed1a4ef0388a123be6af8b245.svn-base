package org.processmining.actionorientedprocessmining.parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.actionengine.ActionFormula;

public class ADLListenerImpl extends ADLBaseListener {
	Map<String, Map<String, Object>> actionMap;
	public Map<String, Object> action = this.generateAction();
	public ADLListenerImpl(Map<String, Map<String, Object>> actionMap) {
		this.actionMap = actionMap;
	} 
	
	public Map<String, Object> generateAction() {
		Map<String, Object> action = new LinkedHashMap<String, Object>();
		return action;
	}
	
	@Override public void enterActionName(ADLParser.ActionNameContext ctx) { 
        String actionName = ctx.getText().substring(1,ctx.getText().length()-1);;
        this.action.put("actionName", actionName);
    }
	@Override public void enterCfName(ADLParser.CfNameContext ctx) { 
		String constraint = ctx.getText().substring(1,ctx.getText().length()-1);
		((List<String>) ((Map<String,Object>) this.action.get("ccv")).get("constraintList")).add(constraint);
    }
	
	@Override public void enterViewDetails(ADLParser.ViewDetailsContext ctx) { 
		String viewDetails = ctx.getText();
        String[] parsedView = viewDetails.split("=");
        if("\"cf\"".equals(parsedView[0])) {
			String tempVal = parsedView[1].substring(1, parsedView[1].length()-1);
			String val = tempVal.replaceAll(" ", "");
			String[] parsedVals = val.split(","); 
			Set<String> procSet = new HashSet<String>();
			for(String v: parsedVals) {
				String cleanedV = v.substring(1, v.length()-1);
				procSet.add(cleanedV);
			}
			this.action.put("cf", procSet);
		}else if("\"proc\"".equals(parsedView[0])) {
			String tempVal = parsedView[1].substring(1, parsedView[1].length()-1);
			String val = tempVal.replaceAll(" ", "");
			String[] parsedVals = val.split(","); 
			Set<String> procSet = new HashSet<String>();
			for(String v: parsedVals) {
				String cleanedV = v.substring(1, v.length()-1);
				procSet.add(cleanedV);
			}
			this.action.put("proc", procSet);
		}else if("\"act\"".equals(parsedView[0])) {
			String tempVal = parsedView[1].substring(1, parsedView[1].length()-1);
			String val = tempVal.replaceAll(" ", "");
			String[] parsedVals = val.split(","); 
			Set<String> procSet = new HashSet<String>();
			for(String v: parsedVals) {
				String cleanedV = v.substring(1, v.length()-1);
				procSet.add(cleanedV);
			}
			this.action.put("act", procSet);
		}else if("\"res\"".equals(parsedView[0])) {
			String tempVal = parsedView[1].substring(1, parsedView[1].length()-1);
			String val = tempVal.replaceAll(" ", "");
			String[] parsedVals = val.split(","); 
			Set<String> procSet = new HashSet<String>();
			for(String v: parsedVals) {
				String cleanedV = v.substring(1, v.length()-1);
				procSet.add(cleanedV);
			}
			this.action.put("res", procSet);
		}else {
			Map<String, Set<String>> objectMapping = new LinkedHashMap<String, Set<String>>();
			String ocName = parsedView[0].substring(1, parsedView[0].length()-1);
			ocName = "object@" + ocName;
			String tempVal = parsedView[1].substring(1, parsedView[1].length()-1);
			String omap = tempVal.replaceAll(" ", "");
			String[] ois = omap.split(",");;
			Set<String> oiSet = new HashSet<String>();
			for(String oi: ois) {
				oiSet.add(oi.substring(1, oi.length()-1));
			}
//			objectMapping.put(ocName, oiSet);
//			this.action.put("omap", objectMapping);
			this.action.put(ocName, oiSet);
		}
        

    }
	
//	@Override public void enterStartTime(ADLParser.StartTimeContext ctx) { 
//		String startTime = ctx.getText();
//		((List<String>) ((Map<String,Object>) this.action.get("ccv")).get("timeRange")).add(startTime);
//    }
//	
//	@Override public void enterEndTime(ADLParser.EndTimeContext ctx) { 
//		String endTime = ctx.getText();
//		((List<String>) ((Map<String,Object>) this.action.get("ccv")).get("timeRange")).add(endTime);
//    }
	
	@Override public void enterRelationalPredicate(ADLParser.RelationalPredicateContext ctx) { 
		String condition = ctx.getText();
		List<String> conditionList = new ArrayList<String>(Arrays.asList(condition.split(",")));
		conditionList.set(0,conditionList.get(0).substring(1,conditionList.get(0).length()-1));
		Iterator itr = conditionList.iterator(); 
        while (itr.hasNext()) 
        { 
            String s = (String) itr.next(); 
            if (s.contains("ACQUIRED")) 
                itr.remove(); 
        }
        String reunion = conditionList.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining(","));
		this.action.put("assessment", reunion);
    }
	
	@Override public void enterOpDesc(ADLParser.OpDescContext ctx) { 
		String actionDesc = ctx.getText().substring(1,ctx.getText().length()-1);
		this.action.put("operation", actionDesc);
    }
	
	@Override public void enterParameterDetails(ADLParser.ParameterDetailsContext ctx) { 
		String[] paramDetail = ctx.getText().split("=");
		String paramName = paramDetail[0].substring(1,paramDetail[0].length()-1);
		String paramValue = paramDetail[1].substring(1,paramDetail[1].length()-1);
		paramName = "parameter@" + paramName;
		this.action.put(paramName, paramValue);
//		((Map<String,Object>) this.action.get("parameter")).put(paramName, paramValue);
    }
	
	@Override public void enterEos(ADLParser.EosContext ctx) { 
		actionMap.put((String) this.action.get("actionName"),this.action);
		System.out.println(this.action);
		this.action = this.generateAction();
    }
	
	public Map<String, ActionFormula> genafMap(Map<String, Map<String, Object>> actionMap) {
		Map<String, ActionFormula> afMap = new LinkedHashMap<String, ActionFormula>();
		for(String afName:actionMap.keySet()) {
			Map<String, Object> action = actionMap.get(afName);
			Map<String, Set<String>> ccvDescription = new LinkedHashMap<String, Set<String>>();
			Map<String, String> pmap = new LinkedHashMap<String, String>();
			for(String key: action.keySet()) {
				if(key.equals("cf")) {
					Set<String> procSet = new HashSet<String>();
					procSet.addAll((Set) action.get(key));
					ccvDescription.put(key, procSet);
				}else if(key.equals("proc")) {
					Set<String> procSet = new HashSet<String>();
					procSet.addAll((Set) action.get(key));
					ccvDescription.put(key, procSet);
				}else if(key.equals("act")) {
					Set<String> actSet = new HashSet<String>();
					actSet.addAll((Set) action.get(key));
					ccvDescription.put(key, actSet);
				}else if(key.equals("res")) {
					Set<String> resSet = new HashSet<String>();
					resSet.addAll((Set) action.get(key));
					ccvDescription.put(key, resSet);
				}else if(key.contains("object@")) {
					Set<String> objSet = new HashSet<String>();
					objSet.addAll((Set) action.get(key));
					ccvDescription.put(key.split("@")[1], objSet);
				}else if(key.contains("parameter@")) {
					pmap.put(key.split("@")[1], (String) action.get(key));
				}
			}
			ActionFormula af = new ActionFormula(afName, ccvDescription, (String) action.get("assessment"), (String) action.get("operation"), pmap);
			afMap.put(afName,af);
		}
		return afMap;
	}
	
}
