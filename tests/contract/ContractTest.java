package contract;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import item.*;

public class ContractTest {
	
	private String[] fieldsB={"B","Glass bottle (19th century) Hand holding a colt",
			"18xx","21/12/2015","55.00","125.00","32","86","175","50"};
	
	private Item itemB;
	private Item sItem;
	private VipContract vContract;
	private StandardContract sContract;
	@Before
	public void setUp() throws IndexOutOfBoundsException, ParseException{
		itemB = new BigItem(fieldsB);
		sItem = new SmallItem("Glass bottle (19th century) Hand holding a colt",
				"18xx","21/12/2015",55.00,125.00,5);
		vContract = new VipContract();
		sContract = new StandardContract();
	}
	/**
	 * Tests the contract constructor
	 */
	@Test
	public void contractTest(){
		assertEquals(vContract.getEndDate(), LocalDate.now().plusYears(1));
	}
	/**
	 * Tests if the discount is applied correctly to the 
	 * delivery costs
	 */
	@Test
	public void applyDeliveryDiscountTest1() {
		assertEquals(sContract.applyDeliveryDiscount(itemB),itemB.calculateDelivery(), 0.005);
	}
	/**
	 * Tests the hasExpired method
	 */
	@Test
	public void hasExpiredTest(){
		assertFalse(sContract.hasExpired());
		sContract.endDate=LocalDate.now().minusDays(1);
		assertTrue(sContract.hasExpired());
	}
	/**
	 * Tests the applyDiscount method
	 */
	@Test
	public void applyDiscountTest(){
		assertEquals(vContract.applyDiscount(sItem),
				(1-sItem.getPercentageDiscount())* sItem.getTargetPrice(),0.005);
	}
}
