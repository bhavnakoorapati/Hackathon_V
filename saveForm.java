package com.verizon.stock;


import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
/**
 * Servlet implementation class Dbconnect
 */
@WebServlet("/saveForm")
public class saveForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saveForm() {
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
			  String desc,price="";
			try {
			    BufferedReader reader = request.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) {  e.printStackTrace(); }

			  try {
				  JsonParser jp = new JsonParser();
				  String jsonString= jb.toString();
				  jsonString=jsonString.replaceAll("\\\\", "");
				  
				  JsonObject jo= jp.parse(jsonString).getAsJsonObject();
				  desc = jo.get("formdata").toString();
				  String name = jo.get("custName").toString().replaceAll("\"", "");
//				  JsonObject jo2= jp.parse(desc).getAsJsonObject();
				  JsonArray jsonArr = jo.getAsJsonArray("formdata");
				  //{"formdata":[{"desc":"phone","qty":"1","price":"700"},{"qty":"1","desc":"iphone","price":"1700"}],"custName":"santhosh","custMob":"987876","custity":"hfh"}-
				  Type listType = new TypeToken<List<FormData>>() {
		            }.getType();
		            List<FormData> jsonObjList =   json.fromJson(jsonArr, listType);
		            System.out.println("List size is : "+jsonObjList.size());
		            System.out.println("List Elements are  : "+jsonObjList.toString());
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		        	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","password");
		        	
		        	String inv = "{ ? = call INVOICE_NUMB(?, ?, ?) }";
		        	CallableStatement cstm = con.prepareCall(inv);
		        	cstm.registerOutParameter(1, java.sql.Types.INTEGER);
		        	   cstm.setInt(2, 1000);
		        	   cstm.setInt(3, 10000);
		        	   cstm.setString(4,"Debit");
		        	   int numinv=cstm.executeUpdate();
		        	   System.out.println(numinv);
		           for(FormData x:jsonObjList)
		           {
		        	   String call = "{ ? = call ORDERS(?, ?, ?) }";
		        	   CallableStatement cstmt = con.prepareCall(call);
		        	   cstmt.registerOutParameter(1,  java.sql.Types.VARCHAR);
		        	   cstmt.setInt(2, numinv);
		        	   cstmt.setString(3, x.getDesc());
		        	   cstmt.setInt(4, Integer.parseInt(x.getQty()));
		        	   System.out.println(x.getDesc()+" "+x.getPrice()+" "+x.getQty());
		        	   System.out.println(cstmt.executeUpdate());
		        	   
		           }

//				  Type listType = new TypeToken<List<String>>() {}.getType();
//				  List<String> yourList = new Gson().fromJson(desc, listType);
				  
			  
			
			
			

				  System.out.println(desc);
				  System.out.println(name);
			  } catch (ParseException e) {
			    // crash and burn
				  e.printStackTrace();
			    throw new IOException("Error parsing JSON request string");
			  }
			System.out.print("Inside Dopost");
//			FormData fd= new FormData();
//			fd.setDesc(desc);
//			fd.setQty("1");
//			fd.setPrice(price);
			response.setContentType("application/json");
//			response.getWriter().write(json.toJson(fd));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
