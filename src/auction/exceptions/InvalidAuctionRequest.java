package auction.exceptions;

import auction.State;

public class InvalidAuctionRequest extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8018299957323847788L;
	/**
	 * State in which the auction was when the invalid
	 * operation was requested
	 */
	private State state;
	/**
	 * InvalidAuctionRequest constructor.
	 * Creates InvalidAuctionRequest exception.
	 * 
	 * @param state State in which the auction was when
	 * the exception was generated.
	 */
	public InvalidAuctionRequest(State state){
		this.state=state;
	}
	/**
	 * Message to output when the exception is caught
	 */
	@Override public String toString(){
		if(this.state.compareTo(State.Opened)==0){
			return "The auction has not been started yet.";
		} else {
			return "The auction is not available";
		}
	}
}
