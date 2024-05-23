package mainPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartPane extends JPanel {

	private Society soc;
	XYSeriesCollection collection = new XYSeriesCollection();
	private JFreeChart chart = ChartFactory.createXYLineChart("", // title
			"time", // OX axis
			"Satisfaction", // OY axis
			collection, // database
			PlotOrientation.VERTICAL, // orientation
			true, // legend
			false, // tooltips
			false); // urls 
	ChartPanel chartPanel = new ChartPanel(chart);
	
	public ChartPane(Society soc) {
		this.soc = soc;
//		chartPanel.setBounds(20, 600, 270, 160);
		chartPanel.setPreferredSize(new Dimension(960-400, 760-600));
		chart.setBorderPaint(Color.green);
		this.add(chartPanel);
		
//		chart.setBackgroundPaint(Color.);
		chart.getPlot().setBackgroundPaint( Color.white );
		
		clearPlots();
	}
	
	public void addXYValue(double time, double value) {
		XYSeries series = collection.getSeries(collection.getSeriesCount() - 1);
		series.add(time, value);
	}
	
	public void clearPlots() {
		collection.removeAllSeries();
		collection.addSeries(new XYSeries(String.valueOf(soc.getTolerance()), true, true));
	}

	public void addNewSeries(double tolerance) {
		try {
			collection.addSeries(new XYSeries(String.valueOf(tolerance), true, true));
		} catch (IllegalArgumentException e) {
			
		}	
		}
	}



