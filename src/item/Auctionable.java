/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.io.Serializable;

import exceptions.StateTransitionException;

/**
 * Implements something that can be put into an auction.
 */
public class Auctionable implements AuctionableInterface, Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3672848650250280256L;

	/**
     * Shows if the auctionable is in auction or not
     */
    private boolean inAuction=false;

    /**
     * Shows if the auctionable is sold or not
     */
    protected boolean sold=false;
    /**
     * Getter
     * @return the variable inAuction
     */
    @Override public boolean isInAuction() {
        return inAuction;
    }

    /**
     * Takes an auctionable to auction
     * @throws StateTransitionException
     */
    @Override public void toAuction() throws StateTransitionException{
        if(sold) throw new StateTransitionException("You are not allowed to do that.");
        this.inAuction = true;
    }
    /**
     * Sells an auctionable in auction
     * @throws StateTransitionException
     */
    @Override public void sold()throws StateTransitionException{
        if(!isInAuction()) throw new StateTransitionException("You are not allowed to do that.");
        sold=true;
        inAuction=false;    
    }
    
    /**
     * Cancells an auctionable in auction
     * @throws StateTransitionException
     */
    @Override public  void cancelled()throws StateTransitionException{
       if(!isInAuction()) throw new StateTransitionException("You are not allowed to do that.");    
        inAuction=false;    
    }
    
}
