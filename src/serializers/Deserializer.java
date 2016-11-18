package serializers;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.util.ArrayList;

import auction.Auction;
import customer.Customer;
import report.Report;

 /**
 * Serializes different objects of the antiquary
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 *
 */
 
public class Deserializer {
	 /**
	 * Loads a list of auctions from a file
	 * @param file File where the auctions will be loaded from
	 * @return The list of loaded auctions
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Auction> deserializeAuctions(String file){
		   
		 ArrayList<Auction> auctions;
		 
		   try{
			    
			   FileInputStream fin = new FileInputStream(file);
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   auctions = (ArrayList<Auction>) ois.readObject();
			   ois.close();
			  
			   return auctions;
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
			   return null;
		   } 
	   } 
	 /**
	  * Loads a list of customers from a file
	  * @param file File where the customers will be loaded from
	  * @return The list of  loaded users
	  */
	 @SuppressWarnings("unchecked")
	public ArrayList<Customer> deserializeCustomers(String file){
		   
		 ArrayList<Customer> customers;
		 
		   try{
			    
			   FileInputStream fin = new FileInputStream(file);
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   customers = (ArrayList<Customer>) ois.readObject();
			   ois.close();
			  
			   return customers;
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
			   return null;
		   } 
	   } 
	 /**
	  * Loads a report from a file
	  * @param file File where the report will be loaded from
	  * @return The report of the antiquary
	  */
	 public Report deserializeReport(String file){
		 Report report;
		 try{
			    
			   FileInputStream fin = new FileInputStream(file);
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   report = (Report) ois.readObject();
			   ois.close();
			  
			   return report;
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
			   return null;
		   } 
	 }
}
