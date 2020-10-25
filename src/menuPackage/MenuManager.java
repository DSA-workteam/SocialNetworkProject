package menuPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.Scanner;

import abstractDataTypesPackage.DataBucketADT;
import enums.MenuEnum;
import exceptions.MenuClosedException;
import socialNetworkPackage.SocialNetwork;


/**
 * This class is the user interface between the program and the user. It has a sort of state machine. The state machine has an state and a substate. When the state changes usually the substate resets and the state machine works like it should this way.
 * @author Borja Moralejo Tobajas
 *
 */
public class MenuManager extends MenuPrint{

	public MenuManager() {
		super();
		run();		
	}
	
	
	/**
	 * Main program's menu loop. It uses {@link FileInputStream} for the scanner, so it can be reopened.
	 * @param dh - {@link DataBucketADT}. Referenced from the main method in {@link SocialNetwork}.
	 */
	private void run() {
				// Console input 
				FileInputStream fis = new FileInputStream(FileDescriptor.in);
				Scanner scanner = new Scanner(fis);
				
				// To end the process when exited
				boolean onMenu = true;

				String input = "";
				
				// Main loop of the menu
				while(onMenu) {
					
					showMenu();
					
					// Gets input one by one, so it reduces the probability of having errors
					if(scanner.hasNext())
						input = scanner.next();
					
					try {
						// Gives the input to the function that the user has selected.
						useInput(input);
					} catch (MenuClosedException e) {
						onMenu = false;
					}
					
				}
										
				scanner.close();
	}
	
	
	
	
	
}
