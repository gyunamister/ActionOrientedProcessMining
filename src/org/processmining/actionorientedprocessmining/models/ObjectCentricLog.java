package org.processmining.actionorientedprocessmining.models;

public class ObjectCentricLog {
	String filePath;
	
	public String toString() {
		return "OCL loaded from " + this.filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
}
