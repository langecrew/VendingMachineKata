package vendingmachine;

public class CoinAcceptor {

	public Coin identifyCoin(int size, int weight) {
		if (size == Coin.DIME.getSize() && weight == Coin.DIME.getWeight()) {
			return Coin.DIME;
		} else {
			return Coin.PENNY;
		}
	}

}
