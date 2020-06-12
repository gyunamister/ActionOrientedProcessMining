package org.processmining.EIS;

import org.processmining.framework.plugin.PluginContext;

public class EISAlgorithm {

	/**
	 * The method that implements your algorithm.
	 *
	 * Note that this method only uses the boolean which is stored in the parameters.
	 * Nevertheless, it could have used the integer and/or the String as well.
	 *
	 * @param context The context where to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @param parameters The parameters to use.
	 * @return The output.
	 */
	public void apply(PluginContext context, EISParameters parameters) {
		/**
		 * Put your algorithm here, which computes an output form the inputs provided the parameters.
		 */
		long time = -System.currentTimeMillis();
		parameters.displayMessage("[YourAlgorithm] Start");
		parameters.displayMessage("[YourAlgorithm] Parameters = " + parameters.toString());



	    //YourOutput output = parameters.isYourBoolean() ? new YourOutput(input1) : new YourOutput(input2);
		time += System.currentTimeMillis();
		parameters.displayMessage("[YourAlgorithm] Output = ");
		parameters.displayMessage("[YourAlgorithm] End (took " + time/1000.0 + "  seconds).");
	}
}
