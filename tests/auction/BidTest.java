package auction;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import customer.*;
import exceptions.InvalidArgumentsException;
import contract.*;


/**
 * Bid class tester
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 *
 */
public class BidTest {
	
	Bid bid1;
	Bid bid2;
	@Before
	public void setUp() throws InvalidArgumentsException{
		Contract contract1 = new StandardContract();
		Contract contract2 = new VipContract();
		ContractCustomer customer1 = new ContractCustomer("Jack", "Smith", "12345678A",
				"example@address.com", contract1);
		ContractCustomer customer2 = new ContractCustomer("Denna", "Wind", "98765432B",
				"other@address.com", contract2);
		bid1= new Bid(customer1, 100.50);
		bid2 = new Bid(customer2, 101.50);
		
	}
	/**
	 * Checks that the method  isGreater  works when 
	 * a negative increment is given
	 */
	@Test
	public void testIsGreater1() {
		
		assertFalse(bid1.isGreater(bid2, -10.00));
	}
	/**
	 * Checks that the method isGreater works when the
	 * first bid is equal to the second bid plus the increment
	 */
	@Test
	public void testIsGreater2(){
		assertTrue(bid2.isGreater(bid1, 1.00));
	}
	
}
