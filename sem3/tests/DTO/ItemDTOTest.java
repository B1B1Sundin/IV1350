package sem3.tests.DTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import sem3.src.DTO.ItemDTO;

public class ItemDTOTest {
	private ItemDTO item;
	private int item_id;
	private int total_VAT;
	private int total_price;
	private String item_name;

	@BeforeEach
	public void setUp() {
		item_id = 110;
		item_name = "tester";
		total_VAT = 1;
		total_price = 10;
		item = new ItemDTO(item_id, total_price, total_VAT, item_name);

	}

	@AfterEach
	public void tearDown() {
		item = null;
	}

	@Test
	public void testGetItem_id() {
		int expectedOutput = 110;
		assertEquals(expectedOutput, item.getItem_id());
	}

	@Test
	public void testGetItem_name() {
		String expectedOutput = "tester";
		assertEquals(expectedOutput, item.getItem_name());
	}

	@Test
	public void testGetTotal_price() {
		int expectedOutput = 10;
		assertEquals(expectedOutput, item.getItem_price());
	}

	@Test
	public void testGetTotal_VAT() {
		int expectedOutput = 1;
		assertEquals(expectedOutput, item.getItem_VAT());
	}

}
