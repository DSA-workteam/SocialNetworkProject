package socialNetworkPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import adt.DataBlockADT;
import dataStructuresImplemented.Person;
import exceptions.ImpulsoryAttributeRequiredException;



public class SocialNetwork {
	/* Test function
	
	private static void testAddingPeople() {
		Person p = null;
		try {
			p = new Person("Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,-,Cadena Perpetua;Your voice,G25527");
			System.out.println(p.toString());
			
		} catch (ImpulsoryAttributeRequiredException e) {
			e.printStackTrace();
		}
		dh.addPersonToNetwork(p);
		
	}
	*/
	
	
	// Attributes
	private static DataHolder dh;	

	public static void main(String[] args) {
		
		// Initializes the data structure holder	
		dh = new DataHolder(100);
		
		
		
		
		
		// The following code is for the menu, TEMPORAL
		
		// TEMPORAL, IMOKE needs to remake this in a better way, using Enums or whatever
		
		// Menu constants
		final int END = -1, HELP = 0, LOADP = 1, LOADR = 2, PRINT = 3, SEARCH = 4;

		// Console input 
		FileInputStream fis = new FileInputStream(FileDescriptor.in);
		Scanner scanner = new Scanner(fis);
		
		// To end the process when exited
		boolean onMenu = true;
		
		showMenu();
		while(onMenu) {
			
			if(scanner.hasNext()) {
				
				int selection;
				
				try {
					selection = scanner.nextInt();
				}catch(InputMismatchException e) {
					selection = -2; // or selection = UNKNOWN and then do something in the switch case
				}
				
				switch(selection) {
				case END:
					onMenu = false;
					break;
				case HELP:
					showMenu();
					break;
				case LOADP:
					dh.loadFile("people.txt");
					break;
				case LOADR:
					break;
				case PRINT:
					dh.printIntoFile("Rpeople");
					break;
				case SEARCH:
					break;
				default:
					break;
						
				
				}
			}
			
			
		}// While loop
		
		// End of program
		scanner.close();
	}
	
	/**
	 * Obvious thing
	 */
	private static void showMenu() {
		System.out.println("1. Load people into the network");
		System.out.println("2. Load relationships of the people");
		System.out.println("3. Print people");
		System.out.println("4. Search people by:");
		System.out.println("5. Add person");
		System.out.println("-1. Log out");
	}

	
	
	
	
}
