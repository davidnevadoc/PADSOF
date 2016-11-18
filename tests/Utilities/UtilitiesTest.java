package Utilities;

import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;


/**
 * Tests to try the utilities module
 */
public class UtilitiesTest {
    
    /**
     * Tries if stringToDate works when correct arguments
     * @throws Exception
     */
    @Test
    public void test1() throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String s1 = "21/02/2015";
        String s2 = "21-02-2015";
        assertEquals(Utilities.stringToDate(s1).compareTo(formato.parse(s2)),0);
    }
    
    /**
     * Try with the wrong format
     * @throws ParseException
     */
    @Test (expected = ParseException.class)
    public void test2() throws ParseException{
        String s1 = "21-02-2015";
        Utilities.stringToDate(s1);
    }
    
    /**
     * Try with wrong parameters format
     * @throws ParseException
     */
    @Test (expected = ParseException.class)
    public void test3() throws ParseException{
        String s1 = "11-05-2ejj13";
        Utilities.stringToDate(s1);
    }
    
    /**
     * Try with wrong parameters format
     * @throws ParseException
     */    
    @Test (expected = ParseException.class)
    public void test31() throws Exception{
        String s1 = "21//2015";
        Utilities.stringToDate(s1);
    }    
    
    /**
     * Try method YNToBoolean with correct parameters
     * @throws ParseException
     */
    @Test
    public void test4() throws ParseException{
        String s1 = "Y";
        String s2 = "N";
        assertTrue(Utilities.YNToBoolean(s1));
        assertFalse(Utilities.YNToBoolean(s2));
    }
    
    /**
     * Try YNToBoolean with wrong parameters
     * @throws ParseException
     */
    @Test (expected = ParseException.class)
    public void test5() throws Exception{
        String s1 = "y";
        assertTrue(Utilities.YNToBoolean(s1));
    }
    
    /**
     * Try YNToBoolean with wrong parameters
     * @throws ParseException
     */    @Test (expected = ParseException.class)
    public void test6() throws Exception{
        String s1 = "true";
        assertTrue(Utilities.YNToBoolean(s1));
    }
    
        
    /**
     * Try BooleanToYN
     */    @Test 
    public void test7(){
        Boolean a= true;
        Boolean b = false;
        assertEquals(Utilities.BooleanToYN(a),"Y");
        assertEquals(Utilities.BooleanToYN(b),"N");

    }
}
