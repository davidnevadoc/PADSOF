package item;

import Utilities.Utilities;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import exceptions.*;

/**
 * Implements a Wok of art
 */
public class WorkOfArt extends Item implements WorkOfArtInterface{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3148103703986457908L;
	/**
     * The original author of the work
     */
    protected String author;
    /**
     * The type of work of art it is
     */
    protected TypeOfWork type;
    /**
     * Marks wether the work of art has the author certificate
     */
    protected boolean authorCertificate;
    /**
     * Needed to the multiple inheritance
     */
    protected Auctionable auctionable = new Auctionable();

    /**
     * Constructor
     * @param description
     * @param targetPrice
     * @param manufacturingDate
     * @param acquisitionDate
     * @param acquisitionPrice
     * @param author
     * @param type
     * @param authorCertificate 
     * @throws ParseException if error while formating
     */
    public WorkOfArt(String description, String manufacturingDate, String acquisitionDate, double acquisitionPrice, double targetPrice,
                    String author, TypeOfWork type, boolean authorCertificate) throws ParseException{
        
            super(description,manufacturingDate, acquisitionDate, acquisitionPrice, targetPrice);
            this.author=author;
            this.type=type;
            this.authorCertificate = authorCertificate;
    }
    
    /**
     * Constructor
     * @param fields array of Strings with all the fields. fields[0] is useless
     * @throws ParseException if error while formating
     */
    public WorkOfArt(String[] fields) throws ParseException, IndexOutOfBoundsException, IllegalArgumentException{
        this(fields[1],fields[2],fields[3], Double.parseDouble(fields[4]),
                Double.parseDouble(fields[5]), fields[6], TypeOfWork.valueOf(fields[7]), Utilities.YNToBoolean(fields[8]));
        if(fields.length==11){
            setState(ItemState.valueOf(fields[9]));
            setId(Integer.parseInt(fields[10]));
        }
    }
    
    /**
     * Getter
     * @return if the Woa is in auction or not 
     */
    @Override public boolean isInAuction() {
        return auctionable.isInAuction();
    }

    /**
     * Setter
     * @throws StateTransitionException
     */
    @Override public void toAuction() throws StateTransitionException{
        if(isAvailable()) auctionable.toAuction();
        else throw new StateTransitionException("You cannot do that");
    }
    
    /**
     * Prints the item in file format
     * @return the string of the item
     */
    @Override public String printItem(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00",symbols); 
        return ("A;" +  description +";" + manufacturingYear +";" +  formatter.format(acquisitionDate) +";"+ df.format(acquisitionPrice)+";" + 
                df.format(targetPrice) +";" +  author +";" + type.toString() + ";" + Utilities.BooleanToYN(authorCertificate) + ";" + 
                state.toString() +";" + Integer.toString(id)+ "\n");
    }
    
    /**
     * Sells a work of art in auction
     * @throws StateTransitionException
     */
    @Override public void sold() throws StateTransitionException{
        auctionable.sold();
        state=ItemState.Sold;

    }
    
    /**
     * Cancells a work of art in auction
     * @throws  StateTransitionException
     */
    @Override public void cancelled()throws StateTransitionException{
        auctionable.cancelled();
        state=ItemState.Available;
    }
    
    /**
     * Checks if the item is available
     * @return true if available, false if not
     */
    @Override public boolean isAvailable(){
            return state.equals(ItemState.Available) && !auctionable.isInAuction();
    }
    
    
}
