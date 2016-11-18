
package user;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import exceptions.*;


/**
 * This class tests the employee file reader
 */
public class EmployeeFileReaderTest {
    
    ArrayList<User> users = null;
    
    /**
     * Tries a well formated file, and checks some random values.
     * @throws IOException 
     * @throws FileNotFoundException
     * @throws NotFoundException
     */
    @Test
    public void test1() throws IOException, FileNotFoundException, NotFoundException {
        
            users = EmployeeFileReader.readEmployees("data2.txt");            
            assertEquals(users.size(),5);
            assertEquals(users.get(0).getName(),"neva");
            assertEquals(users.get(4).getPassword(),"campelloesguay");

    }
    
    /**
     * Tries if an exception is thrown if a file that does not exists is tried to be read
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException
     */
    @Test (expected=FileNotFoundException.class)
    public void test2() throws IOException , FileNotFoundException, NotFoundException {

        users = EmployeeFileReader.readEmployees("dataa.txt");            


    }
    
    
    /**
     * Tests wether a file can be read if there are some lines wrongly formatted.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException
     */
    @Test
    public void test3() throws IOException, FileNotFoundException, NotFoundException {
        
        users = EmployeeFileReader.readEmployees("data2WithErrors.txt");          
        assertEquals(users.size(),5);
        assertEquals(users.get(3).getPassword(),"campelloesguay");

    }    
    /**
     * Tests wether an exception is thrown if the file is empty
     * @throws IOException 
     * @throws FileNotFoundException
     * @throws NotFoundException
     */
    @Test (expected=NotFoundException.class)
    public void test4() throws IOException, FileNotFoundException, NotFoundException {

        users = EmployeeFileReader.readEmployees("empty.txt");            
        
    }
    
    /**
     * Tries to save a well formated file, then load it.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException
     */
    @Test
    public void test5() throws IOException , FileNotFoundException, NotFoundException{
        
            users = EmployeeFileReader.readEmployees("data2.txt"); 
            EmployeeFileReader.saveEmployees("save2.txt",users);
            users = EmployeeFileReader.readEmployees("save2.txt"); 
            assertEquals(users.size(),5);
            assertEquals(users.get(0).getName(),"neva");
            assertEquals(users.get(4).getPassword(),"campelloesguay");

    }
    
    /**
     * Tries readEmployeesWithoutManager
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException
     */
    @Test
    public void test6() throws IOException , FileNotFoundException, NotFoundException{
        
            users = EmployeeFileReader.readEmployees("data2.txt"); 
            ArrayList<Employee> users2=new ArrayList<>();
            users2 = EmployeeFileReader.readEmployeesWithoutManager("data2.txt");
            assertTrue(users2.get(0) instanceof Employee);
            int i=0;
            for(Employee emp: users2){
                assertEquals(emp.getName(),users.get(i).getName());
                assertEquals(emp.getPassword(),users.get(i).getPassword());
                i++;
            }
    }
    
    
    
    
    
}
