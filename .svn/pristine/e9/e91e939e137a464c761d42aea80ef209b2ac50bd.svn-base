package org.processmining.actionorientedprocessmining.plugins;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.processmining.actionorientedprocessmining.models.ActionFormulaDefinition;
import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

@Plugin(name = "Import Action Formula Definition", parameterLabels = { "Filename" }, returnLabels = { "Action Formula Definition" }, returnTypes = { ActionFormulaDefinition.class })
@UIImportPlugin(description = "AFD files", extensions = { "adl" })
public class AFDImporter extends AbstractImportPlugin {
        protected Object importFromStream(PluginContext context, InputStream input, String filename, long fileSizeInBytes)
                        throws Exception {
        	ActionFormulaDefinition afd = new ActionFormulaDefinition();
        	StringWriter writer = new StringWriter();
    		try {
    			IOUtils.copy(input, writer);
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		String text = writer.toString();
        	afd.setAfMap(text);
                return afd;
        }
}
