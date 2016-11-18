package Utilities;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;


/**
 * Tests to try the configuration module
 */
public class ConfigurationTest {
    
    /**
     * Tries if to load macros and tests some values
     * @throws Exception
     */
    @Test
    public void test1() throws Exception{
        Configuration.loadMacros();
        assertEquals(Configuration.getIncrementPrice(),2,0);
        assertEquals(Configuration.getBasePrice(),0,0);
        assertEquals(Configuration.getUsersFile(),"employees.txt");            
    }
    
    /**
     * Try with the wrong file (the values are the ones by default)
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test 
    public void test2() throws IOException, FileNotFoundException{
        Configuration.fileMacros="idontexist.txt";
        Configuration.loadMacros();
        assertEquals(Configuration.getIncrementPrice(),2,0);
        assertEquals(Configuration.getBasePrice(),0,0);
        assertEquals(Configuration.getUsersFile(),"employees.txt");
    }
    
     /** 
      * Try with the wrongly formatted file
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test 
    public void test3() throws IOException, FileNotFoundException{
        Configuration.fileMacros="macrosWithErrors.txt";
        Configuration.loadMacros();
        assertEquals(Configuration.getIncrementPrice(),3,0);
        assertEquals(Configuration.getBasePrice(),0,0);
        assertEquals(Configuration.getUsersFile(),"usersfile.txt");
    }
}
