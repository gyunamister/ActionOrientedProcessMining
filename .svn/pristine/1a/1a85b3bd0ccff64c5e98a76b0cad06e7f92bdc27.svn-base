package org.processmining.actionorientedprocessmining.plugins;

import java.util.Collection;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.actionorientedprocessmining.algorithms.AOPMAlgorithm;
import org.processmining.actionorientedprocessmining.connections.AOPMConnection;
import org.processmining.actionorientedprocessmining.dialogs.InputDialog;
import org.processmining.actionorientedprocessmining.models.AOPMProgram;
import org.processmining.actionorientedprocessmining.models.ActionFormulaDefinition;
import org.processmining.actionorientedprocessmining.models.ConstraintFormulaDefinition;
import org.processmining.actionorientedprocessmining.models.ObjectCentricLog;
import org.processmining.actionorientedprocessmining.parameters.AOPMParameters;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

@Plugin(name = "ActionOrientedProcessMining", parameterLabels = { "Information System", "Object Centric Log", "Constraint Formula Definition", "Action Formula Definition" },
	    returnLabels = { "AOPM program" }, returnTypes = { AOPMProgram.class })
public class UserInput extends AOPMAlgorithm {

	/**
	 * The plug-in variant that runs in any context and requires a parameters.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @param parameters The parameters to use.
	 * @return The output.
	 */
//	@UITopiaVariant(affiliation = "Your affiliation", author = "Your name", email = "Your e-mail address")
//	@PluginVariant(variantLabel = "Your plug-in name, parameters", requiredParameterLabels = { 0, 1, 2 })
//	public AOPMProgram run(PluginContext context, YourFirstInput input1, YourSecondInput input2, YourParameters parameters) {
//		// Apply the algorithm depending on whether a connection already exists.
//	    return runConnections(context, input1, input2, parameters);
//	}

	/**
	 * The plug-in variant that runs in any context and uses the default parameters.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @return The output.
	 */
//	@UITopiaVariant(affiliation = "PADS", author = "Gyunam Park", email = "gnpark@pads.rwth-aachen.de")
//	@PluginVariant(variantLabel = "Your plug-in name, parameters", requiredParameterLabels = { 0, 1 })
//	public AOPMProgram runDefault(PluginContext context, YourFirstInput input1, YourSecondInput input2) {
//		// Get the default parameters.
//	    YourParameters parameters = new YourParameters(input1, input2);
//		// Apply the algorithm depending on whether a connection already exists.
//	    return runConnections(context, input1, input2, parameters);
//	}

	/**
	 * The plug-in variant that runs in a UI context and uses a dialog to get the parameters.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @return The output.
	 */
	@UITopiaVariant(affiliation = "PADS", author = "Gyunam Park", email = "gnpark@pads.rwth-aachen.de")
	@PluginVariant(variantLabel = "with the connected information system", requiredParameterLabels = {0, 2, 3})
	public AOPMProgram runUI(UIPluginContext context, ProcessSimulation pSim, ConstraintFormulaDefinition cfd, ActionFormulaDefinition afd) {
		// Get the default parameters.
	    AOPMParameters parameters = new AOPMParameters();
	    // Get a dialog for this parameters.
	    InputDialog dialog = new InputDialog(context, cfd,afd,parameters);
	    // Show the dialog. User can now change the parameters.
	    InteractionResult result = context.showWizard("Your dialog title", true, true, dialog);
	    // User has close the dialog.
	    if (result == InteractionResult.FINISHED) {
			// Apply the algorithm depending on whether a connection already exists.
	    	return runConnections(context, pSim, cfd, afd, parameters);
	    }
	    // Dialog got canceled.
	    return null;
	}

	/**
	 * The plug-in variant that allows one to test the dialog to get the parameters.
	 *
	 * @param context The context to run in.
	 * @return The output.
	 */
	@UITopiaVariant(affiliation = "PADS", author = "Gyunam Park", email = "gnpark@pads.rwth-aachen.de")
	@PluginVariant(variantLabel = "AOPM without the connected information system", requiredParameterLabels = {1,2,3})
	public AOPMProgram testUI(UIPluginContext context, ObjectCentricLog ocl,ConstraintFormulaDefinition cfd, ActionFormulaDefinition afd) {
		// Create default inputs.
		// Get the default parameters.
	    AOPMParameters parameters = new AOPMParameters();
	    // Get a dialog for this parameters.
	    InputDialog dialog = new InputDialog(context, cfd,afd, parameters);
	    // Show the dialog. User can now change the parameters.
	    InteractionResult result = context.showWizard("AOPM - Configurations", true, true, dialog);
	    // User has close the dialog.
	    if (result == InteractionResult.FINISHED) {
			// Apply the algorithm depending on whether a connection already exists.
	    	return runConnections(context, ocl, cfd, afd, parameters);
	    }
	    // Dialog got canceled. 
	    return null;
	}

