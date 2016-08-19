package com.verizon.stock;


import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
/**
 * Servlet implementation class Dbconnect
 */
@WebServlet("/Dbconnect")
public class Dbconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dbconnect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			BufferedReader br=request.getReader();
			System.out.print("Inside Dopost1");
			StringBuffer jb = new StringBuffer();
			  String line = "";
			  Gson json= new Gson();
			  String desc="",price="";
			try {
			    BufferedReader reader = request.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) {  e.printStackTrace(); }

			  try {
				  JsonParser jp = new JsonParser();
				  JsonObject jo= (JsonObject)jp.parse(jb.toString());
				  desc = jo.get("desc").toString().replace("\"", "");
				  Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","password");
					String query="select PROD_PRICE from product where PROD_NAME='"+desc+"'";
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					if(rs.next()){
						price=rs.getString(1);
					}
			  } catch (ParseException e) {
			    // crash and burn
				  e.printStackTrace();
			    throw new IOException("Error parsing JSON request string");
			  }
			System.out.print("Inside Dopost");
			FormData fd= new FormData();
			fd.setDesc(desc);
			fd.setQty("1");
			System.out.println(price);
			fd.setPrice(price);
			response.setContentType("application/json");
			response.getWriter().write(json.toJson(fd));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
