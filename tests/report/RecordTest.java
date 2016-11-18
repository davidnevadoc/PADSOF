package report;



import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import auction.Auction;
import auction.Bid;
import auction.exceptions.InvalidAuctionAction;
import contract.Contract;
import contract.StandardContract;
import customer.ContractCustomer;
import es.uam.eps.padsof.emailconnection.EmailException;
import exceptions.InvalidArgumentsException;
import exceptions.StateTransitionException;
import item.Auctionable;
import item.Item;
import item.Lot;
import item.SmallItem;

public class RecordTest {
	Auction auctionOpened;
	



	@Before
	public void setUp() throws ParseException, StateTransitionException, InvalidArgumentsException, InvalidAuctionAction, EmailException{
		Item sItem = new SmallItem("Glass bottle (19th century) Hand holding a colt",
				"18xx","21/12/2015",55.00,125.00,0.05);
		Auctionable auctionable1;
		Lot lot1;
		lot1= new Lot();
		lot1.addItem(sItem);
		auctionable1 = lot1;
		auctionOpened= new Auction(10,1.00,80.00, auctionable1);
		Contract contract1 = new StandardContract();
		ContractCustomer customer1;
		customer1 = new ContractCustomer("Jack", "Smith", "12345678A",
				"example@address.com", contract1);
		Bid bid1;
		bid1= new Bid(customer1, 100.50);
		auctionOpened.addBid(bid1);

	}
	/**
	 * Record constructor test, it fails when trying
	 * to generate a report of a not closed auction
	 * @throws InvalidAuctionAction when trying
	 * to generate a report of a not closed auction
	 */
	@SuppressWarnings("unused")
	@Test (expected = InvalidAuctionAction.class)
	public void RecordTest1() throws InvalidAuctionAction {
		Record record= new Record(auctionOpened);

	}

}
