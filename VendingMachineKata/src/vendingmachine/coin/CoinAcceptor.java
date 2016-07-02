package vendingmachine.coin;

import static vendingmachine.VendingMachineConstants.DIME_VALUE;
import static vendingmachine.VendingMachineConstants.NICKEL_VALUE;
import static vendingmachine.VendingMachineConstants.QUARTER_VALUE;
import static vendingmachine.VendingMachineConstants.ZERO;

public class CoinAcceptor {

	public Coin identifyCoin(int size, int weight) {
		boolean coinIsDime = (size == Coin.DIME.getSize() && weight == Coin.DIME.getWeight());
		boolean coinIsPenny = (size == Coin.PENNY.getSize() && weight == Coin.PENNY.getWeight());
		boolean coinIsNickel = (size == Coin.NICKEL.getSize() && weight == Coin.NICKEL.getWeight());
		boolean coinIsQuarter = (size == Coin.QUARTER.getSize() && weight == Coin.QUARTER.getWeight());
		
		if (coinIsDime) {
			return Coin.DIME;
		} else if (coinIsPenny) {
			return Coin.PENNY;
		} else if (coinIsNickel) {
			return Coin.NICKEL;
		} else if (coinIsQuarter) {
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
