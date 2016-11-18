package contract;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import item.BigItem;
import item.Item;


public class VipContractTest {
	private String[] fieldsB={"B","Glass bottle (19th century) Hand holding a colt",
			"18xx","21/12/2015","55.00","125.00","32","86","175","50"};
	
	private Item itemB;

	private VipContract vContract;

	@Before
	public void setUp() throws IndexOutOfBoundsException, ParseException{
		itemB = new BigItem(fieldsB);
		
		vContract = new VipContract();

	}
	/**
	 * Tests the freeAuction method, it returns true
	 * always if the contract has not expired
	 */
	@Test
	public void freeAuctionTest() {
		assertTrue(vContract.freeAuction());
		vContract.endDate=LocalDate.now().minusDays(1);
		assertFalse(vContract.freeAuction());
	}
	/**
	 * Tests the applyDiscount method 
	 */
	@Test
	public void applyDeliveryDiscount(){
		assertEquals(vContract.applyDeliveryDiscount(itemB),
				0.5* itemB.calculateDelivery(),0.005);
	}

}
