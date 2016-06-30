package vendingmachine;

public class VendingMachine {

	private float currentTotal = 0;
	
	public String getDisplay() {
		if (this.currentTotal == 0) {
			return "INSERT COIN";
		} else {
			return "$0.10";
		}
	}

	public void coinInserted(Coin coin) {
		this.currentTotal = 0.10f;
	}

}
