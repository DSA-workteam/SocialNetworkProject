package exceptions;

/**
 * Exception that is thrown when you try to get an element from an empty collection.
 * @author Borja Moralejo Tobajas
 *
 */
@SuppressWarnings("serial")
public class EmptyCollectionException extends Exception{
	
  /**
   * Shows which collection is empty. 
   * @param collection - String. The collection in string.
   */
  public EmptyCollectionException (String collection){
    super ("The " + collection + " is empty.");
  }
  
  
}