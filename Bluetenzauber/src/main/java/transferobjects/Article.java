package transferobjects;

public class Article {
	

	private int id;
	private String name;
	private String picture;
	private String color;
	private String description;
	private float price;
	private int amount;
	//1 true, 0 false
	private int inOffer;
	private String category;
	private int amountInCart;
	
	//Konstruktor
	public Article (int id, String name, String picture, String color, String description, 
			float price, int amount, int inOffer, String category){
		
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.color = color;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.inOffer= inOffer;
		this.category= category;
		this.amountInCart = 1;
	}
	
	
	//Konstruktor leer
	public Article() {
		// TODO Auto-generated constructor stub
	}


	//Getter und Setter
	public int getAmountInCart() {
		return amountInCart;
	}

	public void setAmountInCart(int amountInCart) {
		this.amountInCart = amountInCart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInOffer() {
		return inOffer;
	}

	public void setInOffer(int inOffer) {
		this.inOffer = inOffer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
