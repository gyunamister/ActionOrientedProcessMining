package org.processmining.actionorientedprocessmining.parameters;

import org.processmining.actionorientedprocessmining.actionengine.AEConfig;
import org.processmining.actionorientedprocessmining.constraintmonitor.CMConfig;
import org.processmining.basicutils.parameters.impl.PluginParametersImpl;

public class AOPMParameters extends PluginParametersImpl {
	private String aisFilePath;
	public AEConfig aeConfig;
	public CMConfig cmConfig;
	
	public AOPMParameters() {
		super();
	}

	public AOPMParameters(AOPMParameters parameters) {
		super(parameters);
		setAisFilePath(parameters.getAisFilePath());
	}

	public boolean equals(Object object) {
		if (object instanceof AOPMParameters) {
			AOPMParameters parameters = (AOPMParameters) object;
			return super.equals(parameters) &&
					getAisFilePath().equals(parameters.getAisFilePath());
		}
		return false;
	}

	public void setAisFilePath(String aisFilePath) {
		System.out.println("AIS will be saved at " + aisFilePath);
		this.aisFilePath = aisFilePath;
	}

	public String getAisFilePath() {
		return aisFilePath;
	}

	public String toString() {
		return "(" + getAisFilePath() + ")";
	}
	
	public void updateAEConf(AEConfig aeConfig) {
		this.aeConfig = aeConfig;
	}
	
	public void updateCMConf(CMConfig cmConfig) {
		this.cmConfig = cmConfig;
	}
}
