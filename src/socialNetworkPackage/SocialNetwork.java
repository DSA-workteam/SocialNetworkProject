package socialNetworkPackage;

import menuPackage.MenuManager;

/**
 * Main class of the project.
 * @author Borja Moralejo Tobajas and Imanol Maraña Hurtado
 *
 */
public class SocialNetwork {

	
	

	public static void main(String[] args) {	

		// Initializes the data structure holder	
		DataHolder.instantiate(128);

		
		// IMOKE needed to remake this in a better way, using Enums or whatever. Turns out Borja did that.
		MenuManager mm = new MenuManager();
		mm.run();

		
	}
	
	
	
}
