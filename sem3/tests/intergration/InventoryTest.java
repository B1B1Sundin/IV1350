package sem3.tests.intergration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import sem3.src.intergration.Inventory;
import sem3.src.model.Item;
import sem3.src.model.Sale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class InventoryTest {
	private Sale sale;
	private Inventory inv;

	@BeforeEach
	public void setUp() {
		sale = new Sale();
		inv = new Inventory();
	}

	@AfterEach
	public void tearDown() {
		sale = null;
		inv = null;
	}

	@Test
	public void testFindItemWithID() {
		Item expected = new Item(101, 16, 2, "Ballerina Kladdkaka 210g Göteborgs");
		Item actual = inv.findItemWithId(101);
		assertEquals(expected, actual);
	}
}
