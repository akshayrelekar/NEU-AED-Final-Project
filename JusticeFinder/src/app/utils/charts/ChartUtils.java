/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils.charts;

import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author PC
 */
public class ChartUtils {

    public static JPanel getPieChart(String chartTitle, DefaultPieDataset dataset) {

        return new ChartPanel(ChartFactory.createPieChart(
                chartTitle,
                dataset,
                true,
                true,
                false));
    }

    public static JPanel getBarChart(String chartTitle, String xaxis, String yaxis, DefaultCategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle,
                xaxis,
                yaxis,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

//        BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();
//        r.setSeriesPaint(0, Color.blue);

        return new ChartPanel(chart);
    }
}
