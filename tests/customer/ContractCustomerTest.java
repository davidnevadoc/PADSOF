package customer;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import contract.Contract;
import contract.StandardContract;
import exceptions.InvalidArgumentsException;
import item.Item;
import item.SmallItem;

public class ContractCustomerTest {
	ContractCustomer customer1;
	Contract contract1;
	Customer customer2;
	Item sItem;
	@Before
	public void setUp() throws InvalidArgumentsException, ParseException{
		sItem = new SmallItem("Glass bottle (19th century) Hand holding a colt",
				"18xx","21/12/2015",55.00,125.00,5);
		contract1 = new StandardContract();
		customer1 = new ContractCustomer("Jack", "Smith", "12345678A",
				"example@address.com", contract1);
		customer2 = new Customer("Jack", "Smith", "12345678A",
				"example@address.com");
	}
	/**
	 * Tests applyDiscount method in a standard scenario
	 */
	@Test
	public void applyDiscountTest() {
		assertEquals(customer1.applyDiscount(sItem),
				contract1.applyDiscount(sItem), 0.005);
	}
	/**
	 * Tests the ContractCustomer constructor when it acts as
	 * a method for adding a contract to an existing customer
	 * @throws InvalidArgumentsException
	 */
	@Test
	public void ContractCustomerTest() throws InvalidArgumentsException{
		ContractCustomer customer3 = new ContractCustomer(customer2, contract1);
		assertTrue(customer1.equals(customer3));
	}
}
