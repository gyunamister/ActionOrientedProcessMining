package org.processmining.plugins.cpnet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.cpntools.accesscpn.model.Condition;
import org.cpntools.accesscpn.model.Label;
import org.cpntools.accesscpn.model.Node;
import org.cpntools.accesscpn.model.Page;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.graphics.Coordinate;
import org.cpntools.accesscpn.model.graphics.NodeGraphics;
import org.cpntools.accesscpn.model.importer.NetCheckException;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.petrinet.elements.Arc;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetImpl;
import org.processmining.plugins.cpnet.visualization.ArcInfo;
import org.processmining.plugins.cpnet.visualization.CoordinateImpl;
import org.processmining.plugins.cpnet.visualization.Css2Colour;
import org.processmining.plugins.cpnet.visualization.Place;
import org.processmining.plugins.cpnet.visualization.Transition;
import org.xml.sax.SAXException;

/**
 * @author michael
 *
 */
public class CPNGraph extends PetrinetImpl {

	/**
	 * @param arg
	 */
	static public <T> void cerr(final T arg) {
		System.err.println(arg);
	}

	/**
	 * @param arg
	 */
	static public <T> void cout(final T arg) {
		System.out.println(arg);
	}

	static String plural(final int number, final String one_base, final String many_suffix) {
		if (number == 1) {
			return number + " " + one_base;
		} else {
			return number + " " + one_base + many_suffix;
		}
	}

	protected Map<Node, Place> places = new HashMap<Node, Place>();

	protected Map<Node, Transition> transitions = new HashMap<Node, Transition>();

	String sorts = "";

	protected int min_x = -1, min_y = -1; ///< minimal encountered values of x and y of cells & transitions, for normalization
	/// Arc coordinate fixes. Subtracted from arc intermediate points only.
	/// TODO reduce amount of magic
	final int magic_y_fix = 130;
	final int magic_x_fix = 0;

	Map<Arc, ArcInfo> arcInfo = new HashMap<Arc, ArcInfo>();

	/**
	 * @param n 
	 * @param page_index 
	 * @param[in] n net to visualize
	 * @param[in] page_index page index (starting with 0) to process
	 * @throws NetCheckException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public CPNGraph(final ColouredPetriNet n, final int page_index) throws NetCheckException, SAXException,
			IOException, ParserConfigurationException {
		super(n.getPetriNet().getName().asString());

		final String netName = n.getPetriNet().getName().asString();
		CPNGraph.cout("Generating CPNGraph for net «" + netName + "», page " + page_index);
		fillNodes(n.getPetriNet().getPage().get(page_index));
		storeColors(n.getPetriNet());
		CPNGraph.cout("CPNGraph for net «" + netName + "», page " + page_index + " has been created ("
				+ CPNGraph.plural(places.size(), "place", "s") + ", "
				+ CPNGraph.plural(transitions.size(), "transition", "s") + ", "
				+ CPNGraph.plural(arcs.size(), "arc", "s"));

	}

	/**
	 * @return
	 */
	public Map<Arc, ArcInfo> getArcInfo() {
		return arcInfo;
	}

	/**
	 * @return
	 */
	public String getSortsInfo() {
		return sorts;
	}

	protected void storeColors(final PetriNet net) {
		final List<Label> lst = net.getLabel();
		if (lst.isEmpty()) {
			return;
		}

		for (int i = 0; i < lst.size() - 1; ++i) {
			String str = lst.get(i).asString();
			if (!str.matches("^colset .*")) {
				continue;
			}
			str = str.replaceFirst("colset ", "");
			if (str.matches("^(UNIT|INT|BOOL|STRING) =.*")) {
				continue;
			}
			if (sorts != "") {
				sorts += "\n";
			}
			sorts += str;
		}

	}

	Arc addArc(final Place p, final Transition t, final List<Coordinate> pts) {
		final Arc a = super.addArc(p, t);
		arcInfo.put(a, new ArcInfo(coords2points(pts)));
		return a;
	}

	Arc addArc(final Transition t, final Place p, final List<Coordinate> pts) {
		final Arc a = super.addArc(t, p);
		arcInfo.put(a, new ArcInfo(coords2points(pts)));
		return a;
	}

	<T extends org.processmining.models.graphbased.directed.petrinet.PetrinetNode> T addCell(final T cell) {
		graphElementAdded(cell);
		if (cell.getLabel() != null) {
			cell.getAttributeMap().put(AttributeMap.SHOWLABEL, true);
			cell.getAttributeMap().put(AttributeMap.LABELHORIZONTALALIGNMENT, SwingConstants.CENTER);
			cell.getAttributeMap().put(AttributeMap.LABELVERTICALALIGNMENT, SwingConstants.CENTER);
		} else {
			cell.getAttributeMap().put(AttributeMap.SHOWLABEL, false);
		}
		return cell;
	}

