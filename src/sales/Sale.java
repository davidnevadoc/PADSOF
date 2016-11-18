package sales;

import java.time.LocalDate;
import java.util.*;

import customer.*;
import exceptions.InvalidArgumentsException;
import exceptions.StateTransitionException;
import item.*;
/**
 * Sale of the antiquary
 * 
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */

public class Sale {
	/**
	 * Final price of the sale
	 */
	private double salePrice;
	/**
	 * Date of the sale
	 */
	private LocalDate saleDate;
	/**
	 * List of the items purchased
	 */
	private ArrayList<Item> items;
	/**
	 * Client buying the items
	 */
	private Customer buyer;

	/**
	 * Sale constructor.
	 * Creates a new empty sale.
	 * @param buyer Client buying the products
	 */
	public Sale(Customer buyer){
		this.salePrice=0.00;
		this.saleDate=LocalDate.now();
		this.buyer = buyer;
		this.items = new ArrayList<Item>();
	}
	/**
	 * Adds an item to the sale.
	 * @param newItem Item added to the sale
	 * @throws InvalidAdditionException exception when trying to add an item
	 * that has already been added
	 */
	public void addItem(Item newItem) throws InvalidAdditionException{
		try {
			newItem.sell();
		} catch (StateTransitionException e) {
			throw new InvalidAdditionException();
		}
		this.items.add(newItem);

		this.salePrice += buyer.applyDiscount(newItem);		
	}
	/**
	 * Restores the state of the items that were being 
	 * sold
	 */
	public void cancel(){
		if(this.items.isEmpty()==false){
			for(Item i : this.items){
				i.reset();
			}
		}
	}
	/**
	 * Finished the sale
	 * @throws InvalidArgumentsException If the total price of the
	 * sale is negative
	 */
	public void finish() throws InvalidArgumentsException{
		this.buyer.increaseAmount(this.getSalePrice());
	}
	
	/**
	 * Prints a report or ticket of the sale with all 
	 * its relevant information such as date, client and
	 * all the items purchased
	 * 
	 * @return String with all the information of the sale
	 */

	@Override public String toString(){
		String report = "At " + this.saleDate.toString() + " by customer " + this.buyer.toString() + ".\n" ;
		for(Item item : this.items){
			report += item.toString() +  " with discount: " + buyer.applyDiscount(item) +" Euros.\n";
		}
		report += "TOTAL: " + this.salePrice + "Euros.\n\n";
		return report;
	}
	/**
	 * Sale date getter
	 * @return Date of the sale
	 */
	public LocalDate getDate(){
		return this.saleDate;
	}
	/**
	 * Items getter
	 * @return The list of items being sold
	 */
	public ArrayList<Item> getItems(){
		return this.items;
	}
	/**
	 * Sale price getter
	 * @return Current total price of the sale
	 */
	public double getSalePrice(){
		return this.salePrice;
	}
	
}
