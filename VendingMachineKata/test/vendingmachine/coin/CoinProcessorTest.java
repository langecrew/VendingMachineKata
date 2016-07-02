package vendingmachine.coin;

import static org.junit.Assert.assertEquals;
import static vendingmachine.VendingMachineConstants.DIME_VALUE;
import static vendingmachine.VendingMachineConstants.ZERO;

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
		int currentTotal = this.coinProcessor.getCurrentTotal();
		assertEquals(ZERO, currentTotal);
		
		this.coinProcessor.processInsertedCoin(Coin.DIME);
		currentTotal = this.coinProcessor.getCurrentTotal();
		assertEquals(DIME_VALUE, currentTotal);
	}
	
	@Test
	public void testCoinProcessorDoesNotAddPenniesToTotal() {
		this.coinProcessor.processInsertedCoin(Coin.PENNY);
		int currentTotal = this.coinProcessor.getCurrentTotal();
		assertEquals(ZERO, currentTotal);
		
		this.coinProcessor.processInsertedCoin(Coin.DIME);
		currentTotal = this.coinProcessor.getCurrentTotal();
		assertEquals(DIME_VALUE, currentTotal);
		
		this.coinProcessor.processInsertedCoin(Coin.PENNY);
		currentTotal = this.coinProcessor.getCurrentTotal();
		assertEquals(DIME_VALUE, currentTotal);
	}
	
	@Test
	public void testCoinProcessorRejectsPennies() {
		ArrayList<Coin> coinReturn = this.coinProcessor.getRejectedCoins();
		ArrayList<Coin> expectedCoinReturn = new ArrayList<>();
		assertEquals(expectedCoinReturn, coinReturn);

		this.coinProcessor.processInsertedCoin(Coin.PENNY);
		expectedCoinReturn.add(Coin.PENNY);
		assertEquals(expectedCoinReturn, coinReturn);
		
		this.coinProcessor.processInsertedCoin(Coin.DIME);
		this.coinProcessor.processInsertedCoin(Coin.NICKEL);
		this.coinProcessor.processInsertedCoin(Coin.QUARTER);
		assertEquals(expectedCoinReturn, coinReturn);
	}

}
