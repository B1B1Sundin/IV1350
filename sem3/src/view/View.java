package sem3.src.view;

import java.util.Scanner;

import sem3.src.DTO.DiscountDTO;
import sem3.src.DTO.SaleCurrentDTO;
import sem3.src.controller.Controller;

public class View {
	private Controller contr;
	private String lineBreaker = "-------------------------------------------------------------------------------------------------------";

	/**
	 * Constructor for View using params;
	 * 
	 * @param contr
	 */
	public View(Controller contr) {
		this.contr = contr;
	}

	/**
	 * Runs a whole fake sale.
	 * "System.in" is a standard input stream.
	 * 
	 * 1. Start Sale and add items to Sale (while(flag1){})
	 * - Customer gives items to cashier. Cashier scans and enters the the barcode
	 * - for item's id and it's quantity.
	 * 
	 * 2. Request discount to sale. (while(flag2){})
	 * - Customer requests discount and provides an id. Cashier enters Discount
	 * - DataBase to check if customer is eligible for discount.
	 * 
	 * 3. End sale and complete Payment.
	 * - Customer provides payment. Cashier takes payement and returns change.
	 * - Updates external systems and provides Customer with Receipt.
	 * 
	 */
	public void runFakeSale() {
		Scanner sc = new Scanner(System.in);

		String input;
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;

		contr.startNewSale();
		System.out.println("Message: \"New sale was started\" \n" + lineBreaker + "\n-INPUT-");

		while (flag1) {
			System.out.print("\nEnter barcode: ");
			int item_id = sc.nextInt();

			System.out.print("Enter quantity of item: ");
			int item_quantity = sc.nextInt();

			if (contr.eligibleItem_id(item_id) && item_quantity > 0) {
				SaleCurrentDTO item_info = contr.addItems(item_id, item_quantity); // add item with id "101" and quantity of 1

				contr.updateTotal();

				System.out.println(
						"\nItem: \"" + item_info.getCurrentItem_name() + "\" Total quantity: "
								+ item_info.getCurrentItem_quantity());
				System.out
						.println("\n" + lineBreaker);
				System.out
						.print(
								"Running Total: " + item_info.getRunningTotal() + " SEK\nRunning VAT: " + item_info.getRunningVAT()
										+ " SEK");
				System.out.print("\n" + lineBreaker);
				System.out.print("\nContinue scanning items? (yes/no) ");
				input = sc.next().toLowerCase();

				if (!(input.equals("yes"))) {
					flag1 = false;
				}
				System.out.print(lineBreaker);

			} else {
				System.out.println(lineBreaker);
				System.out.println("Message: \"Invalid input. Please enter a new barcode or quantity\"");
				System.out.print(lineBreaker);
			}
		}

		contr.displayCurrentSale();
		contr.updateTotal();

		System.out.print("\n-Totals-");
		System.out.println("\nTotal price : " + contr.getTotalPrice() + " SEK");
		System.out.println("Total VAT: " + contr.getTotalVAT() + " SEK");
		System.out.println("Total with VAT: " + contr.getTotalWithVAT() + " SEK");
		System.out.println(lineBreaker);

		System.out.print("Apply discount: (yes/no) ");
		String discountRequest = sc.next().toLowerCase();
		System.out.println(lineBreaker);

		if (discountRequest.equals("yes")) {
			while (flag2) {
				System.out.print("Enter ID for discount: ");
				int customer_id = sc.nextInt();
				DiscountDTO discount = contr.eligibleForDiscount(customer_id);
				System.out.println(discount);
				if (discount.getDiscount() != 0) {
					contr.applyDiscount(discount);
					flag2 = false;
				} else {
					System.out.println("Message: \"The entered ID has no eligble discounts\"");
					flag2 = false;
				}
			}
		}

		System.out.println(lineBreaker);

		while (flag3) {
			System.out.print("Enter payment: ");
			int payment = sc.nextInt();

			if (!(payment < contr.getTotalWithVAT())) {
				System.out.println(lineBreaker);
				contr.payment(payment, contr.getTotalWithVAT());
				flag3 = false;
			} else {
				System.out.println(
						"\nMessage: Invalid input. Your payment of " + payment
								+ " SEK was insufficient. Please enter a new sum.");
			}
		}
		sc.close();
	}

}
