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
<%@page import="java.util.LinkedList;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Übersicht</title>
</head>
<body>


<h1>Übersicht</h1>
<h2>Datenbank</h2>

	<table border = 1>
		<tr>
	    	<th>ID</th><th>Artikel</th><th>Preis</th><th>Menge</th><th>Link</th>
	    </tr>
		  
		  <% 
	      List <Article> articles = (List <Article>)request.getAttribute("articleList");
	      
	      //Durchlaufe Liste und packe Artikel in die Tabelle 
	      for (Article article : articles){%>
	    	  
	    	  
	         <tr>
	         	<td> <%= article.getId() %></td>
	         	<td> <%= article.getName()%></td>
	            <td> <%= article.getPrice()%></td>
	            <td> <%= article.getAmount()%> </td>
	            <td> <a href="Frontcontroller?action=Details&item=<%=article.getId()%>">Details</a></td>
	         </tr>
	   
	     <%}%>
	   </table>
	   

	      
	    <h2>Warenkorb</h2>

		<table border = 1>
			<tr>
		    	<th>ID</th><th>Artikel</th><th>Preis</th><th>Menge</th><th>Löschen</th>
		    </tr>
		    

			  
			  <%// Holle Warenkorb
	
		    	ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		        List<Article> articlesCart = cart.getShoppingCart();
		      
		        //Durchlaufe Liste und packe Artikel in die Tabelle 
			      for (Article article : articlesCart){%>
			    	  
			    	  
			         <tr>
		         	<td> <%= article.getId() %></td>
		         	<td> <%= article.getName()%></td>
		            <td> <%= article.getPrice()%></td>
		            <td> <%= article.getAmountInCart()%> </td>
		            <td>             	
		            	<form action="Frontcontroller?action=loeschen&item=<%=article.getId()%>" method="Post"> 
							<button type="submit" name="loeschen" value="loeschen">Löschen</button> 
					    </form> </td>
		         </tr>
		   
		     <%}%>
	     
	       </table>
	      
	      
	      <% float total = cart.getTotal();%>
	      <h4>Gesamtsumme: <%= total %> Euro</h4>
	      
	      
	       
	       <form action="Frontcontroller?action=checkout" method="Post"> 
				<button type="submit" name="checkout" value="checkout">Kaufen</button> 
			</form>
			
			<form action="Frontcontroller?action=addArticle" method="Post"> 
				<button type="submit" name="addArticle" value="addArticle">Artikel hinzufügen</button> 
			</form>
	  

</body>
</html>