	/**
	 * Apply the algorithm depending on whether a connection already exists.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @return The output.
	 */
//	private AOPMProgram runConnections(PluginContext context, YourFirstInput input1, YourSecondInput input2, YourParameters parameters) {
//		if (parameters.isTryConnections()) {
//			// Try to found a connection that matches the inputs and the parameters.
//			Collection<AOPMConnection> connections;
//			try {
//				connections = context.getConnectionManager().getConnections(
//						AOPMConnection.class, context, input1, input2);
//				for (AOPMConnection connection : connections) {
//					if (connection.getObjectWithRole(AOPMConnection.FIRSTINPUT)
//							.equals(input1) && connection.getObjectWithRole(AOPMConnection.SECONDINPUT)
//							.equals(input2) && connection.getParameters().equals(parameters)) {
//						// Found a match. Return the associated output as result of the algorithm.
//						return connection
//								.getObjectWithRole(AOPMConnection.OUTPUT);
//					}
//				}
//			} catch (ConnectionCannotBeObtained e) {
//			}
//		}
//		// No connection found. Apply the algorithm to compute a fresh output result.
//		AOPMProgram output = apply(context, input1, input2, parameters);
//		if (parameters.isTryConnections()) {
//			// Store a connection containing the inputs, output, and parameters.
//			context.getConnectionManager().addConnection(
//					new AOPMConnection(input1, input2, output, parameters));
//		}
//		// Return the output.
//		return output;
//	} 

	private AOPMProgram runConnections(PluginContext context, ObjectCentricLog ocl, ConstraintFormulaDefinition cfd, ActionFormulaDefinition afd, AOPMParameters parameters) {
		if (parameters.isTryConnections()) {
			// Try to found a connection that matches the inputs and the parameters.
			Collection<AOPMConnection> connections;
			try {
				connections = context.getConnectionManager().getConnections(
						AOPMConnection.class, context, ocl);
				for (AOPMConnection connection : connections) {
					if (connection.getObjectWithRole(AOPMConnection.FOURTHINPUT)
							.equals(ocl) && connection.getObjectWithRole(AOPMConnection.SECONDINPUT)
							.equals(cfd) && connection.getObjectWithRole(AOPMConnection.THIRDINPUT)
							.equals(afd) && connection.getParameters().equals(parameters)) {
						// Found a match. Return the associated output as result of the algorithm.
						return connection
								.getObjectWithRole(AOPMConnection.OUTPUT);
					}
				}
			} catch (ConnectionCannotBeObtained e) {
			}
		}
		// No connection found. Apply the algorithm to compute a fresh output result.
		AOPMProgram output = apply(context, ocl, parameters);
		if (parameters.isTryConnections()) {
			// Store a connection containing the inputs, output, and parameters.
			context.getConnectionManager().addConnection(
					new AOPMConnection(ocl, cfd, afd, output, parameters));
		}
		// Return the output.
		return output;
	}
	
	private AOPMProgram runConnections(PluginContext context, ProcessSimulation pSim, ConstraintFormulaDefinition cfd, ActionFormulaDefinition afd, AOPMParameters parameters) {
		if (parameters.isTryConnections()) {
			// Try to found a connection that matches the inputs and the parameters.
			Collection<AOPMConnection> connections;
			try {
				connections = context.getConnectionManager().getConnections(
						AOPMConnection.class, context, pSim, cfd, afd);
				for (AOPMConnection connection : connections) {
					if (connection.getObjectWithRole(AOPMConnection.FIRSTINPUT)
							.equals(pSim) && connection.getObjectWithRole(AOPMConnection.SECONDINPUT)
							.equals(cfd) && connection.getObjectWithRole(AOPMConnection.THIRDINPUT)
							.equals(afd) && connection.getParameters().equals(parameters)) {
						// Found a match. Return the associated output as result of the algorithm.
						return connection
								.getObjectWithRole(AOPMConnection.OUTPUT);
					}
				}
			} catch (ConnectionCannotBeObtained e) {
			}
		}
		// No connection found. Apply the algorithm to compute a fresh output result.
		AOPMProgram output = apply(context, pSim, parameters);
		if (parameters.isTryConnections()) {
			// Store a connection containing the inputs, output, and parameters.
			context.getConnectionManager().addConnection(
					new AOPMConnection(pSim, cfd, afd, output, parameters));
		}
		// Return the output.
		return output;
	}

//	private AOPMProgram runConnections(PluginContext context, YourParameters parameters) {
//		if (parameters.isTryConnections()) {
//			// Try to found a connection that matches the inputs and the parameters.
//			Collection<AOPMConnection> connections;
//			try {
//				connections = context.getConnectionManager().getConnections(
//						AOPMConnection.class, context);
//				for (AOPMConnection connection : connections) {
//					if (connection.getParameters().equals(parameters)) {
//						// Found a match. Return the associated output as result of the algorithm.
//						return connection
//								.getObjectWithRole(AOPMConnection.OUTPUT);
//					}
//				}
//			} catch (ConnectionCannotBeObtained e) {
//			}
//		}
//		// No connection found. Apply the algorithm to compute a fresh output result.
//		AOPMProgram output = apply(context, parameters);
//		if (parameters.isTryConnections()) {
//			// Store a connection containing the inputs, output, and parameters.
//			context.getConnectionManager().addConnection(
//					new AOPMConnection(output, parameters));
//		}
//		// Return the output.
//		return output;
//	}

}
