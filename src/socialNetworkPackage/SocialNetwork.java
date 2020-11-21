package socialNetworkPackage;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import dataStructuresImplemented.DataHolder;
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
		InputStream input = null;
		if(args.length != 0)
			input = new ByteArrayInputStream(args[0].getBytes());
		new MenuManager(input);
		
		

		
		
	}
	
	
	
}