	void addNode(final Node n) {
		final double font_line_h = 17; ///TODO something reasonable here
		final double magic_min_width = 35; /// if sort or condition present
		if (n instanceof org.cpntools.accesscpn.model.Place) {
			if (places.containsKey(n)) {
				return;
			}
			final NodeGraphics g = n.getNodeGraphics();
			/*
			 * In CPNTools position is the position of a place or transition
			 * center, while in ProM it's a position of the upper left corner.
			 * Hence we have to translate that position using a place/transition
			 * size.
			 */
			final Coordinate sz = n.getNodeGraphics().getDimension();
			final Coordinate pos = new CoordinateImpl(g.getPosition().getX() - sz.getX() / 2, -g.getPosition().getY()
					- sz.getY() / 2);
			String label = n.getName().asString();
			final String sort = ((org.cpntools.accesscpn.model.Place) n).getSort().asString();
			Place p;
			if (sort != null && sort != "") {
				if (label != null && label != "") {
					label += ", ";
				} else {
					label = "";
				}
				label += "sort: " + sort;
				sz.setY(sz.getY() + font_line_h); /// TODO: add not one line, but as many as necessary
				if (sz.getX() < magic_min_width) {
					sz.setX(magic_min_width);
				}

				p = addPlace(label, pos);
				/*
				 * // Separate transition holding sort info. Looks not very
				 * good. AnnotationGraphics ann_gr =
				 * ((org.cpntools.accesscpn.model.Place)
				 * n).getSort().getAnnotationGraphics(); CoordinateImpl c = new
				 * CoordinateImpl(ann_gr.getOffset().getX(),
				 * -ann_gr.getOffset().getY()); c.shiftBy(pos); Transition tr =
				 * addTransition(sort, c);
				 * tr.getAttributeMap().put(AttributeMap.BORDERWIDTH, 0);
				 * tr.getAttributeMap().put(AttributeMap.LINEWIDTH, (float) 0.);
				 * tr.getAttributeMap().put(AttributeMap.AUTOSIZE, true);
				 * 
				 * Arc a = addArc(p, tr);
				 * a.getAttributeMap().put(AttributeMap.DASHPATTERN, new float[]
				 * { 10, 10 }); a.getAttributeMap().put(AttributeMap.EDGESTART,
				 * AttributeMap.ArrowType.ARROWTYPE_NONE);
				 * a.getAttributeMap().put(AttributeMap.EDGEEND,
				 * AttributeMap.ArrowType.ARROWTYPE_NONE);
				 */
			} else {
				p = addPlace(label, pos);
			}
			//			if (sort != null)
			//				p.getAttributeMap().put(AttributeMap.AUTOSIZE, true);
			store_min_coordinates(pos);

			p.getAttributeMap().put(AttributeMap.TOOLTIP,
					"Initial marking:\n" + ((org.cpntools.accesscpn.model.Place) n).getInitialMarking().asString());

			p.getAttributeMap().put(AttributeMap.SIZE, new Dimension((int) sz.getX(), (int) sz.getY()));
			final Color fillcolor = Css2Colour.get(n.getNodeGraphics().getFill().getColor());
			p.getAttributeMap().put(AttributeMap.FILLCOLOR, fillcolor);
			if (fillcolor == Color.white) {
				p.getAttributeMap().put(AttributeMap.LABELCOLOR,
						Css2Colour.get(n.getNodeGraphics().getLine().getColor()));
			} else if (fillcolor.equals(Color.yellow)) {
				p.getAttributeMap().put(AttributeMap.LABELCOLOR, Color.blue);
			} else {
				p.getAttributeMap().put(AttributeMap.LABELCOLOR, Color.white);
			}
			putToCellMap(n, p);
		} else if (n instanceof org.cpntools.accesscpn.model.Transition) {
			if (transitions.containsKey(n)) {
				return;
			}
			final NodeGraphics g = n.getNodeGraphics();
			// see comments concerning coordinate translation above in the first 'if' branch.
			final Coordinate sz = n.getNodeGraphics().getDimension();
			final Coordinate pos = new CoordinateImpl(g.getPosition().getX() - sz.getX() / 2, -g.getPosition().getY()
					- sz.getY() / 2);
			String label = n.getName().asString();
			final Condition condition = ((org.cpntools.accesscpn.model.Transition) n).getCondition();
			if (condition != null && condition.asString() != null) {
				if (label != null && label != "") {
					label = label + " " + condition.asString();
				} else {
					label = condition.asString();
				}
				sz.setY(sz.getY() + font_line_h); /// TODO: add not one line, but as many as necessary
				if (sz.getX() < magic_min_width) {
					sz.setX(magic_min_width);
				}
			}
			final Transition t = addTransition(label, pos);
			t.getAttributeMap().put(AttributeMap.SIZE, new Dimension((int) sz.getX(), (int) sz.getY()));
			store_min_coordinates(pos);
			t.getAttributeMap().put(AttributeMap.FILLCOLOR, Css2Colour.get(n.getNodeGraphics().getFill().getColor()));
			final Color fillcolor = Css2Colour.get(n.getNodeGraphics().getFill().getColor());
			t.getAttributeMap().put(AttributeMap.FILLCOLOR, fillcolor);
			if (fillcolor == Color.white) {
				t.getAttributeMap().put(AttributeMap.LABELCOLOR,
						Css2Colour.get(n.getNodeGraphics().getLine().getColor()));
			} else if (fillcolor.equals(Color.yellow)) {
				t.getAttributeMap().put(AttributeMap.LABELCOLOR, Color.blue);
			} else {
				t.getAttributeMap().put(AttributeMap.LABELCOLOR, Color.white);
			}
			putToCellMap(n, t);
		} else {
			System.err.println("CPNGraph.addNode: found node of unknown type «" + n.getClass().toString()
					+ "», labelled «" + n.getName().asString() + "». Ignored.");
		}
	}

