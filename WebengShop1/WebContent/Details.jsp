<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.io.IOException"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>

<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%@page import="businesslogic.ArticleManager"%>
<%@page import="businesslogic.ShoppingCartManager"%>
<%@page import="presentation.managed_beans.Article"%>
<%@page import="transferobjects.ShoppingCart"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details</title>
</head>
<body>

<h1>Details</h1>
	
<% String id = request.getParameter("item"); %>


	<table border = 1>
		<tr>
	    	<th>ID</th><th>Artikel</th><th>Preis</th><th>Menge</th><th>Kaufen</th>
	    </tr>
		  
		  <% ArticleManager articleManger = new ArticleManager();
	      Article article = articleManger.getArticle(Integer.parseInt(id));%>

	    	  
         <tr>
         	<td> <%= article.getId() %></td>
         	<td> <%= article.getName()%></td>
            <td> <%= article.getPrice()%></td>
            <td> <%= article.getAmount()%> </td>
            <td> 
            	<form action="Details.jsp?item=<%=article.getId()%>" method="Post"> 
					<button type="submit" name="kaufen" value="kaufen">In den Warenkorb</button> 
				</form> 
			</td>
         </tr>
	    
	   </table>
	   
   	  <% //In den Warenkorb
      if(request.getParameter("kaufen") != null){
    	  
    	  //Hole Session
    	  HttpSession httpSession = request.getSession(true); 
    	
    	  //Füge Artikel über ID dem Warenkorb hinzu
    	  ShoppingCartManager cartManager  = new ShoppingCartManager();
    	  boolean articleAvailable = cartManager.addArtikelCart(httpSession, Integer.parseInt(id));
    	  
    	  //Artikel verfügbar
    	  if  (articleAvailable){
    	  	%> Artikel <%= article.getName()%> wurde in den Warenkorb gelegt <br><% 
    	  }else{
    		  %>Artikel nicht mehr verfügbar:( <br><% 
    	  }
    	  

      }%>
      
      <a href= "Uebersicht.jsp">Zurück Übersicht</a>


</body>
</html>