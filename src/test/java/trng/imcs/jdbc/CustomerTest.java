package trng.imcs.jdbc;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void testConstructor() {
		//given
		Integer id = 101;
		String name = "Raj";
		LocalDate dob = LocalDate.now();
		Float anualSalary = 2000f;
		
		//when
		Customer customer = new Customer(id, name, dob, anualSalary, "ABC Street");
		
		//assert
		assertNotNull(customer.getDob());
		assertNotNull(customer.getId());
		assertNotNull(customer.getName());
		assertNotNull(customer.getAnualSalary());
		assertNotNull(customer.toString());
		
		assertEquals(id, customer.getId());
		assertEquals(dob, customer.getDob());
		assertEquals(anualSalary, customer.getAnualSalary());
		assertEquals(name, customer.getName());
	}
}
