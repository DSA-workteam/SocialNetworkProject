package socialNetworkPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import abstractDataTypesImplemented.GenericArrayListHashMap;
import adt.DataBlockADT;
import adt.HashMapADT;
import dataStructuresImplemented.ArrayListDataBlock;
import dataStructuresImplemented.Person;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;



/**
 * This is the main class which contains the data while in runtime. The data is stored in hash maps. The only thing that is not stored here is the friends relationships. They are stored in Person class, inside one of their attributes.
 * @author Borja Moralejo Tobajas
 *
 */
public class DataHolder{

	
	private static HashMapADT<DataBlockADT<String,String>, String>[] stringHashMaps;	
	private static HashMapADT<Person, String> personHashMap;
	
	/**
	 * Constructor of the class. Input the desired initial size of the hash maps.
	 * @param hashMapsSize - int. Desired size.
	 */
	@SuppressWarnings("unchecked")
	public DataHolder(int hashMapsSize) {
		personHashMap = new GenericArrayListHashMap<Person, String>(hashMapsSize);
		
		stringHashMaps = (HashMapADT<DataBlockADT<String, String>, String>[]) Array.newInstance(GenericArrayListHashMap.class, Person.NPARAMETERS-1);
		
		for(int i = 0; i < Person.NPARAMETERS-1;i++)
			stringHashMaps[i] = new GenericArrayListHashMap<DataBlockADT<String, String>, String>(hashMapsSize);
	}
	
	/**
	 * Gets the attribute's data block with the given key, if there isn't any, it throws ElementNotFoundException.
	 * @param attribute - int. Person's public static constant ints are used here.
	 * @param key - String. Wanted key inside of the data block.
	 * @return datablock - DataBlockADT<String, String>.
	 * @throws ElementNotFoundException
	 */
	private static DataBlockADT<String, String> getDataBlockOf(int attribute, String key) throws ElementNotFoundException{
		// Searches inside the conflicts inside the hash map
		Iterator<DataBlockADT<String, String>> resultsFromHashMap = stringHashMaps[attribute].get(key).iterator();
        DataBlockADT<String, String> ret = null;        
        
        boolean found = false;
        // Searches inside the collection the data block
        while(!found && resultsFromHashMap.hasNext()) {
            ret = resultsFromHashMap.next();
            if(key.equals(ret.getKey())) {
                found = true;
            }            
        }
        
        if(!found) 
            throw new ElementNotFoundException("There's not such stringdatablock");
            
        return ret;
	}
	
	
	
	/**
	 * This private function returns a data block with the requested key and requested attribute, if an ElementNotFoundException is thrown, it creates a new one, adds it into the hash map and returns that.
	 * @param attribute - int. Requested attribute. Use Person's public static final ints for this.
	 * @param key - String. Wanted key.
	 * @return DataBlockADT<String, String>. It returns an object, it could be recently created or from the hash map created.
	 */
	private DataBlockADT<String, String> constructorUseDataBlock(int attribute, String key) {
		DataBlockADT<String, String> ret;
		try {
		ret = getDataBlockOf(attribute, key);
		}catch(ElementNotFoundException e) {
			ret = new ArrayListDataBlock<String, String>(key);
			stringHashMaps[attribute].put(key, ret);		
		}
		
		return ret;
	}
	
	
	
	
	
	
	
	/**
	 * Adds person into the network data base, it takes the related data blocks and adds Person p's id into it.
	 * @param p - Person.
	 */
	public void addPersonToNetwork(Person p) {
		String id = p.getAttribute(Person.ID)[0];
		if(!personHashMap.isIn(id, p)) {
			
			// Put the person in the Person Hash map
			personHashMap.put(id, p);		
			
			// Put the person's attributes in the corresponding StringDataBlock
			DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
			
			for(int i = 0; i < attributes.length; i++)
				if(attributes[i] != null)
				for(int j = 0; j < attributes[i].length;j++) {
					attributes[i][j] = constructorUseDataBlock(i, attributes[i][j].getKey());
					attributes[i][j].add(id);
				}
			
		}else {
			// Already on the network, duplicated
		}
		
	}	
	
	
	/**
	 * Removes person from the network data and if the person's data block is left empty, it removes the data block from the hash map.
	 * @param p - Person.
	 * @throws ElementNotFoundException. Throws this exception if the Person p isn't in the network data.
	 */
	public void removePersonFromNetwork(Person p) throws ElementNotFoundException{
		String id = p.getAttribute(Person.ID)[0];
		if(personHashMap.remove(id, p)){
			DataBlockADT<String, String>[][] attributes = p.getDataBlocks();
			for(int i = 0; i < attributes.length; i++)
				if(attributes[i] != null)
				for(int j = 0; j < attributes[i].length;j++) {
					
					DataBlockADT<String, String> dataBlock = attributes[i][j];				
					// If the collection of datablock is empty, removes that datablock from the hashmap
					
					if(attributes[i][j].remove(id) ) {
						if(attributes[i][j].size() == 0 )
							stringHashMaps[i].remove(dataBlock.getKey(), dataBlock);
					}
				}
			
		}else {
			throw new ElementNotFoundException(p.toString());	
		}
		
	}
	
	/**
	 * Gets person by its id.
	 * @param id - String.
	 * @return Person - Person with the given id.
	 * @throws ElementNotFoundException
	 */
	public static Person getPersonByID(String id) throws ElementNotFoundException{
		Collection<Person> people = personHashMap.get(id);
		Person ret = null;
		for(Person p : people) {
			if(id.equals(p.getAttribute(Person.ID)[0])) {
				ret = p;
			}
		}
		if(ret == null)
			throw new ElementNotFoundException("The person with the id "+id+" isn't in the network");
		return ret;
	}
	
	
	/**
	 * Searches people by the given attribute. It returns the group that matches the search.
	 * @param attribute - int. Use Person class' public static final ints for this.
	 * @param value - String search value.
	 * @return Person[] group of people that matches the search.
	 * @throws ElementNotFoundException
	 */
	public static Person[] searchPeopleByAttribute(int attribute, String value) throws ElementNotFoundException{
		// TODO
		boolean found = false;
		Person[] ret = null;
		
		if(attribute == -1) {
			ret = new Person[1];
			ret[0] = getPersonByID(value);
		}
		else {
		}
		return ret;
		
	}
	
	
	
	
	
	/**
	 * Loads file and then loads people that is stored inside.
	 * @param fileName - String.
	 * @param option - Indicates if this method will load people or load relationships
	 */
	public void loadFile(String fileName, int option) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName+".txt";
		File f = new File(path);
		try {
			Scanner s = new Scanner(f);
			s.useDelimiter("\n");
			if (option == 0)
				while(s.hasNext())
					loadPerson(s.nextLine());
			else {
				s.nextLine();
				while (s.hasNext())
					loadRelationship(s.nextLine());
			}
			s.close();
			System.out.println("All people loaded perfectly");
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("You introduced an invalid filename.");
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
		
		System.out.println(data);
		String[] separatedID = data.split(",");
		try {
			person1 = getPersonByID(separatedID[0]);
			person2 = getPersonByID(separatedID[1]);
			person1.getNode().link(person2.getNode());
			person2.getNode().link(person1.getNode());
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
		System.out.println(data);
		try {
			addPersonToNetwork(new Person(data));
		} catch (ImpulsoryAttributeRequiredException e) {			
			e.printStackTrace();
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
			personHashMap.iterator().forEachRemaining(person -> {writerPrinter.print(person.toString());});;
		
			writerPrinter.close();
			System.out.println("Done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}

