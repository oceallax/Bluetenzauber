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

   <% 
   	  List <Article> articlesCart;
   	  ShoppingCart cart;
   	  ShoppingCartManager cartManager  = new ShoppingCartManager();
   	  //int[]amountInCart;
   	  
      //Erstelle Session, wenn noch keine erstellt wurde 
      HttpSession httpSession = request.getSession(true); 
      //Prüfe, ob es schon einen Warenkorb gibt
      if(httpSession.getAttribute("cart")== null){
    	  
    	  //Erstelle einen Warenkorb (Artikel-Liste)
    	  articlesCart = new LinkedList();
    	  cart = new ShoppingCart(0,articlesCart);
    	  //Erstelle Warenkorb, wenn es keinen gibt (Speichere Session)
    	  httpSession.setAttribute("cart", cart);
      }else{
    	  // Es existiert ein Warenkorb in der Session
    	  cart = (ShoppingCart) httpSession.getAttribute("cart");
    	  articlesCart = cart.getShoppingCart();
      }
      %>

<h1>Übersicht</h1>
<h2>Datenbank</h2>

	<table border = 1>
		<tr>
	    	<th>ID</th><th>Artikel</th><th>Preis</th><th>Menge</th><th>Link</th>
	    </tr>
		  
		  <% ArticleManager articleManger = new ArticleManager();
	      List <Article> articles = articleManger.allArticles();
	      
	      //Durchlaufe Liste und packe Artikel in die Tabelle 
	      for (Article article : articles){%>
	    	  
	    	  
	         <tr>
	         	<td> <%= article.getId() %></td>
	         	<td> <%= article.getName()%></td>
	            <td> <%= article.getPrice()%></td>
	            <td> <%= article.getAmount()%> </td>
	            <td> <a href="Details.jsp?item=<%=article.getId()%>">Details</a></td>
	         </tr>
	   
	     <%}%>
	   </table>
	   
	 <% String id = request.getParameter("item"); %>
	 <% boolean showDeleteText =false; %>
	   

	 
	   
	      	  <% //Aus dem Warenkorb löschen
	      if(request.getParameter("loeschen") != null){
	    	  
	    	  showDeleteText = true;
	    	  
	    	  //Hole Session
	    	 // HttpSession httpSession = request.getSession(true); 
	    	
	    	  //Füge Artikel über ID dem Warenkorb hinzu
	    	  cartManager.removeArtikelCart(httpSession, Integer.parseInt(id));
	    	 
	    	  //Refresh 
	    	  //response.setHeader("Refresh", "0; URL=" + request.getContextPath() + "/Uebersicht.jsp");
	
	      }%>
	      
	      
	    <h2>Warenkorb</h2>

		<table border = 1>
			<tr>
		    	<th>ID</th><th>Artikel</th><th>Preis</th><th>Menge</th><th>Löschen</th>
		    </tr>
			  
			  <% 
		      
		      //Durchlaufe Liste und packe Artikel in die Tabelle 
		      for (Article article : articlesCart){%>
		    	  
		    	  
		         <tr>
		         	<td> <%= article.getId() %></td>
		         	<td> <%= article.getName()%></td>
		            <td> <%= article.getPrice()%></td>
		            <td> <%= article.getAmountInCart()%> </td>
		            <td>             	
		            	<form action="Uebersicht.jsp?item=<%=article.getId()%>" method="Post"> 
							<button type="submit" name="loeschen" value="loeschen">Löschen</button> 
					    </form> </td>
		         </tr>
		   
		     <%}%>
	     
	       </table>
	      
	      
	      <%//Bereche die Gesamtsumme und update sie im Warenkorb 
	      	cartManager.calculate(httpSession);
	      	float total = cart.getTotal();
	      	%>
	      	
	       <h4>Gesamtsumme: <%= total %> Euro</h4>
	       <% if (showDeleteText){%>
	        	Artikel <%-- article.getName()--%> wurde gelöscht <br>
	       <% }%>
	       
	       
	       
	       <form action="Checkout.jsp" method="Post"> 
				<button type="submit" name="checkout" value="checkout">Kaufen</button> 
			</form>
	   




</body>
</html>