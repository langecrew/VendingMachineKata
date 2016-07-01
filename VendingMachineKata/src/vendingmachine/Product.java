package vendingmachine;

public enum Product {
	COLA(100), 
	CHIPS(50), 
	CANDY(65);
	
	private int price;
	
	private Product(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

}
