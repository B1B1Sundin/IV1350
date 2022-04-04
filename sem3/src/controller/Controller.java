package sem3.src.controller;

import java.util.ArrayList;

import sem3.src.DTO.DiscountDTO;
import sem3.src.DTO.PaymentDTO;
import sem3.src.DTO.SaleCurrentDTO;
import sem3.src.DTO.SaleFinalDTO;
import sem3.src.intergration.Accounting;
import sem3.src.intergration.Discount;
import sem3.src.intergration.Inventory;
import sem3.src.intergration.Printer;
import sem3.src.intergration.Register;
import sem3.src.model.Item;
import sem3.src.model.Receipt;
import sem3.src.model.Sale;

public class Controller {
	private Sale sale;
	private Register reg;
	private Inventory inv;
	private Accounting acc;
	private Printer printer;
	private Discount disc;

	/**
	 * Creates a new constructer of Controller
	 * 
	 * @param inv
	 */
	public Controller(Inventory inv) {
		this.inv = inv; // Display inventory
		System.out.println("\nMessage: \"Controller was started successfully\""); // throw controller exception?
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
	public SaleCurrentDTO addItems(int item_id, int quantity) {
		Item item = inv.findItemWithId(item_id); // go directly to inventory
		System.out.println("-RESULT-\nMessage: The following item was found: "); // debug to see scanned item.

		if (sale.itemAlreadyExists(item)) {
			return sale.increaseQuantity(item, quantity);
		}
		return sale.addNewItem(item, quantity);
	}

	/**
	 * Checks if item exists in inventory.
	 * 
	 * @param item_id
	 * @return true or false
	 */

	public boolean eligibleItem_id(int item_id) {
		Item item = inv.findItemWithId(item_id);
		if (item.getItem_id() == 000) {
			return false;
		}
		return true;
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
	 */
	public DiscountDTO eligibleForDiscount(int customer_id) {
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
		reg.addPayment(payment.getAmount()); // adds payment to register

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
		this.sale = null;

		System.out.println("Thank you for shopping with us!\n");

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
