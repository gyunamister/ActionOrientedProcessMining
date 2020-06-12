package org.processmining.EIS.Exporter;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.processmining.actionorientedprocessmining.event.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OCXMLExporter {
	public String filePath;
	
	public OCXMLExporter(String filePath){
		this.filePath=filePath;
	}
	public void recordEvent(Event event) throws Exception {
		Document document;
		File f = new File(this.filePath);
		
		Element root;
		if(f.exists() && !f.isDirectory()) { 
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
//            Document document = documentBuilder.newDocument();
            document = documentBuilder.parse(this.filePath);
            root = document.getDocumentElement();
		}else {
			//In the first writing
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            root = document.createElement("event-stream");
            document.appendChild(root);
		}
             
            Element e = document.createElement("event");
            e.setAttribute("event-id", event.getEventID());
            root.appendChild(e);
 
            // process-id element
            Element pid = document.createElement("process-id");
            pid.appendChild(document.createTextNode(event.getProcess()));
            e.appendChild(pid);
            
            Element aid = document.createElement("activity-id");
            aid.appendChild(document.createTextNode(event.getAct()));
            e.appendChild(aid);
            
            Element rid = document.createElement("resource-id");
            rid.appendChild(document.createTextNode(event.getRes()));
            e.appendChild(rid);
 
            Element timestamp = document.createElement("timestamp");
            timestamp.appendChild(document.createTextNode(Integer.toString(event.getTimestamp())));
            e.appendChild(timestamp);
            
            Element omap = document.createElement("object-mapping");
            e.appendChild(omap);
            for(String ocName:event.getOmap().keySet()) {
            	Element oc = document.createElement("object-class");
            	oc.setAttribute("type", ocName);
            	for(String oiName:event.getOmap().get(ocName)) {
            		Element oi = document.createElement("object-id");
            		oi.appendChild(document.createTextNode(oiName));
                    oc.appendChild(oi);
            	}
            	omap.appendChild(oc);
            }
 
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(this.filePath));
 
            // If you use
//            StreamResult result =  new StreamResult(System.out);
//            transformer.transform(domSource, result);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);

	}
	
//	public void appendToXML(OrderHandlingEvent event) throws Exception {
//            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
////            Document document = documentBuilder.newDocument();
//            Document document = documentBuilder.parse(this.filePath);
// 
//            // root element
//            Element root = document.createElement("event");
//            root.setAttribute("event-id", event.getEventID());
//            document.appendChild(root);
// 
//            // process-id element
//            Element pid = document.createElement("process-id");
//            pid.appendChild(document.createTextNode(event.getProcess()));
//            root.appendChild(pid);
//            
//            Element aid = document.createElement("activity-id");
//            aid.appendChild(document.createTextNode(event.getAct()));
//            root.appendChild(aid);
//            
//            Element rid = document.createElement("resource-id");
//            rid.appendChild(document.createTextNode(event.getRes()));
//            root.appendChild(rid);
// 
//            Element timestamp = document.createElement("timestamp");
//            timestamp.appendChild(document.createTextNode(Integer.toString(event.getTimestamp())));
//            root.appendChild(timestamp);
//            
//            Element omap = document.createElement("object-mapping");
//            root.appendChild(omap);
//            for(String ocName:event.getOmap().keySet()) {
//            	Element oc = document.createElement("object-class");
//                oc.appendChild(document.createTextNode(ocName));
//            	for(String oiName:event.getOmap().get(ocName)) {
//            		Element oi = document.createElement("object-id");
//            		oi.appendChild(document.createTextNode(oiName));
//                    oc.appendChild(oi);
//            	}
//            	omap.appendChild(oc);
//            }
// 
//            // create the xml file
//            //transform the DOM Object to an XML File
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File(this.filePath));
// 
//            // If you use
//            StreamResult result =  new StreamResult(System.out);
//            transformer.transform(domSource, result);
//            // the output will be pushed to the standard output ...
//            // You can use that for debugging 
// 
//            transformer.transform(domSource, streamResult);
// 
//            System.out.println("Done creating XML File");
//	}
}
