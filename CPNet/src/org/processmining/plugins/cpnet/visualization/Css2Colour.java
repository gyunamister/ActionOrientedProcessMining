package org.processmining.plugins.cpnet.visualization;

import java.awt.Color;

/**
 * @author michael
 *
 */
public class Css2Colour {
	/**
	 * @param arg
	 */
	static public <T> void cerr(final T arg) {
		System.err.println(arg);
	}

	/**
	 * @param str
	 * @return
	 */
	static public Color get(final String str) {
		if (str == null) {
			return Color.black;
		}

		if (str == "Aqua") {
			return new Color(0x00ffff);
		}
		if (str == "Black") {
			return Color.black;
		}
		if (str == "Blue") {
			return Color.blue;
		}
		if (str == "Fucia") {
			return new Color(0xff00ff);
		}
		if (str == "Gray") {
			return new Color(0x808080);
		}
		if (str == "Green") {
			return new Color(0x008000);
		}
		if (str == "Lime") {
			return new Color(0x00ff00);
		}
		if (str == "Maroon") {
			return new Color(0x800000);
		}
		if (str == "Navy") {
			return new Color(0x000080);
		}
		if (str == "Olive") {
			return new Color(0x808000);
		}
		if (str == "Purple") {
			return new Color(0x800080);
		}
		if (str == "Red") {
			return Color.red;
		}
		if (str == "Silver") {
			return new Color(0xC0C0C0); //< Cococo, this is the silver, cococo!
		}
		if (str == "Teal") {
			return new Color(0x008080);
		}
		if (str == "White") {
			return Color.white;
		}
		if (str == "Yellow") {
			return new Color(0xffff00);
		} else {
			Css2Colour.cerr("Colour «" + str + "» not defined, fix it!");
			return Color.black;
		}
	}
}
