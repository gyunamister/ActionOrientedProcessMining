package org.processmining.plugins.cpnet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.cpntools.accesscpn.engine.highlevel.CheckerException;
import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.LocalCheckFailed;
import org.cpntools.accesscpn.engine.highlevel.PageSorter;
import org.cpntools.accesscpn.engine.highlevel.checker.Checker;
import org.cpntools.accesscpn.engine.highlevel.checker.ErrorInitializingSMLInterface;
import org.cpntools.accesscpn.model.FusionGroup;
import org.cpntools.accesscpn.model.HLDeclaration;
import org.cpntools.accesscpn.model.Page;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.Place;
import org.cpntools.accesscpn.model.RefPlace;
import org.cpntools.accesscpn.model.Transition;
import org.cpntools.accesscpn.model.importer.DOMParser;
import org.cpntools.accesscpn.model.importer.NetCheckException;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.Progress;
import org.xml.sax.SAXException;

/**
 * @author michael
 * 
 */
public class ColouredPetriNet {
	private transient PetriNet petriNet;
	protected transient HighLevelSimulator simulator;
	private transient PluginContext context;
	private final String modelFileContents;
	private final String modelName;

	/**
	 * @param petriNet
	 */
	public ColouredPetriNet(final PetriNet petriNet) {
		this.petriNet = petriNet;
		modelFileContents = "";
		modelName = petriNet.getName().getText();
	}

	/**
	 * @param modelFile
	 * @param modelName
	 */
	public ColouredPetriNet(final String modelFile, final String modelName) {
		modelFileContents = modelFile;
		this.modelName = modelName;
	}

	/**
	 * @return
	 */
	public PluginContext getContext() {
		return context;
	}

	/**
	 * @return
	 * @throws NetCheckException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public PetriNet getPetriNet() throws NetCheckException, SAXException, IOException, ParserConfigurationException {
		if (petriNet == null) {
			petriNet = DOMParser.parse(new ByteArrayInputStream(modelFileContents.getBytes()), modelName);
		}
		return petriNet;
	}

	/**
	 * @return
	 */
	public HighLevelSimulator getSimulator() {
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
				e.printStackTrace();
			}

		}
		return simulator;
	}

	/**
	 * @param context
	 */
	public void setContext(final PluginContext context) {
		this.context = context;
	}

	/**
	 * @param petriNet
	 */
	public void setPetriNet(final PetriNet petriNet) {
		this.petriNet = petriNet;
	}

	/**
	 * @param simulator
	 */
	public void setSimulator(final HighLevelSimulator simulator) {
		this.simulator = simulator;
	}

	private int checkDeclarations(int worked, final Checker checker, final PetriNet petriNet, final Progress progress,
			final Logger logger) throws Exception {
		logger.log("Checking declarations");
		for (final HLDeclaration decl : petriNet.declaration()) {
			checker.checkDeclaration(decl);
			worked++;
			progress.setValue(worked);
		}
		checker.generateSerializers();
		worked++;
		progress.setValue(worked);
		return worked;
	}

	private int checkInitializing(int worked, final Checker checker, final Progress progress, final Logger logger)
			throws IOException, Exception {
		logger.log("Initializing");
		checker.checkInitializing();
		worked++;
		progress.setValue(worked);
		return worked;
	}

	protected HighLevelSimulator checkModel(final PetriNet petriNet, final Progress progress, final Logger logger)
			throws Exception {
		logger.log("Starting CPN Simulator");
		final HighLevelSimulator highLevelSimulator = HighLevelSimulator.getHighLevelSimulator();
		final Checker checker = new Checker(petriNet, null, highLevelSimulator);
		progress.setMinimum(0);
		progress.setMaximum(computeWorkload(petriNet));
		logger.log("Checking CPN model");
		int worked = 0;
		worked = localCheck(worked, checker, progress, logger);
		worked = checkInitializing(worked, checker, progress, logger);
		worked = checkDeclarations(worked, checker, petriNet, progress, logger);
		worked = checkPages(worked, checker, petriNet, progress, logger);
		worked = generateInstances(worked, checker, petriNet, progress, logger);
		checker.initialiseSimulationScheduler();
		try {
			worked = instantiateSMLInterface(worked, checker, progress, logger);
		} catch (final ErrorInitializingSMLInterface e) {
			// Mask this; don't work for timed models
		}
		return highLevelSimulator;
	}

	private int checkPages(int worked, final Checker checker, final PetriNet petriNet, final Progress progress,
			final Logger logger) throws IOException, CheckerException {
		logger.log("Checking pages");
		final PageSorter pageSorter = new PageSorter(petriNet.getPage());
		for (final Page page : pageSorter) {
			progress.setCaption("Checking page " + page.getName().getText());
			checker.checkPage(page, pageSorter.isPrime(page));
			worked += 3;
			progress.setValue(worked);
		}
		return worked;
	}

	private int computeWorkload(final PetriNet petriNet) {
		int workload = 3;
		workload += iterableCount(petriNet.declaration());
		workload += 3 * petriNet.getPage().size();
		workload += petriNet.getFusionGroups().size();
		for (final Page page : petriNet.getPage()) {
			workload += iterableCount(page.readyFusionGroups());
			workload += iterableCount(page.readyPortPlaces());
			workload += iterableCount(page.readyPlaces());
			workload += iterableCount(page.readyTransitions());
		}
		workload++;
		return workload;
	}

	private int generateInstances(int worked, final Checker checker, final PetriNet petriNet, final Progress progress,
			final Logger logger) throws IOException {
		logger.log("Generating instances");

		for (final FusionGroup fusionGroup : petriNet.getFusionGroups()) {
			checker.generateInstanceForFusionGroup(fusionGroup.getId());
			worked++;
			progress.setValue(worked);
		}

		for (final Page page : petriNet.getPage()) {
			logger.log("Generating instances for " + page.getName().getText());

			for (final RefPlace refPlace : page.readyFusionGroups()) {
				checker.generateInstanceForPlace(refPlace.getId());
				worked++;
				progress.setValue(worked);
			}
			for (final RefPlace refPlace : page.readyPortPlaces()) {
				checker.generateInstanceForPlace(refPlace.getId());
				worked++;
				progress.setValue(worked);
			}
			for (final Place place : page.readyPlaces()) {
				checker.generateInstanceForPlace(place.getId());
				worked++;
				progress.setValue(worked);
			}

			for (final Transition transition : page.readyTransitions()) {
				checker.generateInstanceForTransition(transition.getId());
				worked++;
				progress.setValue(worked);
			}
		}

		return worked;
	}

	private int instantiateSMLInterface(int worked, final Checker checker, final Progress progress, final Logger logger)
			throws ErrorInitializingSMLInterface {
		logger.log("Instantiating SML interface");
		checker.instantiateSMLInterface();
		worked++;
		progress.setValue(worked);
		return worked;
	}

	private int iterableCount(final Iterable<?> it) {
		int i = 0;
		for (@SuppressWarnings("unused")
		final Object o : it) {
			i++;
		}
		return i;
	}

	private int localCheck(int worked, final Checker checker, final Progress progress, final Logger logger)
			throws LocalCheckFailed {
		logger.log("Checking names");
		checker.localCheck();
		worked++;
		progress.setValue(worked);
		return worked;
	}
}
