package dataStructuresImplemented;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	 * @param option - 0 = load people; 1 = load relationships.
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
			else {
				while (s.hasNext())
					loadRelationship(s.nextLine());
			}
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
				try {
					DataHolder.getInstance().removeRelationshipsOfPerson(person);
				} catch (ElementNotFoundException e) {					
					e.printStackTrace(); // Should not give this error
				}
				
				
			});;
		
			writerPrinter.close();
			System.out.println("Reloading relationships");
			System.out.println("Done printing relationships");
			DataManager.getInstance().loadFile(fileName,1);
		} catch (FileNotFoundException e) {
		}
		
	}
	
	
	
}
