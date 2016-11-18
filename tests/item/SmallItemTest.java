package item;


import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Tests the module item (constructors and availability)
 */
public class SmallItemTest {
    
    SmallItem sitem;
    BigItem bitem;
    WorkOfArt woa;
    /**
     * Incorrect array of strings to try small item constructor
     */
    String[] fieldsSerr={"S","African ethnic Wooden mask","0","11/05ee","2.00","100.00","10"};
    /**
     * Correct array of strings to try small item constructor
     */
    String[] fieldsSok={"S","Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015","55.00","125.00","0.05"};

    /**
     * Initialises correctly some items
     * @throws ParseException 
     */
    @Before
    public void setUp() throws ParseException{
        sitem = new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,0.05);
        
    }
    
    /**
     * Tests wether the SmallItem constructor works well.
     * @throws ParseException 
     */
    @Test
    public void test1() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        assertEquals(sitem.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(sitem.manufacturingYear.compareTo("18xx"),0);
        assertEquals(sitem.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(sitem.acquisitionPrice,55.00,0);
        assertEquals(sitem.targetPrice,125.00,0);
        assertEquals(sitem.percentageDiscount,0.05,0);

    }
    
    /**
     * Tests wether the other SmallItem constructor works well.
     * @throws ParseException 
     */
    @Test
    public void test2() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        sitem=new SmallItem(fieldsSok);       
        assertEquals(sitem.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(sitem.manufacturingYear.compareTo("18xx"),0);
        assertEquals(sitem.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(sitem.acquisitionPrice,55.00,0);
        assertEquals(sitem.targetPrice,125.00,0);
        assertEquals(sitem.percentageDiscount,0.05,0);

    }
    /**
     * Tests wether an item is well printed
     */
    @Test
    public void test3(){
        assertEquals(sitem.printItem(),"S;Glass bottle (19th century) Hand holding a colt;18xx;21/12/2015;55.00;125.00;.05;Available;-1\n");
    }
    /**
     * Tests if the constructor by Strings sends correctly exceptions (wrong arguments)
     * @throws ParseException 
     */
    @Test(expected=ParseException.class)
    public void test4() throws ParseException{
        sitem=new SmallItem(fieldsSerr);

    }
        /**
     * Tests wether the other small item constructor works well with all the fields
     * @throws ParseException 
     */
    @Test
    public void test5() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String[] array={"S","Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015","55.00","125.00","5","Available","3"};
        sitem=new SmallItem(array);
        
        assertEquals(sitem.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(sitem.manufacturingYear.compareTo("18xx"),0);
        assertEquals(sitem.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(sitem.acquisitionPrice,55.00,0);
        assertEquals(sitem.targetPrice,125.00,0);
        assertEquals(sitem.state,ItemState.Available);
        assertEquals(sitem.id,3);
    } 
}