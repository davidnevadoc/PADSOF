package item;

import exceptions.StateTransitionException;

/**
 * Interface of the item (needed to the multiple inheritance of WorkOfArt.
 * All methods detailed in class Item
 */
public interface ItemInterface {
	

    public int getId();
    public void setId(int id);
    public boolean isAvailable();
    public void setBroken()throws StateTransitionException;
    public void toLot()throws StateTransitionException;
    public void sell() throws StateTransitionException;
    public void reset();
    public String printItem();
}
