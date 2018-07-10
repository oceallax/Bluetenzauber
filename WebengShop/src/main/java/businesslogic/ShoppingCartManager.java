package businesslogic;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import data_access.DAOFactory;
import data_access.DataAccess;
import transferobjects.Article;
import transferobjects.ShoppingCart;

public class ShoppingCartManager {
	
	List <Article> articleInCart = null;
	float total;
	
	
	/**
	 * Methode calculate()
	 * die Gesamtsumme aller Artikel eines Warenkorbs berechnen und
	 * diese dann im Warenkorb-Objekt aktualisieren.
	 */
	public void calculate(HttpSession httpSession) {
		
		System.out.println("calculate***********************************************************");
		
		total = 0;
		
		//Hole mir Warenkorb über Session
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		//Bereche Gesamtwert des Warenkorbs
		float priceArticle = 0;
		for (Article currentArticle : articleInCart) {
			//TODO int vs float
			priceArticle = currentArticle.getPrice();
			//System.out.println("priceArticle "+ priceArticle);
			
			total = total + priceArticle;
			//System.out.println("total "+ total);
		}
		
		//Gesamtsumme im Warenkorb setzten
		cart.setTotal(total);
		
		//Session update
		httpSession.setAttribute("cart", cart);
		
		
	}
	
	
	
	/**
	 * Methode checkout()
	 * die Anzahl von Artikeln im Warenkorb in der Datenbank reduzieren und den Warenkorb zurücksetzen.
	 */
	public void checkout (HttpSession httpSession) {
		
		DataAccess articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		
		//Hole mir Warenkorb über Session
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		
		//Gekaufte Artikel in der Datenbank reduzieren
		try {
			articleDAO.removeBoughtArticle(articleInCart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Warenkorb zurücksetzten
		
		List <Article> emptyList = new LinkedList <Article> ();
		cart.setShoppingCart(emptyList);
		cart.setTotal(0);
		
		//Session update
		httpSession.setAttribute("cart", cart);
		
	}

}
