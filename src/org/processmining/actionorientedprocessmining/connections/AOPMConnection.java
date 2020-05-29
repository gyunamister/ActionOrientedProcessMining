package org.processmining.actionorientedprocessmining.connections;

import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.actionorientedprocessmining.models.AOPMProgram;
import org.processmining.actionorientedprocessmining.models.ActionFormulaDefinition;
import org.processmining.actionorientedprocessmining.models.ConstraintFormulaDefinition;
import org.processmining.actionorientedprocessmining.models.ObjectCentricLog;
import org.processmining.actionorientedprocessmining.parameters.AOPMParameters;
import org.processmining.framework.connections.impl.AbstractConnection;

public class AOPMConnection extends AbstractConnection {

	/**
	 * Label for first input.
	 */
	public final static String FIRSTINPUT = "Information System";

	/**
	 * Label for second input.
	 */
	public final static String SECONDINPUT = "Constraint Formula Definition";
	public final static String THIRDINPUT = "Action Formula Definition";
	public final static String FOURTHINPUT = "Object Centric Log";
	/**
	 * Label for output.
	 */
	public final static String OUTPUT = "Output";

	/**
	 * Private copy of parameters.
	 */
	private AOPMParameters parameters;

	/**
	 * Create a connection.
	 * @param input1 First input.
	 * @param input2 Second input.
	 * @param output Output.
	 * @param parameters Parameters.
	 */
	public AOPMConnection(ProcessSimulation input1, ConstraintFormulaDefinition input2, ActionFormulaDefinition input3, AOPMProgram output, AOPMParameters parameters) {
		super("Your Connection");
		put(FIRSTINPUT, input1);
		put(SECONDINPUT, input2);
		put(THIRDINPUT, input3);
		put(OUTPUT, output);
		this.parameters = new AOPMParameters(parameters);
	}
	
	public AOPMConnection(ObjectCentricLog input4, ConstraintFormulaDefinition input2, ActionFormulaDefinition input3, AOPMProgram output, AOPMParameters parameters) {
		super("Your Connection");
		put(FOURTHINPUT, input4);
		put(SECONDINPUT, input2);
		put(THIRDINPUT, input3);
		put(OUTPUT, output);
		this.parameters = new AOPMParameters(parameters);
	}
	
	public AOPMConnection(ProcessSimulation input1, AOPMProgram output, AOPMParameters parameters) {
		super("Your Connection");
		put(FIRSTINPUT, input1);
		put(OUTPUT, output);
		this.parameters = new AOPMParameters(parameters);
	}
	
	public AOPMConnection(AOPMProgram output, AOPMParameters parameters) {
		super("Your Connection");
		put(OUTPUT, output);
		this.parameters = new AOPMParameters(parameters);
	}

	/**
	 *
	 * @return The parameters stored in the connection.
	 */
	public AOPMParameters getParameters() {
		return parameters;
	}
}
