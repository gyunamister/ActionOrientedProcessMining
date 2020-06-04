package org.processmining.actionorientedprocessmining.dialogs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.processmining.actionorientedprocessmining.actionengine.AEConfig;
import org.processmining.actionorientedprocessmining.actionengine.ActionFormula;
import org.processmining.actionorientedprocessmining.constraintmonitor.CMConfig;
import org.processmining.actionorientedprocessmining.constraintmonitor.ConstraintFormula;
import org.processmining.actionorientedprocessmining.event.TimeWindow;
import org.processmining.actionorientedprocessmining.models.ActionFormulaDefinition;
import org.processmining.actionorientedprocessmining.models.ConstraintFormulaDefinition;
import org.processmining.actionorientedprocessmining.parameters.AOPMParameters;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.util.ui.widgets.ProMComboBox;
import org.processmining.framework.util.ui.widgets.ProMTable;

import com.fluxicon.slickerbox.factory.SlickerFactory;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

public class InputDialog extends JPanel {
	public int constrNumber=1;
	public int actionNumber=1;
	Map<String, Map<String, Object>> parsedCFD = new LinkedHashMap<String, Map<String, Object>>();
	Map<String, ConstraintFormula> cfMap;
	CMConfig cmConfig = new CMConfig(10000);
	Map<String, Map<String, Object>> parsedAFD = new LinkedHashMap<String, Map<String, Object>>();
	Map<String, ActionFormula> afMap;
	AEConfig aeConfig = new AEConfig(10000);
	public String constraint;
	public String action;
	public AISPathPanel aisPathPanel;
	public ConstraintConfPanel constraintPanel;
	public ActionConfPanel actionPanel;
	public ProMTable constrTable;
	public ProMTable actionTable;
	public AOPMParameters parameters;

	private static final long serialVersionUID = -60087716353524468L;

	/**
	 * The JPanel that allows the user to set (a subset of) the parameters.
	 */

	public InputDialog(UIPluginContext context, ConstraintFormulaDefinition cfd, ActionFormulaDefinition afd,
			final AOPMParameters parameters) {
		this.parameters = parameters;
		//read CDL
		this.parsedCFD = cfd.getParsedCFD();
		this.cfMap = cfd.getCfMap();

		//read ADL
		this.parsedAFD = afd.getParsedAFD();
		this.afMap = afd.getAfMap();


		double size[][] = { { TableLayoutConstants.FILL }, { 30, 30, 30, 30, 30, TableLayoutConstants.FILL, 30, 30, 30, 30, TableLayoutConstants.FILL } };
		setLayout(new TableLayout(size));
		
		//Part0: AIS file path
		this.aisPathPanel = new AISPathPanel(context,parameters);
		this.aisPathPanel.setPreferredSize(new Dimension(15, 100));
		add(this.aisPathPanel, "0, 0");

		//Part1: Constraint selection
		JLabel cLabel = this.genLabel("Constraint Monitor");
		cLabel.setPreferredSize(new Dimension(15, 100));
		add(cLabel,"0, 1");
		
		ProMComboBox<String> constraintComboBox = this.genConstraintComboBox();
		add(constraintComboBox, "0, 2");

		//Part2: Constraint specification
		this.constrTable = this.genConstraintTable();
		this.constrTable.setPreferredSize(new Dimension(50, 100));
		add(this.constrTable, "0, 5");
		
		this.constraintPanel = new ConstraintConfPanel(context,constrTable);
		this.constraintPanel.setPreferredSize(new Dimension(15, 100));
		add(this.constraintPanel, "0, 3");

		JButton constrAddButton = this.genConstAddButton();
		add(constrAddButton,"0, 4");


		//Part3: Action Formula selection
		JLabel aLabel = this.genLabel("Action Engine");
		aLabel.setPreferredSize(new Dimension(15, 100));
		add(aLabel,"0, 6");
		
		ProMComboBox<String> actionComboBox = this.genActionComboBox();
		add(actionComboBox, "0, 7");

		//Part4: Action specification
		this.actionTable = this.genActionTable();
		this.actionTable.setPreferredSize(new Dimension(50, 100));
		add(this.actionTable, "0, 10");
		
		this.actionPanel = new ActionConfPanel(context, actionTable);
		this.actionPanel.setPreferredSize(new Dimension(15, 100));
		add(this.actionPanel, "0, 8");

		JButton actionAddButton = this.genActionAddButton();
		add(actionAddButton,"0, 9");
	}

