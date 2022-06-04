package sem4.src.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sem4.src.util.Observer;

import sem4.src.DTO.PaymentDTO;
import sem4.src.DTO.SaleCurrentDTO;
import sem4.src.DTO.SaleFinalDTO;

public class Sale {
	private ArrayList<Item> requestedItems; // contains all items from customer
	private int runningTotal = 0;
	private int runningVAT = 0;
	private List<Observer<Integer>> observers;

	/**
	 * Constructor creates Sale. Creates new list for new sale
	 */
	public Sale() {
		requestedItems = new ArrayList<>();
		observers = new LinkedList<>();
	}

	/**
	 * Adds an observer
	 * 
	 * @param obs observer to add
	 */
	public void addObserver(Observer<Integer> obs) {
		observers.add(obs);
	}

	/**
	 * Notifys the observers.
	 * Used this as the observers is based on instance of Sale object.
	 */
	private void notifyObservers() {
		for (Observer<Integer> obs : observers) {
			obs.notice(this.getRunningTotal() + this.getRunningVAT());
		}
	}

	/**
	 * Adds a new item to sale using following parameters;
	 * Updates the running totals and creates a new SaleCurrentDTO with new item and
	 * updated running totals -> to be shown on "display"
	 * 
	 * 
	 * @param item
	 * @param quantity
	 * @return
	 */
	public SaleCurrentDTO addNewItem(Item item, int quantity) {
		Item newItem = new Item(item, quantity);
		requestedItems.add(newItem);
		updateRunningTotal();
		SaleCurrentDTO currentItem = new SaleCurrentDTO(newItem, this.runningTotal, this.runningVAT);
		return currentItem;
	}

	/**
	 * Increases the quantity of an existing item in current sale.
	 * 
	 * @param item
	 * @param quantity
	 * @return
	 */
	public SaleCurrentDTO increaseQuantity(Item item, int quantity) {
		int new_quantity = 0;

		for (Item foundItem : requestedItems) {
			if (foundItem.getItem_id() == item.getItem_id()) {
				new_quantity = foundItem.getItem_quantity() + quantity;
				foundItem.setItem_quantity(new_quantity);
				item = foundItem;
				updateRunningTotal();
			}
		}
		updateRunningTotal();
		return new SaleCurrentDTO(item, this.runningTotal, this.runningVAT); // create new SaleCurrentDTO
	}

	/**
	 * Checks if item already exists in current sale.
	 * 
	 * @param item
	 * @return
	 */
	public boolean itemAlreadyExists(Item item) {
		for (Item match : requestedItems) {
			if (match.getItem_id() == item.getItem_id()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds all items that has been scanned and returns that list
	 * 
	 * @return
	 */
	public ArrayList<Item> getAllItems() {
		ArrayList<Item> allItems = new ArrayList<Item>();
		for (Item item : this.requestedItems) {
			if (allItems.contains(item)) {
			} else {
				allItems.add(new Item(item, item.getItem_quantity()));
			}
		}
		return allItems;
	}

	/**
	 * Finalizes the sale. Creating SaleFinalDTO with all info about the concluded
	 * sale.
	 * 
	 * @param item
	 * @return SaleFinalDTO
	 */
	public SaleFinalDTO convertSaleToFinalDTO() {
		LocalDateTime dateAndTime = LocalDateTime.now();
		SaleFinalDTO salelog = new SaleFinalDTO(dateAndTime, requestedItems, this.runningTotal, this.runningVAT);
		this.notifyObservers();
		return salelog;
	}

	/**
	 * Updates the running totals.
	 */
	public void updateRunningTotal() {
		int item_total = 0;
		int item_VAT = 0;
		for (Item item : requestedItems) {
			item_total += item.getItem_price() * item.getItem_quantity();
			item_VAT += item.getItem_VAT() * item.getItem_quantity();
		}
		this.runningTotal = item_total;
		this.runningVAT = item_VAT;

	}

	/**
	 * Prints in case of item is not found
	 */
	public void notFound() {
		System.out.println("NOT FOUND");
	}

	/**
	 * GETTER METHODS FOR Sale
	 */

	public int getRunningTotal() {
		return this.runningTotal;
	}

	public int getRunningVAT() {
		return this.runningVAT;
	}

	public void setVAT(int new_VAT) {
		this.runningVAT = new_VAT;
	}

	public void setTotal(int new_total) {
		this.runningTotal = new_total;
	}

	public Receipt endSale(PaymentDTO payment, SaleFinalDTO sale) {
		return new Receipt(payment, sale);
	}

	/**
	 * @return A string representation of all fields in this object.
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Items:\n");
		string.append(requestedItems + "\n");
		// string.append(" | item quantity: ");
		// string.append(quantity);
		string.append("Running total: ");
		string.append(runningTotal);
		string.append(" kr | Running VAT: ");
		string.append(runningVAT);
		string.append(" kr ");
		return string.toString();
	}

}
