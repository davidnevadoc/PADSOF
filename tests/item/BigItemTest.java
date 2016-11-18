package item;


import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Tests the module item (constructors and availability)
 */
public class BigItemTest {
    
    BigItem bitem;
    
    /**
     * Incorrect array of strings to try big item constructor
     */    
    String[] fieldsBerr={"B","Stand in mahogany","198x","7/7/2013","50.00","100.00","12","11","45"};
    /**
     * Correct array of strings to try big item constructor
     */    
    String[] fieldsBok={"B","Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015","55.00","125.00","32","86","175","50"};
        
    /**
     * Initialises correctly an item
     * @throws ParseException 
     */
    @Before
    public void setUp() throws ParseException{
        bitem= new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,86,175,50);
        
    }
    
     /**
     * Tests wether the Big Item constructor works well.
     * @throws ParseException 
     */
    @Test
    public void test1() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        assertEquals(bitem.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(bitem.manufacturingYear.compareTo("18xx"),0);
        assertEquals(bitem.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(bitem.acquisitionPrice,55.00,0);
        assertEquals(bitem.targetPrice,125.00,0);
        assertEquals(bitem.length,32,0);
        assertEquals(bitem.width,86,0);
        assertEquals(bitem.height,175,0);
        assertEquals(bitem.weight,50,0);


    }
    /**
     * Tests wether the other Big Item constructor works well.
     * @throws ParseException 
     */
    @Test
    public void test2() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        bitem=new BigItem(fieldsBok);

        assertEquals(bitem.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(bitem.manufacturingYear.compareTo("18xx"),0);
        assertEquals(bitem.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(bitem.acquisitionPrice,55.00,0);
        assertEquals(bitem.targetPrice,125.00,0);
        assertEquals(bitem.length,32,0);
        assertEquals(bitem.width,86,0);
        assertEquals(bitem.height,175,0);
        assertEquals(bitem.weight,50,0);

    }
    /**
     * Tests wether an item is well printed
     */
    @Test
    public void test3(){
        assertEquals(bitem.printItem(),"B;Glass bottle (19th century) Hand holding a colt;18xx;21/12/2015;55.00;125.00;32.00;86.00;175.00;50.00;Available;-1\n");
    }
    /**
     * Tests if the constructor by Strings send correctly exceptions (wrong arguments)
     * @throws ParseException 
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void test4() throws ParseException{
        bitem=new BigItem(fieldsBerr);

    }
    /**
     * Tests wether the other Big item constructor works well with all the fields
     * @throws ParseException 
     */
    @Test
    public void test5() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String[] array={"B","Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015","55.00","125.00","32","86","175","50","Broken","3"};
        bitem=new BigItem(array);
        
        assertEquals(bitem.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(bitem.manufacturingYear.compareTo("18xx"),0);
        assertEquals(bitem.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(bitem.acquisitionPrice,55.00,0);
        assertEquals(bitem.targetPrice,125.00,0);
        assertEquals(bitem.state,ItemState.Broken);
        assertEquals(bitem.id,3);
    }  
    
    /**
     * Checks if the delivery cost is well implemented
     */
    @Test
    public void test6(){
        assertEquals(34,bitem.calculateDelivery(),0);
    }
    
    /**
     * Checks if the delivery cost is well implemented
     * @throws ParseException
     */
    @Test
    public void test7() throws ParseException{
        bitem= new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,226,175,50);        
        assertEquals(84,bitem.calculateDelivery(),0);
    }
    
    /**
     * Checks if the delivery cost is well implemented
     * @throws ParseException
     */
    @Test
    public void test8()throws ParseException{
        bitem= new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,126,175,15);                
        assertEquals(20,bitem.calculateDelivery(),0);
    } 
    
    /**
     * Checks if the delivery cost is well implemented
     * @throws ParseException
     */
    @Test
    public void test9()throws ParseException{
        bitem= new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,126,175,15.01);                
        assertEquals(22,bitem.calculateDelivery(),0);
    }    
   

}
