package org.processmining.actionorientedprocessmining.plugins;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.processmining.actionorientedprocessmining.UI.RelativeLayout;
import org.processmining.actionorientedprocessmining.actionengine.Action;
import org.processmining.actionorientedprocessmining.actionengine.ActionFormula;
import org.processmining.actionorientedprocessmining.models.AOPMProgram;
import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

import com.fluxicon.slickerbox.components.NiceIntegerSlider;
import com.fluxicon.slickerbox.components.NiceSlider.Orientation;
import com.fluxicon.slickerbox.factory.SlickerFactory;

@Plugin(name = "Initialize AOPM",
	parameterLabels = {"Initialize AOPM"},
	returnLabels = {"JPanel"},
	returnTypes = {JPanel.class})

@Visualizer
public class AOPMPlugin {
	@PluginVariant(requiredParameterLabels = {0})
	public static JPanel visualize (PluginContext context, AOPMProgram sm) {
		
		return new MainView(context, sm);
	}
}

class MainView extends JPanel {
	PluginContext context;
	AOPMProgram simulator;
	RightPanel rightPanel;
	LeftPanel leftPanel;
	
	public MainView(PluginContext ctx, AOPMProgram sm) {
		context = ctx;
		simulator = sm;
		
		RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
		rl.setFill(true);
		setLayout(rl);

		leftPanel = new LeftPanel(context, simulator, rightPanel);
		rightPanel = new RightPanel(context, simulator, leftPanel);
		
		add(leftPanel, new Float(70));
		add(rightPanel, new Float(30));
	}
}

class RightPanel extends JPanel {
	public Thread play;
	
	PluginContext context;
	LeftPanel leftPanel;	
	AOPMProgram simulator;
	
	// Attributes Setting
	JCheckBox[] attrCheckBox;
	
	JComboBox<String>[] dataTypeSelection;
	JComboBox<String> patternSelection;
	JLabel conditionLengthLabel;
	JComboBox<Integer> conditionLengthSelection;
	
	//Slider
	NiceIntegerSlider simulationSpeedSlider;
	
	// Button (Export, refresh)
	JButton exportNoiseLogButton;
	JButton exportFilteredLogButton;
	JButton refreshButton;
	JButton addLabelButton; 
	
	
	public RightPanel(PluginContext context, AOPMProgram sm, LeftPanel leftPanel) {
		simulator = sm;
		
		RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl.setFill(true);
		setLayout(rl);
		 
//		JPanel attrPanel = new JPanel();
		TextPanel attrPanel = new TextPanel(context,sm);
		attrPanel.setLayout(new BoxLayout(attrPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setViewportView(attrPanel);
		
		simulationSpeedSlider = SlickerFactory.instance().createNiceIntegerSlider("Simulation speed", 1, 100, 1, 
				Orientation.HORIZONTAL);
		simulationSpeedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				simulator.speed = simulationSpeedSlider.getValue();
				
			}
		});
		JPanel control_panel = new JPanel();
//		control_panel.setLayout(new BoxLayout(control_panel, BoxLayout.X_AXIS));
		control_panel.setLayout(new RelativeLayout(RelativeLayout.X_AXIS));
		JButton play_button = SlickerFactory.instance().createButton("play");
		play_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulator.speed=1;
				play = new Thread(simulator, "simulator");
				attrPanel.timer.start();
				leftPanel.chartVisualizePanel.timer.start();
				play.start();
			}
		});
		JButton pause_button = SlickerFactory.instance().createButton("pause");
		pause_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(1000);
					simulator.isSleep=true;
					attrPanel.timer.stop();
					leftPanel.chartVisualizePanel.timer.stop();
					play.interrupt();
				} catch (InterruptedException exep) {
					exep.printStackTrace();
				}				
			}
		});
		JButton stop_button = SlickerFactory.instance().createButton("stop");
		stop_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(1000);
					attrPanel.timer.stop();
					leftPanel.chartVisualizePanel.timer.stop();
					play.stop();
					if(simulator.mode.equals("log")) {
						simulator = new AOPMProgram(simulator.cmConfig, simulator.aeConfig, simulator.ocl, simulator.AISdirPath);
					}else if(simulator.mode.equals("information_system")) {
						simulator = new AOPMProgram(simulator.cmConfig, simulator.aeConfig, simulator.pSimulator, simulator.AISdirPath);
					}
					leftPanel.chartVisualizePanel.simulator=simulator;
					attrPanel.simulator=simulator;
					simulator.speed=1;
				} catch (InterruptedException exep) {
					exep.printStackTrace();
				}				
			}
		});
		control_panel.add(play_button, new Float(10));
		control_panel.add(pause_button, new Float(10));
		control_panel.add(stop_button, new Float(10));
		/**
		 * Adding Widget to Panel
		 */
		
		//Attribute List
		JLabel attrLabel = new JLabel("Action Instance Log", SwingConstants.CENTER);
		Font font_info = new Font("Arial", Font.BOLD,25);
		attrLabel.setFont(font_info);
		add(attrLabel);
		add(scrollPanel, new Float(30));
		add(control_panel);
		
		add(simulationSpeedSlider);
	}
}

