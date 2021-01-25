package org.processmining.plugins.cpnet.visualization;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.processmining.framework.util.ui.scalableview.ScalableComponent;
import org.processmining.framework.util.ui.scalableview.ScalableViewPanel;
import org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel;
import org.processmining.framework.util.ui.widgets.ProMScrollablePanel;

/**
 * @author michael
 *
 */
public class TypesPanel extends JPanel implements ViewInteractionPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8415559591750873766L;
	JTextArea DetailsLabel;
	ProMScrollablePanel pane;

	protected ScalableComponent scalable;
	protected final ScalableViewPanel panel;

	/**
	 * @param panel
	 * @param sortInfo
	 */
	public TypesPanel(final ScalableViewPanel panel, final String sortInfo) {

		super(null);
		this.panel = panel;

		pane = new ProMScrollablePanel();
		add(pane);
		pane.setBackground(Color.LIGHT_GRAY);
		pane.setForeground(Color.DARK_GRAY);

		DetailsLabel = new JTextArea("Sorts information:\n\n" + sortInfo);
		DetailsLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
		DetailsLabel.setBackground(Color.LIGHT_GRAY);
		DetailsLabel.setForeground(Color.DARK_GRAY);
		DetailsLabel.setEditable(false);
		pane.add(DetailsLabel);

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
		return Math.min(DetailsLabel.getPreferredSize().getHeight(), panel.getHeight() - 10);
	}

	/**
	 * @see org.processmining.framework.util.ui.scalableview.interaction.ViewInteractionPanel#getPanelName()
	 */
	@Override
	public String getPanelName() {
		return "Sorts info";
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
		return DetailsLabel.getPreferredSize().getWidth();
	}

	/**
	 * @param height
	 */
	public void setHeight(final int height) {
		pane.setBounds(0, 0, getWidth(), getHeight());
		DetailsLabel.setBounds(0, 0, pane.getWidth(), pane.getHeight());
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
