package org.processmining.plugins.cpnet;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.State;
import org.cpntools.accesscpn.engine.proxy.ProxyDaemon;
import org.cpntools.accesscpn.engine.proxy.ProxySimulator;
import org.cpntools.accesscpn.model.PetriNet;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.Progress;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

/**
 * @author michael
 * 
 */
@Plugin(name = "Load CPN Model from CPN Tools", keywords = {"CPN"}, parameterLabels = {}, returnLabels = { "CPN Tools Model" }, returnTypes = { ColouredPetriNet.class },
		help = "Load a CPN Model from a connection with CPN Tools.")
public class ScrapeModel {
	/**
	 * @param context
	 * @return
	 * @throws Exception
	 */
	@UITopiaVariant(uiLabel = "Load CPN Model from CPN Tools", affiliation = UITopiaVariant.EHV, author = "M. Westergaard", email = "m.westergaard@tue.nl", pack = "CPNet")
	@PluginVariant(requiredParameterLabels = {})
	public ColouredPetriNet scrape(final PluginContext context) throws Exception {
		final ProxyDaemon pd = ProxyDaemon.getDefaultInstance();
		pd.clear();
		final Progress progress = context.getProgress();
		context.log("Please start CPN Tools and load your model now");
		progress.setIndeterminate(true);
		final ProxySimulator ps = pd.getNext();
		context.log("Waiting for model to load");
		PetriNet petriNet;
		while ((petriNet = ps.getPetriNet()) == null) {
			try {
				Thread.sleep(200);
			} catch (final InterruptedException e) {
				// Mask interrupt
			}
		}
		context.getFutureResult(0).setLabel("CPN Tools Model (" + petriNet.getName().getText() + ")");
		final HighLevelSimulator simulator = ps.getSimulator();
		@SuppressWarnings("unused")
		final State initialMarking = simulator.getMarking();
		final ColouredPetriNet colouredPetriNet = new ColouredPetriNet(petriNet);
		colouredPetriNet.setSimulator(simulator);
		return colouredPetriNet;
	}

}
