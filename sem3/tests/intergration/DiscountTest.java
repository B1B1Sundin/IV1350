package sem3.tests.intergration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import sem3.src.DTO.CustomerDTO;
import sem3.src.DTO.DiscountDTO;
import sem3.src.intergration.Discount;

public class DiscountTest {
	private Discount disc;
	private ArrayList<DiscountDTO> discount_list;

	@BeforeEach
	public void startUp() {
		disc = new Discount();
		discount_list = new ArrayList<>();

		discount_list.add(new DiscountDTO(new CustomerDTO(19570331), 0.95));
		discount_list.add(new DiscountDTO(new CustomerDTO(20010103), 0.90));
		discount_list.add(new DiscountDTO(new CustomerDTO(19690420), 0.69));

	}

	@AfterEach
	public void tearDown() {
		disc = null;
		discount_list = null;
	}

	@Test
	public void testFindDiscountWithID() {
		DiscountDTO expectedDiscountDTO = new DiscountDTO(new CustomerDTO(20010103), 0.90);
		DiscountDTO actualDiscountDTO = disc.findDiscountWithId(20010103);
		assertEquals(expectedDiscountDTO.toString(), actualDiscountDTO.toString());
	}
}
