package vendingmachine;

import static org.junit.Assert.assertEquals;
import static vendingmachine.VendingMachineConstants.INSERT_COIN;
import static vendingmachine.VendingMachineConstants.PRICE;
import static vendingmachine.VendingMachineConstants.THANK_YOU;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import vendingmachine.coin.Coin;

public class VendingMachineTest {
	
	private VendingMachine vendingMachine;
	
	@Before
	public void setup() {
		this.vendingMachine = new VendingMachine();
	}

	@Test
	public void testVendingMachineDisplaysInsertCoinWhenNoCoinsHaveBeenInserted() {
		assertEquals(INSERT_COIN, this.vendingMachine.getDisplay());
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
		assertEquals(INSERT_COIN, this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testVendingMachinePutsPenniesInCoinReturn() {
		ArrayList<Coin> expectedCoinReturn = new ArrayList<>();
		assertEquals(expectedCoinReturn, this.vendingMachine.getCoinReturn());
		
		this.vendingMachine.coinInserted(Coin.PENNY);
		expectedCoinReturn.add(Coin.PENNY);
		assertEquals(expectedCoinReturn, this.vendingMachine.getCoinReturn());
	}
	
	@Test
	public void testVendingMachineCanPutMultiplePenniesInCoinReturn() {
		this.vendingMachine.coinInserted(Coin.PENNY);
		this.vendingMachine.coinInserted(Coin.PENNY);

		ArrayList<Coin> expectedCoinReturn = new ArrayList<>();
		expectedCoinReturn.add(Coin.PENNY);
		expectedCoinReturn.add(Coin.PENNY);
		assertEquals(expectedCoinReturn, this.vendingMachine.getCoinReturn());
	}
	
	@Test
	public void testVendingMachineDisplaysProductPriceWheneThereIsNotEnoughMoneyInserted() {
		this.vendingMachine.selectProduct(Product.COLA);
		assertEquals(PRICE + "$1.00", this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testVendingMachineDisplaysInsertCoinAfterProductPriceCheckWhenThereIsNoMoneyInserted() {
		this.vendingMachine.selectProduct(Product.CHIPS);
		assertEquals(PRICE + "$0.50", this.vendingMachine.getDisplay());
		assertEquals(INSERT_COIN, this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testVendingMachineDisplaysCurrentTotalAfterProductPriceCheckWhenInsufficientCoinsHaveBeenInserted() {
		this.vendingMachine.selectProduct(Product.CANDY);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		assertEquals(PRICE + "$0.65", this.vendingMachine.getDisplay());
		assertEquals("$0.25", this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testVendingMachineDisplaysThankYouIfEnoughMoneyHasBeenInsertedToPurchaseSelectedProduct() {
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.selectProduct(Product.COLA);
		assertEquals(THANK_YOU, this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testVendingMachineDispayIsResetAfterSuccessfullyDispensingProduct() {
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.selectProduct(Product.CHIPS);
		assertEquals(THANK_YOU, this.vendingMachine.getDisplay());
		assertEquals(INSERT_COIN, this.vendingMachine.getDisplay());
	}
	
	@Test
	public void testVendingMachineMakesChangeWhenTotalCoinsInsertedExceedsSelectedProductPrice() {
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.DIME);
		this.vendingMachine.coinInserted(Coin.DIME);
		this.vendingMachine.coinInserted(Coin.NICKEL);
		this.vendingMachine.coinInserted(Coin.NICKEL);
		this.vendingMachine.selectProduct(Product.CANDY);
		
		assertEquals(THANK_YOU, this.vendingMachine.getDisplay());
		
		ArrayList<Coin> expectedCoinReturn = new ArrayList<>();
		expectedCoinReturn.add(Coin.QUARTER);
		expectedCoinReturn.add(Coin.QUARTER);
		expectedCoinReturn.add(Coin.QUARTER);
		expectedCoinReturn.add(Coin.DIME);
		expectedCoinReturn.add(Coin.NICKEL);
		
		assertEquals(expectedCoinReturn, this.vendingMachine.getCoinReturn());
	}
	
	@Test
	public void testVendingMachineCanReturnInsertedCoins() {
		this.vendingMachine.coinInserted(Coin.QUARTER);
		this.vendingMachine.coinInserted(Coin.DIME);
		this.vendingMachine.coinInserted(Coin.NICKEL);
		ArrayList<Coin> returnedCoins = this.vendingMachine.returnCoins();
		
		ArrayList<Coin> expectedReturnedCoins = new ArrayList<>();
		expectedReturnedCoins.add(Coin.QUARTER);
		expectedReturnedCoins.add(Coin.DIME);
		expectedReturnedCoins.add(Coin.NICKEL);
		
		assertEquals(expectedReturnedCoins, returnedCoins);
		assertEquals(INSERT_COIN, this.vendingMachine.getDisplay());
	}

}
