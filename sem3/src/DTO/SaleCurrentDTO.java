package sem3.src.DTO;

import sem3.src.model.Item;

/**
 * This is what will be shown on the display to the customer and cashier
 */

public class SaleCurrentDTO {
	// Current Info about currently scanned item
	private int item_id;
	private int item_price;
	private int item_VAT;
	private String item_name;
	private int item_quantity;

	// current running total and VAT of sale,
	private int runningTotal;
	private int runningVAT;

	/**
	 * Creates instance of SaleCurrentDTO using following @params. Item contains
	 * info/attributes of current item.
	 * 
	 * @param item
	 * @param runningTotal
	 * @param runningVAT
	 */
	public SaleCurrentDTO(Item item, int runningTotal, int runningVAT) {
		item_id = item.getItem_id();
		item_price = item.getItem_price();
		item_VAT = item.getItem_VAT();
		item_name = item.getItem_name();
		item_quantity = item.getItem_quantity();

		this.runningTotal = runningTotal;
		this.runningVAT = runningVAT;
	}

	/**
	 * GETTER METHODS FOR SaleCurrentDTO
	 */

	public int getCurrentItem_id() {
		return this.item_id;
	}

	public int getCurrentItem_price() {
		return this.item_price;
	}

	public int getCurrentItem_VAT() {
		return this.item_VAT;
	}

	public String getCurrentItem_name() {
		return this.item_name;
	}

	public int getCurrentItem_quantity() {
		return this.item_quantity;
	}

	public int getRunningTotal() {
		return this.runningTotal;
	}

	public int getRunningVAT() {
		return this.runningVAT;
	}

	/**
	 * @return A string representation of all fields in this object.
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("");
		string.append("\nItem id: ");
		string.append(item_id + " ");
		string.append("| Item price: ");
		string.append(item_price + " SEK | ");
		string.append("Item VAT: ");
		string.append(item_VAT + " SEK | ");
		string.append("Item name: ");
		string.append(item_name + " ");
		string.append("Item quantity: ");
		string.append(item_quantity + " ");

		string.append("\nRunning total: ");
		string.append(runningTotal);
		string.append("\nRunning VAT: ");
		string.append(runningVAT);

		string.append("");
		return string.toString();
	}
}
