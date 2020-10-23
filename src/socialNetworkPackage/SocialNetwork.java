package socialNetworkPackage;



/**
 * Main class of the project.
 * @author Borja Moralejo Tobajas and Imanol Maraña Hurtado
 *
 */
public class SocialNetwork {

	
	

	public static void main(String[] args) {	

		// Initializes the data structure holder	
		DataHolder dh = new DataHolder(100);

		
		// IMOKE needed to remake this in a better way, using Enums or whatever. Turns out Borja did that.
		MenuManager mm = new MenuManager();
		mm.run(dh);

		
	}
	
	
	
}
