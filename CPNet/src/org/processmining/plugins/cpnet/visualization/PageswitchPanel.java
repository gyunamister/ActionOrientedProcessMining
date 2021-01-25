package org.processmining.plugins.cpnet.visualization;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import org.processmining.framework.util.ui.scalableview.ScalableComponent;
import org.processmining.framework.util.ui.scalableview.ScalableViewPanel;
import org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel;
import org.processmining.framework.util.ui.widgets.ProMScrollablePanel;

/**
 * @author michael
 *
 */
public class PageswitchPanel extends JPanel implements ViewInteractionPanel {
	/**
	 * @author michael
	 *
	 */
	public interface Updater {
		/**
		 * @param page_numero
		 */
		void set_page(int page_numero);
	}

	private static final long serialVersionUID = 8415559591750879766L;
	ButtonGroup options = new ButtonGroup();

	ProMScrollablePanel pane;
	protected ScalableComponent scalable;
	protected final ScalableViewPanel panel;

	int x_sz = getPreferredSize().width;;

	Updater updater;

	/**
	 * @param panel
	 * @param pageNames
	 * @param u
	 */
	public PageswitchPanel(final ScalableViewPanel panel, final List<String> pageNames, final Updater u) {

		super(null);
		this.panel = panel;

		setAlignmentX(Component.LEFT_ALIGNMENT);
		pane = new ProMScrollablePanel();
		add(pane);
		pane.setBackground(Color.LIGHT_GRAY);
		pane.setForeground(Color.DARK_GRAY);
		pane.setAlignmentX(Component.LEFT_ALIGNMENT);

		final JRadioButton[] btns = new JRadioButton[pageNames.size()];
		int i = 0;
		for (final String s : pageNames) {
			final int index = i;
			final JRadioButton rb = new JRadioButton(s);
			rb.setBackground(Color.LIGHT_GRAY);
			rb.setForeground(Color.DARK_GRAY);
			rb.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			pane.add(rb);
			options.add(rb);
			btns[i++] = rb;
			rb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					u.set_page(index);
				}
			});
			rb.setAlignmentX(Component.LEFT_ALIGNMENT);
			x_sz = Math.max(x_sz, rb.getPreferredSize().width);
		}

		btns[0].setSelected(true);
		for (final JRadioButton btn : btns) {
			btn.setSize(x_sz, btn.getHeight());
		}

		setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#getComponent()
	 */
	@Override
	public JComponent getComponent() {
		return this;
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#getHeightInView()
	 */
	@Override
	public double getHeightInView() {
		return Math.min(pane.getPreferredSize().getHeight(), panel.getHeight() - 10);
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#getPanelName()
	 */
	@Override
	public String getPanelName() {
		return "Pages...";
	}

	/**
	 * @return
	 */
	public int getPosition() {
		return SwingConstants.NORTH;
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#getWidthInView()
	 */
	@Override
	public double getWidthInView() {
		return x_sz;
	}

	/**
	 * @param height
	 */
	public void setHeight(final int height) {
		pane.setBounds(0, 0, getWidth(), getHeight());
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#setParent(org.processmining.framework.util.ui.scalableview.ScalableViewPanel)
	 */
	@Override
	public void setParent(final ScalableViewPanel parent) {

	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#setScalableComponent(org.processmining.framework.util.ui.scalableview.ScalableComponent)
	 */
	@Override
	public void setScalableComponent(final ScalableComponent scalable) {
		this.scalable = scalable;
	}

	/**
	 * @see java.awt.Component#setSize(int, int)
	 */
	@Override
	public void setSize(final int width, final int height) {
		super.setSize(width, height);
		setHeight(height);
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.ScalableComponent.UpdateListener#updated()
	 */
	@Override
	public void updated() {
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#willChangeVisibility(boolean)
	 */
	@Override
	public void willChangeVisibility(final boolean to) {
	}
}
