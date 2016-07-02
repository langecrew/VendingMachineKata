package vendingmachine.coin;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CoinProcessorTest {
	
	private CoinProcessor coinProcessor;
	
	@Before
	public void setup() {
		this.coinProcessor = new CoinProcessor();
	}

	@Test
	public void testCoinProcessorCanReturnCoins() {
		this.coinProcessor.processInsertedCoin(Coin.QUARTER);
		this.coinProcessor.processInsertedCoin(Coin.DIME);
		this.coinProcessor.processInsertedCoin(Coin.NICKEL);
		ArrayList<Coin> returnedCoins = this.coinProcessor.returnCoins();
		
		ArrayList<Coin> expectedReturnedCoins = new ArrayList<>();
		expectedReturnedCoins.add(Coin.QUARTER);
		expectedReturnedCoins.add(Coin.DIME);
		expectedReturnedCoins.add(Coin.NICKEL);
		
		assertEquals(expectedReturnedCoins, returnedCoins);
	}
	
	@Test
	public void testCoinProcessorCanClearInsertedCoinTracking() {
		this.coinProcessor.processInsertedCoin(Coin.QUARTER);
		ArrayList<Coin> returnedCoins = this.coinProcessor.returnCoins();
		
		ArrayList<Coin> expectedReturnedCoins = new ArrayList<>();
		expectedReturnedCoins.add(Coin.QUARTER);
		
		assertEquals(expectedReturnedCoins, returnedCoins);
		
		this.coinProcessor.clearInsertedCoins();
		
		returnedCoins = this.coinProcessor.returnCoins();
		expectedReturnedCoins.clear();
		assertEquals(expectedReturnedCoins, returnedCoins);
	}
	
	@Test
	public void testCoinProcessorAddsCoinValuesToTotal() {
		this.coinProcessor.processInsertedCoin(Coin.DIME);
		int currentTotal = this.coinProcessor.getCurrentTotal();
	}

}
