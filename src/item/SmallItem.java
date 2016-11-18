package item;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormatSymbols;

/**
 * This class implements a small item
 */
public class SmallItem extends StandardItem{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3853609281670471000L;
	/**
     * The percenage of discount applied if the client has the required contract when purchasing the object
     */
    protected double percentageDiscount;

    /**
     * Constructor
     * @param description
     * @param manufacturingDate
     * @param acquisitionDate
     * @param acquisitionPrice
     * @param targetPrice
     * @param percentageDiscount
     * @throws ParseException is parsing problem
     */
    public SmallItem(String description, String manufacturingDate, String acquisitionDate, double acquisitionPrice, 
                        double targetPrice, double percentageDiscount) throws ParseException{
        super(description,manufacturingDate, acquisitionDate, acquisitionPrice, targetPrice);
        if(percentageDiscount>=0 && percentageDiscount<=1) this.percentageDiscount = percentageDiscount;
        else this.percentageDiscount=0;
    }
    
    /**
     * Constructor
     * @param fields array of Strings with all the fields. filds[0] is useless
     * @throws ParseException if error while formating
     */
    public SmallItem(String[] fields) throws ParseException,IndexOutOfBoundsException{
        this(fields[1],fields[2],fields[3],Double.parseDouble(fields[4]),
                Double.parseDouble(fields[5]), Double.parseDouble(fields[6]));
        if(fields.length==9){
            setState(ItemState.valueOf(fields[7]));
            setId(Integer.parseInt(fields[8]));
        }
        
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
        return ("S;" +  description +";" + manufacturingYear +";" +  formatter.format(acquisitionDate) +";"+ df.format(acquisitionPrice)+";" + 
                df.format(targetPrice)+";" +   df.format(percentageDiscount)+";" +
                state.toString() +";" + Integer.toString(id)+ "\n");
    }    
       
    
}
