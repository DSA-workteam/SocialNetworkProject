package socialNetworkPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import socialNetworkPackage.DataStructures.PeopleBlock;
import socialNetworkPackage.DataStructures.Person;
import socialNetworkPackage.DataStructures.StringBlock;


public class SocialNetwork {

	// The variable where all the data from the social network is going to be
	private static PeopleBlock[] people;
	private static StringBlock[] mainStringBlocks;
	
	private final static int NPARAMETERS = 9;
	
	private final static int ARRAYSIZE = 1024;
	public final static int NAME =0, SURNAME = 1, BIRTHDATE = 2, BIRTHPLACE = 3, HOME = 4,STUDIEDAT = 5,
			WORKEDAT =6, MOVIES = 7, GROUPCODE = 8;
	
	/**
	 * Gets the root of the binary tree of StringBlock
	 * @param var int - Data parameter type
	 * @return The root of the asked parameter
	 */
	public static StringBlock getStaticStringBlock(int var) {		
		return mainStringBlocks[var];
	}
	
	/**
	 * Adds people to the social network and saves the person by hashing it's id
	 * @param p - Person
	 */
	public static void addPeopleToSocialNetwork(Person p) {
		people[encoder(p.id)].addPerson(p);
	}
	
	public static void removePeopleFromSocialNetwork(Person p) {
		people[encoder(p.id)].removePerson(p);
	}
	private static int encoder(String s) {
		int r = 0;
		
		for (int i = 0; i < s.length(); i++) 
			r += (int)s.charAt(i)*i;
				
		return r % ARRAYSIZE;
	}
	
	
	
	private static StringBlock createAlphabeticalSeparation() {
		StringBlock ret = new StringBlock("M");
		ret.getBlock("G");
		ret.getBlock("B");
		ret.getBlock("J");
		ret.getBlock("U");
		ret.getBlock("Q");
		ret.getBlock("W");
		return ret;
	}
	public static void main(String[] args) {
		// Initializes the data structure
		
		people = new PeopleBlock[ARRAYSIZE];
		mainStringBlocks = new StringBlock[NPARAMETERS];
		// Creates an alphabetical order in each StringBlock
		for(int i = 0; i<NPARAMETERS;i++)
			mainStringBlocks[i] = createAlphabeticalSeparation();
		
		
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
