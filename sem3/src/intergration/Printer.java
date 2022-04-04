package sem3.src.intergration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sem3.src.model.Item;
import sem3.src.model.Receipt;

/**
 * Class for external Printer
 */
public class Printer {
	private int totalPrice;
	private int totalVAT;
	private int payment;
	private int total;
	private int change;
	private ArrayList<Item> soldItems;
	private LocalDateTime timestamp;

	public Printer() {
	}

	/**
	 * Prints receipt using Receipt information
	 * 
	 * @param receipt
	 */
	public void printReceipt(Receipt receipt) {
		this.totalPrice = receipt.getTotalPrice();
		this.totalVAT = receipt.getTotalVAT();
		this.payment = receipt.getPayment();
		this.soldItems = receipt.getItems();
		this.timestamp = receipt.getDateAndTime();
		total = this.totalPrice + this.totalVAT;
		change = this.payment - total;

		String str = "----------------------------------------------------------------------------------------------------------------------------";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formatDateTime = timestamp.format(formatter);

		System.out
				.println(str);
		System.out.println("RECEIPT");
		System.out.print("Sale ended  ");
		System.out.println(formatDateTime);
		System.out
				.println(str);
		System.out.println(soldItems);
		System.out
				.println(str);
		System.out.println("Total: " + totalPrice + " SEK | VAT: " + totalVAT + " SEK");
		System.out.println("Total with VAT: " + total + " SEK");
		System.out.println("Payment: " + payment + " SEK | Change: " + change + " SEK");
		System.out
				.println(str);

	}
}
