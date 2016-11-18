
package item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Checks the class StandardItem
 */
public class StandardItemTest {
    
    
    /**
     * Tests wether the StandardItem constructor works well.
     * @throws ParseException 
     */
    @Test
    public void test1() throws ParseException{
        StandardItem stitem = new SmallItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,0.05);
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        assertEquals(stitem.description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertEquals(stitem.manufacturingYear.compareTo("18xx"),0);
        assertEquals(stitem.acquisitionDate.compareTo(format.parse("21/12/2015")),0);
        assertEquals(stitem.acquisitionPrice,55.00,0);
        assertEquals(stitem.targetPrice,125.00,0);

    }
    
}
