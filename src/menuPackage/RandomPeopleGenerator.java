package menuPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

import dataStructuresImplemented.DataHolder;
import dataStructuresImplemented.Person;
import exceptions.ImpulsoryAttributeRequiredException;

/**
 * This class is a singleton and generates random Person objects from a sample of names, surnames, genders, cities and movies list.
 * @author Borja Moralejo Tobajas
 *
 */
public class RandomPeopleGenerator {
	private Random random;
	private String[] names, surnames, genders, cities, movies;
	
	private RandomPeopleGenerator() {
		names = loadSampleTextsFromFile("Names", 200);
		surnames = loadSampleTextsFromFile("Surnames", 200);
		cities = loadSampleTextsFromFile("Cities", 100);
		movies = loadSampleTextsFromFile("Movies", 100);
		genders = loadSampleTextsFromFile("Genders", 51);
		random = new Random();
		
	}
	private final int PROB_OF_NULLATTRIBUTE = 10;
	
	// Singleton
	private static RandomPeopleGenerator instance;
	/**
	 * Singleton's instance getter
	 * @return
	 */
	public static RandomPeopleGenerator getInstance() {
		if(instance == null) instance = new RandomPeopleGenerator();
		return instance;		
	}
	
	
	/**
	 * Generates a random Person
	 * @return Person {@link Person}
	 */
	public Person generateRandomPerson() {
		
		String name = names[random.nextInt(200)];
		// Id and name
		String data = name.substring(0,random.nextInt(name.length())) + random.nextInt(2000)+ ","+name+",";
		
		// Surnames
		if(random.nextInt(100) >= PROB_OF_NULLATTRIBUTE) {				
			int nSurnames = random.nextInt(3)+1;
			for(int i = 0; i < nSurnames;i++)
				if( i != nSurnames-1)
				data += surnames[random.nextInt(200)] + ";";
				else 
					data += surnames[random.nextInt(200)];
		}
		data += ",";
		
		
		// birthdate and gender
		if(random.nextInt(100) <= PROB_OF_NULLATTRIBUTE) // Skips birth date or not
			data += ",";
		else
			data+=(random.nextInt(29)+1)+"-"+(random.nextInt(12)+1)+"-"+(1950+random.nextInt(60))+",";
		
		 
		//Gender
		 if(random.nextInt(100) <= PROB_OF_NULLATTRIBUTE) // skips gender or not
			 data += ",";
		 else data+= genders[random.nextInt(51)]+",";
		 
		 // Birthplace
		if(random.nextInt(100) <= PROB_OF_NULLATTRIBUTE)
			data += ",";
		else data += cities[random.nextInt(100)]+",";
		
		
		
		if(random.nextInt(100) >= PROB_OF_NULLATTRIBUTE) { // Skips home or not
		int nHome = random.nextInt(2)+1;
		for(int i = 0; i < nHome;i++)
			if(i != nHome-1)
				data+= cities[random.nextInt(100)]+ ";";
			else data+= cities[random.nextInt(100)];
		
		}
		data += ",";
		

		if(random.nextInt(100) >= PROB_OF_NULLATTRIBUTE) { // Skips studied at or not
		int nStudy = random.nextInt(2)+1;
		for(int i = 0; i < nStudy;i++)
			if(i != nStudy-1)
				data+= cities[random.nextInt(100)]+ ";";
			else data+= cities[random.nextInt(100)];
		
		}
		data += ",";
		
		

		if(random.nextInt(100) >= PROB_OF_NULLATTRIBUTE) { // Skips worked at or not
		int nWork = random.nextInt(2)+1;
		for(int i = 0; i < nWork;i++)
			if(i != nWork-1)
				data+= cities[random.nextInt(100)]+ ";";
			else data+= cities[random.nextInt(100)];
		
		}
		data += ",";
		
		
		if(random.nextInt(100) >= PROB_OF_NULLATTRIBUTE) { // Skips movies or not
			int nMovies = random.nextInt(3);
			for(int i = 0; i < nMovies;i++)
				if(i != nMovies-1)
					data+= movies[random.nextInt(100)]+ ";";
				else data+= movies[random.nextInt(100)];
			
			}
			data += ",";
		
		if(random.nextInt(100) >= PROB_OF_NULLATTRIBUTE) { // Skips movies or not
			data+= "G"+(random.nextInt(200)*random.nextInt(4)+random.nextInt(10));
		}
		
		Person ret = null;
		try {
			ret = new Person(data);
		} catch (ImpulsoryAttributeRequiredException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Private method for getting the sample values of the attributes
	 * @param fileName - String
	 * @param size - int. Number of items
	 * @return String[]
	 */
	private String[] loadSampleTextsFromFile(String fileName, int size) {
		String[] ret = new String[size];
		String path = System.getProperty("user.dir") +"\\res\\ResourcesForRandomGenerator\\"+ fileName+".txt";
		File f = new File(path);
		try {
			Scanner s = new Scanner(f);
			s.useDelimiter("\n");

			
				for(int i = 0; i < size; i++) {
					ret[i] = s.nextLine();
				}
			
			s.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found for random generator: " + path);
			System.out.println();
		}
		return ret;
	}
	
	/**
	 * Creates a single random relationship between to people, it could be that the relationships was already created and this doesn't do anything then.
	 */
	public void createRandomRelationship() {
		int size = DataHolder.getInstance().getNumberOfPeople();
		Collection<Person> c = DataHolder.getInstance().getPeople();
		if(size > 1) {
			Person p1 = (Person) c.toArray()[random.nextInt(size-1)];
			Person p2 = (Person) c.toArray()[random.nextInt(size-1)];
			if(p1 != p2)
				p1.createRelationshipWith(p2);
		}

	}
	
}
