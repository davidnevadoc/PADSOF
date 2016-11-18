package report;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import customer.Customer;
import exceptions.InvalidArgumentsException;
import item.BigItem;
import item.Item;
import item.SmallItem;
import sales.InvalidAdditionException;
import sales.Sale;

public class ReportTest {
	Sale sale1;
	Sale sale2;
	Item item1;
	Item item2;
	Item item3;
	Record record1;
	Record record2;
	Record record3;
	Report report;
	
	LocalDate date1;
	LocalDate date2;

	@Before
	public void setUp() throws ParseException, InvalidArgumentsException, InvalidAdditionException {
		date1= LocalDate.now().minusDays(5);
		date2= LocalDate.now().minusDays(1);
		Customer customer = new Customer("Jack", "Smith", "12345678A",
				"example@address.com");
		sale1 = new Sale(customer);
		sale2 = new Sale(customer);
		item1 = new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
		item2 = new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,86,175,50);
		item3 = new SmallItem("Ancient coin", "10xx", "22/11/2015", 45.45, 89.99, 2);
		sale1.addItem(item1);
		sale1.addItem(item2);
		sale2.addItem(item3);
		record1= new Record(sale1);
		record2 = new Record(sale2);
		report = new Report();
	}
	/**
	 * Tests the addReport method
	 */
	@Test
	public void addReportTest1() {

		assertTrue(report.records.isEmpty());
		report.addRecord(record1);
		assertTrue(report.records.contains(record1));
	}
	/**
	 *Tests the PrintReport method in a standard
	 *scenario with no arguments 
	 */
	@Test
	public void publicPrintReport1(){
		String expected = "Report:\n";
		expected += record1.toString();
		expected += "\n";
		
		report.addRecord(record1);
		
		
		assertEquals(expected, report.printReport());
	}
	/**
	 * Tests the PrintReport method when the requested
	 * records dont exist
	 */
	@Test
	public void publicPrintReport2(){
		String expected = "Report:\n";
		expected += "\n";
		
		report.addRecord(record1);
		
		
		assertEquals(expected, report.printReport(date1,date2));
	}
}
