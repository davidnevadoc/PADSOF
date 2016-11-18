package serializers;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import auction.Auction;
import customer.Customer;
import exceptions.InvalidArgumentsException;
import exceptions.StateTransitionException;
import item.Auctionable;
import item.BigItem;
import item.Item;
import item.Lot;
import item.SmallItem;
import report.Record;
import report.Report;
import sales.InvalidAdditionException;
import sales.Sale;
/**
 * Serializer and Deserializer testers
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public class SerializersTest {
	ArrayList<Auction> auctions; 
	ArrayList<Customer> customers;
	Report report;
	
	Auction auction1;
	Auction auction2;
	
	Customer customer1;
	Customer customer2;
	
	Record record1;
	Record record2;


	@Before
	public void setUp() throws ParseException, StateTransitionException, InvalidArgumentsException, InvalidAdditionException{
		auctions = new ArrayList<Auction>();
		customers = new ArrayList<Customer>();
		Item sItem = new SmallItem("Glass bottle (19th century) Hand holding a colt",
				"18xx","21/12/2015",55.00,125.00,0.05);
		Item sItem2 = new SmallItem("Glass bottle (18th century) Hand holding a colt",
				"17xx","21/12/2015",65.00,155.00,0.05);
		Lot lot1 = new Lot();
		Lot lot2 = new Lot();
		lot1.addItem(sItem);
		lot2.addItem(sItem2);
		Auctionable auctionable1 = lot1;
		Auctionable auctionable2 = lot2;
		auction1 = new Auction(10,1.00,10.00, auctionable1);
		auction2 = new Auction(10,1.00,10.00, auctionable2);
		auctions.add(auction1);
		auctions.add(auction2);
		customer1 = new Customer("Johnny", "Silvery", "11111678A",
				"example2@address.com", false);
		customer2 = new Customer("Johnny", "Silvery", "11331678A",
				"example2@address.com", false);
		customers.add(customer1);
		customers.add(customer2);
		Sale sale1 = new Sale(customer1);
		Sale sale2 = new Sale(customer1);
		SmallItem item1 = new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
		BigItem item2 = new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,86,175,50);
		SmallItem item3 = new SmallItem("Ancient coin", "10xx", "22/11/2015", 45.45, 89.99, 2);
		sale1.addItem(item1);
		sale1.addItem(item2);
		sale2.addItem(item3);
		record1= new Record(sale1);
		record2 = new Record(sale2);
		report = new Report();
		report.addRecord(record1);
		report.addRecord(record2);
	}
	/**
	 * Tests the saving and loading of the auctions
	 */
	@Test
	public void serializeDeserializeAuctionTest() {
		Serializer serializer = new Serializer();
    	serializer.serializeAuctions(auctions, "auctionsTest.ser");
		Deserializer deserializer = new Deserializer();
    	ArrayList<Auction> auctionscopy = deserializer.deserializeAuctions("auctionsTest.ser");
    	assertTrue(auctionscopy.contains(auction1));
    	assertTrue(auctionscopy.contains(auction2));
	}
	/**
	 * Tests the saving and loading of the customers
	 */
	@Test
	public void serializeDeserializeCustomerTest() {
		Serializer serializer = new Serializer();
    	serializer.serializeCustomers(customers, "customersTest.ser");
		Deserializer deserializer = new Deserializer();
    	ArrayList<Customer> customercopy = deserializer.deserializeCustomers("customersTest.ser");
    	assertTrue(customercopy.contains(customer1));
    	assertTrue(customercopy.contains(customer2));
	}
	/**
	 * Tests the saving and loading of the report
	 */
	@Test
	public void serializeDeserializeReportTest() {
		Serializer serializer = new Serializer();
    	serializer.serializeReport(report, "reportTest.ser");
		Deserializer deserializer = new Deserializer();
    	Report reportcopy = deserializer.deserializeReport("reportTest.ser");
    	assertTrue(reportcopy.equals(report));

	}
}
