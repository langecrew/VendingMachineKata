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
			boolean noCoinsHaveBeenInsertedYet = (this.coinProcessor.getCurrentTotal() == ZERO);
			if (noCoinsHaveBeenInsertedYet) {
				this.currentState = VendingMachineState.READY;
			} else {
				this.currentState = VendingMachineState.COIN_INSERTED;
			}
			return PRICE + this.formatNumberForDisplay(this.selectedProduct.getPrice());
		case DISPENSE_PRODUCT:
			this.selectedProduct = null;
			this.coinProcessor.resetCurrentTotal();
			this.currentState = VendingMachineState.READY;
			return THANK_YOU;
		case RETURN_COINS:
			this.coinProcessor.clearInsertedCoins();
			this.coinProcessor.resetCurrentTotal();
			this.currentState = VendingMachineState.READY;
			return INSERT_COIN;
		default:
			return INSERT_COIN;
		}
	}
	
	private String formatNumberForDisplay(int number) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		float intPriceConvertedToFloatForDisplay = (float) number / CURRENCY_CONVERSION_FACTOR;
		return numberFormat.format(intPriceConvertedToFloatForDisplay);
	}

	public void coinInserted(Coin coin) {
		int coinValue = this.coinAcceptor.acceptCoin(coin);
		this.coinProcessor.processInsertedCoin(coin);
		
		boolean insertedCoinWasNotRejected = (coinValue != ZERO);
		boolean vendingMachinIsNotDisplayingCurrentTotalYet = (VendingMachineState.READY.equals(this.currentState));
		if (insertedCoinWasNotRejected && vendingMachinIsNotDisplayingCurrentTotalYet) {
			this.currentState = VendingMachineState.COIN_INSERTED;
		}
	}

	public ArrayList<Coin> getCoinReturn() {
		return this.coinProcessor.getCoinReturn();
	}

	public void selectProduct(Product product) {
		
		boolean sufficientCoinsHaveBeenInsertedToPurchaseProduct = (this.coinProcessor.getCurrentTotal() >= product.getPrice());
		
		if (sufficientCoinsHaveBeenInsertedToPurchaseProduct) {
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
