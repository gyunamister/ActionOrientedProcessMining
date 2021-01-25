package org.processmining.plugins.cpnet;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.processmining.framework.util.ui.widgets.ProMPropertiesPanel;
import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.framework.util.ui.widgets.WidgetColors;

import com.fluxicon.slickerbox.factory.SlickerFactory;

/**
 * Panel for simulation options
 * 
 * @author mwesterg
 * 
 */
public class SimulationPropertiesPanel extends ProMPropertiesPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProMTextField steps;
	private ProMTextField repetitions;
	private ProMTextField delay;
	private JCheckBox updateUI;
	private ProMTextField day;
	private ProMTextField month;
	private ProMTextField year;
	private ProMTextField hour;
	private ProMTextField minute;
	private ProMTextField second;
	private JRadioButton ms;
	private JRadioButton seconds;
	private JRadioButton minutes;
	private JRadioButton hours;
	private JRadioButton days;
	private JRadioButton weeks;
	private ProMTextField customGranularity;

	private JCheckBox lifeCycleTransition;
	private JCheckBox ignorePage;
	private ProMTextField ignorePattern;
	private String[] ignorePatterns = null;

	public SimulationPropertiesPanel(final boolean steps, final boolean repetitions, final boolean delay,
			final boolean updateUI, final boolean timed, final boolean useLifeCycleExtension, final boolean ignorePage,
			final boolean ignorePattern) {
		super(null);

		if (steps) {
			this.steps = addTextField("Max steps (-1 for no limit)");
			this.steps.setText("-1");
		}

		if (repetitions) {
			this.repetitions = addTextField("Repetitions");
			this.repetitions.setText("1");
		}

		if (delay) {
			this.delay = addTextField("Step delay (ms)");
			this.delay.setText("0");
		}

		if (updateUI) {
			this.updateUI = addCheckBox("Update CPN Tools GUI");
		}

		if (timed) {
			final Calendar calendar = new GregorianCalendar();
			day = changeSize(new ProMTextField("" + calendar.get(Calendar.DAY_OF_MONTH)));
			month = changeSize(new ProMTextField("" + (calendar.get(Calendar.MONTH) + 1)));
			year = changeSize(new ProMTextField("" + calendar.get(Calendar.YEAR)));
			hour = changeSize(new ProMTextField("" + calendar.get(Calendar.HOUR_OF_DAY)));
			minute = changeSize(new ProMTextField("" + calendar.get(Calendar.MINUTE)));
			second = changeSize(new ProMTextField("" + calendar.get(Calendar.SECOND)));

			final JPanel buttonGroup = new JPanel();
			buttonGroup.setOpaque(false);
			buttonGroup.setLayout(new BoxLayout(buttonGroup, BoxLayout.PAGE_AXIS));
			final ButtonGroup timeGanularity = new ButtonGroup();
			ms = createRadioButton(buttonGroup, timeGanularity, "miliseconds (1)");
			seconds = createRadioButton(buttonGroup, timeGanularity, "seconds (1000)");
			seconds.setSelected(true);
			minutes = createRadioButton(buttonGroup, timeGanularity, "minutes (60000)");
			hours = createRadioButton(buttonGroup, timeGanularity, "hours (3600000)");
			days = createRadioButton(buttonGroup, timeGanularity, "days (86400000)");
			weeks = createRadioButton(buttonGroup, timeGanularity, "weeks (604800000)");
			final JPanel customFrame = new JPanel();
			customFrame.setOpaque(false);
			customFrame.setLayout(new BoxLayout(customFrame, BoxLayout.X_AXIS));
			createRadioButton(customFrame, timeGanularity, "custom: ", false);
			customGranularity = new ProMTextField("1000");
			customFrame.add(customGranularity);
			customFrame.add(Box.createHorizontalGlue());
			buttonGroup.add(customFrame);

			final JPanel line = new JPanel();
			line.setLayout(new BoxLayout(line, BoxLayout.X_AXIS));
			line.setOpaque(false);
			line.add(day);
			line.add(createLabel("/"));
			line.add(month);
			line.add(createLabel("/"));
			line.add(year);
			line.add(Box.createHorizontalStrut(20));

			line.add(hour);
			line.add(createLabel(":"));
			line.add(minute);
			line.add(createLabel(":"));
			line.add(second);

			addProperty("Time offset", line);
			addProperty("Time ganularity", buttonGroup);
		}

		if (useLifeCycleExtension) {
			lifeCycleTransition = addCheckBox("Use lifecycle extension", true);
		}
		if (ignorePage) {
			this.ignorePage = addCheckBox("Ignore \"page\" in transition name", false);
		}
		if (ignorePattern) {
			this.ignorePattern = addTextField("Ignore patterns (csv):", "");
		}
	}

	/**
	 * @param steps
	 * @param repetitions
	 * @param delay
	 * @param updateUI
	 * @param timed
	 */
	public SimulationPropertiesPanel(final boolean steps, final boolean repetitions, final boolean delay,
			final boolean updateUI, final boolean timed) {
		this(steps, repetitions, delay, updateUI, timed, true, true, true);
	}

	/**
	 * @return
	 */
	public int getDelay() {
		try {
			return Integer.parseInt(delay.getText());
		} catch (final Exception _) {
			return 0;
		}
	}

	/**
	 * @return
	 */
	public long getGranularity() {
		try {
			if (ms.isSelected()) {
				return 1;
			} else if (seconds.isSelected()) {
				return 1000;
			} else if (minutes.isSelected()) {
				return 60000;
			} else if (hours.isSelected()) {
				return 3600000;
			} else if (days.isSelected()) {
				return 86400000;
			} else if (weeks.isSelected()) {
				return 604800000;
			} else {
				return Long.parseLong(customGranularity.getText());
			}
		} catch (final Exception _) {
			return 1000;
		}
	}

	/**
	 * @return
	 */
	public Calendar getOffset() {
		try {
			return new GregorianCalendar(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()),
					Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()),
					Integer.parseInt(minute.getText()), Integer.parseInt(second.getText()));
		} catch (final NullPointerException _) {
			return new GregorianCalendar();
		}
	}

	/**
	 * @return
	 */
	public int getRepetitions() {
		try {
			return Integer.parseInt(repetitions.getText());
		} catch (final Exception _) {
			return 1;
		}
	}

	/**
	 * @return
	 */
	public int getSteps() {
		try {
			return Integer.parseInt(steps.getText());
		} catch (final Exception _) {
			return -1;
		}
	}

	/**
	 * @return
	 */
	public boolean getUpdateUI() {
		try {
			return updateUI.isSelected();
		} catch (final Exception _) {
			return false;
		}
	}

	protected JCheckBox getIgnorePage() {
		return ignorePage;
	}

	public boolean isIgnorePage() {
		try {
			return ignorePage.isSelected();
		} catch (final Exception _) {
			return false;
		}
	}

	public boolean shouldIgnoreSomePattern() {
		try {
			return !ignorePattern.getText().isEmpty();
		} catch (final Exception _) {
			return false;
		}
	}

	public String[] getIgnorePatterns() {
		try {
			if (ignorePatterns == null) {
				ignorePatterns = ignorePattern.getText().split(",");
			}

			return ignorePatterns;
		} catch (final Exception _) {
			return new String[] {};
		}
	}

	public boolean isUseLifeCycleTransitionExtendsion() {
		try {
			return lifeCycleTransition.isSelected();
		} catch (final Exception _) {
			return false;
		}
	}

	protected <T extends JComponent> T changeSize(final T c) {
		final Dimension minimumSize = c.getMinimumSize();
		if (minimumSize == null) {
			return c;
		}
		minimumSize.setSize(60, minimumSize.getHeight());
		c.setMinimumSize(minimumSize);
		return c;
	}

	protected JLabel createLabel(final String string) {
		final JLabel label = new JLabel(string);
		label.setForeground(WidgetColors.PROPERTIES_BACKGROUND);
		label.setFont(label.getFont().deriveFont(Font.BOLD, 24));
		return label;
	}

	protected JRadioButton createRadioButton(final JPanel component, final ButtonGroup buttonGroup,
			final String label) {
		return createRadioButton(component, buttonGroup, label, true);
	}

	protected JRadioButton createRadioButton(final JPanel component, final ButtonGroup buttonGroup, final String label,
			final boolean leftAdjust) {
		final JRadioButton button = SlickerFactory.instance().createRadioButton(label);
		button.setForeground(WidgetColors.TEXT_COLOR);
		buttonGroup.add(button);
		if (leftAdjust) {
			final JPanel lefty = new JPanel();
			lefty.setOpaque(false);
			lefty.setLayout(new BoxLayout(lefty, BoxLayout.X_AXIS));
			lefty.add(button);
			lefty.add(Box.createHorizontalGlue());
			component.add(lefty);
		} else {
			component.add(button);
		}
		return button;
	}
}
