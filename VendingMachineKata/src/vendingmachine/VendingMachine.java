package vendingmachine;

import java.text.NumberFormat;

public class VendingMachine {

	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private float currentTotal = 0;
	
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
		this.currentTotal += coinValue;
	}

	public Coin getCoinReturn() {
		return Coin.PENNY;
	}

}
