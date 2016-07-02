package vendingmachine.coin;

import java.util.ArrayList;

public class CoinProcessor {

	public void addCoinToInsertedCoins(Coin dime) {
		
	}

	public ArrayList<Coin> returnCoins() {
		ArrayList<Coin> returnedCoins = new ArrayList<>();
		returnedCoins.add(Coin.DIME);
		return returnedCoins;
	}

}
