package antiquary;

import item.*;
import exceptions.*;
import org.junit.Test;
import static org.junit.Assert.*;
import user.Employee;
import user.Manager;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Checks if the employees module of antiquary works well.
 * The methods save() and load() are untested since I test save(file) and load(file) that are exactly
 * the same but giving myself a file to load and save (if I test the others, the database of the antiquary
 * is changed everytime I test it and I do not want that to happen)
 */
public class AntiquaryEmployeesTest {

    Manager manager;
    WorkOfArt woa;
    BigItem bitem;
    Antiquary shop;
       
    /**
     * Loads the employees
     * @throws AddingFailureException
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NotFoundException
     */
    @Test
    public void Test1() throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop=Antiquary.loadUsers("employeesTestLoad.txt");
        assertEquals(shop.getEmployees().size(),4);
        assertEquals(shop.getManager().getName(),"neva");
        assertEquals(shop.getEmployees().get(3).getPassword(),"campelloesguay");
    }
    

    
    /**
     * Checks if the app does saving correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test2()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop=Antiquary.loadUsers("employeesTestLoad.txt");
        shop.setManager(new Manager("arellano","muvis"));
        shop.addUser(new Employee("mr chip","rrr"));
        shop.saveUsers("employeesTestSave.txt");
        shop=Antiquary.loadUsers("employeesTestSave.txt");       
        assertEquals(shop.getEmployees().size(),5);
        assertEquals(shop.getManager().getName(),"arellano");
        assertEquals(shop.getEmployees().get(4).getPassword(),"rrr");
    
    }
    
    /**
     * Checks if the app adds new employees form a file correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test3()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop=Antiquary.loadUsers("data2WithErrors.txt");        
        assertEquals(shop.getEmployees().size(),4);
        assertEquals(shop.getEmployees().get(0).getName(),"dionisio");
        assertEquals(shop.getEmployees().get(3).getPassword(),"dito");

    
    }
    
    /**
     * Checks if the method addUsersFromFile works correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test4()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop=Antiquary.loadUsers("employeesTestLoad.txt");   
        assertEquals(shop.getEmployees().size(),4);
        shop.addEmployeesFromFile("employeesTestMoreEmployees.txt");
        assertEquals(shop.getEmployees().size(),8);
        assertEquals(shop.getEmployees().get(4).getPassword(),"dito");
        assertEquals(shop.getEmployees().get(7).getName(),"triste");

    
    }
    
    /**
     * Checks if the method addUsersFromFile throws exceptions correctly correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test(expected=FileNotFoundException.class)
    public void Test5()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop=Antiquary.loadUsers("employeesTestLoad.txt");        
        shop.addEmployeesFromFile("idontexist.txt");
        assertEquals(shop.getEmployees().size(),8);
        assertEquals(shop.getEmployees().get(7).getPassword(),"dito");
        assertEquals(shop.getEmployees().get(7).getName(),"triste");

    
    }
    
        
}