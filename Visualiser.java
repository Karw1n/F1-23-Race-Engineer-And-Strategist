import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

public class BrakingDataPlotter {

    public static class BrakingDataChart extends JFrame {

        public BrakingDataChart(String title, String csvFilePath) {
            super(title);

            // Create series for braking data
            XYSeries brakingSeries = new XYSeries("Braking Intensity");

            try (FileReader reader = new FileReader(csvFilePath);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                for (CSVRecord record : csvParser) {
                    double distance = Double.parseDouble(record.get("distance"));
                    double brakingIntensity = Double.parseDouble(record.get("braking_intensity"));
                    brakingSeries.add(distance, brakingIntensity);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // Create a dataset containing the braking series
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(brakingSeries);

            // Create the chart
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Braking Intensity vs. Distance", // Chart title
                    "Distance (m)",                   // X-axis label
                    "Braking Intensity",              // Y-axis label
                    dataset                           // Dataset
            );

            // Display the chart in a JFrame
            ChartPanel chartPanel = new ChartPanel(chart);
            setContentPane(chartPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        String csvFilePath = "path/to/braking_data.csv";
        SwingUtilities.invokeLater(() -> new BrakingDataChart("Braking Data Plot", csvFilePath));
    }
}
