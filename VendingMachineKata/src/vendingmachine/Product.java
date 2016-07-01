package vendingmachine;

import static vendingmachine.VendingMachineConstants.CANDY_PRICE;
import static vendingmachine.VendingMachineConstants.CHIPS_PRICE;
import static vendingmachine.VendingMachineConstants.COLA_PRICE;

public enum Product {
	COLA(COLA_PRICE), 
	CHIPS(CHIPS_PRICE), 
	CANDY(CANDY_PRICE);
	
	private int price;
	
	private Product(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

}
