<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<% 
Connection con=null;
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");	
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","password");
}catch(ClassNotFoundException er1){
    System.out.println("Class not found exception: "+er1.getMessage());
}
catch(Exception e)
{System.out.println("Any problem :"+e.getMessage());
}

 String uname=(String)session.getAttribute("t1");
String SQL="select * from store_"+uname; 
//String SQL="select * from store_uname";
Statement st= con.createStatement();
ResultSet rs= st.executeQuery(SQL);
int id=0;
String fname=null;
String lname=null;
String addrs=null;
String city=null;
String state=null;
int pin=0;
while(rs.next())
{
 id=(int)rs.getInt("Store_Id");
 fname=rs.getString("First_Name");
 lname=rs.getString("Last_Name");
 addrs=rs.getString("Adddress");
 city=rs.getString("City");
 state=rs.getString("State");
 pin=(int)rs.getInt("Pincode");
}


//	out.println("any problem:"+e.getMessage());

%>

Store Id :<%=id%><br>
First Name : <%=fname%><br>
Last Name : <%=lname %><br>
Address : <%=addrs %><br>
City : <%=city %><br>
State : <%=state %><br>
Pincode : <%=pin%><br>
 
 
 <%
 rs.close();
 st.close(); 
 con.close();
 %>
</body>
</html>
