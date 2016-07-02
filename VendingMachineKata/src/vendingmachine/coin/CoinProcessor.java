package vendingmachine.coin;

import static vendingmachine.VendingMachineConstants.ZERO;

import java.util.ArrayList;

public class CoinProcessor {
	
	private ArrayList<Coin> insertedCoins = new ArrayList<>();
	private CoinAcceptor coinAcceptor = new CoinAcceptor();
	private int currentTotal = ZERO;

	public void processInsertedCoin(Coin coin) {
		int coinValue = this.coinAcceptor.acceptCoin(coin);
		this.insertedCoins.add(coin);
		this.currentTotal  += coinValue;
	}

	public ArrayList<Coin> returnCoins() {
		return this.insertedCoins;
	}

	public void clearInsertedCoins() {
		this.insertedCoins.clear();
	}

	public int getCurrentTotal() {
		return this.currentTotal;
	}

}
