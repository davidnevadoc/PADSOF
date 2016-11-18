package item;
import java.util.*;
import exceptions.*;

/**
 * This class implements a lot of items.
 */
public class Lot extends Auctionable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7034245193764238475L;
	/**
     * Items that are in the lot
     */
    private ArrayList<Item> items;
    /**
     * The creation date of the lot
     */
    protected Date creationDate;

    /**
     * Constructor. Sets the creation date to the date this method was invoked
     */
    public Lot() {
        this.items = new ArrayList<>();
        this.creationDate = new Date();
    }
    
    /**
     * Adds if possible the item to the lot
     * @param it item to be added
     * @throws StateTransitionException thrown if the objects is not available
     */
    public void addItem(Item it) throws StateTransitionException{
            it.toLot();
            items.add(it);
    }
    
    /**
     * Dissolves a lot
     * @return an arraylist with the items
     * @throws StateTransitionException when the lot is  in an auction
     */
    public ArrayList<Item> dissolveLot() throws StateTransitionException{
        ArrayList<Item> aux=new ArrayList<>();
        if(this.isInAuction()){
        	throw new StateTransitionException("Lot in auction, cannot  be disolved");
        } 
        while(!items.isEmpty()){
            Item item=items.get(0);
            items.remove(item);
            item.reset(); /*now it is available*/
            aux.add(item);
        }
        return aux;
    }

    /**
     * Getter
     * @return the items in the lot
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    

    
    
    
}
