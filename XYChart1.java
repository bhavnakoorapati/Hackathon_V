

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.sql.*;
/**
 * Servlet implementation class XYChart1
 */
@WebServlet("/XYChart1")
public class XYChart1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
	ResultSet rs;
    
	PreparedStatement ps;
    public XYChart1() {
        super();
            }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		XYSeries series = new XYSeries("XYGraph");
		series.add(1,20);
		series.add(2,30);
		series.add(3,90);
		series.add(4,60);
		series.add(5,70);
        XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
	
		//ToConnect cn=new ToConnect();
		//Connection con=cn.getConnection();
/*		PreparedStatement ps;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","Admin");
			ps = con.prepareStatement
			                 ("select quantity from store_products where prod_id=20000");
		rs=ps.executeQuery();
		while(rs.next())
		{
		series.add(1,rs.getInt("quantity"));
		}
		ps = con.prepareStatement
                ("select quantity from store_product where prod_id=20002");
rs=ps.executeQuery();
while(rs.next())
{
series.add(1,rs.getInt("quantity"));
}
ps = con.prepareStatement
("select quantity from store_product where prod_id=20032");
rs=ps.executeQuery();
while(rs.next())
{
series.add(1,rs.getInt("quantity"));
}
ps = con.prepareStatement
("select quantity from store_product where prod_id=20039");
rs=ps.executeQuery();
while(rs.next())
{
series.add(1,rs.getInt("quantity"));
}
ps = con.prepareStatement
("select quantity from store_product where prod_id=20004");
rs=ps.executeQuery();
while(rs.next())
{
series.add(1,rs.getInt("quantity"));
}
		} catch(Exception e)
	    {
	    	System.out.println("Any problem :"+e.getMessage());
	    }*//*catch (SQLException e1) {
			// TODO Auto-generated catch block
			/*e1.printStackTrace();
		}*/
		
		// Add the series to your data set
		
		// Generate the gFsraph
		/*XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);*/
		JFreeChart chart = ChartFactory.createXYLineChart(
				"SALES REPORT", // Title
				"PRODUCT in 20000", // x-axis Label
				"SALES", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		try {
			ChartUtilities.saveChartAsJPEG(new File("C:\\try3.jpg"), chart,
					500, 300);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}
	}



	}


