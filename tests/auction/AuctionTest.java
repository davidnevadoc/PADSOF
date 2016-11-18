package auction;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import auction.exceptions.InvalidAuctionAction;
import contract.Contract;
import contract.StandardContract;
import contract.VipContract;
import customer.ContractCustomer;
import es.uam.eps.padsof.emailconnection.EmailException;
import exceptions.InvalidArgumentsException;
import exceptions.StateTransitionException;

import java.time.LocalDate;
import java.text.ParseException;



import item.*;

public class AuctionTest {
	Auction auction1;
	Auctionable auctionable1;
	Lot lot1;
	Bid bid1;
	Bid bid2;
	Bid bid3;
	Bid bid4;
	Bid bid5;
	ContractCustomer customer1;
	@Before
	public void setUp() throws ParseException, StateTransitionException, InvalidArgumentsException{
		Item sItem = new SmallItem("Glass bottle (19th century) Hand holding a colt",
				"18xx","21/12/2015",55.00,125.00,0.05);
		lot1= new Lot();
		lot1.addItem(sItem);
		auctionable1 = lot1;
		auction1= new Auction(10,1.00,10.00, auctionable1);
		Contract contract1 = new StandardContract();
		Contract contract2 = new VipContract();
		customer1 = new ContractCustomer("Jack", "Smith", "12345678A",
				"example@address.com", contract1);
		ContractCustomer customer2 = new ContractCustomer("Denna", "Wind", "98765432B",
				"other@example.com", contract2);
		bid1= new Bid(customer1, 100.50);
		bid2 = new Bid(customer2, 130.19);
		bid3 = new Bid(customer1, 131.19);
		bid4 = new Bid(customer2, 132.00);
		bid5 = new Bid(customer1, 1.00);
	}
	/**
	 * Constructor  tester, with invalid increment
	 * @throws InvalidArgumentsException when invalid arguments are
	 * passed to  the constructor
	 * @throws StateTransitionException when a not availabe auctionable
	 * is received
	 */
	@SuppressWarnings("unused")
	@Test (expected = InvalidArgumentsException.class)
	public void auctionTest1() throws InvalidArgumentsException, StateTransitionException{
		int id =auctionable1.hashCode() ^ LocalDate.now().hashCode();
		Auction auction = new Auction(20, - 0.8, id, auctionable1 );
	}
	/**
	 * Constructor  tester, with invalid duration of the auction
	 * @throws InvalidArgumentsException when invalid arguments are
	 * passed to  the constructor
	 * @throws StateTransitionException when a not availabe auctionable
	 * is received
	 */
	@SuppressWarnings("unused")
	@Test (expected = InvalidArgumentsException.class)
	public void auctionTest2() throws InvalidArgumentsException, StateTransitionException{
		int id =auctionable1.hashCode() ^ LocalDate.now().hashCode();
		Auction auction = new Auction(- 20, 8, id, auctionable1 );
	}
	/**
	 * Constructor  tester, with invalid auctionable
	 * @throws InvalidArgumentsException when invalid arguments are
	 * passed to  the constructor
	 * @throws StateTransitionException when a not availabe auctionable
	 * is received
	 */
	@SuppressWarnings("unused")
	@Test (expected = InvalidArgumentsException.class)
	public void auctionTest3() throws InvalidArgumentsException, StateTransitionException{
		int id =auctionable1.hashCode() ^ LocalDate.now().hashCode();
		Auction auction = new Auction(10, 8, id, null );
	}
	/**
	 * Tests the addition of a new participant
	 * to the auction
	 * @throws InvalidAuctionAction Will not be thrown
	 */
	@Test
	public void addParticipantTest1() throws InvalidAuctionAction{
		assertTrue(auction1.participants.isEmpty());
		auction1.addParticipant(customer1);
		assertTrue(auction1.participants.contains(customer1));
	}
	/**
	 * Tests an invalid addition of a participant when
	 * the auction is not available
	 * @throws InvalidAuctionAction when the action is 
	 * not allowed in the current state of the auction
	 */
	@Test (expected = InvalidAuctionAction.class)
	public void addParticipantTest2() throws InvalidAuctionAction{
		assertTrue(auction1.participants.isEmpty());
		auction1.cancel();
		auction1.addParticipant(customer1);
	}
	/**
	 * Tests the effective addition of customers from
	 * the mailing list
	 */
	@Test
	public void addToMailingListTest(){
		assertTrue(auction1.mailingList.isEmpty());
		auction1.addToMailingList(customer1);
		assertTrue(auction1.mailingList.contains(customer1));
	}
	/**
	 * Tests the effective removal of customers from
	 * the mailing list
	 */
	@Test
	public void removeFromMailingListTest1(){
		assertTrue(auction1.mailingList.isEmpty());
		auction1.addToMailingList(customer1);
		auction1.removeFromMailingList(customer1);
		assertTrue(auction1.mailingList.isEmpty());
	}
	/**
	 * Tests the non-effective removal of customers from
	 * the mailing list. This is removing a customer that 
	 * is not in the mailing list
	 */
	@Test
	public void removeFromMailingListTest2(){
		assertTrue(auction1.mailingList.isEmpty());
		auction1.removeFromMailingList(customer1);
		assertTrue(auction1.mailingList.isEmpty());
	}
	/**
	 * Tests an ordinary successful cancellation of
	 * an auction
	 * @throws InvalidAuctionAction Will not be thrown
	 */
	@Test
	public void cancelTest1() throws InvalidAuctionAction {	
		auction1.cancel();
		assertEquals(auction1.state, State.Cancelled);
	}
	/**
	 * Tests an invalid cancellation of an auction.
	 * It can't be cancelled because it is on running
	 * state
	 * @throws InvalidAuctionAction Will not be  thrown
	 * @throws EmailException Will not be  thrown
	 */
	@Test (expected = InvalidAuctionAction.class)
	public void cancelTest2() throws InvalidAuctionAction, EmailException {
		auction1.addBid(bid1);
		auction1.cancel();
	}

