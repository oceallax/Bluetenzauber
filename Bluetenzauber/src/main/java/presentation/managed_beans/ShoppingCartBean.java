package presentation.managed_beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import businesslogic.ShoppingCartManager;
import transferobjects.Article;
import transferobjects.ShoppingCart;


@ManagedBean(name = "shoppingcartBean")
@SessionScoped
public class ShoppingCartBean implements Serializable {
	
	private float total;
	private List<Article> articlesInCart;
	ShoppingCartManager cartManager;
 	ShoppingCart cart;
 	
 	FacesContext facesContext; 
 	HttpSession session;
 	
	
	public ShoppingCartBean() {

	   	cartManager  = new ShoppingCartManager();
	   	
	   	facesContext = FacesContext.getCurrentInstance();
	    //Erstelle Session, wenn noch keine erstellt wurde 
	   	session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	    //Prüfe, ob es schon einen Warenkorb gibt
	    if(session.getAttribute("cart")== null){
	    	  
	    	//Erstelle einen Warenkorb (Artikel-Liste)
	    	articlesInCart = new LinkedList<>();
	    	cart = new ShoppingCart(0,articlesInCart);
	    	//Erstelle Warenkorb, wenn es keinen gibt (Speichere Session)
	    	session.setAttribute("cart", cart);
	     }else{
	    	// Es existiert ein Warenkorb in der Session
	    	cart = (ShoppingCart) session.getAttribute("cart");
	    	articlesInCart = cart.getShoppingCart();
	     }
		
		
	}

	
	//Getter and Setter
	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public List<Article> getShoppingCart() {
		return articlesInCart;
	}


	public void setShoppingCart(List<Article> shoppingCart) {
		this.articlesInCart = shoppingCart;
	}
	
	
	
	
	

}
