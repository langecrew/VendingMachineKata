package vendingmachine;

public enum Product {
	COLA(1.00f), 
	CHIPS(0.50f), 
	CANDY(0.65f);
	
	private float price;
	
	private Product(float price) {
		this.price = price;
	}

	public float getPrice() {
		return this.price;
	}

}
