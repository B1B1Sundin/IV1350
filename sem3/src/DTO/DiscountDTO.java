package sem3.src.DTO;

/**
 * Class for DiscountDTO
 */
public class DiscountDTO {
	private double discount_percentage;
	CustomerDTO customer_id;

	/**
	 * Creates new constructor of DiscountDTO
	 * 
	 * @param customer_id
	 * @param discount
	 */
	public DiscountDTO(CustomerDTO customer_id, double discount) {
		this.customer_id = customer_id;
		this.discount_percentage = discount;
	}

	/**
	 * GETTER for discount
	 * 
	 * @return
	 */
	public double getDiscount() {
		return this.discount_percentage;
	}

	/**
	 * GETTER for customer id
	 * 
	 * @return
	 */
	public int getCustomer_id() {
		return customer_id.getCustomer_id();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("");
		string.append("Discount : ");
		double x = 100 - (discount_percentage * 100);
		string.append(x);
		string.append(" % | Customer ID: ");
		int id = customer_id.getCustomer_id();
		String customer_ID = String.valueOf(id);
		string.append(customer_ID.substring(0, 4) + "-");
		string.append(customer_ID.substring(4, 6) + "-");
		string.append(customer_ID.substring(6, 8));
		string.append("");
		return string.toString();
	}

}
