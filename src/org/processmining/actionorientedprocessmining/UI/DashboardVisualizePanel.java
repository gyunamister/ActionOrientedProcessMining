package org.processmining.actionorientedprocessmining.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.processmining.actionorientedprocessmining.models.AOPMProgram;
import org.processmining.framework.plugin.PluginContext;

public class DashboardVisualizePanel extends JPanel implements ActionListener{
	PluginContext context;
	AOPMProgram simulator;
	JTable table;
	public TimeSeries series;
	/** The most recent value added. */
    private double lastValue = 100.0;
	//public Timer timer = new Timer(250, this);
	
	public DashboardVisualizePanel(PluginContext context, AOPMProgram sm) {
		simulator = sm;
		RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl.setFill(true);
		setLayout(rl);
		this.series = new TimeSeries("Random Data");
		final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
        
        //timer.setInitialDelay(1000);
        
        //Sets background color of chart
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        
        //Created JPanel to show graph on screen
        //final JPanel content = new JPanel(new BorderLayout());
 
        //Created Chartpanel for chart area
        final ChartPanel chartPanel = new ChartPanel(chart);
 
        //Added chartpanel to main panel
        add(chartPanel);
 
        //Sets the size of whole window (JPanel)
        //chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
 
        //Puts the whole content on a Frame
        //setContentPane(content);
 
        //timer.start();
	}
	
	private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );
        final XYPlot plot = result.getXYPlot();
 
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);
 
        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);
 
        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);
 
        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0.0, 300.0);
 
        return result;
    }
	
	public void actionPerformed(final ActionEvent e) {
        final double factor = 0.9 + 0.2*Math.random();
        this.lastValue = this.lastValue * factor;
 
        final Minute now = new Minute();
        this.series.add(new Minute(), this.lastValue);
        System.out.println(this.series);
        //System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+this.lastValue);
    }
}
