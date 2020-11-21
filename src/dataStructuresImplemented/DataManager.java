package dataStructuresImplemented;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import enums.PersonAttributesEnum;
import exceptions.AlreadyOnTheCollectionException;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;

/**
 * This class loads/prints data from/to files. 
 * @author Borja Moralejo Tobajas
 *
 */
public class DataManager {
	
	
	// Singleton
	
		private static DataManager instance;
		
		/**
		 * Singleton's instance getter
		 * @return {@link DataHolder}
		 */
		public static DataManager getInstance() {
			if(instance == null)instance = new DataManager();
			
			return instance;
		}
		
		private DataManager() {
			
		}
	

	/**
	 * Loads file and then loads people that is stored inside.
	 * @param fileName - String.
	 * @param option - 0 = load people; 1 = load relationships; 2 = load residence.
	 */
	public void loadFile(String fileName, int option) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName+".txt";
		Stopwatch stopwatch = new Stopwatch();
		File f = new File(path);
		try {
			Scanner s = new Scanner(f);
			s.useDelimiter("\n");
			s.nextLine();

			if (option == 0)
				while(s.hasNext())
					loadPerson(s.nextLine());
			else if (option == 1)
				while (s.hasNext())
					loadRelationship(s.nextLine());
			else
				while (s.hasNext())
					loadResidential(s.nextLine());
			s.close();
			System.out.println();
			System.out.println("Elapsed time loading the file: "+ stopwatch.elapsedTime());
			System.out.println("Load process finished");
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + path);
			System.out.println();
		}
		
		
	}
	
	/**
	 * Takes the input and links the nodes of the 2 given ID, separated by ","
	 * @param data 2 String separated by ","
	 */
	public void loadRelationship(String data) {
		Person person1;
		Person person2;
		
		String[] separatedID = data.split(",");
		try {
			person1 = DataHolder.getInstance().getPersonByID(separatedID[0]);
			person2 = DataHolder.getInstance().getPersonByID(separatedID[1]);
			person1.createRelationshipWith(person2);
			

		}
		catch(ElementNotFoundException e) {
			System.out.println("At least one of the ID introduced to make the relationship with doesn't exist");
		}
		
		
	}
	
	
	/**
	 * Given an ID, prints all the people who where born in the same place as where the person with the given ID lives. Only prints name, surname, birthplace and where they studied
	 * @param data Given ID
	 */
	private void loadResidential(String data) {
		Person person;
		String birthplace[];
		Person[] people;
		System.out.println();
		try {
			// Gets the person and defines the variable home for easier manipulation
			person = DataHolder.getInstance().getPersonByID(data);
			birthplace = person.getAttribute(PersonAttributesEnum.BIRTHPLACE);
			// Iterates with all the homes that the person with the given ID has
			for (String home: birthplace) {
				
				try {
					
					people = DataHolder.getInstance().searchPeopleByAttribute(PersonAttributesEnum.HOME, home);
					System.out.println();
					System.out.println("People that live in " + home);
					System.out.println();
					
					// Iterates through all the people that have the correct birthplace and prints their name, surname, birthplace and study location
					for (Person rPerson: people)
						System.out.println(rPerson.attributeToString(PersonAttributesEnum.NAME) + ", " + rPerson.attributeToString(PersonAttributesEnum.SURNAME) + ", " + rPerson.attributeToString(PersonAttributesEnum.HOME) + ", " + rPerson.attributeToString(PersonAttributesEnum.STUDIEDAT));
				}
				catch (ElementNotFoundException e){
					System.err.println("No one lives in "+home);
				}
			}
		//There's no one with the given ID
		} catch (ElementNotFoundException e) {
			System.err.println("There's no one with the given ID inside our Data base");
		}
		System.out.println();
	}
	
	
	/**
	 * Takes the input and converts it into a Person object.
	 * @param data String - csv format.
	 */
	private void loadPerson(String data) {
		try {
			DataHolder.getInstance().addPersonToNetwork(new Person(data));
		} catch (ImpulsoryAttributeRequiredException e) {			
			e.printStackTrace();
		} catch(AlreadyOnTheCollectionException e) {
			
		}
		
	}
	
	
	/**
	 * Prints all people in the network into the given file.
	 * @param fileName - String. Wanted file name.
	 */
	public void printIntoFile(String fileName) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName+".txt";
		File writeFile = new File(path);
		try {
			PrintWriter writerPrinter = new PrintWriter(writeFile);
			//TODO put this with actual attributes you lazy
			writerPrinter.println("id"+",".repeat(PersonAttributesEnum.values().length-1));
			DataHolder.getInstance().getPeople().iterator().forEachRemaining(person -> {writerPrinter.println(person.toString());});;
		
			writerPrinter.close();
			System.out.println("Done");
		} catch (FileNotFoundException e) {
		}
		
	}
	
	
	/**
	 * Prints all relationships in the network into the given file.
	 * @param fileName - String. Wanted file name.
	 */
	public void printRelationshipsIntoFile(String fileName) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName+".txt";
		File writeFile = new File(path);
		try {
			PrintWriter writerPrinter = new PrintWriter(writeFile);
			writerPrinter.println("Id1,Id2");
			DataHolder.getInstance().getPeople().iterator().forEachRemaining(person -> {
				writerPrinter.print(person.getNode().toString());
				//try {
				//	DataHolder.getInstance().removeRelationshipsOfPerson(person);
				//} catch (ElementNotFoundException e) {					
				//	e.printStackTrace(); // Should not give this error
				//}
				
				
			});;
		
			writerPrinter.close();
			//System.out.println("Reloading relationships");
			System.out.println("Done printing relationships");
			//DataManager.getInstance().loadFile(fileName,1);
		} catch (FileNotFoundException e) {
		}
		
	}
	
	
	/**
	 * Prints given people into a new file, which name is a given name
	 * @param filename Name of the file
	 * @param people People to print into the file
	 */
	public void printPeoplesFriendsIntoFile(String fileName, Collection<Collection<Person>> friendsCollection) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName +".txt";
		File writeFile = new File(path);
		Person personToPrint = null;
		try {
			PrintWriter writerPrinter = new PrintWriter(writeFile);
			writerPrinter.println("id"+",".repeat(PersonAttributesEnum.values().length-1));
			Iterator<Collection<Person>> it = friendsCollection.iterator();
			while (it.hasNext()) { // Iterates over all the people with the same surname
				Iterator<Person> itF = it.next().iterator();
				writerPrinter.println();
				writerPrinter.println(itF.next().getAttribute(PersonAttributesEnum.ID)[0] + "'s friends:");
				writerPrinter.println();
				while (itF.hasNext()) { // Iterates over all the friends
					personToPrint = itF.next();
					writerPrinter.println("   " + personToPrint.attributeToString(PersonAttributesEnum.ID) + ", " + personToPrint.attributeToString(PersonAttributesEnum.SURNAME));
				}
				if(personToPrint == null)
					writerPrinter.println("He has no friends at the moment...");
			}
			writerPrinter.close();
			System.out.println("The printing process has been completed succesfully");
		}
		catch (FileNotFoundException e) {
		}
	}
	
	
}
