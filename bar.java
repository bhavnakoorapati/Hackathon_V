package serv;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;

public class bar {
	public static void main(String[] args) {
		// Create a simple pie chart
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("Product", new Integer(30));
		pieDataset.setValue("Services", new Integer(3));
		JFreeChart chart = ChartFactory.createPieChart(
				"Products and Services", // Title
				pieDataset, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		try {
			ChartUtilities.saveChartAsJPEG(new File("C:\\chart2.jpg"), chart,
					500, 300);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
		}
	}
}
