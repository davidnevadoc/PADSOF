package customer;

import contract.*;
import exceptions.InvalidArgumentsException;
import item.*;

public class ContractCustomer extends Customer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1758306447268213474L;
	/**
	 * Customers contract.
	 */
	private Contract contract;
	/**
	 * Contract Customer constructor.
	 * Creates a customer with a contract.
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param email Email address of the customer 
	 * @param phoneNumber Phone number of the customer
	 * @param contract Contract of the customer
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid
	 */
	public ContractCustomer(String firstName, String lastName, String dni,
			String email, String phoneNumber, Contract contract) throws InvalidArgumentsException{
		super(firstName, lastName, dni, email, phoneNumber);
		this.contract=contract;
	}
	/**
	 * ContractCustomer constructor.
	 * Creates a customer with a contract.
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param email Email address of the customer 
	 * @param contract Contract of the customer
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid
	 */
	public ContractCustomer(String firstName, String lastName, String dni,
			String email, Contract contract) throws InvalidArgumentsException{
		super(firstName, lastName, dni, email);
		this.contract=contract;
	}
	/**
	 * Contract Customer constructor.
	 * Creates a customer with a contract.
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param email Email address of the customer 
	 * @param phoneNumber Phone number of the customer
	 * @param contract Contract of the customer
	 * @param notification Notification activation
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid
	 */
	public ContractCustomer(String firstName, String lastName, String dni,
			String email, String phoneNumber, boolean notification, Contract contract) throws InvalidArgumentsException{
		super(firstName, lastName, dni, email, phoneNumber,notification);
		this.contract=contract;
	}
	/**
	 * ContractCustomer constructor.
	 * Creates a customer with a contract.
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param email Email address of the customer 
	 * @param contract Contract of the customer
	 * @param notification Notification activation
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid
	 */
	public ContractCustomer(String firstName, String lastName, String dni,
			String email, boolean notification, Contract contract) throws InvalidArgumentsException{
		super(firstName, lastName, dni, email, notification);
		this.contract=contract;
	}
	/**
	 * ContractCustomer constructor.
	 * Creates a customer with a contract.
	 * 
	 * @param customer The  customer that will be upgraded
	 * @param contract Contract the client will get
	 * @throws InvalidArgumentsException when invalid arguments are received
	 */
	public ContractCustomer(Customer customer, Contract contract) throws InvalidArgumentsException{
		this(customer.firstName, customer.lastName, customer.dni, customer.email,customer.phoneNumber, customer.notification, contract);
	}
	/**
	 * Updates customer's contract.
	 * The customer will permanently lose his previous contract
	 * @param newContract Customer's new contract
	 */
	public void newContract(Contract newContract){
		this.contract=newContract;
	}
	/**
	 * Returns the price of an item when its discount has been applied
	 * @param item Item which discount price will be returned
	 * @return final price of the object 
	 */
	@Override public double applyDiscount(Item item){
		return this.contract.applyDiscount(item);
			
		}
	@Override public void increaseAmount(double amount) throws InvalidArgumentsException{
		this.contract.increaseAmount(amount);
	}
}
