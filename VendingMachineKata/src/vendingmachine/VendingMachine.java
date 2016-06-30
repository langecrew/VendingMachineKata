package vendingmachine;

import java.text.NumberFormat;
import java.util.ArrayList;

public class VendingMachine {

	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private float currentTotal = 0;
	private ArrayList<Coin> coinReturn = new ArrayList<>();
	
	public String getDisplay() {
		if (this.currentTotal == 0) {
			return "INSERT COIN";
		} else {
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			return numberFormat.format(this.currentTotal);
		}
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

}
