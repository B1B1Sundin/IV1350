package sem4.tests.intergration;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import sem4.src.intergration.DataBaseException;
import sem4.src.intergration.Inventory;
import sem4.src.model.ItemNotFoundException;
import sem4.src.controller.Controller;

public class DataBaseExceptionTest {
	private Inventory inv;
	private Controller contr;

	@BeforeEach
	public void setUp() {
		inv = new Inventory();
		contr = new Controller(inv);
	}

	@AfterEach
	public void tearDown() {
		inv = null;
		contr = null;
	}

	@Test
	public void testEligibleItem_id() throws DataBaseException, ItemNotFoundException {
		boolean thrown = false;
		try {
			contr.eligibleItem_id(666);
		} catch (DataBaseException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
}