	/**
	 * Tests an ordinary  successful auction closing,
	 * @throws InvalidAuctionAction Will not be thrown
	 * @throws EmailException Will not be thrown
	 */
	@Test
	public void closeTest1() throws InvalidAuctionAction, EmailException{
		auction1.addBid(bid1);
		auction1.startDate = LocalDate.now().minusDays(auction1.duration);
		auction1.close();
		assertEquals(auction1.state, State.Closed);
	}
	/**
	 * Test an invalid closing when the user
	 * tries to close the auction but its duration its
	 * not over yet.
	 * @throws InvalidAuctionAction when an invalid action
	 * is tried for the actual state of the auction
	 * @throws EmailException when the notification could not
	 * be sent
	 */
	@Test(expected = InvalidAuctionAction.class)
	
	public void closeTetst2() throws InvalidAuctionAction, EmailException{
		auction1.addBid(bid1);
		auction1.close();
	}
	/**
	 * Test an invalid closing when the user
	 * tries to close the auction but not bids have been made yet.
	 * @throws InvalidAuctionAction when an invalid action
	 * is tried for the actual state of the auction
	 */
	@Test(expected = InvalidAuctionAction.class)
	
	public void closeTetst3() throws InvalidAuctionAction{
		auction1.startDate = LocalDate.now().minusDays(auction1.duration);
		auction1.close();
	}
	/**
	 * Tests the daysRemaining method when its 
	 * expected result is maximum
	 * @throws InvalidAuctionAction Will not be thrown
	 * @throws EmailException Will not be thrown
	 */
	@Test 
	public void daysRemainingTest1() throws InvalidAuctionAction, EmailException{
		auction1.addBid(bid1);
		assertEquals(auction1.daysRemaining(), auction1.duration);
	}
	/**
	 * Tests the daysRemaining method when its 
	 * expected result is minimum
	 * @throws InvalidAuctionAction Will not be thrown
	 * @throws EmailException Will not be thrown
	 */
	@Test 
	public void daysRemainingTest2() throws InvalidAuctionAction, EmailException{
		auction1.addBid(bid1);
		auction1.startDate=LocalDate.now().minusDays(auction1.duration);
		assertEquals(auction1.daysRemaining(), 0);
	}
	/**
	 * Tests the daysRemaining method when it is executed 
	 * in an invalid state
	 * @throws InvalidAuctionAction when called in a state 
	 * different from Running
	 */
	@Test(expected = InvalidAuctionAction.class)
	
	public void daysRemainingTest3() throws InvalidAuctionAction{
		
		auction1.daysRemaining();
	}
	
	/**
	 * Tests the getMaxBidTest method, assuring it selects
	 * the appropriate bid
	 * @throws InvalidAuctionAction Will not be thrown
	 * @throws EmailException Will not be thrown
	 */
	@Test 
	
