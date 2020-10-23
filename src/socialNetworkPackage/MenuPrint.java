package socialNetworkPackage;

import java.util.Iterator;

import adt.DataBlockADT;
import enums.MenuEnum;
import enums.PersonAttributesEnum;
import socialNetworkPackage.MenuManager.StateMachineAttributes;


/**
 * Menu class which means that the main functionality of this class is to show to the user the input that has to enter to make certain functions.
 * @author Borja Moralejo Tobajas
 *
 */
public class MenuPrint {
	
	
		
	/**
	 * Prints in console the information to the user according to the state machine's state.
	 * @param dh - DataHolder{@link DataHolder} from the main method.
	 * @param sma - {@link StateMachineAttributes} from the state machine of the MenuManager
	 */
	public void showMenu(DataHolder dh, StateMachineAttributes sma) {
		if( sma.state != MenuEnum.MAIN) {
			showActionMenu(dh, sma);
		}else if(sma.state == MenuEnum.MAIN) {
			switch(sma.substate) {
			case 1:
				showFirstMilestoneMenu();
				break;
			case 2:
				showSecondMilestoneMenu();
				break;
			case 3:
				showExtraFeatures();
				break;
			default:
				showMainMenu();
				break;
			}
		}
			
		
	}
	
	/**
	 * Shows main menu to the user by printing it in the console
	 */
	public static void showMainMenu() {
		
		
		System.out.println("1. Show First Milestone Menu");
		System.out.println("2. Show Second Milestone Menu");
		System.out.println("3. Show extra features Menu");


		System.out.println("-1. Log out form the program");
	}
	
	/**
	 * Shows First Milestone menu to the user by printing it in the console
	 */
	public void showFirstMilestoneMenu() {
		System.out.println("	1. Load people from a file");
		System.out.println("	2. Load relationships from a file");
		System.out.println("	3. Print people into a file");
		System.out.println("0. Back");

	}
	/**
	 * Shows Second Milestone menu to the user by printing it in the console
	 */
	public void showSecondMilestoneMenu() {
		System.out.println("	1. Search friends by surname (point 6)");
		System.out.println("	2. Get people born in (point 7)");
		System.out.println("	3. Get people born between D1 and D2 (point 8)");
		System.out.println("	4. Residential, change this (point 9)");
		System.out.println("	5. Build profiles (point 10)");
		System.out.println("0. Back");



	}
	
	
	/**
	 * Shows our extra features menu to the user by printing it in the console
	 */
	public void showExtraFeatures() {		
		System.out.println("	1. Print relationships into a file");
		System.out.println("	2. Search people by:");
		System.out.println("	3. Add person into the network");
		System.out.println("	4. Remove person by ID");
		System.out.println("	5. Show friends of the given id");

		System.out.println("0. Back");

	}

	/**
	 * Shows search's functionality menu to the user by printing it in the console
	 */
	public static void showSearchOptions() {
		System.out.println("	Search using: ");
		for(int i = 0; i < PersonAttributesEnum.values().length; i++) {
			System.out.println("		"+(i+1)+". "+PersonAttributesEnum.values()[i].toString());
		}		
		System.out.println("0. Go back");

	}
	
	
	/**
	 * Shows current state's functionalities menu to the user by printing it in the console
	 * @param dh
	 */
	public void showActionMenu(DataHolder dh, StateMachineAttributes sma) {
		switch(sma.state) {
		case ADD:
			System.out.println("Type a persons details using ONLY THE INDICATED METHOD in a SINGLE LINE.");
			System.out.println("Only obligatory info, ID.");
			System.out.println("For null values, don't let any space between ','.");
			System.out.println("For more than one value in a field, type ';' between values. Only not accepted field with various values: ID");
			System.out.println("ID,NAME,SURNAME,BIRTHDATE(dd/mm/yyyy),GENDER,BIRTHPLACE,HOME,STUDIEDAT,WORKEDAT,FILMS,GROUPCODE");
			break;
		case BORNPEOPLE:
			// TODO Borja
			break;
		case BUILDPROFILES:
			System.out.println();
			System.out.println("People that have the same profile: ");
			System.out.println();
			
			Iterator<DataBlockADT<String, String>> it = dh.getCollectionOfAttribute(PersonAttributesEnum.MOVIES).iterator();
			while(it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("Enter anything to continue:");


			break;
		case DATES:
			
			if(sma.substate == 0) {
				System.out.println();
				System.out.println("Enter D1, the year of birth of the people that you want to search for: ");
				System.out.println();
			}else {
				System.out.println();
				System.out.println("Enter another year:");
				System.out.println();
			}
			
			break;
		
		case LOADP:
			System.out.println();
			System.out.println("Please, insert the name of the file from which you wish to load the data");
			System.out.println();
			break;
		case LOADR:
			System.out.println();
			System.out.println("Please, insert the name of the file from which you wish to load the relationships");
			System.out.println();
			break;
		case PRINTP:
			System.out.println();
			System.out.println("Please, insert the name of the file which you will create to print the people data in");
			System.out.println();
			break;
		case PRINTR:
			System.out.println();
			System.out.println("Please, insert the name of the file which you will create to print the relationships data in");
			System.out.println();
			break;
		case REMOVEPERSON:
			System.out.println();
			System.out.println("Type the ID of the person you want to delete");
			System.out.println();
			break;
		case RESIDENTIAL:
			// TODO Imoke

			 break;
		case SEARCH:
			if(sma.substate == 0) {
				System.out.println();
				showSearchOptions();
				System.out.println("Please, enter an option:");
				System.out.println();
			}else {
				System.out.println();
				System.out.println("You are searching by "+PersonAttributesEnum.values()[sma.parsedOption-1].toString());
				System.out.println("Please, insert the value with which you want to search");
				System.out.println();

			}
			break;
		case SEARCHFRIENDS:
			// TODO Imoke
			break;
		case SHOWFRIENDS: 
			System.out.println();
			System.out.println("Please, input the id of the person that you want to show up its friends");
			System.out.println();
			break;
		case MAIN:
			break;
		default:
			break;
		}
	}
}
