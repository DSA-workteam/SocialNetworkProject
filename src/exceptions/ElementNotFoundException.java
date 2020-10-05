package exceptions;

/**
 * Exception class that is thrown when an element is not found.
 * @author Borja Moralejo Tobajas
 *
 */
@SuppressWarnings("serial")
public class ElementNotFoundException extends Exception{
	
	/**
	 * Constructor that has an String as message
	 * @param element - Element that was trying to be found.
	 */
	public ElementNotFoundException(String element) {
		super("The element "+ element + " was not found.");	
	}
}
