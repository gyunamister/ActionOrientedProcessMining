package org.processmining.plugins.cpnet.plugins;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.cpntools.accesscpn.model.importer.NetCheckException;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.connections.petrinets.behavioral.InitialMarkingConnection;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.cpnet.ColouredPetriNet;
import org.processmining.plugins.cpnet.algorithms.FlattenAlgorithm;
import org.xml.sax.SAXException;

@Plugin( //
		name = "Flatten Coloured Petri net", //
		parameterLabels = { "Coloured Petri net" }, //
		returnLabels = { "Petri net" }, //
		returnTypes = { Petrinet.class }, //
		userAccessible = true, //
		icon = "prom_duck.png", //
		url = "http://www.win.tue.nl/~hverbeek/", //
		help = "Flatten the Coloured Petrinet into a Petri net" //
) //
public class FlattenPlugin extends FlattenAlgorithm {

	@UITopiaVariant( //
			affiliation = UITopiaVariant.EHV, //
			author = "H.M.W. Verbeek", //
			email = "h.m.w.verbeek@tue.nl" //
	) //
	@PluginVariant( //
			variantLabel = "Flatten Coloured Petri net", //
			requiredParameterLabels = { 0 } //
	) //
	public Petrinet run(PluginContext context, ColouredPetriNet cpn) {
		Petrinet net;
		try {
			net = PetrinetFactory.newPetrinet(cpn.getPetriNet().getName().asString());
		} catch (NetCheckException | SAXException | IOException | ParserConfigurationException e) {
			net = PetrinetFactory.newPetrinet("<no name>");
		}
		GraphLayoutConnection layout = new GraphLayoutConnection(net);
		layout.setLayedOut(true);
		Marking marking = new Marking();
		apply(cpn, net, layout, marking);
		context.getConnectionManager().addConnection(layout);
		context.getConnectionManager().addConnection(new InitialMarkingConnection(net, marking));
		return net;
	}

}
