package org.processmining.actionorientedprocessmining.plugins;


import java.io.InputStream;

import org.processmining.actionorientedprocessmining.models.ObjectCentricLog;
import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

@Plugin(name = "Import Object-Centric Log", parameterLabels = { "Filename" }, returnLabels = { "Object-Centric Log" }, returnTypes = { ObjectCentricLog.class })
@UIImportPlugin(description = "OCL files", extensions = { "xml" })
public class OCLImporter extends AbstractImportPlugin {
        protected Object importFromStream(PluginContext context, InputStream input, String filename, long fileSizeInBytes)
                        throws Exception {
        	ObjectCentricLog ocl = new ObjectCentricLog();
        	ocl.setFilePath(getFile().getPath());
        	System.out.println("At " + this + ", Event Stream recorded at " + ocl.getFilePath());
            return ocl;
        }
}
