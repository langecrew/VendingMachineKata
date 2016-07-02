package vendingmachine.coin;

import java.util.ArrayList;

public class CoinProcessor {
	
	private ArrayList<Coin> insertedCoins = new ArrayList<>();

	public void processInsertedCoin(Coin coin) {
		this.insertedCoins.add(coin);
	}

	public ArrayList<Coin> returnCoins() {
		return this.insertedCoins;
	}

	public void clearInsertedCoins() {
		this.insertedCoins.clear();
	}

}
