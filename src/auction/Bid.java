package auction;

import java.time.LocalDate;

import customer.*;
/**
 * 
 * Bid for an auction
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public class Bid {
	/**
	 * Customer who has set the bid
	 */
	private ContractCustomer customer;
	/**
	 * Price for which the customer is bidding
	 */
	private double price;
	/**
	 * Date when the bid has been made
	 */
	private LocalDate date;
	
	/**
	 * Bid constructor.
	 * Creates a bid given a customer and a price.
	 * @param customer Customer who places the bid
	 * @param price Price for which the customer  is bidding
	 */
	public Bid(ContractCustomer customer, double price){
		this.customer=customer;
		this.price=price;
		this.date=LocalDate.now();
	}
	/**
	 * Compares two bids.
	 * @param bid Bid with which the comparison is made
	 * @param increment Amount in which the bid must be greater than
	 * the passed bid. Must always be positive, if not it will be 0.
	 * @return true if the bid is greater or equal than the bid passed
	 * plus the increment value
	 */
	public boolean isGreater(Bid bid, double increment){
		if(increment<0){
			increment=0;
		}
		return (this.price>=bid.getPrice()+increment);
	}
	/**
	 * Price getter
	 * @return Price for which the customer is bidding
	 */
	public double getPrice(){
		return this.price;
	}
	/**
	 * Customer getter
	 * @return Customer who has placed the bid
	 */
	public ContractCustomer getCustomer(){
		return this.customer;	
	}
	/**
	 * Prints relevant information of the bid
	 * @return String with the information of the bid
	 */
	@Override public String toString(){
		return "Bid for " + this.price + " by " + this.customer.toString()
		+ " on " + this.date.toString();
	}
}
