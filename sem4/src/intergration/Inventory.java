package sem4.src.intergration;

import java.util.ArrayList;
import java.util.List;

import sem4.src.DTO.SaleFinalDTO;
import sem4.src.model.Item;
import sem4.src.model.ItemNotFoundException;

/**
 * Class for Inventory
 */
public class Inventory {
	private List<Item> itemList;

	/**
	 * Creates new instance of Inventory
	 * Assumes that the quantities of the items is infinite.
	 */
	public Inventory() {
		itemList = new ArrayList<>();
		itemList.add(new Item(101, 16, 2, "Ballerina Kladdkaka 210g Göteborgs"));
		itemList.add(new Item(102, 9, 1, "Kexchoklad 60g Cloetta"));
		itemList.add(new Item(103, 45, 5, "Rustica 4 Cheese Pizza Fryst 555g"));
		itemList.add(new Item(104, 22, 3, "Gröna kärnfria druvor 500g"));
		itemList.add(new Item(105, 13, 2, "Energidryck Kolsyrad Kiwi Frank´S 50cl"));
		itemList.add(new Item(106, 30, 4, "Avokado Mogen 3-p, Peru"));
		itemList.add(new Item(666, 00, 0, "DATABASE FAILURE"));
		System.out.println(itemList.toString());
	}

	/**
	 * Finds item in inventory, with a given id.
	 * If not found, throws NotFoundException
	 * 
	 * @param item_id
	 * @return
	 */
	public Item findItemWithId(int item_id) throws ItemNotFoundException {
		for (Item item : itemList)
			if (item.getItem_id() == (item_id)) {
				return item;
			}
		throw new ItemNotFoundException("Message: The ID " + item_id + " couldn't be found");
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
