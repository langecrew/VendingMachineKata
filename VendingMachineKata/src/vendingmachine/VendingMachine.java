package vendingmachine;

import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private float currentTotal = 0;
	private ArrayList<Coin> coinReturn = new ArrayList<>();
	private Product selectedProduct = null;
	
	public String getDisplay() {
		if (this.selectedProduct != null) {
			return "PRICE " + this.formatNumberForDisplay(this.selectedProduct.getPrice());
		} else if (this.currentTotal == 0) {
			return "INSERT COIN";
		} else  {
			return this.formatNumberForDisplay(this.currentTotal);
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
		}
		this.currentTotal += coinValue;
	}

	public ArrayList<Coin> getCoinReturn() {
		return this.coinReturn;
	}

	public void selectProduct(Product product) {
		this.selectedProduct = product;
	}

}
