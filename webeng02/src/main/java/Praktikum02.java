

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Praktikum02
 */
@WebServlet(urlPatterns ={ "/Praktikum02" })
public class Praktikum02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Praktikum02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Titel
		response.getWriter().append("<h1> 2. Praktikum WebEng </h1>");
		
		/*Links-> Setze Action Parameter
		response.getWriter().append("<a href=\"Praktikum02?action=/home\">    Link Home</a>   <br>");
		response.getWriter().append("<a href=\"Praktikum02?action=/header\">  Link Header</a> <br>");
		response.getWriter().append("<a href=\"Praktikum02?action=/cookies\"> Link Cookies</a><br>");
		//response.getWriter().append("<a href=\"Praktikum02?action=/search\">  Link Search</a> <br>");
		//TODO ??? Anfrage zu Suchmaschine weiterleiten ???
		response.getWriter().append("<a href= https://www.google.com\">  Link Search</a> <br>");
		*/
		
		//Navigation einbinden
		RequestDispatcher rd = request.getRequestDispatcher("Navigation");
		rd.include(request,response);
		
		//Hole mir Action-Parameter aus dem request-Attribut
		String contextPath = request.getParameter("action");
		switch(contextPath) {
		
		case "/home":
			response.getWriter().append("<h2> Startseite </h2>");
			response.getWriter().append("Willkommen <br>");
			response.getWriter().append("Name: Jana Pelzer <br>");
			response.getWriter().append("MatrNr.: 620276 <br>");
			break;
			
		case "/header":
			response.getWriter().append("<h2> HttpHeader </h2>");
			  String title = "Header-Analyse";

			  response.getWriter().append(
					  
		        "<table border = \"1\">" +
		        "<tr>" +
		        "<th>Header Name</th><th>Header Wert(e)</th>"+
		        "</tr>");
		 
		      Enumeration headerNames = request.getHeaderNames();
		      
		      while(headerNames.hasMoreElements()) {
		         String paramName = (String)headerNames.nextElement();
		         response.getWriter().append("<tr><td>" + paramName + "</td>");
		         String paramValue = request.getHeader(paramName);
		         response.getWriter().append("<td> " + paramValue + "</td>");
		         
		         String paramExplain = " ";
		         if(paramName.equals("Accept")) {
		        	 paramExplain = "Formate, die der Browser akzeptiert";
		         }
		         if(paramName.equals("User-Agent")) {
		        	 paramExplain = "Mein Browser";
		         }

		         response.getWriter().append("<td> " + paramExplain + "</td></tr>");
		   
		      }
		      response.getWriter().append("</table>");
			break;
			
		case "/cookies":
			//TODO Alle Cookies ausgeben
			response.getWriter().append("<h2>Cookies</h2>");
	
			
		      //Cookies auslesen
				Cookie[] cookies = request.getCookies();
					if(cookies != null){
						for(int i = 0; i < cookies.length; i++){
							
							String cookieName = cookies[i].getName();
							int cookieAge = cookies[i].getMaxAge();
							
							response.getWriter().append("cookieName: "+cookieName+ " cookieAge: "+ cookieAge);
							response.getWriter().append("<br>");
					

						}
					}
				break;

		case "/search":
			//response.getWriter().append("Search");
			response.setStatus(301);
			response.addHeader("Location", "https://www.google.com/");
			//response.sendRedirect("https://www.google.com/");
			
			break;
			
		default:
			response.getWriter().append("<h2> Startseite </h2>");
			response.getWriter().append("Willkommen <br>");
			response.getWriter().append("Name: Jana Pelzer <br>");
			response.getWriter().append("MatrNr.: 620276 <br>");
		}
		

		

    
	      // Cookie erstellen     
	      //Cookie myCookie = new Cookie("lastVisit", ft.format(dNow));
	      Cookie myCookie = new Cookie("Hallo", contextPath);
	      


	      // Lebensspanne des Cookies in s
	      myCookie.setMaxAge(10); 
	      

	      // Füge den Cookie dem response header hinzu
	      response.addCookie( myCookie );
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