class TextPanel extends JPanel implements ActionListener{
	PluginContext context;
	AOPMProgram simulator;
	JTextPane pane;
	public Timer timer = new Timer(10, this);
	
	public TextPanel(PluginContext context, AOPMProgram sm) {
		simulator = sm;
		RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl.setFill(true);
		setLayout(rl);
		createTextPanel();
		timer.setInitialDelay(1000);
        timer.start();
	}

	public void createTextPanel() { 
		pane = new JTextPane();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
        StyleConstants.setBold(attributeSet, true);  
  
        // Set the attributes before adding text  
        pane.setCharacterAttributes(attributeSet, true);  
        pane.setText(simulator.ae.messages);
        pane.setEditable(true);
  
        attributeSet = new SimpleAttributeSet();  
        StyleConstants.setItalic(attributeSet, true);  
  
        Document doc = pane.getStyledDocument();  
        try {
			doc.insertString(doc.getLength(), "Action log here", attributeSet);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
		JScrollPane scrollPanel = new JScrollPane(pane);
//		scrollPanel.setViewportView(table);
		add(scrollPanel, new Float(30)); 
	}
	
	public void actionPerformed(final ActionEvent e) {
		pane.setText(simulator.ae.messages);
		/*
		SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		Document doc = pane.getStyledDocument();
        try {

			doc.insertString(doc.getLength(), "To Java ", attributeSet);
		} catch (BadLocationException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}
		*/
    }
}

/**
 * This panel contains Info, List panel
 * @author gyumin
 *
 */
class LeftPanel extends JPanel {
	PluginContext context;
	AOPMProgram simulator;
	RightPanel rightPanel;
	ChartVisualizePanel chartVisualizePanel;
	InfoPanel infoPanel;
	List<Integer> exceptionList;
	JCheckBox selectionCheckBox;
	
	public LeftPanel(PluginContext context, AOPMProgram sm, RightPanel rightPanel) {
		context = context;
		simulator = sm;
		
		RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl.setFill(true);
		setLayout(rl);
		
		infoPanel = new InfoPanel(context, simulator, this);
		chartVisualizePanel = new ChartVisualizePanel(context, simulator);
		
		JLabel listLabel = new JLabel("Dashboard", SwingConstants.CENTER);
		Font font = new Font("Arial", Font.BOLD,25);
		listLabel.setFont(font);
		add(listLabel);
		add(chartVisualizePanel, new Float(75));
		JLabel infoLabel = new JLabel("Action specifications", SwingConstants.CENTER);
		Font font_info = new Font("Arial", Font.BOLD,25);
		infoLabel.setFont(font_info);
		//selectionCheckBox = new JCheckBox("Select / Deselect all");
		add(infoLabel);
		//add(selectionCheckBox);
		add(infoPanel, new Float(25));
		
	}
}



class ChartVisualizePanel extends JPanel implements ActionListener{
	PluginContext context;
	AOPMProgram simulator;
	JTable table;
	public Map<String,XYSeries> series_map = new LinkedHashMap<String,XYSeries>();
	//public TimeSeries series;
	/** The most recent value added. */
	public Timer timer = new Timer(10, this);
	
