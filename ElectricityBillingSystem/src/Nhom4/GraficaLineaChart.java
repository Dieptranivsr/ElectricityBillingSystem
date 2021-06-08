/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nhom4;

/**
 *
 * @author Dieptuantran
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficaLineaChart {
    public static void main(String[] args) {
        //Create the object dataset to put the values to draw the line
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(6, "2019", "Alex");
        //dataset.setValue(7, "2020", "Alex");

        dataset.setValue(8, "2019", "Carmen");
        //dataset.setValue(5, "2020", "Carmen");

        dataset.setValue(12, "2019", "Tony");
        //dataset.setValue(9, "2020", "Tony");

        //Create the chart object for the type Line Chart. Tip: the second field "true" is very important to the draw the value top the line

        JFreeChart chart = ChartFactory.createLineChart( "Promedio de calificaciones  2019-2020", "Alumnos", "Calificaciones", dataset, PlotOrientation.VERTICAL, false, true, false); 
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.ORANGE);

        /*
        //Create of the renderer object to the draw point and the value
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setShapesVisible(true);

        //Define the format to the value to the draw
        DecimalFormat decimalformat1 = new DecimalFormat("##");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));

        renderer.setItemLabelsVisible(true);
        renderer.setSeriesVisible(true);
        */
        //Show of the graph in the desktop 
        /*
        ChartFrame frame = new ChartFrame("Ejemplo Grafica de Lineas", chart);
        frame.pack();
        frame.setVisible(true);
        */
        
        JPanel jPanel1 = new JPanel(); 
        ChartPanel barPanel = new ChartPanel(chart);
        jPanel1.removeAll();
        jPanel1.add(barPanel, BorderLayout.EAST);
        jPanel1.updateUI();
        }
    }

/*
 result[5]=getDataSum("202105");
        
    DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
    barChartData.setValue(Integer.valueOf(result[1]),"Doanh thu tiền điện", "January");
    barChartData.setValue(Integer.valueOf(result[2]),"Doanh thu tiền điện", "February");
    barChartData.setValue(Integer.valueOf(result[3]),"Doanh thu tiền điện", "March");
    barChartData.setValue(Integer.valueOf(result[4]),"Doanh thu tiền điện", "April");
    barChartData.setValue(Integer.valueOf(result[5]),"Doanh thu tiền điện", "May");
        
    JFreeChart barChartData1 = ChartFactory.createBarChart("Doanh thu tiền điện năm nay", "Tháng", "Doanh thu", barChartData, PlotOrientation.VERTICAL, false, true, false);
    CategoryPlot barchrt = barChartData1.getCategoryPlot();
    barchrt.setRangeGridlinePaint(Color.ORANGE);
       
    ChartPanel barPanel = new ChartPanel(barChartData1);
    jPanel1.removeAll();
    jPanel1.add(barPanel, BorderLayout.EAST);
    jPanel1.updateUI();
*/
