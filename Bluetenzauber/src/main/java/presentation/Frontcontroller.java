package presentation;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class Frontcontroller
 */
@WebServlet(urlPatterns ={ "/Frontcontroller" })
public class Frontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Frontcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	
		
		boolean showDeleteText =false;
		String id = request.getParameter("item");
		
	   	List <Article> articlesCart;
	   	ShoppingCart cart;
	   	ShoppingCartManager cartManager  = new ShoppingCartManager();
	   	//int[]amountInCart;
	   	
	   	
	    //Erstelle Session, wenn noch keine erstellt wurde 
	    HttpSession httpSession = request.getSession(true); 
	    //Prüfe, ob es schon einen Warenkorb gibt
	    if(httpSession.getAttribute("cart")== null){
	    	  
	    	//Erstelle einen Warenkorb (Artikel-Liste)
	    	articlesCart = new LinkedList<>();
	    	cart = new ShoppingCart(0,articlesCart);
	    	//Erstelle Warenkorb, wenn es keinen gibt (Speichere Session)
	    	httpSession.setAttribute("cart", cart);
	     }else{
	    	// Es existiert ein Warenkorb in der Session
	    	cart = (ShoppingCart) httpSession.getAttribute("cart");
	    	articlesCart = cart.getShoppingCart();
	     }
		
		String viewName = null;
		String action = request.getParameter("action");
		
		
		
		//Action nicht gesetzt -> Übersicht.jsp
		if(action == null) {
			
			/*ArticleManager articleManger = new ArticleManager();
		    List <Article> articles = articleManger.allArticles();
		    request.setAttribute("articleList", articles);
		    
			viewName = "Uebersicht.jsp";
			
			//Bereche die Gesamtsumme und update sie im Warenkorb 
	      	cartManager.calculate(httpSession);*/
		    
			
		}else {
			
	
			switch(action){
		
			case "Details":
				
				ArticleManager articleManger = new ArticleManager();
			    Article article = articleManger.getPlant(Integer.parseInt(id));
			    request.setAttribute("article", article);

				viewName = "Details.jsp";
				
				break;
				
			case "InWarenkorb":
				
				//Artikel in den Warenkorb legen
				viewName = "Details.jsp";
		    	
		    	//Füge Artikel über ID dem Warenkorb hinzu
		    	boolean articleAvailable = cartManager.addArtikelCart(httpSession, Integer.parseInt(id));
		    	 
		    	//TODO Texte anzeigen lassen
	    	    //Artikel verfügbar
	    	    if  (articleAvailable){
	    		    response.getWriter().append("Artikel wurde in den Warenkorb gelegt <br>"); 
	    	    }else{
	    		    response.getWriter().append("Artikel nicht mehr verfügbar:( <br>"); 
	    	    }
	    	    
	    	    break;
	    	    
			case "loeschen":
	    	  
				//Löschen aus Warenkorb
				viewName = "Uebersicht.jsp";
				
		    	 showDeleteText = true;
		    	
		    	 //Füge Artikel über ID dem Warenkorb hinzu
		    	 cartManager.removeArtikelCart(httpSession, Integer.parseInt(id));
		    	 
		    	 //Refresh 
		    	 response.setHeader("Refresh", "0; URL=" + request.getContextPath() + "/Frontcontroller");
		    	 
		    	 //TODO Text einbinden
			     if (showDeleteText){
			    	 response.getWriter().append( "Artikel wurde gelöscht <br>");
			     }
			     
			     //Bereche die Gesamtsumme und update sie im Warenkorb 
			     cartManager.calculate(httpSession);
			     
		    	 break;

			
			case "checkout":
				
				//Kaufen
				viewName = "Checkout.jsp";
		   
				cartManager.checkout(httpSession);
				
				break;
				
			case "addArticle":
				//AuthToken wird über einen Filter geprüft
				String authToken = "admin";
				viewName = "AddArticle.jsp?item="+authToken;
				
				//Prüfe auf leer und Zahl
				int idAddArticle = 0;
				String name = " ";
				float price = 0;
				int amount = 0;
				
				//Fehlermeldungen Leer
				String idEmpty     = "Bitte geben Sie eine ID an";
				String nameEmpty   = "Bitte geben Sie einen Namen an";
				String priceEmpty  = "Bitte geben Sie einen Preis an";
				String amountEmpty = "Bitte geben Sie eine Menge an";
				
				//Fehlermeldungen ungültiger Wert
				String idWrong     = "Bitte geben Sie eine gültige ID an";
				String nameWrong   = "Bitte geben Sie einen gültigen Namen an";
				String priceWrong  = "Bitte geben Sie einen gültigen Preis an";
				String amountWrong = "Bitte geben Sie eine gültige Menge an";
				
				//Liste für Fehlermeldungen
				//String[] messages = new String[7];
				String[] messages = {"","","","","","","",""};
				
				//Sind alle Angaben richtig?
				boolean createArticle = true;
				
				//Button wurde gedrückt (AddArticle.jsp)
				if (request.getParameter("addArticleButton")!=null){
					
					/* ******************** Prüfe Input ********************* */
					
					//0 & 1 ID 
					if (!request.getParameter("id").isEmpty()){
						
						//Input ist nicht leer
						try{
							
							//Packe Wert in Integer-Variabel
							idAddArticle = Integer.valueOf(request.getParameter("id"));
							
						}catch(InputMismatchException exception){
							
							//Angabe ungültig
							createArticle = false;
							messages[1] = idWrong;
						}
						
					}else {
						//id leer
						createArticle = false;
						messages[0]= idEmpty;

					}
					
					//2 & 3 Name
					if (!request.getParameter("name").isEmpty()){
						
						//Input ist nicht leer
						try{
							
							//Packe Wert in Variabel
							name = request.getParameter("name");
							
						}catch(InputMismatchException exception){
							
							//Angabe ungültig
							createArticle = false;
							messages[3] = nameWrong;
						}
						
					}else {
						//name leer
						createArticle = false;
						messages[2]= nameEmpty;

					}
					
					//4 & 5 Preis
					if (!request.getParameter("price").isEmpty()){
						
						//Input ist nicht leer
						try{
							
							//Packe Wert in Variabel
							price = Float.valueOf(request.getParameter("price"));
							
						}catch(InputMismatchException exception){
							
							//Angabe ungültig
							createArticle = false;
							messages[5] = priceWrong;
						}
						
					}else {
						//Preis leer
						createArticle = false;
						messages[4]= priceEmpty;

					}
					
					//6 & 7 Menge
					if (!request.getParameter("amount").isEmpty()){
						
						//Input ist nicht leer
						try{
							
							//Packe Wert in Variabel
							amount = Integer.valueOf(request.getParameter("amount"));
							
						}catch(InputMismatchException exception){
							
							//Angabe ungültig
							createArticle = false;
							messages[7] = amountWrong;
						}
						
					}else {
						//Menge leer
						createArticle = false;
						messages[6]= amountEmpty;

					}
					
					/* ******************** Ende ********************* */

					//Fehlermeldungen in Session speichern
					httpSession.setAttribute("messages", messages);
					
					//Artikel erstellen
			        //Article newArticle = new Article (idAddArticle, name, price, amount);
			        
					//Eingaben vom Benutzer in Session speichern
					/*httpSession.setAttribute("article", newArticle);
					
					if(createArticle) {

				        //Artikel Datenbank hinzufügen
				        ArticleManager articleManager = new ArticleManager();
				        articleManager.addArticle(newArticle);
				        
				        //Gehe auf die Übersicht
				        viewName = "Uebersicht.jsp";
				        
				        //delete session
				        httpSession.removeAttribute("article");*/
						
					}
					
				}
				
				
				
			}
			
		}
		
		//Weiterleitung auf JSP Seite
		//RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
		//dispatcher.forward(request, response); 

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
