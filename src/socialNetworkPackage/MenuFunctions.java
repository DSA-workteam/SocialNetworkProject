package socialNetworkPackage;

import dataStructuresImplemented.Person;
import enums.MenuEnum;
import enums.PersonAttributesEnum;
import exceptions.AlreadyOnTheCollectionException;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import exceptions.MenuClosedException;
import socialNetworkPackage.MenuManager.StateMachineAttributes;

public class MenuFunctions {
	
	
	private String s1, s2; // Strings saved for later use in the functions

	/**
	 * This method is a collection of functions of the menu. In this method near all the functionality of the menu options are implemented.
	 * @param input - String. It takes the input from the main menu loop.
	 * @param dh DataHolder - {@link DataHolder}.
	 * @param sma - {@link StateMachineAttributes} from MenuManager
	 * @throws MenuClosedException - {@link MenuClosedException}. If this is thrown, the main loop of the menu will stop and the program terminates.
	 */
	public void useInput(String input, DataHolder dh,StateMachineAttributes sma) throws MenuClosedException{
		switch(sma.state) {
		case ADD: // Extra function added by us
			
			try {
				Person nPerson = new Person(input);
				dh.addPersonToNetwork(nPerson);			
				System.out.println("Your person has been loaded successfully");
			} catch (ImpulsoryAttributeRequiredException e) {
				System.out.println("You haven't typed any ID.");
			} catch(AlreadyOnTheCollectionException e) {
				System.out.println("This ID was already selected, please chose a new one");		
			}
			sma.substate = 3;
			sma.state = MenuEnum.MAIN;
			break;
		case BORNPEOPLE: // Point 7 of the programming project
			// TODO Borja search friends by surname
			sma.substate = 2;
			sma.state = MenuEnum.MAIN;
			break;
		case BUILDPROFILES:	// Point 10 of the programming project
			System.out.println("Press enter to continue:");
			sma.substate = 2;
			sma.state = MenuEnum.MAIN;
			break;
		case DATES: // Point 8 of the programming project
			if(sma.substate == 0) {
				s1 = input;
				sma.substate = 1;
				System.out.println("Enter another year:");
			}else {
				s2 = input;			
				
				s1 = s2 = s1;//nada, ignoralo es para el warning borralo
				s1 = s2;
				//TODO borja
				
				sma.substate = 2;
				sma.state = MenuEnum.MAIN;				
			}
			
			break;
		case LOADP: // Point 2 or 3 of the programming project //TODO check this
			dh.loadFile(input, 0);
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 1;
			break;
		case LOADR: // Point 5 of the programming project
			dh.loadFile(input, 1);
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 1;
			break;
		case MAIN: // Point 1 of the programming project and it used for navigating in the main menu
			try {
				int intCast = Integer.parseInt(input);
				switch(sma.substate) {
				case 0: // MAIN MENU
					if(intCast == -1) {
						throw new MenuClosedException();
					}else if(intCast >= 1 && intCast <= 3) {
						sma.substate = intCast;
					}
					
					break;
				case 1: // FIRST MILESTONE MENU
					
					switch(intCast) {
					case 0:
						sma.substate = 0;
						break;
					case 1:
						sma.substate = 0;
						sma.state = MenuEnum.LOADP;
						break;
					case 2:
						sma.substate = 0;
						sma.state = MenuEnum.LOADR;
						break;
					case 3:
						sma.substate = 0;
						sma.state = MenuEnum.PRINTP;
						break;
					default:
						System.err.println("That sma.state doesn't exist");
						break;
					}
					
					break;
				case 2: // SECOND MILESTONE MENU
					switch(intCast) {
					case 0:
						sma.substate = 0;
						break;
					case 1:
						sma.substate = 0;
						sma.state = MenuEnum.SEARCHFRIENDS;
						break;
					case 2:
						sma.substate = 0;
						sma.state = MenuEnum.BORNPEOPLE;
						break;
					case 3:
						sma.substate = 0;
						sma.state = MenuEnum.DATES;
						break;
					case 4:
						sma.substate = 0;
						sma.state = MenuEnum.RESIDENTIAL;
						break;
					case 5:
						sma.substate = 0;
						sma.state = MenuEnum.BUILDPROFILES;
						break;
					default:
						System.err.println("That sma.state doesn't exist");
						break;
					}
					
					break;
				case 3: // EXTRA FEATURES MENU
					switch(intCast) {
					case 0:
						sma.substate = 0;
						break;
					case 1:
						sma.substate = 0;
						sma.state = MenuEnum.PRINTR;
						break;
					case 2:
						sma.substate = 0;
						sma.state = MenuEnum.SEARCH;
						break;
					case 3:
						sma.substate = 0;
						sma.state = MenuEnum.ADD;
						break; 
					case 4:
						sma.substate = 0;
						sma.state = MenuEnum.REMOVEPERSON;
						break;
					case 5:
						sma.substate = 0;
						sma.state = MenuEnum.SHOWFRIENDS;
					default:
						System.err.println("That sma.state doesn't exist");
						break;
					}
					break;
				}
			}catch(NumberFormatException e) {
				System.err.println("That's not a number!");	
			}
			
			break;
		case PRINTP: // Point 4 of the programming project // TODO check this
			dh.printIntoFile(input);
			sma.state = MenuEnum.MAIN;
			sma.substate = 1;
			break;
		case PRINTR: // Extra function added by us
			// TODO print relationships
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 3;
			break;
		case REMOVEPERSON: // Extra function added by us

			try {
				Person p = dh.getPersonByID(input);
				dh.removePersonFromNetwork(p);
				System.out.println("The person has been removed succesfully");
				System.out.println(p);
				System.out.println();
			}catch(ElementNotFoundException e) {
				System.out.println("The person you wanted to delete hasn't been found");
			}
			
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 3;
			break;
		case RESIDENTIAL: // Point 9 of the programming project
			// TODO residential
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 2;
			break;
		case SEARCH: // Point ? of the programming project // TODO check this?
			
			
			if(sma.substate == 0) { // Choosing the attribute to search for
				try{
				sma.parsedOption = Integer.parseInt(input);
				
				if(sma.parsedOption == 0) {
					sma.state = MenuEnum.MAIN;
					sma.substate = 3;
				}else if(sma.parsedOption > 0 && sma.parsedOption <= 11)
				sma.substate = 1;
				}catch(NumberFormatException e) {
					System.err.println("That's not a number!");	
				}
			
			}else { // Searching for people that fulfill the have the requested attribute with the given value
				
				try {
					Person[] output = dh.searchPeopleByAttribute(PersonAttributesEnum.values()[sma.parsedOption - 1],input);
					System.out.println();
					System.out.println("At least one person has been found, here is the output:");
					for (int i = 0; i < output.length; i++) {
						System.out.println(output[i].toString());
					}
					System.out.println();
				} catch (ElementNotFoundException e) {
					System.out.println();
					System.out.println("The person you tried to find is not in the data base");
					System.out.println();
				}
				
				sma.state = MenuEnum.MAIN;
				sma.substate = 3;
			}			
			break;
		case SEARCHFRIENDS: // Point 6 of the programming project
			// TODO IMOKE
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 2;
			break;
		case SHOWFRIENDS: // Extra function by us, this shows the friends of the given id
			// TODO show friends of input
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 3;
			
			break;
		default:
			break;
			
		}
	}
	
	
	
	
}
