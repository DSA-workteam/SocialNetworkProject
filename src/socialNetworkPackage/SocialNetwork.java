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
	
	public final static int NPARAMETERS = 9;
	
	private final static int ARRAYSIZE = 1024;
	public final static int ID = -1, NAME =0, SURNAME = 1, BIRTHDATE = 2, BIRTHPLACE = 3, HOME = 4,STUDIEDAT = 5,
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
		System.out.println("Person added: " + p.id);
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
	
	private static Person[] searchPersonBy(int key, String value) {
		Person[] p = null;
		if(key == -1) {
			 p = new Person[1];
			p[0] =people[encoder(value)].getPerson(value);
		}else {
			StringBlock sb = mainStringBlocks[key].getBlock(value, false);
			
			if(sb != null) {
				int size = sb.ids.size();
				p = new Person[size];
				for(int i = 0; i < size;i++)
					p[i] = sb.ids.get(i);
					
				
			}
		}
		return p;
	}
	private static void testAddingPeople() {
		Person p = new Person("Lmao");
		String[] s = {"Donostia"};
		p.setParameter(HOME, s);
		String[] s2 = {"Donramon","Pokemon"};
		p.setParameter(MOVIES, s2);
		p.print();
		addPeopleToSocialNetwork(p);
	}
	
	private static void testAddingPeople2() {
		Person p = new Person("NobitaNobi");
		String[] s = {"Tokio"};
		p.setParameter(HOME, s);
		String[] s2 = {"Digimon","Donramon"};
		p.setParameter(MOVIES, s2);
		p.print();
		addPeopleToSocialNetwork(p);
	}
	
	private static StringBlock createAlphabeticalSeparation() {
		StringBlock ret = new StringBlock("M");
		ret.getBlock("G",true);
		ret.getBlock("B",true);
		ret.getBlock("J",true);
		ret.getBlock("U",true);
		ret.getBlock("Q",true);
		ret.getBlock("W",true);
		return ret;
	}
	public static void main(String[] args) {
		// Initializes the data structure
		
		people = new PeopleBlock[ARRAYSIZE];
		for(int i = 0; i < ARRAYSIZE;i++)
			people[i] = new PeopleBlock();
		mainStringBlocks = new StringBlock[NPARAMETERS];
		// Creates an alphabetical order in each StringBlock
		for(int i = 0; i<NPARAMETERS;i++)
			mainStringBlocks[i] = createAlphabeticalSeparation();
		
		
		// Menu constants
		final int END = -1, HELP = 0, LOADP = 1, LOADR = 2, PRINT = 3, SEARCH = 4;
		
		testAddingPeople();
		testAddingPeople2();
		
		//System.out.println(searchPersonBy(-1, "Lmao")[0].getParameter(HOME)[0]);
		
		//System.out.println(searchPersonBy(HOME, "Donostia")[0].id);
		
		System.out.println(searchPersonBy(MOVIES, "Donramon")[0].id);
		System.out.println(searchPersonBy(MOVIES, "Donramon")[1].id);


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
