package sem4.src.intergration;

import java.util.ArrayList;

import sem4.src.DTO.CustomerDTO;
import sem4.src.DTO.DiscountDTO;
import sem4.src.exceptions.InvalidException;

/**
 * Class for Discount
 */
public class Discount {
	private ArrayList<DiscountDTO> discount_list;

	/**
	 * Creates new instance of Discount
	 */
	public Discount() {
		createList();
		System.out.println("Message: \"Discount database started succesfully\"");
	}

	/**
	 * Finds discount using the customer's ID.
	 * 
	 * @param customer_id The customer's ID.
	 * @throws InvalidException is thrown if the given ID has no valid discounts.
	 * @return The found disount.
	 */
	public DiscountDTO findDiscountWithId(int customer_id) throws InvalidException {
		for (DiscountDTO discount : discount_list) {
			if (discount.getCustomer_id() == customer_id) {
				return discount;
			}
		}
		throw new InvalidException("Message: \"No valid discounts for the ID.\"" + customer_id);
	}

	/**
	 * Creates discounts, with CustomerDTO.
	 * yyyy-mm-dd.
	 * Year have to be entered first. Else if day is entered first and customer is
	 * born between 01 to 09, then it will not count and match the '0',
	 * 01-09 -> 1-9
	 */
	public void createList() {
		CustomerDTO customer_id1 = new CustomerDTO(19570331);
		CustomerDTO customer_id2 = new CustomerDTO(20010103);
		CustomerDTO customer_id3 = new CustomerDTO(19690420);

		discount_list = new ArrayList<>();
		discount_list.add(new DiscountDTO(customer_id1, 0.95));
		discount_list.add(new DiscountDTO(customer_id2, 0.90));
		discount_list.add(new DiscountDTO(customer_id3, 0.66));
	}

	/**
	 * IMPROVE, add discount criterias, student discount, elderly discount and so
	 * on.
	 */
}
