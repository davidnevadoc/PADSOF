/**
* @author Jorge Arellano &lt;jorge.arellano@estudiante.uam.es&gt;
* @author David Nevado &lt;david.nevadoc@estudiante.uam.es&gt;
*
* This class implements the antiquary shop
*/
package antiquary;

import java.util.*;
import item.*;
import report.Record;
import report.Report;
import serializers.Deserializer;
import serializers.Serializer;
import user.*;
import exceptions.*;
import java.io.*;
import Utilities.*;
import auction.Auction;
import customer.Customer;

public class Antiquary{
    /**
    * The employees accounts 
    */
    private ArrayList<Employee> employees = new ArrayList<>();
    /**
    * The manager
    */
    private Manager manager;
    /**
     * The stock of items
     */
    private ArrayList<Item> stock = new ArrayList<>();
    /**
     * The antiquary ongoing auctions
     */
    private ArrayList<Auction> auctions = new ArrayList<Auction>();
    /**
     * The antiquary customers
     */
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    /**
     * The antiquary sales and auction report
     */
    private Report report;
    /**
     * The text file where I read/save items
     */
    private static final String itemFile= Configuration.getItemsFile(); 
    /**
     * The text file where I read/save employees and the manager
     */
    private static final String employeeFile = Configuration.getUsersFile();
    private static final String auctionsFile = Configuration.getAuctionsFile();
    private static final String customersFile = Configuration.getCustomersFile();
    private static final String reportFile = Configuration.getReportFile();
    
    
    /**
     * Constructor
     * @param manager manager of the antiquary
     * @param users the employees of the shop
     */
    protected Antiquary(Manager manager, ArrayList<Employee> users) {
            super();
            this.manager=manager;
            this.employees= users;
    }
	/**
     * Autentifies an account seeing if the username and the password are in the database (and correspond to the same user)
     * @param username name
     * @param password password
     * @return true if login correctly, false if not
     */
    public boolean login (String username, String password){
            if((manager.getName().equals(username) && manager.getPassword().equals(password))==true){
                    return true;
            }
            Iterator<Employee> iterator = employees.iterator();
            while(iterator.hasNext()){ /*search in employee array*/
                    Employee employee = iterator.next();
                    if((employee.getName().equals(username) && employee.getPassword().equals(password))==true){
                            return true;
                    }
            }
            return false;
    }

	/**
     * Adds user to the database (an employee)
     * @param employee employee added
     * @throws AddingFailureException
     */
    public void addUser(Employee employee) throws AddingFailureException{ 
            if(employees.contains(employee)) throw new AddingFailureException("The user is already in the database"); /*check there are not two identic employees*/
            this.employees.add(employee); 
    }
	
    /**
     * @return the list of employees
     */
    public ArrayList<Employee> getEmployees() {
            return employees;
    }
	
    /**
     * Getter
     * @return the manager
     */
    public Manager getManager() {
            return manager;
    }
    /**
     * Setter
     * @param manager 
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }
    /**
     * Removes an employee account from the database
     * @param employee employee that will be removed
     */
    public void removeUser(Employee employee){
            employees.remove(employee);
    }

    /**
     * Adds an item to the stock
     * @param item the item to be added
     */
    public void addItem(Item item){
            int idmax=0;
            int aux;
            if(item.getId()<0){ /*If the item does not have an id yet*/
                for(Item it: stock){
                    aux=it.getId();
                    if(aux>idmax) idmax=aux;
                }
                idmax++;
                item.setId(idmax);
            }
            stock.add(item);
    }
    
    /**
     * Loads all the items in the database to the antiquary
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadItems() throws FileNotFoundException, IOException{
        stock=ObjectFileReader.readItems(itemFile);
    }
    
    /**
     * Loads all the items from another source
     * @param file another file to load
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadItems(String file) throws FileNotFoundException, IOException{
        stock=ObjectFileReader.readItems(file);
    }
    /**
     * Saves all the items in the antiquary database
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveItems() throws FileNotFoundException, IOException{
        ObjectFileReader.saveItems(itemFile,stock);
    }
    /**
     * Saves all the items in another file
     * @param file another file to load
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveItems(String file) throws FileNotFoundException, IOException{
        ObjectFileReader.saveItems(file,stock);
    }
    
    /**
     * Adds items to the the database from a source
     * @param file a file to add items
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void addItemsFromFile(String file) throws FileNotFoundException, IOException{
        ArrayList<Item> items=ObjectFileReader.readItems(file);
        for(Item it: items) addItem(it);
    }
    
    /**
     * returns an item with the id given
     * @param id the id of the item that we ust return
     * @return the id of the item
     * @throws NotFoundException if item with the id not found
     */
    public Item getItemWithId(int id) throws NotFoundException{
        for(Item it:stock){
            if(it.getId()==id) return it;
        }
        throw new NotFoundException("Item with id "+id+" not found");
    }

    /**
     * Getter
     * @return the stock 
     */
    public ArrayList<Item> getStock() {
        return stock;
    }
    
    /**
     * Searchs by description (search if text appers partially in the description)
     * @param text to search
     * @return the list of items
     */
    public ArrayList<Item> searchItemByDescription(String text){
        ArrayList<Item> list=new ArrayList<>();
        for(Item it: stock){
            if(it.getDescription().toLowerCase().contains(text.toLowerCase()))
                list.add(it);
        }
        return list;
    }
	
