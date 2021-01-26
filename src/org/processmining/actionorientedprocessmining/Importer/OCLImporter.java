package org.processmining.actionorientedprocessmining.Importer;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.processmining.actionorientedprocessmining.event.Event;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OCLImporter {
public String filePath;
	
	public OCLImporter(String filePath){
		this.filePath=filePath;
	}
	
	public Set<Event> readOCL() {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(this.filePath);
		Set<Event> eventSet = new HashSet<Event>();
		try {
			eventSet = objectMapper.readValue(file, new TypeReference<Set<Event>>(){});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eventSet;
	}
	
	public Set<Event> getEventsAtT(int t) {
		Set<Event> eventAtT = new HashSet<Event>();
		Set<Event> eventSet = this.readOCL();
		eventAtT = eventSet.stream().filter(event -> event.getTimestamp()==t).collect(Collectors.toSet());
		return eventAtT;
	}
	
	public int getTimeOffset() {
		Set<Event> eventSet = this.readOCL();
		Event firstEvent = eventSet
			      .stream()
			      .min(Comparator.comparing(Event::getTimestamp))
			      .orElseThrow(NoSuchElementException::new);
		int startTime = firstEvent.getTimestamp();
		return startTime;
	}
	
	public static void main(String[] arg) {
		String filePath="/Users/gyunam/Documents/experiments/2020-JoDS/logs/log2.jsonocel";
		OCLImporter oclImporter = new OCLImporter(filePath);
		Set<Event> eventSet = oclImporter.readOCL();
		for(Event event:eventSet) {
			System.out.println(event);
			System.out.println(event.getTimestamp());
		}
	}
}
