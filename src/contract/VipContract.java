package contract;

import Utilities.Configuration;
import item.Item;

/**
 * Vip contract of the antiquary
 * @author David
 * @author Jorge Arellano Subias
 */
public class VipContract extends Contract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5356136148337482472L;
	/**
	 * Percentage discounted to the delivery of items
	 */
	protected double deliverydiscount = 0.5;
	/**
	 * VipContract constructor.
	 * Creates a VipContract
	 */
	public VipContract(){		
		super();
		this.deliverydiscount=Configuration.getDeliveryDiscount();
	}
	/**
	 * Calculates the final delivery cost of an item applying
	 * the appropriate discount offered by the contract
	 * @param item Item which discounted delivery cost we want to obtain
	 * @return Discounted delivery cost of the item
	 */
	@Override public double applyDeliveryDiscount(Item item){
		return item.calculateDelivery() * (1 - deliverydiscount) ;
	}
	/**
	 * Checks if the contract can provide a free auction
	 * @return true if the contract has not expired, false 
	 * otherwise
	 */
	@Override public boolean freeAuction(){
		if(this.hasExpired()){
			return false;
		} else {
			return true;
		}
	}
	@Override public void increaseAmount(double amount) {
		/*Does nothing*/
	}
}
