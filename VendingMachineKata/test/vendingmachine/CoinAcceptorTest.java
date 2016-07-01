package vendingmachine;

import static org.junit.Assert.assertEquals;
import static vendingmachine.VendingMachineConstants.DIME_SIZE;
import static vendingmachine.VendingMachineConstants.DIME_VALUE;
import static vendingmachine.VendingMachineConstants.DIME_WEIGHT;
import static vendingmachine.VendingMachineConstants.NICKEL_SIZE;
import static vendingmachine.VendingMachineConstants.NICKEL_VALUE;
import static vendingmachine.VendingMachineConstants.NICKEL_WEIGHT;
import static vendingmachine.VendingMachineConstants.PENNY_SIZE;
import static vendingmachine.VendingMachineConstants.PENNY_WEIGHT;
import static vendingmachine.VendingMachineConstants.QUARTER_SIZE;
import static vendingmachine.VendingMachineConstants.QUARTER_VALUE;
import static vendingmachine.VendingMachineConstants.QUARTER_WEIGHT;
import static vendingmachine.VendingMachineConstants.ZERO;

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
		Coin coin = this.coinAcceptor.identifyCoin(DIME_SIZE, DIME_WEIGHT);
		assertEquals(Coin.DIME, coin);
		
		coin = this.coinAcceptor.identifyCoin(PENNY_SIZE, PENNY_WEIGHT);
		assertEquals(Coin.PENNY, coin);
		
		coin = this.coinAcceptor.identifyCoin(NICKEL_SIZE, NICKEL_WEIGHT);
		assertEquals(Coin.NICKEL, coin);
		
		coin = this.coinAcceptor.identifyCoin(QUARTER_SIZE, QUARTER_WEIGHT);
		assertEquals(Coin.QUARTER, coin);
	}
	
	@Test
	public void testCoinAcceptorAssignsProperValueToCoin() {
		int value = this.coinAcceptor.getCoinValue(Coin.DIME);
		assertEquals(DIME_VALUE, value);
		value = this.coinAcceptor.getCoinValue(Coin.NICKEL);
		assertEquals(NICKEL_VALUE, value);
		value = this.coinAcceptor.getCoinValue(Coin.QUARTER);
		assertEquals(QUARTER_VALUE, value);
	}
	
	@Test 
	public void testCoinAcceptorAssignsNoValueToPenny() {
		int value = this.coinAcceptor.getCoinValue(Coin.PENNY);
		assertEquals(ZERO, value);
	}
	
	@Test
	public void testCoinAcceptorAcceptsCoinAndReturnsCorrectValue() {
		int value = this.coinAcceptor.acceptCoin(Coin.DIME);
		assertEquals(DIME_VALUE, value);
		
		value = this.coinAcceptor.acceptCoin(Coin.NICKEL);
		assertEquals(NICKEL_VALUE, value);
		
		value = this.coinAcceptor.acceptCoin(Coin.QUARTER);
		assertEquals(QUARTER_VALUE, value);
		
		value = this.coinAcceptor.acceptCoin(Coin.PENNY);
		assertEquals(ZERO, value);
	}

}
