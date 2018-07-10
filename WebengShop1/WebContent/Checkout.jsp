<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="businesslogic.ShoppingCartManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vielen Dank</title>
</head>
<body>

	  <% if(request.getParameter("checkout") != null){
		  
    	  //Hole Session
    	  HttpSession httpSession = request.getSession(true); 
		  
		  ShoppingCartManager cartManager = new ShoppingCartManager();
		  cartManager.checkout(httpSession);
		  
		  %> <h3>Vielen Dank für Ihren Einkauf</h3> <br> <% 
	  }%>
	  
	  <a href= "Uebersicht.jsp">Zurück Übersicht</a>

</body>
</html>