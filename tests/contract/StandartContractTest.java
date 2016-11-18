package contract;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidArgumentsException;
import item.Item;
import item.SmallItem;

public class StandartContractTest {
	private Item sItem;
	private StandardContract sContract;
	@Before
	public void setUp() throws IndexOutOfBoundsException, ParseException{

		sItem = new SmallItem("Glass bottle (19th century) Hand holding a colt",
				"18xx","21/12/2015",55.00,125.00,5);
		sContract = new StandardContract();
	}
	/**
	 * freeAuction method test.
	 * Tests the expiration condition
	 */
	@Test
	public void freeAuctionTest1() {
		assertTrue(sContract.freeAuction());
		sContract.endDate=LocalDate.now().minusDays(1);
		assertFalse(sContract.freeAuction());
	}
	/**
	 * freeAuction method test.
	 * Tests the limited free auctions condition
	 */
	@Test
	public void freeAuctionTest2(){
		assertTrue(sContract.freeAuction());
		sContract.freeAuctions=0;
		assertFalse(sContract.freeAuction());
	}
	/**
	 * Tests increaseSpentAmount method
	 * Standard scenario, various additions
	 * @throws InvalidArgumentsException Will not be thrown
	 */
	@Test 
	public void increaseSpentAmount1() throws InvalidArgumentsException{
		assertEquals(sContract.currentAmount, 0.00, 0.005);
		sContract.increaseAmount(85.23);
		sContract.increaseAmount(54.28);
		assertEquals(sContract.currentAmount,139.51,0.005);
	}
	/**
	 * Tests increaseSpentAmount method
	 * Failure scenario, a negative amount is passed
	 * @throws InvalidArgumentsException when a negative amount
	 * is received
	 */
	@Test (expected = InvalidArgumentsException.class)
	public void increaseSpentAmount2() throws InvalidArgumentsException{
		sContract.increaseAmount(-54.21);

	}
	/**
	 * Tests spendFreeAuction method when there are 
	 * free auctions still available
	 * 
	 */
	@Test
	public void spendFreeAuctionTest1(){
		assertTrue(sContract.spendFreeAuction());
		assertEquals(sContract.freeAuctions, 4);
	}
	/**
	 * Tests spendFreeAuction method when there are 
	 * no free auctions available
	 * 
	 */
	@Test
	public void spendFreeAuctionTest2(){
		sContract.freeAuctions=0;
		assertFalse(sContract.spendFreeAuction());

	}
	/**
	 * Tests the isActivated method when the contract 
	 * is activated and when it is not
	 */
	@Test
	public void isActivatedTest(){
		assertFalse(sContract.isActivated());
		sContract.currentAmount=200.00;
		assertTrue(sContract.isActivated());
	}
	/**
	 * Tests the applyDiscount method when the contract is 
	 * activated and when it is not
	 */
	@Test
	public void applyDiscountTest1(){
		assertEquals(sContract.applyDiscount(sItem),sItem.getTargetPrice(),
				0.005);
		sContract.currentAmount=400.00;
		assertEquals(sContract.applyDiscount(sItem),
				(1-sItem.getPercentageDiscount())* sItem.getTargetPrice(),
				0.005);
	}
}
