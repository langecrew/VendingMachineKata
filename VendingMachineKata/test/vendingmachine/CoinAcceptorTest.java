package vendingmachine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CoinAcceptorTest {
	
	private CoinAcceptor coinAcceptor;
	
	@Before
	public void setup() {
		this.coinAcceptor = new CoinAcceptor();
	}

	@Test
	public void testCoinAcceptorCorrectlyIdentifiesCoinsBySizeAndWeight() {
		Coin coin = this.coinAcceptor.identifyCoin(1, 1);
		assertEquals(Coin.DIME, coin);
		
		coin = this.coinAcceptor.identifyCoin(2, 2);
		assertEquals(Coin.PENNY, coin);
		
		coin = this.coinAcceptor.identifyCoin(3, 3);
		assertEquals(Coin.NICKEL, coin);
		
		coin = this.coinAcceptor.identifyCoin(4, 4);
		assertEquals(Coin.QUARTER, coin);
	}
	
	@Test
	public void testCoinAcceptorAssignsProperValueToCoin() {
		int value = this.coinAcceptor.getCoinValue(Coin.DIME);
		assertEquals(10, value);
		value = this.coinAcceptor.getCoinValue(Coin.NICKEL);
		assertEquals(5, value);
		value = this.coinAcceptor.getCoinValue(Coin.QUARTER);
		assertEquals(25, value);
	}
	
	@Test 
	public void testCoinAcceptorAssignsNoValueToPenny() {
		int value = this.coinAcceptor.getCoinValue(Coin.PENNY);
		assertEquals(0, value);
	}
	
	@Test
	public void testCoinAcceptorAcceptsCoinAndReturnsCorrectValue() {
		int value = this.coinAcceptor.acceptCoin(Coin.DIME);
		assertEquals(10, value);
		
		value = this.coinAcceptor.acceptCoin(Coin.NICKEL);
		assertEquals(5, value);
		
		value = this.coinAcceptor.acceptCoin(Coin.QUARTER);
		assertEquals(25, value);
		
		value = this.coinAcceptor.acceptCoin(Coin.PENNY);
		assertEquals(0, value);
	}

}
