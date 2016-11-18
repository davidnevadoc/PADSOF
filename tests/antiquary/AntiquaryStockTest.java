package antiquary;

import item.*;
import java.util.*;
import exceptions.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import user.Employee;
import user.Manager;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Checks if the stock module of antiquary works well.
 * The methods save() and load() are untested since I test save(file) and load(file) that are exactly
 * the same but giving myself a file to load and save (if I test the others, the database of the antiquary
 * is changed everytime I test it and I do not want that to happen)
 */
public class AntiquaryStockTest {

    Manager manager;
    WorkOfArt woa;
    BigItem bitem;
    Antiquary shop;
        
    /**
     * Creates some employees and a manager and adds them to the shop
     * @throws AddingFailureException
     * @throws ParseException
      
     */
    @Before
    public void setUp() throws AddingFailureException, ParseException{

        manager = new Manager("mrManager", "yikKnGW");
        shop = new Antiquary(manager, new ArrayList<Employee>());
        bitem= new BigItem("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,32,86,175,50);
        woa = new WorkOfArt("Glass bottle (19th century) Hand holding a colt","18xx","21/12/2015",55.00,125.00,"French School",TypeOfWork.Sculpture,false);
    }
    
    /**
     * Tests if the shop adds items correctly without loading
     * @throws AddingFailureException
     * @throws NotFoundException
     */
    @Test
    public void Test1() throws AddingFailureException, NotFoundException {
        shop.addItem(bitem);
        shop.addItem(woa);
        assertEquals(woa.getId(),2);
        assertEquals(bitem.getId(),1); 
    }
    
    /**
     * Tests if the mtehod getItemWithId throws correctly exceptions
     * @throws AddingFailureException
     * @throws NotFoundException 
     */
    @Test(expected = NotFoundException.class)
    public void Test2()throws AddingFailureException, NotFoundException {
        shop.addItem(bitem);
        shop.addItem(woa);
        Item item=shop.getItemWithId(3);
    }    
    /**
     * Loads the items
     * @throws AddingFailureException
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void Test3() throws AddingFailureException, IOException, FileNotFoundException {
        shop.loadItems("itemsTestLoad.txt");
        ArrayList<Item> items=shop.getStock();
        assertEquals(items.size(),10);
        assertEquals(items.get(0).getItemState(),ItemState.Broken);
        assertTrue(items.get(3) instanceof WorkOfArt);

    }
    
    /**
     * Checks if the shop adds items correctly after loading objects
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException 
     */
    @Test
    public void Test4()throws AddingFailureException, IOException, FileNotFoundException {
        shop.loadItems("itemsTestLoad.txt");        
        shop.addItem(woa);
        shop.addItem(bitem);
        assertEquals(woa.getId(),11);
        assertEquals(bitem.getId(),12);
        assertEquals(shop.getStock().size(),12);

    
    }
    
    /**
     * Checks if the app does saving correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test5()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop.loadItems("itemsTestLoad.txt");        
        shop.addItem(woa);
        shop.addItem(bitem);
        shop.saveItems("itemsTestSave.txt");
        shop.loadItems("itemsTestSave.txt");        
        assertEquals(shop.getStock().size(),12);

    
    }
    
    /**
     * Checks if the app adds new items form a file correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test6()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop.loadItems("itemsTestLoad.txt");        
        shop.addItemsFromFile("itemsTestMoreItems.txt");       
        assertEquals(shop.getStock().size(),13);    
    }
    
    /**
     * Checks if the method addItemsFromFile throws exceptions correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test(expected=FileNotFoundException.class)
    public void Test7()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop.loadItems("itemsTestLoad.txt");        
        shop.addItemsFromFile("itemsTestMoreItemsyea");
    
    }
        
    /**
     * Checks if the method searchByDescription works correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test8()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop.loadItems("itemsTestLoad.txt"); 
        ArrayList<Item> search=shop.searchItemByDescription("Girl #");
        assertEquals(search.size(), 2);
        assertEquals(search.get(0).getDescription(),"Girl #1 in bronze");    
    }

    /**
     * Checks if the method searchByDescription works correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test9()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop.loadItems("itemsTestLoad.txt"); 
        ArrayList<Item> search=shop.searchItemByDescription("m");
        assertEquals(search.size(), 5);
        assertEquals(search.get(4).getDescription(),"Two small French table lamps");    
    }  
    
    /**
     * Checks if the method searchByDescription works correctly
     * @throws AddingFailureException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NotFoundException 
     */
    @Test
    public void Test10()throws AddingFailureException, IOException, FileNotFoundException, NotFoundException {
        shop.loadItems("itemsTestLoad.txt"); 
        ArrayList<Item> search=shop.searchItemByDescription("dubidu");
        assertEquals(search.size(), 0);
    }
    
}