package org.processmining.plugins.cpnet;

import java.math.BigInteger;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Binding;
import org.cpntools.accesscpn.engine.highlevel.instance.State;
import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XLifecycleExtension;
import org.deckfour.xes.extension.std.XLifecycleExtension.StandardModel;
import org.deckfour.xes.extension.std.XOrganizationalExtension;
import org.deckfour.xes.extension.std.XTimeExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryRegistry;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.plugins.cpnet.utils.CPNPageStripper;
import org.processmining.plugins.cpnet.utils.TransitionUtils;

/**
 * @author michael
 * 
 */
@Plugin(name = "Simulate CPN Model", parameterLabels = { "CPN Tools Model", "Start Marking", "Steps", "Repetitions",
		"Delay" }, returnLabels = { "Simulation log" }, returnTypes = { XLog.class }, help = "Simulate a CPN Model.")
public class SimulateCPNModel {
	/**
	 * @param context
	 * @param petriNet
	 * @param startState
	 * @return
	 * @throws Exception
	 */
	@PluginVariant(requiredParameterLabels = { 0, 1 })
	public XLog simulate(final PluginContext context, final ColouredPetriNet petriNet, final State startState)
			throws Exception {
		return simulate(context, petriNet, startState, -1, 1, 0);
	}

	/**
	 * @param context
	 * @param petriNet
	 * @param startState
	 * @param steps
	 * @return
	 * @throws Exception
	 */
	@PluginVariant(requiredParameterLabels = { 0, 1, 2 })
	public XLog simulate(final PluginContext context, final ColouredPetriNet petriNet, final State startState,
			final int steps) throws Exception {
		return simulate(context, petriNet, startState, steps, 1, 1000);
	}

	/**
	 * @param context
	 * @param petriNet
	 * @param startState
	 * @param steps
	 * @param delay
	 * @return
	 * @throws Exception
	 */
	@PluginVariant(requiredParameterLabels = { 0, 1, 2, 4 })
	public XLog simulate(final PluginContext context, final ColouredPetriNet petriNet, final State startState,
			final int steps, final int delay) throws Exception {
		return simulate(context, petriNet, startState, steps, 1, delay);
	}

	/**
	 * @param context
	 * @param petriNet
	 * @param startState
	 * @param steps
	 * @param repetitions
	 * @param delay
	 * @return
	 * @throws Exception
	 */
	@PluginVariant(requiredParameterLabels = { 0, 1, 2, 3, 4 })
	public XLog simulate(final PluginContext context, final ColouredPetriNet petriNet, final State startState,
			final int steps, final int repetitions, final int delay) throws Exception {
		return simulate(context, petriNet, startState, steps, repetitions, delay, delay > 0, 0, 1000);
	}

	/**
	 * @param context
	 * @param petriNet
	 * @return
	 * @throws Exception
	 */
	@UITopiaVariant(uiLabel = "Simulate CPN Model", affiliation = UITopiaVariant.EHV, author = "M. Westergaard", email = "m.westergaard@tue.nl", pack = "CPNet")
	@PluginVariant(requiredParameterLabels = { 0 })
	public XLog simulate(final UIPluginContext context, final ColouredPetriNet petriNet) throws Exception {
		petriNet.setContext(context);
		petriNet.getSimulator().initialState();
		return simulate(context, petriNet, petriNet.getSimulator().getMarking());
	}

	/**
	 * @param context
	 * @param petriNet
	 * @param startState
	 * @return
	 * @throws Exception
	 */
	@UITopiaVariant(uiLabel = "Simulate CPN Model", affiliation = UITopiaVariant.EHV, author = "M. Westergaard", email = "m.westergaard@tue.nl", pack = "CPNet")
	@PluginVariant(requiredParameterLabels = { 0, 1 })
	public XLog simulate(final UIPluginContext context, final ColouredPetriNet petriNet, final State startState)
			throws Exception {
		final SimulationPropertiesPanel panel = new SimulationPropertiesPanel(true, true, true, true,
				petriNet.getPetriNet().isTimed(), true, true, true);
		final InteractionResult interactionResult = context.showConfiguration("Simulation Options", panel);
		if (interactionResult == InteractionResult.FINISHED || interactionResult == InteractionResult.CONTINUE) {
			return simulate(context, petriNet, startState, panel.getSteps(), panel.getRepetitions(), panel.getDelay(),
					panel.getUpdateUI(), panel.getOffset().getTimeInMillis(), panel.getGranularity(),
					panel.isUseLifeCycleTransitionExtendsion(), panel.isIgnorePage(), panel.getIgnorePatterns());
		}
		context.getProgress().cancel();
		return null;
	}

