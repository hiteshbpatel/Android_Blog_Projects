package com.example.achartengineexample;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

	public class LineChartActivity extends Activity {

	private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        drawChart();
    }
    
    private void drawChart(){

        int[] x_values = { 1,2,3,4,5,6,7,8 };
        int[] y_values = { 1000,1500,1700,2000,2500,3000,3500,3600};

        // Creating an  XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");

        // Adding data to Expense Series
        for(int i=0;i<x_values.length;i++){
            expenseSeries.add(x_values[i], y_values[i]);

        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();
        // Adding Expense Series to the dataset
        xyMultipleSeriesDataset.addSeries(expenseSeries);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.GREEN);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);
        renderer.setLineWidth(3);
        renderer.setDisplayChartValues(true);


        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Expense Chart");
        multiRenderer.setXTitle("Year 2016");
        multiRenderer.setYTitle("Amount in Dollars");
        multiRenderer.setZoomButtonsVisible(true);
        for(int i=0;i<x_values.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }

        // Adding expenseRenderer to multipleRenderer
        multiRenderer.addSeriesRenderer(renderer);

        // Getting a reference to LinearLayout of the LineChartActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);

        // Creating a Line Chart
        View chart = ChartFactory.getLineChartView(getBaseContext(), xyMultipleSeriesDataset, multiRenderer);

        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(chart);
    }

}
