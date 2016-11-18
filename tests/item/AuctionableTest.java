
package item;

import exceptions.StateTransitionException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Checks the class auctionable
 */
public class AuctionableTest {
    
    Auctionable auctionable;
    
    /**
     * Create an auctionable
     */
    @Before
    public void setUp() {
        auctionable = new Auctionable();
    }
    
    /**
     * See if the auctionable is well initialised
     */
    @Test
    public void test1() {
        assertFalse(auctionable.isInAuction());
        assertFalse(auctionable.sold);
    }
    
    /**
     * See if the transition of states works well
     * @throws StateTransitionException 
     */
    @Test
    public void test2() throws StateTransitionException{
        auctionable.toAuction();
        assertTrue(auctionable.isInAuction());
        auctionable.cancelled();
        assertFalse(auctionable.isInAuction());        
       
    }
    
    /**
     * See if state exceptions are well thrown
     * @throws StateTransitionException 
     */
    @Test(expected=StateTransitionException.class)
    public void test3() throws StateTransitionException{
        auctionable.sold();
    }
    
    /**
     * See if state exceptions are well thrown
     * @throws StateTransitionException 
     */
    @Test(expected=StateTransitionException.class)
    public void test4() throws StateTransitionException{
        auctionable.toAuction();        
        auctionable.sold();
        auctionable.cancelled();
        
    }
    
    /**
     * 
     * @throws StateTransitionException 
     */
    @Test
    public void test5() throws StateTransitionException{
        auctionable.toAuction();
        assertFalse(auctionable.sold);
        auctionable.sold();
        assertTrue(auctionable.sold);        
       
    }

       
}
