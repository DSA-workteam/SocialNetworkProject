package socialNetworkPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import dataStructuresImplemented.Person;



public class SocialNetwork {

	
	
	public final static int NPARAMETERS = 10;
	
	public final static int ID = -1, NAME =0, SURNAME = 1, BIRTHDATE = 2, BIRTHPLACE = 3, HOME = 4,STUDIEDAT = 5,
			WORKEDAT =6, MOVIES = 7, GROUPCODE = 8;
	
	
	/**
	 * Adds people to the social network and saves the person by hashing it's id
	 * @param p - Person
	 */
	public static void addPeopleToSocialNetwork(Person p) {
	
	}
	
	public static void removePeopleFromSocialNetwork(Person p) {
		//people[encoder(p.id)].removePerson(p);
	}
	
	
	
	
	/*
	private static void testAddingPeople() {
		Person p = new Person("Lmao");
		String[] s = {"Donostia"};
		p.setParameter(HOME, s);
		String[] s2 = {"Donramon","Pokemon"};
		p.setParameter(MOVIES, s2);
		p.print();
		addPeopleToSocialNetwork(p);
	}
	
	*/

	public static void main(String[] args) {
		// Initializes the data structure
		
	
		
		// Menu constants
		final int END = -1, HELP = 0, LOADP = 1, LOADR = 2, PRINT = 3, SEARCH = 4;
		
		//testAddingPeople();
		
		
	


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
					selection = -2;
				}
				
				switch(selection) {
				case END:
					onMenu = false;
					break;
				case HELP:
					showMenu();
					break;
				case LOADP:
					break;
				case LOADR:
					break;
				case PRINT:
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
	 * 
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
