package user;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Checks wether the user class works well
 */
public class UserTest {
       
    User user1;
    User user2;

        
    /**
     * Creates two users
     */
    @Before
    public void setUp() {
        user1 = new Manager("Mr","manager");
        user2 = new Employee("neva","dito");        

    }
    
    /**
     * Checks the constructor
     */
    @Test
    public void test1() {
        assertEquals(user1.getName(),"Mr");
        assertEquals(user2.getPassword(),"dito");
    }

    
    /**
     * Checks the getters and setter
     */
    @Test
    public void test2() {
        user1.setName("Manolo");
        user2.setPassword("dubidu");
        
        assertEquals(user1.getName(),"Manolo");
        assertEquals(user2.getPassword(),"dubidu");
    }
    
    /**
     * Checks the print user
     */
    @Test
    public void test3(){
        assertEquals(user1.printUser(),"Mr;manager\n");
        assertEquals(user2.printUser(),"neva;dito\n");
        
    }
}
