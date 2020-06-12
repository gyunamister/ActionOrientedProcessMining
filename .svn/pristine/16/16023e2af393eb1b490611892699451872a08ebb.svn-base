package org.processmining.EIS;

import org.processmining.basicutils.parameters.impl.PluginParametersImpl;

public class EISParameters extends PluginParametersImpl {

	private boolean yourBoolean;
	private int yourInteger;
	private String yourString;
	
	public EISParameters() {
		super();
	}

	public EISParameters(EISParameters parameters) {
		super(parameters);
		setYourBoolean(parameters.isYourBoolean());
		setYourInteger(parameters.getYourInteger());
		setYourString(parameters.getYourString());
	}

	public boolean equals(Object object) {
		if (object instanceof EISParameters) {
			EISParameters parameters = (EISParameters) object;
			return super.equals(parameters) &&
					isYourBoolean() == parameters.isYourBoolean() &&
					getYourInteger() == parameters.getYourInteger() &&
					getYourString().equals(parameters.getYourString());
		}
		return false;
	}

	public void setYourBoolean(boolean yourBoolean) {
		this.yourBoolean = yourBoolean;
	}

	public boolean isYourBoolean() {
		return yourBoolean;
	}

	public void setYourInteger(int yourInteger) {
		this.yourInteger = yourInteger;
	}

	public int getYourInteger() {
		return yourInteger;
	}

	public void setYourString(String yourString) {
		this.yourString = yourString;
	}

	public String getYourString() {
		return yourString;
	}

	public String toString() {
		return "(" + getYourString() + "," + getYourInteger() + "," + isYourBoolean() + ")";
	}
}
