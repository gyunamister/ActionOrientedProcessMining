package org.processmining.actionorientedprocessmining.Importer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.processmining.actionorientedprocessmining.event.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opencsv.CSVReader;

public class OCXMLImporter {
	public String filePath;
	
	public OCXMLImporter(String filePath){
		this.filePath=filePath;
	}
	
	public Set<Event> readOCXML(int t) {
		Set<Event> eventAtT = new HashSet<Event>();
		try {
	         File inputFile = new File(this.filePath);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("event");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               String eid = eElement.getAttribute("event-id");
	               String tempTimestamp = eElement.getElementsByTagName("timestamp").item(0).getTextContent();
	               int timestamp = Integer.valueOf(tempTimestamp);
	               if(timestamp!=t) {
	            	   continue;
	               }

	               String pid = eElement.getElementsByTagName("process-id").item(0).getTextContent();

	               String aid = eElement.getElementsByTagName("activity-id").item(0).getTextContent();

	               String rid = eElement.getElementsByTagName("resource-id").item(0).getTextContent();
	               Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
	               Element tempOmap = (Element) eElement.getElementsByTagName("object-mapping").item(0);
	               for(int i=0;i<tempOmap.getElementsByTagName("object-class").getLength();i++) {
	            	   Element oc = (Element) tempOmap.getElementsByTagName("object-class").item(i);
	            	   String ocName = oc.getAttribute("type");
	            	   Set<String> objectSet = new HashSet<String>();
	            	   for(int j=0;j<oc.getElementsByTagName("object-id").getLength();j++) {
	            		   objectSet.add(oc.getElementsByTagName("object-id").item(j).getTextContent());
	            	   }
	            	   omap.put(ocName,objectSet);
	               }
	               Event event = new Event(eid,pid,aid,rid,timestamp,omap);
	               eventAtT.add(event);
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		return eventAtT;
	}
	
	public Set<Event> readCSV(int time) {
		Set<Event> eventAtT = new HashSet<Event>();  
		try {
			CSVReader csvReader = new CSVReader(new FileReader("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/IS_OHP/eventlog.csv"));
			String[] row;
			int e = -1;
			while ((row = csvReader.readNext()) != null) {
				e+=1;
				if(e==0) {
					continue;
				}
				String[] objectNames = {"Order", "Item", "Package", "Route"};
				Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
				for(int i=5;i<row.length;i++) {
					Set<String> objectSet = new HashSet<String>();
					for(String s:row[i].split("&")) {
						objectSet.add(s);
					}
					omap.put(objectNames[i-5], objectSet);
				}
				//Read the events at time t
				if(time==Integer.parseInt(row[4])) {
					Event event = new Event(row[0], row[1], row[2], row[3], Integer.parseInt(row[4]),omap);
					eventAtT.add(event);
				}
			}
			csvReader.close();
		}catch (IOException ex) {
			System.out.println(ex);
		}
		return eventAtT;
	}
}
