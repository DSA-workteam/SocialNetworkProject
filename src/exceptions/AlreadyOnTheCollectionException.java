package exceptions;

@SuppressWarnings("serial")
public class AlreadyOnTheCollectionException extends Exception{

	public AlreadyOnTheCollectionException(String msg) {
		super(msg);
	}
}
