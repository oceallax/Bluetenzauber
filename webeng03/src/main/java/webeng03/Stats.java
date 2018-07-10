package webeng03;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webeng03.CountSession;

/**
 * Servlet implementation class Stats
 */
//Servlet in den geschützten Bereich gemapped
@WebServlet(urlPatterns ={ "/protected/Stats" })
public class Stats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		CountSession count = new CountSession();
		//Hole Session aus der Request
		HttpSession session = request.getSession();
		
		//Hole String von dem Attribut "user"
		String currentUser = (String) session.getAttribute("user");
		//Erhalte Anzahl der User aus Klasse CountSession
		int userCount = count.getCountUser();
		
		String goBack = request.getHeader("referer");
		
		response.getWriter().append("<!DOCTYPE html>"
				+ "		<html lang=\"de\">\r\n" + 
				"		  <head>\r\n" + 
				"           <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n   " +
				"		    <title>Statistik</title>\r\n" + 
				"		  </head>\r\n" + 
				"		  <body>\r\n" + 
				
		        "		  <h1>Statistik</h1>\r\n" + 
		        "         Anzahl Benutzer: "+ Integer.toString(userCount) +" <br>\r\n" + 
		        "         Benutzer: "+ currentUser +" <br>\r\n" + 
		        // Zurück mit Referer
		        "         <a href="+ goBack +"> Zurueck </a> <br>\r\n" + 

				"		  </body>\r\n" + 
				"		</html>");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
