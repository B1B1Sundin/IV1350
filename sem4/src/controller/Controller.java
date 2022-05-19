package sem4.src.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sem4.src.DTO.DiscountDTO;
import sem4.src.DTO.PaymentDTO;
import sem4.src.DTO.SaleCurrentDTO;
import sem4.src.DTO.SaleFinalDTO;
import sem4.src.intergration.DataBaseException;
import sem4.src.model.ItemNotFoundException;
import sem4.src.exceptions.InvalidException;
import sem4.src.intergration.Accounting;
import sem4.src.intergration.Discount;
import sem4.src.intergration.Inventory;
import sem4.src.intergration.Printer;
import sem4.src.intergration.Register;
import sem4.src.model.Item;
import sem4.src.model.Receipt;
import sem4.src.model.Sale;
import sem4.src.util.Observer;

public class Controller {
	private Sale sale;
	private Register reg;
	private Inventory inv;
	private Accounting acc;
	private Printer printer;
	private Discount disc;
	private List<Observer<Sale>> observers;

	/**
	 * Creates a new constructer of Controller
	 * 
	 * @param inv
	 */
	public Controller(Inventory inv) {
		this.inv = inv; // Display inventory
		observers = new LinkedList<>();
		System.out.println("\nMessage: \"Controller was started successfully\""); // throw controller exception?
	}

	/**
	 * Adds an observer
	 * 
	 * @param obs observer to add
	 */
	public void addObserver(Observer<Sale> obs) {
		observers.add(obs);
	}

	/**
	 * Notifys the observers.
	 * Used this.sale as the observers is based on Sale object. If we just used
	 * "this" then it would notify the whole instance of Controller.
	 */
	private void notifyObservers() {
		for (Observer<Sale> obs : observers) {
			obs.notice(this.sale);
		}
	}

	/**
	 * Creates new instances of classes in intergration
	 */
	public void startNewSale() {
		sale = new Sale();
		disc = new Discount();
		reg = new Register();
		acc = new Accounting();
		printer = new Printer();
		System.out.println("Message: \"New Sale started successfully\""); // throw controller exception?
	}

	/**
	 * Adds an item or increases quantity of item,
	 * to the instance of sale.
	 * 
	 * @param item_id
	 * @param quantity
	 * @return
	 */
	public SaleCurrentDTO addItems(int item_id, int quantity) throws ItemNotFoundException {
		// try {
		Item item = inv.findItemWithId(item_id); //
		System.out.println("-RESULT- \n\nMessage: \"The following item was found:\" "); // debug to see scanned item.

		if (sale.itemAlreadyExists(item)) {
			return sale.increaseQuantity(item, quantity);
		}
		return sale.addNewItem(item, quantity);

		// } catch (InvalidException e) {
		// System.out.println("Could not add missing item");
		// return null;
		// }
	}

	/**
	 * Checks if item exists in inventory (else findItemWithId throws
	 * ItemNotFoundException) and returns that id. If that found id == 666, then
	 * throws new DataBaseException, to simulate database failure.
	 * 
	 * @param item_id
	 * @return true or false
	 * @throws ItemException
	 * @throws DataBaseException
	 */

	public void eligibleItem_id(int item_id) throws ItemNotFoundException, DataBaseException {
		Item item;
		item = inv.findItemWithId(item_id);

		if (item.getItem_id() == 666) {
			throw new DataBaseException("Message: \"POS couldn't connect to the Database, please wait or reload.\"");
		}
	}

	/**
	 * Displays all added items and it's atttributes
	 */
	public void displayCurrentSale() {
		System.out.println("\n-Items & Price-");
		ArrayList<Item> result = sale.getAllItems();
		for (Item item : result)
			System.out.println(
					item.getItem_quantity() + "x" + " \"" + item.getItem_name() + "\" - (" + item.getItem_price()
							+ " SEK/" + item.getItem_VAT() + " SEK)");
	}

	/**
	 * Applies discount on totals.
	 * 
	 * @param discount
	 */
	public void applyDiscount(DiscountDTO discount) {
		double disc = discount.getDiscount();
		int new_VAT = (int) Math.round(disc * sale.getRunningVAT());
		int new_total = (int) Math.round(disc * sale.getRunningTotal());
		sale.setTotal(new_total);
		sale.setVAT(new_VAT);
	}

	/**
	 * gets Discount for customer, if discount is not eligible then it returns a
	 * default new Discount(00000000, 0.0) -> not eligible
	 * 
	 * @param customer_id
	 * @return
	 * @throws InvalidException
	 */
	public DiscountDTO eligibleForDiscount(int customer_id) throws InvalidException {
		DiscountDTO discount = disc.findDiscountWithId(customer_id);
		return discount;
	}

	/**
	 * Creates payment. Calculates and returns change.
	 * 
	 * @param amount (the payment from customer)
	 * @param cost   (the cost of the sale)
	 * @return the change from the payment
	 */
	public int payment(int amount, int cost) {
		PaymentDTO payment = new PaymentDTO(amount);
		SaleFinalDTO saleLog = this.sale.convertSaleToFinalDTO();

		int change = payment.getAmount() - cost;
		int old_balance = reg.getBalance(); //
		reg.addPayment(cost); // adds to register

		System.out
				.println("Message: \"Register has been updated\" - " + "(Old balance: " + old_balance + " SEK | New balance: "
						+ reg.getBalance() + " SEK)");

		endPaymentAndSale(payment, saleLog);
		return change;
	}

	/**
	 * Ends sale and completes payment.
	 * 
	 * @param payment
	 * @param sale
	 */
	public void endPaymentAndSale(PaymentDTO payment, SaleFinalDTO sale) {
		Receipt receipt = this.sale.endSale(payment, sale);

		this.inv.updateInventory(sale);
		this.acc.updateAccounting(sale);
		this.printer.printReceipt(receipt);
		this.notifyObservers();
		this.sale = null;

		System.out.println("Message: \"Thank you for shopping with us!\"\n");

	}

	/**
	 * Updates the running total cost of sale
	 */
	public void updateTotal() {
		sale.updateRunningTotal();
	}

	/**
	 * Getter for running total of sale
	 * 
	 * @return total price of sale
	 */
	public int getTotalPrice() {
		int totalPrice = sale.getRunningTotal();
		return totalPrice;
	}

	/**
	 * Getter for running VAT of sale
	 * 
	 * @return total VAT of sale
	 */
	public int getTotalVAT() {
		int totalVAT = sale.getRunningVAT();
		return totalVAT;
	}

	/**
	 * Getter for the whole cost of sale
	 * 
	 * @return the cost of sale including VAT
	 */
	public int getTotalWithVAT() {
		int total = sale.getRunningTotal() + sale.getRunningVAT();
		return total;
	}

}
