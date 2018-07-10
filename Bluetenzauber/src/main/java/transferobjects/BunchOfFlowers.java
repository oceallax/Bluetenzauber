package transferobjects;


public class BunchOfFlowers extends Article{

	String kindOfFlower;
	String event;
	
	//Konstruktor
	public BunchOfFlowers(int id, String name, String picture, String color, String description, 
			float price, int amount, int inOffer, String category,String kindOfFlower, String event) {
		super(id, name, picture, color, description, price, amount, inOffer, category);
		
		this.kindOfFlower = kindOfFlower;
		this.event = event;
	}
	
	//Getter und Setter
	public String getKindOfFlower() {
		return kindOfFlower;
	}

	public void setKindOfFlower(String kindOfFlower) {
		this.kindOfFlower = kindOfFlower;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
