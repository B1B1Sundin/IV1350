package sem3.src.intergration;

import java.util.ArrayList;

import sem3.src.DTO.CustomerDTO;
import sem3.src.DTO.DiscountDTO;

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
		System.out.println("Discount database started succesfully");
	}

	/**
	 * Finds matching discount with customer's id.
	 * Returns a default new DiscountDTO(new CustomerDTO(00000000), 0.0)
	 * if none is found (false).
	 * 
	 * @param customer_id
	 * @return
	 */
	public DiscountDTO findDiscountWithId(int customer_id) {
		for (DiscountDTO discount : discount_list) {
			if (discount.getCustomer_id() == customer_id) {
				return discount;
			}
		}
		DiscountDTO notFound = new DiscountDTO(new CustomerDTO(00000000), 0.0);
		return notFound;
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
