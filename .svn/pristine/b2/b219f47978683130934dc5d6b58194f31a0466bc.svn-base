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

import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintFormula;
import org.processmining.actionorientedprocessmining.constraintmonitor.ContextDescription;



public class CDLListenerImpl extends CDLBaseListener {
	Map<String, Map<String, Object>> constraintMap;
	public Map<String, Object> constraint = this.generateConstraint();
	public CDLListenerImpl(Map<String, Map<String, Object>> c) {
		this.constraintMap = c;
	} 
	
	public Map<String, Object> generateConstraint() {
		Map<String, Object> constraint = new LinkedHashMap<String, Object>();
		return constraint;
	}
	
	@Override public void enterConstraintName(CDLParser.ConstraintNameContext ctx) { 
		String constraintName = ctx.getText().substring(1,ctx.getText().length()-1);;
        this.constraint.put("constraintName", constraintName);   
    }
	
	@Override public void enterFilterName(CDLParser.FilterNameContext ctx) { 
		String filter = ctx.getText().substring(1,ctx.getText().length()-1);;
        this.constraint.put("filter", filter);   
    }
	
	@Override public void enterContextDetails(CDLParser.ContextDetailsContext ctx) { 
		String ctxDetail = ctx.getText();
		String[] parsedCtx = ctxDetail.split("=");
		if("\"Proc\"".equals(parsedCtx[0])) {
			String tempVal = parsedCtx[1].substring(1, parsedCtx[1].length()-1);
			String val = tempVal.replaceAll(" ", "");
			String[] parsedVals = val.split(","); 

			Set<String> procSet = new HashSet<String>();
			for(String v: parsedVals) {
				String cleanedV = v.substring(1, v.length()-1);
				procSet.add(cleanedV);
			}
			this.constraint.put("Proc", procSet);
		}else if("\"Act\"".equals(parsedCtx[0])) {
			String tempVal = parsedCtx[1].substring(1, parsedCtx[1].length()-1);
			String val = tempVal.replaceAll(" ", "");
			String[] parsedVals = val.split(",");

			Set<String> procSet = new HashSet<String>();
			for(String v: parsedVals) {
				String cleanedV = v.substring(1, v.length()-1);
				procSet.add(cleanedV);
			}
			this.constraint.put("Act", procSet);
		}else if("\"Res\"".equals(parsedCtx[0])) {
			String tempVal = parsedCtx[1].substring(1, parsedCtx[1].length()-1);
			String val = tempVal.replaceAll(" ", "");
			String[] parsedVals = val.split(",");

			Set<String> procSet = new HashSet<String>();
			for(String v: parsedVals) {
				String cleanedV = v.substring(1, v.length()-1);
				procSet.add(cleanedV);
			}
			this.constraint.put("Res", procSet);
		}else if("\"omap\"".equals(parsedCtx[0])) {
			Map<String, Set<String>> objectMapping = new LinkedHashMap<String, Set<String>>();
			String tempVal = parsedCtx[1].substring(1, parsedCtx[1].length()-1);
			String omap = tempVal.replaceAll(" ", "");
			String[] ocSet = omap.split(",");;
		
			for(String oc: ocSet) {
				String[] ocParsed = oc.split(":");
				String ocName = ocParsed[0].substring(1, ocParsed[0].length()-1);
				String tempOis = ocParsed[1].substring(1, ocParsed[1].length()-1);
				String[] ois = tempOis.split(",");;
				Set<String> oiSet = new HashSet<String>();
				for(String oi:ois) {
					oiSet.add(oi.substring(1, oi.length()-1));
				}
				objectMapping.put(ocName, oiSet);
			}
			this.constraint.put("omap", objectMapping);
		}else {
			System.out.println("Implement handling function for " + ctxDetail + " in CDLListenerImpl.java");
		}
    }
	
	
	@Override public void enterObjName(CDLParser.ObjNameContext ctx) {
		String objName = ctx.getText();
	}
	
	
//	@Override public void enterRelationalPredicate(CDLParser.RelationalPredicateContext ctx) { 
//		String condition = ctx.getText();
//		List<String> conditionList = new ArrayList<String>(Arrays.asList(condition.split(",")));
//		conditionList.set(0,conditionList.get(0).substring(1,conditionList.get(0).length()-1));
//		Iterator itr = conditionList.iterator(); 
//        String reunion = conditionList.stream()
//			      .map(n -> String.valueOf(n))
//			      .collect(Collectors.joining(","));
//        this.constraint.put("evaluation", reunion);
//    }
	
