package customer;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import exceptions.InvalidArgumentsException;

import org.junit.Before;

import item.*;
public class CustomerTest {
	Customer customer1;
	Customer customer2;
	Customer customer3;
	Customer customer4;
	Item item1;
	@Before
	public void setUp() throws InvalidArgumentsException, ParseException{
		customer2 = new Customer("John", "Silver", "11111678A",
				"example2@address.com");
		customer3 = new Customer("Johnny", "Silvery", "11111678A",
				"example2@address.com", false);
		customer4 = new Customer("Johnny", "Silvery", "11331678A",
				"example2@address.com", false);
		item1 = new SmallItem("Glass bottle (19th century) Hand holding a colt",
				"18xx","21/12/2015",55.00,125.00,5);
	}
	/**
	 * Customer constructor test
	 * Successful scenario specifying mandatory fields and
	 * notifications field.
	 * @throws InvalidArgumentsException Will not be thrown
	 */
	@Test
	public void customerTest1() throws InvalidArgumentsException {
		customer1 = new Customer("Jack", "Smith", "12345678A",
				"example@address.com", false);
		assertEquals("Jack", customer1.firstName);
		assertEquals("Smith", customer1.lastName);
		assertEquals("12345678A", customer1.dni);
		assertEquals("undefined", customer1.phoneNumber);
		assertFalse(customer1.notification);
	}
	/**
	 * Customer constructor test
	 * Failure scenario, incorrect dni
	 * @throws InvalidArgumentsException when dni, or email fields
	 * are incorrect
	 */
	@Test (expected = InvalidArgumentsException.class)
	public void customerTest2() throws InvalidArgumentsException{
		customer1= new Customer("Jack", "Smith", "12345A",
				"example@address.com");
	}
	
	/**
	 * Customer constructor test
	 * Failure scenario, incorrect email address
	 * @throws InvalidArgumentsException when dni, or email fields
	 * are incorrect
	 */
	@Test (expected = InvalidArgumentsException.class)
	public void customerTest3() throws InvalidArgumentsException{
		customer1= new Customer("Jack", "Smith", "12345678A",
				"exampleaddress.com");
	}
	
	/**
	 * Tests the changeMail method in a success scenario,
	 * this is, when a valid email address is  received
	 * @throws InvalidArgumentsException Will not be thrown
	 */
	@Test
	public void changeMailTest1() throws InvalidArgumentsException{
		customer1 = new Customer("Jack", "Smith", "12345678A",
				"example@address.com", false);
		customer1.changeMail("newexample@address.com");
		assertEquals(customer1.email, "newexample@address.com");
	}
	/**
	 * Tests the changeMail method in a failure scenario,
	 * this is, when an invalid email address is received
	 * @throws InvalidArgumentsException when the email address
	 * received is not valid
	 */
	@Test (expected =  InvalidArgumentsException.class)
	public void changeMailTest2() throws InvalidArgumentsException{
		customer1 = new Customer("Jack", "Smith", "12345678A",
				"example@address.com", false);
		customer1.changeMail("newexampleaddress.com");

	}
	
	/**
	 * Tests the applyDiscount method
	 */
	@Test
	public void applyDiscountTest(){
		assertEquals(customer2.applyDiscount(item1), item1.getTargetPrice(),
				0.005);
	}
	/**
	 * Tests the onMailingList method
	 */
	@Test
	public void onMailingListTest(){
		assertTrue(customer2.onMailingList());
		assertFalse(customer3.onMailingList());
	}
	/**
	 * Test the equals method in all possible scenarios
	 */
	@Test
	public void equalsTest(){
		assertTrue(customer2.equals(customer2));
		assertTrue(customer2.equals(customer3));
		assertTrue(customer3.equals(customer2));
		assertFalse(customer4.equals(customer2));
	}

}
