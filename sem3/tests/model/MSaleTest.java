package sem3.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import sem3.src.DTO.ItemDTO;
import sem3.src.DTO.PaymentDTO;
import sem3.src.DTO.SaleCurrentDTO;
import sem3.src.DTO.SaleFinalDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import sem3.src.model.Item;
import sem3.src.model.Receipt;
import sem3.src.model.Sale;

public class MSaleTest {
	private ArrayList<Item> requestedItems; // list of items that customer wants

	private int runningTotal = 0;
	private int runningVAT = 0;
	private Sale sale;
	private LocalDateTime dateAndTime;

	@BeforeEach
	public void startUp() {
		sale = new Sale();
		requestedItems = new ArrayList<>();
	}

	@AfterEach
	public void tearDown() {
		sale = null;
		requestedItems = null;
	}

	@Test
	public void testAddNewItem() {
		Item item = new Item(new ItemDTO(110, 10, 1, "Kiwi"));
		sale.addNewItem(item, 1); // adds item to requestedItems in sale
		ArrayList<Item> expected = sale.getAllItems(); // get all "items" in bag of sale.
		Item actual = item;
		assertEquals(actual, expected.get(0));
	}

	@Test
	public void testIncreaseQuantity() {
		Item item = new Item(new ItemDTO(111, 10, 1, "Kiwi")); // used for testing increaseQuantity()
		Item item2 = new Item(new Item(111, 10, 1, "Kiwi"), 2); // result

		sale.addNewItem(item, 1); // add item to sale
		SaleCurrentDTO expected = sale.increaseQuantity(item, 1); // increase quantity

		SaleCurrentDTO actual = new SaleCurrentDTO(item2, runningTotal, runningVAT);
		assertEquals(expected.getCurrentItem_quantity(), actual.getCurrentItem_quantity());
	}

	@Test
	public void testItemAlreadyExists() {
		Item item = new Item(new ItemDTO(111, 10, 1, "Kiwi"));
		sale.addNewItem(item, 1);
		boolean expected = sale.itemAlreadyExists(item);
		boolean itemExists = true;
		assertEquals(expected, itemExists);
	}

	@Test
	public void testGetAllItems() {
		Item item = new Item(new ItemDTO(111, 10, 1, "Kiwi"));

		ArrayList<Item> expected = new ArrayList<>();
		expected.add(item);

		sale.addNewItem(item, 1);
		ArrayList<Item> actual = sale.getAllItems();
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertSaleToFinalDTO() {
		Item item = new Item(new ItemDTO(111, 10, 1, "Kiwi"));
		sale.addNewItem(item, 1);
		dateAndTime = LocalDateTime.now();
		SaleFinalDTO actual = sale.convertSaleToFinalDTO();
		SaleFinalDTO expected = new SaleFinalDTO(dateAndTime, sale.getAllItems(), 10, 1);

		String[] actualString = actual.toString().split("\n");
		String[] expectedString = expected.toString().split("\n");

		for (int i = 0; i < actualString.length; i++) {
			if (!actualString[i].startsWith("Date & Time: "))
				assertEquals(expectedString[i], actualString[i]);
		}
	}

	@Test
	public void testUpdateRunningTotal() {
		int expected_price = 10;
		int expected_VAT = 1;

		Item item = new Item(new ItemDTO(111, 10, 1, "Kiwi"));
		sale.addNewItem(item, 1);
		sale.updateRunningTotal();

		int actual_price = sale.getRunningTotal();
		int actual_VAT = sale.getRunningVAT();
		assertEquals(expected_price, actual_price);
		assertEquals(expected_VAT, actual_VAT);
	}

	@Test
	public void testEndSale() {
		PaymentDTO payment = new PaymentDTO(100);
		Item item = new Item(new ItemDTO(111, 10, 1, "Kiwi"));
		sale.addNewItem(item, 1);

		SaleFinalDTO final_sale = new SaleFinalDTO(dateAndTime, sale.getAllItems(), 10, 1);

		Receipt expected = new Receipt(payment, final_sale);
		Receipt actual = sale.endSale(payment, final_sale);

		String expected_string = expected.toString();
		String actual_string = actual.toString();

		assertEquals(expected_string, actual_string);
	}

}