	Place addPlace(final String name, final Coordinate where) {
		final Place p = new Place(name, where, this);
		super.places.add(p);
		return addCell(p);
	}

	Transition addTransition(final String name, final Coordinate where) {
		final Transition p = new Transition(name, where, this);
		super.transitions.add(p);
		return addCell(p);
	}

	List<Point2D> coords2points(final List<Coordinate> coords) {
		final List<Point2D> ptlist = new ArrayList<Point2D>();
		for (final Coordinate c : coords) {
			store_min_coordinates(c);
			ptlist.add(new Point2D.Double(c.getX(), c.getY()));
		}
		return ptlist;
	}

	/**
	 * Add all the places, transitions and arcs of the page p to this object.
	 * @param p 
	 */
	void fillNodes(final Page p) {
		for (final org.cpntools.accesscpn.model.Arc a : p.getArc()) {
			final Node src = a.getSource();
			final Node dest = a.getTarget();

			addNode(src);
			addNode(dest);
			Arc newArc = null;
			if (src instanceof org.cpntools.accesscpn.model.Place) {
				newArc = addArc(places.get(src), transitions.get(dest), a.getArcGraphics().getPosition());
			} else {
				newArc = addArc(transitions.get(src), places.get(dest), a.getArcGraphics().getPosition());
			}
			newArc.getAttributeMap().put(AttributeMap.FILLCOLOR,
					Css2Colour.get(a.getArcGraphics().getLine().getColor()));
			final String label = a.getHlinscription().asString();
			if (label != null) {
				final AttributeMap amap = newArc.getAttributeMap();
				amap.put(AttributeMap.SHOWLABEL, true);
				amap.put(AttributeMap.LABEL, label);
				if (label.length() > 5) {
					amap.put(AttributeMap.LABELALONGEDGE, true);
				} else {
					// There should be code to place labels horizontally always
					// in such a way that it is not crossed out by a line
					amap.put(AttributeMap.LABELALONGEDGE, true);
				}
				// The following commented lines work fine, but I need extra labels for places
				//				amap.put(AttributeMap.EXTRALABELS, new String[]{"Hello"});
				//				amap.put(AttributeMap.EXTRALABELPOSITIONS, new Point2D[]{new Point2D.Double(10, 10)});
			}
			a.getArcGraphics().getPosition();
		}

		for (final org.processmining.models.graphbased.directed.petrinet.elements.Place n : super.places) {
			final Place pl = (Place) n;
			final Coordinate c = pl.getPosition();
			c.setX(c.getX() - min_x);
			c.setY(c.getY() - min_y);
			pl.setPosition(c);
		}

		for (final org.processmining.models.graphbased.directed.petrinet.elements.Transition n : super.transitions) {
			final Transition t = (Transition) n;
			final Coordinate c = t.getPosition();
			c.setX(c.getX() - min_x);
			c.setY(c.getY() - min_y);
			t.setPosition(c);
		}

		for (final ArcInfo i : arcInfo.values()) {
			for (final Point2D point : i.getPoints()) {
				point.setLocation(point.getX() - min_x - magic_x_fix, -point.getY() - min_y - magic_y_fix);
			}
		}
	}

	void putToCellMap(final Node n, final Place p) {
		places.put(n, p);
	}

	void putToCellMap(final Node n, final Transition t) {
		transitions.put(n, t);
	}

	void store_min_coordinates(final Coordinate p) {
		min_x = Math.min(min_x, (int) p.getX());
		min_y = Math.min(min_y, (int) p.getX());
	}
}
