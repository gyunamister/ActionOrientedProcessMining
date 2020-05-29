package org.processmining.actionorientedprocessmining.Exporter;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.processmining.actionorientedprocessmining.actionengine.ActionInstance;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AISExporter {
public String filePath;
	
	public AISExporter(String filePath){
		this.filePath=filePath;
	}
	public void recordAI(ActionInstance ai) throws Exception {
		Document document;
		File f = new File(this.filePath);
		System.out.println(this.filePath);
		System.out.println(ai);
		Element root;
		if(f.exists() && !f.isDirectory()) { 
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.parse(this.filePath);
            root = document.getDocumentElement();
		}else {
			//In the first writing
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            root = document.createElement("action-instance-stream");
            document.appendChild(root);
		}
             
            Element e = document.createElement("action-instance");
            root.appendChild(e);
 
            Element af = document.createElement("action-formula");
            af.appendChild(document.createTextNode(ai.getAfName()));
            e.appendChild(af);
            
            Element op = document.createElement("operation");
            op.appendChild(document.createTextNode(ai.getTrans().getOp()));
            e.appendChild(op);
            
            Element timestamp = document.createElement("timestamp");
            timestamp.appendChild(document.createTextNode(Integer.toString(ai.getTime())));
            e.appendChild(timestamp);
            
            Element pmap = document.createElement("paramter-mapping");
            e.appendChild(pmap);
            for(String pName:ai.getTrans().getPmap().keySet()) {
            	Element oc = document.createElement("parameter");
            	oc.setAttribute("name", pName);
            	String v = ai.getTrans().getPmap().get(pName);
            	Element value = document.createElement("value");
            	value.appendChild(document.createTextNode(v));
                oc.appendChild(value);
            	pmap.appendChild(oc);
            }
 
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(this.filePath));
 
            transformer.transform(domSource, streamResult);

	}
}
