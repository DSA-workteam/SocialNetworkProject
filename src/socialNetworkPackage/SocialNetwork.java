package socialNetworkPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import adt.DataBlockADT;
import dataStructuresImplemented.Person;
import exceptions.AlreadyOnTheCollectionException;
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
		final int END = -1, HELP = 0, LOADP = 1, LOADR = 2, PRINT = 3, SEARCH = 4, ADD = 5, REMOVEPERSON = 6;

		// Console input 
		FileInputStream fis = new FileInputStream(FileDescriptor.in);
		Scanner scanner = new Scanner(fis);
		
		// To end the process when exited
		boolean onMenu = true;
		int selection;
		int attribute;
		String value;
		Person[] output;
		// Starts the main loop
		while(onMenu) {
			
			showMenu();
			if(scanner.hasNextInt()) {
				
				
				
				
				try {
					selection = scanner.nextInt();
					if(selection == -1) {
						System.out.println("Closing program...");
					}
				}catch(InputMismatchException e) {
					selection = -2; // or selection = UNKNOWN and then do something in the switch case
				}
				
				switch(selection) {
				// Ends the program
				case END:
					onMenu = false;
					break;
				// Shows the menu again	
				case HELP:
					break;
				// Loads people into the data holder from a given file
				case LOADP:
					System.out.println();
					System.out.println("Please, insert the name of the file from which you wish to load the data");
					if(scanner.hasNext())
						dh.loadFile(scanner.next(), 0);
					break;
				// Loads relationships into the data holder from a given file
				case LOADR:
					System.out.println();
					System.out.println("Please, insert the name of the file from which you wish to load the relationships");
					if(scanner.hasNext())
						dh.loadFile(scanner.next(), 1);
					break;
				// Creates a file with a given name where appears the people from the data holder's data 
				case PRINT:
					System.out.println();
					System.out.println("Please, insert the name of the file which you will create to print the data in");
					if(scanner.hasNext())
						dh.printIntoFile(scanner.next());
					break;
				// Searchs people inside the data holder with 2 keys, the first one to know the attribute and the second one to know the key used to search
				case SEARCH:
					System.out.println();
					System.out.println("Please, enter a number for searching:");
					showOptions();
					if(scanner.hasNextInt()) {
						attribute = scanner.nextInt();
						// Checks if the given number for the first key is valid and if not, asks for a new number
						while(attribute < 0 && attribute > 10){
							System.out.println();
							System.out.println("The input you have introduced is not valid, please try again");
							System.out.println();
							showOptions();
							if(scanner.hasNext()) {
								attribute = scanner.nextInt();
							}
						}
						// Makes nothing, or what's the same, goes back to the main loop
						if(attribute == 0) {
						}
						else{
							System.out.println();
							System.out.println("Please, insert the value with which you want to search");
							if(scanner.hasNext()) {
								value = scanner.next();
								// Tryes to search for at least one person with the given keys, and if there is none, it throws an error
								try {
									output = dh.searchPeopleByAttribute(attribute - 2,value);
									System.out.println();
									System.out.println("At least one person has been found, here is the output:");
									for (int i = 0; i < output.length; i++) {
										System.out.println();
										System.out.print(output[i].toString());
									}
									System.out.println();
								} catch (ElementNotFoundException e) {
									System.out.println();
									System.out.println("The person you tried to find is not in the data base");
									System.out.println();
								}
							}
						}
					}
					else {
						scanner.nextLine();
						System.out.println("You haven't introduced a valid operation, please try again");
						System.out.println("Press enter to continue");
						scanner.nextLine();
					}
					break;
				case ADD:
					Person nPerson;
					boolean alreadyInNetwork;
					System.out.println("Type a persons details using ONLY THE INDICATED METHOD in a SINGLE LINE.");
					System.out.println("Only obligatory info, ID.");
					System.out.println("For null values, don't let any space between ','.");
					System.out.println("For more than one value in a field, type ';' between values. Only not accepted field with various values: ID");
					System.out.println("ID,NAME,SURNAME,BIRTHDATE(dd/mm/yyyy),BIRTHPLACE,HOME,STUDIEDAT,WORKEDAT,FILMS,GROUPCODE");
					if(scanner.hasNext())
						try {
							nPerson = new Person(scanner.next());
							dh.addPersonToNetwork(nPerson);							
							System.out.println("This ID was already selected, please chose a new one");							
						} catch (ImpulsoryAttributeRequiredException e) {
							System.out.println("You haven't typed any ID.");
						} catch(AlreadyOnTheCollectionException e) {
							System.out.println("Your person has been loaded succesfully");

						}
					break;
				case REMOVEPERSON:
					String id;
					System.out.println("Type the ID of the person you want to delete");
					if(scanner.hasNext())
						try {
							id = scanner.next();
							Person p = dh.getPersonByID(id);
							dh.removePersonFromNetwork(p);
							System.out.println("The person has been removed succesfully");
							System.out.println(p);
						}
						catch(ElementNotFoundException e) {
							System.out.println("The person you wanted to delete hasn't been found");
						}
					break;
				default:
					System.out.println("That value is not a valid operation, please select a new operation");
					System.out.println("Type anything to continue");
					if(scanner.hasNext())
					break;
				}
			}
			else {
				scanner.nextLine();
				System.out.println("You haven't introduced an integer, please try again.");
				System.out.println("Press enter to continue:");
				scanner.nextLine();
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
		System.out.println("3. Print people into a file");
		System.out.println("4. Search people by:");
		System.out.println("5. Add person into the network");
		System.out.println("6. Remove person by ID");
		System.out.println("-1. Log out form the program");
	}

	/**
	 * Obvious thing
	 */
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
