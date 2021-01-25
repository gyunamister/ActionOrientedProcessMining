package org.processmining.actionorientedprocessmining.models;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.processmining.EIS.OHP.ActionGateway;
import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.actionorientedprocessmining.Exporter.AISExporter;
import org.processmining.actionorientedprocessmining.Importer.OCLImporter;
import org.processmining.actionorientedprocessmining.Importer.OCXMLImporter;
import org.processmining.actionorientedprocessmining.actionengine.AEConfig;
import org.processmining.actionorientedprocessmining.actionengine.ActionEngine;
import org.processmining.actionorientedprocessmining.actionengine.ActionInstance;
import org.processmining.actionorientedprocessmining.constraintcube.ConstraintCubeStructure;
import org.processmining.actionorientedprocessmining.constraintmonitor.CMConfig;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstance;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintInstanceStream;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintMonitor;
import org.processmining.actionorientedprocessmining.dashboard.Dashboard;
import org.processmining.actionorientedprocessmining.event.Event;
import org.processmining.actionorientedprocessmining.event.EventStream;


public class AOPMProgram implements Runnable{
	public boolean record =false;
	public OCXMLImporter ocxmlImporter;
	public OCLImporter oclImporter;
	public String AISdirPath; 
	public String AISfilePath; 
	public AISExporter exporter;
	public CMConfig cmConfig;
	public AEConfig aeConfig;
	public ConstraintMonitor cm;
	public ConstraintInstanceStream cs;
	public ConstraintCubeStructure ccs;
	public Dashboard db;
	public ActionEngine ae;
	public EventStream es;
	public ProcessSimulation pSimulator;
	public ObjectCentricLog ocl;
	public ActionGateway aGateway;
	public Set<String> objectSet = new HashSet<String>();
	
	public int speed;
	public String mode;
	
	public int currentTime=0;
	public int timeOffset;
	public boolean isRestart = false;
	public boolean isSleep = false;

	public void run() {
		synchronized(this) { 
			for(int i=this.currentTime;i<10000;i++) {
				if(this.mode.equals("OCL")) {
					simulateWithOCL(this.currentTime,this.timeOffset);
				}
				else if(this.mode.equals("OCXML")) {
					simulateWithOCXMLLog(this.currentTime,this.timeOffset);
				}else if(this.mode.equals("information_system")) {
					this.simulateWithIS(this.currentTime,record);
				}else {
					System.out.println("At " + this +" - undefined mode");
				}
				try {
					isSleep=false;
					Thread.sleep(1000/(speed*10));
	            } catch (InterruptedException ex) {
	            		System.out.println("PAUSED");
	            		return;
	            }
				this.currentTime=i;
			}
		}
	} 

