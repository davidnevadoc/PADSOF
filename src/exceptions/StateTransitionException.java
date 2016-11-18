package exceptions;

/**
 * This class implements a self created exception, released when an impossible state transition is tried
 */
public class StateTransitionException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6411974435956537781L;
	/**
     * A comment on why the exception was thrown
     */
    String comment;
    /**
     * Constructor
     * @param s the comment
     */
    public StateTransitionException (String s) { 
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
