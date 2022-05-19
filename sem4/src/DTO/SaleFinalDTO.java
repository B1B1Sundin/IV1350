package sem4.src.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

import sem4.src.model.Item;

public class SaleFinalDTO {
	private LocalDateTime dateAndTime;
	private ArrayList<Item> boughtItems;
	private int totalPrice;
	private int totalVAT;

	/**
	 * SaleCurrentDTO values is transferred to SaleFinalDTO when sale is ended.
	 * 
	 * @param dateAndTime
	 * @param boughtItems
	 * @param itemQuantity
	 * @param totalPrice
	 * @param totalVAT
	 */
	public SaleFinalDTO(LocalDateTime dateAndTime, ArrayList<Item> boughtItems, int totalPrice, int totalVAT) {
		this.dateAndTime = dateAndTime;
		this.boughtItems = boughtItems;
		this.totalPrice = totalPrice;
		this.totalVAT = totalVAT;
	}

	/**
	 * GETTER METHODS FOR SaleFinalDTO
	 */

	public LocalDateTime getDateTime() {
		return this.dateAndTime;
	}

	public ArrayList<Item> getBoughtItems() {
		return this.boughtItems;
	}

	public int getTotalPrice() {
		return this.totalPrice;
	}

	public int getTotalVAT() {
		return this.totalVAT;
	}

	/**
	 * @return A string representation of all fields in this object.
	 *         dateAndTime is the time stamp of when sale ended
	 *         boughtItems is an arraylist of all items and it's attributes
	 *         totalPrice is the total price of the sale
	 *         totalVAT is the total VAT of the sale
	 */
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("");
		string.append("\n-Receipt- ");
		string.append("\nDate & Time: ");
		string.append(dateAndTime);
		string.append("\nItems:\n ");
		string.append(boughtItems);
		string.append("\nTotal:\n ");
		string.append(totalPrice);
		string.append("SEK\nTotal VAT: ");
		string.append(totalVAT);
		string.append(" SEK");
		string.append("");
		return string.toString();
	}
}
