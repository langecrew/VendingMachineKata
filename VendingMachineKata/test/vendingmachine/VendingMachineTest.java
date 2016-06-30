package vendingmachine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
		ArrayList<Coin> expectedCoinReturn = new ArrayList<>();
		assertEquals(expectedCoinReturn, this.vendingMachine.getCoinReturn());
		
		this.vendingMachine.coinInserted(Coin.PENNY);
		ArrayList<Coin> coinReturn = this.vendingMachine.getCoinReturn();
		expectedCoinReturn.add(Coin.PENNY);
		assertEquals(expectedCoinReturn, this.vendingMachine.getCoinReturn());
	}
	
	@Test
	public void testVendingMachineCanPutMultiplePenniesInCoinReturn() {
		this.vendingMachine.coinInserted(Coin.PENNY);
		this.vendingMachine.coinInserted(Coin.PENNY);
		ArrayList<Coin> coinReturn = this.vendingMachine.getCoinReturn();

		ArrayList<Coin> expectedCoinReturn = new ArrayList<>();
		expectedCoinReturn.add(Coin.PENNY);
		expectedCoinReturn.add(Coin.PENNY);
		assertEquals(expectedCoinReturn, this.vendingMachine.getCoinReturn());
	}

}
