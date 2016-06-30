package vendingmachine;

public enum Coin {

	DIME(1, 1), 
	PENNY(2, 2), 
	NICKEL(3, 3), 
	QUARTER(4, 4);

	private int size;
	private int weight;
	
	private Coin(int size, int weight) {
		this.size = size;
		this.weight = weight;
	}
	
	public int getSize() {
		return this.size;
	}

	public int getWeight() {
		return this.weight;
	}
}
