package transferobjects;

import java.util.List;

public class ShoppingCart {
	
	private float total;
	//private int[] amountInCart;
	private List<Article> shoppingCart;
	
	//Konstruktor
	public ShoppingCart(float total, List<Article> shoppingCart) {
		
		this.total = total;
		//this.amountInCart = amountInCart;
		this.shoppingCart = shoppingCart;
		
	}
	

	//Setter und Getter
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<Article> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<Article> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


}
