package serializers;

import java.util.*;

import auction.Auction;
import customer.Customer;
import report.Report;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Serializes different objects of the antiquary
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 *
 */
public class Serializer {
	/**
	 * Saves a list of auctions into a file
	 * @param auctions List of auctions to be saved
	 * @param file File where the report will be saved
	 */
	public void serializeAuctions(ArrayList<Auction> auctions, String file){
		try{
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(auctions);
			oos.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Saves a list of customers to a file
	 * 
	 * @param customers List of customers to be saved
	 * @param file File where the report will be saved
	 */
	public void serializeCustomers(ArrayList<Customer> customers, String file){
		
		try{
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(customers);
			oos.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Saves the report into a file
	 * @param report Report to be saved
	 * @param file File where the report will be saved
	 */
	public void serializeReport(Report report, String file){
		try{
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(report);
			oos.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	 
}
