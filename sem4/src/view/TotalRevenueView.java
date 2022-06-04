package sem4.src.view;

import sem4.src.util.Observer;

public class TotalRevenueView implements Observer<Integer> {
	private int totalRevenue;

	public TotalRevenueView() {
		totalRevenue = 0;
	}

	/**
	 * Method notice is defined in Observer.java.
	 */
	@Override
	public void notice(Integer sum) {
		totalRevenue += sum;
	}

	public void printTotalRevenue() {
		System.out.println("Total revenue from all sales (UI): " + totalRevenue + " SEK");
	}
}
