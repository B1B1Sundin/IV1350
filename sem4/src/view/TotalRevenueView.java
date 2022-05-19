package sem4.src.view;

import sem4.src.model.Sale;
import sem4.src.util.Observer;

public class TotalRevenueView implements Observer<Sale> {
	private int totalRevenue;

	public TotalRevenueView() {
		totalRevenue = 0;
	}

	/**
	 * Method notice is defined in Observer.java.
	 */
	@Override
	public void notice(Sale sale) {
		totalRevenue += sale.getRunningTotal() + sale.getRunningVAT();
	}

	public void printTotalRevenue() {
		System.out.println("Total revenue from all sales (UI): " + totalRevenue + " SEK");
	}
}
