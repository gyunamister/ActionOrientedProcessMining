package org.processmining.plugins.cpnet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.cpntools.accesscpn.model.Page;
import org.cpntools.accesscpn.model.importer.NetCheckException;
import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;
import org.processmining.framework.util.ui.scalableview.ScalableViewPanel;
import org.processmining.framework.util.ui.scalableview.interaction.PIPInteractionPanel;
import org.processmining.framework.util.ui.scalableview.interaction.ZoomInteractionPanel;
import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.graphbased.ViewSpecificAttributeMap;
import org.processmining.models.jgraph.ProMGraphModel;
import org.processmining.models.jgraph.ProMJGraph;
import org.processmining.models.jgraph.visualization.ProMJGraphPanel;
import org.processmining.plugins.cpnet.visualization.Graphs;
import org.processmining.plugins.cpnet.visualization.PageswitchPanel;
import org.processmining.plugins.cpnet.visualization.PageswitchPanel.Updater;
import org.processmining.plugins.cpnet.visualization.TypesPanel;
import org.xml.sax.SAXException;

import com.jgraph.layout.JGraphFacade;

/**
 * Visualizes coloured Petri net
 */

@Plugin(name = "Visualize Coloured Petri net", returnLabels = { "Visualized ColouredPetrinet" }, returnTypes = { JComponent.class }, parameterLabels = {
		"Coloured Petri net", "Initial Marking" }, userAccessible = true)
@Visualizer
public class DrawCPNGraph {

	class PageUpdater implements Updater {
		int displayed_page = 0;

		ColouredPetriNet net;

		ProMJGraphPanel panel;
		PluginContext context;

		PageUpdater(final ColouredPetriNet n, final ProMJGraphPanel p, final PluginContext c) {
			net = n;
			panel = p;
			context = c;
		}

		@Override
		public void set_page(final int page_numero) {
			DrawCPNGraph.cout("Requested page " + page_numero);
			if (page_numero == displayed_page) {
				return;
			}
			displayed_page = page_numero;
			//			context.getConnectionManager() how to remove an old connection?
			// Black magic begins
			try {
				final CPNGraph graph = new CPNGraph(net, page_numero);
				final ProMJGraph jgraph = visualizeGraph(context, graph, new ViewSpecificAttributeMap());

				final Field componentField = ScalableViewPanel.class.getDeclaredField("component");
				componentField.setAccessible(true);
				final Field scalableField = ScalableViewPanel.class.getDeclaredField("component");
				scalableField.setAccessible(true);

				scalableField.set(panel, jgraph);
				componentField.set(panel, jgraph.getComponent());
				panel.repaint();
			} catch (final Exception x) {
				DrawCPNGraph.cerr("Failed to show a page: " + x);
			}
			// Black magic done
		}
	}

	static <T> void cerr(final T t) {
		System.err.println(t);
	}

	static <T> void cout(final T t) {
		System.out.println(t);
	}

	/**
	 * @param context
	 * @param n
	 * @return
	 * @throws NetCheckException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	@PluginVariant(requiredParameterLabels = { 0 })
	public JComponent visualize(final PluginContext context, final ColouredPetriNet n) throws NetCheckException,
			SAXException, IOException, ParserConfigurationException {
		return drawGraph(n, context);
	}

	private JComponent drawGraph(final ColouredPetriNet n, final PluginContext context) throws NetCheckException,
			SAXException, IOException, ParserConfigurationException {
		if (n.getPetriNet().getPage().size() < 1) {
			return new JTextArea("The net contains " + n.getPetriNet().getPage().size() + " pages. \n\n"
					+ "No visualization can be created.");
		}

		try {
			final CPNGraph graph = new CPNGraph(n, 0);
			final ProMJGraph jgraph = visualizeGraph(context, graph, new ViewSpecificAttributeMap());
			final ProMJGraphPanel panel = new ProMJGraphPanel(jgraph);
			panel.addViewInteractionPanel(new PIPInteractionPanel(panel), SwingConstants.NORTH);
			panel.addViewInteractionPanel(new ZoomInteractionPanel(panel, ScalableViewPanel.MAX_ZOOM),
					SwingConstants.WEST);
			final String sorts = graph.getSortsInfo();
			if (sorts != "") {
				panel.addViewInteractionPanel(new TypesPanel(panel, graph.getSortsInfo()), SwingConstants.NORTH);
			}

			//The line bellow allows only to obtain a segfault upon clicking «Export».
			//		panel.addViewInteractionPanel(new ExportInteractionPanel(panel), SwingConstants.SOUTH);

			final List<String> pageNames = new ArrayList<String>();
			final List<Page> pages = n.getPetriNet().getPage();
			if (pages.size() > 1) {
				for (final Page p : pages) {
					pageNames.add(p.getName().asString());
				}
				panel.addViewInteractionPanel(
						new PageswitchPanel(panel, pageNames, new PageUpdater(n, panel, context)), SwingConstants.NORTH);
			}
			return panel;
		} catch (final Exception x) {
			return new JTextArea("An error encountered while preparing visualization:\n\n" + x.getMessage());
		}
	}

	ProMJGraph visualizeGraph(final PluginContext context, final CPNGraph graph, final ViewSpecificAttributeMap map) {
		final GraphLayoutConnection layoutConnection = new GraphLayoutConnection(graph);
		layoutConnection.expandAll();

		final ProMGraphModel model = new ProMGraphModel(graph);
		final ProMJGraph jgraph = new ProMJGraph(model, map, layoutConnection);
		Graphs.updateEdges(jgraph, graph);

		final JGraphFacade facade = new JGraphFacade(jgraph);

		facade.setOrdered(false);
		facade.setEdgePromotion(true);
		facade.setIgnoresCellsInGroups(false);
		facade.setIgnoresHiddenCells(false);
		facade.setIgnoresUnconnectedCells(false);
		facade.setDirected(true);

		Graphs.setPositions(jgraph, graph, facade);
		final Map<?, ?> nested = facade.createNestedMap(true, true);
		jgraph.getGraphLayoutCache().edit(nested);
		jgraph.repositionToOrigin();
		layoutConnection.setLayedOut(true);

		layoutConnection.updated();
		context.getConnectionManager().addConnection(layoutConnection);

		return jgraph;
	}

}
