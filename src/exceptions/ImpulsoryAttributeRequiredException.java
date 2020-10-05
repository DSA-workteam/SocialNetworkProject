package exceptions;

/**
 * Expection class that is used in the constructor of other classes. In our case we use it in Person class
 * @author Borja Moralejo Tobajas
 *
 */
@SuppressWarnings("serial")
public class ImpulsoryAttributeRequiredException extends Exception {

	
	/**
	 * Shows a message saying which attribute is necessary for the constructor.
	 * @param msg - String.
	 */
	public ImpulsoryAttributeRequiredException(String msg) {
		super(msg);
	}
	
}
