package sem3.tests.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import sem3.src.DTO.CustomerDTO;
import sem3.src.DTO.DiscountDTO;
import sem3.src.DTO.ItemDTO;
import sem3.src.DTO.PaymentDTO;
import sem3.src.DTO.SaleCurrentDTO;
import sem3.src.DTO.SaleFinalDTO;
import sem3.src.controller.Controller;
import sem3.src.intergration.Accounting;
import sem3.src.intergration.Discount;
import sem3.src.intergration.Inventory;
import sem3.src.intergration.Printer;
import sem3.src.intergration.Register;
import sem3.src.model.Item;
import sem3.src.model.Sale;

public class TestController {
	private ByteArrayOutputStream printOutBuffer; // see course book
	private PrintStream originalSysOut;

	private Controller contr;
	private Sale sale;
	private Register reg;
	private Inventory inv;
	private Accounting acc;
	private Printer printer;
	private Discount disc;
	private int runningTotal = 0;
	private int runningVAT = 0;

	@BeforeEach
	public void setUp() {
		printOutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printOutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
		inv = new Inventory();
		contr = new Controller(inv);
		sale = new Sale();
		contr.startNewSale();

	}

	@AfterEach
	public void tearDown() {
		printOutBuffer = null;
		originalSysOut = null;
		inv = null;
		contr = null;
		reg = null;
		sale = null;
		acc = null;
		printer = null;
		disc = null;
	}

	@Test
	public void testStartNewSale() {
		String printOut = this.printOutBuffer.toString();
		String expectedOutput = "started";
		assertTrue("The controller failed to start properly", printOut.contains(expectedOutput));
	}

	@Test
	public void testAddItems() {
		contr.startNewSale();
		Item item = new Item(new Item(101, 16, 2, "Ballerina Kladdkaka 210g Göteborgs"), 1);
		sale.addNewItem(item, 1);
		SaleCurrentDTO expected = new SaleCurrentDTO(item, item.getItem_price(), item.getItem_VAT());
		String expected_string = expected.toString();

		int item_id = 101;
		SaleCurrentDTO actual = contr.addItems(item_id, 1);
		String actual_string = actual.toString();

		assertEquals(expected_string, actual_string);
	}

	@Test
	public void testEligibleItem_id() {
		// Item item = new Item(new ItemDTO(101, 16, 2, "Ballerina Kladdkaka 210g
		// Göteborgs"));
		int item_id = 101;
		boolean actual = contr.eligibleItem_id(item_id);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void testDisplayCurrentSale() {
		contr.startNewSale();
		String printOut = this.printOutBuffer.toString();
		Item item = new Item(new ItemDTO(101, 16, 2, "Ballerina Kladdkaka 210g Göteborgs"));
		sale.addNewItem(item, 1);
		contr.displayCurrentSale();
		assertTrue("The screen failed to display current sale", printOut.contains("Ballerina Kladdkaka 210g Göteborgs"));
	}

	@Test
	public void testEligibleForDiscount() {
		contr.startNewSale();
		DiscountDTO expected = new DiscountDTO(new CustomerDTO(20010103), 0.90);
		DiscountDTO actual = contr.eligibleForDiscount(20010103);

		String expected_string = expected.toString();
		String actual_string = actual.toString();
		assertEquals(expected_string, actual_string);
	}

	@Test
	public void testApplyDiscount() {
		DiscountDTO discount = new DiscountDTO(new CustomerDTO(20010103), 0.50);
		contr.startNewSale();
		contr.addItems(101, 1);
		contr.applyDiscount(discount);

		int actual_total = contr.getTotalPrice();
		int actual_VAT = contr.getTotalVAT();

		int expected_total = 8;
		int expected_VAT = 1;

		assertEquals(expected_total, actual_total);
		assertEquals(expected_VAT, actual_VAT);
	}

	@Test
	public void testpayment() {
		contr.startNewSale();
		int payment = 100;
		int cost = 80;
		int expected = payment - cost;

		int actual = contr.payment(payment, cost);

		assertEquals(expected, actual);
	}

	@Test
	public void testEndPaymentAndSal() {
		contr.startNewSale();

		PaymentDTO payment = new PaymentDTO(100);
		SaleFinalDTO saleFinal = sale.convertSaleToFinalDTO();
		contr.endPaymentAndSale(payment, saleFinal);

		String printOut = this.printOutBuffer.toString(); // must be placed after all function calls.
		String expectedOutput = "Thank you for shopping with us!";

		assertTrue("POS failed to end current sale", printOut.contains(expectedOutput));

	}
}
