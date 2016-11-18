package item;


import exceptions.StateTransitionException;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Tests the module item (constructors and availability)
 */
public class ItemTest {
    
    SmallItem sitem;
    
    /**
     * Initialises correctly an item, for example a small one
     * @throws ParseException 
     */
    @Before
    public void setUp() throws ParseException{
        sitem = new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,5);
        
    }
     
    /**
     * Tests the availability of the item
     * @throws StateTransitionException
     */
    @Test
    public void test1()throws StateTransitionException{
            assertTrue(sitem.isAvailable());            
            sitem.toLot();
            assertFalse(sitem.isAvailable()); 
            sitem.reset();
            assertTrue(sitem.isAvailable());
            sitem.sell();
            assertFalse(sitem.isAvailable()); 
            sitem.reset();
            assertTrue(sitem.isAvailable());
            sitem.setBroken();
            assertFalse(sitem.isAvailable()); 
            sitem.reset();
            assertTrue(sitem.isAvailable());
    }
    
    /**
     * Tests if the method toLot throws correctly exceptions 
     * @throws StateTransitionException
     */
    @Test (expected=StateTransitionException.class)
    public void test2()throws StateTransitionException{
            sitem.sell();
            sitem.toLot();
    }
    
    /**
     * Tests if the method sell throws correctly exceptions 
     * @throws StateTransitionException
     */
    @Test (expected=StateTransitionException.class)
    public void test3()throws StateTransitionException{
            sitem.toLot();
            sitem.sell();
    }

    
    /**
     * Tests if the method setBroken throws correctly exceptions 
     * @throws StateTransitionException
     */
    @Test (expected=StateTransitionException.class)
    public void test4()throws StateTransitionException{
            sitem.sell();
            sitem.setBroken();
    }
    
    /**
     * Tests toString method
     */
    @Test
    public void test5(){
        assertEquals(sitem.toString(),"Glass bottle (19th century) Hand holding a colt, 125.00 Euros");
    }
}
