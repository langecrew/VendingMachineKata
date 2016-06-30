package vendingmachine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void testVendingMachineDisplaysInsertCoinWhenNoCoinsHaveBeenInserted() {
		VendingMachine machine = new VendingMachine();
		assertEquals("INSERT COIN", machine.getDisplay());
	}

}
