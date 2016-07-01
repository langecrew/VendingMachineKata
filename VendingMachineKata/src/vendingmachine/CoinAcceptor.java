package vendingmachine;

import static vendingmachine.VendingMachineConstants.DIME_VALUE;
import static vendingmachine.VendingMachineConstants.NICKEL_VALUE;
import static vendingmachine.VendingMachineConstants.QUARTER_VALUE;
import static vendingmachine.VendingMachineConstants.ZERO;

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

	public int getCoinValue(Coin coin) {
		switch (coin) {
		case DIME:
			return DIME_VALUE;
		case NICKEL:
			return NICKEL_VALUE;
		case QUARTER:
			return QUARTER_VALUE;
		default:
			return ZERO;
		}
	}

	public int acceptCoin(Coin coin) {
		coin = this.identifyCoin(coin.getSize(), coin.getWeight());
		int value = this.getCoinValue(coin);
		return value;
	}

}
