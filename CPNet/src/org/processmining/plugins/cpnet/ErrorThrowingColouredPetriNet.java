package org.processmining.plugins.cpnet;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.model.PetriNet;
import org.processmining.framework.plugin.Progress;

public class ErrorThrowingColouredPetriNet extends ColouredPetriNet {

	public ErrorThrowingColouredPetriNet(PetriNet petriNet) {
		super(petriNet);
	}
	

	public HighLevelSimulator getSimulatorWithError() throws Exception {
		if (simulator == null) {
			try {
				if (getContext() == null) {
					simulator = checkModel(getPetriNet(), new Progress() {
						@Override
						public void cancel() {
						}

						@Override
						public String getCaption() {
							return null;
						}

						@Override
						public int getMaximum() {
							return 0;
						}

						@Override
						public int getMinimum() {
							return 0;
						}

						@Override
						public int getValue() {
							return 0;
						}

						@Override
						public void inc() {
						}

						@Override
						public boolean isCancelled() {
							return false;
						}

						@Override
						public boolean isIndeterminate() {
							return false;
						}

						@Override
						public void setCaption(final String message) {
						}

						@Override
						public void setIndeterminate(final boolean makeIndeterminate) {
						}

						@Override
						public void setMaximum(final int value) {
						}

						@Override
						public void setMinimum(final int value) {
						}

						@Override
						public void setValue(final int value) {
						}
					}, new Logger() {
						@Override
						public void log(final String message) {
							// Don't really log							
						}

					});
				} else {
					simulator = checkModel(getPetriNet(), getContext().getProgress(), new Logger() {
						@Override
						public void log(final String message) {
							getContext().log(message);
						}
					});
				}
			} catch (final Exception e) {
				throw e;
			}
		}
		return simulator;
	}


}
