package vendingmachine;

import static vendingmachine.VendingMachineConstants.DIME_SIZE;
import static vendingmachine.VendingMachineConstants.DIME_WEIGHT;
import static vendingmachine.VendingMachineConstants.NICKEL_SIZE;
import static vendingmachine.VendingMachineConstants.NICKEL_WEIGHT;
import static vendingmachine.VendingMachineConstants.PENNY_SIZE;
import static vendingmachine.VendingMachineConstants.PENNY_WEIGHT;
import static vendingmachine.VendingMachineConstants.QUARTER_SIZE;
import static vendingmachine.VendingMachineConstants.QUARTER_WEIGHT;

public enum Coin {

	DIME(DIME_SIZE, DIME_WEIGHT), 
	PENNY(PENNY_SIZE, PENNY_WEIGHT), 
	NICKEL(NICKEL_SIZE, NICKEL_WEIGHT), 
	QUARTER(QUARTER_SIZE, QUARTER_WEIGHT);

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
