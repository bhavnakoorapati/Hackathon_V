
import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class BarChart
{
   public static void main( String[ ] args )throws Exception 
   {

	      final String mobile = "mobile";        
	      final String landline_a = "landline_a";        
	      final String cell = "cell";        
	      final String charger = "charger";        
	      final String phone = "phone";        
	      final String ipad = "ipad";        
	      final String iphone = "iphone"; 
	      final String landline_b = "landline_b";        
	      final String tab = "tab";        
	      final String landline_c = "landline_c"; 
	      final String Product = "Product";

      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

      dataset.addValue( 20,Product,mobile );        
      dataset.addValue( 30,Product,landline_a );        
      dataset.addValue( 50,Product,cell ); 
      dataset.addValue( 80,Product,charger );           

      dataset.addValue( 10,Product,ipad );        
      dataset.addValue( 60,Product,phone);       
      dataset.addValue( 100,Product,tab);        
      dataset.addValue( 35,Product,iphone);

      dataset.addValue( 23,Product,landline_b);        
      dataset.addValue(86,Product,landline_c);

      JFreeChart barChart = ChartFactory.createBarChart(
    		     "Sales",
    	         "Product",            
    	         "Sales Quantity",            
    	         dataset,          
    	         PlotOrientation.VERTICAL,           
    	         true, true, false);
      ChartUtilities.saveChartAsJPEG(new File("C:\\chart3.jpg"), barChart,
				500, 300);
         
     // int width = 640; /* Width of the image */
     // int height = 480; /* Height of the image */ 
     // File BarChart = new File( "C:\\BarChart.jpeg" ); 
     
      //ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
   }
}