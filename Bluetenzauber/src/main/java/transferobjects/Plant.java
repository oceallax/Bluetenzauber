package transferobjects;



public class Plant extends Article {
	

	private String location;
	private int increasedHeight;
	private String floweringOf;
	private String floweringTo;
	
	//Konstruktor
	public Plant(int id, String name, String picture, String color, String description, 
			float price, int amount, int inOffer, String category, String location, 
			int increasedHeight, String floweringOf, String floweringTo) {
		super(id, name, picture, color, description, price, amount, inOffer, category);
		
		this.location = location;
		this.increasedHeight = increasedHeight;
		this.floweringOf = floweringOf;
		this.floweringTo = floweringTo;
	}
	
	
	//Getter und Setter
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getIncreasedHeight() {
		return increasedHeight;
	}

	public void setIncreasedHeight(int increasedHeight) {
		this.increasedHeight = increasedHeight;
	}

	public String getFloweringOf() {
		return floweringOf;
	}

	public void setFloweringOf(String floweringOf) {
		this.floweringOf = floweringOf;
	}

	public String getFloweringTo() {
		return floweringTo;
	}

	public void setFloweringTo(String floweringTo) {
		this.floweringTo = floweringTo;
	}

}