	@Override public void enterProposition(CDLParser.PropositionContext ctx) {
		String condition = ctx.getText();
//		List<String> conditionList = new ArrayList<String>(Arrays.asList(condition.split(",")));
//		conditionList.set(0,conditionList.get(0).substring(1,conditionList.get(0).length()-1));
//		Iterator itr = conditionList.iterator(); 
//        String reunion = conditionList.stream()
//			      .map(n -> String.valueOf(n))
//			      .collect(Collectors.joining(","));
//        this.constraint.put("evaluation", reunion);
        this.constraint.put("evaluation", condition);
    }
	
	@Override public void enterSetPredicate(CDLParser.SetPredicateContext ctx) { 
		String condition = ctx.getText();
		List<String>conditionList = new ArrayList<String>(Arrays.asList(condition.split(",")));
		
		conditionList.set(0,conditionList.get(0).substring(1,conditionList.get(0).length()-1));
		conditionList.set(2,conditionList.get(2).substring(1,conditionList.get(2).length()-1));
		Iterator itr = conditionList.iterator(); 
        String reunion = conditionList.stream()
			      .map(n -> String.valueOf(n)) 
			      .collect(Collectors.joining(","));
        this.constraint.put("evaluation", reunion);
    }
	
	
	@Override public void enterEos(CDLParser.EosContext ctx) { 
		constraintMap.put((String) this.constraint.get("constraintName"),this.constraint);
		this.constraint = this.generateConstraint();
    }
	
	public Map<String, ConstraintFormula> gencfMap(Map<String, Map<String, Object>> constraintMap) {
		Map<String, ConstraintFormula> cfMap = new LinkedHashMap<String, ConstraintFormula>();
		for(String cfName:constraintMap.keySet()) {
			Map<String, Object> constraint = constraintMap.get(cfName);
			Set<String> procSet = new HashSet<String>();
			Set<String> actSet = new HashSet<String>();
			Set<String> resSet = new HashSet<String>();
			Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
			Map<String,Set<String>> vmap = new LinkedHashMap<String,Set<String>>();
			for(String key: constraint.keySet()) {
				if(key.equals("Proc")) {
					procSet.addAll((Set) constraint.get("Proc"));
				}else if(key.equals("Act")) {
					actSet.addAll((Set) constraint.get("Act"));
				}else if(key.equals("Res")) {
					resSet.addAll((Set) constraint.get("Res"));
				}else if(key.equals("omap")) {
					Map<String, Set<String>> objectMapping = (Map) constraint.get("omap");
					for(String ocName:  objectMapping.keySet()) {
						omap.put(ocName, objectMapping.get(ocName));
					}
				}else if(key.equals("vmap")) {
					Map<String, Set<String>> attrMapping = new LinkedHashMap<String, Set<String>>();
					for(String attrName:  attrMapping.keySet()) {
						vmap.put(attrName, attrMapping.get(attrName));
					}
				}
			}
			ContextDescription ctxdesc = new ContextDescription(procSet,actSet,resSet,omap,vmap);
			ConstraintFormula cf = new ConstraintFormula(cfName, ctxdesc, (String) constraint.get("evaluation"));
			cfMap.put(cfName,cf);
		}
		return cfMap;
	}
	
}
