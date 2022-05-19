package sem4.tests.exceptions;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import sem4.src.intergration.Discount;
import sem4.src.exceptions.InvalidException;

public class InvalidExceptionTest {

	private Discount disc;

	@BeforeEach
	public void setUp() {
		disc = new Discount();
	}

	@AfterEach
	public void tearDown() {
		disc = null;
	}

	@Test
	public void testFindItemWithID() throws InvalidException {
		boolean thrown = false;
		try {
			disc.findDiscountWithId(00000000);
			// contr.eligibleForDiscount(00000000); // invalid input,
		} catch (InvalidException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

}
