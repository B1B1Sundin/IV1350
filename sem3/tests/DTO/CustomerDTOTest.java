package sem3.tests.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import sem3.src.DTO.CustomerDTO;

public class CustomerDTOTest {
	private int customer_id;
	private CustomerDTO customer;

	@BeforeEach
	public void setUp() {
		customer_id = 20000101;
		customer = new CustomerDTO(customer_id);
	}

	@AfterEach
	public void tearDown() {
		customer = null;
		customer_id = 0;
	}

	@Test
	public void testGetCustomerDTO() {
		int expectedOutput = customer_id;
		assertEquals(expectedOutput, customer.getCustomer_id(), "getCustomer_id failed!");
	}

	@Test
	public void testSetCustomerDTO() {
		int new_customer_id = 19990101;
		customer.setCustomer_id(new_customer_id);
		assertEquals(new_customer_id, customer.getCustomer_id());
	}

}
