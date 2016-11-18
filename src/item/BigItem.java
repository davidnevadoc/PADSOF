package item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import Utilities.*;

/**
 * This class implements a bulky item
 */
public class BigItem extends StandardItem{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1018659362859408758L;
	/**
     * lenght of the item
     */
    protected double length;
    /**
     * width of the item
     */
    protected double width;
    /**
     * height of the item
     */
    protected double height;
    /**
     * wight of the item
     */
    protected double weight;

    /**
     * maximum lenght before applying extra delivery cost (cm)
     */
    private static final double maxlength= Configuration.getMaxlength();
    /**
     * delivery cost if maxlenght reached
     */
    private static final double priceIfLong = Configuration.getPriceIfLong();
    /**
     * maximum weight before applying extra delivery cost (kg)
     */
    private static final double maxweight = Configuration.getMaxweight();
    /**
     * base price if maxweight reached
     */
    private static final double basePriceIfWeight = Configuration.getBasePriceIfWeight();
    /**
     * Increment added to the basePrice for every incrementWeight kilos
     */
    private static final double incrementPrice = Configuration.getIncrementPrice();
    /**
     * Every incrementWeight passing the maxweight we must pay incrementPrice more
     */
    private static final double incrementWeight = Configuration.getIncrementWeight();
    /**
     * The base price of the deliveing
     */
    private static final double basePrice = Configuration.getBasePrice();
    
    /**
     * Constructor
     * @param description
     * @param targetPrice
     * @param manufacturingDate
     * @param acquisitionDate
     * @param acquisitionPrice
     * @param length
     * @param width
     * @param height
     * @param weight
     * @throws ParseException if error while parsing
     */
    public BigItem(String description, String manufacturingDate, String acquisitionDate, double acquisitionPrice, double targetPrice,
                    double length, double width, double height, double weight) throws ParseException{
            super(description,manufacturingDate, acquisitionDate, acquisitionPrice, targetPrice);
            this.length = Math.abs(length);
            this.width = Math.abs(width);
            this.height = Math.abs(height);
            this.weight = Math.abs(weight);
    }

    /**
     * Constructor
     * @param fields array of Strings with all the fields (fields[0] means nothing)
     * @throws ParseException if error while formating
     */
        
    public BigItem(String[] fields) throws ParseException, IndexOutOfBoundsException{
        this(fields[1],fields[2],fields[3],Double.parseDouble(fields[4]),
                Double.parseDouble(fields[5]), Double.parseDouble(fields[6]),Double.parseDouble(fields[7]),
                Double.parseDouble(fields[8]),Double.parseDouble(fields[9]));
        if(fields.length==12){
            setState(ItemState.valueOf(fields[10]));
            setId(Integer.parseInt(fields[11]));
        }
    }

    /**
     * Calculates the delivery cost
     * @return the delivery cost (Euros)
     */
    public double calculateDelivery(){
            double price=basePrice;
            if(length>maxlength || width>maxlength || height>maxlength) price =price + priceIfLong;
            if(weight>=maxweight) price =price + basePriceIfWeight + incrementPrice*(Math.ceil((weight-maxweight)/incrementWeight));
            return price;
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
        return ("B;" +  description +";" + manufacturingYear +";" +  formatter.format(acquisitionDate) +";"+ df.format(acquisitionPrice)+";" + 
                df.format(targetPrice)+";" +   df.format(length)+";" + df.format(width) +";" + df.format(height)+";" + df.format(weight)+";" +
                state.toString() +";" + Integer.toString(id)) + "\n";
    }
        
        

}
