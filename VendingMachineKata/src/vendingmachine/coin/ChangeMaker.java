package vendingmachine.coin;

import static vendingmachine.VendingMachineConstants.DIME_VALUE;
import static vendingmachine.VendingMachineConstants.NICKEL_VALUE;
import static vendingmachine.VendingMachineConstants.QUARTER_VALUE;
import static vendingmachine.VendingMachineConstants.ZERO;

import java.util.ArrayList;

import vendingmachine.Product;

public class ChangeMaker {
	
	private ArrayList<Coin> change;

	public ArrayList<Coin> makeChange(Product product, int currentTotal) {
		
		this.change = new ArrayList<>();
		
		int difference = currentTotal - product.getPrice();
		int numberOfQuarters = putChangeInCoinReturn(Coin.QUARTER, QUARTER_VALUE, difference);
		
		difference = difference - (numberOfQuarters * QUARTER_VALUE);
		int numberOfDimes = putChangeInCoinReturn(Coin.DIME, DIME_VALUE, difference);
		
		difference = difference - (numberOfDimes * DIME_VALUE);
		putChangeInCoinReturn(Coin.NICKEL, NICKEL_VALUE, difference);
		
		return this.change;
	}

	private int putChangeInCoinReturn(Coin coin, int coinValue, int difference) {
		int numberOfCoins = difference / coinValue;
		for (int i = ZERO; i < numberOfCoins; i++) {
			this.change.add(coin);
		}
		return numberOfCoins;
	}
	
}