     /**
     * Loads all the employees in the database to the antiquary.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NotFoundException
     * @return the antiquary initialised with the stuff
     */
    @SuppressWarnings("unchecked")
	public static Antiquary loadUsers() throws FileNotFoundException, IOException, NotFoundException{       
        ArrayList<User> users=EmployeeFileReader.readEmployees(employeeFile);
        Manager manager = ((Manager)users.remove(0)); /*the first one in the list is the manager*/
        Antiquary shop = new Antiquary(manager,(ArrayList<Employee>)(ArrayList<?>)users);
        return shop;
    }
    
    /**
     * Loads all the users from another source
     * @param file another file to load
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NotFoundException
     * @return the antiquary initialised
     */
    @SuppressWarnings("unchecked")
	protected static Antiquary loadUsers(String file) throws FileNotFoundException, IOException, NotFoundException{
        ArrayList<User> users=EmployeeFileReader.readEmployees(file);
        Manager manager = ((Manager)users.remove(0)); /*the first one in the list is the manager*/
        Antiquary shop = new Antiquary(manager,(ArrayList<Employee>)(ArrayList<?>)users);
        return shop;
    }
    
    /**
     * Saves all the users in the antiquary database
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveUsers() throws FileNotFoundException, IOException{
        ArrayList<User> users=new ArrayList<>();
        users.add(manager);
        users.addAll(employees);
        EmployeeFileReader.saveEmployees(employeeFile,users);
    }
    /**
     * Saves all the users in a file
     * @param file another file to load
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveUsers(String file) throws FileNotFoundException, IOException{
        ArrayList<User> users=new ArrayList<>();
        users.add(manager);
        users.addAll(employees);
        EmployeeFileReader.saveEmployees(file,users);
    }
    
    /**
     * Adds users to the the database from a source
     * @param file a file to add items
     * @throws FileNotFoundException
     * @throws IOException
     * @throws AddingFailureException
     * @throws NotFoundException
     */
    public void addEmployeesFromFile(String file) throws FileNotFoundException, IOException, AddingFailureException, NotFoundException{
        ArrayList<Employee> emps=EmployeeFileReader.readEmployeesWithoutManager(file);
        for(Employee emp: emps){
            try{
                addUser(emp);
            }
            catch(AddingFailureException e){
                /*ignore*/
            }
        }
    }
    /**
     * Adds an auction to the antiquary
     * @param auction auction to be added
     * @throws AddingFailureException when the auction was already added
     */
    public void addAuction(Auction auction) throws AddingFailureException{
    	if(this.auctions.contains(auction)==false){
    		this.auctions.add(auction);
    	}else {
    		throw new AddingFailureException("Auction already added");
    	}
    }
    /**
     * Adds a customer to the antiquary
     * @param customer customer to be added
     * @throws AddingFailureException when the customer was already added
     */
    public void addCustomer(Customer customer) throws AddingFailureException{
    	if(this.customers.contains(customer)==false){
    		this.customers.add(customer);
    	} else {
    		throw new AddingFailureException("Customer already added");
    	}
    }
    /**
     * Adds a new record to the anitiquary report
     * @param record new record added
     */
    public void updateReport(Record record){
    	this.report.addRecord(record);
    }
    /**
     * Loads the antiquary auctions
     */
    protected void loadAuctions(){
    	Deserializer deserializer = new Deserializer();
    	this.auctions = deserializer.deserializeAuctions(auctionsFile);
    }
    /**
     * Loads the antiquary customers
     */
    protected void loadCustomers(){
    	Deserializer deserializer = new Deserializer();
    	this.customers = deserializer.deserializeCustomers(customersFile);
    }
    /**
     * Loads the antiquary report
     */
    protected void loadReport(){
    	Deserializer deserializer = new Deserializer();
    	this.report = deserializer.deserializeReport(reportFile);
    }
    /**
     * Saves the antiquary auctions
     */
    protected void saveAuctions(){
    	Serializer serializer = new Serializer();
    	serializer.serializeAuctions(this.auctions, auctionsFile);
    }
    /**
     * Saves the antiquary customers
     */
    protected void saveCustomers(){
    	Serializer serializer = new Serializer();
    	serializer.serializeCustomers(this.customers, customersFile);
    }
    /**
     * Saves the antiquary report
     */
    protected void saveReport(){
    	Serializer serializer = new Serializer();
    	serializer.serializeReport(this.report, reportFile);
    }
    /**
     * Report getter
     * @return Report of the antiquary
     */
	public Report getReport(){
		return this.report;
	}
	/**
	 * Auctions getter
	 * @return Auctions of the antiquary
	 */
	public ArrayList<Auction> getAuctions(){
		return this.auctions;
	}
	/**
	 * Customers getter
	 * @return Customers of the antiquary
	 */
	public ArrayList<Customer> getCustomers(){
		return this.customers;
	}
	/**
	 * Loads the antiquary
	 * @return The antiquary
	 * @throws FileNotFoundException when a file where the information
	 * was saved is not found
	 * @throws IOException when a problem with the loading occurs
	 * @throws NotFoundException when there is information missing
	 */
	public static Antiquary loadAntiquary() throws FileNotFoundException, IOException, NotFoundException{
		Antiquary antiquary = Antiquary.loadUsers();
		antiquary.loadAuctions();
		antiquary.loadCustomers();
		antiquary.loadReport();
		antiquary.loadItems();
		return antiquary;
	}
	/**
	 * Closes the antiquary and saves all the information
	 * @throws FileNotFoundException when a file where the information
	 * is stored is not found 
	 * @throws IOException when a problem with the saving occurs
	 */
	public void closeAntiquary() throws FileNotFoundException, IOException{
		this.saveAuctions();
		this.saveCustomers();
		this.saveItems();
		this.saveReport();
		this.saveUsers();
	}
	
	

	
	
}
