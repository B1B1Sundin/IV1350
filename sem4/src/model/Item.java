package sem4.src.model;

import sem4.src.DTO.ItemDTO;

/**
 * Class for Item
 */
public class Item {
	private int item_id;
	private int item_quantity;
	private int item_price;
	private int item_VAT;
	private String item_name;

	/**
	 * Constructor creates Item with the specified params
	 * 
	 * @param item_id
	 * @param item_price
	 * @param item_VAT
	 * @param item_name
	 */
	public Item(int item_id, int item_price, int item_VAT, String item_name) {
		this.item_id = item_id;
		this.item_VAT = item_VAT;
		this.item_price = item_price;
		this.item_name = item_name;
	}

	/**
	 * Constructor creates Item with specified ItemDTO item
	 * 
	 * @param item
	 */
	public Item(ItemDTO item) {
		item_id = item.getItem_id();
		item_price = item.getItem_price();
		item_VAT = item.getItem_VAT();
		item_name = item.getItem_name();
	}

	/**
	 * Constructor, creates Item with speficed Item and quantity
	 * 
	 * @param item
	 * @param item_quantity
	 */
	public Item(Item item, int item_quantity) {
		this.item_id = item.getItem_id();
		this.item_price = item.getItem_price();
		this.item_VAT = item.getItem_VAT();
		this.item_name = item.getItem_name();
		this.item_quantity = item_quantity;
	}

	/**
	 * SETTEr for quantity of Item
	 * 
	 * @param new_quantity
	 */
	public void setItem_quantity(int new_quantity) {
		this.item_quantity = new_quantity;
	}

	/**
	 * GETTER METHODS FOR Items
	 */
	public int getItem_id() {
		return item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public int getItem_price() {
		return item_price;
	}

	public int getItem_VAT() {
		return item_VAT;
	}

	public int getItem_quantity() {
		return item_quantity;
	}

	/**
	 * @return A string representation of all fields in this object.
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("");
		string.append("\nItem ID: ");
		string.append(item_id);
		string.append(" | Item price : ");
		string.append(item_price);
		string.append(" SEK | Item VAT: ");
		string.append(item_VAT);
		string.append(" SEK | Item name: \"");
		string.append(item_name);
		string.append("\" | Item quantity: ");
		string.append(item_quantity);
		string.append("");
		return string.toString();
	}

	/**
	 * @return True if the specified object is considered to represent the same
	 *         item as this object.
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof Item)) {
			return false;
		}
		Item otherItem = (Item) other;
		return otherItem.item_id == this.item_id && otherItem.item_name == this.item_name
				&& otherItem.item_price == this.item_price && otherItem.item_VAT == this.item_VAT;
	}

}
