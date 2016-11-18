package item;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import exceptions.*;
/**
 * Tests the module item (constructors and availability)
 */
public class WorkOfArtTest {
    

    WorkOfArt woa;

    /**
     * Incorrect array of strings to try work of art constructor
     */    
    String[] fieldsAerr={"A","Girl #2 in bronze","19xx","10/8/2014","200.00","600.00","French School","sculpture","N"};
    /**
     * Correct array of strings to try work of art constructor
     */    
    String[] fieldsAok={"A","Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015","55.00","125.00","French School","Sculpture","N"};
   
    /**
     * Initialises correctly some items
     * @throws ParseException 
     */
    @Before
    public void setUp() throws ParseException{
        woa = new WorkOfArt("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,"French School",TypeOfWork.Sculpture,false);
        
    }
    

    /**
     * Tests wether the WOA constructor works well.
     * @throws ParseException 
     */
    @Test
    public void test1() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        assertEquals(woa.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(woa.manufacturingYear.compareTo("18xx"),0);
        assertEquals(woa.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(woa.acquisitionPrice,55.00,0);
        assertEquals(woa.targetPrice,125.00,0);
        assertEquals(woa.author,"French School");
        assertEquals(woa.type,TypeOfWork.Sculpture);
        assertFalse(woa.authorCertificate);


    }
    /**
     * Tests wether the other WOA constructor works well.
     * @throws ParseException 
     */
    @Test
    public void test2() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        woa=new WorkOfArt(fieldsAok);
        
        assertEquals(woa.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(woa.manufacturingYear.compareTo("18xx"),0);
        assertEquals(woa.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(woa.acquisitionPrice,55.00,0);
        assertEquals(woa.targetPrice,125.00,0);
        assertEquals(woa.author,"French School");
        assertEquals(woa.type,TypeOfWork.Sculpture);
        assertFalse(woa.authorCertificate);


    }
    
    /**
     * Tests wether an item is well printed
     */
    @Test
    public void test3(){
        assertEquals(woa.printItem(),"A;Glass bottle (19th century) Hand holding a colt;18xx;21/12/2015;55.00;125.00;French School;Sculpture;N;Available;-1\n");
    }
    /**
     * Tests if the constructor by Strings send correctly exceptions (wrong arguments)
     * @throws ParseException 
     */
    @Test(expected=IllegalArgumentException.class)
    public void test4() throws ParseException{
        woa=new WorkOfArt(fieldsAerr);

    }
    
    /**
     * Tests wether the other WOA constructor works well with all the fields
     * @throws ParseException 
     */
    @Test
    public void test5() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String[] array={"A","Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015","55.00","125.00","French School","Sculpture","N","Broken","3"};
        woa=new WorkOfArt(array);
        
        assertEquals(woa.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(woa.manufacturingYear.compareTo("18xx"),0);
        assertEquals(woa.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(woa.acquisitionPrice,55.00,0);
        assertEquals(woa.targetPrice,125.00,0);
        assertEquals(woa.author,"French School");
        assertEquals(woa.type,TypeOfWork.Sculpture);
        assertFalse(woa.authorCertificate);
        assertEquals(woa.state,ItemState.Broken);
        assertEquals(woa.id,3);
    }
    
    /**
     * Tests putting a woa to auction and takinng it out
     * @throws ParseException
     * @throws StateTransitionException 
     */
    @Test
    public void test6() throws ParseException, StateTransitionException{
        woa.toAuction();
        assertTrue(woa.isInAuction());
        woa.cancelled();
        assertFalse(woa.isInAuction());
        woa.toAuction();
        woa.sold();
        assertFalse(woa.isInAuction());
        
    }
    
    /**
     * Tests if the class throws well exceptions when putting woa to auction
     * @throws ParseException
     * @throws StateTransitionException 
     */
    @Test(expected=StateTransitionException.class)
    public void test7() throws ParseException, StateTransitionException{
        woa.toAuction();
        assertTrue(woa.isInAuction());
        woa.sell();        
        woa.cancelled();
        assertFalse(woa.isInAuction());
    }
    
    /**
     * Checks if the availability works well for woa
     * @throws StateTransitionException 
     */
    @Test
    public void test8() throws StateTransitionException{
        woa.setBroken();
        assertFalse(woa.isAvailable());
        woa.reset();
        assertTrue(woa.isAvailable());        
        woa.toAuction();
        assertFalse(woa.isAvailable());
        
    }
    
    /**
     * Checks if the availability works well for woa, and throws exceptions
     * @throws StateTransitionException 
     */
    @Test(expected=StateTransitionException.class)
    public void test9() throws StateTransitionException{
        woa.setBroken();         
        woa.toAuction();     
    }
}
