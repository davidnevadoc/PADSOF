package item;

import Utilities.Utilities;
import exceptions.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.text.ParseException;

/**
 * This class implements an item abstractly
 */

public abstract class Item implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7178253764477213089L;
	/**
     * id of the item. Only has sense if member of the shop
     */
    protected int id;
    /**
     * description of the object
     */
    protected String description;
    /**
     * Year the item was manufactured. May be incomplete.
     */
    protected String manufacturingYear;
    /**
     * Date the shop bought the item
     */
    protected Date acquisitionDate;
    /**
     * Price for which the shop bought the item
     */
    protected double acquisitionPrice;
    /**
     * Target pricee to sell the item
     */
    protected double targetPrice;

    /**
     * Marks if the item is sold
     */
    protected ItemState state = ItemState.Available;

    /**
     * Constructor
     * @param description
     * @param manufacturingDate
     * @param acquisitionDate
     * @param acquisitionPrice
     * @param targetPrice
     * @throws ParseException is error while parsing
     */
    protected Item(String description, String manufacturingDate, String acquisitionDate, double acquisitionPrice,
            double targetPrice) throws ParseException{
        this.id = -1;
        this.description = description;
        this.manufacturingYear=manufacturingDate;
        this.targetPrice = Math.abs(targetPrice);
        this.acquisitionPrice= Math.abs(acquisitionPrice);
        this.acquisitionDate=Utilities.stringToDate(acquisitionDate);
   
    }

    /**
     * Getter
     * @return the id
     */
    public int getId() {
            return id;
    }

    /**
     * Setter
     * @param id the id to set
     */
    public void setId(int id) {
            this.id = id;
    }

    /**
     * Checks if the item is available
     * @return true if available, false if not
     */
    public boolean isAvailable(){
            return state.equals(ItemState.Available);
    }

    /**
     * Getter
     * @return the item state
     */
    public ItemState getItemState(){
        return state;
    }
    
    /**
     * Set an object state to broken
     * @throws StateTransitionException 
     */
    public void setBroken() throws StateTransitionException{
        if (!isAvailable()) throw new StateTransitionException("The object is not available.\n");
        state=ItemState.Broken;
    }
    /**
     * Sets the item state to in a lot
     * @throws StateTransitionException
    */
    public void toLot()throws StateTransitionException{
        if (!isAvailable()) throw new StateTransitionException("The object is not available.\n");
        state=ItemState.InLot;
    }

   /**
     * Sets the item state to in a lot
     * @throws StateTransitionException
    */
    public void sell() throws StateTransitionException{
        if (!isAvailable()) throw new StateTransitionException("The object is not available.\n");
        state=ItemState.Sold;
    }

    /**
     * Resets the item to the initial state
     */
    public void reset(){
            state=ItemState.Available;
    }
    
    /**
     * Setter
     * @param state the new state (does not tak into account the allowed state transitions)
     */
    public void setState(ItemState state){
        this.state=state;
    }
    
    /**
     * Getter
     * @return 0 (will be overwritten if needed) 
     */
    public double getPercentageDiscount() {
        return 0;
    }
    
    /**
     * Prints an item in file format
     * @return String with the item printed
     */
    public abstract String printItem();
    
    /**
     * Overrides equals: two items are equal if they have the same id
     * @param o item I'm comparing to
     * @return true if they are equal, false if not
     */
    @Override public boolean equals(Object o){ 
        if (o==this) return true;
        if (!(o instanceof Item)) return false;
        Item obj = (Item)o;		
        return obj.id==id;
    }

    /**
     * Gets the hash code
     * @return the hash code
     */
    @Override public int hashCode(){
        return 101*id;
    }

    /**
     * To string metho
     * @return 
     */
    @Override public String toString(){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00",symbols); 
        return (description + ", " + df.format(targetPrice) + " Euros");
    }

    /**
     * Getter
     * @return description 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter
     * @return description
     */
    public double getTargetPrice() {
        return targetPrice;
    }
    /**
     * Calculates the delivery costs.
     * In a default item is 0
     * @return 0 
     */
	public double calculateDelivery() {

		return 0;
	}
        
        
        
}
