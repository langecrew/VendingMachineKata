package vendingmachine.coin;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CoinProcessorTest {

	@Test
	public void testCoinProcessorCanReturnCoins() {
		CoinProcessor coinProcessor = new CoinProcessor();
		coinProcessor.addCoinToInsertedCoins(Coin.DIME);
		
		ArrayList<Coin> returnedCoins = coinProcessor.returnCoins();
		ArrayList<Coin> expectedReturnedCoins = new ArrayList<>();
		expectedReturnedCoins.add(Coin.DIME);
		assertEquals(expectedReturnedCoins, returnedCoins);
	}

}
