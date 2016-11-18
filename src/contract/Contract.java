
package contract;
import java.io.Serializable;

import java.time.LocalDate;

import exceptions.InvalidArgumentsException;
import item.*;
/**
 * Contract of the antiquary
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 *
 */
public abstract class Contract implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 81289492842501877L;
	/**
	 * Expiration date of  the contract
	 */
	protected LocalDate endDate;

	/**
	 * Constructor of a generic contract.
	 * It sets the Expiration date to one year from
	 * the moment the contract is created
	 */
	public Contract(){
		this.endDate = LocalDate.now().plusYears(1);
	}
	/**
	 * Checks if the contract has expired.
	 * @return true if the contract is too old and has expired
	 * false if it is still valid.
	 */
	
	protected boolean hasExpired(){
		return LocalDate.now().isAfter(this.endDate);
	}
	/**
	 * Checks if the contract provides a free 
	 * auction for the user
	 * @return true if the contracts provides a free auction
	 * false if it does not.
	 */
	public abstract boolean freeAuction();
	/**
	 * Applies the appropriate discount to an item
	 * @param item Item which discounted price we want to obtain
	 * @return Discounted price of item.
	 */
	public double applyDiscount(Item item){
		if (this.hasExpired()){
			return item.getTargetPrice();
		} else {
			return (1-item.getPercentageDiscount()) * item.getTargetPrice();
	
		}
	}
	/**
	 * Calculates the final delivery cost of an item applying
	 * the appropriate discount offered by the contract
	 * @param item Item which discounted delivery cost we want to obtain
	 * @return Discounted delivery cost of the item
	 */
	public double applyDeliveryDiscount(Item item){
		return item.calculateDelivery();
	}
	
	/**
	 * Get the date of expiration of the contract
	 * 
	 * @return The date in which the contract expires
	 */
	public LocalDate getEndDate(){
		return this.endDate;
	}
	/**
	 * Increases the amount of money spent by the customer
	 * with the contract.
	 * The generic contract doesn't keep track of the spentAmount
	 * so it does nothing.
	 * @param amount Amount spent in Euros
	 * @throws InvalidArgumentsException when receives a negative amount
	 */
	public abstract void increaseAmount(double amount) throws InvalidArgumentsException;	
	
}
	