	public ChartVisualizePanel(PluginContext context, AOPMProgram sm) {
		simulator = sm;
//		RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
//		rl.setFill(true);
//		setLayout(rl);
		setLayout(new GridLayout(0,2));
		
		for(String cfName:simulator.cmConfig.getCFNames()) {
			XYSeriesCollection dataset = new XYSeriesCollection();
			XYSeries series = new XYSeries(cfName);
			series_map.put(cfName,series);
		    dataset.addSeries(series);
//		    double thre;
//		    if(cf.actionRequisite=="empty") {
//		    		thre = 999999999;
//		    }else {
//		    		thre = Double.parseDouble(cf.actionRequisite.split(",")[2]);
//		    } 
		    
		    JFreeChart chart = createChart(cfName,dataset);
		    //Sets background color of chart
	        chart.setBackgroundPaint(Color.LIGHT_GRAY);
	 
	        //Created Chartpanel for chart area
	        ChartPanel chartPanel = new ChartPanel(chart);
	  
	        //Added chartpanel to main panel
	        add(chartPanel);
	 
	        //Sets the size of whole window (JPanel)
	        //chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
	 
	        //Puts the whole content on a Frame
	        //setContentPane(content);
		}        
        timer.setInitialDelay(1000);
        timer.start();
	}
	
	private JFreeChart createChart(String name, final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createXYLineChart(
        		name,
            "Time",
            "Value",
            dataset 
        );
//        ValueMarker marker = new Marker(0, Color.red, new BasicStroke(1), Color.red, 1f);
        final XYPlot plot = result.getXYPlot();
        ValueMarker marker1 = new ValueMarker(0);
        marker1.setPaint(Color.BLUE);
        plot.addRangeMarker(marker1);
 
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);
 
        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);
 
        //Domain axis would show data of 60 seconds for a time
//        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);
 
        ValueAxis yaxis = plot.getRangeAxis();
//        yaxis.setRange(0.0, 300.0);
        yaxis.setAutoRange(true);
 
        return result;
    }
	
	public void actionPerformed(final ActionEvent e) {
        for(String cfName:simulator.cmConfig.getCFNames()) {
        		XYSeries series = series_map.get(cfName);
	        	series.clear();
	        	for(int i=0;i<simulator.db.vMap.get(cfName).size();i++) {
	    			series.add(i,simulator.db.vMap.get(cfName).get(i));
	    		}
        }
    }
	
	/*
	public void createDataSet() {
		this.series.clear();
		for(int i=0;i<simulator.ae.value_list.size();i++) {
			this.series.add(i,simulator.ae.value_list.get(i));
		}
	}
	*/
}

/**
 * This panel shows detail of selected relation
 * @author gyumin
 *
 */
class InfoPanel extends JPanel {
	PluginContext context;
	AOPMProgram simulator;
	JTable table;
	LeftPanel leftPanel;
	
	public InfoPanel(PluginContext context, AOPMProgram sm, LeftPanel leftPanel) {
		simulator = sm;
		RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl.setFill(true);
		setLayout(rl);
		createInfoTable();
	}
	
	public String convertWithStream(Map<String, ?> map) {
	    String mapAsString = map.keySet().stream()
	      .map(key -> key + "=" + map.get(key))
	      .collect(Collectors.joining(", ", "{", "}"));
	    return mapAsString;
	}

	public void createInfoTable() {
		TableModel model = new DefaultTableModel() { 
            String[] column_names = {"Action Formula Name", "Operation", "Parameters", "Analysis"}; 

            @Override 
            public int getColumnCount() { 
                return column_names.length; 
            } 

            @Override 
            public String getColumnName(int index) { 
                return column_names[index]; 
            } 
        };
        
		table = new JTable(model);
		 
		for(Action a : simulator.aeConfig.getActionSet()) {
			List<String> row = new ArrayList<String>();
			ActionFormula af = a.getAF(); 
			row.add(af.afName);
			row.add(af.op);
			row.add(convertWithStream(af.pmap));
//			String constraintName = af.ccv.get("constraintList").get(0);
//			row.add(constraintName);
//			row.add(convertWithStream(af.ccvDescription));
			row.add(convertWithStream(af.ccvDescription));
			((DefaultTableModel) model).addRow(row.toArray());
		}
		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setViewportView(table);
		add(scrollPanel, new Float(30));
	}
}
