package org.processmining.actionorientedprocessmining.dialogs;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.processmining.actionorientedprocessmining.UI.RelativeLayout;
import org.processmining.actionorientedprocessmining.parameters.AOPMParameters;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.util.ui.widgets.ProMTextField;

public class AISPathPanel extends JPanel{
	PluginContext context;
	public AOPMParameters parameters;
	public ProMTextField aisPathTextField;
	
	public AISPathPanel(PluginContext ctx, AOPMParameters parameters) {
		this.context = ctx;
		this.parameters = parameters;
		Color prom_color = new Color(238, 238, 238);
		this.setOpaque(false);
		this.setBackground(prom_color);
		this.setForeground(prom_color);
		RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
		this.setLayout(rl);
		
		this.addLabel("Output AIS file will be saved at:");
		this.addAISPathTextField(System.getProperty("user.dir")+"/output");
		
	}
	
	public void addLabel(String label) {
		JLabel constraintLabel = new JLabel(label, SwingConstants.CENTER);
		add(constraintLabel, new Float(30));
	}
	
	public void addAISPathTextField(String text) {
		aisPathTextField = new ProMTextField();
		aisPathTextField.setText(text);
		aisPathTextField.setEditable(true);
		//set initial value
		this.parameters.setAisFilePath(text);
		aisPathTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				parameters.setAisFilePath(aisPathTextField.getText());
			    }
			public void removeUpdate(DocumentEvent e) {
				parameters.setAisFilePath(aisPathTextField.getText());
				  }
			public void insertUpdate(DocumentEvent e) {
				parameters.setAisFilePath(aisPathTextField.getText());
				}
		});
		add(aisPathTextField, new Float(70));
	}
}
