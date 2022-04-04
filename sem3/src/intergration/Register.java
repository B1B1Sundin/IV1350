package sem3.src.intergration;

/**
 * Class for Register of POS
 */
public class Register {
	int balance;

	/**
	 * Create new instance of Register with a inital balance of 1000 SEK, used to
	 * withdraw Change
	 */
	public Register() {
		this.balance = 1000;
	}

	/**
	 * adds payment to instance of Register
	 * 
	 * @param payment
	 */
	public void addPayment(int payment) {
		this.balance += payment;
	}

	/**
	 * GETTER for balance of instance Register
	 * 
	 * @return balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Withdraws change for customer. Change is given if payment > cost
	 * 
	 * @param payment
	 * @param cost
	 * @return change
	 */
	public int getChange(int payment, int cost) {
		int change = payment - cost;
		balance -= change;
		return change;
	}

}
