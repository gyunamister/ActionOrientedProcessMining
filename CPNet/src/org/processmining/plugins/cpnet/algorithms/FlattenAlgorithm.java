package org.processmining.plugins.cpnet.algorithms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.model.Arc;
import org.cpntools.accesscpn.model.HLArcType;
import org.cpntools.accesscpn.model.Instance;
import org.cpntools.accesscpn.model.Node;
import org.cpntools.accesscpn.model.Page;
import org.cpntools.accesscpn.model.Place;
import org.cpntools.accesscpn.model.Transition;
import org.cpntools.accesscpn.model.graphics.Coordinate;
import org.cpntools.accesscpn.model.importer.NetCheckException;
import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.cpnet.ColouredPetriNet;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

public class FlattenAlgorithm {

	private Petrinet net;
	private GraphLayoutConnection layout;
	private Marking marking;

	private Map<String, Object> objectMap;
	private double minX;
	private double minY;
	private boolean firstXY;
	private double scale;
	private double offsetX;
	private double offsetY;

	public void apply(ColouredPetriNet cpn, Petrinet net, GraphLayoutConnection layout, Marking marking) {
		this.net = net;
		this.layout = layout;
		this.marking = marking;

		this.objectMap = new HashMap<String, Object>();
		minX = 0.0;
		minY = 0.0;
		firstXY = true;
		scale = 1.0;
		offsetX = 0.0;
		offsetY = 0.0;

		try {
			List<Page> pages = cpn.getPetriNet().getPage();
			if (!pages.isEmpty()) {
				Page page = pages.get(0);
				apply(page);

			}
		} catch (NetCheckException | SAXException | IOException | ParserConfigurationException e) {
			// Ignore
		}
	}

	private void apply(Page page) {
		/*
		 * Preprocess all nodes and arc.
		 */
		for (Place place : page.place()) {
			pre(place);
		}
		for (Transition transition : page.transition()) {
			pre(transition);
		}
		for (Instance instance : page.instance()) {
			pre(instance);
		}
		for (Arc arc : page.getArc()) {
			pre(arc);
		}
		/*
		 * Process all nodes and arcs.
		 */
		for (Place place : page.place()) {
			apply(place);
		}
		for (Transition transition : page.transition()) {
			apply(transition);
		}
		for (Instance instance : page.instance()) {
			apply(instance);
		}
		for (Arc arc : page.getArc()) {
			apply(arc);
		}
	}

	private void pre(Node node) {
		Point2D point = getPosition(node.getNodeGraphics().getPosition(), node.getNodeGraphics().getDimension());
		System.out.println("[FlattenAlgorithm] Node " + node.getName() + ": " + point);
		if (firstXY) {
			minX = point.getX();
			minY = point.getY();
			firstXY = false;
		} else {
			minX = Double.min(minX, point.getX());
			minY = Double.min(minY, point.getY());
		}
	}

	private void pre(Arc arc) {
		if (arc.getKind() != HLArcType.NORMAL && arc.getKind() != HLArcType.TEST) {
			return;
		}
		for (Coordinate coordinate : arc.getArcGraphics().getPosition()) {
			Point2D point = getPosition(coordinate);
			if (firstXY) {
				minX = point.getX();
				minY = point.getY();
				firstXY = false;
			} else {
				minX = Double.min(minX, point.getX());
				minY = Double.min(minY, point.getY());
			}
		}
	}

	Point2D getPosition(Coordinate position, Coordinate dimension) {
		return new Point2D.Double((position.getX() - dimension.getX() / 2.0),
				(-dimension.getY() / 2.0 - position.getY()));
	}

	Point2D transformPosition(Coordinate position, Coordinate dimension) {
		return new Point2D.Double(offsetX + (-minX + position.getX() - dimension.getX() / 2.0) / scale,
				offsetY + (-minY - dimension.getY() / 2.0 - position.getY()) / scale);
	}

	Point2D getPosition(Coordinate position) {
		return new Point2D.Double(position.getX(), -position.getY());
	}

	Point2D transformPosition(Coordinate position) {
		return new Point2D.Double(offsetX + (-minX + position.getX()) / scale,
				offsetY + (-minY - position.getY()) / scale);
	}

	Dimension transformDimension(Coordinate dimension) {
		Dimension d = new Dimension();
		d.setSize(dimension.getX() / scale, dimension.getY() / scale);
		return d;
	}

