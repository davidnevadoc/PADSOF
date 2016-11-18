package report;

import java.io.Serializable;
import java.time.LocalDate;

import auction.Auction;
import auction.exceptions.InvalidAuctionAction;
import sales.Sale;

public class Record implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4461896467510829746L;
	/**
	 * Date of the sale or auction
	 */
	protected LocalDate date;
	/**
	 * Ticket of the record
	 */
	protected String record;
	
	/**
	 * Record constructor
	 * Creates  a record of a sale
	 * @param sale Sale which record we want to generate
	 */
	public Record( Sale sale){
		this.date= sale.getDate();
		this.record =sale.toString();
	}
	/**
	 * Record constructor
	 * Creates the record of a closed auction
	 * @param auction Auction which report we want to generate
	 * @throws InvalidAuctionAction when the auction was not closed
	 */
	public Record (Auction auction) throws InvalidAuctionAction{

		this.date = auction.getEndDate();
		this.record = auction.toString();
	}
	/**
	 * Date getter
	 * @return The date of the auction or sale of the record
	 */
	public LocalDate getDate(){
		return this.date;
	}
	/**
	 * Returns a String with the informtation of the record
	 * @return String with the record
	 */
	@Override public String toString(){
		return this.record;
	}
	/**
	 * Comparees two records
	 * @param Record the record we want to compare
	 * @return true if they are equal, false otherwise
	 */
	@Override public boolean equals (Object record){
		Record  record2 = (Record) record;
		return (this.record.equals(record2.toString()));
	}
}
