package org.processmining.plugins.cpnet.visualization;

import org.cpntools.accesscpn.model.graphics.Coordinate;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.petrinet.PetrinetEdge;
import org.processmining.models.graphbased.directed.petrinet.PetrinetNode;

// How to avoid Place <-> Transition position code repetition without multiple
// inheritance here, in java?
/**
 * @author michael
 *
 */
public class Transition extends org.processmining.models.graphbased.directed.petrinet.elements.Transition {
	protected Coordinate position;

	/**
	 * @param label
	 * @param p
	 * @param net
	 */
	public Transition(final String label, final Coordinate p,
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
