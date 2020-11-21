package socialNetworkPackage;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;

import abstractDataTypesImplemented.GenericArrayListBinaryTree;
import comparator.PersonComparators;
import dataStructuresImplemented.DataHolder;
import dataStructuresImplemented.Person;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
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
