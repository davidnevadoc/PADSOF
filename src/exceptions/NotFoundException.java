package exceptions;

/**
 * This class implements a self created exception, released when a search didn't produce any result
 */
public class NotFoundException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8402372959054663266L;
	/**
     * A comment on why the exception was thrown
     */
    String comment;
    /**
     * Constructor
     * @param s the comment
     */
    public NotFoundException (String s) { 
        comment=s;
    } 
    /**
     * Transforms the exception to a String
     * @return 
     */
    @Override public String toString () {
        return comment;
    }    
}

