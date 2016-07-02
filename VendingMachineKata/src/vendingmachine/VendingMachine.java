package vendingmachine;

import static vendingmachine.VendingMachineConstants.CURRENCY_CONVERSION_FACTOR;
import static vendingmachine.VendingMachineConstants.INSERT_COIN;
import static vendingmachine.VendingMachineConstants.PRICE;
import static vendingmachine.VendingMachineConstants.THANK_YOU;
import static vendingmachine.VendingMachineConstants.ZERO;

import java.text.NumberFormat;
import java.util.ArrayList;

import vendingmachine.coin.ChangeMaker;
import vendingmachine.coin.Coin;
import vendingmachine.coin.CoinAcceptor;
import vendingmachine.coin.CoinProcessor;

public class VendingMachine {
	
	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private CoinProcessor coinProcessor = new CoinProcessor();
	private ChangeMaker changeMaker = new ChangeMaker();
	private Product selectedProduct = null;
	private VendingMachineState currentState = VendingMachineState.READY;
	
	public String getDisplay() {
		switch (this.currentState ) {
		case READY:
			return INSERT_COIN;
		case COIN_INSERTED:
			return this.formatNumberForDisplay(this.coinProcessor.getCurrentTotal());
		case PRODUCT_SELECTED:
			if (this.coinProcessor.getCurrentTotal() == ZERO) {
				this.currentState = VendingMachineState.READY;
			} else {
				this.currentState = VendingMachineState.COIN_INSERTED;
			}
			return PRICE + this.formatNumberForDisplay(this.selectedProduct.getPrice());
		case DISPENSE_PRODUCT:
			this.coinProcessor.resetCurrentTotal();;
			this.selectedProduct = null;
			this.currentState = VendingMachineState.READY;
			return THANK_YOU;
		case RETURN_COINS:
			this.currentState = VendingMachineState.READY;
			this.coinProcessor.clearInsertedCoins();
			this.coinProcessor.resetCurrentTotal();;
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
		this.coinProcessor.processInsertedCoin(coin);
		if (coinValue != 0 && VendingMachineState.READY.equals(this.currentState)) {
			this.currentState = VendingMachineState.COIN_INSERTED;
		}
	}

	public ArrayList<Coin> getCoinReturn() {
		return this.coinProcessor.getCoinReturn();
	}

	public void selectProduct(Product product) {
		if (this.coinProcessor.getCurrentTotal() >= product.getPrice()) {
			ArrayList<Coin> change = this.changeMaker.makeChange(product, this.coinProcessor.getCurrentTotal());
			this.coinProcessor.addCoinsToCoinReturn(change);
			this.currentState = VendingMachineState.DISPENSE_PRODUCT;
		} else {
			this.currentState = VendingMachineState.PRODUCT_SELECTED;
		}
		this.selectedProduct = product;
	}

	public ArrayList<Coin> returnCoins() {
		this.currentState = VendingMachineState.RETURN_COINS;
		return this.coinProcessor.returnCoins();
	}

}
