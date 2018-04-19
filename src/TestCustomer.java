import static org.junit.Assert.*;

import org.junit.Test;

public class TestCustomer {
	String name = "Test name";
	String phoneNum = "1234567890";
	
	Customer customer = new Customer(name, phoneNum);

	@Test
	public void testCreateCustomerAndGetMethods() {
		assertNotNull(customer);
		assertEquals(name, customer.getName());
		assertEquals(phoneNum, customer.getPhone());
		assertEquals(0.0, customer.getAccountBalance(), 0.00001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionBadPhone() {
		String invalidPhoneNum = "1234567";
		
		new Customer(name, invalidPhoneNum);
	}
	
	@Test
	public void testChargeAccount() {
		assertEquals(0.0, customer.getAccountBalance(), 0.00001);
		customer.chargeAccountBalance(50.0);
		assertEquals(50.0, customer.getAccountBalance(), 0.00001);
	}
	
	@Test
	public void testCreditAccount() {
		testChargeAccount();
		customer.creditAccountBalance(50.0);
		assertEquals(0.0, customer.getAccountBalance(), 0.00001);
	}
	
	@Test
	public void testSetters() {
		customer.setName("Bill Bob");
		customer.setPhoneNum("1112223333");
		assertEquals("Bill Bob", customer.getName());
		assertEquals("1112223333", customer.getPhone());
	}
}

