<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="presentation.managed_beans.Article"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddArticle</title>
</head>
<body>

<h1> Füge einen Artikel hinzu </h1>

  <%
    String[] messages = {"","","","","","","",""};
  	Article article = new Article (0, " ", 0, 0);
    // Hole mir Liste mit Fehlermeldungen
   	HttpSession httpSession = request.getSession(true); 
    if(httpSession.getAttribute("messages")!= null){
   		messages = (String[]) httpSession.getAttribute("messages");
       }
    if(httpSession.getAttribute("article")!= null){
   		article = (Article) httpSession.getAttribute("article");
       }
    
   %>


<form action="Frontcontroller?action=addArticle" method="Post"> 
			
	<p>Artikel </p>
	<%=messages[0]%>
	<%=messages[1]%>
	<p>ID<input name="id"  type="number" <% if (article.getId() != 0) { %> value = "<%= article.getId() %>" <% } %>></p>
	<%=messages[2]%>
	<%=messages[3]%>
	<p>Name <input name="name" type=text <% if (!article.getName().contentEquals(" ")) { %> value = "<%= article.getName() %>" <% } %>></p>
	<%=messages[4]%>
	<%=messages[5]%>
	<p>Preis<input name="price" type="number" <% if (article.getPrice() != 0) { %> value = "<%= article.getPrice() %>" <% } %>> </p>
	<%=messages[6]%>
	<%=messages[7]%>
	<p>Menge <input name="amount" type=number <% if (article.getAmount() != 0) { %> value = "<%= article.getAmount() %>" <% } %>></p>
	<button type="submit" name="addArticleButton" value="addArticleButton">Artikel hinzufügen</button> 
	
</form>

</body>
</html>