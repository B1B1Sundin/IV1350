package sem3.src.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import sem3.src.DTO.PaymentDTO;
import sem3.src.DTO.SaleFinalDTO;

public class Receipt {
	private int totalPrice;
	private int totalVAT;
	private ArrayList<Item> soldItems;
	private LocalDateTime timestamp;
	private int payment;

	/**
	 * Constructor creates Receipt with the specified params;
	 * 
	 * @param payment
	 * @param saleLog
	 */
	public Receipt(PaymentDTO payment, SaleFinalDTO saleLog) {
		this.payment = payment.getAmount();
		this.totalPrice = saleLog.getTotalPrice();
		this.soldItems = saleLog.getBoughtItems();
		this.timestamp = saleLog.getDateTime();
		this.totalVAT = saleLog.getTotalVAT();
	}

	/**
	 * GETTER METHODS FOR Receipt
	 */

	public int getTotalPrice() {
		return this.totalPrice;
	}

	public int getTotalVAT() {
		return this.totalVAT;
	}

	public int getPayment() {
		return this.payment;
	}

	public ArrayList<Item> getItems() {
		return this.soldItems;
	}

	public LocalDateTime getDateAndTime() {
		return this.timestamp;
	}

	/**
	 * @return A string representation of all fields in this object.
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Items:\n");
		string.append(soldItems + "\n");
		// string.append(" | item quantity: ");
		// string.append(quantity);
		string.append("Total: ");
		string.append(totalPrice);
		string.append(" SEK | VAT: ");
		string.append(totalVAT);
		string.append(" SEK | ");
		string.append(payment);
		string.append(" SEK ");
		return string.toString();
	}
}