	public AOPMProgram(CMConfig cmConfig, AEConfig aeConfig, ObjectCentricLog ocl, String AISdirName){
		this.mode = "OCL";
		if(this.mode.equals("OCL")) {
			this.oclImporter = new OCLImporter(ocl.getFilePath());
			this.timeOffset = this.oclImporter.getTimeOffset();
//			this.currentTime = this.currentTime + this.timeOffset;
		}
		else if(this.mode.equals("OCXML")) {
			this.ocxmlImporter = new OCXMLImporter(ocl.getFilePath());
			this.timeOffset = this.ocxmlImporter.getTimeOffset();
//			this.currentTime = this.currentTime + this.timeOffset;
		}else if(this.mode.equals("information_system")) {
			this.timeOffset = 0;
		}else {
			System.out.println("At " + this +" - undefined mode");
		}
		this.ocl = ocl;
		this.es = new EventStream();
		this.cm = new ConstraintMonitor(cmConfig);
		this.cs = new ConstraintInstanceStream();
		//(TODO) hard-coded properties
		Set<String> defaultProp= new HashSet<String>();
		Collections.addAll(defaultProp, "cf","proc","act","res","time");
		Set<String> objectProp= new HashSet<String>();
//		Collections.addAll(objectProp, "Order","Item","Package","Route");
		Collections.addAll(objectProp, "order","item","delivery_line","delivery");
		Set<String> attrProp= new HashSet<String>();
		this.ccs = new ConstraintCubeStructure(this.cs.cis,defaultProp,objectProp,attrProp);
		this.db = new Dashboard(cmConfig, this.cs);
		this.ae = new ActionEngine(aeConfig);
		this.cmConfig = cmConfig;
		this.aeConfig = aeConfig;
		this.AISdirPath = AISdirName;
		this.AISfilePath = String.format("%s/OH-AIS-%s.xml", this.AISdirPath,new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		this.exporter = new AISExporter(this.AISfilePath);
	}
	
	public AOPMProgram(CMConfig cmConfig, AEConfig aeConfig, ProcessSimulation pSimulator,String AISdirName){
		this.mode = "information_system";
		this.es = new EventStream();
		this.cm = new ConstraintMonitor(cmConfig);
		this.cs = new ConstraintInstanceStream();
		//(TODO) hard-coded properties
		Set<String> defaultProp= new HashSet<String>();
		Collections.addAll(defaultProp, "cf","proc","act","res","time");
		Set<String> objectProp= new HashSet<String>();
//		Collections.addAll(objectProp, "Order","Item","Package","Route");
		Collections.addAll(objectProp, "order","item","delivery_line","delivery");
		Set<String> attrProp= new HashSet<String>();
		this.ccs = new ConstraintCubeStructure(this.cs.cis,defaultProp,objectProp,attrProp);
		this.db = new Dashboard(cmConfig, this.cs);
		this.ae = new ActionEngine(aeConfig);
		this.cmConfig = cmConfig;
		this.aeConfig = aeConfig;
		this.pSimulator=pSimulator;
		this.aGateway = new ActionGateway(pSimulator);
		this.AISdirPath = AISdirName;
		this.AISfilePath = String.format("%s/OH-AIS-%s.xml", this.AISdirPath,new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		this.exporter = new AISExporter(this.AISfilePath);
	}
	
	public void simulateWithOCL(int t, int offset) {
		Set<Event> eventsAtT = this.oclImporter.getEventsAtT(t+offset);
		this.es.setEventSet(eventsAtT);
		Set<ConstraintInstance> cis = this.cm.monitor(t,this.es);
		this.cs.addInstances(cis);
		this.ccs.updateElem(t);
		this.ccs.updateHier(t);
		this.db.updateVMap(t);
		Set<ActionInstance> ais = this.ae.engine(t,this.cs,this.ccs);
		this.recordAI(ais);
	}

	public void simulateWithOCXMLLog(int t, int offset) {
		Set<Event> eventsAtT = this.ocxmlImporter.getEventsAtT(t+offset);
		this.es.setEventSet(eventsAtT);
		Set<ConstraintInstance> cis = this.cm.monitor(t,this.es);
		this.cs.addInstances(cis);
		this.ccs.updateElem(t);
		this.ccs.updateHier(t);
		this.db.updateVMap(t);
		Set<ActionInstance> ais = this.ae.engine(t,this.cs,this.ccs);
		this.recordAI(ais);
	}
	
	public void simulateWithIS(int t, boolean record) {
		Set<Event> eventsAtT = this.pSimulator.simulateProcess(t,record);
//		Set<Event> eventsAtT = this.importer.readOCXML(t);
		this.es.setEventSet(eventsAtT);
		Set<ConstraintInstance> cis = this.cm.monitor(t,this.es);
		this.cs.addInstances(cis);
		this.ccs.updateElem(t);
		this.ccs.updateHier(t);
		this.db.updateVMap(t);
		Set<ActionInstance> ais = this.ae.engine(t,this.cs,this.ccs);
		this.recordAI(ais);
		this.aGateway.apply(ais);
	}
	
	public void recordAI(Set<ActionInstance> aisAtT) {
		for(ActionInstance ai:aisAtT) {
			try {
				this.exporter.recordAI(ai);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	}