	/*
	 * Maps CPN Tools color names to colors.
	 */
	private Color getColor(String name) {
		//		System.out.println("[FlattenAlgorithm] Get color " + name + ".");
		if (name == null) {
			return Color.BLACK;
		} else if (name.equals("Fucia")) {
			return Color.decode("#ff00ff");
		} else if (name.equals("Maroon")) {
			return Color.decode("#800000");
		} else if (name.equals("Yellow")) {
			return Color.decode("#ffff00");
		} else if (name.equals("White")) {
			return Color.decode("#ffffff");
		} else if (name.equals("Red")) {
			return Color.decode("#ff0000");
		} else if (name.equals("Silver")) {
			return Color.decode("#c0c0c0");
		} else if (name.equals("Teal")) {
			return Color.decode("#008080");
		} else if (name.equals("Navy")) {
			return Color.decode("#000080");
		} else if (name.equals("Aqua")) {
			return Color.decode("#00ffff");
		} else if (name.equals("Black")) {
			return Color.decode("#000000");
		} else if (name.equals("Olive")) {
			return Color.decode("#808000");
		} else if (name.equals("Lime")) {
			return Color.decode("#00ff00");
		} else if (name.equals("Gray")) {
			return Color.decode("#808080");
		} else if (name.equals("Purple")) {
			return Color.decode("#800080");
		} else if (name.equals("Green")) {
			return Color.decode("#008000");
		} else if (name.equals("Blue")) {
			return Color.decode("#0000ff");
		}
		return Color.BLACK;
	}

	private int countTokens(String tokens, int countIfEmpty, int countIfComplex) {
		try {
			if (tokens == null || tokens.isEmpty()) {
				return countIfEmpty;
			}
			if (!tokens.contains("++")) {
				if (!tokens.contains("`")) {
					return 1;
				}
				return Integer.valueOf(tokens.split("`")[0]);
			}
			String[] splitTokens = tokens.split(tokens.contains("+++") ? "\\+\\+\\+" : "\\+\\+");
			int count = 0;
			for (int i = 0; i < splitTokens.length; i++) {
				if (splitTokens[i].contains("`")) {
					count += Integer.valueOf(splitTokens[i].split("`")[0].trim());
				}
			}
			return count;
		} catch (Exception e) {
			try {
				String eval = HighLevelSimulator.getHighLevelSimulator().evaluate("size(" + tokens + ")");
				// This returns, for example, "val it = 8 : int". We only need "8".
				return Integer.valueOf(eval.split(" ")[3]);
			} catch (Exception e1) {
				return countIfComplex;
			}
		}
	}

	private void apply(Place place) {
		org.processmining.models.graphbased.directed.petrinet.elements.Place p = net
				.addPlace(place.getName().asString());
		objectMap.put(place.getId(), p);
		layout.setPosition(p,
				transformPosition(place.getNodeGraphics().getPosition(), place.getNodeGraphics().getDimension()));
		p.getAttributeMap().put(AttributeMap.SIZE, transformDimension(place.getNodeGraphics().getDimension()));
		p.getAttributeMap().put(AttributeMap.STROKECOLOR, getColor(place.getNodeGraphics().getLine().getColor()));
		p.getAttributeMap().put(AttributeMap.FILLCOLOR, getColor(place.getNodeGraphics().getFill().getColor()));
		try {
			int count = countTokens(place.getInitialMarking().getText(), 0, 0);
			if (count > 0) {
				marking.add(p, count);
			}
		} catch (Exception e) {
			// Not in initial marking
		}
	}

	private void apply(Transition transition) {
		org.processmining.models.graphbased.directed.petrinet.elements.Transition t = net
				.addTransition(transition.getName().asString());
		objectMap.put(transition.getId(), t);
		layout.setPosition(t, transformPosition(transition.getNodeGraphics().getPosition(),
				transition.getNodeGraphics().getDimension()));
		t.getAttributeMap().put(AttributeMap.SIZE, transformDimension(transition.getNodeGraphics().getDimension()));
		t.getAttributeMap().put(AttributeMap.STROKECOLOR, getColor(transition.getNodeGraphics().getLine().getColor()));
		t.getAttributeMap().put(AttributeMap.FILLCOLOR, getColor(transition.getNodeGraphics().getFill().getColor()));
		/*
		 * Transition with a black fill are considered to be invisible transitions.
		 */
		if (transition.getNodeGraphics().getFill().getColor().equals("Black")) {
			t.setInvisible(true);
		}
	}

