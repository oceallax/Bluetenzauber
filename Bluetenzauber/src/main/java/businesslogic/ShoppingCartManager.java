package businesslogic;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import data_access.DAOFactory;
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
		
		total = 0;
		
		//Hole mir Warenkorb über Session
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		//Bereche Gesamtwert des Warenkorbs
		float priceArticle = 0;
		for (Article currentArticle : articleInCart) {
			//TODO int vs float
			priceArticle = currentArticle.getPrice() * currentArticle.getAmountInCart();
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
		
		/*DataAccessArticle articleDAO = DAOFactory.getDAOFactory(1).getArticleDAO();
		
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
		httpSession.setAttribute("cart", cart);*/
		
	}
	
	/**
	 * Methode addArticleCart
	 * fügt einen Artikel dem Warenkorb hinzu
	 */
	public boolean addArtikelCart(HttpSession httpSession, int id) {
		
		return false;
		
	}
	 
	
	/**
	 * Methode removeArticleCart
	 * löscht einen Artikel aus dem Warenkorb
	 */
	public void removeArtikelCart(HttpSession httpSession, int id) {
		System.out.println("remove********************************************");
		//Hole mir Warenkorb über Session
		ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
		
		//Hole mir Korb
		articleInCart = cart.getShoppingCart();
		
		//Liste durchlaufen
		for (Article currentArticle : articleInCart) {
			
			//Artikel in Warenkorb finden
			if(currentArticle.getId()== id) {
				
				System.out.println("Artikel im Warenkorb gefunden");
				
				//Anzahl im Warenkorb
				int amountInCart = currentArticle.getAmountInCart();
				System.out.println("amountInCart "+amountInCart);
				//Wenn ein Artikel im Warenkorb
				if(amountInCart == 1) {
					System.out.println("lösche Artikel");
					//lösche Artikel
					articleInCart.remove(currentArticle);
				}else {
					//veringere Anzahl im Korb
					System.out.println("veringere Anzahl von Artikel");
					currentArticle.setAmountInCart(amountInCart-1);
				}
			}
		}
		
		//Update Warenkorb in Object
		cart.setShoppingCart(articleInCart);
		
		//Session update
		httpSession.setAttribute("cart", cart);
		
	}
	
	


}
