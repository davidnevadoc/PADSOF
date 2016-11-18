package auction.exceptions;

import auction.State;

public class InvalidAuctionAction extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4696679967648505931L;
	/**
	 * State in which the auction was when the invalid
	 * operation was requested
	 */

	private String message;
	/**
	 * InvalidAuctionAction constructor.
	 * Creates an InvalidAuctionAction exception
	 * @param state State in which the auction was when 
	 * the exception was generated
	 */
	public InvalidAuctionAction(State state){
		
		this.message = "This operation cannot be performed in a " +  state.toString() +
				" auction.";
	}
	/**
	 * InvalidAuctionAction constructor
	 * Creates an InvalidAuctionAction exception
	 * @param message Error message the exception will 
	 * generate
	 */
	public InvalidAuctionAction(String message){
		this.message=message;
	}
	/**
	 * Message to output when the exception is caught
	 */
	@Override public String toString(){
		return message;
	}
	
}
