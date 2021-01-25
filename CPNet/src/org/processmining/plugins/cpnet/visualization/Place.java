package org.processmining.plugins.cpnet.visualization;

import org.cpntools.accesscpn.model.graphics.Coordinate;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.petrinet.PetrinetEdge;
import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;

/**
 * @author michael
 *
 */
public class Place extends org.processmining.models.graphbased.directed.petrinet.elements.Place {
	protected Coordinate position;

	/**
	 * @param label
	 * @param p
	 * @param net
	 */
	public Place(final String label, final Coordinate p,
			final AbstractDirectedGraph<PetrinetNode, PetrinetEdge<? extends PetrinetNode, ? extends PetrinetNode>> net) {
		super(label, net);
		position = p;
	}

	/**
	 * @return
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * @param p
	 */
	public void setPosition(final Coordinate p) {
		position = p;
	}
}
