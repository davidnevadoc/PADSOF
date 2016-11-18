package demonstrators;


import antiquary.Antiquary;
import auction.Bid;
import auction.exceptions.InvalidAuctionAction;
import contract.*;
import customer.ContractCustomer;
import customer.Customer;
import es.uam.eps.padsof.emailconnection.EmailException;
import exceptions.AddingFailureException;
import exceptions.InvalidArgumentsException;
import exceptions.NotFoundException;
import report.Record;
import sales.InvalidAdditionException;
import sales.Sale;
/**
 * Employee Demonstrator
 * 
 * Demonstrates the basic functionalities of the apllication that
 * an employee would perform
 * 
 * This demonstrator is called in the manager demonstrator
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public class EmployeeDemonstrator {
	/**
	 * 
	 * Entry point of the employee demonstrator
	 * 
	 * The antiquary is passed instead of being saved and reloaded
	 * in order to keep the antiquary in the same state and be able
	 * to run the demos several times.
	 * 
	 * @param antiquary Antiquary passed by the manager demonstrator
	 */
	public static void demoEmployee(Antiquary antiquary){


		System.out.println("Trying to enter with wrong credentials: hello, goodbye");
		if(antiquary.login("hello", "goodbye")){
			System.out.println("Login successful");
		} else  {
			System.out.println("Login failure");
		}
		
		System.out.println("Trying to enter with right credentials: are, muvis");
		if(antiquary.login("are", "muvis")){
			System.out.println("Login successful");
		} else  {
			System.out.println("Login failure");
		}
		System.out.println("Creating customer");
		Customer customer1 =null;
		try {
			
			customer1 = new Customer("Johnny", "Silvery", "11331678A",
					"example2@address.com", false);
			
			
		} catch (InvalidArgumentsException e) {
			
			System.out.println("Customer could not be created");
		}
		
		Contract contract1 = new VipContract();
		System.out.println("Assigning a contract customer");
		ContractCustomer customer1upgraded = null;
		try {
			customer1upgraded = new ContractCustomer(customer1, contract1);
		} catch (InvalidArgumentsException e1) {
			// TODO Auto-generated catch block
			System.out.println("Customer could not be upgraded");
		}
		System.out.println("Deactivate notifications for customers");
		customer1upgraded.setNotification(false);
		try {
			antiquary.addCustomer(customer1upgraded);
		} catch (AddingFailureException e) {
			System.out.println("Customer could not be added");
		}
		
		Bid bid1= new Bid(customer1upgraded, 100.50);
		
		try {
			antiquary.getAuctions().get(0).addBid(bid1);
		} catch (InvalidAuctionAction | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Sale sale1= new Sale(customer1upgraded);
		try {
			sale1.addItem(antiquary.getItemWithId(9));
			System.out.println(antiquary.getItemWithId(9).getPercentageDiscount());
			System.out.println(antiquary.getItemWithId(9).getDescription());

		} catch (InvalidAdditionException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sale1.addItem(antiquary.getItemWithId(10));
		} catch (InvalidAdditionException | NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sale1.finish();
		} catch (InvalidArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Record recordSale1 = new Record(sale1);
		antiquary.updateReport(recordSale1);
		
		
	}
}
