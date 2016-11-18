package sales;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;



import customer.*;
import exceptions.InvalidArgumentsException;
import item.*;

import java.util.*;
import java.text.ParseException;

public class SaleTest {
	Sale sale1;
	Item item1;
	Item item2;
	Item item3;
	Item item3copy;

	@Before
	public void setUp() throws ParseException, InvalidArgumentsException {
		Customer customer = new Customer("Jack", "Smith", "12345678A",
				"example@address.com");
		sale1 = new Sale(customer);
		item1 = new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
		item2 = new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,86,175,50);
		item3 = new SmallItem("Ancient coin", "10xx", "22/11/2015", 45.45, 89.99, 2);
		item3copy = new SmallItem("Ancient coin", "10xx", "22/11/2015", 45.45, 89.99, 2);
	}
	/**
	 * Tests the addition of a single item to the sale
	 * ant the calculation of the total price
	 * @throws InvalidAdditionException it wont be thrown
	 */
	@Test
	public void addItemTest1() throws InvalidAdditionException {
		sale1.addItem(item1);
		ArrayList<Item> itemsAdded = new ArrayList<Item>(1) ;
		itemsAdded.add(item1);
		assertEquals(sale1.getItems(), itemsAdded);
		assertEquals(sale1.getSalePrice(), 125.00, 0.005);
		assertEquals(item1.getItemState(),ItemState.Sold);
	}
	/**
	 * Tests the addition of multiple items to the sale
	 * and the calculation of the total price
	 * @throws InvalidAdditionException it wont be thrown
	 */
	@Test
	public void addItemTest2() throws InvalidAdditionException{
		sale1.addItem(item1);
		sale1.addItem(item2);
		sale1.addItem(item3);
		ArrayList<Item> itemsAdded = new ArrayList<Item>(3);
		itemsAdded.add(item1);
		itemsAdded.add(item2);
		itemsAdded.add(item3);
		assertEquals(sale1.getItems(), itemsAdded);
		assertEquals(sale1.getSalePrice(), 339.99, 0.005);
		assertEquals(item1.getItemState(),ItemState.Sold);
		assertEquals(item2.getItemState(),ItemState.Sold);
		assertEquals(item3.getItemState(),ItemState.Sold);

	}
	/**
	 * Tests the exception thrown when trying to add 
	 * the same item twice a sale
	 * @throws InvalidAdditionException when the same item 
	 * is added twice
	 */
	@Test(expected = InvalidAdditionException.class)
	
	public void addItemTest3() throws InvalidAdditionException{
		sale1.addItem(item3);
		sale1.addItem(item3);
	}
	
	/**
	 * Tests the addition of copies items, this is allowed
	 * because they are identical but not the same object
	 * @throws InvalidAdditionException Will not be thrown
	 */
	@Test
	
	public void addItemTest4() throws InvalidAdditionException{
		sale1.addItem(item3);
		sale1.addItem(item3copy);
		ArrayList<Item> itemsAdded = new ArrayList<Item>(2);
		itemsAdded.add(item3);
		itemsAdded.add(item3copy);
		assertEquals(sale1.getItems(), itemsAdded);
		
	}
	/**
	 * Test that the sell has been canceled and the items 
	 * returned to their original state
	 * @throws InvalidAdditionException Will not be thrown
	 */
	@Test
	public void cancelTest() throws InvalidAdditionException{
		sale1.addItem(item1);
		sale1.addItem(item2);
		sale1.addItem(item3);
		sale1.cancel();
		assertEquals(item1.getItemState(),ItemState.Available);
		assertEquals(item2.getItemState(),ItemState.Available);
		assertEquals(item3.getItemState(),ItemState.Available);
	}
	
	
}