	protected XLog simulate(final PluginContext context, final ColouredPetriNet petriNet, final State startState,
			final int steps, final int repetitions, final int delay, final boolean showFeedback, final long base,
			final long multiplier) throws Exception {
		return simulate(context, petriNet, startState, steps, repetitions, delay, showFeedback, base, multiplier, false,
				false, new String[] {});
	}

	protected XLog simulate(final PluginContext context, final ColouredPetriNet petriNet, final State startState,
			final int steps, final int repetitions, final int delay, final boolean showFeedback, final long base,
			final long multiplier, final boolean isLifeCycleTransition, final boolean ignorePage,
			final String[] ignorePatterns) throws Exception {
		petriNet.setContext(context);
		final HighLevelSimulator simulator = petriNet.getSimulator();
		context.getProgress().setMinimum(0);
		context.getProgress().setMaximum(1);
		if (steps >= 0) {
			context.getProgress().setMaximum(steps * repetitions);
		} else {
			context.getProgress().setIndeterminate(false);
			context.getProgress().setIndeterminate(true);
		}

		final XFactory factory = XFactoryRegistry.instance().currentDefault();
		final XLog log = factory.createLog();

		if (repetitions > 1) {
			context.log("Simulating (1/" + repetitions + ")");
		} else {
			context.log("Simulating");
		}
		CPNPageStripper pageStripper = (ignorePage ? new CPNPageStripper(petriNet.getPetriNet()) : null);
		for (int repetition = 0; repetition < repetitions && !context.getProgress().isCancelled(); repetition++) {

			final XTrace trace = factory.createTrace();
			XConceptExtension.instance().assignName(trace, "Run " + (repetition + 1));

			simulator.initialState();
			simulator.setMarking(startState);
			simulator.refreshViews();

			long step = 0L;
			long lastTime = 0L;
			final boolean timed = petriNet.getPetriNet().isTimed();
			BigInteger lastStep = BigInteger.valueOf(-1);
			while ((step < steps || steps < 0) && !lastStep.equals(simulator.getStep())
					&& !context.getProgress().isCancelled()) {
				lastStep = simulator.getStep();
				if (steps % 100 == 0) {
					if (steps >= 0) {
						context.getProgress().setValue((int) (repetition * steps + step));
					} else {
						context.log("Simulating - step " + lastStep);
					}
				}
				final Binding binding = simulator.executeAndGet();
				if (binding != null) {
					String eventName = getTransitionName(binding, pageStripper);
					if (!shouldConsiderIgnorePatterns(ignorePatterns)
							|| !TransitionUtils.containsIgnorePattern(eventName, ignorePatterns)) {
						XEvent event = factory.createEvent();
						XConceptExtension.instance().assignName(event, eventName);
						XConceptExtension.instance().assignInstance(event, binding.toString());
						XOrganizationalExtension.instance().assignResource(event,
								binding.getTransitionInstance().getNode().getName().getText());
						XOrganizationalExtension.instance().assignRole(event, "Transition");
						if (timed) {
							XTimeExtension.instance().assignTimestamp(event, base + lastTime * multiplier);
						}
						if (isLifeCycleTransition) {
							XLifecycleExtension.instance().assignStandardTransition(event, StandardModel.START);
							trace.add(event);

							event = factory.createEvent();
							XConceptExtension.instance().assignName(event, eventName);
							XConceptExtension.instance().assignInstance(event, binding.toString());
							XLifecycleExtension.instance().assignStandardTransition(event, StandardModel.COMPLETE);
							XOrganizationalExtension.instance().assignResource(event,
									binding.getTransitionInstance().getNode().getName().getText());
							XOrganizationalExtension.instance().assignRole(event, "Transition");
							if (timed) {
								lastTime = new BigInteger(simulator.getTime()).longValue();
								XTimeExtension.instance().assignTimestamp(event, base + lastTime * multiplier);
							}
							trace.add(event);
						} else {
							trace.add(event);
						}
					}
					if (showFeedback) {
						simulator.refreshViews();
					}
					step++;
					try {
						Thread.sleep(delay);
					} catch (final InterruptedException e) {
						// Ignore interrupt
					}
				}
			}
			context.log("Simulated (" + (repetition + 1) + "/" + repetitions + "); " + lastStep + " step"
					+ (lastStep.equals(BigInteger.ONE) ? "" : "s"));
			log.add(trace);
		}
		simulator.refreshViews();
		return log;
	}

	private boolean shouldConsiderIgnorePatterns(final String[] patterns) {
		return !(patterns.length == 0 || (patterns.length == 1 && patterns[0].equals("")));
	}

	private String getTransitionName(Binding binding, CPNPageStripper pageStripper) {
		String result = binding.getTransitionInstance().toString();
		if (pageStripper != null) {
			result = pageStripper.convertToStringWithoutPages(binding.getTransitionInstance());
		}
		return result;
	}

}
