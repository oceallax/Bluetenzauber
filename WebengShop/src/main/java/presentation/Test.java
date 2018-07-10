package presentation;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businesslogic.ArticleManager;
import businesslogic.ShoppingCartManager;
import transferobjects.Article;
import transferobjects.ShoppingCart;

/**
 * Servlet implementation class Test
 */
@WebServlet(urlPatterns ={ "/Test" })
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Tabellen erstellen & Artikel einfügen
		 //request.getRequestDispatcher("/Bootstrapping").include(request, response);
		
		//TODO Immer nur einen Artikel in den Warenkorb (amount -1)
		
		//Alle Artikel Ausgeben
		response.getWriter().append("<h2> Test </h2>");
		  String title = "Datenbank";

		  response.getWriter().append(
				  
	        "<table border = \"1\">" +
	        "<tr>" +
	        "<th>ID</th><th>Artikel</th><th>Preis</th><th>Menge</th>"+
	        "</tr>");
		  
		  ArticleManager articleManger = new ArticleManager();
	      List <Article> articles = articleManger.allArticles();
	      
	      //Durchlaufe Liste und packe Artikel in die Tabelle 
	      for (Article article : articles){
	    	  
	    	  
	         response.getWriter().append("<tr><td>" + article.getId() + "</td>");
	         response.getWriter().append("<td> " + article.getName() + "</td>");
	         response.getWriter().append("<td> " + article.getPrice()+ "</td>");
	         response.getWriter().append("<td> " + article.getAmount() + "</td></tr>");
	   
	      }
	      response.getWriter().append("</table>");
	      
	      
		  //Alle Artikel in den Warenkorb/Session packen
	      HttpSession httpSession = request.getSession(true); 
	      //Prüfe, ob es schon einen Warenkorb gibt
	      if(httpSession.getAttribute("cart")== null){
	    	  
	    	  ShoppingCart cart = new ShoppingCart(0, articles);
	    	  //Erstelle Warenkorb, wenn es keinen gibt
	    	  httpSession.setAttribute("cart", cart);
	      }
	      
	      //Methoden calculate und checkout
	      response.getWriter().append("<form action=\"Test\" method=\"post\" autocomplete=\"off\">");
	      response.getWriter().append("<button type=\"submit\" name=\"calculate\" value=\"calculate\">calculate</button>");
	      response.getWriter().append("<button type=\"submit\" name=\"checkout\" value=\"checkout\">checkout</button>");
	      response.getWriter().append("</form>");
	      
	      //checkout
	      if(request.getParameter("checkout") != null){
	    	  
	    
	    	  //Berechne Total und erstelle einen Warenkorb in einer session 
	    	  ShoppingCartManager cartManager  = new ShoppingCartManager();
	    	  cartManager.checkout(httpSession);
	    	  
	    	  //Hole Warenkorb aus Session
	    	  ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
	    	  response.getWriter().append("<h3> Gesamtsumme:  "+ cart.getTotal() +"</h3>");
	    	  
	    	  //Refresh 
	    	  response.setHeader("Refresh", "0; URL=" + request.getContextPath() + "/Test");
	      }
	      
	      //calculate
	      if(request.getParameter("calculate") != null){
	    	  
	    	  //Berechne Total und erstelle einen Warenkorb in einer session 
	    	  ShoppingCartManager cartManager  = new ShoppingCartManager();
	    	  cartManager.calculate(httpSession);
	    	  
	    	  //Hole Warenkorb aus Session
	    	  ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
	    	  response.getWriter().append("<h3> Gesamtsumme:  "+ cart.getTotal() +"</h3>");
	    	  
	    	  //Refresh
	    	  //response.setHeader("Refresh", "0; URL=" + request.getContextPath() + "/Test"); 
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
