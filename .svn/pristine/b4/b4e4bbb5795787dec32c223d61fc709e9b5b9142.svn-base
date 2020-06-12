package org.processmining.EIS;

import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

@Plugin(name = "Synthetic Information System", parameterLabels = { "Not specified", "Not specified", "Simulator settings" },
	    returnLabels = { "Synthetic Information System" }, returnTypes = { ProcessSimulation.class })
public class ExampleInformationSystem extends EISAlgorithm {
	/**
	 * The plug-in variant that allows one to test the dialog to get the parameters.
	 *
	 * @param context The context to run in.
	 * @return The output.
	 */
	@UITopiaVariant(affiliation = "PADS", author = "Gyunam Park", email = "gnpark@pads.rwth-aachen.de")
	@PluginVariant(variantLabel = "Synthetic Information System", requiredParameterLabels = { })
	public ProcessSimulation testUI(UIPluginContext context) {
		// Create default inputs.
		ProcessSimulation pSim = new ProcessSimulation();
//	    InputDialog dialog = new InputDialog(context);
	    // Show the dialog. User can now change the parameters.
//	    InteractionResult result = context.showWizard("AOPM - Simulator settings", true, true, dialog);
	    // User has close the dialog.
//	    if (result == InteractionResult.FINISHED) {
//			// Apply the algorithm depending on whether a connection already exists.
//	    	return runConnections(context, input2, parameters);
//	    }
	    // Dialog got canceled. 
	    return pSim;
	}

	/**
	 * Apply the algorithm depending on whether a connection already exists.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @return The output.
	 */
//	private Simulator runConnections(PluginContext context, YourFirstInput input1, YourSecondInput input2, YourParameters parameters) {
//		if (parameters.isTryConnections()) {
//			// Try to found a connection that matches the inputs and the parameters.
//			Collection<YourConnection> connections;
//			try {
//				connections = context.getConnectionManager().getConnections(
//						YourConnection.class, context, input1, input2);
//				for (YourConnection connection : connections) {
//					if (connection.getObjectWithRole(YourConnection.FIRSTINPUT)
//							.equals(input1) && connection.getObjectWithRole(YourConnection.SECONDINPUT)
//							.equals(input2) && connection.getParameters().equals(parameters)) {
//						// Found a match. Return the associated output as result of the algorithm.
//						return connection
//								.getObjectWithRole(YourConnection.OUTPUT);
//					}
//				}
//			} catch (ConnectionCannotBeObtained e) {
//			}
//		}
//		// No connection found. Apply the algorithm to compute a fresh output result.
//		Simulator output = apply(context, input1, input2, parameters);
//		if (parameters.isTryConnections()) {
//			// Store a connection containing the inputs, output, and parameters.
//			context.getConnectionManager().addConnection(
//					new YourConnection(input1, input2, output, parameters));
//		}
//		// Return the output.
//		return output;
//	}
}