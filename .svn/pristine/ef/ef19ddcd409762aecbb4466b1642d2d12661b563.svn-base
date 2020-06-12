package org.processmining.actionorientedprocessmining.algorithms;

import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.actionorientedprocessmining.models.AOPMProgram;
import org.processmining.actionorientedprocessmining.models.ObjectCentricLog;
import org.processmining.actionorientedprocessmining.parameters.AOPMParameters;
import org.processmining.framework.plugin.PluginContext;

public class AOPMAlgorithm {

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
//	public Simulator apply(PluginContext context, YourFirstInput input1, YourSecondInput input2, YourParameters parameters) {
//		/**
//		 * Put your algorithm here, which computes an output form the inputs provided the parameters.
//		 */
//		long time = -System.currentTimeMillis();
//		parameters.displayMessage("[YourAlgorithm] Start");
//		parameters.displayMessage("[YourAlgorithm] First input = " + input1.toString());
//		parameters.displayMessage("[YourAlgorithm] Second input = " + input2.toString());
//		parameters.displayMessage("[YourAlgorithm] Parameters = " + parameters.toString());
//		
//		Simulator aopmProgram = new Simulator(parameters.cmConfig, parameters.aeConfig);
//		
//	    //YourOutput output = parameters.isYourBoolean() ? new YourOutput(input1) : new YourOutput(input2);
//		time += System.currentTimeMillis();
//		parameters.displayMessage("[YourAlgorithm] Output = " + aopmProgram.toString());
//		parameters.displayMessage("[YourAlgorithm] End (took " + time/1000.0 + "  seconds).");
//	    return aopmProgram;
//	}
	
	public AOPMProgram apply(PluginContext context, ProcessSimulation pSim, AOPMParameters parameters) {
		/**
		 * Put your algorithm here, which computes an output form the inputs provided the parameters.
		 */
		long time = -System.currentTimeMillis();
		parameters.displayMessage("[AOPM] Start");
		parameters.displayMessage("[AOPM] First input = " + pSim.toString());
		parameters.displayMessage("[AOPM] Second input = " + "Constraint Formula Definition");
		parameters.displayMessage("[AOPM] Parameters = " + "Action Formula Definition");
		
		AOPMProgram aopmProgram = new AOPMProgram(parameters.cmConfig, parameters.aeConfig,pSim,parameters.getAisFilePath());
		
	    //YourOutput output = parameters.isYourBoolean() ? new YourOutput(input1) : new YourOutput(input2);
		time += System.currentTimeMillis();
		parameters.displayMessage("[AOPM] Output = " + aopmProgram.toString());
		parameters.displayMessage("[AOPM] End (took " + time/1000.0 + "  seconds).");
	    return aopmProgram;
	}
	
	public AOPMProgram apply(PluginContext context, ObjectCentricLog ocl, AOPMParameters parameters) {
		/**
		 * Put your algorithm here, which computes an output form the inputs provided the parameters.
		 */
		long time = -System.currentTimeMillis();
		parameters.displayMessage("[AOPM] Start");
		parameters.displayMessage("[AOPM] First input = " + ocl.toString());
		parameters.displayMessage("[AOPM] Second input = " + "Constraint Formula Definition");
		parameters.displayMessage("[AOPM] Parameters = " + "Action Formula Definition");
		
		AOPMProgram aopmProgram = new AOPMProgram(parameters.cmConfig, parameters.aeConfig,ocl,parameters.getAisFilePath());
		
	    //YourOutput output = parameters.isYourBoolean() ? new YourOutput(input1) : new YourOutput(input2);
		time += System.currentTimeMillis();
		parameters.displayMessage("[AOPM] Output = " + aopmProgram.toString());
		parameters.displayMessage("[AOPM] End (took " + time/1000.0 + "  seconds).");
	    return aopmProgram;
	}
}
