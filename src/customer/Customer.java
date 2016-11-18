package customer;

import java.io.Serializable;
import item.*;
import es.uam.eps.padsof.emailconnection.EmailSystem;
import exceptions.InvalidArgumentsException;
/**
 * Customer of the antiquary
 * 
 * @author David Nevado Catalan
 * @author Jorge Arelaano Subias
 *
 */
public class Customer implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -224691744466778922L;
	/**
	 * First name
	 */
	protected String firstName;
	/**
	 * Last name
	 */
	protected String lastName;
	/**
	 * Client id
	 */
	protected String dni;
	/**
	 * E-mail address
	 */
	protected String email;
	/**
	 * Telephone or mobile phone number
	 */
	protected String phoneNumber;
	/**
	 * Notifications activated or not
	 */
	protected boolean notification;
	/**
	 * Constant for undefined phone number
	 */
	private static final String UNDEFINED = "undefined";
	/**
	 * Default state of the notification option
	 */
	private static final boolean DEFAULT = true;
	/**
	 * Customer constructor.
	 * Creates a customer given his/her full name, DNI,
	 * email address and phone number.
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param email Email address of the customer 
	 * @param phoneNumber Phone number of the customer
	 * @param notification Notification activation
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid
	 */
	public Customer(String firstName, String lastName, String dni,
			String email, String phoneNumber, boolean notification)
					throws InvalidArgumentsException{
		if(dni.length()!=9 || EmailSystem.isValidEmailAddr(email)==false ){
			throw new InvalidArgumentsException();
		}
		this.firstName=firstName;
		this.lastName=lastName;
		this.dni=dni;
		this.email=email;
		this.phoneNumber=phoneNumber;
		this.notification=notification;
	}
	/**
	 * Customer constructor.
	 * Creates a customer given his/her full name, DNI,
	 * phone number and email address
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param phoneNumber Phone number of the customer
	 * @param email Email address of the customer
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid
	 * 
	 */
	public Customer(String firstName, String lastName, String dni,
			String phoneNumber, String email) throws InvalidArgumentsException{
		this(firstName,lastName,dni,email,phoneNumber, DEFAULT);

	}
	/**
	 * Customer constructor.
	 * Creates a customer given his/her full name, DNI
	 * and email address
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param email Email address of the customer 
	 * @param notification Notification activation
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid
	 */
	public Customer(String firstName, String lastName, String dni,
			String email, boolean notification) throws InvalidArgumentsException{
		this(firstName,lastName,dni,email,UNDEFINED, notification);

	}
	/**
	 * Customer constructor.
	 * Creates a customer given his/her full name, DNI
	 * and email address
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param dni DNI of the customer 
	 * @param email Email address of the customer 
	 * @throws InvalidArgumentsException when the dni or the
	 * email are not valid 
	 */
	public Customer(String firstName, String lastName, String dni,
			String email) throws InvalidArgumentsException{
		this(firstName,lastName,dni,email,UNDEFINED, DEFAULT);

	}
	/*Podriamos annadir un campo genero para saber si mandar
	 * sr o sra en los emails, o podriamos sudar*/
	/**
	 * Updates customer's email address.
	 * The new email address must be valid
	 * @param newMail Customer's new email.
	 * @throws InvalidArgumentsException if the email 
	 * address passed is not valid
	 */
	public void changeMail(String newMail) throws InvalidArgumentsException{
		if(EmailSystem.isValidEmailAddr(newMail)){
			this.email=newMail;
		} else {
			throw new InvalidArgumentsException();
		}
	}
	/**
	 * Updates customer's phone number.
	 * @param newPhone Customer's new phone number.
	 */
	public void changePhone(String newPhone){
		this.phoneNumber=newPhone;
	}
	/**
	 * Email getter
	 * @return Email address of the customer
	 */
	public String getEmail(){
		return this.email;
	}
	/**
	 * Dni getter
	 * @return DNI of the client
	 */
	public String getDni(){
		return this.dni;
	}
	/**
	 * Applies the appropriate discounts for a given item
	 * @param item Item to which the discount is applied
	 * @return Final price with discount
	 */
	public double applyDiscount(Item item){
		return item.getTargetPrice();
	}
	/**
	 * This method determines whether the customer is in 
	 * the mailing list or not.
	 * @return True if the customer is in the mailing list,
	 * false otherwise
	 */
	public boolean onMailingList(){
		return this.notification;
	}
	/**
	 * Notification setter
	 * @param notification new value of notification
	 */
	public void setNotification(boolean notification){
		this.notification=notification;
	}
	/**
	 * Compares two customers.
	 * Two customers are the same if the have the same 
	 * dni
	 * @param customer Customer with which we compare
	 * @return true if they are the same customer, false 
	 * otherwise
	 */
	@Override public boolean equals(Object customer){
		Customer customer2 = (Customer) customer;
		return this.dni.equals(customer2.getDni());
	}
	/**
	 * Prints the basic information of the customer
	 * @return Complete name of the customer
	 */
	@Override public String toString(){
		return this.firstName + " " + this.lastName;
	}
	
	public void increaseAmount(double amount) throws InvalidArgumentsException{
		/*Does nothing*/
	}
	

}
