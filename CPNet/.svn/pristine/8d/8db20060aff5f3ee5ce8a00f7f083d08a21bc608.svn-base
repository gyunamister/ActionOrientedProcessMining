package org.processmining.plugins.cpnet.visualization;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jgraph.graph.GraphConstants;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphNode;
import org.processmining.models.graphbased.directed.petrinet.PetrinetEdge;
import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.jgraph.ProMJGraph;
import org.processmining.models.jgraph.elements.ProMGraphCell;
import org.processmining.models.jgraph.elements.ProMGraphEdge;
import org.processmining.plugins.cpnet.CPNGraph;

import com.jgraph.layout.JGraphFacade;

/**
 * Various utilities to handle CPNGraph / ProMJGraph
 * 
 * @author gluk47
 * 
 */
public class Graphs {
	/**
	 * Get ProM Graph Cell given a cell (place or transition) from the structure
	 * CPNGraph
	 * 
	 * @param graph
	 *            being drawn
	 * @param n 
	 * @return null if no corresponding cell found
	 */
	static public ProMGraphCell getCell(final ProMJGraph graph, final DirectedGraphNode n) {
		try {
			final Field nodeMapField = ProMJGraph.class.getDeclaredField("nodeMap");
			nodeMapField.setAccessible(true);
			@SuppressWarnings("unchecked")
			final Map<?, ProMGraphCell> nodeMap = (Map<?, ProMGraphCell>) nodeMapField
					.get(graph);
			return nodeMap.get(n);
		} catch (final Exception x) {
			Graphs.cerr("Failed to getGraphCell: " + x);
			return null;
		}
	}

	/**
	 * Get ProM Graph Edge given an edge from the structure CPNGraph
	 * 
	 * @param graph
	 *            being drawn
	 * @param e
	 *            cpn edge
	 * @return null if no corresponding edge found
	 */
	static public ProMGraphEdge getEdge(final ProMJGraph graph, final DirectedGraphEdge<?, ?> e) {
		try {
			final Field nodeMapField = ProMJGraph.class.getDeclaredField("edgeMap");
			nodeMapField.setAccessible(true);
			@SuppressWarnings("unchecked")
			final Map<?, ProMGraphEdge> nodeMap = (Map<?, ProMGraphEdge>) nodeMapField
					.get(graph);
			return nodeMap.get(e);
		} catch (final Exception x) {
			Graphs.cerr("Failed to getGraphCell: " + x);
			return null;
		}
	}

	/**
	 * Transfer cell positions from graph to jgraph and its facade
	 * 
	 * @param jgraph
	 * @param graph
	 * @param facade
	 */
	static public void setPositions(final ProMJGraph jgraph, final CPNGraph graph, final JGraphFacade facade) {
		for (final Place generic_p : graph.getPlaces()) {
			assert generic_p instanceof org.processmining.plugins.cpnet.visualization.Place;
			final org.processmining.plugins.cpnet.visualization.Place p = (org.processmining.plugins.cpnet.visualization.Place) generic_p;
			final ProMGraphCell cell = Graphs.getCell(jgraph, p);
			// does not work:
			GraphConstants.setBorderColor(cell.getAttributes(),
					(Color) generic_p.getAttributeMap().get(AttributeMap.LABELCOLOR));
			if (p.getPosition() != null) {
				facade.setLocation(cell, p.getPosition().getX(), p.getPosition().getY());
			} else {
				Graphs.cerr("Place " + p.getLabel().toString() + ": no position info");
			}
		}

		for (final Transition generic_t : graph.getTransitions()) {
			final org.processmining.plugins.cpnet.visualization.Transition t = (org.processmining.plugins.cpnet.visualization.Transition) generic_t;
			final ProMGraphCell cell = Graphs.getCell(jgraph, t);
			// does not work:
			GraphConstants.setBorderColor(cell.getAttributes(),
					(Color) generic_t.getAttributeMap().get(AttributeMap.LABELCOLOR));
			if (t.getPosition() != null) {
				facade.setLocation(cell, t.getPosition().getX(), t.getPosition().getY());
			} else {
				Graphs.cerr("Transition " + t.getLabel().toString() + ": no position info");
			}
		}
	}

	/**
	 * @param jgraph
	 * @param graph
	 */
	static public void updateEdges(final ProMJGraph jgraph, final CPNGraph graph) {
		final Map<org.processmining.models.graphbased.directed.petrinet.elements.Arc, ArcInfo> arcInfo = graph
				.getArcInfo();
		for (final PetrinetEdge<? extends PetrinetNode, ? extends PetrinetNode> cpn_e : graph.getEdges()) {
			final ProMGraphEdge e = Graphs.getEdge(jgraph, cpn_e);
			if (e == null) {
				Graphs.cerr("Graph edge for «" + cpn_e.getLabel().toString() + "» is null x_x");
				continue;
			}
			final org.jgraph.graph.AttributeMap map = e.getAttributes();
			if (arcInfo.get(cpn_e) == null) {
				//				cerr("Graph edge for «" + cpn_e.getLabel().toString() + "»: no arc info");
				continue;
			}
			final List<Point2D> points = new ArrayList<Point2D>();
			points.addAll(graph.getArcInfo().get(cpn_e).getPoints());
			if (points == null || points.size() == 0) {
				continue;
			}

			points.add(0, e.getView().getPoint(0));
			points.add(e.getView().getPoint(1));
			GraphConstants.setLineStyle(map, GraphConstants.STYLE_ORTHOGONAL);
			GraphConstants.setPoints(map, points);
		}
	}

	static <T> void cerr(final T t) {
		System.err.println(t);
	}
}
