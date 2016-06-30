package vendingmachine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoinAcceptorTest {

	@Test
	public void testCoinAcceptorCorrectlyIdentifiesCoinsBySizeAndWeight() {
		CoinAcceptor coinAcceptor = new CoinAcceptor();
		Coin coin = coinAcceptor.identifyCoin(1, 1);
		assertEquals(Coin.DIME, coin);
		coin = coinAcceptor.identifyCoin(2, 2);
		assertEquals(Coin.PENNY, coin);
	}

}
