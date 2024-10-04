package nhap1;

import javax.swing.JFrame;  
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.DefaultCategoryDataset;  

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class LineChartExample extends JFrame {  
  
  private static final long serialVersionUID = 1L;  
  
  public LineChartExample(String title) {  
    super(title);  
    // Create dataset for the desired period
    DefaultCategoryDataset dataset = createDataset("2016-12-19", "2016-12-25");  
    // Create chart  
    JFreeChart chart = ChartFactory.createLineChart(  
        "Site Traffic", // Chart title  
        "Date", // X-Axis Label  
        "Number of Visitor", // Y-Axis Label  
        dataset  
        );  
  
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);  
  }  
  
  private DefaultCategoryDataset createDataset(String startDate, String endDate) {  
    // Parse start and end dates
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);

    String series1 = "Visitor";  
    String series2 = "Unique Visitor";  
  
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
  
    // Add data for each day within the period
    for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
        String dateStr = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dataset.addValue(getRandomVisitorCount(), series1, dateStr);  
        dataset.addValue(getRandomUniqueVisitorCount(), series2, dateStr);
    }
  
    return dataset;  
  }

  // Sample method to get random visitor count for a specific date
  private int getRandomVisitorCount() {
    // Generate random visitor count (between 100 and 300)
    return new Random().nextInt(201) + 100;
  }

  // Sample method to get random unique visitor count for a specific date
  private int getRandomUniqueVisitorCount() {
    // Generate random unique visitor count (between 80 and 250)
    return new Random().nextInt(171) + 80;
  }
  
  public static void main(String[] args) {  
    SwingUtilities.invokeLater(() -> {  
      LineChartExample example = new LineChartExample("Line Chart Example");  
      example.setAlwaysOnTop(true);  
      example.pack();  
      example.setSize(600, 400);  
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });  
  }  
}
