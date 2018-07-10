<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Übung</title>
</head>
<body>

<!-- Aufgabe 2.1 Übungen -->
<!-- a) Expressions: aktuelles Datum und Mathematische Funktion -->
<h1> Übung für JSP: </h1>
<h2> Aufgabe a) Expressions</h2>
<p>  Ausgabe des aktuellen Datums: <%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%> </p>
<p>  Einfache Mathe-Aufgabe: 1+5= <%=1+5 %></p>


<h2> Aufgabe b) Scriptlets</h2>
<form action="" method="Post"> 
<p>Passwortgenerator </p>
	<p>Codewort<input name="codewort" type="text" value="abc"> </p>
	<p>Verschiebung <input name="verschiebung" type=number></p>
	<button type="submit" name="erstellen">Passwort generieren</button> 
	
			<%
			int ausgabe;
			String passwort="";
			// sobald der Button für die Passwortgenerierung gedrückt wurde
			if (request.getParameter("erstellen")!=null){
				// nur wenn die zwei Eingabefelder nicht leer sind
				if (!request.getParameter("codewort").isEmpty() && !request.getParameter("verschiebung").isEmpty()){
					// Parameter in Variablen speichern
					String codewort=request.getParameter("codewort");
					int verschiebung=1+Integer.valueOf(request.getParameter("verschiebung"));
					// Passwort generieren
					// durch einzelne Buchstaben im Codewort iterieren, verschieben und als neuen String zusammenfügen
					char [] buchstabenArray=codewort.toCharArray();
					
					for (char buchstabe:buchstabenArray){
						char neuerBuchstabe=0;
						for (int i=0; i<verschiebung; i++){
							neuerBuchstabe=buchstabe++;
						}
						passwort=passwort+neuerBuchstabe;
					}
					ausgabe=1;
				}
		
				else ausgabe=0;
			}
			else ausgabe=-1;
			%>
			<p>
			<%switch (ausgabe){ case 1: %> Passwort:   <input type="text" name="passwort" value=<%=passwort %>> <%break;
			case 0: %> Bitte ergänze dein Codewort und deine Verschiebung. <%break;
			case -1:%> Um ein Passwort zu generieren, fülle die Formularfelder aus und drücke auf den Button! <%break;	} %> 
			</p>
</form>

<h2> Aufgabe c) Implizite Objekte</h2>

	<% out.println(getServletContext().getInitParameter("test").toString()); %>

</body>
</html>