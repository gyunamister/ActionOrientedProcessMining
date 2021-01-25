package org.processmining.plugins.cpnet.visualization;

import org.cpntools.accesscpn.model.graphics.Coordinate;

/*
 * Arrgh.
 * 
 * I want to create a pair of numbers. Just plain pair of numbers. Why the hell
 * should I investigate some way to create an implementation of some abstract
 * coordinate, with protected-constructible implementation?..
 * 
 * Why is there no way for a plain human to create just a 2D point? java.awt.Point
 * 
 * I found no javadoc, no rationale, no relevant factory, and I do not want to
 * dig for these factories anymore to create (I remind) just a pair of numbers.
 * I did not find any subclass like Coordinate.Double as well (like those
 * existing for awt Point2D class).
 * 
 * The time has come. I'll create my own implementation. Hope, I'll not hurt
 * much anyone, but feel free to refactor this in case you're so lucky to know
 * the correct way.
 * 
 * Sorry for all this. I promise not to confuse you using java anymore, just
 * plain old stupid C++11.
 */
/**
 * @author michael
 *
 */
public class CoordinateImpl implements Coordinate {
	double x, y;

	/**
	 * @param _c
	 */
	public CoordinateImpl(final Coordinate _c) {
		x = _c.getX();
		y = _c.getY();
	}

	/**
	 * @param _x
	 * @param _y
	 */
	public CoordinateImpl(final double _x, final double _y) {
		x = _x;
		y = _y;
	}

	/**
	 * @see org.cpntools.accesscpn.model.graphics.Coordinate#getX()
	 */
	@Override
	public double getX() {
		return x;
	}

	/**
	 * @see org.cpntools.accesscpn.model.graphics.Coordinate#getY()
	 */
	@Override
	public double getY() {
		return y;
	}

	/**
	 * @see org.cpntools.accesscpn.model.graphics.Coordinate#setX(double)
	 */
	@Override
	public void setX(final double value) {
		x = value;
	}

	/**
	 * @see org.cpntools.accesscpn.model.graphics.Coordinate#setY(double)
	 */
	@Override
	public void setY(final double value) {
		y = value;
	}

	/**
	 * @param _c
	 */
	public void shiftBy(final Coordinate _c) {
		x += _c.getX();
		y += _c.getY();
	}

}