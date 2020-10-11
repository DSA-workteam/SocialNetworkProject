package socialNetworkPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import adt.DataBlockADT;
import dataStructuresImplemented.Person;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;


/**
 * Main class of the project.
 * @author Borja Moralejo Tobajas and Imanol Maraña Hurtado
 *
 */
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
		
		
		while(onMenu) {
			
			showMenu();
			if(scanner.hasNext()) {
				
				int selection;
				int attribute;
				String value;
				Person[] output;
				
				
				try {
					selection = scanner.nextInt();
					if(selection == -1) {
						System.out.println("Closing program...");
					}
					else {
						System.out.println("Inloop");
					}
				}catch(InputMismatchException e) {
					selection = -2; // or selection = UNKNOWN and then do something in the switch case
				}
				
				switch(selection) {
				case END:
					onMenu = false;
					break;
				case HELP:
					break;
				case LOADP:
					System.out.println();
					System.out.println("Please, insert the name of the file from which you wish to load the data");
					if(scanner.hasNext())
						dh.loadFile(scanner.next(), 0);
					break;
				case LOADR:
					System.out.println();
					System.out.println("Please, insert the name of the file from which you wish to load the relationships");
					if(scanner.hasNext())
						dh.loadFile(scanner.next(), 1);
/*					try {
						System.out.println(dh.getPersonByID("Ainhoa34").getLinkedNumber());
						System.out.println(dh.getPersonByID("Ainhoa34").getLinkedNodes()[3]);
						System.out.println(dh.getPersonByID("Ane52").getLinkedNumber());
						System.out.println(dh.getPersonByID("Ane52").getLinkedNodes()[2]);
						System.out.println(dh.getPersonByID("Silvia3").getLinkedNumber());
						System.out.println(dh.getPersonByID("Silvia3").getLinkedNodes()[0]);
					}
					catch(ElementNotFoundException e){
						System.out.println("Algo ha ido mal");
					}*/
					break;
				case PRINT:
					System.out.println();
					System.out.println("Please, insert the name of the file which you will create to print the data in");
					if(scanner.hasNext())
						dh.printIntoFile(scanner.next());
					break;
				case SEARCH:
					System.out.println();
					System.out.println("Please, enter a number for searching:");
					showOptions();
					if(scanner.hasNext()) {
						attribute = scanner.nextInt();
						while(attribute != 0 && attribute != 1 && attribute != 2 && attribute != 3 && attribute != 4 && attribute != 5 && attribute != 6 && attribute != 7 && attribute != 8 && attribute != 9 && attribute != 10){
							System.out.println();
							System.out.println("The input you have introduced is not valid, please try again");
							System.out.println();
							showOptions();
							if(scanner.hasNext()) {
								attribute = scanner.nextInt();
							}
						}
						if(attribute == 0) {
						}
						else{
							System.out.println();
							System.out.println("Please, insert the value with which you want to search");
							if(scanner.hasNext()) {
								value = scanner.next();
								try {
									output = dh.searchPeopleByAttribute(attribute - 2,value);
									System.out.println();
									System.out.println("The person has been found, here is the output:");
									for (int i = 0; i < output.length; i++) {
										System.out.println();
										System.out.println(output[i].toString());
									}
								} catch (ElementNotFoundException e) {
									System.out.println();
									System.out.println("The person you tried to find is not in the data base");
									System.out.println();
								}
							}
						}
					}
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

	private static void showOptions() {
		System.out.println("	0.Go back");
		System.out.println("	1.ID");
		System.out.println("	2.Name");
		System.out.println("	3.Surname");
		System.out.println("	4.Birth date");
		System.out.println("	5.Birth place");
		System.out.println("	6.Home");
		System.out.println("	7.Studied at");
		System.out.println("	8.Worked at");
		System.out.println("	9.Movies");
		System.out.println("	10.Groupcode");
	}
	
	
	
}
