package auction.exceptions;

/**
 * This class implements a self created exception, released when there is a problem adding
 * an element to the set
 */
public class AddingFailureException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -468025792041499913L;
	/**
     * A comment on why the exception was thrown
     */
    String comment;
    /**
     * Constructor
     * @param s the comment
     */
    public AddingFailureException (String s) { 
        comment=s;
    } 
    /**
     * Transforms the exception to a String
     * @return Error message
     */
    @Override public String toString () {
        return comment;
    }    
}
