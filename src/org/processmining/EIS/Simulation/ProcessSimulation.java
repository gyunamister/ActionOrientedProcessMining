package org.processmining.EIS.Simulation;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.processmining.EIS.Exporter.OCXMLExporter;
import org.processmining.EIS.OHP.OrderHandlingProcess;
import org.processmining.EIS.OHP.Resource;
import org.processmining.EIS.OHP.Scheduler;
import org.processmining.actionorientedprocessmining.event.Event;

import com.opencsv.CSVReader;


public class ProcessSimulation{
	public OCXMLExporter exporter;
	public OrderHandlingProcess mp;
	public Scheduler sch;
	public int speed;
	public String mode = "order-handling-process";
	public List<Resource> resourceList;
	public boolean isRestart = false;
	public boolean isSleep = false;
	public String filePath;

	public ProcessSimulation(){
		String logFilePath = System.getProperty("user.dir")+"/logs";
		this.filePath = String.format("%s/OH-OCL-%s.xml", logFilePath, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		exporter = new OCXMLExporter(filePath);
		mp = new OrderHandlingProcess();
		String resFilePath = System.getProperty("user.dir")+"/src/org/processmining/EIS/metadata/res_act_mat.csv";
//		String resoureFile = "/Users/GYUNAM/Documents/AOPM/tests/testfiles/res_act_mat.csv";
		resourceList = this.generateResource(resFilePath);
		sch = new Scheduler(resourceList);
		mp.setResourceNames(sch.getResourceNames());
	}

	public String toString() {
		return "Information System simulating an Order-Handling-Process";
	}

	public OrderHandlingProcess getProcess() {
		return this.mp;
	}

	public Set<Event> simulateProcess(int t, boolean record) {
		//System.out.println(t + " begins:");
		/*
		 * process runs at every time step.
		 * 1. generate order with probability
		 * 2. initialize relevant items
		 * 3. check if a set of items is ready to be packed
		 * 4. if so, initialize package
		 * 5. check if a package is ready to be delivered
		 * 6. initialize delivery (route)
		 * 7. if delivery failed, initialize another delivery
		 * 8. find ready-to-assign objects and give them priority (for resource scheduling)
		 * 9. Remove relevant objects if delivery is done
		 * */
		this.mp.run(t);
		/*
		 * update the current status of resources - to identify who is ready for assignment.
		 * */
		this.sch.updateResourceStatus(t);
		/*
		 * assign resource and generate event
		 * */
		Set<Event> eventAtT = this.sch.assign(mp.eventlog, mp.ready_object_list, t);
		if(record==true) {
			this.recordLog(eventAtT);
		}
		return eventAtT;
	}

	public List<Resource> generateResource(String csvFile) {
		List<Resource> rl = new ArrayList<Resource>();
		//String csvFile = "/Users/GYUNAM/Documents/AOPM/tests/testfiles/res_act_mat.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            String[] header = reader.readNext();

            while ((line = reader.readNext()) != null) {
                //System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
            		Resource r = new Resource(line[0]);
            		rl.add(r);
            		for(int i=1;i<line.length;i++) {
            			if(!line[i].isEmpty()) {
            				r.setTask(header[i], Integer.parseInt(line[i]));
            			}

            		}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rl;
	}

	public void addResource(String r_name, Map<String,Integer> tasks) {
		Resource r = new Resource(r_name);
		for(String task:tasks.keySet()) {
			r.setTask(task, tasks.get(task));
		}
		this.resourceList.add(r);

	}
	
	public void recordLog(Set<Event> eventAtT) {
		for(Event event:eventAtT) {
			try {
				this.exporter.recordEvent(event);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
