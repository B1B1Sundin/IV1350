package sem3.tests.intergration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.time.LocalDateTime;
import java.util.ArrayList;

import sem3.src.DTO.SaleFinalDTO;
import sem3.src.intergration.Accounting;
import sem3.src.intergration.Inventory;
import sem3.src.model.Item;

public class AccountingTest {
	private Accounting acc;
	private Inventory inv;
	private LocalDateTime dateAndTime;
	ArrayList<Item> boughtItems;

	private int totalPrice;
	private int totalVAT;

	@BeforeEach
	public void startUp() {
		acc = new Accounting();
		inv = new Inventory();
		Item item = new Item(110, 20, 2, "testItem");
		ArrayList<Item> boughtItems = new ArrayList<>();
		boughtItems.add(item);

		dateAndTime = LocalDateTime.now();
		totalPrice = 20;
		totalVAT = 2;
	}

	@AfterEach
	public void tearDown() {
		acc = null;
	}

	@Test
	public void updateAccountingTest() {
		SaleFinalDTO saleInfo = new SaleFinalDTO(dateAndTime, boughtItems, totalPrice, totalVAT);
		acc.updateAccounting(saleInfo);
		int expectedOutput = 10000 + 22; // inital balance of 10 000 SEK
		assertEquals(expectedOutput, acc.getAccountingBalance());
	}
}
