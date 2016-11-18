package exceptions;

public class InvalidArgumentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4162627470086424850L;

	public InvalidArgumentsException(){
		super();
	}
	@Override public String toString(){
		return "Inocrrect parameters";
	}
}
