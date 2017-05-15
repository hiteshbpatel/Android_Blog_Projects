package com.example.achartengineexample;
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.app.Activity;
import android.graphics.Color;

public class BarChartActivity extends Activity {
	
	private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar_chart);
		 drawChart();
	}

	private void drawChart(){

		int[] x = { 0,1,2,3,4,5,6,7 };
        int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800};
        int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };
 
        // Creating an  XYSeries for Income
        XYSeries incomeSeries = new XYSeries("Income");
        // Creating an  XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");
        // Adding data to Income and Expense Series
        for(int i=0;i<x.length;i++){
            incomeSeries.add(i,income[i]);
            expenseSeries.add(i,expense[i]);
        }
 
        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        dataset.addSeries(incomeSeries);
        // Adding Expense Series to dataset
        dataset.addSeries(expenseSeries);
 
        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();
        incomeRenderer.setColor(Color.BLUE);
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(2);
        incomeRenderer.setDisplayChartValues(true);
 
        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.RED);
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(2);
        expenseRenderer.setDisplayChartValues(true);
 
        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Income vs Expense Chart");
        multiRenderer.setXTitle("Year 2016");
        multiRenderer.setYTitle("Amount in Dollars");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0; i< x.length;i++){
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }
 
        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(incomeRenderer);
        multiRenderer.addSeriesRenderer(expenseRenderer);
 
        // Getting a reference to LinearLayout of the BarChartActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);
        
        // Creating a Bar Chart
        View chart = ChartFactory.getBarChartView(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);
        
        // Adding the Bar Chart to the LinearLayout
        chartContainer.addView(chart);
        
        
	}

}
