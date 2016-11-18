package demonstrators;

import java.io.IOException;

import java.util.ArrayList;

import Utilities.Configuration;
import antiquary.Antiquary;
import auction.Auction;
import exceptions.AddingFailureException;
import exceptions.InvalidArgumentsException;
import exceptions.NotFoundException;
import exceptions.StateTransitionException;
import item.Item;
import item.Lot;
import report.Report;
import user.Employee;

/**
 * Application demonstrator.
 * It  starts with the demonstrator of the actions performed by the 
 * manager, then some employees actions are performed.
 * 
 * VERY IMPORTANT:  Normally the antiquary would save its state with
 * the method close. This line is commented and wont be executed in 
 * order to be able to execute the demonstrator serveral times
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 *
 */
public class ManagerDemonstrator {
	/**
	 * Entry point of the demonstrator.
	 * Demonstrates the  basic functionalities of the application
	 * @param args No arguments needed
	 */
	public static void main(String [] args) {

		Antiquary antiquary = null;
		Configuration.loadMacros();
		try {
			antiquary = Antiquary.loadAntiquary();
		} catch (IOException | NotFoundException e1) {
			System.out.println("Load error");
			System.exit(1);
		}
		
		
		
		System.out.println("Trying to enter with wrong credentials: hello, goodbye");
		if(antiquary.login("hello", "goodbye")){
			System.out.println("Login successful");
		} else  {
			System.out.println("Login failure");
		}
		
		System.out.println("Trying to enter with right credentials: neva, dito");
		if(antiquary.login("neva", "dito")){
			System.out.println("Login successful");
		} else  {
			System.out.println("Login failure");
		}
		System.out.println("Addign user: comomola");
		Employee employee1 = new Employee("comomola","padsof");
		try {
			antiquary.addUser(employee1);
			System.out.println("User comomola added successfully");
		} catch (AddingFailureException e) {
			e.printStackTrace();
		}
		
		System.out.println("Trying to add the same  user again");
		try {
			antiquary.addUser(employee1);
			System.out.println("User comomola added successfully");
		} catch (AddingFailureException e) {
			System.out.println("User already registered. Nothing was  done");
		}
		System.out.println("Removing user comomola");
		antiquary.removeUser(employee1);
		System.out.println("User comomola removed");
		
		ArrayList<Item> items = antiquary.searchItemByDescription("Girl");
		Lot lot1 = new Lot();
		System.out.println("Creating lot");
	
		for(Item i : items){
			try {
				
				lot1.addItem(i);
				System.out.println("Item added:" + i.toString());
			} catch (StateTransitionException e) {
				e.printStackTrace();
			}
		}
	
		System.out.println("Lot created");
		System.out.println("Creating auction with the lot");
		try {
			Auction auction1 = new Auction(10, 1,80.50, lot1);
			antiquary.addAuction(auction1);
		} catch (InvalidArgumentsException e) {
	
			e.printStackTrace();
		} catch (StateTransitionException e) {
	
			e.printStackTrace();
		} catch (AddingFailureException e) {
			e.printStackTrace();
		}

		System.out.println("Auction created");

		System.out.println("Printing report");
		
		Report report = antiquary.getReport();
		System.out.print(report.printReport());
		
		/*Now, we call the employee demonstrator, which will do things with the stuff we have created here
		 * We could have save this and then load it in the other main, but that can lead into future not working of these demos
		 */
		System.out.println("Now is employee turn to do things with the things the manager have done.");
		
		/***/

		
		EmployeeDemonstrator.demoEmployee(antiquary);
		System.out.println("Printing report again ");
		report = antiquary.getReport();
		System.out.print(report.printReport());
		
		/*We dont close the antiquary and therefore dont save its state so that the demonstrator 
		 * may be run multiple times*/
		//antiquary.closeAntiquary();
	}
}
