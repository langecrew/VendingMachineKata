package vendingmachine;

import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private int currentTotal = 0;
	private ArrayList<Coin> coinReturn = new ArrayList<>();
	private Product selectedProduct = null;
	private VendingMachineState currentState = VendingMachineState.READY;
	private ArrayList<Coin> insertedCoins = new ArrayList<>();
	
	public String getDisplay() {
		switch (this.currentState ) {
		case READY:
			return "INSERT COIN";
		case COIN_INSERTED:
			return this.formatNumberForDisplay(this.currentTotal);
		case PRODUCT_SELECTED:
			if (this.currentTotal == 0) {
				this.currentState = VendingMachineState.READY;
			} else {
				this.currentState = VendingMachineState.COIN_INSERTED;
			}
			return "PRICE " + this.formatNumberForDisplay(this.selectedProduct.getPrice());
		case DISPENSE_PRODUCT:
			this.currentTotal = 0;
			this.selectedProduct = null;
			this.currentState = VendingMachineState.READY;
			return "THANK YOU";
		case RETURN_COINS:
			this.currentState = VendingMachineState.READY;
			this.insertedCoins.clear();
			return "INSERT COIN";
		default:
			return "INSERT COIN";
		}
	}
	
	private String formatNumberForDisplay(int number) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format((float) number / 100f);
	}

	public void coinInserted(Coin coin) {
		int coinValue = this.coinAcceptor.acceptCoin(coin);
		
		if (coinValue == 0) {
			this.coinReturn.add(coin);
		} else {
			this.insertedCoins.add(coin);
			if (VendingMachineState.READY.equals(this.currentState)) {
				this.currentState = VendingMachineState.COIN_INSERTED;
			}
		}
		this.currentTotal += coinValue;
	}

	public ArrayList<Coin> getCoinReturn() {
		return this.coinReturn;
	}

	public void selectProduct(Product product) {
		if (this.currentTotal == product.getPrice()) {
			this.currentState = VendingMachineState.DISPENSE_PRODUCT;
		} else if (this.currentTotal > product.getPrice()) {
			this.makeChange(product);
			this.currentState = VendingMachineState.DISPENSE_PRODUCT;
		} else {
			this.currentState = VendingMachineState.PRODUCT_SELECTED;
		}
		this.selectedProduct = product;
	}

	private void makeChange(Product product) {
		int difference = this.currentTotal - product.getPrice();
		int numberOfQuarters = putChangeInCoinReturn(Coin.QUARTER, 25, difference);
		
		difference = difference - (numberOfQuarters * 25);
		int numberOfDimes = putChangeInCoinReturn(Coin.DIME, 10, difference);
		
		difference = difference - (numberOfDimes * 10);
		putChangeInCoinReturn(Coin.NICKEL, 5, difference);
	}

	private int putChangeInCoinReturn(Coin coin, int coinValue, int difference) {
		int numberOfCoins = difference / coinValue;
		for (int i = 0; i < numberOfCoins; i++) {
			this.coinReturn.add(coin);
		}
		return numberOfCoins;
	}

	public ArrayList<Coin> returnCoins() {
		this.currentState = VendingMachineState.RETURN_COINS;
		return this.insertedCoins ;
	}

}
