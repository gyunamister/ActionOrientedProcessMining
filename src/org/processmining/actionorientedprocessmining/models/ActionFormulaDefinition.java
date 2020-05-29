package org.processmining.actionorientedprocessmining.models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.processmining.actionorientedprocessmining.actionengine.ActionFormula;
import org.processmining.actionorientedprocessmining.parser.ADLLexer;
import org.processmining.actionorientedprocessmining.parser.ADLListenerImpl;
import org.processmining.actionorientedprocessmining.parser.ADLParser;

public class ActionFormulaDefinition {
	public Map<String, ActionFormula> afMap;
	Map<String, Map<String, Object>> parsedAFD;
	
	public void setAfMap(String text) {
		this.parsedAFD = new LinkedHashMap<String, Map<String, Object>>();
		ParseTree adlTree = this.readADL(text);
		ADLListenerImpl adlListener = new ADLListenerImpl(this.parsedAFD);
		ParseTreeWalker adlWalker = new ParseTreeWalker();
		adlWalker.walk(adlListener, adlTree);
		this.afMap = this.genafMap(this.parsedAFD);
	}
	
	public Map<String, ActionFormula> getAfMap(){
		return this.afMap;
	}
	
	public Map<String, Map<String, Object>> getParsedAFD(){
		return this.parsedAFD;
	}
	
	public ParseTree readADL(String text)  {
		
		System.out.println( "ADL File:\n" + text + "\n\n");

		ANTLRInputStream input = new ANTLRInputStream( text );

		ADLLexer lexer = new ADLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		ADLParser parser = new ADLParser(tokens);

		ParseTree tree = parser.action();
		return tree;
	}
	
	public Map<String, ActionFormula> genafMap(Map<String, Map<String, Object>> parsedAFD) {
		Map<String, ActionFormula> afMap = new LinkedHashMap<String, ActionFormula>();
		for(String afName:parsedAFD.keySet()) {
			Map<String, Object> action = parsedAFD.get(afName);
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
