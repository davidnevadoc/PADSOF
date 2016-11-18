
package item;

import java.util.*;
import exceptions.*;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Utilities.*;

/**
 * This class tests the class Lot
 */
public class LotTest {
    
    SmallItem sitem;
    BigItem bitem;
    WorkOfArt woa;
    Lot lot1;
    Lot lot2;
    
    /**
     * Initialises two lots with items in them
     * @throws ParseException
     * @throws StateTransitionException 
     */
    @Before
    public void setUp() throws ParseException, StateTransitionException{
        sitem = new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
        bitem= new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,86,175,50);
        woa = new WorkOfArt("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,"French School",TypeOfWork.Sculpture,false);
        lot1= new Lot();
        lot2= new Lot();
        lot1.addItem(sitem);
        lot1.addItem(bitem);
        lot2.addItem(woa);

    }
    
    /**
     * See if the elements are correctly added
     */
    @Test
    public void test1() {
        assertEquals(lot1.getItems().size(),2);
        assertEquals(lot2.getItems().size(),1);
        assertFalse(lot1.getItems().get(1).isAvailable());
        assertEquals(lot2.getItems().get(0).getItemState(),ItemState.InLot);        
    }
    
     /**
     * See that I can add an item with the same charracteristics, but not exactly the same item
     * @throws ParseException
     * @throws StateTransitionException
     */
    @Test
    public void test2() throws ParseException, StateTransitionException{
        /*exactly the same as before*/
        SmallItem sitembis=new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
        lot1.addItem(sitembis);       
    } 
    
    /**
     * See that I can cannot add an already added item
     * @throws ParseException
     * @throws StateTransitionException
     */
    @Test (expected=StateTransitionException.class)
    public void test3() throws ParseException, StateTransitionException{
        /*exactly the same as before*/
        SmallItem sitembis=new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
        lot1.addItem(sitembis);
        lot1.addItem(sitembis);       
    } 
    
    /**
     * See that I can cannot add an item not available
     * @throws ParseException
     * @throws StateTransitionException
     */
    @Test (expected=StateTransitionException.class)
    public void test4() throws ParseException, StateTransitionException{
        /*exactly the same as before*/
        SmallItem sitembis=new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
        lot1.addItem(sitembis);
        lot2.addItem(sitembis);       
    }
    
    /**
     * See that I can cannot add an item not available
     * @throws ParseException
     * @throws StateTransitionException
     */
    @Test (expected=StateTransitionException.class)
    public void test5() throws ParseException, StateTransitionException{
        /*exactly the same as before*/
        SmallItem sitembis=new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
        sitembis.setBroken();
        lot1.addItem(sitembis);
    }  
    
    /**
     * See that I can disolve correctly a lot
     * @throws StateTransitionException 
     */
    
    @Test
    public void test6() throws StateTransitionException{
        
        ArrayList<Item> aux=lot1.dissolveLot();
        assertEquals(lot1.getItems().size(),0);
        assertTrue(aux.get(0).isAvailable());
    }
    
    /**
     * See if the date is well initilised
     * @throws ParseException
     */
    public void test7() throws ParseException{
        assertTrue(lot1.creationDate.before(new Date()));
        assertTrue(lot2.creationDate.after(Utilities.stringToDate("30/03/2016")));
    }
 

}
