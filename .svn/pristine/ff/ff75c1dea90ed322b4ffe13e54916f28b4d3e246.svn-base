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

public class ActionConfPanel extends JPanel{
	PluginContext context;
	String action;
	public int timeInterval = 24;
	public int timeOffset = 9;
	public String timeWindow = "-24,0";
	public ProMTable actionTable;
	
	public ActionConfPanel(PluginContext ctx, ProMTable actionTable) {
		this.context = ctx;
		this.actionTable = actionTable;
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
		this.addLabel("time window:");
		this.addTWTextField("-24,0");
	}
	
	public void addLabel(String label) {
//		JLabel actionLabel = SlickerFactory.instance().createLabel(label);
		JLabel actionLabel = new JLabel(label, SwingConstants.CENTER);
		add(actionLabel, new Float(15));
	}
	
	public void addIntervalTextField(String text) {
		ProMTextField intervalTextField = new ProMTextField();
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
		ProMTextField offsetTextField = new ProMTextField();
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

