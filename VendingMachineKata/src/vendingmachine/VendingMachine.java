package vendingmachine;

import static vendingmachine.VendingMachineConstants.CURRENCY_CONVERSION_FACTOR;
import static vendingmachine.VendingMachineConstants.DIME_VALUE;
import static vendingmachine.VendingMachineConstants.INSERT_COIN;
import static vendingmachine.VendingMachineConstants.NICKEL_VALUE;
import static vendingmachine.VendingMachineConstants.PRICE;
import static vendingmachine.VendingMachineConstants.QUARTER_VALUE;
import static vendingmachine.VendingMachineConstants.THANK_YOU;
import static vendingmachine.VendingMachineConstants.ZERO;

import java.text.NumberFormat;
import java.util.ArrayList;

import vendingmachine.coin.Coin;
import vendingmachine.coin.CoinAcceptor;
import vendingmachine.coin.CoinProcessor;

public class VendingMachine {
	
	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private CoinProcessor coinProcessor = new CoinProcessor();
	private int currentTotal = ZERO;
	private ArrayList<Coin> coinReturn = new ArrayList<>();
	private Product selectedProduct = null;
	private VendingMachineState currentState = VendingMachineState.READY;
	
	public String getDisplay() {
		switch (this.currentState ) {
		case READY:
			return INSERT_COIN;
		case COIN_INSERTED:
			return this.formatNumberForDisplay(this.currentTotal);
		case PRODUCT_SELECTED:
			if (this.currentTotal == ZERO) {
				this.currentState = VendingMachineState.READY;
			} else {
				this.currentState = VendingMachineState.COIN_INSERTED;
			}
			return PRICE + this.formatNumberForDisplay(this.selectedProduct.getPrice());
		case DISPENSE_PRODUCT:
			this.currentTotal = ZERO;
			this.selectedProduct = null;
			this.currentState = VendingMachineState.READY;
			return THANK_YOU;
		case RETURN_COINS:
			this.currentState = VendingMachineState.READY;
			this.coinProcessor.clearInsertedCoins();
			this.currentTotal = ZERO;
			return INSERT_COIN;
		default:
			return INSERT_COIN;
		}
	}
	
	private String formatNumberForDisplay(int number) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format((float) number / CURRENCY_CONVERSION_FACTOR);
	}

	public void coinInserted(Coin coin) {
		int coinValue = this.coinAcceptor.acceptCoin(coin);
		
		if (coinValue == ZERO) {
			this.coinReturn.add(coin);
		} else {
			this.coinProcessor.processInsertedCoin(coin);
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
		int numberOfQuarters = putChangeInCoinReturn(Coin.QUARTER, QUARTER_VALUE, difference);
		
		difference = difference - (numberOfQuarters * QUARTER_VALUE);
		int numberOfDimes = putChangeInCoinReturn(Coin.DIME, DIME_VALUE, difference);
		
		difference = difference - (numberOfDimes * DIME_VALUE);
		putChangeInCoinReturn(Coin.NICKEL, NICKEL_VALUE, difference);
	}

	private int putChangeInCoinReturn(Coin coin, int coinValue, int difference) {
		int numberOfCoins = difference / coinValue;
		for (int i = ZERO; i < numberOfCoins; i++) {
			this.coinReturn.add(coin);
		}
		return numberOfCoins;
	}

	public ArrayList<Coin> returnCoins() {
		this.currentState = VendingMachineState.RETURN_COINS;
		return this.coinProcessor.returnCoins();
	}

}
