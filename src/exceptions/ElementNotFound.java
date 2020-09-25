package exceptions;

@SuppressWarnings("serial")
public class ElementNotFound extends Exception{
	
	
	public ElementNotFound() {					
		super();					
	}
	
	public ElementNotFound(String element) {
		super("The element "+ element + " was not found.");	
	}
}
