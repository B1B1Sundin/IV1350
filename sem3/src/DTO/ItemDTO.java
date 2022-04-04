package sem3.src.DTO;

/**
 * Class for ItemDTO
 */
public class ItemDTO {
	private int item_id;
	private int item_VAT;
	private int item_price;
	private String item_name;

	/**
	 * Creates new constructor of ItemDTO, using:
	 * 
	 * @param item_id
	 * @param item_price
	 * @param item_VAT
	 * @param item_name
	 */
	public ItemDTO(int item_id, int item_price, int item_VAT, String item_name) {
		this.item_id = item_id;
		this.item_VAT = item_VAT;
		this.item_price = item_price;
		this.item_name = item_name;
	}

	/**
	 * GETTER METHODS FOR ItemDTO
	 */

	public int getItem_id() {
		return this.item_id;
	}

	public int getItem_price() {
		return this.item_price;
	}

	public int getItem_VAT() {
		return this.item_VAT;
	}

	public String getItem_name() {
		return this.item_name;
	}
}