	private void apply(Instance instance) {
		org.processmining.models.graphbased.directed.petrinet.elements.Transition t = net
				.addTransition(instance.getName().asString());
		objectMap.put(instance.getId(), t);
		layout.setPosition(t,
				transformPosition(instance.getNodeGraphics().getPosition(), instance.getNodeGraphics().getDimension()));
		t.getAttributeMap().put(AttributeMap.SIZE, transformDimension(instance.getNodeGraphics().getDimension()));
		t.getAttributeMap().put(AttributeMap.STROKECOLOR, getColor(instance.getNodeGraphics().getLine().getColor()));
		t.getAttributeMap().put(AttributeMap.FILLCOLOR, getColor(instance.getNodeGraphics().getFill().getColor()));
	}

	private void apply(Arc arc) {
		if (arc.getKind() != HLArcType.NORMAL && arc.getKind() != HLArcType.TEST) {
			return;
		}
		org.processmining.models.graphbased.directed.petrinet.elements.Arc a = null;
		String inscription = arc.getHlinscription().asString();
		int n = countTokens(inscription, 1, 1);
		if (n < 1) {
			n = 1;
		}
		if (arc.getSource() instanceof Place) {
			org.processmining.models.graphbased.directed.petrinet.elements.Place p = (org.processmining.models.graphbased.directed.petrinet.elements.Place) objectMap
					.get(arc.getSource().getId());
			org.processmining.models.graphbased.directed.petrinet.elements.Transition t = (org.processmining.models.graphbased.directed.petrinet.elements.Transition) objectMap
					.get(arc.getTarget().getId());
			if (p != null && t != null) {
				a = net.addArc(p, t, n);
				//				System.out.println("[FlattenAlgorithm] Arc color " + arc.getArcGraphics().getLine().getColor() + ".");
				a.getAttributeMap().put(AttributeMap.EDGECOLOR, getColor(arc.getArcGraphics().getLine().getColor()));
				List<Point2D> points = new ArrayList<Point2D>();
				for (Coordinate coordinate : arc.getArcGraphics().getPosition()) {
					points.add(transformPosition(coordinate));
				}
				/*
				 * Edge points in CPN tools are from T to P. Need to reverse the points if from
				 * P to T and if more than 1 edge point.
				 */
				if (points.size() > 1) {
					points = Lists.reverse(points);
				}
				layout.setEdgePoints(a, points);
				if (arc.getKind() == HLArcType.TEST) {
					/*
					 * Test arc, that is, BOTHDIR. Add backwards arc as well.
					 */
					a = net.addArc(t, p, n);
					a.getAttributeMap().put(AttributeMap.EDGECOLOR,
							getColor(arc.getArcGraphics().getLine().getColor()));
					layout.setEdgePoints(a, Lists.reverse(points));
				}
			}
		} else {
			org.processmining.models.graphbased.directed.petrinet.elements.Place p = (org.processmining.models.graphbased.directed.petrinet.elements.Place) objectMap
					.get(arc.getTarget().getId());
			org.processmining.models.graphbased.directed.petrinet.elements.Transition t = (org.processmining.models.graphbased.directed.petrinet.elements.Transition) objectMap
					.get(arc.getSource().getId());
			if (p != null && t != null) {
				a = net.addArc(t, p, n);
				a.getAttributeMap().put(AttributeMap.EDGECOLOR, getColor(arc.getArcGraphics().getLine().getColor()));
				List<Point2D> points = new ArrayList<Point2D>();
				for (Coordinate coordinate : arc.getArcGraphics().getPosition()) {
					points.add(transformPosition(coordinate));
				}
				layout.setEdgePoints(a, points);
				if (arc.getKind() == HLArcType.TEST) {
					/*
					 * Test arc, that is, BOTHDIR. Add backwards arc as well.
					 */
					a = net.addArc(p, t, n);
					a.getAttributeMap().put(AttributeMap.EDGECOLOR,
							getColor(arc.getArcGraphics().getLine().getColor()));
					layout.setEdgePoints(a, Lists.reverse(points));
				}
			}
		}
	}
}
