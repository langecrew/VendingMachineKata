package vendingmachine;

import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private float currentTotal = 0;
	private ArrayList<Coin> coinReturn = new ArrayList<>();
	private Product selectedProduct = null;
	private VendingMachineState currentState = VendingMachineState.READY;
	
	public String getDisplay() {
		switch (this.currentState ) {
		case READY:
			return "INSERT COIN";
		case COIN_INSERTED:
			return this.formatNumberForDisplay(this.currentTotal);
		case PRODUCT_SELECTED:
			if (this.currentTotal == 0) {
				this.currentState = VendingMachineState.READY;
			}
			return "PRICE " + this.formatNumberForDisplay(this.selectedProduct.getPrice());
		default:
			return "INSERT COIN";
		}
	}
	
	private String formatNumberForDisplay(float number) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(number);
	}

	public void coinInserted(Coin coin) {
		float coinValue = this.coinAcceptor.acceptCoin(coin);
		
		if (coinValue == 0) {
			this.coinReturn.add(coin);
		} else {
			this.currentState = VendingMachineState.COIN_INSERTED;
		}
		this.currentTotal += coinValue;
	}

	public ArrayList<Coin> getCoinReturn() {
		return this.coinReturn;
	}

	public void selectProduct(Product product) {
		this.currentState = VendingMachineState.PRODUCT_SELECTED;
		this.selectedProduct = product;
	}

}
