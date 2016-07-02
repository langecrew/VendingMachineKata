package vendingmachine.coin;

import static org.junit.Assert.assertEquals;
import static vendingmachine.VendingMachineConstants.DIME_VALUE;
import static vendingmachine.VendingMachineConstants.NICKEL_VALUE;
import static vendingmachine.VendingMachineConstants.QUARTER_VALUE;

import java.util.ArrayList;

import org.junit.Test;

import vendingmachine.Product;

public class ChangeMakerTest {

	@Test
	public void testChangeMakerMakesCorrectChangeForGivenProduct() {
		ChangeMaker changeMaker = new ChangeMaker();
		
		int currentTotal = (QUARTER_VALUE * 5) + (DIME_VALUE * 2) + (NICKEL_VALUE * 2);
		
		ArrayList<Coin> change = changeMaker.makeChange(Product.CANDY, currentTotal);
		
		ArrayList<Coin> expectedCoinReturn = new ArrayList<>();
		expectedCoinReturn.add(Coin.QUARTER);
		expectedCoinReturn.add(Coin.QUARTER);
		expectedCoinReturn.add(Coin.QUARTER);
		expectedCoinReturn.add(Coin.DIME);
		expectedCoinReturn.add(Coin.NICKEL);
		
		assertEquals(expectedCoinReturn, change);
	}

}
