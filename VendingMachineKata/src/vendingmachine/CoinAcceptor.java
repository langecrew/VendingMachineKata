package vendingmachine;

public class CoinAcceptor {

	public Coin identifyCoin(int size, int weight) {
		if (size == Coin.DIME.getSize() && weight == Coin.DIME.getWeight()) {
			return Coin.DIME;
		} else if (size == Coin.PENNY.getSize() && weight == Coin.PENNY.getWeight()) {
			return Coin.PENNY;
		} else if (size == Coin.NICKEL.getSize() && weight == Coin.NICKEL.getWeight()) {
			return Coin.NICKEL;
		} else if (size == Coin.QUARTER.getSize() && weight == Coin.QUARTER.getWeight()) {
			return Coin.QUARTER;
		} else {
			return null;
		}
	}

	public float getCoinValue(Coin coin) {
		switch (coin) {
		case DIME:
			return 0.10f;
		case NICKEL:
			return 0.05f;
		case QUARTER:
			return 0.25f;
		default:
			return 0;
		}
	}

}
