
package item;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * This class tests the item file reader
 */
public class ObjectFileReaderTest {
    
    ArrayList<Item> items = null;
    
    /**
     * Tries a well formated file, and checks some random values.
     * @throws IOException 
     */
    @Test
    public void test1() throws IOException {
        
            items = ObjectFileReader.readItems("data.txt");            
            assertEquals(items.size(),10);
            assertEquals(items.get(0).description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
            assertTrue(items.get(3) instanceof WorkOfArt);
            assertTrue(((WorkOfArt)items.get(4)).authorCertificate);  
            assertEquals(items.get(7).manufacturingYear.compareTo("198x"),0);
            assertEquals(((SmallItem)items.get(8)).percentageDiscount,0.10,0);
            assertEquals(4f, ((BigItem)items.get(9)).weight,50);

    }
    
    /**
     * Tries if an exception is thrown if a file that does not exists is tried to be read
     * @throws IOException
     */
    @Test (expected=FileNotFoundException.class)
    public void test2() throws IOException {

        items = ObjectFileReader.readItems("dataa.txt");            
        assertEquals(items.size(),10);
        assertEquals(items.get(0).description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertTrue(items.get(3) instanceof WorkOfArt);
        assertTrue(((WorkOfArt)items.get(4)).authorCertificate);  
        assertEquals(items.get(7).manufacturingYear.compareTo("198x"),0);
        assertEquals(4f, ((BigItem)items.get(9)).weight,50);

    }
    
    /**
     * Tests wether a file can be read if there are some lines wrongly formatted.
     * @throws IOException 
     */
    @Test
    public void test3() throws IOException {
        
        items = ObjectFileReader.readItems("dataWithFormatErrors.txt");          
        assertEquals(items.size(),2);
        assertEquals(items.get(1).description.compareTo("Old polychromatic altar from Sonora (Arizona)"),0);

    }
    
    /**
     * Tests wether a file can be read if there are some lines wrongly formatted.
     * @throws IOException 
     */
    @Test
    public void test4() throws IOException {
        
        items = ObjectFileReader.readItems("dataWithWrongNumberOfArguments.txt");          
        assertEquals(items.size(),3);
        assertEquals(4f,items.get(2).targetPrice,120.00);

    }    
    /**
     * Tests wether a file can be read if there are some lines with random errors.
     * @throws IOException 
     */
    @Test 
    public void test5() throws IOException {

        items = ObjectFileReader.readItems("dataWithErrors.txt");            
        assertEquals(items.size(),5);
        assertEquals(items.get(0).description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
        assertTrue(items.get(3) instanceof WorkOfArt);
        assertEquals(4f, ((BigItem)items.get(4)).weight,50);

    }
    
        /**
     * Tries to save a well formated file, then load it.
     * @throws IOException 
     */
    @Test
    public void test6() throws IOException {
        
            items = ObjectFileReader.readItems("data.txt"); 
            ObjectFileReader.saveItems("save.txt",items);
            items = ObjectFileReader.readItems("save.txt"); 
            assertEquals(items.size(),10);
            assertEquals(items.get(0).description.compareTo("Glass bottle (19th century) Hand holding a colt"),0);
            assertTrue(items.get(3) instanceof WorkOfArt);
            assertTrue(((WorkOfArt)items.get(4)).authorCertificate);  
            assertEquals(items.get(7).manufacturingYear.compareTo("198x"),0);
            assertEquals(4f, ((BigItem)items.get(9)).weight,50);

    }
    
    
    
}
