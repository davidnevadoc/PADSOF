package item;

import java.text.ParseException;


/**
 * implements a standard item (not a work of art)
 */
public abstract class StandardItem extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8591981200475576869L;

	/**
         * Constructor
	 * @param description
	 * @param targetPrice
	 * @param manufacturingDate
         * @param acquisitionDate
         * @param acquisitionPrice
         * @throws ParseException
	 */
        protected StandardItem(String description, String manufacturingDate, String acquisitionDate, double acquisitionPrice,
                double targetPrice) throws ParseException{
            super(description,manufacturingDate, acquisitionDate, acquisitionPrice, targetPrice);
        }

	
}
