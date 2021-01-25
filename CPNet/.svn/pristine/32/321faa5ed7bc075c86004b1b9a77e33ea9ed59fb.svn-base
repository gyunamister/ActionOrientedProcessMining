package org.processmining.plugins.cpnet.plugins;

import java.io.InputStream;

import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginLevel;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.plugins.cpnet.ColouredPetriNet;
import org.processmining.plugins.cpnet.LoadCPNModelFromFile;

@Plugin(name = "Import Petri net from CPNXML file", level = PluginLevel.PeerReviewed, parameterLabels = { "Filename" }, returnLabels = { "Petri Net"
}, returnTypes = { Petrinet.class })
@UIImportPlugin(description = "Petri net from CPNXML file", extensions = { "cpn" })
public class ImportPetrinetPlugin extends AbstractImportPlugin {

	public Petrinet importFromStream(final PluginContext context,
			final InputStream input, final String filename, final long fileSizeInBytes) throws Exception {
		ColouredPetriNet cpn = LoadCPNModelFromFile.importColouredPetriNetFromStream(context, input, filename, fileSizeInBytes);
		return (new FlattenPlugin().run(context, cpn));
	}
}
