package user;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Checks wether the employee class works well
 */
public class EmployeeTest {
       
    Employee employee1;
    Employee employee2;
    Employee employee3;

        
    /**
     * Creates some employees
     */
    @Before
    public void setUp() {
        employee1 = new Employee("juanjo", "1234");
        employee2 = new Employee("juanjo", "password1");
        employee3 = new Employee("pepe", "password1");

    }
    
    /**
     * Checks the method equals
     */
    @Test
    public void Test1() {
        boolean result1 = employee1.equals(employee2);
        boolean result2 = employee2.equals(employee1);

        assertTrue(result1);
        assertTrue(result2);

    }
    
    /**
     * Checks the method equals
     */    
    @Test
    public void Test2() {
        boolean result1 = employee2.equals(employee3);
        boolean result2 = employee3.equals(employee2);

        assertFalse(result1);
        assertFalse(result2);

    }
    

}
