package sem3.src.intergration;

import java.util.ArrayList;
import java.util.List;

import sem3.src.DTO.SaleFinalDTO;
import sem3.src.model.Item;

/**
 * Class for Inventory
 */
public class Inventory {
	private List<Item> itemList;

	/**
	 * Creates new instance of Inventory
	 */
	public Inventory() {
		createItems();
		System.out.println(itemList.toString());
	}

	/**
	 * Finds item in inventory, with a given id.
	 * If not found, then returns a default new Item(000, 0, 0, "NOT FOUND")
	 * as a false.
	 * 
	 * @param item_id
	 * @return
	 */
	public Item findItemWithId(int item_id) {
		for (Item item : itemList) //
			if (item.getItem_id() == (item_id)) {
				return item;
			}

		Item notFound = new Item(000, 0, 0, "NOT FOUND");
		return new Item(notFound, 0);
	}

	/**
	 * Creates new arraylist(inventory list) with items.
	 * id, price, vat, name, quantity
	 * SETS values
	 */
	private void createItems() {
		itemList = new ArrayList<>();

		itemList.add(new Item(101, 16, 2, "Ballerina Kladdkaka 210g Göteborgs"));
		itemList.add(new Item(102, 9, 1, "Kexchoklad 60g Cloetta"));
		itemList.add(new Item(103, 45, 5, "Rustica 4 Cheese Pizza Fryst 555g"));
		itemList.add(new Item(104, 22, 3, "Gröna kärnfria druvor 500g"));
		itemList.add(new Item(105, 13, 2, "Energidryck Kolsyrad Kiwi Frank´S 50cl"));
		itemList.add(new Item(106, 30, 4, "Avokado Mogen 3-p, Peru"));
	}

	/**
	 * Messages that inventory is updated.
	 * 
	 * @param saleInfo
	 */
	public void updateInventory(SaleFinalDTO saleInfo) {
		System.out.println("Message: \"Inventory has been updated\"");
	}

	/**
	 * // maybe implement so that inventory quantity decreases
	 */
}
