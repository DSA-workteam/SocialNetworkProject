package socialNetworkPackage;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import adt.DataBlockADT;
import dataStructuresImplemented.Person;
import exceptions.ImpulsoryAttributeRequiredException;



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
	
	
	private static DataHolder dh;
	
	
	private static void testAddingPeople() {
		Person p = null;
		try {
			p = new Person("Silvia3,Silvia,Ruiz,20-06-2001,Madrid,Donostia,Madrid;Donostia,-,Cadena Perpetua;Your voice,G25527");
			System.out.println(p.toString());
			
		} catch (ImpulsoryAttributeRequiredException e) {
			e.printStackTrace();
		}
		//addPeopleToSocialNetwork(p);
		dh.addPersonToNetwork(p);
		
		
		//System.out.println(p.getAttributesRelatedDataBlocks(NAME));
		//System.out.println(DataHolder.getPersonByID("Silvia3").toString());
	}
	private static void testAddingPeople2() {
		Person p = null;
		try {
			p = new Person("Pepe77,Don Pepe,Balloon,3-10-2003,Los Angeles,San Francisco,San Francisco,New York,Tiana;Cadena Perpetua,G77371");
			System.out.println(p.toString());
			
		} catch (ImpulsoryAttributeRequiredException e) {
			e.printStackTrace();
		}
		//addPeopleToSocialNetwork(p);
		dh.addPersonToNetwork(p);
		
		
		System.out.println(p.getAttributesRelatedDataBlocks(MOVIES)[1].getKey());
		System.out.println(p.getAttributesRelatedDataBlocks(MOVIES)[1].getCollection());
		DataBlockADT<String,String> db = p.getAttributesRelatedDataBlocks(MOVIES)[1];
		dh.removePersonFromNetwork(p);
		System.out.println(db.getCollection());
		//System.out.println(DataHolder.getPersonByID("Silvia3").toString());
	}
	
	

	public static void main(String[] args) {
		// Initializes the data structure
		
	
		dh = new DataHolder(100);
		// Menu constants
		final int END = -1, HELP = 0, LOADP = 1, LOADR = 2, PRINT = 3, SEARCH = 4;
		
		testAddingPeople();
		testAddingPeople2();
		
	


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
