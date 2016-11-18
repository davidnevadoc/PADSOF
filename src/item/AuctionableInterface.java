
package item;

import exceptions.StateTransitionException;

/**
 * Auctionable interface, needed to simulate multiple inheritance
 */
public interface AuctionableInterface {
    
    /**
     * Setter
     * @return variable inAuction
     */
    public boolean isInAuction();
    
    /**
     * Takes an auctionable to auction
     * @throws StateTransitionException
     */
    public void toAuction() throws StateTransitionException;
    
    /**
     * Sells an auctionable in auction
     * @throws StateTransitionException
     */
    public void sold()throws StateTransitionException;
    
    /**
     * Cancells an auctionable in auction
     * @throws StateTransitionException
     */
    public void cancelled()throws StateTransitionException;
    
}
