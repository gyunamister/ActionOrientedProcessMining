package org.processmining.actionorientedprocessmining.dialogs;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.processmining.actionorientedprocessmining.UI.RelativeLayout;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.util.ui.widgets.ProMTable;
import org.processmining.framework.util.ui.widgets.ProMTextField;

public class ConstraintConfPanel extends JPanel{
	PluginContext context;
	ProMTextField intervalTextField;
	ProMTextField offsetTextField;
	ProMTable constrTable;
	String constraint;
	public int timeInterval = 24;
	public int timeOffset = 9;
	public String timeWindow = "-24,0";
	
	public ConstraintConfPanel(PluginContext ctx, ProMTable constrTable) {
		this.context = ctx;
		this.constrTable = constrTable;
		Color prom_color = new Color(238, 238, 238);
		this.setOpaque(false);
		this.setBackground(prom_color);
		this.setForeground(prom_color);
		RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
		this.setLayout(rl);
		
		this.addLabel("Interval:");
		this.addIntervalTextField("24");
		this.addLabel("with offset:");
		this.addOffsetTextField("9");
		this.addLabel("time window: ");
		this.addTWTextField("-24,0");
	}
	
	public int getTimeInterval() {
		return this.timeInterval;
	}
	
	public void addLabel(String label) {
//		JLabel constraintLabel = SlickerFactory.instance().createLabel(label);
		JLabel constraintLabel = new JLabel(label, SwingConstants.CENTER);
		add(constraintLabel, new Float(15));
	}
	
	public void addIntervalTextField(String text) {
		intervalTextField = new ProMTextField();
		intervalTextField.setText(text);
		intervalTextField.setEditable(true);
		intervalTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			    timeInterval = Integer.parseInt(intervalTextField.getText());
			    }
			public void removeUpdate(DocumentEvent e) {
				timeInterval = Integer.parseInt(intervalTextField.getText());
				  }
			public void insertUpdate(DocumentEvent e) {
				timeInterval = Integer.parseInt(intervalTextField.getText());
				}
		});
		add(intervalTextField, new Float(15));
	}
	
	public void addOffsetTextField(String text) {
		offsetTextField = new ProMTextField();
		offsetTextField.setText(text);
		offsetTextField.setEditable(true);
		offsetTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			    timeOffset = Integer.parseInt(offsetTextField.getText());
			    }
			public void removeUpdate(DocumentEvent e) {
				timeOffset = Integer.parseInt(offsetTextField.getText());
				  }
			public void insertUpdate(DocumentEvent e) {
				timeOffset = Integer.parseInt(offsetTextField.getText());
				}
		});
		add(offsetTextField, new Float(15));
	}
	
	public void addTWTextField(String text) {
		ProMTextField twTextField = new ProMTextField();
		twTextField.setText(text);
		twTextField.setEditable(true);
		twTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				timeWindow = twTextField.getText();
			    }
			public void removeUpdate(DocumentEvent e) {
				timeWindow = twTextField.getText();
				  }
			public void insertUpdate(DocumentEvent e) {
				timeWindow = twTextField.getText();
				}
		});
		add(twTextField, new Float(15));
	}
}

