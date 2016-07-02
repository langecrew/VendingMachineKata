package vendingmachine.coin;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CoinProcessorTest {

	@Test
	public void testCoinProcessorCanReturnCoins() {
		CoinProcessor coinProcessor = new CoinProcessor();
		coinProcessor.processInsertedCoin(Coin.QUARTER);
		coinProcessor.processInsertedCoin(Coin.DIME);
		coinProcessor.processInsertedCoin(Coin.NICKEL);
		
		ArrayList<Coin> returnedCoins = coinProcessor.returnCoins();
		ArrayList<Coin> expectedReturnedCoins = new ArrayList<>();
		expectedReturnedCoins.add(Coin.QUARTER);
		expectedReturnedCoins.add(Coin.DIME);
		expectedReturnedCoins.add(Coin.NICKEL);
		assertEquals(expectedReturnedCoins, returnedCoins);
	}

}
