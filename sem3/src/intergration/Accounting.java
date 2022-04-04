package sem3.src.intergration;

import sem3.src.DTO.SaleFinalDTO;

/**
 * The class for External Accounting System. All profits from sale are added
 * to balance.
 */
public class Accounting {
	private int balance;

	/**
	 * Creates new instace with a inital balance of 10 000 SEK
	 */
	public Accounting() {
		this.balance = 10000;
	}

	/**
	 * Updates the balance with the profit from sale.
	 * SaleFinalDTO saleInfo is the sale log
	 * 
	 * @param saleInfo
	 */
	public void updateAccounting(SaleFinalDTO saleInfo) {
		balance += saleInfo.getTotalPrice();
		balance += saleInfo.getTotalVAT();
		saleInfo.toString();

		System.out.println("Message: \"Accounting has been updated\"");
	}

	/**
	 * GETTTER for the balance of instance Accounting
	 * 
	 * @return
	 */
	public int getAccountingBalance() {
		return this.balance;
	}

}
