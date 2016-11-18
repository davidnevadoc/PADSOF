package antiquary;

import java.util.*;
import exceptions.AddingFailureException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import user.Employee;
import user.Manager;

/**
 * Checks if th log in management works well, and the adding of employees to the shop
 */
public class AntiquaryLogInTest {
       
    Employee employee1;
    Employee employee2;
    Employee employee3;    
    Manager manager;
    Antiquary shop;
    ArrayList<Employee> employees;
        
    /**
     * Creates some employees and a manager and adds them to the shop
     * @throws AddingFailureException 
     */
    @Before
    public void setUp() throws AddingFailureException{
        employee1 = new Employee("juanjo", "1234");
        employee2 = new Employee("pepe02", "password1");
        employee3 = new Employee("neva", "dito");
        manager = new Manager("are","muvis");
        employees= new ArrayList<>();

        shop= new Antiquary(manager,employees); /*has two employees, are(manager) and neva*/
        shop.addUser(employee1);
        shop.addUser(employee2);
        shop.addUser(employee3);

    }
    
    /**
     * See if the app adds correcctly users
     * @throws AddingFailureException
     */
    @Test
    public void Test1() throws AddingFailureException {
        Employee employee4 = new Employee("Juanjo", "1234"); /*not repeated!*/
        
        shop.addUser(employee4);
        
        String[] names={ "juanjo", "pepe02", "neva","Juanjo"};
        
        int i=0;
        
        for(Employee emp : shop.getEmployees()){
            assertEquals(emp.getName(),names[i++]);
        }
        assertEquals(shop.getManager().getName(),"are");
    }
    
    /**
     * Sees if the app rejects an already added user
     * @throws AddingFailureException
     */
    @Test (expected= AddingFailureException.class)
    public void Test11() throws AddingFailureException{
        shop.addUser(employee2);
    }
    
    /**
     * See if the app adds correcctly rejects a repeated user
     * @throws AddingFailureException
     */
    @Test (expected= AddingFailureException.class)
    public void Test12() throws AddingFailureException{
        Employee employee4 = new Employee("juanjo", "strstr");/*repeated*/
        shop.addUser(employee4);
    }
    
    
    /**
     * This test consist of adding 100 users (see if the app can support it)
     * @throws AddingFailureException
     */
    @Test
    public void Test2() throws AddingFailureException{
        
        String name;
        for(int i=0; i<100; i++){
            name = ("employee" + i);
            employee1 = new Employee(name,"1234");
            shop.addUser(employee1);
        }
        assertEquals(shop.getEmployees().get(102).getName(),"employee99");
    }
    
    /**
     * tests if the log in works
     */
    @Test
    public void Test3() {
        
        assertTrue(shop.login(employee1.getName(),employee1.getPassword()));
        assertTrue(shop.login(shop.getManager().getName(),shop.getManager().getPassword()));
        assertFalse(shop.login(employee1.getName(),employee2.getPassword()));
        
    }
    
    /**
     * tests if the log in works when modifying accounts
     */
    @Test
    public void Test4() {
        
        String old=employee1.getPassword();
        employee1.setPassword("new");
        assertTrue(shop.login(employee1.getName(),"new"));
        assertFalse(shop.login(employee1.getName(),old));
        
        		shop.removeUser(employee1);

        assertFalse(shop.login(employee1.getName(),employee2.getPassword()));
        
    }
    
    /**
     * tests if the log in works when erasing accounts
     */
    @Test
    public void Test5() {
        
        shop.removeUser(employee1);

        assertFalse(shop.login(employee1.getName(),employee1.getPassword()));
        
    }
}
