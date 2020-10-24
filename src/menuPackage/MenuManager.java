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
public class MenuManager {

	// Attributes for the state machine
	private MenuPrint mp;
	private MenuFunctions mf;
	private StateMachineAttributes sma;
	
	
	
	
	/**
	 * Initializes the state machine of the menu
	 */
	public MenuManager() {
		sma = new StateMachineAttributes();
		sma.state = MenuEnum.MAIN;
		sma.substate = 0;
		sma.parsedOption = 0;
		mp = new MenuPrint();
		mf = new MenuFunctions();
	}
	
	
	/**
	 * Main program's menu loop. It uses {@link FileInputStream} for the scanner, so it can be reopened.
	 * @param dh - {@link DataBucketADT}. Referenced from the main method in {@link SocialNetwork}.
	 */
	public void run() {
				// Console input 
				FileInputStream fis = new FileInputStream(FileDescriptor.in);
				Scanner scanner = new Scanner(fis);
				
				// To end the process when exited
				boolean onMenu = true;

				String input = "";
				
				// Main loop of the menu
				while(onMenu) {
					
					mp.showMenu(sma);
					
					// Gets input one by one, so it reduces the probability of having errors
					if(scanner.hasNext())
						input = scanner.next();
					
					try {
						// Gives the input to the function that the user has selected.
						mf.useInput(input, sma);
					} catch (MenuClosedException e) {
						onMenu = false;
					}
					
				}
										
				scanner.close();
	}
	
	
	/**
	 * It holds and stores the state machines attributes. Because java doesn't have the option to give as reference an integer, I made this.
	 * @author Borja Moralejo Tobajas
	 *
	 */
	public class StateMachineAttributes {
		public MenuEnum state;
		public int substate;
		public int parsedOption;
		
	}
	
	
}