	public JLabel genLabel(String title) {
		JLabel label = SlickerFactory.instance().createLabel(title);
		label.setFont(new Font("Serif", Font.BOLD, 14));
		return label;
	}

	public ProMComboBox<String> genConstraintComboBox() {
		List<String> constraintList = new ArrayList<String>();
		constraintList.add("Select constraint formula");
		for(String constraintName:this.parsedCFD.keySet()) {
			constraintList.add(constraintName);
		}

		final ProMComboBox<String> constraintComboBox = new ProMComboBox<String>(constraintList);
		constraintComboBox.setSelectedIndex(0);
		constraintComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				constraint = constraintComboBox.getSelectedItem().toString();
			}
		});
		constraintComboBox.setPreferredSize(new Dimension(15, 100));
		return constraintComboBox;
	}

	public ProMComboBox<String> genActionComboBox() {
		List<String> actionList = new ArrayList<String>();
		actionList.add("Select action formula");
		for(String actionName:parsedAFD.keySet()) {
			actionList.add(actionName);
		}

		final ProMComboBox<String> actionComboBox = new ProMComboBox<String>(actionList);
		actionComboBox.setSelectedIndex(0);
		actionComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = actionComboBox.getSelectedItem().toString();
			}
		});
		actionComboBox.setPreferredSize(new Dimension(15, 100));
		return actionComboBox;
	}

	public ProMTable genActionTable() {
		TableModel actionTableModel = new DefaultTableModel() {
        	String[] column_names = {"Action Formula", "Interval", "Offset", "Time Window"};

            @Override
            public int getColumnCount() {
                return column_names.length;
            }

            @Override
            public String getColumnName(int index) {
                return column_names[index];
            }
        };

		ProMTable actionTable = new ProMTable(actionTableModel);
		return actionTable;
	}

	public ProMTable genConstraintTable() {
		TableModel constrTableModel = new DefaultTableModel() {
        	String[] column_names = {"Constraint Formula", "Interval", "Offset", "Time Window"};

            @Override
            public int getColumnCount() {
                return column_names.length;
            }

            @Override
            public String getColumnName(int index) {
                return column_names[index];
            }
        };

		ProMTable constrTable = new ProMTable(constrTableModel);
		return constrTable;
	}

	public JButton genConstAddButton() {
		JButton constraintAddButton = SlickerFactory.instance().createButton("Add");
		constraintAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) constrTable.getTable().getModel();
				String cfName = "cf" + constrNumber;

				List<String> row = new ArrayList<String>();
//				row.add(cfName);
				row.add(constraint);
				row.add(Integer.toString(constraintPanel.timeInterval));
				row.add(Integer.toString(constraintPanel.timeOffset));
				row.add(constraintPanel.timeWindow);
				model.addRow(row.toArray());
				TimeWindow tw = new TimeWindow(Integer.parseInt(constraintPanel.timeWindow.split(",")[0]), Integer.parseInt(constraintPanel.timeWindow.split(",")[1]));
				cmConfig.addConstraint(cfMap.get(constraint), constraintPanel.timeOffset, constraintPanel.timeInterval, tw);
				constrNumber+=1;
				parameters.updateCMConf(cmConfig);
			}
		});
		return constraintAddButton;
	}

	public JButton genActionAddButton() {
		JButton actionAddButton = SlickerFactory.instance().createButton("Add");
		actionAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) actionTable.getTable().getModel();
				String afName = "af" + actionNumber;

				List<String> row = new ArrayList<String>();
//				row.add(afName);
				row.add(action);
				row.add(Integer.toString(actionPanel.timeInterval));
				row.add(Integer.toString(actionPanel.timeOffset));
				row.add(actionPanel.timeWindow);
				model.addRow(row.toArray());
				TimeWindow tw = new TimeWindow(Integer.parseInt(actionPanel.timeWindow.split(",")[0]), Integer.parseInt(actionPanel.timeWindow.split(",")[1]));
				aeConfig.addAction(afMap.get(action), actionPanel.timeOffset, actionPanel.timeInterval, tw);
				actionNumber+=1;
				parameters.updateAEConf(aeConfig);
			}
		});
		return actionAddButton;
	}

	public void setActiveAction() {

	}

}
