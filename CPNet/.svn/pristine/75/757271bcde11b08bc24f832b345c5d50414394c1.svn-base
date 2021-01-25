package org.processmining.plugins.cpnet;

import java.io.InputStream;

import org.cpntools.accesscpn.engine.highlevel.utils.StreamUtillities;
import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

/**
 * @author michael svzelst@20151005: add public static access method (intended
 *         use from RapdiMiner)
 */
@Plugin(name = "CPN Tools Model", parameterLabels = { "Filename" }, returnLabels = {
		"CPN Tools Model" }, returnTypes = { ColouredPetriNet.class })
@UIImportPlugin(description = "CPN Tools model", extensions = { "cpn" })
public class LoadCPNModelFromFile extends AbstractImportPlugin {

	public static ColouredPetriNet importColouredPetriNetFromStream(final PluginContext context,
			final InputStream input, final String filename, final long fileSizeInBytes) throws Exception {
		String modelName = null;
		if (filename != null) {
			modelName = filename.replaceAll("[.]cpn$", "").replaceAll(".*[/\\\\]", "");
		}
		if (modelName != null) {
			context.log("Parsing File (" + modelName + ")");
		} else {
			context.log("Parsing File");
		}
		final ColouredPetriNet colouredPetriNet = new ColouredPetriNet(StreamUtillities.copyToString(input), modelName);
		colouredPetriNet.getPetriNet();
		return colouredPetriNet;
	}

	@Override
	protected ColouredPetriNet importFromStream(final PluginContext context, final InputStream input,
			final String filename, final long fileSizeInBytes) throws Exception {
		String modelName = null;
		if (filename != null) {
			modelName = filename.replaceAll("[.]cpn$", "").replaceAll(".*[/\\\\]", "");
		}
		if (modelName != null) {
			context.log("Parsing File (" + modelName + ")");
		} else {
			context.log("Parsing File");
		}
		final ColouredPetriNet colouredPetriNet = new ColouredPetriNet(StreamUtillities.copyToString(input), modelName);
		colouredPetriNet.getPetriNet();
		if (modelName != null) {
			context.getFutureResult(0).setLabel("CPN Tools Model (" + modelName + ")");
		}
		return colouredPetriNet;
	}
}
