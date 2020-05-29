package org.processmining.actionorientedprocessmining.models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintFormula;
import org.processmining.actionorientedprocessmining.constraintmonitor.ContextDescription;
import org.processmining.actionorientedprocessmining.parser.CDLLexer;
import org.processmining.actionorientedprocessmining.parser.CDLListenerImpl;
import org.processmining.actionorientedprocessmining.parser.CDLParser;

public class ConstraintFormulaDefinition {
	Map<String, Map<String, Object>> parsedCFD;
	Map<String, ConstraintFormula> cfMap;
	
	public void setCfMap(String text) {
		this.parsedCFD = new LinkedHashMap<String, Map<String, Object>>();
		ParseTree cdlTree = this.readCDL(text);
		CDLListenerImpl cdlListener = new CDLListenerImpl(this.parsedCFD);
		ParseTreeWalker cdlWalker = new ParseTreeWalker();
		cdlWalker.walk(cdlListener, cdlTree);
		this.cfMap = this.gencfMap(this.parsedCFD);
		System.out.println(this.cfMap);
	}
	
	public Map<String, ConstraintFormula> getCfMap(){
		return this.cfMap;
	}
	
	public Map<String, Map<String, Object>> getParsedCFD(){
		return this.parsedCFD;
	}
	
	public ParseTree readCDL(String text)  {
		System.out.println( "CDL File:\n" + text + "\n\n");

		ANTLRInputStream input = new ANTLRInputStream( text );

		CDLLexer lexer = new CDLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CDLParser parser = new CDLParser(tokens);

		ParseTree tree = parser.constraint();
		return tree;
	}
	
	public Map<String, ConstraintFormula> gencfMap(Map<String, Map<String, Object>> parsedCFD) {
		Map<String, ConstraintFormula> cfMap = new LinkedHashMap<String, ConstraintFormula>();
		for(String cfName:parsedCFD.keySet()) {
			Map<String, Object> constraint = parsedCFD.get(cfName);
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
