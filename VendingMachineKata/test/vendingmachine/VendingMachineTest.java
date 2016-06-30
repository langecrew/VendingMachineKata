package vendingmachine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	
	private VendingMachine vendingMachine;
	
	@Before
	public void setup() {
		this.vendingMachine = new VendingMachine();
	}

	@Test
	public void testVendingMachineDisplaysInsertCoinWhenNoCoinsHaveBeenInserted() {
		assertEquals("INSERT COIN", this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testInsertingCoinUpdatesDisplayWithCurrentTotal() {
		this.vendingMachine.coinInserted(Coin.DIME);
		assertEquals("$0.10", this.vendingMachine.getDisplay());
		
		this.vendingMachine.coinInserted(Coin.NICKEL);
		assertEquals("$0.15", this.vendingMachine.getDisplay());
		
		this.vendingMachine.coinInserted(Coin.QUARTER);
		assertEquals("$0.40", this.vendingMachine.getDisplay());
	}

	@Test
	public void testVendingMachineDoesNotAddPenniesToTotal() {
		this.vendingMachine.coinInserted(Coin.PENNY);
		assertEquals("INSERT COIN", this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testVendingMachinePutsPenniesInCoinReturn() {
		assertEquals(null, this.vendingMachine.getCoinReturn());
		
		this.vendingMachine.coinInserted(Coin.PENNY);
		
		Coin coinReturn = this.vendingMachine.getCoinReturn();
		assertEquals(Coin.PENNY, coinReturn);
	}

}
