package sales;

public class InvalidAdditionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4207452255572445268L;
	/**
	 * Error message
	 */
	private String message;
	/**
	 * Exception constructor
	 */
	public InvalidAdditionException(){
		this.message= "Item unavailable";
	}
	/**
	 * Transforms the exception to a String.
	 * @return Error message
	 */
	@Override public String toString(){
		return this.message;
	}
}
