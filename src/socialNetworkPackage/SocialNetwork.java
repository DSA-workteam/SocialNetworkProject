package socialNetworkPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import socialNetworkPackage.DataStructures.PeopleBlock;
import socialNetworkPackage.DataStructures.Person;

public class SocialNetwork {

	// The variable where all the data from the social network is going to be
	private static PeopleBlock[] people;
	private final static int ARRAYSIZE = 1024;
	
	
	
	public static void addPeopleToSocialNetwork(Person p) {
		people[encoder(p.id)].addPerson(p);
	}
	
	private static int encoder(String s) {
		int r = 0;
		for (int i = 0; i < s.length(); i++) {
			r += (int)s.charAt(i)*i;
		}
		
		
		return r % ARRAYSIZE;
	}
	
	
	public static void main(String[] args) {
		// Initializes the array list
		
		people = new PeopleBlock[ARRAYSIZE];
		
		
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
