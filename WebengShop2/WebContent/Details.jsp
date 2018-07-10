<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="businesslogic.ArticleManager"%>
<%@page import="presentation.managed_beans.Article"%>


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
		  
		  <% //ArticleManager articleManger = new ArticleManager();
	      //Article article = articleManger.getArticle(Integer.parseInt(id));
	      Article article = (Article)request.getAttribute("article");
	      %>

	    	  
         <tr>
         	<td> <%= article.getId() %></td>
         	<td> <%= article.getName()%></td>
            <td> <%= article.getPrice()%></td>
            <td> <%= article.getAmount()%> </td>
            <td> 
            	<form action="Frontcontroller?action=InWarenkorb&item=<%=article.getId()%>" method="Post"> 
					<button type="submit" name="kaufen" value="kaufen">In den Warenkorb</button> 
				</form> 
			</td>
         </tr>
	    
	   </table>
	   

      
      <a href= "Frontcontroller">Zurück Übersicht</a>


</body>
</html>