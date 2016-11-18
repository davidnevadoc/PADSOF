
package contract;

import Utilities.Configuration;
import exceptions.InvalidArgumentsException;
import item.Item;

/**
 * Standard contract of the antiquary
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public class StandardContract extends Contract {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6312177991540864628L;
	/**
	 * Number of auctions in which the client can 
	 * enter for free
	 */
	protected int freeAuctions;
	/**
	 * Amount of money spent in the shop (sales or
	 * auctions) since he got the contract.
	 */
	protected double currentAmount;
	

	private static double minamount; 
	
	
	/**
	 * Constructor of object StandardContract.
	 * Creates a standard contract with 0 euros
	 * spent, and the number of free auctions
	 * specified by the configuration file
	 */
	public StandardContract(){
		this.currentAmount=0;
		this.freeAuctions=Configuration.getFreeAuctions();
		this.minamount=Configuration.getMinAmount();
	}
	
	/**
	 * Increases the amount of money spent on the 
	 * antiquary by the client
	 * @param amount Money the client has spent in
	 * this purchase or auction.
	 * @throws InvalidArgumentsException when a negative
	 * amount is passed
	 */
	@Override public void increaseAmount(double amount) throws InvalidArgumentsException{
		if(amount < 0 ){
			throw new InvalidArgumentsException();
		}
		this.currentAmount+=amount;
	}
	
	/**
	 * Decreases the number of free auctions the 
	 * contract has
	 * @return true if there the free auction was 
	 * available and was spent, false otherwise.
	 */
	public boolean spendFreeAuction(){
		if(this.freeAuction()){
			this.freeAuctions--;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the contract provides a free 
	 * auction for the user.
	 * @return true if the contracts provides a free auction
	 * false if it does not.
	 */
	@Override public boolean freeAuction(){
		if(this.freeAuctions>0 && this.hasExpired()==false){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Gets the amount of money spent by the client
	 * since he got the contract
	 * @return the amount of money spent by the client.
	 */
	public double getCurrentAmount(){
		return this.currentAmount;
	}
	/**
	 * Gets the number of free auctions the client
	 * can use
	 * @return the number of free auctions the customer
	 * has left
	 */
	public int getFreeAuctions(){
		return this.freeAuctions;
	}
	/**
	 * Applies the appropriate discount to the item but
	 * only if the contract is activated
	 * @param item Item which discounted price we want to get
	 * @return discounted price of the item
	 */
	@Override public double applyDiscount(Item item){
		if(this.isActivated()){
			return super.applyDiscount(item);
		}else{
			return item.getTargetPrice();
		} 
	}
	/**
	 * Checks if the contract is activated. 
	 * It does not check if it has expired, that issue
	 * has been taken care of in the Contract class
	 * @return true if the contract is activated, false otherwise.
	 */
	protected boolean isActivated(){
		return (this.currentAmount >= this.minamount);
	}
	
	
}
