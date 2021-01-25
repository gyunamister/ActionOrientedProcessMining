package org.processmining.plugins.cpnet.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cpntools.accesscpn.model.Instance;
import org.cpntools.accesscpn.model.Page;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.Transition;

/**
 * Utility class that allows us to strip page information from a transition
 * name. Useful for allowing equal labels from different sub nets (i.e. when
 * modeling concept drifts)
 *
 */
public class CPNPageStripper {

	private List<String> pages;
	private final PetriNet net;

	public CPNPageStripper(PetriNet net) {
		this.net = net;
		pages = fetchAllPages();
		Collections.sort(pages, new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
	}

	private List<String> fetchAllPages() {
		// try to find all non-root pages
		List<String> result = new ArrayList<String>();
		Set<Page> roots = findRootPages();
		for (Page r : roots) {
			result.addAll(processSubPages(r, ""));
		}
		return result;
	}

	private Set<Page> findRootPages() {
		Set<String> subPageIDs = new HashSet<String>();
		Set<Page> rootPages = new HashSet<Page>(net.getPage());
		for (Page p : net.getPage()) {
			for (Instance i : p.instance()) {
				if (!i.getSubPageID().isEmpty() && !(i.getSubPageID() == null)) {
					subPageIDs.add(i.getSubPageID());
				}
			}
		}
		for (Page p : net.getPage()) {
			if (subPageIDs.contains(p.getId())) {
				rootPages.remove(p);
			}
		}
		return rootPages;
	}

	private List<String> processSubPages(Page parent, String prefix) {
		List<String> result = new ArrayList<String>();
		prefix += parent.getName().asString() + ".";
		result.add(prefix);
		for (Instance i : parent.instance()) {
			Page child = getPageById(i.getSubPageID());
			if (child != null) {
				result.addAll(processSubPages(child, prefix));
			}
		}
		return result;
	}

	private Page getPageById(String id) {
		if (id != null && !id.isEmpty()) {
			for (Page p : net.getPage()) {
				if (p.getId().equals(id))
					return p;
			}
		}
		return null;
	}

	public String convertToStringWithoutPages(
			final org.cpntools.accesscpn.engine.highlevel.instance.Instance<Transition> transition) {
		String oldName = transition.toString();
		for (String page : pages) {
			if (oldName.contains(page)) {
				//avoid problems with '.' characters (i.e. using replaceAll needs a regex).
				// we ordered the list of pages on length, so first match will be best match.
				int beginIndex = oldName.indexOf(page) + page.length();
				return oldName.substring(beginIndex, oldName.length());
			}
		}
		return oldName;
	}

}
