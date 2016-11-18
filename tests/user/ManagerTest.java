package user;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Checks wether the manager class works well
 */
public class ManagerTest {
       
    Manager manager;

        
    /**
     * Creates a manager
     */
    @Before
    public void setUp() {
        manager = new Manager("Mr","manager");

    }
    
    /**
     * Checks the constructor and getters
     */
    @Test
    public void Test1() {
        assertEquals(manager.getName(),"Mr");
        assertEquals(manager.getPassword(),"manager");
    }

}
