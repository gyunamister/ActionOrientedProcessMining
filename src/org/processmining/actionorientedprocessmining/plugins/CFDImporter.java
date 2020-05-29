package org.processmining.actionorientedprocessmining.plugins;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.processmining.actionorientedprocessmining.models.ConstraintFormulaDefinition;
import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

@Plugin(name = "Import Constraint Formula Definition", parameterLabels = { "Filename" }, returnLabels = { "Constraint Formula Definition" }, returnTypes = { ConstraintFormulaDefinition.class })
@UIImportPlugin(description = "CFD files", extensions = { "cdl" })
public class CFDImporter extends AbstractImportPlugin {
        protected Object importFromStream(PluginContext context, InputStream input, String filename, long fileSizeInBytes)
                        throws Exception {
        	ConstraintFormulaDefinition cfd = new ConstraintFormulaDefinition();
        	StringWriter writer = new StringWriter();
    		try {
    			IOUtils.copy(input, writer);
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		String text = writer.toString();
        	cfd.setCfMap(text);
                return cfd;
        }
}