	public void getMaxBidTest1() throws InvalidAuctionAction, EmailException{
		auction1.addBid(bid1);
		auction1.addBid(bid2);
		auction1.addBid(bid3);
		auction1.addBid(bid4);
		assertEquals(auction1.getMaxBid(), bid3);
		assertEquals(auction1.getMaxBid(), auction1.highestBid);
	}
	/**
	 * Tests the getMaxBidTest method when no bids have
	 * been made yet
	 * @throws InvalidAuctionAction when the auction has
	 * no bids
	 */
	@Test (expected = InvalidAuctionAction.class)
	public void getMaxBidTest2() throws InvalidAuctionAction{
		
		auction1.getMaxBid();
	}
	/**
	 * Tests the getMaxBidTest method when no bids have
	 * been made and has been cancelled.
	 * @throws InvalidAuctionAction when the auction has
	 * no bids
	 */
	@Test (expected = InvalidAuctionAction.class)
	public void getMaxBidTest3() throws InvalidAuctionAction{
		auction1.cancel();
		auction1.getMaxBid();
	}
	/**
	 * Tests the isAvailable method when the auction
	 * is in the cancelled state
	 */
	@Test
	public void isAvailableTest1(){
		auction1.state= State.Cancelled;
		assertFalse(auction1.isAvailable());
	
	}
	/**
	 * Tests the isAvailable method when the auction
	 * is in the closed state
	 */
	@Test
	public void isAvailableTest2(){
		auction1.state= State.Closed;
		assertFalse(auction1.isAvailable());
	
	}
	/**
	 * Tests the isAvailable method when the auction
	 * is in the running state
	 */
	@Test
	public void isAvailableTest3(){
		auction1.state= State.Running;
		assertTrue(auction1.isAvailable());
	
	}
	/**
	 * Tests the isAvailable method when the auction
	 * is in the opened state
	 */
	@Test
	public void isAvailableTest4(){
		assertTrue(auction1.isAvailable());
	
	}
	/**
	 * Tests getWinner method, in a standard success
	 * scenario
	 * @throws InvalidAuctionAction Will not be thrown
	 * @throws EmailException Will not be thrown
	 */
	@Test
	public void getWinnerTest1() throws InvalidAuctionAction, EmailException{
		auction1.addBid(bid1);
		assertEquals(auction1.getWinner(), customer1);
	}
	/**
	 * Tests getWinner method in an invalid state of the auction.
	 * An InvalidAuctionAction exception is thrown
	 * @throws InvalidAuctionAction when the auction is not closed or running
	 * @throws EmailException Will not be thrown
	 */
	@Test(expected = InvalidAuctionAction.class)
	public void getWinnerTest2() throws InvalidAuctionAction, EmailException{
		assertEquals(auction1.getWinner(), customer1);
	}
	/**
	 * Tests the addBid method, it checks that after a successful
	 * addition of a bid the information of a bid is set correctly
	 * @throws InvalidAuctionAction when the auction is not closed or running
	 * @throws EmailException Will not be thrown
	 */
	@Test
	public void addBidTest1() throws InvalidAuctionAction, EmailException{
		assertTrue(auction1.bids.isEmpty());
		assertTrue(auction1.participants.isEmpty());
		auction1.addBid(bid1);
		assertEquals(LocalDate.now(), auction1.startDate);
		assertTrue(auction1.bids.contains(bid1));
		assertTrue(auction1.participants.contains(customer1));
		assertEquals(auction1.state, State.Running);
	}
	/**
	 * Tests the addBid method, it checks the return of the method is
	 * correct.
	 * @throws InvalidAuctionAction when the auction is not closed or running
	 * @throws EmailException Will not be thrown
	 */
	@Test
	public void addBidTest2() throws InvalidAuctionAction, EmailException{
		assertTrue(auction1.addBid(bid1));
		assertTrue(auction1.addBid(bid2));
		assertFalse(auction1.addBid(bid3));
	}
	/**
	 * Tests the addBid method when the Auction is in a state  when 
	 * no bids can be made (unavailable).
	 * @throws InvalidAuctionAction when the auction is not available
	 * @throws EmailException Will not be thrown
	 */
	@Test (expected = InvalidAuctionAction.class)
	public void addBidTest3() throws InvalidAuctionAction, EmailException{
		auction1.cancel();
		auction1.addBid(bid1);
	}
	/**
	 * Tests the addBid method when the bid price is below the auction
	 * bas price.
	 * @throws InvalidAuctionAction when the bid price is too low
	 * @throws EmailException Will not be thrown
	 */
	@Test (expected = InvalidAuctionAction.class)
	public void addBidTest4() throws InvalidAuctionAction, EmailException{
		auction1.cancel();
		auction1.addBid(bid4);
	}
}
