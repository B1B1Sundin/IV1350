package sem3.src.DTO;

public class PaymentDTO {
	private int amount;

	/**
	 * Creates constructor of a payment using:
	 * 
	 * @param amount
	 */
	public PaymentDTO(int amount) {
		this.amount = amount;
	}

	/**
	 * GETTER for amount of instance PaymentDTO
	 * 
	 * @return
	 */
	public int getAmount() {
		return this.amount;
	}

}
