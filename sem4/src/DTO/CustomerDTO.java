package sem4.src.DTO;

/**
 * The class for CustomerDTO.
 */
public class CustomerDTO {
	private int customer_id;

	/**
	 * Creates new constructor of customerDTO using (int)customer_id
	 * 
	 * @param customer_id
	 */
	public CustomerDTO(int customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * Setter for customer_id
	 * 
	 * @param new_id
	 */
	public void setCustomer_id(int new_id) {
		this.customer_id = new_id;
	}

	/**
	 * Getter for customer_id
	 * 
	 * @return
	 */
	public int getCustomer_id() {
		return customer_id;
	}
}
