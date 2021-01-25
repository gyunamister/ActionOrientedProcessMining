package org.processmining.plugins.cpnet.utils;

public class TransitionUtils {

	public static boolean containsIgnorePattern(String transitionName, String[] ignorePatterns) {
		for (String pattern : ignorePatterns) {
			if (transitionName.contains(pattern)) {
				return true;
			}
		}
		return false;
	}

}
