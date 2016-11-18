package auction;

import java.io.Serializable;
import item.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import auction.exceptions.InvalidAuctionAction;
import es.uam.eps.padsof.emailconnection.*;
import exceptions.*;
import customer.*;
/**
 * Auction of the antiquary.
 * 
 * @author David Nevado Catalan
 * @author Jorge Arellano Subias
 */
public class Auction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4136969686481942088L;
	/**
	 * Date when the first bid was placed
	 */
	protected LocalDate startDate;
	/**
	 * Duration of the auction since the start date in days
	 */
	protected int duration;
	/**
	 * Minimum amount in which a bid must be greater than
	 * the highest bid to be updated.
	 */
	private double minIncrement;
	/**
	 * Base price of the auction
	 */
	private double minPrice;
	/**
	 * Id of the auction
	 */
	private int  id;
	/**
	 * Current highest bid
	 */
	protected Bid highestBid;
	/**
	 * State of the auction: opened, running, cancelled or closed.
	 */
	protected State state;
	/**
	 * Item, WoA or lot that is being auctioned
	 */
	private Auctionable auctionable;
	/**
	 * Bids that have been made
	 */
	protected ArrayList<Bid> bids;
	/**
	 * Customers that will receive a notifications
	 */
	protected ArrayList<Customer> mailingList;
	/**
	 * Customers that have entered the auction both for
	 * free or paying the entrance fee
	 */
	protected ArrayList<ContractCustomer> participants;
	/**
	 * Unitialized date, 01/01/-9999
	 */
	private static final LocalDate UNDEFINEDDATE = LocalDate.MIN;
	/**
	 * Undefined bid, null by default
	 */
	private static final Bid UNDEFINEDBID = null;
	/**
	 * Constant for the sendNotification method
	 */
	private static final boolean START = true;
	/**
	 * Constant for the sendNotification method
	 */
	private static final boolean CLOSE = false;
	
	
	/**
	 * Auction constructor. 
	 * Creates an auction given its duration, minimum bid increase,
	 * id, the auctionable to set in auction.
	 * @param duration Duration of the auction since the first bid.
	 * @param minIncrement Minimum increase of the bids.
	 * @param minPrice Minimum price of the auction
	 * @param auctionable Auctionable in auction
	 * @param mailingList Customers that will receive a notification
	 * @throws InvalidArgumentsException when an invalid argument has been
	 * introduced, negative duration or increment, or not items for auction
	 * are specified
	 * @throws StateTransitionException when a not available item is trying to 
	 * added
	 */
	public Auction(int duration, double minIncrement,double minPrice, Auctionable auctionable, ArrayList<Customer> mailingList)
			throws InvalidArgumentsException, StateTransitionException{
		if(duration < 1 || minIncrement < 0 || auctionable == null ){
			throw new InvalidArgumentsException();
		}
		this.duration=duration;
		this.minIncrement=minIncrement;
		this.minPrice=minPrice;
		this.id=LocalDate.now().hashCode() ^ auctionable.hashCode();
		this.auctionable=auctionable;
		this.state = State.Opened;
		this.highestBid=UNDEFINEDBID;
		this.bids = new ArrayList<Bid>();
		this.startDate=UNDEFINEDDATE;
		if(mailingList==null){
			this.mailingList = new ArrayList<Customer>();
		} else {
			this.mailingList=mailingList;
		}
		this.participants=new ArrayList<ContractCustomer>();
		
		this.auctionable.toAuction();
		try {
			this.sendNotification(START);
		} catch (InvalidEmailAddressException | FailedInternetConnectionException e) {
			
		}
	}
	/**
	 * Auction constructor. 
	 * Creates an auction given its duration, minimum bid increase,
	 * id and the auctionable to set in auction.
	 * @param duration Duration of the auction since the first bid.
	 * @param minIncrement Minimum increase of the bids.
	 * @param minPrice Minimum price of the auction
	 * @param auctionable Auctionable in auction
	 * @throws InvalidArgumentsException when an invalid argument has been
	 * introduced, negative duration or increment, or not items for auction
	 * are specified
	 * @throws StateTransitionException when a not available item is trying to 
	 * added
	 */
	public Auction(int duration, double minIncrement,double minPrice, Auctionable auctionable)
			throws InvalidArgumentsException, StateTransitionException{
		this(duration, minIncrement,minPrice, auctionable, null);
	}
	/**
	 * Closes the auction.
	 * Therefore the auction passes to the closed state and
	 * it is permanently finished
	 * @throws InvalidAuctionAction when the auction is already
	 * closed, cancelled or opened.
	 */
	public void close() throws InvalidAuctionAction{
		if(this.state.compareTo(State.Running) == 0 && LocalDate.now().isEqual(this.startDate.plusDays(this.duration)) ){
			try {
				this.auctionable.sold();
			} catch (StateTransitionException e1) {
				e1.printStackTrace();
			}
			this.state=State.Closed;
			try {
				this.sendNotification(CLOSE);
			} catch (InvalidEmailAddressException | FailedInternetConnectionException e) {
				
			}
		} else {
			throw new InvalidAuctionAction(this.state);
		}

	}
	/**
	 * Cancels the auction.
	 * Therefore the auction passes to the cancelled state and
	 * it is permanently finished
	 * @throws InvalidAuctionAction when the auction is already
	 * cancelled, closed or opened.
	 */
	public void cancel() throws InvalidAuctionAction{
		if (this.state.compareTo(State.Opened)==0){
			try {
				this.auctionable.cancelled();
			} catch (StateTransitionException e1) {
				e1.printStackTrace();
			}
			this.state=State.Cancelled;
			try {
				this.sendNotification(CLOSE);
			} catch (InvalidEmailAddressException | FailedInternetConnectionException e) {
				
			}
		} else {
			throw new InvalidAuctionAction(this.state);
		}
		
	}
	/**
	 * Adds a participant to the auction
	 * @param newParticipant Customer added to the auction
	 * 
	 * @throws InvalidAuctionAction when the auction is not
	 * available or the customer is already added
	 */
	public void addParticipant(ContractCustomer newParticipant) throws InvalidAuctionAction{
		if(this.isAvailable()){
			if(this.participants.contains(newParticipant)){
				throw new InvalidAuctionAction("Customer already participating");
			} else {
				this.participants.add(newParticipant);
			}
		} else {
			throw new InvalidAuctionAction("Auction is not available");
		}
		
	}
	/**
	 * Adds a customer to the mailing list
	 * If the client was already in the mailing list nothing
	 * will occur
	 * @param client Customer added to the mailingList
	 */
	public void addToMailingList(Customer client){
		if(this.isAvailable()){
			if(this.mailingList.contains(client) == false){
				this.mailingList.add(client);
			}
		}
	}
	/**
	 * Removes a customer from the mailing list.
	 * If the customer was not in the mailing list
	 * nothing will occur
	 * @param client Customer removed from the mailingList
	 */
	public void removeFromMailingList(Customer client){
		if(this.mailingList.contains(client)){
			this.mailingList.remove(client);

		}
	}
	/**
	 * Calculates the days remaining of a running auction.
	 *  
	 * @return Days remaining of a running auction.
	 * @throws InvalidAuctionAction when the auction is not
	 * in the running state
	 */
	public int daysRemaining() throws InvalidAuctionAction{
		if(this.state.compareTo(State.Running)==0) {
			return (int) (this.duration - ChronoUnit.DAYS.between(this.startDate, LocalDate.now()));
		}else {
			throw new InvalidAuctionAction(this.state);
		}
	}
	/**
	 * Adds a new bid to the auction.
	 * It also puts the auction into running state and
	 * sets it start date if it is the first bid.
	 * It also updates the highest bid.
	 * If the customer making the bid was not participating
	 * in the auction he will be added
	 * @param newBid bid to be added.
	 * @return flag true if the client who made the bid was not participating
	 * and therefore must pay the fee (this will also depend on his contract)
	 * @throws InvalidAuctionAction when the auction is not 
	 * available 
	 * 
	 * @throws EmailException when a notification of the bid could not be sent
	 */
	public boolean addBid( Bid newBid) throws InvalidAuctionAction, EmailException{
		boolean flag =false;
		if(this.isAvailable() && newBid.getPrice()> this.minPrice){
			if(this.participants.contains(newBid.getCustomer())==false){
				this.addParticipant(newBid.getCustomer());
				flag= true;
			}
			this.bids.add(newBid);
			if(this.state.compareTo(State.Opened)==0){
				this.state=State.Running;
				this.startDate=LocalDate.now();
				this.highestBid=newBid;
				if( LocalDate.now().isBefore(this.startDate.plusDays(this.duration-1))){
					this.sendNotification(newBid);	
				}
			} else {
				if(newBid.isGreater(this.highestBid, this.minIncrement)){
					this.highestBid=newBid;
					if( LocalDate.now().isBefore(this.startDate.plusDays(this.duration-1))){
						this.sendNotification(newBid);	
					}				
				}
			}
			return flag;
		} else {
			throw new InvalidAuctionAction(this.state);
		}
	}
	/**
	 * Gets the current highest bid made in the auction
	 * @return Highest bid made in the auction
	 * @throws InvalidAuctionAction when no bids have been
	 * made yet.
	 */
	public Bid getMaxBid() throws InvalidAuctionAction{
		if(this.state.compareTo(State.Cancelled)!=0 && this.state.compareTo(State.Opened)!=0){
			return this.highestBid;
		} else {
			throw new InvalidAuctionAction(this.state);
		}
	}
	/**
	 * Gets the current winner of the auction
	 * @return Customer who made the highest bid
	 * @throws InvalidAuctionAction when no bids have been
	 * made yet.
	 */
	public ContractCustomer getWinner() throws InvalidAuctionAction{
		if(this.state.compareTo(State.Cancelled)!=0 && this.state.compareTo(State.Opened)!=0){
			return this.highestBid.getCustomer();
		}else{
			throw new InvalidAuctionAction(this.state);
		}
	}
	/**
	 * Sends a notification to the customers that decided 
	 * to receive it on the mailing list with the information
	 * of the bid passed.
	 * The notification are only sent if the bid is made before
	 * the blind bidding period is opened
	 * @param bid Bid about which the email will notify
	 * @throws EmailException when the email is not valid or there is a 
	 * connection issue
	 */
	protected void sendNotification( Bid bid) throws EmailException {
		
		String body = this.toString() + "\n" + bid.toString();
		for(Customer client : this.mailingList){
			EmailSystem.send(client.getEmail(), "Auction notification", body, true );	
		}
		
	}
	/**
	 * Sends a notification to the customers on the mailing list
	 * of the opening or closing of an auction
	 * @param s START if a start notification is sent, CLOSE if a close notification
	 * is sent
	 * @throws InvalidEmailAddressException when there is an invalid email address
	 * @throws FailedInternetConnectionException when there are connection problems
	 */
	protected void sendNotification(boolean s) throws InvalidEmailAddressException, FailedInternetConnectionException{
		String body;
		if(s){
			body= "The following auction has been opened :" + this.toString();
			
		}else {
			body = "The folowing auction has been " + this.state.toString() + " : " 
		+ this.toString();
		}
		for(Customer client : this.mailingList){
			EmailSystem.send(client.getEmail(), "Auction notification", body, true );	
		}
	}
	
	/**
	 * Checks if an auction is available, this is, it is not
	 * cancelled nor closed
	 * @return True if the auction is available, false if it is not.
	 */
	protected boolean isAvailable(){
		return (this.state.compareTo(State.Cancelled)!=0 && this.state.compareTo(State.Closed)!=0);
	}
	/**
	 * Id getter
	 * @return Auction id
	 */
	public int getId(){
		return this.id;
	}
	/**
	 * Returns the closing  date  of the auction
	 * @return Date in which the auction was closed
	 * @throws InvalidAuctionAction when the auction is not closed
	 */
	public LocalDate getEndDate() throws InvalidAuctionAction{
		if(this.state.compareTo(State.Closed)==0){
			return this.startDate.plusDays(this.duration);
		} else {
			throw new InvalidAuctionAction(this.state);
		}
	}
	/**
	 * Compares to auctions
	 * @param auction Auction to be compared
	 * @return True if the auctions are equal, have the same id
	 */
	@Override public boolean equals(Object auction){
		Auction auction2 = (Auction) auction;
		return (this.id== auction2.getId());
	}
}
