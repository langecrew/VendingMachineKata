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
		float value = this.coinAcceptor.getCoinValue(Coin.DIME);
		assertEquals(0.10, value, 0.001);
		value = this.coinAcceptor.getCoinValue(Coin.NICKEL);
		assertEquals(0.05, value, 0.001);
	}

}
